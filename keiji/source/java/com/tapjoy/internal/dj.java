package com.tapjoy.internal;

import com.tapjoy.internal.dn.a;

public abstract class dj extends dn {
    public abstract dq a(int i);

    public final /* synthetic */ void a(dp dpVar, Object obj) {
        dpVar.c(((dq) obj).a());
    }

    public dj(Class cls) {
        super(dk.VARINT, cls);
    }

    public final /* synthetic */ Object a(do doVar) {
        int d = doVar.d();
        dq a = a(d);
        if (a != null) {
            return a;
        }
        throw new a(d, this.a);
    }
}
