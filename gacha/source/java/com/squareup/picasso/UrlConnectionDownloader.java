package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import com.squareup.picasso.Downloader.Response;
import com.squareup.picasso.Downloader.ResponseException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import twitter4j.HttpResponseCode;

public class UrlConnectionDownloader implements Downloader {
    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private static final Object lock = new Object();
    private final Context context;

    private static class ResponseCacheIcs {
        private ResponseCacheIcs() {
        }

        static Object install(Context context) {
            File createDefaultCacheDir = Utils.createDefaultCacheDir(context);
            Object installed = HttpResponseCache.getInstalled();
            if (installed == null) {
                return HttpResponseCache.install(createDefaultCacheDir, Utils.calculateDiskCacheSize(createDefaultCacheDir));
            }
            return installed;
        }

        static void close(Object obj) {
            try {
                ((HttpResponseCache) obj).close();
            } catch (IOException e) {
            }
        }
    }

    public UrlConnectionDownloader(Context context) {
        this.context = context.getApplicationContext();
    }

    protected HttpURLConnection openConnection(Uri uri) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        return httpURLConnection;
    }

    public Response load(Uri uri, boolean z) {
        if (VERSION.SDK_INT >= 14) {
            installCacheIfNeeded(this.context);
        }
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
        long headerFieldInt = (long) openConnection.getHeaderFieldInt("Content-Length", -1);
        return new Response(openConnection.getInputStream(), Utils.parseResponseSourceHeader(openConnection.getHeaderField(RESPONSE_SOURCE)), headerFieldInt);
    }

    public void shutdown() {
        if (VERSION.SDK_INT >= 14 && cache != null) {
            ResponseCacheIcs.close(cache);
        }
    }

    private static void installCacheIfNeeded(Context context) {
        if (cache == null) {
            try {
                synchronized (lock) {
                    if (cache == null) {
                        cache = ResponseCacheIcs.install(context);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
