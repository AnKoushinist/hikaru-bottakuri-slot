package com.tapjoy.internal;

import java.util.List;
import twitter4j.TwitterResponse;

public final class ei extends dl {
    public static final dn c = new b();
    public final List d;

    public static final class a extends com.tapjoy.internal.dl.a {
        public List c = ds.a();

        public final ei b() {
            return new ei(this.c, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            ei eiVar = (ei) obj;
            return eh.c.a().a(1, eiVar.d) + eiVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ei eiVar = (ei) obj;
            eh.c.a().a(dpVar, 1, eiVar.d);
            dpVar.a(eiVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ei.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c.add(eh.c.a(doVar));
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

    public ei(List list) {
        this(list, hu.b);
    }

    public ei(List list, hu huVar) {
        super(c, huVar);
        this.d = ds.a("pushes", list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ei)) {
            return false;
        }
        ei eiVar = (ei) obj;
        if (a().equals(eiVar.a()) && this.d.equals(eiVar.d)) {
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
            stringBuilder.append(", pushes=").append(this.d);
        }
        return stringBuilder.replace(0, 2, "PushList{").append('}').toString();
    }
}
