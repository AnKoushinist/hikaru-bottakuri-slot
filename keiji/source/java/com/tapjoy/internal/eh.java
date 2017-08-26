package com.tapjoy.internal;

import twitter4j.TwitterResponse;

public final class eh extends dl {
    public static final dn c = new b();
    public static final Long d = Long.valueOf(0);
    public static final Long e = Long.valueOf(0);
    public final String f;
    public final Long g;
    public final Long h;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public Long d;
        public Long e;

        public final eh b() {
            if (this.c != null && this.d != null) {
                return new eh(this.c, this.d, this.e, super.a());
            }
            throw ds.a(this.c, "id", this.d, "received");
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            eh ehVar = (eh) obj;
            int a2 = dn.i.a(2, ehVar.g) + dn.p.a(1, ehVar.f);
            if (ehVar.h != null) {
                a = dn.i.a(3, ehVar.h);
            } else {
                a = 0;
            }
            return (a + a2) + ehVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            eh ehVar = (eh) obj;
            dn.p.a(dpVar, 1, ehVar.f);
            dn.i.a(dpVar, 2, ehVar.g);
            if (ehVar.h != null) {
                dn.i.a(dpVar, 3, ehVar.h);
            }
            dpVar.a(ehVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, eh.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c = (String) dn.p.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            aVar.d = (Long) dn.i.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (Long) dn.i.a(doVar);
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

    public eh(String str, Long l) {
        this(str, l, null, hu.b);
    }

    public eh(String str, Long l, Long l2, hu huVar) {
        super(c, huVar);
        this.f = str;
        this.g = l;
        this.h = l2;
    }

    public final a b() {
        a aVar = new a();
        aVar.c = this.f;
        aVar.d = this.g;
        aVar.e = this.h;
        aVar.a(a());
        return aVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eh)) {
            return false;
        }
        eh ehVar = (eh) obj;
        if (a().equals(ehVar.a()) && this.f.equals(ehVar.f) && this.g.equals(ehVar.g) && ds.a(this.h, ehVar.h)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        i = (this.h != null ? this.h.hashCode() : 0) + (((((a().hashCode() * 37) + this.f.hashCode()) * 37) + this.g.hashCode()) * 37);
        this.b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", id=").append(this.f);
        stringBuilder.append(", received=").append(this.g);
        if (this.h != null) {
            stringBuilder.append(", clicked=").append(this.h);
        }
        return stringBuilder.replace(0, 2, "Push{").append('}').toString();
    }
}
