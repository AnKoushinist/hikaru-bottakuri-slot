package jp.co.mediasdk.android;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class NetUtilResponseSupport extends NetUtilListenerSupport {
    protected ByteArrayOutputStream q = null;
    protected boolean r = false;
    protected boolean s = false;

    protected boolean s() {
        this.q = new ByteArrayOutputStream();
        return a(this.q);
    }

    protected boolean a(OutputStream outputStream) {
        if (outputStream == null) {
            LoggerBase.a((Object) this, "readAll", "os is null.", new Object[0]);
            this.r = true;
            return false;
        }
        l();
        do {
            int g = (int) g();
            if (g <= -1) {
                break;
            } else if (!OutputStreamUtil.a(outputStream, this.c, 0, g)) {
                this.r = true;
                break;
            } else {
                n();
            }
        } while (!this.s);
        LoggerBase.d(this, "readAll", "read is cancelled.", new Object[0]);
        m();
        return true;
    }

    public byte[] t() {
        if (this.q != null) {
            return this.q.toByteArray();
        }
        LoggerBase.a((Object) this, "getResult", "result is null.", new Object[0]);
        return null;
    }

    public boolean e() {
        if (!this.r) {
            return super.e();
        }
        LoggerBase.a((Object) this, "isSuccess", "stream is error.", new Object[0]);
        return false;
    }
}
