package jp.co.mediasdk.android;

import jp.co.mediasdk.mscore.util.UserAgentUtil;

public class NetUtilGetSupport extends NetUtilPostCallbackSupport {
    protected boolean f(String str) {
        b("GET");
        c(UserAgentUtil.a());
        if (!StringUtilEmptySupport.c(k())) {
            str = String.format("%s?%s", new Object[]{str, k()});
        }
        return a(str);
    }

    public byte[] g(String str) {
        if (!f(str)) {
            LoggerBase.a((Object) this, "get", "failed to connect.", new Object[0]);
            return null;
        } else if (s()) {
            a();
            return t();
        } else {
            LoggerBase.a((Object) this, "get", "failed to read.", new Object[0]);
            return null;
        }
    }

    public String h(String str) {
        byte[] g = g(str);
        if (g == null) {
            return null;
        }
        return StringUtil.a(g);
    }

    public HashMapEX i(String str) {
        byte[] g = g(str);
        if (g == null) {
            return null;
        }
        return new HashMapEX(g);
    }

    protected String k() {
        HashMapEX hashMapEX = new HashMapEX();
        for (String str : this.p.a()) {
            if (!(this.o.a(str) || this.p.f(str))) {
                hashMapEX.c(str, this.p.j(str));
            }
        }
        return hashMapEX.a(true);
    }
}
