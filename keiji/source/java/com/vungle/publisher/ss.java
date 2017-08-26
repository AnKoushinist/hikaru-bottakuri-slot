package com.vungle.publisher;

import android.webkit.WebView;
import com.vungle.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class ss {
    static void a(WebView webView, String str, String... strArr) {
        a("window.vungle.mraidBridge", webView, str, strArr);
    }

    public static void a(String str, WebView webView, String str2, String... strArr) {
        a(webView, str + "." + str2 + "(" + ags.a(",", (Object[]) strArr) + ")");
    }

    static void a(WebView webView, String str) {
        Logger.v(Logger.AD_TAG, "load javascript: " + str);
        webView.loadUrl("javascript:" + str);
    }
}
