package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class aq extends WebViewClient {
    private final AppLovinSdk a;
    private final AppLovinLogger b;
    private WeakReference c;

    public aq(AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk;
        this.b = appLovinSdk.getLogger();
    }

    void a(WebView webView, String str) {
        this.b.i("WebViewButtonClient", "Processing click on ad URL \"" + str + "\"");
        if (str != null && (webView instanceof ap)) {
            ap apVar = (ap) webView;
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            ar arVar = (ar) this.c.get();
            if (!AppLovinSdk.URI_SCHEME.equalsIgnoreCase(scheme) || !AppLovinSdk.URI_HOST.equalsIgnoreCase(host) || arVar == null) {
                return;
            }
            if ("/track_click".equals(path)) {
                arVar.a(apVar);
            } else if ("/close_ad".equals(path)) {
                arVar.b(apVar);
            } else if ("/skip_ad".equals(path)) {
                arVar.c(apVar);
            } else {
                this.b.w("WebViewButtonClient", "Unknown URL: " + str);
                this.b.w("WebViewButtonClient", "Path: " + path);
            }
        }
    }

    public void a(WeakReference weakReference) {
        this.c = weakReference;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }
}
