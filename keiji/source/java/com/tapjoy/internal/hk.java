package com.tapjoy.internal;

import com.tapjoy.internal.dz.a;
import java.util.Map;

public final class hk extends hi {
    private final a c = new a();
    private eb d = null;

    public final String c() {
        return this.d == eb.USAGES ? "api/v1/usages" : "api/v1/cevs";
    }

    public final boolean a(dy dyVar) {
        if (this.d == null) {
            this.d = dyVar.n;
        } else if (dyVar.n != this.d) {
            return false;
        }
        this.c.c.add(dyVar);
        return true;
    }

    public final int g() {
        return this.c.c.size();
    }

    public final Map e() {
        Map e = super.e();
        e.put("events", new br(gp.a(this.c.b())));
        return e;
    }
}
