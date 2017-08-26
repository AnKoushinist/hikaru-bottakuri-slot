package com.tapjoy.internal;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.trigger.TriggerMethod;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import twitter4j.HttpResponseCode;

public final class ci implements ch {
    private final String a;
    private final URL b;

    public ci(String str, URL url) {
        this.a = str;
        this.b = url;
    }

    public final Object a(ce ceVar) {
        URL url = new URL(this.b, ceVar.c());
        String b = ceVar.b();
        if ("GET".equals(b) || TriggerMethod.DELETE.equals(b)) {
            Map e = ceVar.e();
            if (!e.isEmpty()) {
                url = new URL(url, url.getPath() + Operation.EMPTY_PARAM + ei.a(e));
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) eh.a(url);
        httpURLConnection.setRequestMethod(b);
        httpURLConnection.setRequestProperty("User-Agent", this.a);
        for (Entry entry : ceVar.a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
        }
        if (!("GET".equals(b) || TriggerMethod.DELETE.equals(b))) {
            if ("POST".equals(b) || "PUT".equals(b)) {
                String d = ceVar.d();
                if (d == null) {
                    ei.a(httpURLConnection, "application/x-www-form-urlencoded", ei.a(ceVar.e()), co.c);
                } else if ("application/json".equals(d)) {
                    ei.a(httpURLConnection, "application/json; charset=utf-8", bl.a(ceVar.e()), co.c);
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
                    Object a = ceVar.a(toURI, inputStream);
                    return a;
                } finally {
                    inputStream.close();
                }
            default:
                throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
        }
    }
}
