package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;

public final class gm {
    public static final bm n = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            return new gm(brVar);
        }
    };
    public go a;
    public go b;
    public go c;
    public go d;
    public int e = 9;
    public int f = 10;
    public String g;
    public String h;
    public String i;
    public boolean j = false;
    public String k;
    public gk l;
    public gk m;

    public gm(br brVar) {
        brVar.h();
        while (brVar.j()) {
            String l = brVar.l();
            if ("x".equals(l)) {
                this.a = go.a(brVar.m());
            } else if ("y".equals(l)) {
                this.b = go.a(brVar.m());
            } else if ("width".equals(l)) {
                this.c = go.a(brVar.m());
            } else if ("height".equals(l)) {
                this.d = go.a(brVar.m());
            } else if (String.URL.equals(l)) {
                this.g = brVar.m();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                this.h = brVar.m();
            } else if ("ad_content".equals(l)) {
                this.i = brVar.m();
            } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                this.j = brVar.n();
            } else if ("value".equals(l)) {
                this.k = brVar.m();
            } else if ("image".equals(l)) {
                this.l = (gk) gk.c.a(brVar);
            } else if ("image_clicked".equals(l)) {
                this.m = (gk) gk.c.a(brVar);
            } else if ("align".equals(l)) {
                l = brVar.m();
                if ("left".equals(l)) {
                    this.e = 9;
                } else if ("right".equals(l)) {
                    this.e = 11;
                } else if ("center".equals(l)) {
                    this.e = 14;
                } else {
                    brVar.s();
                }
            } else if ("valign".equals(l)) {
                l = brVar.m();
                if ("top".equals(l)) {
                    this.f = 10;
                } else if ("middle".equals(l)) {
                    this.f = 15;
                } else if ("bottom".equals(l)) {
                    this.f = 12;
                } else {
                    brVar.s();
                }
            } else {
                brVar.s();
            }
        }
        brVar.i();
    }
}
