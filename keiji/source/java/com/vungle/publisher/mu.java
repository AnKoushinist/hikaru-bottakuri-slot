package com.vungle.publisher;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: vungle */
public class mu extends WebViewClient {
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        webView.setOnTouchListener(null);
    }
}
