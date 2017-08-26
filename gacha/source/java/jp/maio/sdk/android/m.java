package jp.maio.sdk.android;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import org.cocos2dx.lib.BuildConfig;

class m extends WebChromeClient {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public void onCloseWindow(WebView webView) {
        this.a.f.d();
        ax.a("Ad View closed.", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        ax.a("WebChromeClient#onConsoleMessage", consoleMessage.message(), BuildConfig.FLAVOR, null);
        if (consoleMessage.message().contains("Cannot read property 'logging_view_through_conversion'")) {
            onCloseWindow(null);
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
