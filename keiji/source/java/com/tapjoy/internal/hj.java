package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.List;
import java.util.Map;

public final class hj extends hi {
    public final String c;
    public boolean d = false;
    private final fz e;
    private final ed f;
    private final dx g;
    private final ek h;
    private Context i;

    public static class a {
        public gg a;
        public final List b;

        public a(gg ggVar, List list) {
            this.a = ggVar;
            this.b = list;
        }
    }

    protected final /* synthetic */ Object a(bs bsVar) {
        bsVar.h();
        List list = null;
        gr grVar = null;
        gu guVar = null;
        while (bsVar.j()) {
            String l = bsVar.l();
            if ("interstitial".equals(l)) {
                guVar = (gu) bsVar.a(gu.n);
            } else if ("contextual_button".equals(l)) {
                grVar = (gr) bsVar.a(gr.d);
            } else if ("enabled_placements".equals(l)) {
                list = bsVar.c();
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
        if (guVar != null && (guVar.a() || guVar.b())) {
            return new a(new ge(this.e, this.c, guVar, this.i), list);
        }
        if (grVar != null) {
            return new a(new fv(this.e, this.c, grVar, this.i), list);
        }
        return new a(new gf(), list);
    }

    public hj(fz fzVar, ed edVar, dx dxVar, ek ekVar, String str, Context context) {
        this.e = fzVar;
        this.f = edVar;
        this.g = dxVar;
        this.h = ekVar;
        this.c = str;
        this.i = context;
    }

    public final String c() {
        return "placement";
    }

    public final Map e() {
        Map e = super.e();
        e.put(String.VIDEO_INFO, new br(gp.a(this.f)));
        e.put("app", new br(gp.a(this.g)));
        e.put("user", new br(gp.a(this.h)));
        e.put("placement", this.c);
        return e;
    }

    protected final /* synthetic */ Object f() {
        a aVar = (a) super.f();
        if (!(aVar.a instanceof gf)) {
            aVar.a.a();
            if (!aVar.a.b()) {
                new Object[1][0] = this.c;
                aVar.a = new gf();
            }
        }
        return aVar;
    }
}
