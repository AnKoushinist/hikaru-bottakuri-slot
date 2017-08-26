package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

public interface Downloader {

    public static class Response {
        final Bitmap bitmap;
        final boolean cached;
        final long contentLength;
        final InputStream stream;

        public Response(Bitmap bitmap, boolean z) {
            if (bitmap == null) {
                throw new IllegalArgumentException("Bitmap may not be null.");
            }
            this.stream = null;
            this.bitmap = bitmap;
            this.cached = z;
            this.contentLength = -1;
        }

        @Deprecated
        public Response(InputStream inputStream, boolean z) {
            this(inputStream, z, -1);
        }

        @Deprecated
        public Response(Bitmap bitmap, boolean z, long j) {
            this(bitmap, z);
        }

        public Response(InputStream inputStream, boolean z, long j) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Stream may not be null.");
            }
            this.stream = inputStream;
            this.bitmap = null;
            this.cached = z;
            this.contentLength = j;
        }

        public InputStream getInputStream() {
            return this.stream;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public long getContentLength() {
            return this.contentLength;
        }
    }

    public static class ResponseException extends IOException {
        public ResponseException(String str) {
            super(str);
        }
    }

    Response load(Uri uri, boolean z);

    void shutdown();
}
