package jp.co.mediasdk.mscore.ui.pva;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MSPVAWebViewClient extends WebViewClient {
    private MSPVAActivityWeb a;

    public MSPVAWebViewClient(MSPVAActivityWeb mSPVAActivityWeb) {
        this.a = mSPVAActivityWeb;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
    }

    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.a.g();
    }

    public void onPageFinished(WebView webView, String str) {
        this.a.h();
        webView.requestFocus(130);
        String j = MSPVAVast.a().j("ClickTracking");
        if (!(j == null || j.isEmpty())) {
            webView.loadUrl("javascript:setClickTracking('" + j + "')");
        }
        j = MSPVAVast.a().j("ClickThrough");
        if (j != null && !j.isEmpty()) {
            webView.loadUrl("javascript:setClickThrough('" + j + "')");
        }
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(str);
    }

    private boolean a(String str) {
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            return true;
        }
        if (str.indexOf(MSPVAVast.a().j("ClickThrough")) != -1) {
            try {
                this.a.a().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public void a() {
        this.a = null;
    }
}
