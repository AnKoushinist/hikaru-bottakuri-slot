package jp.co.mediasdk.android;

import com.tapjoy.TapjoyConstants;
import java.io.OutputStream;

public class HTTPUtilRequestSupport extends HTTPUtilPropertySupport {
    protected String d = "GET";
    protected int e = -1;
    protected int f = -1;
    protected byte[] g = null;
    protected HashMapEX h = new HashMapEX();

    protected boolean c() {
        for (String str : this.h.a()) {
            LoggerBase.d(this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "header '%s: %s' is set.", str, this.h.get(str));
            this.a.setRequestProperty(str, (String) this.h.get(str));
        }
        return true;
    }

    public boolean b(String str) {
        if (!StringUtilEqualsSupport.a(str, "GET") && !StringUtilEqualsSupport.a(str, "POST")) {
            return false;
        }
        this.d = str;
        return true;
    }

    public boolean c(String str) {
        this.h.c("User-Agent", str);
        return true;
    }

    public boolean a(String str, String str2) {
        this.h.c(str, str2);
        LoggerBase.d(this, "setRequestHeader", "key '%s' val '%s' is set.", str, str2);
        if (StringUtilEmptySupport.c(str2)) {
            LoggerBase.b((Object) this, "setRequestHeader", "val is empty.", new Object[0]);
        }
        return true;
    }

    protected boolean d() {
        if (!StringUtilEqualsSupport.a("POST", this.d) || this.g == null) {
            return true;
        }
        this.a.setDoOutput(true);
        this.a.setDoInput(true);
        OutputStream j = HttpURLConnectionUtil.j(this.a);
        if (j == null) {
            LoggerBase.a((Object) this, "setPost", "failed to get output stream.", new Object[0]);
            return false;
        }
        LoggerBase.d(this, "setPost", "post data length is '%d'.", Integer.valueOf(this.g.length));
        if (!OutputStreamUtil.a(j, this.g)) {
            LoggerBase.a((Object) this, "setPost", "failed to write post data.", new Object[0]);
            return false;
        } else if (!StreamBase.a(j)) {
            LoggerBase.a((Object) this, "setPost", "failed to flush.", new Object[0]);
            return false;
        } else if (OutputStreamUtil.a(j)) {
            return true;
        } else {
            LoggerBase.a((Object) this, "setPost", "failed to close.", new Object[0]);
            return false;
        }
    }

    public boolean a(byte[] bArr) {
        this.g = bArr;
        return true;
    }
}
