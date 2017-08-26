package com.tapjoy.internal;

import android.graphics.Rect;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import org.cocos2dx.lib.BuildConfig;

public final class gg {
    public static final bm h = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            ey eyVar = null;
            boolean z = false;
            String str = BuildConfig.FLAVOR;
            brVar.h();
            String str2 = null;
            String str3 = null;
            String str4 = null;
            Rect rect = null;
            while (brVar.j()) {
                String l = brVar.l();
                if ("region".equals(l)) {
                    rect = (Rect) bn.b.a(brVar);
                } else if ("value".equals(l)) {
                    str4 = brVar.m();
                } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                    z = brVar.n();
                } else if (String.URL.equals(l)) {
                    str = brVar.m();
                } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                    str3 = brVar.b();
                } else if ("ad_content".equals(l)) {
                    str2 = brVar.b();
                } else if (gb.a(l)) {
                    eyVar = gb.a(l, brVar);
                } else {
                    brVar.s();
                }
            }
            brVar.i();
            return new gg(rect, str4, z, str, str3, str2, eyVar);
        }
    };
    public final Rect a;
    public final String b;
    public final boolean c;
    public final String d;
    public String e;
    public String f;
    public final ey g;

    gg(Rect rect, String str, boolean z, String str2, String str3, String str4, ey eyVar) {
        this.a = rect;
        this.b = str;
        this.c = z;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = eyVar;
    }
}
