package com.tapjoy.internal;

import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class eg extends dl {
    public static final dn c = new b();
    public static final Integer d = Integer.valueOf(1);
    public static final Double e = Double.valueOf(0.0d);
    public static final Integer f = Integer.valueOf(0);
    public static final Long g = Long.valueOf(0);
    public final String h;
    public final Integer i;
    public final Double j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final Integer p;
    public final Long q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public Integer d;
        public Double e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public Integer k;
        public Long l;
        public String m;
        public String n;
        public String o;
        public String p;

        public final eg b() {
            if (this.c != null) {
                return new eg(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, super.a());
            }
            throw ds.a(this.c, "productId");
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int i = 0;
            eg egVar = (eg) obj;
            int a2 = dn.p.a(1, egVar.h);
            if (egVar.i != null) {
                a = dn.d.a(2, egVar.i);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.j != null) {
                a = dn.o.a(3, egVar.j);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.k != null) {
                a = dn.p.a(4, egVar.k);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.l != null) {
                a = dn.p.a(5, egVar.l);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.m != null) {
                a = dn.p.a(6, egVar.m);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.n != null) {
                a = dn.p.a(7, egVar.n);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.o != null) {
                a = dn.p.a(8, egVar.o);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.p != null) {
                a = dn.d.a(9, egVar.p);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.q != null) {
                a = dn.i.a(10, egVar.q);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.r != null) {
                a = dn.p.a(11, egVar.r);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.s != null) {
                a = dn.p.a(12, egVar.s);
            } else {
                a = 0;
            }
            a2 += a;
            if (egVar.t != null) {
                a = dn.p.a(13, egVar.t);
            } else {
                a = 0;
            }
            a += a2;
            if (egVar.u != null) {
                i = dn.p.a(14, egVar.u);
            }
            return (a + i) + egVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            eg egVar = (eg) obj;
            dn.p.a(dpVar, 1, egVar.h);
            if (egVar.i != null) {
                dn.d.a(dpVar, 2, egVar.i);
            }
            if (egVar.j != null) {
                dn.o.a(dpVar, 3, egVar.j);
            }
            if (egVar.k != null) {
                dn.p.a(dpVar, 4, egVar.k);
            }
            if (egVar.l != null) {
                dn.p.a(dpVar, 5, egVar.l);
            }
            if (egVar.m != null) {
                dn.p.a(dpVar, 6, egVar.m);
            }
            if (egVar.n != null) {
                dn.p.a(dpVar, 7, egVar.n);
            }
            if (egVar.o != null) {
                dn.p.a(dpVar, 8, egVar.o);
            }
            if (egVar.p != null) {
                dn.d.a(dpVar, 9, egVar.p);
            }
            if (egVar.q != null) {
                dn.i.a(dpVar, 10, egVar.q);
            }
            if (egVar.r != null) {
                dn.p.a(dpVar, 11, egVar.r);
            }
            if (egVar.s != null) {
                dn.p.a(dpVar, 12, egVar.s);
            }
            if (egVar.t != null) {
                dn.p.a(dpVar, 13, egVar.t);
            }
            if (egVar.u != null) {
                dn.p.a(dpVar, 14, egVar.u);
            }
            dpVar.a(egVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, eg.class);
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
                            aVar.e = (Double) dn.o.a(doVar);
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
                            aVar.i = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            aVar.j = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            aVar.k = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            aVar.l = (Long) dn.i.a(doVar);
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

    public eg(String str, Integer num, Double d, String str2, String str3, String str4, String str5, String str6, Integer num2, Long l, String str7, String str8, String str9, String str10, hu huVar) {
        super(c, huVar);
        this.h = str;
        this.i = num;
        this.j = d;
        this.k = str2;
        this.l = str3;
        this.m = str4;
        this.n = str5;
        this.o = str6;
        this.p = num2;
        this.q = l;
        this.r = str7;
        this.s = str8;
        this.t = str9;
        this.u = str10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eg)) {
            return false;
        }
        eg egVar = (eg) obj;
        if (a().equals(egVar.a()) && this.h.equals(egVar.h) && ds.a(this.i, egVar.i) && ds.a(this.j, egVar.j) && ds.a(this.k, egVar.k) && ds.a(this.l, egVar.l) && ds.a(this.m, egVar.m) && ds.a(this.n, egVar.n) && ds.a(this.o, egVar.o) && ds.a(this.p, egVar.p) && ds.a(this.q, egVar.q) && ds.a(this.r, egVar.r) && ds.a(this.s, egVar.s) && ds.a(this.t, egVar.t) && ds.a(this.u, egVar.u)) {
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
        int hashCode = ((this.i != null ? this.i.hashCode() : 0) + (((a().hashCode() * 37) + this.h.hashCode()) * 37)) * 37;
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
        i2 = (i2 + hashCode) * 37;
        if (this.u != null) {
            i = this.u.hashCode();
        }
        i2 += i;
        this.b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", productId=").append(this.h);
        if (this.i != null) {
            stringBuilder.append(", productQuantity=").append(this.i);
        }
        if (this.j != null) {
            stringBuilder.append(", productPrice=").append(this.j);
        }
        if (this.k != null) {
            stringBuilder.append(", productPriceCurrency=").append(this.k);
        }
        if (this.l != null) {
            stringBuilder.append(", productType=").append(this.l);
        }
        if (this.m != null) {
            stringBuilder.append(", productTitle=").append(this.m);
        }
        if (this.n != null) {
            stringBuilder.append(", productDescription=").append(this.n);
        }
        if (this.o != null) {
            stringBuilder.append(", transactionId=").append(this.o);
        }
        if (this.p != null) {
            stringBuilder.append(", transactionState=").append(this.p);
        }
        if (this.q != null) {
            stringBuilder.append(", transactionDate=").append(this.q);
        }
        if (this.r != null) {
            stringBuilder.append(", campaignId=").append(this.r);
        }
        if (this.s != null) {
            stringBuilder.append(", currencyPrice=").append(this.s);
        }
        if (this.t != null) {
            stringBuilder.append(", receipt=").append(this.t);
        }
        if (this.u != null) {
            stringBuilder.append(", signature=").append(this.u);
        }
        return stringBuilder.replace(0, 2, "Purchase{").append('}').toString();
    }
}
