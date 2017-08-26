package jp.maio.sdk.android;

import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

class bm {
    public final int a;
    public final HashMap b = new HashMap();
    public final Object c;

    public bm(HttpResponse httpResponse, Object obj) {
        this.a = httpResponse.getStatusLine().getStatusCode();
        for (Header header : httpResponse.getAllHeaders()) {
            this.b.put(header.getName(), header.getValue());
        }
        this.c = obj;
    }

    public String a() {
        return "utf-8";
    }
}
