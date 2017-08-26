package com.tapjoy.internal;

import com.tapjoy.internal.gd.c;
import com.tapjoy.internal.gd.d;
import com.tapjoy.internal.gd.d.a;
import com.tapjoy.internal.gd.i;
import java.util.Map;

public final class gr extends gp {
    public final a c = d.e();
    private i d = null;

    public final String c() {
        return "api/v1/cevs";
    }

    public final boolean a(c cVar) {
        if (this.d == null) {
            this.d = cVar.c;
        } else if (cVar.c != this.d) {
            return false;
        }
        a aVar = this.c;
        if (cVar == null) {
            throw new NullPointerException();
        }
        aVar.e();
        aVar.b.add(cVar);
        return true;
    }

    public final Map e() {
        Map e = super.e();
        e.put("events", new bq(gc.a(this.c.d())));
        return e;
    }
}
