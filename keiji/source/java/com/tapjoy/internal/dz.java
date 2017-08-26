package com.tapjoy.internal;

import java.util.List;
import twitter4j.TwitterResponse;

public final class dz extends dl {
    public static final dn c = new b();
    public final List d;

    public static final class a extends com.tapjoy.internal.dl.a {
        public List c = ds.a();

        public final dz b() {
            return new dz(this.c, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            dz dzVar = (dz) obj;
            return dy.c.a().a(1, dzVar.d) + dzVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            dz dzVar = (dz) obj;
            dy.c.a().a(dpVar, 1, dzVar.d);
            dpVar.a(dzVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, dz.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c.add(dy.c.a(doVar));
                            break;
                        default:
                            dk c = doVar.c();
                            aVar.a(b, c, c.a().a(doVar));
                            break;
                    }
                }
                doVar.a(a);
                return aVar.b();
            }
        }
    }

    public dz(List list, hu huVar) {
        super(c, huVar);
        this.d = ds.a("events", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dz)) {
            return false;
        }
        dz dzVar = (dz) obj;
        if (a().equals(dzVar.a()) && this.d.equals(dzVar.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        i = (a().hashCode() * 37) + this.d.hashCode();
        this.b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.d.isEmpty()) {
            stringBuilder.append(", events=").append(this.d);
        }
        return stringBuilder.replace(0, 2, "EventBatch{").append('}').toString();
    }
}
