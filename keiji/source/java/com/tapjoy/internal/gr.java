package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class gr extends gq {
    public static final bn d = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            return new gr(bsVar);
        }
    };
    public ArrayList a = new ArrayList();
    public Map b;
    public float c;

    public gr(bs bsVar) {
        bsVar.h();
        String str = null;
        String str2 = null;
        while (bsVar.j()) {
            String l = bsVar.l();
            if ("layouts".equals(l)) {
                bsVar.a(this.a, ha.d);
            } else if ("meta".equals(l)) {
                this.b = bsVar.d();
            } else if ("max_show_time".equals(l)) {
                this.c = (float) bsVar.p();
            } else if ("ad_content".equals(l)) {
                str2 = bsVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str = bsVar.b();
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
        if (this.a != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ha haVar = (ha) it.next();
                if (haVar.c != null) {
                    Iterator it2 = haVar.c.iterator();
                    while (it2.hasNext()) {
                        gz gzVar = (gz) it2.next();
                        if (gzVar.i == null) {
                            gzVar.i = str2;
                        }
                        if (gzVar.h == null) {
                            gzVar.h = str;
                        }
                    }
                }
            }
        }
    }
}
