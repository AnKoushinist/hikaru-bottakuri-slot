package com.tapjoy.internal;

import android.graphics.PointF;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.adunit.AdUnitActivity;
import java.util.ArrayList;

public final class gn {
    public static final bm d = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            return new gn(brVar);
        }
    };
    public af a = af.UNSPECIFIED;
    public PointF b;
    public ArrayList c = new ArrayList();

    public gn(br brVar) {
        brVar.h();
        while (brVar.j()) {
            String l = brVar.l();
            if (String.BUTTONS.equals(l)) {
                Object obj;
                if (brVar.k() == bw.BEGIN_ARRAY) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    brVar.a(this.c, gm.n);
                } else {
                    brVar.s();
                }
            } else if ("window_aspect_ratio".equals(l)) {
                if (brVar.a()) {
                    PointF pointF = new PointF();
                    brVar.h();
                    while (brVar.j()) {
                        String l2 = brVar.l();
                        if ("width".equals(l2)) {
                            pointF.x = (float) brVar.p();
                        } else if ("height".equals(l2)) {
                            pointF.y = (float) brVar.p();
                        } else {
                            brVar.s();
                        }
                    }
                    brVar.i();
                    if (!(pointF.x == 0.0f || pointF.y == 0.0f)) {
                        this.b = pointF;
                    }
                } else {
                    brVar.s();
                }
            } else if (AdUnitActivity.EXTRA_ORIENTATION.equals(l)) {
                l = brVar.m();
                if (String.LANDSCAPE.equals(l)) {
                    this.a = af.LANDSCAPE;
                } else if ("portrait".equals(l)) {
                    this.a = af.PORTRAIT;
                }
            } else {
                brVar.s();
            }
        }
        brVar.i();
    }
}
