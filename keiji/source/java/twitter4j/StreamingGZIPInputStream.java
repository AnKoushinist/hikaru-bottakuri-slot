package twitter4j;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;

final class StreamingGZIPInputStream extends GZIPInputStream {
    private final InputStream wrapped;

    public StreamingGZIPInputStream(InputStream inputStream) {
        super(inputStream);
        this.wrapped = inputStream;
    }

    public int available() {
        return this.wrapped.available();
    }
}
