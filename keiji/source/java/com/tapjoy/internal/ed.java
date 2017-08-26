package com.tapjoy.internal;

import android.os.Build;
import android.os.Build.VERSION;
import java.util.Locale;
import java.util.TimeZone;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class ed extends dl {
    public static final dn c = new b();
    public static final Integer d = Integer.valueOf(0);
    public static final Integer e = Integer.valueOf(0);
    public static final Integer f = Integer.valueOf(0);
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final String p;
    public final String q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;
    public final String v;
    public final String w;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public String d;
        public String e = Build.MANUFACTURER;
        public String f = Build.MODEL;
        public String g = "Android";
        public String h = VERSION.RELEASE;
        public Integer i;
        public Integer j;
        public Integer k;
        public String l = Locale.getDefault().toString();
        public String m = TimeZone.getDefault().getID();
        public String n;
        public String o;
        public String p = "11.10.0/Android";
        public String q;
        public String r;
        public String s;

        public final ed b() {
            return new ed(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, super.a());
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int a2;
            int i = 0;
            ed edVar = (ed) obj;
            if (edVar.g != null) {
                a = dn.p.a(1, edVar.g);
            } else {
                a = 0;
            }
            if (edVar.h != null) {
                a2 = dn.p.a(2, edVar.h);
            } else {
                a2 = 0;
            }
            a2 += a;
            if (edVar.i != null) {
                a = dn.p.a(3, edVar.i);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.j != null) {
                a = dn.p.a(4, edVar.j);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.k != null) {
                a = dn.p.a(5, edVar.k);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.l != null) {
                a = dn.p.a(6, edVar.l);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.m != null) {
                a = dn.d.a(7, edVar.m);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.n != null) {
                a = dn.d.a(8, edVar.n);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.o != null) {
                a = dn.d.a(9, edVar.o);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.p != null) {
                a = dn.p.a(10, edVar.p);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.q != null) {
                a = dn.p.a(11, edVar.q);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.r != null) {
                a = dn.p.a(12, edVar.r);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.s != null) {
                a = dn.p.a(13, edVar.s);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.t != null) {
                a = dn.p.a(14, edVar.t);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.u != null) {
                a = dn.p.a(15, edVar.u);
            } else {
                a = 0;
            }
            a2 += a;
            if (edVar.v != null) {
                a = dn.p.a(16, edVar.v);
            } else {
                a = 0;
            }
            a += a2;
            if (edVar.w != null) {
                i = dn.p.a(17, edVar.w);
            }
            return (a + i) + edVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ed edVar = (ed) obj;
            if (edVar.g != null) {
                dn.p.a(dpVar, 1, edVar.g);
            }
            if (edVar.h != null) {
                dn.p.a(dpVar, 2, edVar.h);
            }
            if (edVar.i != null) {
                dn.p.a(dpVar, 3, edVar.i);
            }
            if (edVar.j != null) {
                dn.p.a(dpVar, 4, edVar.j);
            }
            if (edVar.k != null) {
                dn.p.a(dpVar, 5, edVar.k);
            }
            if (edVar.l != null) {
                dn.p.a(dpVar, 6, edVar.l);
            }
            if (edVar.m != null) {
                dn.d.a(dpVar, 7, edVar.m);
            }
            if (edVar.n != null) {
                dn.d.a(dpVar, 8, edVar.n);
            }
            if (edVar.o != null) {
                dn.d.a(dpVar, 9, edVar.o);
            }
            if (edVar.p != null) {
                dn.p.a(dpVar, 10, edVar.p);
            }
            if (edVar.q != null) {
                dn.p.a(dpVar, 11, edVar.q);
            }
            if (edVar.r != null) {
                dn.p.a(dpVar, 12, edVar.r);
            }
            if (edVar.s != null) {
                dn.p.a(dpVar, 13, edVar.s);
            }
            if (edVar.t != null) {
                dn.p.a(dpVar, 14, edVar.t);
            }
            if (edVar.u != null) {
                dn.p.a(dpVar, 15, edVar.u);
            }
            if (edVar.v != null) {
                dn.p.a(dpVar, 16, edVar.v);
            }
            if (edVar.w != null) {
                dn.p.a(dpVar, 17, edVar.w);
            }
            dpVar.a(edVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ed.class);
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
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                            aVar.f = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                            aVar.g = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            aVar.h = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            aVar.i = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            aVar.j = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            aVar.k = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            aVar.l = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_popupTheme /*11*/:
                            aVar.m = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleTextAppearance /*12*/:
                            aVar.n = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                            aVar.o = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMargin /*14*/:
                            aVar.p = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginStart /*15*/:
                            aVar.q = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            aVar.r = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginTop /*17*/:
                            aVar.s = (String) dn.p.a(doVar);
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

    public ed(String str, String str2, String str3, String str4, String str5, String str6, Integer num, Integer num2, Integer num3, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, hu huVar) {
        super(c, huVar);
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = str4;
        this.k = str5;
        this.l = str6;
        this.m = num;
        this.n = num2;
        this.o = num3;
        this.p = str7;
        this.q = str8;
        this.r = str9;
        this.s = str10;
        this.t = str11;
        this.u = str12;
        this.v = str13;
        this.w = str14;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ed)) {
            return false;
        }
        ed edVar = (ed) obj;
        if (a().equals(edVar.a()) && ds.a(this.g, edVar.g) && ds.a(this.h, edVar.h) && ds.a(this.i, edVar.i) && ds.a(this.j, edVar.j) && ds.a(this.k, edVar.k) && ds.a(this.l, edVar.l) && ds.a(this.m, edVar.m) && ds.a(this.n, edVar.n) && ds.a(this.o, edVar.o) && ds.a(this.p, edVar.p) && ds.a(this.q, edVar.q) && ds.a(this.r, edVar.r) && ds.a(this.s, edVar.s) && ds.a(this.t, edVar.t) && ds.a(this.u, edVar.u) && ds.a(this.v, edVar.v) && ds.a(this.w, edVar.w)) {
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
        int hashCode = ((this.g != null ? this.g.hashCode() : 0) + (a().hashCode() * 37)) * 37;
        if (this.h != null) {
            i2 = this.h.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.i != null) {
            i2 = this.i.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.j != null) {
            i2 = this.j.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.k != null) {
            i2 = this.k.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.l != null) {
            i2 = this.l.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.m != null) {
            i2 = this.m.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.n != null) {
            i2 = this.n.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.o != null) {
            i2 = this.o.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.p != null) {
            i2 = this.p.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.q != null) {
            i2 = this.q.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.r != null) {
            i2 = this.r.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.s != null) {
            i2 = this.s.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.t != null) {
            i2 = this.t.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.u != null) {
            i2 = this.u.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.v != null) {
            i2 = this.v.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.w != null) {
            i = this.w.hashCode();
        }
        i2 += i;
        this.b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.g != null) {
            stringBuilder.append(", mac=").append(this.g);
        }
        if (this.h != null) {
            stringBuilder.append(", deviceId=").append(this.h);
        }
        if (this.i != null) {
            stringBuilder.append(", deviceMaker=").append(this.i);
        }
        if (this.j != null) {
            stringBuilder.append(", deviceModel=").append(this.j);
        }
        if (this.k != null) {
            stringBuilder.append(", osName=").append(this.k);
        }
        if (this.l != null) {
            stringBuilder.append(", osVer=").append(this.l);
        }
        if (this.m != null) {
            stringBuilder.append(", displayD=").append(this.m);
        }
        if (this.n != null) {
            stringBuilder.append(", displayW=").append(this.n);
        }
        if (this.o != null) {
            stringBuilder.append(", displayH=").append(this.o);
        }
        if (this.p != null) {
            stringBuilder.append(", locale=").append(this.p);
        }
        if (this.q != null) {
            stringBuilder.append(", timezone=").append(this.q);
        }
        if (this.r != null) {
            stringBuilder.append(", pkgId=").append(this.r);
        }
        if (this.s != null) {
            stringBuilder.append(", pkgSign=").append(this.s);
        }
        if (this.t != null) {
            stringBuilder.append(", sdk=").append(this.t);
        }
        if (this.u != null) {
            stringBuilder.append(", countrySim=").append(this.u);
        }
        if (this.v != null) {
            stringBuilder.append(", countryNet=").append(this.v);
        }
        if (this.w != null) {
            stringBuilder.append(", imei=").append(this.w);
        }
        return stringBuilder.replace(0, 2, "Info{").append('}').toString();
    }
}
