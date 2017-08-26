package jp.co.mediasdk.mscore.ui.adformat;

import android.annotation.TargetApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MSAdClickWebClient extends WebViewClient {
    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        return true;
    }
}
