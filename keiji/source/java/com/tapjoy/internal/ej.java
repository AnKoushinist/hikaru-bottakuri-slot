package com.tapjoy.internal;

import java.util.List;
import twitter4j.TwitterResponse;

public final class ej extends dl {
    public static final dn c = new b();
    public final List d;

    public static final class a extends com.tapjoy.internal.dl.a {
        public List c = ds.a();

        public final ej b() {
            return new ej(this.c, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            ej ejVar = (ej) obj;
            return dn.p.a().a(1, ejVar.d) + ejVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ej ejVar = (ej) obj;
            dn.p.a().a(dpVar, 1, ejVar.d);
            dpVar.a(ejVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ej.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c.add(dn.p.a(doVar));
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

    public ej(List list) {
        this(list, hu.b);
    }

    public ej(List list, hu huVar) {
        super(c, huVar);
        this.d = ds.a("elements", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ej)) {
            return false;
        }
        ej ejVar = (ej) obj;
        if (a().equals(ejVar.a()) && this.d.equals(ejVar.d)) {
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
            stringBuilder.append(", elements=").append(this.d);
        }
        return stringBuilder.replace(0, 2, "StringList{").append('}').toString();
    }
}
