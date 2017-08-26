package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import org.cocos2dx.lib.BuildConfig;

class bj implements WebAdTracker {
    private final a a;
    private final ap b;

    bj(WebView webView, a aVar, ap apVar) {
        this.b = apVar;
        if (apVar.b()) {
            Log.d("MoatWebAdTracker", "In initialization method.");
        }
        if (webView == null) {
            if (apVar.b()) {
                Log.e("MoatWebAdTracker", "WebView is null. Will not track.");
            }
            this.a = a.a();
            return;
        }
        this.a = a.a(new bi(webView, webView, false, aVar, apVar));
    }

    public boolean track() {
        boolean c;
        boolean b = this.b.b();
        boolean z = false;
        if (b) {
            try {
                Log.d("MoatWebAdTracker", "In track method.");
            } catch (Exception e) {
                com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            }
        }
        if (this.a.c()) {
            c = ((bh) this.a.b()).c();
        } else if (b) {
            Log.e("MoatWebAdTracker", "Internal tracker not available. Not tracking.");
            if (b) {
                Log.d("MoatWebAdTracker", "Attempt to start tracking ad was " + (z ? BuildConfig.FLAVOR : "un") + "successful.");
            }
            return z;
        } else {
            c = false;
        }
        z = c;
        if (b) {
            if (z) {
            }
            Log.d("MoatWebAdTracker", "Attempt to start tracking ad was " + (z ? BuildConfig.FLAVOR : "un") + "successful.");
        }
        return z;
    }
}
