package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import twitter4j.HttpResponseCode;

public final class cj implements ci {
    private final String a;
    private final URL b;

    public cj(String str, URL url) {
        this.a = str;
        this.b = url;
    }

    public final Object a(cf cfVar) {
        URL url = new URL(this.b, cfVar.c());
        String b = cfVar.b();
        if ("GET".equals(b) || "DELETE".equals(b)) {
            Map e = cfVar.e();
            if (!e.isEmpty()) {
                url = new URL(url, url.getPath() + "?" + en.a(e));
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) em.a(url);
        httpURLConnection.setRequestMethod(b);
        httpURLConnection.setRequestProperty("User-Agent", this.a);
        for (Entry entry : cfVar.a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
        }
        if (!("GET".equals(b) || "DELETE".equals(b))) {
            if ("POST".equals(b) || "PUT".equals(b)) {
                String d = cfVar.d();
                if (d == null) {
                    en.a(httpURLConnection, "application/x-www-form-urlencoded", en.a(cfVar.e()), cp.c);
                } else if ("application/json".equals(d)) {
                    en.a(httpURLConnection, "application/json; charset=utf-8", bm.a(cfVar.e()), cp.c);
                } else {
                    throw new IllegalArgumentException("Unknown content type: " + d);
                }
            }
            throw new IllegalArgumentException("Unknown method: " + b);
        }
        httpURLConnection.connect();
        switch (httpURLConnection.getResponseCode()) {
            case HttpResponseCode.OK /*200*/:
            case 201:
            case 409:
                URI toURI;
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    toURI = httpURLConnection.getURL().toURI();
                } catch (URISyntaxException e2) {
                    toURI = null;
                }
                try {
                    Object a = cfVar.a(toURI, inputStream);
                    return a;
                } finally {
                    inputStream.close();
                }
            default:
                throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
        }
    }
}
