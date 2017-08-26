package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.BuildConfig;

class x implements ba {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ v c;

    x(v vVar, WeakReference weakReference, ap apVar) {
        this.c = vVar;
        this.a = weakReference;
        this.b = apVar;
    }

    public a a() {
        ViewGroup viewGroup = (ViewGroup) this.a.get();
        boolean b = this.b.b();
        if (viewGroup == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return a.a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
        }
        a a = this.c.a.a(viewGroup);
        boolean c = a.c();
        if (b) {
            Log.e("MoatFactory", "WebView " + (c ? BuildConfig.FLAVOR : "not ") + "found inside of ad container.");
        }
        return a.a(new bj((WebView) a.c(null), this.c.b, this.b));
    }
}
