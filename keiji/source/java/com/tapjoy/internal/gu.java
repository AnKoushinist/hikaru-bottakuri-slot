package com.tapjoy.internal;

import android.graphics.Point;
import android.os.SystemClock;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public final class gu extends gq {
    public static final bn n = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            return new gu(bsVar);
        }
    };
    public gx a;
    public gx b;
    public gx c;
    public Point d;
    public gx e;
    public gx f;
    public String g;
    public fl h;
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public Map k;
    public long l;
    public gv m;

    gu(bs bsVar) {
        Iterator it;
        gs gsVar;
        bsVar.h();
        String str = null;
        String str2 = null;
        while (bsVar.j()) {
            String l = bsVar.l();
            if ("frame".equals(l)) {
                bsVar.h();
                while (bsVar.j()) {
                    l = bsVar.l();
                    if ("portrait".equals(l)) {
                        this.a = (gx) gx.e.a(bsVar);
                    } else if (String.LANDSCAPE.equals(l)) {
                        this.b = (gx) gx.e.a(bsVar);
                    } else if ("close_button".equals(l)) {
                        this.c = (gx) gx.e.a(bsVar);
                    } else if ("close_button_offset".equals(l)) {
                        this.d = (Point) bo.a.a(bsVar);
                    } else {
                        bsVar.s();
                    }
                }
                bsVar.i();
            } else if ("creative".equals(l)) {
                bsVar.h();
                while (bsVar.j()) {
                    l = bsVar.l();
                    if ("portrait".equals(l)) {
                        this.e = (gx) gx.e.a(bsVar);
                    } else if (String.LANDSCAPE.equals(l)) {
                        this.f = (gx) gx.e.a(bsVar);
                    } else {
                        bsVar.s();
                    }
                }
                bsVar.i();
            } else if (String.URL.equals(l)) {
                this.g = bsVar.b();
            } else if (go.a(l)) {
                this.h = go.a(l, bsVar);
            } else if ("mappings".equals(l)) {
                bsVar.h();
                while (bsVar.j()) {
                    l = bsVar.l();
                    if ("portrait".equals(l)) {
                        bsVar.a(this.i, gs.h);
                    } else if (String.LANDSCAPE.equals(l)) {
                        bsVar.a(this.j, gs.h);
                    } else {
                        bsVar.s();
                    }
                }
                bsVar.i();
            } else if ("meta".equals(l)) {
                this.k = bsVar.d();
            } else if ("ttl".equals(l)) {
                this.l = ((long) (bsVar.p() * 1000.0d)) + SystemClock.elapsedRealtime();
            } else if ("no_more_today".equals(l)) {
                this.m = (gv) gv.d.a(bsVar);
            } else if ("ad_content".equals(l)) {
                str = bsVar.b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str2 = bsVar.b();
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
        if (this.g == null) {
            this.g = BuildConfig.FLAVOR;
        }
        if (this.i != null) {
            it = this.i.iterator();
            while (it.hasNext()) {
                gsVar = (gs) it.next();
                if (gsVar.f == null) {
                    gsVar.f = str;
                }
                if (gsVar.e == null) {
                    gsVar.e = str2;
                }
            }
        }
        if (this.j != null) {
            it = this.j.iterator();
            while (it.hasNext()) {
                gsVar = (gs) it.next();
                if (gsVar.f == null) {
                    gsVar.f = str;
                }
                if (gsVar.e == null) {
                    gsVar.e = str2;
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
