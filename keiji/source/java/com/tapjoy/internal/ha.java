package com.tapjoy.internal;

import android.graphics.PointF;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.adunit.AdUnitActivity;
import java.util.ArrayList;

public final class ha {
    public static final bn d = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            return new ha(bsVar);
        }
    };
    public af a = af.UNSPECIFIED;
    public PointF b;
    public ArrayList c = new ArrayList();

    public ha(bs bsVar) {
        bsVar.h();
        while (bsVar.j()) {
            String l = bsVar.l();
            if (String.BUTTONS.equals(l)) {
                Object obj;
                if (bsVar.k() == bx.BEGIN_ARRAY) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    bsVar.a(this.c, gz.n);
                } else {
                    bsVar.s();
                }
            } else if ("window_aspect_ratio".equals(l)) {
                if (bsVar.a()) {
                    PointF pointF = new PointF();
                    bsVar.h();
                    while (bsVar.j()) {
                        String l2 = bsVar.l();
                        if ("width".equals(l2)) {
                            pointF.x = (float) bsVar.p();
                        } else if ("height".equals(l2)) {
                            pointF.y = (float) bsVar.p();
                        } else {
                            bsVar.s();
                        }
                    }
                    bsVar.i();
                    if (!(pointF.x == 0.0f || pointF.y == 0.0f)) {
                        this.b = pointF;
                    }
                } else {
                    bsVar.s();
                }
            } else if (AdUnitActivity.EXTRA_ORIENTATION.equals(l)) {
                l = bsVar.m();
                if (String.LANDSCAPE.equals(l)) {
                    this.a = af.LANDSCAPE;
                } else if ("portrait".equals(l)) {
                    this.a = af.PORTRAIT;
                }
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
    }
}
