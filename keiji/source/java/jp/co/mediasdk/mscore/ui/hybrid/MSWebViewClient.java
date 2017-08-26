package jp.co.mediasdk.mscore.ui.hybrid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import jp.co.mediasdk.android.StringUtil;
import jp.co.mediasdk.mscore.ui.MSWebViewActivity;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import org.cocos2dx.lib.BuildConfig;

public class MSWebViewClient extends WebViewClient {
    static final String[] a = new String[]{"spr-adv.jp", "car.mobadme.jp", "upm.mobadme.jp", "sr.ca-mpr.jp", "growth.mobadme.jp", "crowd.mobadme.jp", "stg.car.mobadme.jp", "stg.upm.mobadme.jp", "dev.car.mobadme.jp", "dev.upm.mobadme.jp", "spr-adv.jp", "stg.spr-adv.jp", "dev.spr-adv.jp"};
    private Activity b;
    private ProgressDialog c;

    public MSWebViewClient(Activity activity) {
        this.b = activity;
    }

    public void a() {
        if (this.c != null) {
            try {
                this.c.dismiss();
            } catch (Exception e) {
            }
            this.c = null;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        a();
        this.c = new ProgressDialog(this.b);
        this.c.setProgressStyle(0);
        CharSequence charSequence = "\u8aad\u307f\u8fbc\u307f\u4e2d";
        if (MSParameterSupport.b(MSParameterSupport.n)) {
            charSequence = MSParameterSupport.a(MSParameterSupport.n);
        }
        this.c.setMessage(charSequence);
        this.c.show();
    }

    public void onPageFinished(WebView webView, String str) {
        a();
        webView.requestFocus(130);
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("media-native://playVideo/")) {
            try {
                String replace = str.replace("media-native://playVideo/", BuildConfig.FLAVOR);
                if (replace.endsWith("/")) {
                    replace = replace.substring(0, replace.lastIndexOf("/"));
                }
                ((MSWebViewActivity) this.b).a(StringUtil.a(replace));
            } catch (Exception e) {
            }
            return true;
        }
        for (String equals : a) {
            if (equals.equals(Uri.parse(str).getHost())) {
                return false;
            }
        }
        a();
        try {
            this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e2) {
        }
        return true;
    }
}
