package com.tapjoy.internal;

import android.graphics.Point;
import android.os.SystemClock;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public final class gh extends ge {
    public static final bm n = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            return new gh(brVar);
        }
    };
    public gk a;
    public gk b;
    public gk c;
    public Point d;
    public gk e;
    public gk f;
    public String g;
    public ey h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public Map k;
    public long l;
    public gi m;

    gh(br brVar) {
        Iterator it;
        gg ggVar;
        brVar.h();
        String str = null;
        String str2 = null;
        while (brVar.j()) {
            String l = brVar.l();
            if ("frame".equals(l)) {
                brVar.h();
                while (brVar.j()) {
                    l = brVar.l();
                    if ("portrait".equals(l)) {
                        this.a = (gk) gk.c.a(brVar);
                    } else if (String.LANDSCAPE.equals(l)) {
                        this.b = (gk) gk.c.a(brVar);
                    } else if ("close_button".equals(l)) {
                        this.c = (gk) gk.c.a(brVar);
                    } else if ("close_button_offset".equals(l)) {
                        this.d = (Point) bn.a.a(brVar);
                    } else {
                        brVar.s();
                    }
                }
                brVar.i();
            } else if ("creative".equals(l)) {
                brVar.h();
                while (brVar.j()) {
                    l = brVar.l();
                    if ("portrait".equals(l)) {
                        this.e = (gk) gk.c.a(brVar);
                    } else if (String.LANDSCAPE.equals(l)) {
                        this.f = (gk) gk.c.a(brVar);
                    } else {
                        brVar.s();
                    }
                }
                brVar.i();
            } else if (String.URL.equals(l)) {
                this.g = brVar.b();
            } else if (gb.a(l)) {
                this.h = gb.a(l, brVar);
            } else if ("mappings".equals(l)) {
                brVar.h();
                while (brVar.j()) {
                    l = brVar.l();
                    if ("portrait".equals(l)) {
                        brVar.a(this.i, gg.h);
                    } else if (String.LANDSCAPE.equals(l)) {
                        brVar.a(this.j, gg.h);
                    } else {
                        brVar.s();
                    }
                }
                brVar.i();
            } else if ("meta".equals(l)) {
                this.k = brVar.d();
            } else if ("ttl".equals(l)) {
                this.l = ((long) (brVar.p() * 1000.0d)) + SystemClock.elapsedRealtime();
            } else if ("no_more_today".equals(l)) {
                this.m = (gi) gi.d.a(brVar);
            } else if ("ad_content".equals(l)) {
                str = brVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str2 = brVar.b();
            } else {
                brVar.s();
            }
        }
        brVar.i();
        if (this.g == null) {
            this.g = BuildConfig.FLAVOR;
        }
        if (this.i != null) {
            it = this.i.iterator();
            while (it.hasNext()) {
                ggVar = (gg) it.next();
                if (ggVar.f == null) {
                    ggVar.f = str;
                }
                if (ggVar.e == null) {
                    ggVar.e = str2;
                }
            }
        }
        if (this.j != null) {
            it = this.j.iterator();
            while (it.hasNext()) {
                ggVar = (gg) it.next();
                if (ggVar.f == null) {
                    ggVar.f = str;
                }
                if (ggVar.e == null) {
                    ggVar.e = str2;
                }
            }
        }
    }

    public final boolean a() {
        return (this.c == null || this.a == null || this.e == null) ? false : true;
    }

    public final boolean b() {
        return (this.c == null || this.b == null || this.f == null) ? false : true;
    }
}
