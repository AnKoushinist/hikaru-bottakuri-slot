package jp.co.mediasdk.android;

import jp.co.mediasdk.android.NetUtil.NetUtilListener;

public class NetUtilListenerSupport extends HTTPUtil {
    protected NetUtilListener j = null;

    protected void l() {
        if (this.j != null) {
            this.j.a((NetUtil) this);
        }
    }

    protected void m() {
        if (this.j != null) {
            this.j.b((NetUtil) this);
        }
    }

    protected void n() {
        if (this.j != null) {
            float f;
            long h = h();
            long i = i();
            if (i > 0) {
                f = ((float) h) / ((float) i);
            } else {
                f = -1.0f;
            }
            this.j.a((NetUtil) this, f, h, i);
        }
    }
}
