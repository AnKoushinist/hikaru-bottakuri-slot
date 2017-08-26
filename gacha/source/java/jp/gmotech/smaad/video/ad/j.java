package jp.gmotech.smaad.video.ad;

import android.app.AlertDialog.Builder;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import jp.gmotech.smaad.video.ad.b.a;

class j extends WebChromeClient {
    final /* synthetic */ SmaAdVideoActivity a;

    j(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        a.a("SmaAdVideoActivity", "onJsAlert");
        new Builder(this.a).setMessage(str2).setPositiveButton("OK", new k(this, jsResult)).setCancelable(false).create().show();
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        a.a("SmaAdVideoActivity", "onJsAlert");
        return super.onJsConfirm(webView, str, str2, jsResult);
    }
}
