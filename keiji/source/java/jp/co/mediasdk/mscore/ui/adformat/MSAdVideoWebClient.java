package jp.co.mediasdk.mscore.ui.adformat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapjoy.TapjoyConstants;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.MathUtilParseSupport;
import jp.co.mediasdk.android.RunnableEX;
import jp.co.mediasdk.android.StringUtil;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.StringUtilRegexpSupport;
import org.cocos2dx.lib.BuildConfig;

public class MSAdVideoWebClient extends WebViewClient {
    private static int a = 3;
    private static String b = BuildConfig.FLAVOR;
    private static String c = BuildConfig.FLAVOR;
    private Activity d;
    private int e = 0;
    private WebView f;

    public class AdFormatVideoJavascriptInterface {
        final /* synthetic */ MSAdVideoWebClient a;

        public AdFormatVideoJavascriptInterface(MSAdVideoWebClient mSAdVideoWebClient) {
            this.a = mSAdVideoWebClient;
        }

        @JavascriptInterface
        public void getsdkVideoOnLoadReturn(String str) {
            this.a.e = MathUtilParseSupport.c(str);
            if (this.a.e == 1) {
                HandlerManager.b(MyRunnable.class);
            }
        }

        @JavascriptInterface
        public void getsdkVideoGetStatusReturn(String str) {
            this.a.e = MathUtilParseSupport.c(str);
            if (this.a.e == 1) {
                HandlerManager.b(MyRunnable.class);
            }
        }
    }

    protected class MyRunnable extends RunnableEX {
        final /* synthetic */ MSAdVideoWebClient a;
        private int b = 0;

        protected MyRunnable(MSAdVideoWebClient mSAdVideoWebClient) {
            this.a = mSAdVideoWebClient;
        }

        public void a() {
            this.a.d();
            if (this.b > MSAdVideoWebClient.a) {
                HandlerManager.b((RunnableEX) this);
            }
            this.b++;
        }
    }

    public MSAdVideoWebClient(Activity activity, WebView webView) {
        this.d = activity;
        HandlerManager.a();
        this.f = webView;
        webView.addJavascriptInterface(new AdFormatVideoJavascriptInterface(this), TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ((MSAdFormatActivity) this.d).b();
    }

    public void onPageFinished(WebView webView, String str) {
        ((MSAdFormatActivity) this.d).c();
        webView.requestFocus(130);
    }

    private synchronized void d() {
        this.f.loadUrl("javascript:android.getsdkVideoGetStatusReturn(sdkVideoGetStatus());");
        if (this.e != 1) {
            this.f.loadUrl("javascript:android.getsdkVideoOnLoadReturn(sdkVideoOnLoad());");
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.loadUrl("javascript:sdkVideoOnUnload();");
        }
    }

    public void b() {
        b = BuildConfig.FLAVOR;
        c = BuildConfig.FLAVOR;
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("media-native://webview-host/")) {
            b = StringUtil.a(str.replace("media-native://webview-host/", BuildConfig.FLAVOR));
            return true;
        } else if (str.startsWith("media-native://scheme/")) {
            c = StringUtil.a(str.replace("media-native://scheme/", BuildConfig.FLAVOR));
            return true;
        } else if (str.startsWith("media-native://webview/")) {
            ((MSAdFormatActivity) this.d).b(StringUtil.a(str.replace("media-native://webview/", BuildConfig.FLAVOR)));
            return true;
        } else if (str.startsWith("media-native://height/")) {
            ((MSAdFormatActivity) this.d).a(str.replace("media-native://height/", BuildConfig.FLAVOR));
            return true;
        } else if (str.startsWith("media-native://message/")) {
            ((MSAdFormatActivity) this.d).d(StringUtil.a(str.replace("media-native://message/", BuildConfig.FLAVOR)));
            return true;
        } else if (str.startsWith("media-native://close/")) {
            ((MSAdFormatActivity) this.d).a();
            this.d.finish();
            return true;
        } else if (str.startsWith("media-native://store/")) {
            return true;
        } else {
            if (str.startsWith("media-native://tracking/")) {
                ((MSAdFormatActivity) this.d).c(StringUtil.a(str.replace("media-native://tracking/", BuildConfig.FLAVOR)));
                return true;
            } else if (str.startsWith("media-native://play/")) {
                RunnableEX myRunnable = new MyRunnable(this);
                myRunnable.a(3000);
                HandlerManager.a(myRunnable, 3000);
                return true;
            } else if (!StringUtilEmptySupport.c(c) && StringUtilRegexpSupport.b(c, str)) {
                b(str);
                return true;
            } else if (!str.startsWith("http://") && !str.startsWith("https://")) {
                return true;
            } else {
                if (StringUtilEmptySupport.c(b)) {
                    b(str);
                    return true;
                } else if (StringUtilRegexpSupport.b(b, str)) {
                    return false;
                } else {
                    b(str);
                    return true;
                }
            }
        }
    }

    private void b(String str) {
        try {
            this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.loadData("<html>\n    <head>\n        <meta charset=\"UTF-8\">\n        <title></title>\n        <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\">\n        <style>\n            * {\n                margin: 0;\n                padding: 0;\n            }\n            html,\n            body {\n                width: 100%;\n                height: 100%;\n            }\n            body {\n                display: table;\n                background: #fefefe;\n            }\n            .wrap {\n                position: relative;\n                display: table-cell;\n                vertical-align: middle;\n                text-align: center;\n            }\n            .icon {\n                position: relative;\n                width: 100px;\n                height: 100px;\n                line-height: 110px;\n                margin: 0 auto 30px;\n                font-size: 75px;\n                font-weight: bold;\n                color: #fefefe;\n            }\n            .icon::after {\n                content: \"\";\n                z-index: -1;\n                display: block;\n                position: absolute;\n                left: -24px;\n                bottom: 0;\n                width: 1px;\n                height: 1px;\n                border: 74px solid transparent;\n                border-bottom: 115px solid #999;\n            }\n            .errorMessage {\n                font-size: 12px;\n                line-height: 1.6;\n                color: #666;\n            }\n        </style>\n    </head>\n    <body>\n        <div class=\"wrap\">\n            <p class=\"icon\">\uff01</p>\n            <p class=\"errorMessage\">\u30da\u30fc\u30b8\u3092\u8aad\u307f\u8fbc\u3081\u307e\u305b\u3093\u3067\u3057\u305f</p><br>\n\t     <a href=\"media-native://close/\">\u9589\u3058\u308b</a>\n        </div>\n\t\n    </body>\n</html>", "text/html; charset=utf-8", "utf-8");
        MSAdListenerManager.a("type=load&status=ng");
    }

    @TargetApi(23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
    }
}
