package jp.co.mediasdk.android;

import com.tapjoy.TapjoyConstants;

public class NetUtilCookieSupport extends NetUtilGetCallbackSupport {
    public boolean a(String str) {
        String a = CookieManagerUtil.a(str);
        if (!StringUtilEmptySupport.c(a)) {
            a("Cookie", a);
            LoggerBase.d(this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "cookie '%s' is loaded.", a);
        }
        return super.a(str);
    }

    public boolean a() {
        if (e("Set-Cookie")) {
            CookieManagerUtil.a(this.b, d("Set-Cookie"));
            LoggerBase.d(this, "disconnect", "cookie '%s' is saved.", r0);
        }
        return super.a();
    }
}
