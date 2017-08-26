package com.e.a.a.a;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.e.a.a.a.a.b.a;

class k extends WebViewClient {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.a.i) {
            try {
                this.a.i = true;
                this.a.c = new ag((View) this.a.g.get(), this.a.h, true, this.a.a, this.a.b);
                this.a.c.a();
                this.a.a();
            } catch (Exception e) {
                a.a(e);
            }
        }
    }
}
