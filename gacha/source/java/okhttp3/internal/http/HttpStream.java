package okhttp3.internal.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okio.Sink;

public interface HttpStream {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    void cancel();

    Sink createRequestBody(Request request, long j);

    void finishRequest();

    ResponseBody openResponseBody(Response response);

    Builder readResponseHeaders();

    void setHttpEngine(HttpEngine httpEngine);

    void writeRequestBody(RetryableSink retryableSink);

    void writeRequestHeaders(Request request);
}
