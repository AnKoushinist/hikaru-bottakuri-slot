package jp.gmotech.smaad.video.ad;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.gmotech.smaad.video.ad.b.a;
import org.cocos2dx.lib.BuildConfig;

class g extends WebViewClient {
    final /* synthetic */ SmaAdVideoActivity a;

    g(SmaAdVideoActivity smaAdVideoActivity) {
        this.a = smaAdVideoActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        a.b("SmaAdVideoActivity", "[onPageFinished] url : " + str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        a.b("SmaAdVideoActivity", "[onPageStarted] url : " + str);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        a.b("SmaAdVideoActivity", "[onReceivedError] deprecated");
        super.onReceivedError(webView, i, str, str2);
        new Builder(this.a).setMessage("\u4e88\u671f\u305b\u306c\u30a8\u30e9\u30fc\u304c\u767a\u751f\u3057\u307e\u3057\u305f\u3002\u30a2\u30d7\u30ea\u306b\u623b\u308a\u307e\u3059\u3002").setPositiveButton(17039370, new h(this)).create().show();
    }

    @SuppressLint({"NewApi"})
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        a.b("SmaAdVideoActivity", "[onReceivedError] new");
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        new Builder(this.a).setMessage("\u4e88\u671f\u305b\u306c\u30a8\u30e9\u30fc\u304c\u767a\u751f\u3057\u307e\u3057\u305f\u3002\u30a2\u30d7\u30ea\u306b\u623b\u308a\u307e\u3059\u3002").setPositiveButton(17039370, new i(this)).create().show();
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a.b("SmaAdVideoActivity", "[shouldOverrideUrlLoading] url : " + str);
        String k = this.a.b();
        if (str.contains("redirect")) {
            a.b("SmaAdVideoActivity", "[shouldOverrideUrlLoading] no_redirect");
            this.a.a(k);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.a.g.e()));
            this.a.startActivity(intent);
            return true;
        } else if (!str.contains("siteopen")) {
            return false;
        } else {
            a.b("SmaAdVideoActivity", "[shouldOverrideUrlLoading] REDIRECT");
            k = k.replaceFirst("&?no_redirect=1", BuildConfig.FLAVOR);
            a.b("SmaAdVideoActivity", "[shouldOverrideUrlLoading] normalClickUrl: " + k);
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(k));
            this.a.startActivity(intent2);
            return true;
        }
    }
}
