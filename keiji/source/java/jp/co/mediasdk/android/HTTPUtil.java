package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import com.tapjoy.TapjoyConstants;

public class HTTPUtil extends HTTPUtilResponseSupport {
    @SuppressLint({"NewApi"})
    public boolean a(String str) {
        if (VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new Builder().permitAll().build());
        }
        LoggerBase.d(this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "connecting '%s'.", str);
        this.a = URLUtil.b(str);
        if (this.a == null) {
            LoggerBase.a((Object) this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "'openConnection' failed.", new Object[0]);
            return false;
        }
        this.a.setInstanceFollowRedirects(true);
        if (this.e > -1) {
            this.a.setConnectTimeout(this.e);
        }
        if (this.f > -1) {
            this.a.setReadTimeout(this.f);
        }
        if (!HttpURLConnectionUtil.a(this.a, this.d)) {
            LoggerBase.a((Object) this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "failed to set method '%s'.", this.d);
            return false;
        } else if (!c()) {
            LoggerBase.a((Object) this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "failed to set header.", new Object[0]);
            return false;
        } else if (!d()) {
            LoggerBase.a((Object) this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "failed to set post.", new Object[0]);
            return false;
        } else if (HttpURLConnectionUtil.b(this.a)) {
            LoggerBase.d(this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "response code is '%d'.", Integer.valueOf(f()));
            this.b = str;
            this.i = 0;
            return true;
        } else {
            LoggerBase.a((Object) this, TapjoyConstants.TJC_SDK_TYPE_CONNECT, "failed to connect.", new Object[0]);
            return false;
        }
    }

    public boolean a() {
        if (b()) {
            HttpURLConnectionUtil.a(this.a);
        }
        return true;
    }
}
