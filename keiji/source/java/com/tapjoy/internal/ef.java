package com.tapjoy.internal;

import twitter4j.TwitterResponse;

public final class ef extends dl {
    public static final dn c = new b();
    public final String d;
    public final String e;
    public final String f;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public String d;
        public String e;

        public final ef b() {
            return new ef(this.c, this.d, this.e, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int a2;
            int i = 0;
            ef efVar = (ef) obj;
            if (efVar.d != null) {
                a = dn.p.a(1, efVar.d);
            } else {
                a = 0;
            }
            if (efVar.e != null) {
                a2 = dn.p.a(2, efVar.e);
            } else {
                a2 = 0;
            }
            a += a2;
            if (efVar.f != null) {
                i = dn.p.a(3, efVar.f);
            }
            return (a + i) + efVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ef efVar = (ef) obj;
            if (efVar.d != null) {
                dn.p.a(dpVar, 1, efVar.d);
            }
            if (efVar.e != null) {
                dn.p.a(dpVar, 2, efVar.e);
            }
            if (efVar.f != null) {
                dn.p.a(dpVar, 3, efVar.f);
            }
            dpVar.a(efVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ef.class);
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
                            aVar.d = (String) dn.p.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (String) dn.p.a(doVar);
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

    public ef(String str, String str2, String str3) {
        this(str, str2, str3, hu.b);
    }

    public ef(String str, String str2, String str3, hu huVar) {
        super(c, huVar);
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ef)) {
            return false;
        }
        ef efVar = (ef) obj;
        if (a().equals(efVar.a()) && ds.a(this.d, efVar.d) && ds.a(this.e, efVar.e) && ds.a(this.f, efVar.f)) {
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
            stringBuilder.append(", fq7Change=").append(this.d);
        }
        if (this.e != null) {
            stringBuilder.append(", fq30Change=").append(this.e);
        }
        if (this.f != null) {
            stringBuilder.append(", pushId=").append(this.f);
        }
        return stringBuilder.replace(0, 2, "Meta{").append('}').toString();
    }
}
