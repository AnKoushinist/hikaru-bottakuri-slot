package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.io.RealConnection;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1xStream implements HttpStream {
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private HttpEngine httpEngine;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state = STATE_IDLE;
    private final StreamAllocation streamAllocation;

    private abstract class AbstractSource implements Source {
        protected boolean closed;
        protected final ForwardingTimeout timeout;

        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1xStream.this.source.timeout());
        }

        public Timeout timeout() {
            return this.timeout;
        }

        protected final void endOfInput(boolean z) {
            if (Http1xStream.this.state != Http1xStream.STATE_CLOSED) {
                if (Http1xStream.this.state != Http1xStream.STATE_READING_RESPONSE_BODY) {
                    throw new IllegalStateException("state: " + Http1xStream.this.state);
                }
                Http1xStream.this.detachTimeout(this.timeout);
                Http1xStream.this.state = Http1xStream.STATE_CLOSED;
                if (Http1xStream.this.streamAllocation != null) {
                    Http1xStream.this.streamAllocation.streamFinished(!z, Http1xStream.this);
                }
            }
        }
    }

    private final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        private ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void write(Buffer buffer, long j) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1xStream.this.sink.writeHexadecimalUnsignedLong(j);
                Http1xStream.this.sink.writeUtf8("\r\n");
                Http1xStream.this.sink.write(buffer, j);
                Http1xStream.this.sink.writeUtf8("\r\n");
            }
        }

        public synchronized void flush() {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        public synchronized void close() {
            if (!this.closed) {
                this.closed = true;
                Http1xStream.this.sink.writeUtf8("0\r\n\r\n");
                Http1xStream.this.detachTimeout(this.timeout);
                Http1xStream.this.state = Http1xStream.STATE_READ_RESPONSE_HEADERS;
            }
        }
    }

    private class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk = NO_CHUNK_YET;
        private boolean hasMoreChunks = true;
        private final HttpEngine httpEngine;

        ChunkedSource(HttpEngine httpEngine) {
            super();
            this.httpEngine = httpEngine;
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (!this.hasMoreChunks) {
                return NO_CHUNK_YET;
            } else {
                if (this.bytesRemainingInChunk == 0 || this.bytesRemainingInChunk == NO_CHUNK_YET) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return NO_CHUNK_YET;
                    }
                }
                long read = Http1xStream.this.source.read(buffer, Math.min(j, this.bytesRemainingInChunk));
                if (read == NO_CHUNK_YET) {
                    endOfInput(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.bytesRemainingInChunk -= read;
                return read;
            }
        }

        private void readChunkSize() {
            if (this.bytesRemainingInChunk != NO_CHUNK_YET) {
                Http1xStream.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1xStream.this.source.readHexadecimalUnsignedLong();
                String trim = Http1xStream.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0 || !(trim.isEmpty() || trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                } else if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    this.httpEngine.receiveHeaders(Http1xStream.this.readHeaders());
                    endOfInput(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() {
            if (!this.closed) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false);
                }
                this.closed = true;
            }
        }
    }

    private final class FixedLengthSink implements Sink {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout;

        private FixedLengthSink(long j) {
            this.timeout = new ForwardingTimeout(Http1xStream.this.sink.timeout());
            this.bytesRemaining = j;
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public void write(Buffer buffer, long j) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0, j);
            if (j > this.bytesRemaining) {
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + j);
            }
            Http1xStream.this.sink.write(buffer, j);
            this.bytesRemaining -= j;
        }

        public void flush() {
            if (!this.closed) {
                Http1xStream.this.sink.flush();
            }
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                Http1xStream.this.detachTimeout(this.timeout);
                Http1xStream.this.state = Http1xStream.STATE_READ_RESPONSE_HEADERS;
            }
        }
    }

    private class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long j) {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                endOfInput(true);
            }
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.bytesRemaining == 0) {
                return -1;
            } else {
                long read = Http1xStream.this.source.read(buffer, Math.min(this.bytesRemaining, j));
                if (read == -1) {
                    endOfInput(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.bytesRemaining -= read;
                if (this.bytesRemaining == 0) {
                    endOfInput(true);
                }
                return read;
            }
        }

        public void close() {
            if (!this.closed) {
                if (!(this.bytesRemaining == 0 || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
                    endOfInput(false);
                }
                this.closed = true;
            }
        }
    }

    private class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        private UnknownLengthSource() {
            super();
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.inputExhausted) {
                return -1;
            } else {
                long read = Http1xStream.this.source.read(buffer, j);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                endOfInput(true);
                return -1;
            }
        }

        public void close() {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false);
                }
                this.closed = true;
            }
        }
    }

    public Http1xStream(StreamAllocation streamAllocation, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.streamAllocation = streamAllocation;
        this.source = bufferedSource;
        this.sink = bufferedSink;
    }

    public void setHttpEngine(HttpEngine httpEngine) {
        this.httpEngine = httpEngine;
    }

    public Sink createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    public void writeRequestHeaders(Request request) {
        this.httpEngine.writingRequestHeaders();
        writeRequest(request.headers(), RequestLine.get(request, this.httpEngine.getConnection().route().proxy().type()));
    }

    public Builder readResponseHeaders() {
        return readResponse();
    }

    public ResponseBody openResponseBody(Response response) {
        return new RealResponseBody(response.headers(), Okio.buffer(getTransferStream(response)));
    }

    private Source getTransferStream(Response response) {
        if (!HttpEngine.hasBody(response)) {
            return newFixedLengthSource(0);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return newChunkedSource(this.httpEngine);
        }
        long contentLength = OkHeaders.contentLength(response);
        if (contentLength != -1) {
            return newFixedLengthSource(contentLength);
        }
        return newUnknownLengthSource();
    }

    public boolean isClosed() {
        return this.state == STATE_CLOSED;
    }

    public void finishRequest() {
        this.sink.flush();
    }

    public void writeRequest(Headers headers, String str) {
        if (this.state != 0) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.sink.writeUtf8(str).writeUtf8("\r\n");
        int size = headers.size();
        for (int i = STATE_IDLE; i < size; i += STATE_OPEN_REQUEST_BODY) {
            this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = STATE_OPEN_REQUEST_BODY;
    }

    public Builder readResponse() {
        if (this.state == STATE_OPEN_REQUEST_BODY || this.state == STATE_READ_RESPONSE_HEADERS) {
            Builder headers;
            StatusLine parse;
            do {
                try {
                    parse = StatusLine.parse(this.source.readUtf8LineStrict());
                    headers = new Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                } catch (Throwable e) {
                    IOException iOException = new IOException("unexpected end of stream on " + this.streamAllocation);
                    iOException.initCause(e);
                    throw iOException;
                }
            } while (parse.code == 100);
            this.state = STATE_OPEN_RESPONSE_BODY;
            return headers;
        }
        throw new IllegalStateException("state: " + this.state);
    }

    public Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String readUtf8LineStrict = this.source.readUtf8LineStrict();
            if (readUtf8LineStrict.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, readUtf8LineStrict);
        }
    }

    public Sink newChunkedSink() {
        if (this.state != STATE_OPEN_REQUEST_BODY) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = STATE_WRITING_REQUEST_BODY;
        return new ChunkedSink();
    }

    public Sink newFixedLengthSink(long j) {
        if (this.state != STATE_OPEN_REQUEST_BODY) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = STATE_WRITING_REQUEST_BODY;
        return new FixedLengthSink(j);
    }

    public void writeRequestBody(RetryableSink retryableSink) {
        if (this.state != STATE_OPEN_REQUEST_BODY) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = STATE_READ_RESPONSE_HEADERS;
        retryableSink.writeToSocket(this.sink);
    }

    public Source newFixedLengthSource(long j) {
        if (this.state != STATE_OPEN_RESPONSE_BODY) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = STATE_READING_RESPONSE_BODY;
        return new FixedLengthSource(j);
    }

    public Source newChunkedSource(HttpEngine httpEngine) {
        if (this.state != STATE_OPEN_RESPONSE_BODY) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = STATE_READING_RESPONSE_BODY;
        return new ChunkedSource(httpEngine);
    }

    public Source newUnknownLengthSource() {
        if (this.state != STATE_OPEN_RESPONSE_BODY) {
            throw new IllegalStateException("state: " + this.state);
        } else if (this.streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.state = STATE_READING_RESPONSE_BODY;
            this.streamAllocation.noNewStreams();
            return new UnknownLengthSource();
        }
    }

    private void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }
}
