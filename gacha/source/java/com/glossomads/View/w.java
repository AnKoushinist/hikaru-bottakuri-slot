package com.glossomads.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.d.a.a.c;
import com.glossomads.SugarUtil;
import com.glossomads.d.d;
import com.glossomads.s;
import java.io.File;

public class w extends WebView {
    private a a;
    private t b;

    public interface a {
        void a();

        void a(com.glossomads.b.b bVar);
    }

    public final class b extends WebViewClient {
        final /* synthetic */ w a;

        public b(w wVar) {
            this.a = wVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            com.glossomads.b.b a;
            String a2 = this.a.a(str);
            com.glossomads.Logger.a.a aVar = com.glossomads.Logger.a.a.endcarodClick;
            String[] strArr = new String[1];
            strArr[0] = SugarUtil.isNotEmptyValue(a2) ? a2 : str;
            com.glossomads.Logger.a.g(aVar, strArr);
            if (!SugarUtil.isNotEmptyValue(a2)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            try {
                webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a2)));
                webView.stopLoading();
            } catch (Exception e) {
                if ("market://".equals(a2)) {
                    a = this.a.c(str);
                } else {
                    a = this.a.b(str);
                }
                if (this.a.a != null) {
                    this.a.a.a(a);
                }
            }
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            com.glossomads.b.b b = this.a.b(str2);
            if (this.a.a != null) {
                this.a.a.a(b);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (this.a.a != null) {
                this.a.a.a();
            }
        }
    }

    public w(t tVar) {
        super(s.e());
        this.b = tVar;
        c();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void c() {
        setWebViewClient(new b(this));
        getSettings().setJavaScriptEnabled(true);
        setVerticalScrollbarOverlay(true);
        setInitialScale((int) (s.e().getResources().getDisplayMetrics().density * 100.0f));
    }

    public void a() {
        if (d.m()) {
            String c = v.c(this.b.b().l());
            File file = new File(c);
            if (SugarUtil.isNotEmptyValue(c)) {
                if (file.exists()) {
                    loadUrl(v.b(this.b.b().l()));
                } else {
                    loadDataWithBaseURL(null, this.b.b().k(), "text/html", c.DEFAULT_CHARSET, null);
                }
            } else if (this.a != null) {
                this.a.a(getEndCardLoadError());
            }
        } else if (this.a != null) {
            this.a.a(getEndCardLoadError());
        }
    }

    private com.glossomads.b.b b(String str) {
        return new com.glossomads.b.b(com.glossomads.b.b.b, str);
    }

    private com.glossomads.b.b c(String str) {
        return new com.glossomads.b.b(com.glossomads.b.b.f, str);
    }

    private com.glossomads.b.b getEndCardLoadError() {
        return new com.glossomads.b.b(com.glossomads.b.b.e);
    }

    public String a(String str) {
        if (str.startsWith("sugar://")) {
            return str.replace("sugar://", "http://");
        }
        if (str.startsWith("sugars://")) {
            return str.replace("sugars://", "https://");
        }
        if (str.startsWith("sugarstore://")) {
            return str.replace("sugarstore://", "market://");
        }
        if (str.startsWith("market://")) {
            return str;
        }
        return null;
    }

    public void b() {
        this.b = null;
        this.a = null;
        stopLoading();
        setWebChromeClient(null);
        setWebViewClient(null);
        removeAllViews();
        destroyDrawingCache();
        destroy();
    }

    public void setSugarWebViewListener(a aVar) {
        this.a = aVar;
    }
}
