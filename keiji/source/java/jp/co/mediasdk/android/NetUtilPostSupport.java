package jp.co.mediasdk.android;

import java.io.OutputStream;
import jp.co.mediasdk.mscore.util.UserAgentUtil;

public class NetUtilPostSupport extends NetUtilRequestSupport {
    protected boolean k = false;
    protected boolean l = false;
    protected String m = null;

    public boolean a(boolean z) {
        this.k = z;
        return true;
    }

    public boolean b(boolean z) {
        this.l = z;
        return true;
    }

    protected boolean j(String str) {
        b("POST");
        c(UserAgentUtil.a());
        if (this.k) {
            a("Content-Type", String.format("multipart/form-data; boundary=%s", new Object[]{r()}));
            a(p());
        } else if (this.l) {
            a("Content-Type", "application/json");
            a(q());
            LoggerBase.d(this, "connectPost", "paramater = " + StringUtil.a(q()), new Object[0]);
        } else {
            a(o());
        }
        return a(str);
    }

    public byte[] k(String str) {
        if (!j(str)) {
            LoggerBase.a((Object) this, "post", "failed to connect.", new Object[0]);
            return null;
        } else if (s()) {
            a();
            this.g = null;
            return t();
        } else {
            LoggerBase.a((Object) this, "get", "failed to read.", new Object[0]);
            return null;
        }
    }

    protected byte[] o() {
        if (this.g != null) {
            return this.g;
        }
        return this.p.a(true).getBytes();
    }

    protected byte[] p() {
        if (this.g != null) {
            return this.g;
        }
        if (this.p.size() == 0) {
            return new byte[0];
        }
        OutputStream byteArrayOutputStreamEX = new ByteArrayOutputStreamEX();
        for (String str : this.p.a()) {
            LoggerBase.d(this, "getPostMultipart", "key '%s', val '%s'.", str, this.p.j(str));
            byteArrayOutputStreamEX.a(String.format("--%s\r\n", new Object[]{r()}));
            if (this.o.a(str)) {
                LoggerBase.d(this, "getPostData", "key '%s' is byte[].", str);
                byteArrayOutputStreamEX.a(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n", new Object[]{str, this.n.b(str, str)}));
                byteArrayOutputStreamEX.a(String.format("Content-Type: %s\r\n", new Object[]{this.o.b(str, MimeUtil.a)}));
                byteArrayOutputStreamEX.a("\r\n");
                byteArrayOutputStreamEX.write(this.p.c(str));
            } else {
                byteArrayOutputStreamEX.a(String.format("Content-Disposition: form-data; name=\"%s\"\r\n", new Object[]{str}));
                byteArrayOutputStreamEX.a("\r\n");
                byteArrayOutputStreamEX.a(this.p.j(str));
            }
            byteArrayOutputStreamEX.a("\r\n");
        }
        byteArrayOutputStreamEX.a(String.format("--%s--", new Object[]{r()}));
        byte[] toByteArray = byteArrayOutputStreamEX.toByteArray();
        OutputStreamUtil.a(byteArrayOutputStreamEX);
        return toByteArray;
    }

    protected byte[] q() {
        if (this.g != null) {
            return this.g;
        }
        return this.p.toString().replaceAll("=", ":").getBytes();
    }

    protected String r() {
        if (this.m == null) {
            String a = StringUtil.a("%d", Long.valueOf(DateUtil.a()));
            this.m = String.format("---------------------------%s", new Object[]{Hash.a(Hash.a(a))});
        }
        return this.m;
    }

    public String l(String str) {
        byte[] k = k(str);
        if (k == null) {
            return null;
        }
        return StringUtil.a(k);
    }

    public HashMapEX m(String str) {
        byte[] k = k(str);
        if (k == null) {
            return null;
        }
        if (JSON.a(k)) {
            return new HashMapEX(k);
        }
        return new HashMapEX();
    }
}
