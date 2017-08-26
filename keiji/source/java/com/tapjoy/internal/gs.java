package com.tapjoy.internal;

import android.graphics.Rect;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import org.cocos2dx.lib.BuildConfig;

public final class gs {
    public static final bn h = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            fl flVar = null;
            boolean z = false;
            String str = BuildConfig.FLAVOR;
            bsVar.h();
            String str2 = null;
            String str3 = null;
            String str4 = null;
            Rect rect = null;
            while (bsVar.j()) {
                String l = bsVar.l();
                if ("region".equals(l)) {
                    rect = (Rect) bo.b.a(bsVar);
                } else if ("value".equals(l)) {
                    str4 = bsVar.m();
                } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                    z = bsVar.n();
                } else if (String.URL.equals(l)) {
                    str = bsVar.m();
                } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                    str3 = bsVar.b();
                } else if ("ad_content".equals(l)) {
                    str2 = bsVar.b();
                } else if (go.a(l)) {
                    flVar = go.a(l, bsVar);
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return new gs(rect, str4, z, str, str3, str2, flVar);
        }
    };
    public final Rect a;
    public final String b;
    public final boolean c;
    public final String d;
    public String e;
    public String f;
    public final fl g;

    gs(Rect rect, String str, boolean z, String str2, String str3, String str4, fl flVar) {
        this.a = rect;
        this.b = str;
        this.c = z;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = flVar;
    }
}
