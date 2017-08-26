package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class gf extends ge {
    public static final bm d = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            return new gf(brVar);
        }
    };
    public ArrayList a = new ArrayList();
    public Map b;
    public float c;

    public gf(br brVar) {
        brVar.h();
        String str = null;
        String str2 = null;
        while (brVar.j()) {
            String l = brVar.l();
            if ("layouts".equals(l)) {
                brVar.a(this.a, gn.d);
            } else if ("meta".equals(l)) {
                this.b = brVar.d();
            } else if ("max_show_time".equals(l)) {
                this.c = (float) brVar.p();
            } else if ("ad_content".equals(l)) {
                str2 = brVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str = brVar.b();
            } else {
                brVar.s();
            }
        }
        brVar.i();
        if (this.a != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                gn gnVar = (gn) it.next();
                if (gnVar.c != null) {
                    Iterator it2 = gnVar.c.iterator();
                    while (it2.hasNext()) {
                        gm gmVar = (gm) it2.next();
                        if (gmVar.i == null) {
                            gmVar.i = str2;
                        }
                        if (gmVar.h == null) {
                            gmVar.h = str;
                        }
                    }
                }
            }
        }
    }
}
