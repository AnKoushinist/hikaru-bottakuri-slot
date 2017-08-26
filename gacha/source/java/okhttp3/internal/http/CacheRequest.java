package okhttp3.internal.http;

import okio.Sink;

public interface CacheRequest {
    void abort();

    Sink body();
}
