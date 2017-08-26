package com.tapjoy.internal;

import twitter4j.TwitterResponse;

public final class ee extends dl {
    public static final dn c = new b();
    public final ed d;
    public final dx e;
    public final ek f;

    public static final class a extends com.tapjoy.internal.dl.a {
        public ed c;
        public dx d;
        public ek e;

        public final ee b() {
            return new ee(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int a2;
            int i = 0;
            ee eeVar = (ee) obj;
            if (eeVar.d != null) {
                a = ed.c.a(1, eeVar.d);
            } else {
                a = 0;
            }
            if (eeVar.e != null) {
                a2 = dx.c.a(2, eeVar.e);
            } else {
                a2 = 0;
            }
            a += a2;
            if (eeVar.f != null) {
                i = ek.c.a(3, eeVar.f);
            }
            return (a + i) + eeVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ee eeVar = (ee) obj;
            if (eeVar.d != null) {
                ed.c.a(dpVar, 1, eeVar.d);
            }
            if (eeVar.e != null) {
                dx.c.a(dpVar, 2, eeVar.e);
            }
            if (eeVar.f != null) {
                ek.c.a(dpVar, 3, eeVar.f);
            }
            dpVar.a(eeVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ee.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c = (ed) ed.c.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            aVar.d = (dx) dx.c.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (ek) ek.c.a(doVar);
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

    public ee(ed edVar, dx dxVar, ek ekVar) {
        this(edVar, dxVar, ekVar, hu.b);
    }

    public ee(ed edVar, dx dxVar, ek ekVar, hu huVar) {
        super(c, huVar);
        this.d = edVar;
        this.e = dxVar;
        this.f = ekVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ee)) {
            return false;
        }
        ee eeVar = (ee) obj;
        if (a().equals(eeVar.a()) && ds.a(this.d, eeVar.d) && ds.a(this.e, eeVar.e) && ds.a(this.f, eeVar.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = ((this.d != null ? this.d.hashCode() : 0) + (a().hashCode() * 37)) * 37;
        if (this.e != null) {
            i2 = this.e.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        i2 += i;
        this.b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.d != null) {
            stringBuilder.append(", info=").append(this.d);
        }
        if (this.e != null) {
            stringBuilder.append(", app=").append(this.e);
        }
        if (this.f != null) {
            stringBuilder.append(", user=").append(this.f);
        }
        return stringBuilder.replace(0, 2, "InfoSet{").append('}').toString();
    }
}
