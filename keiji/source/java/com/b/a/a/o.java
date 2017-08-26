package com.b.a.a;

import a.a.a.a.a.b.k;
import a.a.a.a.a.d.b;
import a.a.a.a.a.d.c;
import android.content.Context;
import java.util.UUID;

/* compiled from: SessionAnalyticsFilesManager */
class o extends b<s> {
    private a.a.a.a.a.g.b g;

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

    void a(a.a.a.a.a.g.b bVar) {
        this.g = bVar;
    }
}
