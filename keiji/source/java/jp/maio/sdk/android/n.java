package jp.maio.sdk.android;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import org.cocos2dx.lib.BuildConfig;

class n extends WebChromeClient {
    final /* synthetic */ l a;

    n(l lVar) {
        this.a = lVar;
    }

    public void onCloseWindow(WebView webView) {
        this.a.g.d();
        bc.a("Ad View closed.", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.message().contains("Cannot read property 'logging_view_through_conversion'") || consoleMessage.message().contains("Uncaught ReferenceError: native_onPreparedVideo is not defined")) {
            try {
                Log.v("Webview Error", "Creative ID: " + this.a.c.b + "  Template Path " + this.a.c.a(this.a.c.e).getPath() + " Template File Size raw " + this.a.c.a(this.a.c.e).length() + " Template File Size ");
                bp.b(FailNotificationReason.VIDEO, String.valueOf(this.a.c.b));
            } catch (Exception e) {
            }
            onCloseWindow(null);
        }
        return true;
    }
}
