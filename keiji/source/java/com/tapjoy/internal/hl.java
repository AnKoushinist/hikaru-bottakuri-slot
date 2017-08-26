package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import java.util.Map;

public final class hl extends hi {
    private final ed c;
    private final dx d;
    private final ek e;
    private final String f;

    private hl(ed edVar, dx dxVar, ek ekVar, String str) {
        this.c = edVar;
        this.d = dxVar;
        this.e = ekVar;
        this.f = str;
    }

    public hl(ee eeVar, String str) {
        this(eeVar.d, eeVar.e, eeVar.f, str);
    }

    public final String c() {
        return "api/v1/tokens";
    }

    public final Map e() {
        Map e = super.e();
        e.put(String.VIDEO_INFO, new br(gp.a(this.c)));
        e.put("app", new br(gp.a(this.d)));
        e.put("user", new br(gp.a(this.e)));
        if (!aq.a(this.f)) {
            e.put("push_token", this.f);
        }
        return e;
    }
}
