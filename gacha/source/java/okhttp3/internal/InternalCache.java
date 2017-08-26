package okhttp3.internal;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.CacheRequest;
import okhttp3.internal.http.CacheStrategy;

public interface InternalCache {
    Response get(Request request);

    CacheRequest put(Response response);

    void remove(Request request);

    void trackConditionalCacheHit();

    void trackResponse(CacheStrategy cacheStrategy);

    void update(Response response, Response response2);
}
