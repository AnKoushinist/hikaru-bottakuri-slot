package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;

public final class gz {
    public static final bn n = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            return new gz(bsVar);
        }
    };
    public hb a;
    public hb b;
    public hb c;
    public hb d;
    public int e = 9;
    public int f = 10;
    public String g;
    public String h;
    public String i;
    public boolean j = false;
    public String k;
    public gx l;
    public gx m;

    public gz(bs bsVar) {
        bsVar.h();
        while (bsVar.j()) {
            String l = bsVar.l();
            if ("x".equals(l)) {
                this.a = hb.a(bsVar.m());
            } else if ("y".equals(l)) {
                this.b = hb.a(bsVar.m());
            } else if ("width".equals(l)) {
                this.c = hb.a(bsVar.m());
            } else if ("height".equals(l)) {
                this.d = hb.a(bsVar.m());
            } else if (String.URL.equals(l)) {
                this.g = bsVar.m();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                this.h = bsVar.m();
            } else if ("ad_content".equals(l)) {
                this.i = bsVar.m();
            } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                this.j = bsVar.n();
            } else if ("value".equals(l)) {
                this.k = bsVar.m();
            } else if ("image".equals(l)) {
                this.l = (gx) gx.e.a(bsVar);
            } else if ("image_clicked".equals(l)) {
                this.m = (gx) gx.e.a(bsVar);
            } else if ("align".equals(l)) {
                l = bsVar.m();
                if ("left".equals(l)) {
                    this.e = 9;
                } else if ("right".equals(l)) {
                    this.e = 11;
                } else if ("center".equals(l)) {
                    this.e = 14;
                } else {
                    bsVar.s();
                }
            } else if ("valign".equals(l)) {
                l = bsVar.m();
                if ("top".equals(l)) {
                    this.f = 10;
                } else if ("middle".equals(l)) {
                    this.f = 15;
                } else if ("bottom".equals(l)) {
                    this.f = 12;
                } else {
                    bsVar.s();
                }
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
    }
}
