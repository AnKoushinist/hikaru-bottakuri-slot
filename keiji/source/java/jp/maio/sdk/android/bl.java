package jp.maio.sdk.android;

import android.net.Uri;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

final class bl implements ResponseHandler {
    final /* synthetic */ Uri a;
    final /* synthetic */ String b;

    bl(Uri uri, String str) {
        this.a = uri;
        this.b = str;
    }

    public bm a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        bc.a("WebApiManager#downloadFile ResponseHandler#handleResponse", "status=" + statusLine.getStatusCode() + ", Request uri:" + this.a, null);
        if (httpResponse.getStatusLine().getStatusCode() >= HttpResponseCode.BAD_REQUEST) {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        httpResponse.getAllHeaders();
        String str = BuildConfig.FLAVOR;
        if (this.a.toString().contains("/templates/")) {
            str = ".html";
        }
        File file = new File(new File(bk.a, this.b), bk.b(this.a) + str);
        file.getParentFile().mkdirs();
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            httpResponse.getEntity().writeTo(fileOutputStream);
            return new bm(httpResponse, file);
        } finally {
            bf.a(fileOutputStream);
        }
    }

    public /* synthetic */ Object handleResponse(HttpResponse httpResponse) {
        return a(httpResponse);
    }
}
