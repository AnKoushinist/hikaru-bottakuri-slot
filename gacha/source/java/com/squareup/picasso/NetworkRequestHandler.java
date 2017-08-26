package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import com.squareup.picasso.Downloader.Response;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import java.io.IOException;
import java.io.InputStream;

class NetworkRequestHandler extends RequestHandler {
    private static final int MARKER = 65536;
    static final int RETRY_COUNT = 2;
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";
    private final Downloader downloader;
    private final Stats stats;

    public NetworkRequestHandler(Downloader downloader, Stats stats) {
        this.downloader = downloader;
        this.stats = stats;
    }

    public boolean canHandleRequest(Request request) {
        String scheme = request.uri.getScheme();
        return SCHEME_HTTP.equals(scheme) || SCHEME_HTTPS.equals(scheme);
    }

    public Result load(Request request) {
        Response load = this.downloader.load(request.uri, request.loadFromLocalCacheOnly);
        if (load == null) {
            return null;
        }
        LoadedFrom loadedFrom = load.cached ? LoadedFrom.DISK : LoadedFrom.NETWORK;
        Bitmap bitmap = load.getBitmap();
        if (bitmap != null) {
            return new Result(bitmap, loadedFrom);
        }
        InputStream inputStream = load.getInputStream();
        if (inputStream == null) {
            return null;
        }
        if (load.getContentLength() == 0) {
            Utils.closeQuietly(inputStream);
            throw new IOException("Received response with 0 content-length header.");
        }
        LoadedFrom loadedFrom2 = LoadedFrom.NETWORK;
        if (loadedFrom == loadedFrom2) {
            loadedFrom2 = (load.getContentLength() > 0 ? 1 : (load.getContentLength() == 0 ? 0 : -1));
            if (loadedFrom2 > null) {
                loadedFrom2 = this.stats;
                loadedFrom2.dispatchDownloadFinished(load.getContentLength());
            }
        }
        try {
            loadedFrom2 = new Result(decodeStream(inputStream, request), loadedFrom);
            return loadedFrom2;
        } finally {
            Utils.closeQuietly(inputStream);
        }
    }

    int getRetryCount() {
        return RETRY_COUNT;
    }

    boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        return networkInfo == null || networkInfo.isConnected();
    }

    boolean supportsReplay() {
        return true;
    }

    private Bitmap decodeStream(InputStream inputStream, Request request) {
        InputStream markableInputStream = new MarkableInputStream(inputStream);
        long savePosition = markableInputStream.savePosition(MARKER);
        Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        boolean requiresInSampleSize = RequestHandler.requiresInSampleSize(createBitmapOptions);
        boolean isWebPFile = Utils.isWebPFile(markableInputStream);
        markableInputStream.reset(savePosition);
        if (isWebPFile) {
            byte[] toByteArray = Utils.toByteArray(markableInputStream);
            if (requiresInSampleSize) {
                BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            }
            return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, createBitmapOptions);
        }
        if (requiresInSampleSize) {
            BitmapFactory.decodeStream(markableInputStream, null, createBitmapOptions);
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            markableInputStream.reset(savePosition);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(markableInputStream, null, createBitmapOptions);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }
}
