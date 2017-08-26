package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.gd.a;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gd.z;
import java.util.Map;

public final class gs extends gp {
    private final l c;
    private final a d;
    private final z e;
    private final String f;

    private gs(l lVar, a aVar, z zVar, String str) {
        this.c = lVar;
        this.d = aVar;
        this.e = zVar;
        this.f = str;
    }

    public final String c() {
        return "api/v1/tokens";
    }

    public final Map e() {
        Object obj;
        Map e = super.e();
        e.put(String.VIDEO_INFO, new bq(gc.a(this.c)));
        e.put("app", new bq(gc.a(this.d)));
        e.put("user", new bq(gc.a(this.e)));
        CharSequence charSequence = this.f;
        if (charSequence == null || charSequence.length() == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            e.put("push_token", this.f);
        }
        return e;
    }

    public gs(n nVar, String str) {
        this(nVar.c, nVar.d, nVar.e, str);
    }
}
