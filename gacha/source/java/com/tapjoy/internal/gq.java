package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.z;
import java.util.List;
import java.util.Map;

public final class gq extends gp {
    public final String c;
    public boolean d = false;
    private final fm e;
    private final l f;
    private final com.tapjoy.internal.gd.a g;
    private final z h;
    private Context i;

    public static class a {
        public ft a;
        public final List b;

        public a(ft ftVar, List list) {
            this.a = ftVar;
            this.b = list;
        }
    }

    protected final /* synthetic */ Object a(br brVar) {
        brVar.h();
        List list = null;
        gf gfVar = null;
        gh ghVar = null;
        while (brVar.j()) {
            String l = brVar.l();
            if ("interstitial".equals(l)) {
                ghVar = (gh) brVar.a(gh.n);
            } else if ("contextual_button".equals(l)) {
                gfVar = (gf) brVar.a(gf.d);
            } else if ("enabled_placements".equals(l)) {
                list = brVar.c();
            } else {
                brVar.s();
            }
        }
        brVar.i();
        if (ghVar != null && (ghVar.a() || ghVar.b())) {
            return new a(new fr(this.e, this.c, ghVar, this.i), list);
        }
        if (gfVar != null) {
            return new a(new fi(this.e, this.c, gfVar, this.i), list);
        }
        return new a(new fs(), list);
    }

    public gq(fm fmVar, l lVar, com.tapjoy.internal.gd.a aVar, z zVar, String str, Context context) {
        this.e = fmVar;
        this.f = lVar;
        this.g = aVar;
        this.h = zVar;
        this.c = str;
        this.i = context;
    }

    public final String c() {
        return "placement";
    }

    public final Map e() {
        Map e = super.e();
        e.put(String.VIDEO_INFO, new bq(gc.a(this.f)));
        e.put("app", new bq(gc.a(this.g)));
        e.put("user", new bq(gc.a(this.h)));
        e.put("placement", this.c);
        return e;
    }

    protected final /* synthetic */ Object f() {
        a aVar = (a) super.f();
        if (!(aVar.a instanceof fs)) {
            aVar.a.a();
            if (!aVar.a.b()) {
                new Object[1][0] = this.c;
                aVar.a = new fs();
            }
        }
        return aVar;
    }
}
