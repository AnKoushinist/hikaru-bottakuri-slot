package com.applovin.impl.adview;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

class n extends WebChromeClient {
    private final AppLovinLogger a;

    public n(AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk.h();
    }

    public void onConsoleMessage(String str, int i, String str2) {
        this.a.c("AdWebView", "console.log[" + i + "] :" + str);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        this.a.a("AdWebView", consoleMessage.sourceId() + ": " + consoleMessage.lineNumber() + ": " + consoleMessage.message());
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.c("AdWebView", "Alert attempted: " + str2);
        return true;
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.c("AdWebView", "JS onBeforeUnload attempted: " + str2);
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.c("AdWebView", "JS confirm attempted: " + str2);
        return true;
    }
}
