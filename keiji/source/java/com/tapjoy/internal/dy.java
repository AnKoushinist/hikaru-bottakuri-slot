package com.tapjoy.internal;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class dy extends dl {
    public static final dn c = new b();
    public static final eb d = eb.APP;
    public static final Long e = Long.valueOf(0);
    public static final Long f = Long.valueOf(0);
    public static final Long g = Long.valueOf(0);
    public static final Long h = Long.valueOf(0);
    public static final Integer i = Integer.valueOf(0);
    public static final Integer j = Integer.valueOf(0);
    public static final Integer k = Integer.valueOf(0);
    public static final Long l = Long.valueOf(0);
    public static final Long m = Long.valueOf(0);
    public final eg A;
    public final String B;
    public final String C;
    public final ef D;
    public final String E;
    public final String F;
    public final String G;
    public final List H;
    public final String I;
    public final Integer J;
    public final Long K;
    public final Long L;
    public final eb n;
    public final String o;
    public final Long p;
    public final Long q;
    public final String r;
    public final Long s;
    public final Long t;
    public final ed u;
    public final dx v;
    public final ek w;
    public final Integer x;
    public final Integer y;
    public final ea z;

    public static final class a extends com.tapjoy.internal.dl.a {
        public Long A;
        public eb c;
        public String d;
        public Long e;
        public Long f;
        public String g;
        public Long h;
        public Long i;
        public ed j;
        public dx k;
        public ek l;
        public Integer m;
        public Integer n;
        public ea o;
        public eg p;
        public String q;
        public String r;
        public ef s;
        public String t;
        public String u;
        public String v;
        public List w = ds.a();
        public String x;
        public Integer y;
        public Long z;

        public final dy b() {
            if (this.c != null && this.d != null && this.e != null) {
                return new dy(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, super.a());
            }
            throw ds.a(this.c, MoatAdEvent.EVENT_TYPE, this.d, MediationMetaData.KEY_NAME, this.e, "time");
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            int i = 0;
            dy dyVar = (dy) obj;
            int a2 = dn.i.a(3, dyVar.p) + (eb.ADAPTER.a(1, dyVar.n) + dn.p.a(2, dyVar.o));
            if (dyVar.q != null) {
                a = dn.i.a(19, dyVar.q);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.r != null) {
                a = dn.p.a(20, dyVar.r);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.s != null) {
                a = dn.i.a(21, dyVar.s);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.t != null) {
                a = dn.i.a(4, dyVar.t);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.u != null) {
                a = ed.c.a(5, dyVar.u);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.v != null) {
                a = dx.c.a(6, dyVar.v);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.w != null) {
                a = ek.c.a(7, dyVar.w);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.x != null) {
                a = dn.d.a(8, dyVar.x);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.y != null) {
                a = dn.d.a(9, dyVar.y);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.z != null) {
                a = ea.c.a(10, dyVar.z);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.A != null) {
                a = eg.c.a(11, dyVar.A);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.B != null) {
                a = dn.p.a(12, dyVar.B);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.C != null) {
                a = dn.p.a(13, dyVar.C);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.D != null) {
                a = ef.c.a(18, dyVar.D);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.E != null) {
                a = dn.p.a(14, dyVar.E);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.F != null) {
                a = dn.p.a(15, dyVar.F);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.G != null) {
                a = dn.p.a(16, dyVar.G);
            } else {
                a = 0;
            }
            a2 = ec.c.a().a(17, dyVar.H) + (a + a2);
            if (dyVar.I != null) {
                a = dn.p.a(22, dyVar.I);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.J != null) {
                a = dn.d.a(23, dyVar.J);
            } else {
                a = 0;
            }
            a2 += a;
            if (dyVar.K != null) {
                a = dn.i.a(24, dyVar.K);
            } else {
                a = 0;
            }
            a += a2;
            if (dyVar.L != null) {
                i = dn.i.a(25, dyVar.L);
            }
            return (a + i) + dyVar.a().c();
        }

        public final /* synthetic */ Object a(do doVar) {
            return b(doVar);
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            dy dyVar = (dy) obj;
            eb.ADAPTER.a(dpVar, 1, dyVar.n);
            dn.p.a(dpVar, 2, dyVar.o);
            dn.i.a(dpVar, 3, dyVar.p);
            if (dyVar.q != null) {
                dn.i.a(dpVar, 19, dyVar.q);
            }
            if (dyVar.r != null) {
                dn.p.a(dpVar, 20, dyVar.r);
            }
            if (dyVar.s != null) {
                dn.i.a(dpVar, 21, dyVar.s);
            }
            if (dyVar.t != null) {
                dn.i.a(dpVar, 4, dyVar.t);
            }
            if (dyVar.u != null) {
                ed.c.a(dpVar, 5, dyVar.u);
            }
            if (dyVar.v != null) {
                dx.c.a(dpVar, 6, dyVar.v);
            }
            if (dyVar.w != null) {
                ek.c.a(dpVar, 7, dyVar.w);
            }
            if (dyVar.x != null) {
                dn.d.a(dpVar, 8, dyVar.x);
            }
            if (dyVar.y != null) {
                dn.d.a(dpVar, 9, dyVar.y);
            }
            if (dyVar.z != null) {
                ea.c.a(dpVar, 10, dyVar.z);
            }
            if (dyVar.A != null) {
                eg.c.a(dpVar, 11, dyVar.A);
            }
            if (dyVar.B != null) {
                dn.p.a(dpVar, 12, dyVar.B);
            }
            if (dyVar.C != null) {
                dn.p.a(dpVar, 13, dyVar.C);
            }
            if (dyVar.D != null) {
                ef.c.a(dpVar, 18, dyVar.D);
            }
            if (dyVar.E != null) {
                dn.p.a(dpVar, 14, dyVar.E);
            }
            if (dyVar.F != null) {
                dn.p.a(dpVar, 15, dyVar.F);
            }
            if (dyVar.G != null) {
                dn.p.a(dpVar, 16, dyVar.G);
            }
            ec.c.a().a(dpVar, 17, dyVar.H);
            if (dyVar.I != null) {
                dn.p.a(dpVar, 22, dyVar.I);
            }
            if (dyVar.J != null) {
                dn.d.a(dpVar, 23, dyVar.J);
            }
            if (dyVar.K != null) {
                dn.i.a(dpVar, 24, dyVar.K);
            }
            if (dyVar.L != null) {
                dn.i.a(dpVar, 25, dyVar.L);
            }
            dpVar.a(dyVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, dy.class);
        }

        private static dy b(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            try {
                                aVar.c = (eb) eb.ADAPTER.a(doVar);
                                break;
                            } catch (com.tapjoy.internal.dn.a e) {
                                aVar.a(b, dk.VARINT, Long.valueOf((long) e.a));
                                break;
                            }
                        case TwitterResponse.READ_WRITE /*2*/:
                            aVar.d = (String) dn.p.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (Long) dn.i.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                            aVar.i = (Long) dn.i.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                            aVar.j = (ed) ed.c.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            aVar.k = (dx) dx.c.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            aVar.l = (ek) ek.c.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            aVar.m = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            aVar.n = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            aVar.o = (ea) ea.c.a(doVar);
                            break;
                        case R.styleable.Toolbar_popupTheme /*11*/:
                            aVar.p = (eg) eg.c.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleTextAppearance /*12*/:
                            aVar.q = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                            aVar.r = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMargin /*14*/:
                            aVar.t = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginStart /*15*/:
                            aVar.u = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            aVar.v = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMarginTop /*17*/:
                            aVar.w.add(ec.c.a(doVar));
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            aVar.s = (ef) ef.c.a(doVar);
                            break;
                        case R.styleable.Toolbar_titleMargins /*19*/:
                            aVar.f = (Long) dn.i.a(doVar);
                            break;
                        case R.styleable.Toolbar_maxButtonHeight /*20*/:
                            aVar.g = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_buttonGravity /*21*/:
                            aVar.h = (Long) dn.i.a(doVar);
                            break;
                        case R.styleable.Toolbar_collapseIcon /*22*/:
                            aVar.x = (String) dn.p.a(doVar);
                            break;
                        case R.styleable.Toolbar_collapseContentDescription /*23*/:
                            aVar.y = (Integer) dn.d.a(doVar);
                            break;
                        case R.styleable.Toolbar_navigationIcon /*24*/:
                            aVar.z = (Long) dn.i.a(doVar);
                            break;
                        case R.styleable.Toolbar_navigationContentDescription /*25*/:
                            aVar.A = (Long) dn.i.a(doVar);
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

    public dy(eb ebVar, String str, Long l, Long l2, String str2, Long l3, Long l4, ed edVar, dx dxVar, ek ekVar, Integer num, Integer num2, ea eaVar, eg egVar, String str3, String str4, ef efVar, String str5, String str6, String str7, List list, String str8, Integer num3, Long l5, Long l6, hu huVar) {
        super(c, huVar);
        this.n = ebVar;
        this.o = str;
        this.p = l;
        this.q = l2;
        this.r = str2;
        this.s = l3;
        this.t = l4;
        this.u = edVar;
        this.v = dxVar;
        this.w = ekVar;
        this.x = num;
        this.y = num2;
        this.z = eaVar;
        this.A = egVar;
        this.B = str3;
        this.C = str4;
        this.D = efVar;
        this.E = str5;
        this.F = str6;
        this.G = str7;
        this.H = ds.a("values", list);
        this.I = str8;
        this.J = num3;
        this.K = l5;
        this.L = l6;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dy)) {
            return false;
        }
        dy dyVar = (dy) obj;
        if (a().equals(dyVar.a()) && this.n.equals(dyVar.n) && this.o.equals(dyVar.o) && this.p.equals(dyVar.p) && ds.a(this.q, dyVar.q) && ds.a(this.r, dyVar.r) && ds.a(this.s, dyVar.s) && ds.a(this.t, dyVar.t) && ds.a(this.u, dyVar.u) && ds.a(this.v, dyVar.v) && ds.a(this.w, dyVar.w) && ds.a(this.x, dyVar.x) && ds.a(this.y, dyVar.y) && ds.a(this.z, dyVar.z) && ds.a(this.A, dyVar.A) && ds.a(this.B, dyVar.B) && ds.a(this.C, dyVar.C) && ds.a(this.D, dyVar.D) && ds.a(this.E, dyVar.E) && ds.a(this.F, dyVar.F) && ds.a(this.G, dyVar.G) && this.H.equals(dyVar.H) && ds.a(this.I, dyVar.I) && ds.a(this.J, dyVar.J) && ds.a(this.K, dyVar.K) && ds.a(this.L, dyVar.L)) {
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
        int hashCode = ((this.q != null ? this.q.hashCode() : 0) + (((((((a().hashCode() * 37) + this.n.hashCode()) * 37) + this.o.hashCode()) * 37) + this.p.hashCode()) * 37)) * 37;
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
        hashCode = (i2 + hashCode) * 37;
        if (this.w != null) {
            i2 = this.w.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.x != null) {
            i2 = this.x.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.y != null) {
            i2 = this.y.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.z != null) {
            i2 = this.z.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.A != null) {
            i2 = this.A.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.B != null) {
            i2 = this.B.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.C != null) {
            i2 = this.C.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.D != null) {
            i2 = this.D.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.E != null) {
            i2 = this.E.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.F != null) {
            i2 = this.F.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.G != null) {
            i2 = this.G.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (((i2 + hashCode) * 37) + this.H.hashCode()) * 37;
        if (this.I != null) {
            i2 = this.I.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.J != null) {
            i2 = this.J.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.K != null) {
            i2 = this.K.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.L != null) {
            i = this.L.hashCode();
        }
        i2 += i;
        this.b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", type=").append(this.n);
        stringBuilder.append(", name=").append(this.o);
        stringBuilder.append(", time=").append(this.p);
        if (this.q != null) {
            stringBuilder.append(", systemTime=").append(this.q);
        }
        if (this.r != null) {
            stringBuilder.append(", instanceId=").append(this.r);
        }
        if (this.s != null) {
            stringBuilder.append(", elapsedRealtime=").append(this.s);
        }
        if (this.t != null) {
            stringBuilder.append(", duration=").append(this.t);
        }
        if (this.u != null) {
            stringBuilder.append(", info=").append(this.u);
        }
        if (this.v != null) {
            stringBuilder.append(", app=").append(this.v);
        }
        if (this.w != null) {
            stringBuilder.append(", user=").append(this.w);
        }
        if (this.x != null) {
            stringBuilder.append(", xxx_session_seq=").append(this.x);
        }
        if (this.y != null) {
            stringBuilder.append(", eventSeq=").append(this.y);
        }
        if (this.z != null) {
            stringBuilder.append(", eventPrev=").append(this.z);
        }
        if (this.A != null) {
            stringBuilder.append(", purchase=").append(this.A);
        }
        if (this.B != null) {
            stringBuilder.append(", exception=").append(this.B);
        }
        if (this.C != null) {
            stringBuilder.append(", metaBase=").append(this.C);
        }
        if (this.D != null) {
            stringBuilder.append(", meta=").append(this.D);
        }
        if (this.E != null) {
            stringBuilder.append(", category=").append(this.E);
        }
        if (this.F != null) {
            stringBuilder.append(", p1=").append(this.F);
        }
        if (this.G != null) {
            stringBuilder.append(", p2=").append(this.G);
        }
        if (!this.H.isEmpty()) {
            stringBuilder.append(", values=").append(this.H);
        }
        if (this.I != null) {
            stringBuilder.append(", dimensions=").append(this.I);
        }
        if (this.J != null) {
            stringBuilder.append(", count=").append(this.J);
        }
        if (this.K != null) {
            stringBuilder.append(", firstTime=").append(this.K);
        }
        if (this.L != null) {
            stringBuilder.append(", lastTime=").append(this.L);
        }
        return stringBuilder.replace(0, 2, "Event{").append('}').toString();
    }
}
