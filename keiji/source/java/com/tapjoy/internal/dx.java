package com.tapjoy.internal;

import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class dx extends dl {
    public static final dn c = new b();
    public static final Integer d = Integer.valueOf(0);
    public final String e;
    public final Integer f;
    public final String g;
    public final String h;
    public final String i;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public Integer d;
        public String e;
        public String f;
        public String g;

        public final dx b() {
            return new dx(this.c, this.d, this.e, this.f, this.g, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int a2;
            int i = 0;
            dx dxVar = (dx) obj;
            if (dxVar.e != null) {
                a = dn.p.a(1, dxVar.e);
            } else {
                a = 0;
            }
            if (dxVar.f != null) {
                a2 = dn.d.a(2, dxVar.f);
            } else {
                a2 = 0;
            }
            a2 += a;
            if (dxVar.g != null) {
                a = dn.p.a(3, dxVar.g);
            } else {
                a = 0;
            }
            a2 += a;
            if (dxVar.h != null) {
                a = dn.p.a(4, dxVar.h);
            } else {
                a = 0;
            }
            a += a2;
            if (dxVar.i != null) {
                i = dn.p.a(5, dxVar.i);
            }
            return (a + i) + dxVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            dx dxVar = (dx) obj;
            if (dxVar.e != null) {
                dn.p.a(dpVar, 1, dxVar.e);
            }
            if (dxVar.f != null) {
                dn.d.a(dpVar, 2, dxVar.f);
            }
            if (dxVar.g != null) {
                dn.p.a(dpVar, 3, dxVar.g);
            }
            if (dxVar.h != null) {
                dn.p.a(dpVar, 4, dxVar.h);
            }
            if (dxVar.i != null) {
                dn.p.a(dpVar, 5, dxVar.i);
            }
            dpVar.a(dxVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, dx.class);
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
                            aVar.d = (Integer) dn.d.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                            aVar.f = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                            aVar.g = (String) dn.p.a(doVar);
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

    public dx(String str, Integer num, String str2, String str3, String str4, hu huVar) {
        super(c, huVar);
        this.e = str;
        this.f = num;
        this.g = str2;
        this.h = str3;
        this.i = str4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dx)) {
            return false;
        }
        dx dxVar = (dx) obj;
        if (a().equals(dxVar.a()) && ds.a(this.e, dxVar.e) && ds.a(this.f, dxVar.f) && ds.a(this.g, dxVar.g) && ds.a(this.h, dxVar.h) && ds.a(this.i, dxVar.i)) {
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
        int hashCode = ((this.e != null ? this.e.hashCode() : 0) + (a().hashCode() * 37)) * 37;
        if (this.f != null) {
            i2 = this.f.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.g != null) {
            i2 = this.g.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.h != null) {
            i2 = this.h.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.i != null) {
            i = this.i.hashCode();
        }
        i2 += i;
        this.b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.e != null) {
            stringBuilder.append(", pkgVer=").append(this.e);
        }
        if (this.f != null) {
            stringBuilder.append(", pkgRev=").append(this.f);
        }
        if (this.g != null) {
            stringBuilder.append(", dataVer=").append(this.g);
        }
        if (this.h != null) {
            stringBuilder.append(", installer=").append(this.h);
        }
        if (this.i != null) {
            stringBuilder.append(", store=").append(this.i);
        }
        return stringBuilder.replace(0, 2, "App{").append('}').toString();
    }
}
