package okhttp3.logging;

import com.d.a.a.c;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Platform;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;
import org.cocos2dx.lib.BuildConfig;

public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName(c.DEFAULT_CHARSET);
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            public void log(String str) {
                Platform.get().log(str);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return this.level;
    }

    public Response intercept(Chain chain) {
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        Object obj = level == Level.BODY ? 1 : null;
        Object obj2 = (obj != null || level == Level.HEADERS) ? 1 : null;
        RequestBody body = request.body();
        Object obj3 = body != null ? 1 : null;
        Connection connection = chain.connection();
        String str = "--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1);
        if (obj2 == null && obj3 != null) {
            str = str + " (" + body.contentLength() + "-byte body)";
        }
        this.logger.log(str);
        if (obj2 != null) {
            if (obj3 != null) {
                if (body.contentType() != null) {
                    this.logger.log("Content-Type: " + body.contentType());
                }
                if (body.contentLength() != -1) {
                    this.logger.log("Content-Length: " + body.contentLength());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                if (!("Content-Type".equalsIgnoreCase(name) || "Content-Length".equalsIgnoreCase(name))) {
                    this.logger.log(name + ": " + headers.value(i));
                }
            }
            if (obj == null || obj3 == null) {
                this.logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                this.logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Object buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                this.logger.log(BuildConfig.FLAVOR);
                this.logger.log(buffer.readString(charset));
                this.logger.log("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
            }
        }
        long nanoTime = System.nanoTime();
        Response proceed = chain.proceed(request);
        long toMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        ResponseBody body2 = proceed.body();
        long contentLength = body2.contentLength();
        this.logger.log("<-- " + proceed.code() + ' ' + proceed.message() + ' ' + proceed.request().url() + " (" + toMillis + "ms" + (obj2 == null ? ", " + (contentLength != -1 ? contentLength + "-byte" : "unknown-length") + " body" : BuildConfig.FLAVOR) + ')');
        if (obj2 != null) {
            Headers headers2 = proceed.headers();
            int size2 = headers2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.logger.log(headers2.name(i2) + ": " + headers2.value(i2));
            }
            if (obj == null || !HttpEngine.hasBody(proceed)) {
                this.logger.log("<-- END HTTP");
            } else if (bodyEncoded(proceed.headers())) {
                this.logger.log("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = body2.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer2 = source.buffer();
                Charset charset2 = UTF8;
                MediaType contentType2 = body2.contentType();
                if (contentType2 != null) {
                    try {
                        charset2 = contentType2.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        this.logger.log(BuildConfig.FLAVOR);
                        this.logger.log("Couldn't decode the response body; charset is likely malformed.");
                        this.logger.log("<-- END HTTP");
                        return proceed;
                    }
                }
                if (contentLength != 0) {
                    this.logger.log(BuildConfig.FLAVOR);
                    this.logger.log(buffer2.clone().readString(charset2));
                }
                this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
            }
        }
        return proceed;
    }

    private boolean bodyEncoded(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity")) ? false : true;
    }
}
