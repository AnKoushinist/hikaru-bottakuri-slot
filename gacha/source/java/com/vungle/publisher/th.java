package com.vungle.publisher;

import android.webkit.WebView;
import com.vungle.log.Logger;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;
import org.json.JSONObject;

/* compiled from: vungle */
public final class th {
    @Inject
    nb a;
    @Inject
    public a b;
    @Inject
    public ss c;
    @Inject
    public tp.a d;

    @Inject
    th() {
    }

    public final void a(boolean z, WebView webView) {
        tp a = this.d.a();
        a.a(z);
        try {
            a(webView, a.a(), false);
        } catch (Throwable e) {
            this.b.b(Logger.AD_TAG, "could not update viewable properties", e);
        }
    }

    public static void a(WebView webView, boolean z) {
        ss.a(webView, "incentivizedDialogResponse", String.valueOf(z));
    }

    public static void a(WebView webView, JSONObject jSONObject, boolean z) {
        ss.a(webView, "notifyPropertiesChange", jSONObject.toString(), String.valueOf(z));
    }
}
