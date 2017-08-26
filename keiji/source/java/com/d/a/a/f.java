package com.d.a.a;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/* compiled from: HttpGet */
public final class f extends HttpEntityEnclosingRequestBase {
    public f(String str) {
        setURI(URI.create(str));
    }

    public String getMethod() {
        return "GET";
    }
}
