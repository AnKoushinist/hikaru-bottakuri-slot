package jp.co.mediasdk.mscore.ui.adformat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MSAdFormatWebClient extends WebViewClient {
    private Activity a;

    public MSAdFormatWebClient(Activity activity) {
        this.a = activity;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ((MSAdFormatActivity) this.a).b();
    }

    public void onPageFinished(WebView webView, String str) {
        ((MSAdFormatActivity) this.a).c();
        webView.requestFocus(130);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.loadData("<html>\n    <head>\n        <meta charset=\"UTF-8\">\n        <title></title>\n        <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\">\n        <style>\n            * {\n                margin: 0;\n                padding: 0;\n            }\n            html,\n            body {\n                width: 100%;\n                height: 100%;\n            }\n            body {\n                display: table;\n                background: #fefefe;\n            }\n            .wrap {\n                position: relative;\n                display: table-cell;\n                vertical-align: middle;\n                text-align: center;\n            }\n            .icon {\n                position: relative;\n                width: 100px;\n                height: 100px;\n                line-height: 110px;\n                margin: 0 auto 30px;\n                font-size: 75px;\n                font-weight: bold;\n                color: #fefefe;\n            }\n            .icon::after {\n                content: \"\";\n                z-index: -1;\n                display: block;\n                position: absolute;\n                left: -24px;\n                bottom: 0;\n                width: 1px;\n                height: 1px;\n                border: 74px solid transparent;\n                border-bottom: 115px solid #999;\n            }\n            .errorMessage {\n                font-size: 12px;\n                line-height: 1.6;\n                color: #666;\n            }\n        </style>\n    </head>\n    <body>\n        <div class=\"wrap\">\n            <p class=\"icon\">\uff01</p>\n            <p class=\"errorMessage\">\u30da\u30fc\u30b8\u3092\u8aad\u307f\u8fbc\u3081\u307e\u305b\u3093\u3067\u3057\u305f</p><br>\n\t     <a href=\"media-native://close/\">\u9589\u3058\u308b</a>\n        </div>\n\t\n    </body>\n</html>", "text/html; charset=utf-8", "utf-8");
        MSAdListenerManager.a("type=load&status=ng");
    }

    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(str);
    }

    private boolean a(String str) {
        if (!str.startsWith("media-native://close/")) {
            return (str.startsWith("http://") || str.startsWith("https://")) ? false : true;
        } else {
            ((MSAdFormatActivity) this.a).a();
            this.a.finish();
            return true;
        }
    }
}
