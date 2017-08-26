package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.picasso.Downloader.Response;
import com.squareup.picasso.Downloader.ResponseException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import twitter4j.HttpResponseCode;

public class OkHttpDownloader implements Downloader {
    static final String RESPONSE_SOURCE_ANDROID = "X-Android-Response-Source";
    static final String RESPONSE_SOURCE_OKHTTP = "OkHttp-Response-Source";
    private final OkUrlFactory urlFactory;

    public OkHttpDownloader(Context context) {
        this(Utils.createDefaultCacheDir(context));
    }

    public OkHttpDownloader(File file) {
        this(file, Utils.calculateDiskCacheSize(file));
    }

    public OkHttpDownloader(Context context, long j) {
        this(Utils.createDefaultCacheDir(context), j);
    }

    public OkHttpDownloader(File file, long j) {
        this(new OkHttpClient());
        try {
            this.urlFactory.client().setCache(new Cache(file, j));
        } catch (IOException e) {
        }
    }

    public OkHttpDownloader(OkHttpClient okHttpClient) {
        this.urlFactory = new OkUrlFactory(okHttpClient);
    }

    protected HttpURLConnection openConnection(Uri uri) {
        HttpURLConnection open = this.urlFactory.open(new URL(uri.toString()));
        open.setConnectTimeout(15000);
        open.setReadTimeout(20000);
        return open;
    }

    protected OkHttpClient getClient() {
        return this.urlFactory.client();
    }

    public Response load(Uri uri, boolean z) {
        HttpURLConnection openConnection = openConnection(uri);
        openConnection.setUseCaches(true);
        if (z) {
            openConnection.setRequestProperty("Cache-Control", "only-if-cached,max-age=2147483647");
        }
        int responseCode = openConnection.getResponseCode();
        if (responseCode >= HttpResponseCode.MULTIPLE_CHOICES) {
            openConnection.disconnect();
            throw new ResponseException(responseCode + " " + openConnection.getResponseMessage());
        }
        String headerField = openConnection.getHeaderField(RESPONSE_SOURCE_OKHTTP);
        if (headerField == null) {
            headerField = openConnection.getHeaderField(RESPONSE_SOURCE_ANDROID);
        }
        long headerFieldInt = (long) openConnection.getHeaderFieldInt("Content-Length", -1);
        return new Response(openConnection.getInputStream(), Utils.parseResponseSourceHeader(headerField), headerFieldInt);
    }

    public void shutdown() {
        Cache cache = this.urlFactory.client().getCache();
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException e) {
            }
        }
    }
}
