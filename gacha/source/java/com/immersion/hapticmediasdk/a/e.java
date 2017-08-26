package com.immersion.hapticmediasdk.a;

import com.immersion.hapticmediasdk.b.b;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public class e {
    public static int a = 1;
    public static int b = 2;
    public static int c = 0;
    public static int d = 3;
    private DefaultHttpClient e;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private e() {
        /*
        r2 = this;
        r2.<init>();
        r0 = b();
        r1 = a;
        r0 = r0 + r1;
        r1 = b();
        r0 = r0 * r1;
        r1 = b;
        r0 = r0 % r1;
        r1 = c;
        if (r0 == r1) goto L_0x001c;
    L_0x0016:
        r0 = b();
        c = r0;
    L_0x001c:
        r0 = 0;
    L_0x001d:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x001d;
            case 1: goto L_0x0026;
            default: goto L_0x0021;
        };
    L_0x0021:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0026;
            case 1: goto L_0x001d;
            default: goto L_0x0025;
        };
    L_0x0025:
        goto L_0x0021;
    L_0x0026:
        r2.e = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.e.<init>():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.immersion.hapticmediasdk.a.e a() {
        /*
        r0 = new com.immersion.hapticmediasdk.a.e;
        r0.<init>();
        r0.d();
    L_0x0008:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0011;
            case 1: goto L_0x0008;
            default: goto L_0x000c;
        };
    L_0x000c:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0011;
            default: goto L_0x0010;
        };
    L_0x0010:
        goto L_0x000c;
    L_0x0011:
        r1 = b();
        r2 = a;
        r2 = r2 + r1;
        r1 = r1 * r2;
        r2 = b;
        r1 = r1 % r2;
        switch(r1) {
            case 0: goto L_0x0023;
            default: goto L_0x001f;
        };
    L_0x001f:
        r1 = 31;
        a = r1;
    L_0x0023:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.e.a():com.immersion.hapticmediasdk.a.e");
    }

    private HttpResponse a(HttpUriRequest httpUriRequest, Map map, int i) {
        URI uri = httpUriRequest.getURI();
        String trim = uri.getHost() != null ? uri.getHost().trim() : BuildConfig.FLAVOR;
        if (trim.length() > 0) {
            httpUriRequest.setHeader("Host", trim);
            if (((d + a) * d) % b != c) {
                d = 43;
                c = 98;
            }
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                httpUriRequest.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Header[] allHeaders = httpUriRequest.getAllHeaders();
        b.a("ImmersionHttpClient", "request URI [" + httpUriRequest.getURI() + "]");
        for (Object obj : allHeaders) {
            b.a("ImmersionHttpClient", "request header [" + obj.toString() + "]");
        }
        HttpConnectionParams.setSoTimeout(this.e.getParams(), i);
        HttpResponse execute = this.e.execute(httpUriRequest);
        if (execute != null) {
            return execute;
        }
        throw new RuntimeException("Null response returned.");
    }

    public static int b() {
        return 26;
    }

    public static int c() {
        return 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
        r6 = this;
        r2 = 0;
        r0 = r6.e;
        if (r0 != 0) goto L_0x005f;
    L_0x0005:
        r0 = d;
        r1 = a;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = b;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0019;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = 66;
        d = r0;
        r0 = 39;
        c = r0;
    L_0x0019:
        r0 = new org.apache.http.params.BasicHttpParams;
        r0.<init>();
    L_0x001e:
        switch(r2) {
            case 0: goto L_0x0025;
            case 1: goto L_0x001e;
            default: goto L_0x0021;
        };
    L_0x0021:
        switch(r2) {
            case 0: goto L_0x0025;
            case 1: goto L_0x001e;
            default: goto L_0x0024;
        };
    L_0x0024:
        goto L_0x0021;
    L_0x0025:
        r1 = 5;
        org.apache.http.conn.params.ConnManagerParams.setMaxTotalConnections(r0, r1);
        r1 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r0, r1);
        r1 = new org.apache.http.conn.scheme.SchemeRegistry;
        r1.<init>();
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "http";
        r4 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();
        r5 = 80;
        r2.<init>(r3, r4, r5);
        r1.register(r2);
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "https";
        r4 = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
        r5 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
        r2.<init>(r3, r4, r5);
        r1.register(r2);
        r2 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
        r2.<init>(r0, r1);
        r1 = new org.apache.http.impl.client.DefaultHttpClient;
        r1.<init>(r2, r0);
        r6.e = r1;
    L_0x005f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.e.d():void");
    }

    public HttpResponse a(String str, Map map, int i) {
        int i2 = d;
        switch ((i2 * (c() + i2)) % b) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                d = b();
                c = b();
                break;
        }
        try {
            try {
                return a(new HttpGet(str), map, i);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
