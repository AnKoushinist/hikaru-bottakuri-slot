package com.b.a.a;

import android.content.Context;
import b.a.a.a.a.b.k;
import b.a.a.a.a.d.b;
import b.a.a.a.a.d.c;
import java.util.UUID;

/* compiled from: SessionAnalyticsFilesManager */
class o extends b<s> {
    private b.a.a.a.a.g.b g;

    o(Context context, u uVar, k kVar, c cVar) {
        super(context, uVar, kVar, cVar, 100);
    }

    protected String a() {
        return "sa" + "_" + UUID.randomUUID().toString() + "_" + this.c.a() + ".tap";
    }

    protected int b() {
        return this.g == null ? super.b() : this.g.e;
    }

    protected int c() {
        return this.g == null ? super.c() : this.g.c;
    }

    void a(b.a.a.a.a.g.b bVar) {
        this.g = bVar;
    }
}
