package jp.co.mediasdk.android;

import java.io.InputStream;
import java.util.Map;
import twitter4j.HttpResponseCode;

public class HTTPUtilResponseSupport extends HTTPUtilRequestSupport {
    protected long i = 0;

    public boolean e() {
        int f = f();
        return f >= HttpResponseCode.OK && f < HttpResponseCode.MULTIPLE_CHOICES;
    }

    public int f() {
        return HttpURLConnectionUtil.c(this.a);
    }

    public String d(String str) {
        return this.a.getHeaderField(str);
    }

    public long g() {
        if (!b() || !HttpURLConnectionUtil.e(this.a)) {
            return -1;
        }
        InputStream d = HttpURLConnectionUtil.d(this.a);
        if (d == null) {
            LoggerBase.a((Object) this, "read", "failed to get input stream.", new Object[0]);
            return -1;
        }
        long a = (long) InputStreamUtil.a(d, this.c);
        if (a < 0) {
            LoggerBase.d(this, "read", "total '%d' bytes received.", Long.valueOf(this.i));
            return -1;
        }
        this.i += a;
        return a;
    }

    public long h() {
        return this.i;
    }

    public long i() {
        if (b()) {
            long contentLength = (long) this.a.getContentLength();
            if (contentLength >= 0) {
                return contentLength;
            }
            LoggerBase.d(this, "getContentLength", "content length is '%d'.", Long.valueOf(contentLength));
            return -1;
        }
        LoggerBase.c(this, "getContentLength", "http status is not connected.", new Object[0]);
        return -1;
    }

    protected boolean e(String str) {
        Map headerFields = this.a.getHeaderFields();
        if (headerFields == null) {
            return false;
        }
        return headerFields.containsKey(str);
    }
}
