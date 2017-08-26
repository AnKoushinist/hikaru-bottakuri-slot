package com.tapjoy.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public final class gd {

    public interface b extends dv {
    }

    public static final class a extends do implements b {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new a(dlVar);
            }
        };
        private static final a d;
        public int c;
        private final dk e;
        private int f;
        private Object g;
        private Object h;
        private Object i;
        private Object j;
        private byte k;
        private int l;

        public static final class a extends com.tapjoy.internal.do.a implements b {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            public Object d = BuildConfig.FLAVOR;
            public Object e = BuildConfig.FLAVOR;
            public Object f = BuildConfig.FLAVOR;
            private int g;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return h();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return h();
            }

            public final /* synthetic */ Object clone() {
                return h();
            }

            private a() {
            }

            public final a d() {
                int i = 1;
                a aVar = new a((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                aVar.g = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                aVar.c = this.g;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                aVar.h = this.d;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                aVar.i = this.e;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                aVar.j = this.f;
                aVar.f = i;
                return aVar;
            }

            public final a a(a aVar) {
                if (aVar != a.d()) {
                    if (aVar.e()) {
                        this.b |= 1;
                        this.c = aVar.g;
                    }
                    if (aVar.g()) {
                        a(aVar.c);
                    }
                    if (aVar.h()) {
                        this.b |= 4;
                        this.d = aVar.h;
                    }
                    if (aVar.j()) {
                        this.b |= 8;
                        this.e = aVar.i;
                    }
                    if (aVar.l()) {
                        this.b |= 16;
                        this.f = aVar.j;
                    }
                    this.a = this.a.a(aVar.e);
                }
                return this;
            }

            public final boolean c() {
                return true;
            }

            public final a a(int i) {
                this.b |= 2;
                this.g = i;
                return this;
            }

            public final boolean e() {
                return (this.b & 4) == 4;
            }

            public final a a(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 4;
                this.d = str;
                return this;
            }

            public final a f() {
                this.b &= -5;
                this.d = a.d().i();
                return this;
            }

            private a h() {
                return new a().a(d());
            }
        }

        private a(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.k = (byte) -1;
            this.l = -1;
            this.e = aVar.a;
        }

        private a() {
            this.k = (byte) -1;
            this.l = -1;
            this.e = dk.b;
        }

        public static a d() {
            return d;
        }

        private a(dl dlVar) {
            this.k = (byte) -1;
            this.l = -1;
            t();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            d = dlVar.d();
                            this.f |= 1;
                            this.g = d;
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            this.f |= 2;
                            this.c = dlVar.e();
                            break;
                        case R.styleable.Toolbar_logoDescription /*26*/:
                            d = dlVar.d();
                            this.f |= 4;
                            this.h = d;
                            break;
                        case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                            d = dlVar.d();
                            this.f |= 8;
                            this.i = d;
                            break;
                        case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                            d = dlVar.d();
                            this.f |= 16;
                            this.j = d;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.e = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.e = g.a();
            }
        }

        static {
            a aVar = new a();
            d = aVar;
            aVar.t();
        }

        public final boolean e() {
            return (this.f & 1) == 1;
        }

        public final String f() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.g = e;
            }
            return e;
        }

        private dk p() {
            Object obj = this.g;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.g = a;
            return a;
        }

        public final boolean g() {
            return (this.f & 2) == 2;
        }

        public final boolean h() {
            return (this.f & 4) == 4;
        }

        public final String i() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.h = e;
            }
            return e;
        }

        private dk q() {
            Object obj = this.h;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.h = a;
            return a;
        }

        public final boolean j() {
            return (this.f & 8) == 8;
        }

        public final String k() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.i = e;
            }
            return e;
        }

        private dk r() {
            Object obj = this.i;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.i = a;
            return a;
        }

        public final boolean l() {
            return (this.f & 16) == 16;
        }

        public final String m() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.j = e;
            }
            return e;
        }

        private dk s() {
            Object obj = this.j;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.j = a;
            return a;
        }

        private void t() {
            this.g = BuildConfig.FLAVOR;
            this.c = 0;
            this.h = BuildConfig.FLAVOR;
            this.i = BuildConfig.FLAVOR;
            this.j = BuildConfig.FLAVOR;
        }

        public final boolean c() {
            byte b = this.k;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.k = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            if ((this.f & 1) == 1) {
                dmVar.a(1, p());
            }
            if ((this.f & 2) == 2) {
                dmVar.a(2, this.c);
            }
            if ((this.f & 4) == 4) {
                dmVar.a(3, q());
            }
            if ((this.f & 8) == 8) {
                dmVar.a(4, r());
            }
            if ((this.f & 16) == 16) {
                dmVar.a(5, s());
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = this.l;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.f & 1) == 1) {
                i = dm.b(1, p()) + 0;
            }
            if ((this.f & 2) == 2) {
                i += dm.b(2, this.c);
            }
            if ((this.f & 4) == 4) {
                i += dm.b(3, q());
            }
            if ((this.f & 8) == 8) {
                i += dm.b(4, r());
            }
            if ((this.f & 16) == 16) {
                i += dm.b(5, s());
            }
            i += this.e.a();
            this.l = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return super.equals(obj);
            }
            a aVar = (a) obj;
            boolean z = e() == aVar.e();
            if (e()) {
                z = z && f().equals(aVar.f());
            }
            if (z && g() == aVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (g()) {
                if (z && this.c == aVar.c) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == aVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (h()) {
                if (z && i().equals(aVar.i())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && j() == aVar.j()) {
                z = true;
            } else {
                z = false;
            }
            if (j()) {
                if (z && k().equals(aVar.k())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && l() == aVar.l()) {
                z = true;
            } else {
                z = false;
            }
            if (!l()) {
                return z;
            }
            if (z && m().equals(aVar.m())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = a.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.c;
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 3) * 53) + i().hashCode();
            }
            if (j()) {
                hashCode = (((hashCode * 37) + 4) * 53) + k().hashCode();
            }
            if (l()) {
                hashCode = (((hashCode * 37) + 5) * 53) + m().hashCode();
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a n() {
            return new a();
        }

        public static a a(a aVar) {
            return new a().a(aVar);
        }

        public final a o() {
            return new a().a(this);
        }
    }

    public interface aa extends dv {
    }

    public interface h extends dv {
    }

    public static final class c extends do implements h {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new c(dlVar, dnVar);
            }
        };
        private static final c q;
        private byte A;
        private int B;
        public i c;
        public long d;
        long e;
        long f;
        long g;
        l h;
        a i;
        public z j;
        int k;
        int l;
        f m;
        r n;
        p o;
        List p;
        private final dk r;
        private int s;
        private Object t;
        private Object u;
        private Object v;
        private Object w;
        private Object x;
        private Object y;
        private Object z;

        public static final class a extends com.tapjoy.internal.do.a implements h {
            public int b;
            public i c = i.APP;
            public Object d = BuildConfig.FLAVOR;
            public long e;
            public Object f = BuildConfig.FLAVOR;
            public l g = l.d();
            public a h = a.d();
            public z i = z.d();
            public f j = f.d();
            public r k = r.d();
            public p l = p.d();
            public Object m = BuildConfig.FLAVOR;
            public Object n = BuildConfig.FLAVOR;
            public Object o = BuildConfig.FLAVOR;
            public List p = Collections.emptyList();
            private long q;
            private long r;
            private long s;
            private int t;
            private int u;
            private Object v = BuildConfig.FLAVOR;
            private Object w = BuildConfig.FLAVOR;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return g();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return g();
            }

            public final /* synthetic */ Object clone() {
                return g();
            }

            private a() {
            }

            public final c d() {
                c cVar = new c((com.tapjoy.internal.do.a) this);
                int i = this.b;
                int i2 = 0;
                if ((i & 1) == 1) {
                    i2 = 1;
                }
                cVar.c = this.c;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                cVar.t = this.d;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                cVar.d = this.e;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                cVar.e = this.q;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                cVar.u = this.f;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                cVar.f = this.r;
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                cVar.g = this.s;
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                cVar.h = this.g;
                if ((i & 256) == 256) {
                    i2 |= 256;
                }
                cVar.i = this.h;
                if ((i & 512) == 512) {
                    i2 |= 512;
                }
                cVar.j = this.i;
                if ((i & 1024) == 1024) {
                    i2 |= 1024;
                }
                cVar.k = this.t;
                if ((i & 2048) == 2048) {
                    i2 |= 2048;
                }
                cVar.l = this.u;
                if ((i & 4096) == 4096) {
                    i2 |= 4096;
                }
                cVar.m = this.j;
                if ((i & 8192) == 8192) {
                    i2 |= 8192;
                }
                cVar.n = this.k;
                if ((i & 16384) == 16384) {
                    i2 |= 16384;
                }
                cVar.v = this.v;
                if ((i & 32768) == 32768) {
                    i2 |= 32768;
                }
                cVar.w = this.w;
                if ((i & 65536) == 65536) {
                    i2 |= 65536;
                }
                cVar.o = this.l;
                if ((i & 131072) == 131072) {
                    i2 |= 131072;
                }
                cVar.x = this.m;
                if ((i & 262144) == 262144) {
                    i2 |= 262144;
                }
                cVar.y = this.n;
                if ((i & 524288) == 524288) {
                    i2 |= 524288;
                }
                cVar.z = this.o;
                if ((this.b & 1048576) == 1048576) {
                    this.p = Collections.unmodifiableList(this.p);
                    this.b &= -1048577;
                }
                cVar.p = this.p;
                cVar.s = i2;
                return cVar;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 2) == 2) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 4) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 512) == 512) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && !this.i.c()) {
                    return false;
                }
                if ((this.b & 4096) == 4096) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && !this.j.c()) {
                    return false;
                }
                if ((this.b & 8192) == 8192) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && !this.k.c()) {
                    return false;
                }
                for (int i = 0; i < this.p.size(); i++) {
                    if (!((j) this.p.get(i)).c()) {
                        return false;
                    }
                }
                return true;
            }

            public final a a(i iVar) {
                if (iVar == null) {
                    throw new NullPointerException();
                }
                this.b |= 1;
                this.c = iVar;
                return this;
            }

            public final a a(long j) {
                this.b |= 4;
                this.e = j;
                return this;
            }

            public final a b(long j) {
                this.b |= 8;
                this.q = j;
                return this;
            }

            public final a c(long j) {
                this.b |= 32;
                this.r = j;
                return this;
            }

            public final a d(long j) {
                this.b |= 64;
                this.s = j;
                return this;
            }

            public final a a(int i) {
                this.b |= 2048;
                this.u = i;
                return this;
            }

            public final a a(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 32768;
                this.w = str;
                return this;
            }

            public final a a(a aVar) {
                this.l = aVar.d();
                this.b |= 65536;
                return this;
            }

            public final void e() {
                if ((this.b & 1048576) != 1048576) {
                    this.p = new ArrayList(this.p);
                    this.b |= 1048576;
                }
            }

            private a g() {
                com.tapjoy.internal.do.a aVar = new a();
                c d = d();
                if (d != c.d()) {
                    if (d.e()) {
                        aVar.a(d.c);
                    }
                    if (d.f()) {
                        aVar.b |= 2;
                        aVar.d = d.t;
                    }
                    if (d.h()) {
                        aVar.a(d.d);
                    }
                    if (d.i()) {
                        aVar.b(d.e);
                    }
                    if (d.j()) {
                        aVar.b |= 16;
                        aVar.f = d.u;
                    }
                    if (d.l()) {
                        aVar.c(d.f);
                    }
                    if (d.m()) {
                        aVar.d(d.g);
                    }
                    if (d.n()) {
                        l lVar = d.h;
                        if ((aVar.b & 128) != 128 || aVar.g == l.d()) {
                            aVar.g = lVar;
                        } else {
                            aVar.g = l.a(aVar.g).a(lVar).d();
                        }
                        aVar.b |= 128;
                    }
                    if (d.o()) {
                        a aVar2 = d.i;
                        if ((aVar.b & 256) != 256 || aVar.h == a.d()) {
                            aVar.h = aVar2;
                        } else {
                            aVar.h = a.a(aVar.h).a(aVar2).d();
                        }
                        aVar.b |= 256;
                    }
                    if (d.p()) {
                        z zVar = d.j;
                        if ((aVar.b & 512) != 512 || aVar.i == z.d()) {
                            aVar.i = zVar;
                        } else {
                            aVar.i = z.a(aVar.i).a(zVar).d();
                        }
                        aVar.b |= 512;
                    }
                    if (d.q()) {
                        int i = d.k;
                        aVar.b |= 1024;
                        aVar.t = i;
                    }
                    if (d.r()) {
                        aVar.a(d.l);
                    }
                    if (d.s()) {
                        f fVar = d.m;
                        if ((aVar.b & 4096) != 4096 || aVar.j == f.d()) {
                            aVar.j = fVar;
                        } else {
                            aVar.j = f.a(aVar.j).a(fVar).d();
                        }
                        aVar.b |= 4096;
                    }
                    if (d.t()) {
                        r rVar = d.n;
                        if ((aVar.b & 8192) != 8192 || aVar.k == r.d()) {
                            aVar.k = rVar;
                        } else {
                            aVar.k = r.a(aVar.k).a(rVar).d();
                        }
                        aVar.b |= 8192;
                    }
                    if (d.u()) {
                        aVar.b |= 16384;
                        aVar.v = d.v;
                    }
                    if (d.w()) {
                        aVar.b |= 32768;
                        aVar.w = d.w;
                    }
                    if (d.y()) {
                        p pVar = d.o;
                        if ((aVar.b & 65536) != 65536 || aVar.l == p.d()) {
                            aVar.l = pVar;
                        } else {
                            aVar.l = p.a(aVar.l).a(pVar).e();
                        }
                        aVar.b |= 65536;
                    }
                    if (d.z()) {
                        aVar.b |= 131072;
                        aVar.m = d.x;
                    }
                    if (d.B()) {
                        aVar.b |= 262144;
                        aVar.n = d.y;
                    }
                    if (d.D()) {
                        aVar.b |= 524288;
                        aVar.o = d.z;
                    }
                    if (!d.p.isEmpty()) {
                        if (aVar.p.isEmpty()) {
                            aVar.p = d.p;
                            aVar.b &= -1048577;
                        } else {
                            aVar.e();
                            aVar.p.addAll(d.p);
                        }
                    }
                    aVar.a = aVar.a.a(d.r);
                }
                return aVar;
            }
        }

        private c(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.A = (byte) -1;
            this.B = -1;
            this.r = aVar.a;
        }

        private c() {
            this.A = (byte) -1;
            this.B = -1;
            this.r = dk.b;
        }

        public static c d() {
            return q;
        }

        private c(dl dlVar, dn dnVar) {
            this.A = (byte) -1;
            this.B = -1;
            O();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            int i = 0;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                            int e = dlVar.e();
                            i a3 = i.a(e);
                            if (a3 != null) {
                                this.s |= 1;
                                this.c = a3;
                                break;
                            }
                            a.e(a2);
                            a.e(e);
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            d = dlVar.d();
                            this.s |= 2;
                            this.t = d;
                            break;
                        case R.styleable.Toolbar_navigationIcon /*24*/:
                            this.s |= 4;
                            this.d = dlVar.f();
                            break;
                        case R.styleable.AppCompatTheme_actionModeCutDrawable /*32*/:
                            this.s |= 64;
                            this.g = dlVar.f();
                            break;
                        case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                            a a4;
                            if ((this.s & 128) == 128) {
                                a4 = l.a(this.h);
                            } else {
                                a4 = null;
                            }
                            this.h = (l) dlVar.a(l.b, dnVar);
                            if (a4 != null) {
                                a4.a(this.h);
                                this.h = a4.d();
                            }
                            this.s |= 128;
                            break;
                        case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                            a o;
                            if ((this.s & 256) == 256) {
                                o = this.i.o();
                            } else {
                                o = null;
                            }
                            this.i = (a) dlVar.a(a.b, dnVar);
                            if (o != null) {
                                o.a(this.i);
                                this.i = o.d();
                            }
                            this.s |= 256;
                            break;
                        case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                            a a5;
                            if ((this.s & 512) == 512) {
                                a5 = z.a(this.j);
                            } else {
                                a5 = null;
                            }
                            this.j = (z) dlVar.a(z.b, dnVar);
                            if (a5 != null) {
                                a5.a(this.j);
                                this.j = a5.d();
                            }
                            this.s |= 512;
                            break;
                        case R.styleable.AppCompatTheme_editTextBackground /*64*/:
                            this.s |= 1024;
                            this.k = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemHeightLarge /*72*/:
                            this.s |= 2048;
                            this.l = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator /*82*/:
                            a a6;
                            if ((this.s & 4096) == 4096) {
                                a6 = f.a(this.m);
                            } else {
                                a6 = null;
                            }
                            this.m = (f) dlVar.a(f.b, dnVar);
                            if (a6 != null) {
                                a6.a(this.m);
                                this.m = a6.d();
                            }
                            this.s |= 4096;
                            break;
                        case R.styleable.AppCompatTheme_colorSwitchThumbNormal /*90*/:
                            a a7;
                            if ((this.s & 8192) == 8192) {
                                a7 = r.a(this.n);
                            } else {
                                a7 = null;
                            }
                            this.n = (r) dlVar.a(r.b, dnVar);
                            if (a7 != null) {
                                a7.a(this.n);
                                this.n = a7.d();
                            }
                            this.s |= 8192;
                            break;
                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                            d = dlVar.d();
                            this.s |= 16384;
                            this.v = d;
                            break;
                        case R.styleable.AppCompatTheme_editTextStyle /*106*/:
                            d = dlVar.d();
                            this.s |= 32768;
                            this.w = d;
                            break;
                        case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                            d = dlVar.d();
                            this.s |= 131072;
                            this.x = d;
                            break;
                        case 122:
                            d = dlVar.d();
                            this.s |= 262144;
                            this.y = d;
                            break;
                        case 130:
                            d = dlVar.d();
                            this.s |= 524288;
                            this.z = d;
                            break;
                        case 138:
                            if ((i & 1048576) != 1048576) {
                                this.p = new ArrayList();
                                i |= 1048576;
                            }
                            this.p.add(dlVar.a(j.b, dnVar));
                            break;
                        case 146:
                            a a8;
                            if ((this.s & 65536) == 65536) {
                                a8 = p.a(this.o);
                            } else {
                                a8 = null;
                            }
                            this.o = (p) dlVar.a(p.b, dnVar);
                            if (a8 != null) {
                                a8.a(this.o);
                                this.o = a8.e();
                            }
                            this.s |= 65536;
                            break;
                        case 152:
                            this.s |= 8;
                            this.e = dlVar.b();
                            break;
                        case 162:
                            d = dlVar.d();
                            this.s |= 16;
                            this.u = d;
                            break;
                        case 168:
                            this.s |= 32;
                            this.f = dlVar.b();
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e2) {
                    e2.a = this;
                    throw e2;
                } catch (IOException e3) {
                    dq dqVar = new dq(e3.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    if ((i & 1048576) == 1048576) {
                        this.p = Collections.unmodifiableList(this.p);
                    }
                    try {
                        a.a();
                    } catch (IOException e4) {
                    } finally {
                        this.r = g.a();
                    }
                }
            }
            if ((i & 1048576) == 1048576) {
                this.p = Collections.unmodifiableList(this.p);
            }
            try {
                a.a();
            } catch (IOException e5) {
            } finally {
                this.r = g.a();
            }
        }

        static {
            c cVar = new c();
            q = cVar;
            cVar.O();
        }

        public final boolean e() {
            return (this.s & 1) == 1;
        }

        public final boolean f() {
            return (this.s & 2) == 2;
        }

        public final String g() {
            Object obj = this.t;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.t = e;
            }
            return e;
        }

        private dk H() {
            Object obj = this.t;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.t = a;
            return a;
        }

        public final boolean h() {
            return (this.s & 4) == 4;
        }

        public final boolean i() {
            return (this.s & 8) == 8;
        }

        public final boolean j() {
            return (this.s & 16) == 16;
        }

        public final String k() {
            Object obj = this.u;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.u = e;
            }
            return e;
        }

        private dk I() {
            Object obj = this.u;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.u = a;
            return a;
        }

        public final boolean l() {
            return (this.s & 32) == 32;
        }

        public final boolean m() {
            return (this.s & 64) == 64;
        }

        public final boolean n() {
            return (this.s & 128) == 128;
        }

        public final boolean o() {
            return (this.s & 256) == 256;
        }

        public final boolean p() {
            return (this.s & 512) == 512;
        }

        public final boolean q() {
            return (this.s & 1024) == 1024;
        }

        public final boolean r() {
            return (this.s & 2048) == 2048;
        }

        public final boolean s() {
            return (this.s & 4096) == 4096;
        }

        public final boolean t() {
            return (this.s & 8192) == 8192;
        }

        public final boolean u() {
            return (this.s & 16384) == 16384;
        }

        public final String v() {
            Object obj = this.v;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.v = e;
            }
            return e;
        }

        private dk J() {
            Object obj = this.v;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.v = a;
            return a;
        }

        public final boolean w() {
            return (this.s & 32768) == 32768;
        }

        public final String x() {
            Object obj = this.w;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.w = e;
            }
            return e;
        }

        private dk K() {
            Object obj = this.w;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.w = a;
            return a;
        }

        public final boolean y() {
            return (this.s & 65536) == 65536;
        }

        public final boolean z() {
            return (this.s & 131072) == 131072;
        }

        public final String A() {
            Object obj = this.x;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.x = e;
            }
            return e;
        }

        private dk L() {
            Object obj = this.x;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.x = a;
            return a;
        }

        public final boolean B() {
            return (this.s & 262144) == 262144;
        }

        public final String C() {
            Object obj = this.y;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.y = e;
            }
            return e;
        }

        private dk M() {
            Object obj = this.y;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.y = a;
            return a;
        }

        public final boolean D() {
            return (this.s & 524288) == 524288;
        }

        public final String E() {
            Object obj = this.z;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.z = e;
            }
            return e;
        }

        private dk N() {
            Object obj = this.z;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.z = a;
            return a;
        }

        public final int F() {
            return this.p.size();
        }

        private void O() {
            this.c = i.APP;
            this.t = BuildConfig.FLAVOR;
            this.d = 0;
            this.e = 0;
            this.u = BuildConfig.FLAVOR;
            this.f = 0;
            this.g = 0;
            this.h = l.d();
            this.i = a.d();
            this.j = z.d();
            this.k = 0;
            this.l = 0;
            this.m = f.d();
            this.n = r.d();
            this.v = BuildConfig.FLAVOR;
            this.w = BuildConfig.FLAVOR;
            this.o = p.d();
            this.x = BuildConfig.FLAVOR;
            this.y = BuildConfig.FLAVOR;
            this.z = BuildConfig.FLAVOR;
            this.p = Collections.emptyList();
        }

        public final boolean c() {
            byte b = this.A;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!e()) {
                this.A = (byte) 0;
                return false;
            } else if (!f()) {
                this.A = (byte) 0;
                return false;
            } else if (!h()) {
                this.A = (byte) 0;
                return false;
            } else if (p() && !this.j.c()) {
                this.A = (byte) 0;
                return false;
            } else if (s() && !this.m.c()) {
                this.A = (byte) 0;
                return false;
            } else if (!t() || this.n.c()) {
                int i = 0;
                while (i < F()) {
                    if (((j) this.p.get(i)).c()) {
                        i++;
                    } else {
                        this.A = (byte) 0;
                        return false;
                    }
                }
                this.A = (byte) 1;
                return true;
            } else {
                this.A = (byte) 0;
                return false;
            }
        }

        public final void a(dm dmVar) {
            b();
            if ((this.s & 1) == 1) {
                dmVar.a(this.c.d);
            }
            if ((this.s & 2) == 2) {
                dmVar.a(2, H());
            }
            if ((this.s & 4) == 4) {
                dmVar.a(3, this.d);
            }
            if ((this.s & 64) == 64) {
                dmVar.a(4, this.g);
            }
            if ((this.s & 128) == 128) {
                dmVar.a(5, this.h);
            }
            if ((this.s & 256) == 256) {
                dmVar.a(6, this.i);
            }
            if ((this.s & 512) == 512) {
                dmVar.a(7, this.j);
            }
            if ((this.s & 1024) == 1024) {
                dmVar.a(8, this.k);
            }
            if ((this.s & 2048) == 2048) {
                dmVar.a(9, this.l);
            }
            if ((this.s & 4096) == 4096) {
                dmVar.a(10, this.m);
            }
            if ((this.s & 8192) == 8192) {
                dmVar.a(11, this.n);
            }
            if ((this.s & 16384) == 16384) {
                dmVar.a(12, J());
            }
            if ((this.s & 32768) == 32768) {
                dmVar.a(13, K());
            }
            if ((this.s & 131072) == 131072) {
                dmVar.a(14, L());
            }
            if ((this.s & 262144) == 262144) {
                dmVar.a(15, M());
            }
            if ((this.s & 524288) == 524288) {
                dmVar.a(16, N());
            }
            for (int i = 0; i < this.p.size(); i++) {
                dmVar.a(17, (du) this.p.get(i));
            }
            if ((this.s & 65536) == 65536) {
                dmVar.a(18, this.o);
            }
            if ((this.s & 8) == 8) {
                dmVar.a(19, this.e);
            }
            if ((this.s & 16) == 16) {
                dmVar.a(20, I());
            }
            if ((this.s & 32) == 32) {
                dmVar.a(21, this.f);
            }
            dmVar.c(this.r);
        }

        public final int b() {
            int i = 0;
            int i2 = this.B;
            if (i2 != -1) {
                return i2;
            }
            if ((this.s & 1) == 1) {
                i2 = dm.b(this.c.d) + 0;
            } else {
                i2 = 0;
            }
            if ((this.s & 2) == 2) {
                i2 += dm.b(2, H());
            }
            if ((this.s & 4) == 4) {
                i2 += dm.b(3, this.d);
            }
            if ((this.s & 64) == 64) {
                i2 += dm.b(4, this.g);
            }
            if ((this.s & 128) == 128) {
                i2 += dm.b(5, this.h);
            }
            if ((this.s & 256) == 256) {
                i2 += dm.b(6, this.i);
            }
            if ((this.s & 512) == 512) {
                i2 += dm.b(7, this.j);
            }
            if ((this.s & 1024) == 1024) {
                i2 += dm.b(8, this.k);
            }
            if ((this.s & 2048) == 2048) {
                i2 += dm.b(9, this.l);
            }
            if ((this.s & 4096) == 4096) {
                i2 += dm.b(10, this.m);
            }
            if ((this.s & 8192) == 8192) {
                i2 += dm.b(11, this.n);
            }
            if ((this.s & 16384) == 16384) {
                i2 += dm.b(12, J());
            }
            if ((this.s & 32768) == 32768) {
                i2 += dm.b(13, K());
            }
            if ((this.s & 131072) == 131072) {
                i2 += dm.b(14, L());
            }
            if ((this.s & 262144) == 262144) {
                i2 += dm.b(15, M());
            }
            if ((this.s & 524288) == 524288) {
                i2 += dm.b(16, N());
            }
            int i3 = i2;
            while (i < this.p.size()) {
                i++;
                i3 = dm.b(17, (du) this.p.get(i)) + i3;
            }
            if ((this.s & 65536) == 65536) {
                i3 += dm.b(18, this.o);
            }
            if ((this.s & 8) == 8) {
                i3 += dm.b(19, this.e);
            }
            if ((this.s & 16) == 16) {
                i3 += dm.b(20, I());
            }
            if ((this.s & 32) == 32) {
                i3 += dm.b(21, this.f);
            }
            i2 = this.r.a() + i3;
            this.B = i2;
            return i2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return super.equals(obj);
            }
            c cVar = (c) obj;
            boolean z = e() == cVar.e();
            if (e()) {
                z = z && this.c == cVar.c;
            }
            if (z && f() == cVar.f()) {
                z = true;
            } else {
                z = false;
            }
            if (f()) {
                if (z && g().equals(cVar.g())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == cVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (h()) {
                if (z && this.d == cVar.d) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && i() == cVar.i()) {
                z = true;
            } else {
                z = false;
            }
            if (i()) {
                if (z && this.e == cVar.e) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && j() == cVar.j()) {
                z = true;
            } else {
                z = false;
            }
            if (j()) {
                if (z && k().equals(cVar.k())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && l() == cVar.l()) {
                z = true;
            } else {
                z = false;
            }
            if (l()) {
                if (z && this.f == cVar.f) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && m() == cVar.m()) {
                z = true;
            } else {
                z = false;
            }
            if (m()) {
                if (z && this.g == cVar.g) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && n() == cVar.n()) {
                z = true;
            } else {
                z = false;
            }
            if (n()) {
                if (z && this.h.equals(cVar.h)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && o() == cVar.o()) {
                z = true;
            } else {
                z = false;
            }
            if (o()) {
                if (z && this.i.equals(cVar.i)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && p() == cVar.p()) {
                z = true;
            } else {
                z = false;
            }
            if (p()) {
                if (z && this.j.equals(cVar.j)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && q() == cVar.q()) {
                z = true;
            } else {
                z = false;
            }
            if (q()) {
                if (z && this.k == cVar.k) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && r() == cVar.r()) {
                z = true;
            } else {
                z = false;
            }
            if (r()) {
                if (z && this.l == cVar.l) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && s() == cVar.s()) {
                z = true;
            } else {
                z = false;
            }
            if (s()) {
                if (z && this.m.equals(cVar.m)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && t() == cVar.t()) {
                z = true;
            } else {
                z = false;
            }
            if (t()) {
                if (z && this.n.equals(cVar.n)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && u() == cVar.u()) {
                z = true;
            } else {
                z = false;
            }
            if (u()) {
                if (z && v().equals(cVar.v())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && w() == cVar.w()) {
                z = true;
            } else {
                z = false;
            }
            if (w()) {
                if (z && x().equals(cVar.x())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && y() == cVar.y()) {
                z = true;
            } else {
                z = false;
            }
            if (y()) {
                if (z && this.o.equals(cVar.o)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && z() == cVar.z()) {
                z = true;
            } else {
                z = false;
            }
            if (z()) {
                if (z && A().equals(cVar.A())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && B() == cVar.B()) {
                z = true;
            } else {
                z = false;
            }
            if (B()) {
                if (z && C().equals(cVar.C())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && D() == cVar.D()) {
                z = true;
            } else {
                z = false;
            }
            if (D()) {
                if (z && E().equals(cVar.E())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.p.equals(cVar.p)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = c.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + dp.a(this.c);
            }
            if (f()) {
                hashCode = (((hashCode * 37) + 2) * 53) + g().hashCode();
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 3) * 53) + dp.a(this.d);
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 19) * 53) + dp.a(this.e);
            }
            if (j()) {
                hashCode = (((hashCode * 37) + 20) * 53) + k().hashCode();
            }
            if (l()) {
                hashCode = (((hashCode * 37) + 21) * 53) + dp.a(this.f);
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 4) * 53) + dp.a(this.g);
            }
            if (n()) {
                hashCode = (((hashCode * 37) + 5) * 53) + this.h.hashCode();
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 6) * 53) + this.i.hashCode();
            }
            if (p()) {
                hashCode = (((hashCode * 37) + 7) * 53) + this.j.hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 8) * 53) + this.k;
            }
            if (r()) {
                hashCode = (((hashCode * 37) + 9) * 53) + this.l;
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 10) * 53) + this.m.hashCode();
            }
            if (t()) {
                hashCode = (((hashCode * 37) + 11) * 53) + this.n.hashCode();
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 12) * 53) + v().hashCode();
            }
            if (w()) {
                hashCode = (((hashCode * 37) + 13) * 53) + x().hashCode();
            }
            if (y()) {
                hashCode = (((hashCode * 37) + 18) * 53) + this.o.hashCode();
            }
            if (z()) {
                hashCode = (((hashCode * 37) + 14) * 53) + A().hashCode();
            }
            if (B()) {
                hashCode = (((hashCode * 37) + 15) * 53) + C().hashCode();
            }
            if (D()) {
                hashCode = (((hashCode * 37) + 16) * 53) + E().hashCode();
            }
            if (F() > 0) {
                hashCode = (((hashCode * 37) + 17) * 53) + this.p.hashCode();
            }
            hashCode = (hashCode * 29) + this.r.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a G() {
            return new a();
        }
    }

    public interface e extends dv {
    }

    public static final class d extends do implements e {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new d(dlVar, dnVar);
            }
        };
        private static final d d;
        List c;
        private final dk e;
        private byte f;
        private int g;

        public static final class a extends com.tapjoy.internal.do.a implements e {
            public List b = Collections.emptyList();
            private int c;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return h();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return h();
            }

            public final /* synthetic */ Object clone() {
                return h();
            }

            private a() {
            }

            public final d d() {
                d i = i();
                if (i.c()) {
                    return i;
                }
                throw new dz();
            }

            private d i() {
                d dVar = new d((com.tapjoy.internal.do.a) this);
                if ((this.c & 1) == 1) {
                    this.b = Collections.unmodifiableList(this.b);
                    this.c &= -2;
                }
                dVar.c = this.b;
                return dVar;
            }

            public final boolean c() {
                for (int i = 0; i < f(); i++) {
                    if (!((c) this.b.get(i)).c()) {
                        return false;
                    }
                }
                return true;
            }

            public final void e() {
                if ((this.c & 1) != 1) {
                    this.b = new ArrayList(this.b);
                    this.c |= 1;
                }
            }

            public final int f() {
                return this.b.size();
            }

            private a h() {
                com.tapjoy.internal.do.a aVar = new a();
                d i = i();
                if (i != d.d()) {
                    if (!i.c.isEmpty()) {
                        if (aVar.b.isEmpty()) {
                            aVar.b = i.c;
                            aVar.c &= -2;
                        } else {
                            aVar.e();
                            aVar.b.addAll(i.c);
                        }
                    }
                    aVar.a = aVar.a.a(i.e);
                }
                return aVar;
            }
        }

        private d(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.f = (byte) -1;
            this.g = -1;
            this.e = aVar.a;
        }

        private d() {
            this.f = (byte) -1;
            this.g = -1;
            this.e = dk.b;
        }

        public static d d() {
            return d;
        }

        private d(dl dlVar, dn dnVar) {
            Throwable th;
            int i = 0;
            this.f = (byte) -1;
            this.g = -1;
            this.c = Collections.emptyList();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            i2 = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            if ((i & 1) != 1) {
                                this.c = new ArrayList();
                                i |= 1;
                            }
                            this.c.add(dlVar.a(c.b, dnVar));
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    dq dqVar = e;
                    i2 = i;
                    dq dqVar2 = dqVar;
                    dqVar2.a = this;
                    throw dqVar2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    i2 = i;
                    dq dqVar3 = new dq(iOException.getMessage());
                    dqVar3.a = this;
                    throw dqVar3;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if ((i & 1) == 1) {
                this.c = Collections.unmodifiableList(this.c);
            }
            try {
                a.a();
            } catch (IOException e3) {
            } finally {
                this.e = g.a();
            }
            return;
            if ((i2 & 1) == 1) {
                this.c = Collections.unmodifiableList(this.c);
            }
            try {
                a.a();
            } catch (IOException e4) {
                throw th;
            } finally {
                this.e = g.a();
            }
            throw th;
        }

        static {
            d dVar = new d();
            d = dVar;
            dVar.c = Collections.emptyList();
        }

        private int f() {
            return this.c.size();
        }

        public final boolean c() {
            byte b = this.f;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < f()) {
                if (((c) this.c.get(i)).c()) {
                    i++;
                } else {
                    this.f = (byte) 0;
                    return false;
                }
            }
            this.f = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            for (int i = 0; i < this.c.size(); i++) {
                dmVar.a(1, (du) this.c.get(i));
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = this.g;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (i = 0; i < this.c.size(); i++) {
                i2 += dm.b(1, (du) this.c.get(i));
            }
            int a = this.e.a() + i2;
            this.g = a;
            return a;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof d)) {
                return super.equals(obj);
            }
            if (this.c.equals(((d) obj).c)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = d.class.hashCode() + 779;
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.c.hashCode();
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a e() {
            return new a();
        }
    }

    public interface g extends dv {
    }

    public static final class f extends do implements g {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new f(dlVar);
            }
        };
        private static final f d;
        i c;
        private final dk e;
        private int f;
        private Object g;
        private Object h;
        private byte i;
        private int j;

        public static final class a extends com.tapjoy.internal.do.a implements g {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            public Object d = BuildConfig.FLAVOR;
            private i e = i.APP;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final f d() {
                int i = 1;
                f fVar = new f((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                fVar.c = this.e;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                fVar.g = this.c;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                fVar.h = this.d;
                fVar.f = i;
                return fVar;
            }

            public final a a(f fVar) {
                if (fVar != f.d()) {
                    if (fVar.e()) {
                        a(fVar.c);
                    }
                    if (fVar.f()) {
                        this.b |= 2;
                        this.c = fVar.g;
                    }
                    if (fVar.h()) {
                        this.b |= 4;
                        this.d = fVar.h;
                    }
                    this.a = this.a.a(fVar.e);
                }
                return this;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 2) == 2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                return false;
            }

            public final a a(i iVar) {
                if (iVar == null) {
                    throw new NullPointerException();
                }
                this.b |= 1;
                this.e = iVar;
                return this;
            }

            private a f() {
                return new a().a(d());
            }
        }

        private f(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.i = (byte) -1;
            this.j = -1;
            this.e = aVar.a;
        }

        private f() {
            this.i = (byte) -1;
            this.j = -1;
            this.e = dk.b;
        }

        public static f d() {
            return d;
        }

        private f(dl dlVar) {
            this.i = (byte) -1;
            this.j = -1;
            m();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                            int e = dlVar.e();
                            i a3 = i.a(e);
                            if (a3 != null) {
                                this.f |= 1;
                                this.c = a3;
                                break;
                            }
                            a.e(a2);
                            a.e(e);
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            d = dlVar.d();
                            this.f |= 2;
                            this.g = d;
                            break;
                        case R.styleable.Toolbar_logoDescription /*26*/:
                            d = dlVar.d();
                            this.f |= 4;
                            this.h = d;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e2) {
                    e2.a = this;
                    throw e2;
                } catch (IOException e3) {
                    dq dqVar = new dq(e3.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e4) {
                    } finally {
                        this.e = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e5) {
            } finally {
                this.e = g.a();
            }
        }

        static {
            f fVar = new f();
            d = fVar;
            fVar.m();
        }

        public final boolean e() {
            return (this.f & 1) == 1;
        }

        public final boolean f() {
            return (this.f & 2) == 2;
        }

        public final String g() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.g = e;
            }
            return e;
        }

        private dk k() {
            Object obj = this.g;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.g = a;
            return a;
        }

        public final boolean h() {
            return (this.f & 4) == 4;
        }

        public final String i() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.h = e;
            }
            return e;
        }

        private dk l() {
            Object obj = this.h;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.h = a;
            return a;
        }

        private void m() {
            this.c = i.APP;
            this.g = BuildConfig.FLAVOR;
            this.h = BuildConfig.FLAVOR;
        }

        public final boolean c() {
            byte b = this.i;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!e()) {
                this.i = (byte) 0;
                return false;
            } else if (f()) {
                this.i = (byte) 1;
                return true;
            } else {
                this.i = (byte) 0;
                return false;
            }
        }

        public final void a(dm dmVar) {
            b();
            if ((this.f & 1) == 1) {
                dmVar.a(this.c.d);
            }
            if ((this.f & 2) == 2) {
                dmVar.a(2, k());
            }
            if ((this.f & 4) == 4) {
                dmVar.a(3, l());
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = this.j;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.f & 1) == 1) {
                i = dm.b(this.c.d) + 0;
            }
            if ((this.f & 2) == 2) {
                i += dm.b(2, k());
            }
            if ((this.f & 4) == 4) {
                i += dm.b(3, l());
            }
            i += this.e.a();
            this.j = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof f)) {
                return super.equals(obj);
            }
            f fVar = (f) obj;
            boolean z = e() == fVar.e();
            if (e()) {
                z = z && this.c == fVar.c;
            }
            if (z && f() == fVar.f()) {
                z = true;
            } else {
                z = false;
            }
            if (f()) {
                if (z && g().equals(fVar.g())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == fVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (!h()) {
                return z;
            }
            if (z && i().equals(fVar.i())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = f.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + dp.a(this.c);
            }
            if (f()) {
                hashCode = (((hashCode * 37) + 2) * 53) + g().hashCode();
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 3) * 53) + i().hashCode();
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a j() {
            return new a();
        }

        public static a a(f fVar) {
            return new a().a(fVar);
        }
    }

    public enum i implements com.tapjoy.internal.dp.a {
        APP(0),
        CAMPAIGN(1),
        CUSTOM(2);
        
        private static com.tapjoy.internal.dp.b e;
        final int d;

        static {
            e = new com.tapjoy.internal.dp.b() {
            };
        }

        public final int a() {
            return this.d;
        }

        public static i a(int i) {
            switch (i) {
                case TwitterResponse.NONE /*0*/:
                    return APP;
                case TwitterResponse.READ /*1*/:
                    return CAMPAIGN;
                case TwitterResponse.READ_WRITE /*2*/:
                    return CUSTOM;
                default:
                    return null;
            }
        }

        private i(int i) {
            this.d = i;
        }
    }

    public interface k extends dv {
    }

    public static final class j extends do implements k {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new j(dlVar);
            }
        };
        private static final j d;
        long c;
        private final dk e;
        private int f;
        private Object g;
        private byte h;
        private int i;

        public static final class a extends com.tapjoy.internal.do.a implements k {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            private long d;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final j d() {
                int i = 1;
                j jVar = new j((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                jVar.g = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                jVar.c = this.d;
                jVar.f = i;
                return jVar;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 2) == 2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                return false;
            }

            public final a a(long j) {
                this.b |= 2;
                this.d = j;
                return this;
            }

            private a f() {
                com.tapjoy.internal.do.a aVar = new a();
                j d = d();
                if (d != j.d()) {
                    if (d.e()) {
                        aVar.b |= 1;
                        aVar.c = d.g;
                    }
                    if (d.g()) {
                        aVar.a(d.c);
                    }
                    aVar.a = aVar.a.a(d.e);
                }
                return aVar;
            }
        }

        private j(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.h = (byte) -1;
            this.i = -1;
            this.e = aVar.a;
        }

        private j() {
            this.h = (byte) -1;
            this.i = -1;
            this.e = dk.b;
        }

        public static j d() {
            return d;
        }

        private j(dl dlVar) {
            this.h = (byte) -1;
            this.i = -1;
            j();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            dk d = dlVar.d();
                            this.f |= 1;
                            this.g = d;
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            this.f |= 2;
                            this.c = dlVar.f();
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.e = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.e = g.a();
            }
        }

        static {
            j jVar = new j();
            d = jVar;
            jVar.j();
        }

        public final boolean e() {
            return (this.f & 1) == 1;
        }

        public final String f() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.g = e;
            }
            return e;
        }

        private dk i() {
            Object obj = this.g;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.g = a;
            return a;
        }

        public final boolean g() {
            return (this.f & 2) == 2;
        }

        private void j() {
            this.g = BuildConfig.FLAVOR;
            this.c = 0;
        }

        public final boolean c() {
            byte b = this.h;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!e()) {
                this.h = (byte) 0;
                return false;
            } else if (g()) {
                this.h = (byte) 1;
                return true;
            } else {
                this.h = (byte) 0;
                return false;
            }
        }

        public final void a(dm dmVar) {
            b();
            if ((this.f & 1) == 1) {
                dmVar.a(1, i());
            }
            if ((this.f & 2) == 2) {
                dmVar.a(2, this.c);
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = this.i;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.f & 1) == 1) {
                i = dm.b(1, i()) + 0;
            }
            if ((this.f & 2) == 2) {
                i += dm.b(2, this.c);
            }
            i += this.e.a();
            this.i = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof j)) {
                return super.equals(obj);
            }
            j jVar = (j) obj;
            boolean z = e() == jVar.e();
            if (e()) {
                z = z && f().equals(jVar.f());
            }
            if (z && g() == jVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (!g()) {
                return z;
            }
            if (z && this.c == jVar.c) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = j.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + dp.a(this.c);
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a h() {
            return new a();
        }
    }

    public interface m extends dv {
    }

    public static final class l extends do implements m {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new l(dlVar);
            }
        };
        private static final l f;
        public int c;
        public int d;
        public int e;
        private final dk g;
        private int h;
        private Object i;
        private Object j;
        private Object k;
        private Object l;
        private Object m;
        private Object n;
        private Object o;
        private Object p;
        private Object q;
        private Object r;
        private Object s;
        private Object t;
        private Object u;
        private Object v;
        private byte w;
        private int x;

        public static final class a extends com.tapjoy.internal.do.a implements m {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            public Object d = BuildConfig.FLAVOR;
            public Object e = BuildConfig.FLAVOR;
            public Object f = BuildConfig.FLAVOR;
            public Object g = BuildConfig.FLAVOR;
            public Object h = BuildConfig.FLAVOR;
            public Object i = BuildConfig.FLAVOR;
            public Object j = BuildConfig.FLAVOR;
            public Object k = BuildConfig.FLAVOR;
            public Object l = BuildConfig.FLAVOR;
            public Object m = BuildConfig.FLAVOR;
            private int n;
            private int o;
            private int p;
            private Object q = BuildConfig.FLAVOR;
            private Object r = BuildConfig.FLAVOR;
            private Object s = BuildConfig.FLAVOR;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final l d() {
                int i = 1;
                l lVar = new l((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                lVar.i = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                lVar.j = this.d;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                lVar.k = this.e;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                lVar.l = this.f;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                lVar.m = this.g;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                lVar.n = this.h;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                lVar.c = this.n;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                lVar.d = this.o;
                if ((i2 & 256) == 256) {
                    i |= 256;
                }
                lVar.e = this.p;
                if ((i2 & 512) == 512) {
                    i |= 512;
                }
                lVar.o = this.q;
                if ((i2 & 1024) == 1024) {
                    i |= 1024;
                }
                lVar.p = this.r;
                if ((i2 & 2048) == 2048) {
                    i |= 2048;
                }
                lVar.q = this.i;
                if ((i2 & 4096) == 4096) {
                    i |= 4096;
                }
                lVar.r = this.j;
                if ((i2 & 8192) == 8192) {
                    i |= 8192;
                }
                lVar.s = this.s;
                if ((i2 & 16384) == 16384) {
                    i |= 16384;
                }
                lVar.t = this.k;
                if ((i2 & 32768) == 32768) {
                    i |= 32768;
                }
                lVar.u = this.l;
                if ((i2 & 65536) == 65536) {
                    i |= 65536;
                }
                lVar.v = this.m;
                lVar.h = i;
                return lVar;
            }

            public final a a(l lVar) {
                if (lVar != l.d()) {
                    if (lVar.e()) {
                        this.b |= 1;
                        this.c = lVar.i;
                    }
                    if (lVar.g()) {
                        this.b |= 2;
                        this.d = lVar.j;
                    }
                    if (lVar.i()) {
                        this.b |= 4;
                        this.e = lVar.k;
                    }
                    if (lVar.k()) {
                        this.b |= 8;
                        this.f = lVar.l;
                    }
                    if (lVar.m()) {
                        this.b |= 16;
                        this.g = lVar.m;
                    }
                    if (lVar.o()) {
                        this.b |= 32;
                        this.h = lVar.n;
                    }
                    if (lVar.q()) {
                        a(lVar.c);
                    }
                    if (lVar.r()) {
                        b(lVar.d);
                    }
                    if (lVar.s()) {
                        c(lVar.e);
                    }
                    if (lVar.t()) {
                        this.b |= 512;
                        this.q = lVar.o;
                    }
                    if (lVar.v()) {
                        this.b |= 1024;
                        this.r = lVar.p;
                    }
                    if (lVar.x()) {
                        this.b |= 2048;
                        this.i = lVar.q;
                    }
                    if (lVar.z()) {
                        this.b |= 4096;
                        this.j = lVar.r;
                    }
                    if (lVar.B()) {
                        this.b |= 8192;
                        this.s = lVar.s;
                    }
                    if (lVar.D()) {
                        this.b |= 16384;
                        this.k = lVar.t;
                    }
                    if (lVar.F()) {
                        this.b |= 32768;
                        this.l = lVar.u;
                    }
                    if (lVar.H()) {
                        this.b |= 65536;
                        this.m = lVar.v;
                    }
                    this.a = this.a.a(lVar.g);
                }
                return this;
            }

            public final boolean c() {
                return true;
            }

            public final a a(int i) {
                this.b |= 64;
                this.n = i;
                return this;
            }

            public final a b(int i) {
                this.b |= 128;
                this.o = i;
                return this;
            }

            public final a c(int i) {
                this.b |= 256;
                this.p = i;
                return this;
            }

            public final a a(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 512;
                this.q = str;
                return this;
            }

            public final a b(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 1024;
                this.r = str;
                return this;
            }

            public final a c(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 8192;
                this.s = str;
                return this;
            }

            private a f() {
                return new a().a(d());
            }
        }

        private l(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.w = (byte) -1;
            this.x = -1;
            this.g = aVar.a;
        }

        private l() {
            this.w = (byte) -1;
            this.x = -1;
            this.g = dk.b;
        }

        public static l d() {
            return f;
        }

        private l(dl dlVar) {
            this.w = (byte) -1;
            this.x = -1;
            Y();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            d = dlVar.d();
                            this.h |= 1;
                            this.i = d;
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            d = dlVar.d();
                            this.h |= 2;
                            this.j = d;
                            break;
                        case R.styleable.Toolbar_logoDescription /*26*/:
                            d = dlVar.d();
                            this.h |= 4;
                            this.k = d;
                            break;
                        case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                            d = dlVar.d();
                            this.h |= 8;
                            this.l = d;
                            break;
                        case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                            d = dlVar.d();
                            this.h |= 16;
                            this.m = d;
                            break;
                        case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                            d = dlVar.d();
                            this.h |= 32;
                            this.n = d;
                            break;
                        case R.styleable.AppCompatTheme_dividerVertical /*56*/:
                            this.h |= 64;
                            this.c = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_editTextBackground /*64*/:
                            this.h |= 128;
                            this.d = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemHeightLarge /*72*/:
                            this.h |= 256;
                            this.e = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator /*82*/:
                            d = dlVar.d();
                            this.h |= 512;
                            this.o = d;
                            break;
                        case R.styleable.AppCompatTheme_colorSwitchThumbNormal /*90*/:
                            d = dlVar.d();
                            this.h |= 1024;
                            this.p = d;
                            break;
                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                            d = dlVar.d();
                            this.h |= 2048;
                            this.q = d;
                            break;
                        case R.styleable.AppCompatTheme_editTextStyle /*106*/:
                            d = dlVar.d();
                            this.h |= 4096;
                            this.r = d;
                            break;
                        case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                            d = dlVar.d();
                            this.h |= 8192;
                            this.s = d;
                            break;
                        case 122:
                            d = dlVar.d();
                            this.h |= 16384;
                            this.t = d;
                            break;
                        case 130:
                            d = dlVar.d();
                            this.h |= 32768;
                            this.u = d;
                            break;
                        case 138:
                            d = dlVar.d();
                            this.h |= 65536;
                            this.v = d;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.g = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.g = g.a();
            }
        }

        static {
            l lVar = new l();
            f = lVar;
            lVar.Y();
        }

        public final boolean e() {
            return (this.h & 1) == 1;
        }

        public final String f() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.i = e;
            }
            return e;
        }

        private dk K() {
            Object obj = this.i;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.i = a;
            return a;
        }

        public final boolean g() {
            return (this.h & 2) == 2;
        }

        public final String h() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.j = e;
            }
            return e;
        }

        private dk L() {
            Object obj = this.j;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.j = a;
            return a;
        }

        public final boolean i() {
            return (this.h & 4) == 4;
        }

        public final String j() {
            Object obj = this.k;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.k = e;
            }
            return e;
        }

        private dk M() {
            Object obj = this.k;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.k = a;
            return a;
        }

        public final boolean k() {
            return (this.h & 8) == 8;
        }

        public final String l() {
            Object obj = this.l;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.l = e;
            }
            return e;
        }

        private dk N() {
            Object obj = this.l;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.l = a;
            return a;
        }

        public final boolean m() {
            return (this.h & 16) == 16;
        }

        public final String n() {
            Object obj = this.m;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.m = e;
            }
            return e;
        }

        private dk O() {
            Object obj = this.m;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.m = a;
            return a;
        }

        public final boolean o() {
            return (this.h & 32) == 32;
        }

        public final String p() {
            Object obj = this.n;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.n = e;
            }
            return e;
        }

        private dk P() {
            Object obj = this.n;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.n = a;
            return a;
        }

        public final boolean q() {
            return (this.h & 64) == 64;
        }

        public final boolean r() {
            return (this.h & 128) == 128;
        }

        public final boolean s() {
            return (this.h & 256) == 256;
        }

        public final boolean t() {
            return (this.h & 512) == 512;
        }

        public final String u() {
            Object obj = this.o;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.o = e;
            }
            return e;
        }

        private dk Q() {
            Object obj = this.o;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.o = a;
            return a;
        }

        public final boolean v() {
            return (this.h & 1024) == 1024;
        }

        public final String w() {
            Object obj = this.p;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.p = e;
            }
            return e;
        }

        private dk R() {
            Object obj = this.p;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.p = a;
            return a;
        }

        public final boolean x() {
            return (this.h & 2048) == 2048;
        }

        public final String y() {
            Object obj = this.q;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.q = e;
            }
            return e;
        }

        private dk S() {
            Object obj = this.q;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.q = a;
            return a;
        }

        public final boolean z() {
            return (this.h & 4096) == 4096;
        }

        public final String A() {
            Object obj = this.r;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.r = e;
            }
            return e;
        }

        private dk T() {
            Object obj = this.r;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.r = a;
            return a;
        }

        public final boolean B() {
            return (this.h & 8192) == 8192;
        }

        public final String C() {
            Object obj = this.s;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.s = e;
            }
            return e;
        }

        private dk U() {
            Object obj = this.s;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.s = a;
            return a;
        }

        public final boolean D() {
            return (this.h & 16384) == 16384;
        }

        public final String E() {
            Object obj = this.t;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.t = e;
            }
            return e;
        }

        private dk V() {
            Object obj = this.t;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.t = a;
            return a;
        }

        public final boolean F() {
            return (this.h & 32768) == 32768;
        }

        public final String G() {
            Object obj = this.u;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.u = e;
            }
            return e;
        }

        private dk W() {
            Object obj = this.u;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.u = a;
            return a;
        }

        public final boolean H() {
            return (this.h & 65536) == 65536;
        }

        public final String I() {
            Object obj = this.v;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.v = e;
            }
            return e;
        }

        private dk X() {
            Object obj = this.v;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.v = a;
            return a;
        }

        private void Y() {
            this.i = BuildConfig.FLAVOR;
            this.j = BuildConfig.FLAVOR;
            this.k = BuildConfig.FLAVOR;
            this.l = BuildConfig.FLAVOR;
            this.m = BuildConfig.FLAVOR;
            this.n = BuildConfig.FLAVOR;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.o = BuildConfig.FLAVOR;
            this.p = BuildConfig.FLAVOR;
            this.q = BuildConfig.FLAVOR;
            this.r = BuildConfig.FLAVOR;
            this.s = BuildConfig.FLAVOR;
            this.t = BuildConfig.FLAVOR;
            this.u = BuildConfig.FLAVOR;
            this.v = BuildConfig.FLAVOR;
        }

        public final boolean c() {
            byte b = this.w;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.w = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            if ((this.h & 1) == 1) {
                dmVar.a(1, K());
            }
            if ((this.h & 2) == 2) {
                dmVar.a(2, L());
            }
            if ((this.h & 4) == 4) {
                dmVar.a(3, M());
            }
            if ((this.h & 8) == 8) {
                dmVar.a(4, N());
            }
            if ((this.h & 16) == 16) {
                dmVar.a(5, O());
            }
            if ((this.h & 32) == 32) {
                dmVar.a(6, P());
            }
            if ((this.h & 64) == 64) {
                dmVar.a(7, this.c);
            }
            if ((this.h & 128) == 128) {
                dmVar.a(8, this.d);
            }
            if ((this.h & 256) == 256) {
                dmVar.a(9, this.e);
            }
            if ((this.h & 512) == 512) {
                dmVar.a(10, Q());
            }
            if ((this.h & 1024) == 1024) {
                dmVar.a(11, R());
            }
            if ((this.h & 2048) == 2048) {
                dmVar.a(12, S());
            }
            if ((this.h & 4096) == 4096) {
                dmVar.a(13, T());
            }
            if ((this.h & 8192) == 8192) {
                dmVar.a(14, U());
            }
            if ((this.h & 16384) == 16384) {
                dmVar.a(15, V());
            }
            if ((this.h & 32768) == 32768) {
                dmVar.a(16, W());
            }
            if ((this.h & 65536) == 65536) {
                dmVar.a(17, X());
            }
            dmVar.c(this.g);
        }

        public final int b() {
            int i = this.x;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.h & 1) == 1) {
                i = dm.b(1, K()) + 0;
            }
            if ((this.h & 2) == 2) {
                i += dm.b(2, L());
            }
            if ((this.h & 4) == 4) {
                i += dm.b(3, M());
            }
            if ((this.h & 8) == 8) {
                i += dm.b(4, N());
            }
            if ((this.h & 16) == 16) {
                i += dm.b(5, O());
            }
            if ((this.h & 32) == 32) {
                i += dm.b(6, P());
            }
            if ((this.h & 64) == 64) {
                i += dm.b(7, this.c);
            }
            if ((this.h & 128) == 128) {
                i += dm.b(8, this.d);
            }
            if ((this.h & 256) == 256) {
                i += dm.b(9, this.e);
            }
            if ((this.h & 512) == 512) {
                i += dm.b(10, Q());
            }
            if ((this.h & 1024) == 1024) {
                i += dm.b(11, R());
            }
            if ((this.h & 2048) == 2048) {
                i += dm.b(12, S());
            }
            if ((this.h & 4096) == 4096) {
                i += dm.b(13, T());
            }
            if ((this.h & 8192) == 8192) {
                i += dm.b(14, U());
            }
            if ((this.h & 16384) == 16384) {
                i += dm.b(15, V());
            }
            if ((this.h & 32768) == 32768) {
                i += dm.b(16, W());
            }
            if ((this.h & 65536) == 65536) {
                i += dm.b(17, X());
            }
            i += this.g.a();
            this.x = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof l)) {
                return super.equals(obj);
            }
            l lVar = (l) obj;
            boolean z = e() == lVar.e();
            if (e()) {
                z = z && f().equals(lVar.f());
            }
            if (z && g() == lVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (g()) {
                if (z && h().equals(lVar.h())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && i() == lVar.i()) {
                z = true;
            } else {
                z = false;
            }
            if (i()) {
                if (z && j().equals(lVar.j())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && k() == lVar.k()) {
                z = true;
            } else {
                z = false;
            }
            if (k()) {
                if (z && l().equals(lVar.l())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && m() == lVar.m()) {
                z = true;
            } else {
                z = false;
            }
            if (m()) {
                if (z && n().equals(lVar.n())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && o() == lVar.o()) {
                z = true;
            } else {
                z = false;
            }
            if (o()) {
                if (z && p().equals(lVar.p())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && q() == lVar.q()) {
                z = true;
            } else {
                z = false;
            }
            if (q()) {
                if (z && this.c == lVar.c) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && r() == lVar.r()) {
                z = true;
            } else {
                z = false;
            }
            if (r()) {
                if (z && this.d == lVar.d) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && s() == lVar.s()) {
                z = true;
            } else {
                z = false;
            }
            if (s()) {
                if (z && this.e == lVar.e) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && t() == lVar.t()) {
                z = true;
            } else {
                z = false;
            }
            if (t()) {
                if (z && u().equals(lVar.u())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && v() == lVar.v()) {
                z = true;
            } else {
                z = false;
            }
            if (v()) {
                if (z && w().equals(lVar.w())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && x() == lVar.x()) {
                z = true;
            } else {
                z = false;
            }
            if (x()) {
                if (z && y().equals(lVar.y())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && z() == lVar.z()) {
                z = true;
            } else {
                z = false;
            }
            if (z()) {
                if (z && A().equals(lVar.A())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && B() == lVar.B()) {
                z = true;
            } else {
                z = false;
            }
            if (B()) {
                if (z && C().equals(lVar.C())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && D() == lVar.D()) {
                z = true;
            } else {
                z = false;
            }
            if (D()) {
                if (z && E().equals(lVar.E())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && F() == lVar.F()) {
                z = true;
            } else {
                z = false;
            }
            if (F()) {
                if (z && G().equals(lVar.G())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && H() == lVar.H()) {
                z = true;
            } else {
                z = false;
            }
            if (!H()) {
                return z;
            }
            if (z && I().equals(lVar.I())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = l.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + h().hashCode();
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 3) * 53) + j().hashCode();
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 4) * 53) + l().hashCode();
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 5) * 53) + n().hashCode();
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 6) * 53) + p().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 7) * 53) + this.c;
            }
            if (r()) {
                hashCode = (((hashCode * 37) + 8) * 53) + this.d;
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 9) * 53) + this.e;
            }
            if (t()) {
                hashCode = (((hashCode * 37) + 10) * 53) + u().hashCode();
            }
            if (v()) {
                hashCode = (((hashCode * 37) + 11) * 53) + w().hashCode();
            }
            if (x()) {
                hashCode = (((hashCode * 37) + 12) * 53) + y().hashCode();
            }
            if (z()) {
                hashCode = (((hashCode * 37) + 13) * 53) + A().hashCode();
            }
            if (B()) {
                hashCode = (((hashCode * 37) + 14) * 53) + C().hashCode();
            }
            if (D()) {
                hashCode = (((hashCode * 37) + 15) * 53) + E().hashCode();
            }
            if (F()) {
                hashCode = (((hashCode * 37) + 16) * 53) + G().hashCode();
            }
            if (H()) {
                hashCode = (((hashCode * 37) + 17) * 53) + I().hashCode();
            }
            hashCode = (hashCode * 29) + this.g.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a J() {
            return new a();
        }

        public static a a(l lVar) {
            return new a().a(lVar);
        }
    }

    public interface o extends dv {
    }

    public static final class n extends do implements o {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new n(dlVar, dnVar);
            }
        };
        private static final n f;
        public l c;
        public a d;
        public z e;
        private final dk g;
        private int h;
        private byte i;
        private int j;

        public static final class a extends com.tapjoy.internal.do.a implements o {
            public int b;
            public l c = l.d();
            public a d = a.d();
            public z e = z.d();

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final n d() {
                int i = 1;
                n nVar = new n((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                nVar.c = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                nVar.d = this.d;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                nVar.e = this.e;
                nVar.h = i;
                return nVar;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 4) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z || this.e.c()) {
                    return true;
                }
                return false;
            }

            private a f() {
                com.tapjoy.internal.do.a aVar = new a();
                n d = d();
                if (d != n.d()) {
                    if (d.e()) {
                        l lVar = d.c;
                        if ((aVar.b & 1) != 1 || aVar.c == l.d()) {
                            aVar.c = lVar;
                        } else {
                            aVar.c = l.a(aVar.c).a(lVar).d();
                        }
                        aVar.b |= 1;
                    }
                    if (d.f()) {
                        a aVar2 = d.d;
                        if ((aVar.b & 2) != 2 || aVar.d == a.d()) {
                            aVar.d = aVar2;
                        } else {
                            aVar.d = a.a(aVar.d).a(aVar2).d();
                        }
                        aVar.b |= 2;
                    }
                    if (d.g()) {
                        z zVar = d.e;
                        if ((aVar.b & 4) != 4 || aVar.e == z.d()) {
                            aVar.e = zVar;
                        } else {
                            aVar.e = z.a(aVar.e).a(zVar).d();
                        }
                        aVar.b |= 4;
                    }
                    aVar.a = aVar.a.a(d.g);
                }
                return aVar;
            }
        }

        private n(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.i = (byte) -1;
            this.j = -1;
            this.g = aVar.a;
        }

        private n() {
            this.i = (byte) -1;
            this.j = -1;
            this.g = dk.b;
        }

        public static n d() {
            return f;
        }

        private n(dl dlVar, dn dnVar) {
            this.i = (byte) -1;
            this.j = -1;
            i();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            a a3;
                            if ((this.h & 1) == 1) {
                                a3 = new a().a(this.c);
                            } else {
                                a3 = null;
                            }
                            this.c = (l) dlVar.a(l.b, dnVar);
                            if (a3 != null) {
                                a3.a(this.c);
                                this.c = a3.d();
                            }
                            this.h |= 1;
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            a o;
                            if ((this.h & 2) == 2) {
                                o = this.d.o();
                            } else {
                                o = null;
                            }
                            this.d = (a) dlVar.a(a.b, dnVar);
                            if (o != null) {
                                o.a(this.d);
                                this.d = o.d();
                            }
                            this.h |= 2;
                            break;
                        case R.styleable.Toolbar_logoDescription /*26*/:
                            a a4;
                            if ((this.h & 4) == 4) {
                                a4 = z.a(this.e);
                            } else {
                                a4 = null;
                            }
                            this.e = (z) dlVar.a(z.b, dnVar);
                            if (a4 != null) {
                                a4.a(this.e);
                                this.e = a4.d();
                            }
                            this.h |= 4;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.g = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.g = g.a();
            }
        }

        static {
            n nVar = new n();
            f = nVar;
            nVar.i();
        }

        public final boolean e() {
            return (this.h & 1) == 1;
        }

        public final boolean f() {
            return (this.h & 2) == 2;
        }

        public final boolean g() {
            return (this.h & 4) == 4;
        }

        private void i() {
            this.c = l.d();
            this.d = a.d();
            this.e = z.d();
        }

        public final boolean c() {
            byte b = this.i;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!g() || this.e.c()) {
                this.i = (byte) 1;
                return true;
            }
            this.i = (byte) 0;
            return false;
        }

        public final void a(dm dmVar) {
            b();
            if ((this.h & 1) == 1) {
                dmVar.a(1, this.c);
            }
            if ((this.h & 2) == 2) {
                dmVar.a(2, this.d);
            }
            if ((this.h & 4) == 4) {
                dmVar.a(3, this.e);
            }
            dmVar.c(this.g);
        }

        public final int b() {
            int i = this.j;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.h & 1) == 1) {
                i = dm.b(1, this.c) + 0;
            }
            if ((this.h & 2) == 2) {
                i += dm.b(2, this.d);
            }
            if ((this.h & 4) == 4) {
                i += dm.b(3, this.e);
            }
            i += this.g.a();
            this.j = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof n)) {
                return super.equals(obj);
            }
            n nVar = (n) obj;
            boolean z = e() == nVar.e();
            if (e()) {
                z = z && this.c.equals(nVar.c);
            }
            if (z && f() == nVar.f()) {
                z = true;
            } else {
                z = false;
            }
            if (f()) {
                if (z && this.d.equals(nVar.d)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && g() == nVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (!g()) {
                return z;
            }
            if (z && this.e.equals(nVar.e)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = n.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.c.hashCode();
            }
            if (f()) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.d.hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + this.e.hashCode();
            }
            hashCode = (hashCode * 29) + this.g.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a h() {
            return new a();
        }
    }

    public interface q extends dv {
    }

    public static final class p extends do implements q {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new p(dlVar);
            }
        };
        private static final p c;
        private final dk d;
        private int e;
        private Object f;
        private Object g;
        private Object h;
        private byte i;
        private int j;

        public static final class a extends com.tapjoy.internal.do.a implements q {
            private int b;
            private Object c = BuildConfig.FLAVOR;
            private Object d = BuildConfig.FLAVOR;
            private Object e = BuildConfig.FLAVOR;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return g();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return g();
            }

            public final /* synthetic */ Object clone() {
                return g();
            }

            private a() {
            }

            public final p d() {
                p e = e();
                if (e.c()) {
                    return e;
                }
                throw new dz();
            }

            public final p e() {
                int i = 1;
                p pVar = new p((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                pVar.f = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                pVar.g = this.d;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                pVar.h = this.e;
                pVar.e = i;
                return pVar;
            }

            public final a a(p pVar) {
                if (pVar != p.d()) {
                    if (pVar.e()) {
                        this.b |= 1;
                        this.c = pVar.f;
                    }
                    if (pVar.g()) {
                        this.b |= 2;
                        this.d = pVar.g;
                    }
                    if (pVar.i()) {
                        this.b |= 4;
                        this.e = pVar.h;
                    }
                    this.a = this.a.a(pVar.d);
                }
                return this;
            }

            public final boolean c() {
                return true;
            }

            public final a a(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 1;
                this.c = str;
                return this;
            }

            public final a b(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 2;
                this.d = str;
                return this;
            }

            public final a c(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 4;
                this.e = str;
                return this;
            }

            private a g() {
                return new a().a(e());
            }
        }

        private p(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.i = (byte) -1;
            this.j = -1;
            this.d = aVar.a;
        }

        private p() {
            this.i = (byte) -1;
            this.j = -1;
            this.d = dk.b;
        }

        public static p d() {
            return c;
        }

        private p(dl dlVar) {
            this.i = (byte) -1;
            this.j = -1;
            o();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            d = dlVar.d();
                            this.e |= 1;
                            this.f = d;
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            d = dlVar.d();
                            this.e |= 2;
                            this.g = d;
                            break;
                        case R.styleable.Toolbar_logoDescription /*26*/:
                            d = dlVar.d();
                            this.e |= 4;
                            this.h = d;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.d = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.d = g.a();
            }
        }

        static {
            p pVar = new p();
            c = pVar;
            pVar.o();
        }

        public final boolean e() {
            return (this.e & 1) == 1;
        }

        public final String f() {
            Object obj = this.f;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.f = e;
            }
            return e;
        }

        private dk l() {
            Object obj = this.f;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.f = a;
            return a;
        }

        public final boolean g() {
            return (this.e & 2) == 2;
        }

        public final String h() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.g = e;
            }
            return e;
        }

        private dk m() {
            Object obj = this.g;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.g = a;
            return a;
        }

        public final boolean i() {
            return (this.e & 4) == 4;
        }

        public final String j() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.h = e;
            }
            return e;
        }

        private dk n() {
            Object obj = this.h;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.h = a;
            return a;
        }

        private void o() {
            this.f = BuildConfig.FLAVOR;
            this.g = BuildConfig.FLAVOR;
            this.h = BuildConfig.FLAVOR;
        }

        public final boolean c() {
            byte b = this.i;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.i = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            if ((this.e & 1) == 1) {
                dmVar.a(1, l());
            }
            if ((this.e & 2) == 2) {
                dmVar.a(2, m());
            }
            if ((this.e & 4) == 4) {
                dmVar.a(3, n());
            }
            dmVar.c(this.d);
        }

        public final int b() {
            int i = this.j;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.e & 1) == 1) {
                i = dm.b(1, l()) + 0;
            }
            if ((this.e & 2) == 2) {
                i += dm.b(2, m());
            }
            if ((this.e & 4) == 4) {
                i += dm.b(3, n());
            }
            i += this.d.a();
            this.j = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof p)) {
                return super.equals(obj);
            }
            p pVar = (p) obj;
            boolean z = e() == pVar.e();
            if (e()) {
                z = z && f().equals(pVar.f());
            }
            if (z && g() == pVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (g()) {
                if (z && h().equals(pVar.h())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && i() == pVar.i()) {
                z = true;
            } else {
                z = false;
            }
            if (!i()) {
                return z;
            }
            if (z && j().equals(pVar.j())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = p.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + h().hashCode();
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 3) * 53) + j().hashCode();
            }
            hashCode = (hashCode * 29) + this.d.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a k() {
            return new a();
        }

        public static a a(p pVar) {
            return new a().a(pVar);
        }
    }

    public interface s extends dv {
    }

    public static final class r extends do implements s {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new r(dlVar);
            }
        };
        private static final r g;
        int c;
        double d;
        int e;
        long f;
        private final dk h;
        private int i;
        private Object j;
        private Object k;
        private Object l;
        private Object m;
        private Object n;
        private Object o;
        private Object p;
        private Object q;
        private Object r;
        private Object s;
        private byte t;
        private int u;

        public static final class a extends com.tapjoy.internal.do.a implements s {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            public Object d = BuildConfig.FLAVOR;
            public Object e = BuildConfig.FLAVOR;
            public Object f = BuildConfig.FLAVOR;
            public Object g = BuildConfig.FLAVOR;
            private int h = 1;
            private double i;
            private Object j = BuildConfig.FLAVOR;
            private Object k = BuildConfig.FLAVOR;
            private Object l = BuildConfig.FLAVOR;
            private Object m = BuildConfig.FLAVOR;
            private int n;
            private long o;
            private Object p = BuildConfig.FLAVOR;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final r d() {
                int i = 1;
                r rVar = new r((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                rVar.j = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                rVar.c = this.h;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                rVar.d = this.i;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                rVar.k = this.d;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                rVar.l = this.j;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                rVar.m = this.k;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                rVar.n = this.l;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                rVar.o = this.m;
                if ((i2 & 256) == 256) {
                    i |= 256;
                }
                rVar.e = this.n;
                if ((i2 & 512) == 512) {
                    i |= 512;
                }
                rVar.f = this.o;
                if ((i2 & 1024) == 1024) {
                    i |= 1024;
                }
                rVar.p = this.e;
                if ((i2 & 2048) == 2048) {
                    i |= 2048;
                }
                rVar.q = this.p;
                if ((i2 & 4096) == 4096) {
                    i |= 4096;
                }
                rVar.r = this.f;
                if ((i2 & 8192) == 8192) {
                    i |= 8192;
                }
                rVar.s = this.g;
                rVar.i = i;
                return rVar;
            }

            public final a a(r rVar) {
                if (rVar != r.d()) {
                    int i;
                    if (rVar.e()) {
                        this.b |= 1;
                        this.c = rVar.j;
                    }
                    if (rVar.g()) {
                        i = rVar.c;
                        this.b |= 2;
                        this.h = i;
                    }
                    if (rVar.h()) {
                        a(rVar.d);
                    }
                    if (rVar.i()) {
                        this.b |= 8;
                        this.d = rVar.k;
                    }
                    if (rVar.k()) {
                        this.b |= 16;
                        this.j = rVar.l;
                    }
                    if (rVar.m()) {
                        this.b |= 32;
                        this.k = rVar.m;
                    }
                    if (rVar.o()) {
                        this.b |= 64;
                        this.l = rVar.n;
                    }
                    if (rVar.q()) {
                        this.b |= 128;
                        this.m = rVar.o;
                    }
                    if (rVar.s()) {
                        i = rVar.e;
                        this.b |= 256;
                        this.n = i;
                    }
                    if (rVar.t()) {
                        long j = rVar.f;
                        this.b |= 512;
                        this.o = j;
                    }
                    if (rVar.u()) {
                        this.b |= 1024;
                        this.e = rVar.p;
                    }
                    if (rVar.w()) {
                        this.b |= 2048;
                        this.p = rVar.q;
                    }
                    if (rVar.y()) {
                        this.b |= 4096;
                        this.f = rVar.r;
                    }
                    if (rVar.A()) {
                        this.b |= 8192;
                        this.g = rVar.s;
                    }
                    this.a = this.a.a(rVar.h);
                }
                return this;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                return false;
            }

            public final a a(double d) {
                this.b |= 4;
                this.i = d;
                return this;
            }

            private a f() {
                return new a().a(d());
            }
        }

        private r(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.t = (byte) -1;
            this.u = -1;
            this.h = aVar.a;
        }

        private r() {
            this.t = (byte) -1;
            this.u = -1;
            this.h = dk.b;
        }

        public static r d() {
            return g;
        }

        private r(dl dlVar) {
            this.t = (byte) -1;
            this.u = -1;
            N();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    dk d;
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            d = dlVar.d();
                            this.i |= 1;
                            this.j = d;
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            this.i |= 2;
                            this.c = dlVar.e();
                            break;
                        case R.styleable.Toolbar_navigationContentDescription /*25*/:
                            this.i |= 4;
                            this.d = Double.longBitsToDouble(dlVar.g());
                            break;
                        case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                            d = dlVar.d();
                            this.i |= 8;
                            this.k = d;
                            break;
                        case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                            d = dlVar.d();
                            this.i |= 16;
                            this.l = d;
                            break;
                        case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                            d = dlVar.d();
                            this.i |= 32;
                            this.m = d;
                            break;
                        case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                            d = dlVar.d();
                            this.i |= 64;
                            this.n = d;
                            break;
                        case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
                            d = dlVar.d();
                            this.i |= 128;
                            this.o = d;
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemHeightLarge /*72*/:
                            this.i |= 256;
                            this.e = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_panelMenuListWidth /*80*/:
                            this.i |= 512;
                            this.f = dlVar.f();
                            break;
                        case R.styleable.AppCompatTheme_colorSwitchThumbNormal /*90*/:
                            d = dlVar.d();
                            this.i |= 1024;
                            this.p = d;
                            break;
                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                            d = dlVar.d();
                            this.i |= 2048;
                            this.q = d;
                            break;
                        case R.styleable.AppCompatTheme_editTextStyle /*106*/:
                            d = dlVar.d();
                            this.i |= 4096;
                            this.r = d;
                            break;
                        case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                            d = dlVar.d();
                            this.i |= 8192;
                            this.s = d;
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.h = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.h = g.a();
            }
        }

        static {
            r rVar = new r();
            g = rVar;
            rVar.N();
        }

        public final boolean e() {
            return (this.i & 1) == 1;
        }

        public final String f() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.j = e;
            }
            return e;
        }

        private dk D() {
            Object obj = this.j;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.j = a;
            return a;
        }

        public final boolean g() {
            return (this.i & 2) == 2;
        }

        public final boolean h() {
            return (this.i & 4) == 4;
        }

        public final boolean i() {
            return (this.i & 8) == 8;
        }

        public final String j() {
            Object obj = this.k;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.k = e;
            }
            return e;
        }

        private dk E() {
            Object obj = this.k;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.k = a;
            return a;
        }

        public final boolean k() {
            return (this.i & 16) == 16;
        }

        public final String l() {
            Object obj = this.l;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.l = e;
            }
            return e;
        }

        private dk F() {
            Object obj = this.l;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.l = a;
            return a;
        }

        public final boolean m() {
            return (this.i & 32) == 32;
        }

        public final String n() {
            Object obj = this.m;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.m = e;
            }
            return e;
        }

        private dk G() {
            Object obj = this.m;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.m = a;
            return a;
        }

        public final boolean o() {
            return (this.i & 64) == 64;
        }

        public final String p() {
            Object obj = this.n;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.n = e;
            }
            return e;
        }

        private dk H() {
            Object obj = this.n;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.n = a;
            return a;
        }

        public final boolean q() {
            return (this.i & 128) == 128;
        }

        public final String r() {
            Object obj = this.o;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.o = e;
            }
            return e;
        }

        private dk I() {
            Object obj = this.o;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.o = a;
            return a;
        }

        public final boolean s() {
            return (this.i & 256) == 256;
        }

        public final boolean t() {
            return (this.i & 512) == 512;
        }

        public final boolean u() {
            return (this.i & 1024) == 1024;
        }

        public final String v() {
            Object obj = this.p;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.p = e;
            }
            return e;
        }

        private dk J() {
            Object obj = this.p;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.p = a;
            return a;
        }

        public final boolean w() {
            return (this.i & 2048) == 2048;
        }

        public final String x() {
            Object obj = this.q;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.q = e;
            }
            return e;
        }

        private dk K() {
            Object obj = this.q;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.q = a;
            return a;
        }

        public final boolean y() {
            return (this.i & 4096) == 4096;
        }

        public final String z() {
            Object obj = this.r;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.r = e;
            }
            return e;
        }

        private dk L() {
            Object obj = this.r;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.r = a;
            return a;
        }

        public final boolean A() {
            return (this.i & 8192) == 8192;
        }

        public final String B() {
            Object obj = this.s;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.s = e;
            }
            return e;
        }

        private dk M() {
            Object obj = this.s;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.s = a;
            return a;
        }

        private void N() {
            this.j = BuildConfig.FLAVOR;
            this.c = 1;
            this.d = 0.0d;
            this.k = BuildConfig.FLAVOR;
            this.l = BuildConfig.FLAVOR;
            this.m = BuildConfig.FLAVOR;
            this.n = BuildConfig.FLAVOR;
            this.o = BuildConfig.FLAVOR;
            this.e = 0;
            this.f = 0;
            this.p = BuildConfig.FLAVOR;
            this.q = BuildConfig.FLAVOR;
            this.r = BuildConfig.FLAVOR;
            this.s = BuildConfig.FLAVOR;
        }

        public final boolean c() {
            byte b = this.t;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (e()) {
                this.t = (byte) 1;
                return true;
            }
            this.t = (byte) 0;
            return false;
        }

        public final void a(dm dmVar) {
            b();
            if ((this.i & 1) == 1) {
                dmVar.a(1, D());
            }
            if ((this.i & 2) == 2) {
                dmVar.a(2, this.c);
            }
            if ((this.i & 4) == 4) {
                dmVar.a(3, this.d);
            }
            if ((this.i & 8) == 8) {
                dmVar.a(4, E());
            }
            if ((this.i & 16) == 16) {
                dmVar.a(5, F());
            }
            if ((this.i & 32) == 32) {
                dmVar.a(6, G());
            }
            if ((this.i & 64) == 64) {
                dmVar.a(7, H());
            }
            if ((this.i & 128) == 128) {
                dmVar.a(8, I());
            }
            if ((this.i & 256) == 256) {
                dmVar.a(9, this.e);
            }
            if ((this.i & 512) == 512) {
                dmVar.a(10, this.f);
            }
            if ((this.i & 1024) == 1024) {
                dmVar.a(11, J());
            }
            if ((this.i & 2048) == 2048) {
                dmVar.a(12, K());
            }
            if ((this.i & 4096) == 4096) {
                dmVar.a(13, L());
            }
            if ((this.i & 8192) == 8192) {
                dmVar.a(14, M());
            }
            dmVar.c(this.h);
        }

        public final int b() {
            int i = this.u;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.i & 1) == 1) {
                i = dm.b(1, D()) + 0;
            }
            if ((this.i & 2) == 2) {
                i += dm.b(2, this.c);
            }
            if ((this.i & 4) == 4) {
                i += dm.d(3) + 8;
            }
            if ((this.i & 8) == 8) {
                i += dm.b(4, E());
            }
            if ((this.i & 16) == 16) {
                i += dm.b(5, F());
            }
            if ((this.i & 32) == 32) {
                i += dm.b(6, G());
            }
            if ((this.i & 64) == 64) {
                i += dm.b(7, H());
            }
            if ((this.i & 128) == 128) {
                i += dm.b(8, I());
            }
            if ((this.i & 256) == 256) {
                i += dm.b(9, this.e);
            }
            if ((this.i & 512) == 512) {
                i += dm.b(10, this.f);
            }
            if ((this.i & 1024) == 1024) {
                i += dm.b(11, J());
            }
            if ((this.i & 2048) == 2048) {
                i += dm.b(12, K());
            }
            if ((this.i & 4096) == 4096) {
                i += dm.b(13, L());
            }
            if ((this.i & 8192) == 8192) {
                i += dm.b(14, M());
            }
            i += this.h.a();
            this.u = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof r)) {
                return super.equals(obj);
            }
            r rVar = (r) obj;
            boolean z = e() == rVar.e();
            if (e()) {
                z = z && f().equals(rVar.f());
            }
            if (z && g() == rVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (g()) {
                if (z && this.c == rVar.c) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == rVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (h()) {
                if (z && Double.doubleToLongBits(this.d) == Double.doubleToLongBits(rVar.d)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && i() == rVar.i()) {
                z = true;
            } else {
                z = false;
            }
            if (i()) {
                if (z && j().equals(rVar.j())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && k() == rVar.k()) {
                z = true;
            } else {
                z = false;
            }
            if (k()) {
                if (z && l().equals(rVar.l())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && m() == rVar.m()) {
                z = true;
            } else {
                z = false;
            }
            if (m()) {
                if (z && n().equals(rVar.n())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && o() == rVar.o()) {
                z = true;
            } else {
                z = false;
            }
            if (o()) {
                if (z && p().equals(rVar.p())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && q() == rVar.q()) {
                z = true;
            } else {
                z = false;
            }
            if (q()) {
                if (z && r().equals(rVar.r())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && s() == rVar.s()) {
                z = true;
            } else {
                z = false;
            }
            if (s()) {
                if (z && this.e == rVar.e) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && t() == rVar.t()) {
                z = true;
            } else {
                z = false;
            }
            if (t()) {
                if (z && this.f == rVar.f) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && u() == rVar.u()) {
                z = true;
            } else {
                z = false;
            }
            if (u()) {
                if (z && v().equals(rVar.v())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && w() == rVar.w()) {
                z = true;
            } else {
                z = false;
            }
            if (w()) {
                if (z && x().equals(rVar.x())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && y() == rVar.y()) {
                z = true;
            } else {
                z = false;
            }
            if (y()) {
                if (z && z().equals(rVar.z())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && A() == rVar.A()) {
                z = true;
            } else {
                z = false;
            }
            if (!A()) {
                return z;
            }
            if (z && B().equals(rVar.B())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = r.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.c;
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 3) * 53) + dp.a(Double.doubleToLongBits(this.d));
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 4) * 53) + j().hashCode();
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 5) * 53) + l().hashCode();
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 6) * 53) + n().hashCode();
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 7) * 53) + p().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 8) * 53) + r().hashCode();
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 9) * 53) + this.e;
            }
            if (t()) {
                hashCode = (((hashCode * 37) + 10) * 53) + dp.a(this.f);
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 11) * 53) + v().hashCode();
            }
            if (w()) {
                hashCode = (((hashCode * 37) + 12) * 53) + x().hashCode();
            }
            if (y()) {
                hashCode = (((hashCode * 37) + 13) * 53) + z().hashCode();
            }
            if (A()) {
                hashCode = (((hashCode * 37) + 14) * 53) + B().hashCode();
            }
            hashCode = (hashCode * 29) + this.h.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a C() {
            return new a();
        }

        public static a a(r rVar) {
            return new a().a(rVar);
        }
    }

    public interface w extends dv {
    }

    public static final class t extends do implements w {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new t(dlVar);
            }
        };
        private static final t e;
        public long c;
        long d;
        private final dk f;
        private int g;
        private Object h;
        private byte i;
        private int j;

        public static final class a extends com.tapjoy.internal.do.a implements w {
            public int b;
            public Object c = BuildConfig.FLAVOR;
            private long d;
            private long e;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return f();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return f();
            }

            public final /* synthetic */ Object clone() {
                return f();
            }

            private a() {
            }

            public final t d() {
                t g = g();
                if (g.c()) {
                    return g;
                }
                throw new dz();
            }

            private t g() {
                int i = 1;
                t tVar = new t((com.tapjoy.internal.do.a) this);
                int i2 = this.b;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                tVar.h = this.c;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                tVar.c = this.d;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                tVar.d = this.e;
                tVar.g = i;
                return tVar;
            }

            public final a a(t tVar) {
                if (tVar != t.d()) {
                    if (tVar.e()) {
                        this.b |= 1;
                        this.c = tVar.h;
                    }
                    if (tVar.g()) {
                        a(tVar.c);
                    }
                    if (tVar.h()) {
                        b(tVar.d);
                    }
                    this.a = this.a.a(tVar.f);
                }
                return this;
            }

            public final boolean c() {
                boolean z;
                if ((this.b & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                if ((this.b & 2) == 2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                return false;
            }

            public final a a(long j) {
                this.b |= 2;
                this.d = j;
                return this;
            }

            public final a b(long j) {
                this.b |= 4;
                this.e = j;
                return this;
            }

            private a f() {
                return new a().a(g());
            }
        }

        private t(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.i = (byte) -1;
            this.j = -1;
            this.f = aVar.a;
        }

        private t() {
            this.i = (byte) -1;
            this.j = -1;
            this.f = dk.b;
        }

        public static t d() {
            return e;
        }

        private t(dl dlVar) {
            this.i = (byte) -1;
            this.j = -1;
            k();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            Object obj = null;
            while (obj == null) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            obj = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            dk d = dlVar.d();
                            this.g |= 1;
                            this.h = d;
                            break;
                        case R.styleable.Toolbar_titleMarginEnd /*16*/:
                            this.g |= 2;
                            this.c = dlVar.f();
                            break;
                        case R.styleable.Toolbar_navigationIcon /*24*/:
                            this.g |= 4;
                            this.d = dlVar.f();
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    e.a = this;
                    throw e;
                } catch (IOException e2) {
                    dq dqVar = new dq(e2.getMessage());
                    dqVar.a = this;
                    throw dqVar;
                } catch (Throwable th) {
                    try {
                        a.a();
                    } catch (IOException e3) {
                    } finally {
                        this.f = g.a();
                    }
                }
            }
            try {
                a.a();
            } catch (IOException e4) {
            } finally {
                this.f = g.a();
            }
        }

        static {
            t tVar = new t();
            e = tVar;
            tVar.k();
        }

        public final boolean e() {
            return (this.g & 1) == 1;
        }

        public final String f() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.h = e;
            }
            return e;
        }

        private dk j() {
            Object obj = this.h;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.h = a;
            return a;
        }

        public final boolean g() {
            return (this.g & 2) == 2;
        }

        public final boolean h() {
            return (this.g & 4) == 4;
        }

        private void k() {
            this.h = BuildConfig.FLAVOR;
            this.c = 0;
            this.d = 0;
        }

        public final boolean c() {
            byte b = this.i;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!e()) {
                this.i = (byte) 0;
                return false;
            } else if (g()) {
                this.i = (byte) 1;
                return true;
            } else {
                this.i = (byte) 0;
                return false;
            }
        }

        public final void a(dm dmVar) {
            b();
            if ((this.g & 1) == 1) {
                dmVar.a(1, j());
            }
            if ((this.g & 2) == 2) {
                dmVar.a(2, this.c);
            }
            if ((this.g & 4) == 4) {
                dmVar.a(3, this.d);
            }
            dmVar.c(this.f);
        }

        public final int b() {
            int i = this.j;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.g & 1) == 1) {
                i = dm.b(1, j()) + 0;
            }
            if ((this.g & 2) == 2) {
                i += dm.b(2, this.c);
            }
            if ((this.g & 4) == 4) {
                i += dm.b(3, this.d);
            }
            i += this.f.a();
            this.j = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof t)) {
                return super.equals(obj);
            }
            t tVar = (t) obj;
            boolean z = e() == tVar.e();
            if (e()) {
                z = z && f().equals(tVar.f());
            }
            if (z && g() == tVar.g()) {
                z = true;
            } else {
                z = false;
            }
            if (g()) {
                if (z && this.c == tVar.c) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == tVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (!h()) {
                return z;
            }
            if (z && this.d == tVar.d) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = t.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 2) * 53) + dp.a(this.c);
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 3) * 53) + dp.a(this.d);
            }
            hashCode = (hashCode * 29) + this.f.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a i() {
            return new a();
        }

        public static a a(t tVar) {
            return new a().a(tVar);
        }
    }

    public interface v extends dv {
    }

    public static final class u extends do implements v {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new u(dlVar, dnVar);
            }
        };
        private static final u d;
        public List c;
        private final dk e;
        private byte f;
        private int g;

        public static final class a extends com.tapjoy.internal.do.a implements v {
            public List b = Collections.emptyList();
            private int c;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return g();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return g();
            }

            public final /* synthetic */ Object clone() {
                return g();
            }

            private a() {
            }

            public final u d() {
                u uVar = new u((com.tapjoy.internal.do.a) this);
                if ((this.c & 1) == 1) {
                    this.b = Collections.unmodifiableList(this.b);
                    this.c &= -2;
                }
                uVar.c = this.b;
                return uVar;
            }

            public final boolean c() {
                for (int i = 0; i < this.b.size(); i++) {
                    if (!((t) this.b.get(i)).c()) {
                        return false;
                    }
                }
                return true;
            }

            public final void e() {
                if ((this.c & 1) != 1) {
                    this.b = new ArrayList(this.b);
                    this.c |= 1;
                }
            }

            private a g() {
                com.tapjoy.internal.do.a aVar = new a();
                u d = d();
                if (d != u.d()) {
                    if (!d.c.isEmpty()) {
                        if (aVar.b.isEmpty()) {
                            aVar.b = d.c;
                            aVar.c &= -2;
                        } else {
                            aVar.e();
                            aVar.b.addAll(d.c);
                        }
                    }
                    aVar.a = aVar.a.a(d.e);
                }
                return aVar;
            }
        }

        private u(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.f = (byte) -1;
            this.g = -1;
            this.e = aVar.a;
        }

        private u() {
            this.f = (byte) -1;
            this.g = -1;
            this.e = dk.b;
        }

        public static u d() {
            return d;
        }

        private u(dl dlVar, dn dnVar) {
            Throwable th;
            int i = 0;
            this.f = (byte) -1;
            this.g = -1;
            this.c = Collections.emptyList();
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            i2 = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            if ((i & 1) != 1) {
                                this.c = new ArrayList();
                                i |= 1;
                            }
                            this.c.add(dlVar.a(t.b, dnVar));
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    dq dqVar = e;
                    i2 = i;
                    dq dqVar2 = dqVar;
                    dqVar2.a = this;
                    throw dqVar2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    i2 = i;
                    dq dqVar3 = new dq(iOException.getMessage());
                    dqVar3.a = this;
                    throw dqVar3;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if ((i & 1) == 1) {
                this.c = Collections.unmodifiableList(this.c);
            }
            try {
                a.a();
            } catch (IOException e3) {
            } finally {
                this.e = g.a();
            }
            return;
            if ((i2 & 1) == 1) {
                this.c = Collections.unmodifiableList(this.c);
            }
            try {
                a.a();
            } catch (IOException e4) {
                throw th;
            } finally {
                this.e = g.a();
            }
            throw th;
        }

        static {
            u uVar = new u();
            d = uVar;
            uVar.c = Collections.emptyList();
        }

        private int f() {
            return this.c.size();
        }

        public final boolean c() {
            byte b = this.f;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < f()) {
                if (((t) this.c.get(i)).c()) {
                    i++;
                } else {
                    this.f = (byte) 0;
                    return false;
                }
            }
            this.f = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            for (int i = 0; i < this.c.size(); i++) {
                dmVar.a(1, (du) this.c.get(i));
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = this.g;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (i = 0; i < this.c.size(); i++) {
                i2 += dm.b(1, (du) this.c.get(i));
            }
            int a = this.e.a() + i2;
            this.g = a;
            return a;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof u)) {
                return super.equals(obj);
            }
            if (this.c.equals(((u) obj).c)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = u.class.hashCode() + 779;
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.c.hashCode();
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static u a(byte[] bArr) {
            return (u) b.a(bArr);
        }

        public static a e() {
            return new a();
        }
    }

    public interface y extends dv {
    }

    public static final class x extends do implements y {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new x(dlVar);
            }
        };
        private static final x d;
        public ds c;
        private final dk e;
        private byte f;
        private int g;

        public static final class a extends com.tapjoy.internal.do.a implements y {
            public ds b = dr.a;
            private int c;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return g();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return g();
            }

            public final /* synthetic */ Object clone() {
                return g();
            }

            private a() {
            }

            public final x d() {
                x xVar = new x((com.tapjoy.internal.do.a) this);
                if ((this.c & 1) == 1) {
                    this.b = this.b.b();
                    this.c &= -2;
                }
                xVar.c = this.b;
                return xVar;
            }

            public final boolean c() {
                return true;
            }

            public final void e() {
                if ((this.c & 1) != 1) {
                    this.b = new dr(this.b);
                    this.c |= 1;
                }
            }

            private a g() {
                com.tapjoy.internal.do.a aVar = new a();
                x d = d();
                if (d != x.d()) {
                    if (!d.c.isEmpty()) {
                        if (aVar.b.isEmpty()) {
                            aVar.b = d.c;
                            aVar.c &= -2;
                        } else {
                            aVar.e();
                            aVar.b.addAll(d.c);
                        }
                    }
                    aVar.a = aVar.a.a(d.e);
                }
                return aVar;
            }
        }

        private x(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.f = (byte) -1;
            this.g = -1;
            this.e = aVar.a;
        }

        private x() {
            this.f = (byte) -1;
            this.g = -1;
            this.e = dk.b;
        }

        public static x d() {
            return d;
        }

        private x(dl dlVar) {
            Throwable th;
            int i = 0;
            this.f = (byte) -1;
            this.g = -1;
            this.c = dr.a;
            OutputStream g = dk.g();
            dm a = dm.a(g, 4096);
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int a2 = dlVar.a();
                    switch (a2) {
                        case TwitterResponse.NONE /*0*/:
                            i2 = 1;
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            dk d = dlVar.d();
                            if ((i & 1) != 1) {
                                this.c = new dr();
                                i |= 1;
                            }
                            this.c.a(d);
                            break;
                        default:
                            if (!dlVar.a(a2, a)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e) {
                    dq dqVar = e;
                    i2 = i;
                    dq dqVar2 = dqVar;
                    dqVar2.a = this;
                    throw dqVar2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    i2 = i;
                    dq dqVar3 = new dq(iOException.getMessage());
                    dqVar3.a = this;
                    throw dqVar3;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if ((i & 1) == 1) {
                this.c = this.c.b();
            }
            try {
                a.a();
            } catch (IOException e3) {
            } finally {
                this.e = g.a();
            }
            return;
            if ((i2 & 1) == 1) {
                this.c = this.c.b();
            }
            try {
                a.a();
            } catch (IOException e4) {
                throw th;
            } finally {
                this.e = g.a();
            }
            throw th;
        }

        static {
            x xVar = new x();
            d = xVar;
            xVar.c = dr.a;
        }

        public final boolean c() {
            byte b = this.f;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.f = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            b();
            for (int i = 0; i < this.c.size(); i++) {
                dmVar.a(1, this.c.a(i));
            }
            dmVar.c(this.e);
        }

        public final int b() {
            int i = 0;
            int i2 = this.g;
            if (i2 != -1) {
                return i2;
            }
            i2 = 0;
            while (i < this.c.size()) {
                i2 += dm.b(this.c.a(i));
                i++;
            }
            i = ((i2 + 0) + (this.c.size() * 1)) + this.e.a();
            this.g = i;
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof x)) {
                return super.equals(obj);
            }
            if (this.c.equals(((x) obj).c)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = x.class.hashCode() + 779;
            if (this.c.size() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.c.hashCode();
            }
            hashCode = (hashCode * 29) + this.e.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static x a(byte[] bArr) {
            return (x) b.a(bArr);
        }

        public static a e() {
            return new a();
        }
    }

    public static final class z extends do implements aa {
        public static dw b = new di() {
            public final /* synthetic */ Object a(dl dlVar, dn dnVar) {
                return new z(dlVar, dnVar);
            }
        };
        private static final z t;
        private Object A;
        private Object B;
        private Object C;
        private Object D;
        private Object E;
        private byte F;
        private int G;
        public long c;
        public int d;
        public int e;
        List f;
        public int g;
        public long h;
        public long i;
        public long j;
        public int k;
        public double l;
        public long m;
        public double n;
        boolean o;
        public int p;
        public int q;
        public ds r;
        boolean s;
        private final dk u;
        private int v;
        private Object w;
        private Object x;
        private Object y;
        private Object z;

        public static final class a extends com.tapjoy.internal.do.a implements aa {
            private Object A = BuildConfig.FLAVOR;
            private boolean B;
            public int b;
            public List c = Collections.emptyList();
            public long d;
            public double e;
            public Object f = BuildConfig.FLAVOR;
            public int g;
            public int h;
            public Object i = BuildConfig.FLAVOR;
            public Object j = BuildConfig.FLAVOR;
            public Object k = BuildConfig.FLAVOR;
            public Object l = BuildConfig.FLAVOR;
            public Object m = BuildConfig.FLAVOR;
            public ds n = dr.a;
            public boolean o;
            private long p;
            private Object q = BuildConfig.FLAVOR;
            private int r;
            private int s;
            private int t;
            private long u;
            private long v;
            private long w;
            private Object x = BuildConfig.FLAVOR;
            private int y;
            private double z;

            public final /* synthetic */ com.tapjoy.internal.dh.a a() {
                return A();
            }

            public final /* synthetic */ com.tapjoy.internal.do.a b() {
                return A();
            }

            public final /* synthetic */ Object clone() {
                return A();
            }

            private a() {
            }

            public final z d() {
                z zVar = new z((com.tapjoy.internal.do.a) this);
                int i = this.b;
                int i2 = 0;
                if ((i & 1) == 1) {
                    i2 = 1;
                }
                zVar.c = this.p;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                zVar.w = this.q;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                zVar.d = this.r;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                zVar.e = this.s;
                if ((this.b & 16) == 16) {
                    this.c = Collections.unmodifiableList(this.c);
                    this.b &= -17;
                }
                zVar.f = this.c;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                zVar.g = this.t;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                zVar.h = this.u;
                if ((i & 128) == 128) {
                    i2 |= 64;
                }
                zVar.i = this.v;
                if ((i & 256) == 256) {
                    i2 |= 128;
                }
                zVar.j = this.w;
                if ((i & 512) == 512) {
                    i2 |= 256;
                }
                zVar.x = this.x;
                if ((i & 1024) == 1024) {
                    i2 |= 512;
                }
                zVar.k = this.y;
                if ((i & 2048) == 2048) {
                    i2 |= 1024;
                }
                zVar.l = this.z;
                if ((i & 4096) == 4096) {
                    i2 |= 2048;
                }
                zVar.m = this.d;
                if ((i & 8192) == 8192) {
                    i2 |= 4096;
                }
                zVar.n = this.e;
                if ((i & 16384) == 16384) {
                    i2 |= 8192;
                }
                zVar.y = this.A;
                if ((i & 32768) == 32768) {
                    i2 |= 16384;
                }
                zVar.o = this.B;
                if ((i & 65536) == 65536) {
                    i2 |= 32768;
                }
                zVar.z = this.f;
                if ((i & 131072) == 131072) {
                    i2 |= 65536;
                }
                zVar.p = this.g;
                if ((i & 262144) == 262144) {
                    i2 |= 131072;
                }
                zVar.q = this.h;
                if ((i & 524288) == 524288) {
                    i2 |= 262144;
                }
                zVar.A = this.i;
                if ((1048576 & i) == 1048576) {
                    i2 |= 524288;
                }
                zVar.B = this.j;
                if ((2097152 & i) == 2097152) {
                    i2 |= 1048576;
                }
                zVar.C = this.k;
                if ((4194304 & i) == 4194304) {
                    i2 |= 2097152;
                }
                zVar.D = this.l;
                if ((8388608 & i) == 8388608) {
                    i2 |= 4194304;
                }
                zVar.E = this.m;
                if ((this.b & 16777216) == 16777216) {
                    this.n = this.n.b();
                    this.b &= -16777217;
                }
                zVar.r = this.n;
                if ((i & 33554432) == 33554432) {
                    i2 |= 8388608;
                }
                zVar.s = this.o;
                zVar.v = i2;
                return zVar;
            }

            public final a a(z zVar) {
                if (zVar != z.d()) {
                    if (zVar.e()) {
                        a(zVar.c);
                    }
                    if (zVar.f()) {
                        this.b |= 2;
                        this.q = zVar.w;
                    }
                    if (zVar.h()) {
                        a(zVar.d);
                    }
                    if (zVar.i()) {
                        b(zVar.e);
                    }
                    if (!zVar.f.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = zVar.f;
                            this.b &= -17;
                        } else {
                            e();
                            this.c.addAll(zVar.f);
                        }
                    }
                    if (zVar.k()) {
                        d(zVar.g);
                    }
                    if (zVar.l()) {
                        b(zVar.h);
                    }
                    if (zVar.m()) {
                        c(zVar.i);
                    }
                    if (zVar.n()) {
                        d(zVar.j);
                    }
                    if (zVar.o()) {
                        this.b |= 512;
                        this.x = zVar.x;
                    }
                    if (zVar.q()) {
                        e(zVar.k);
                    }
                    if (zVar.r()) {
                        a(zVar.l);
                    }
                    if (zVar.s()) {
                        e(zVar.m);
                    }
                    if (zVar.t()) {
                        b(zVar.n);
                    }
                    if (zVar.u()) {
                        this.b |= 16384;
                        this.A = zVar.y;
                    }
                    if (zVar.w()) {
                        a(zVar.o);
                    }
                    if (zVar.x()) {
                        this.b |= 65536;
                        this.f = zVar.z;
                    }
                    if (zVar.z()) {
                        f(zVar.p);
                    }
                    if (zVar.A()) {
                        g(zVar.q);
                    }
                    if (zVar.B()) {
                        this.b |= 524288;
                        this.i = zVar.A;
                    }
                    if (zVar.D()) {
                        this.b |= 1048576;
                        this.j = zVar.B;
                    }
                    if (zVar.F()) {
                        this.b |= 2097152;
                        this.k = zVar.C;
                    }
                    if (zVar.H()) {
                        this.b |= 4194304;
                        this.l = zVar.D;
                    }
                    if (zVar.J()) {
                        this.b |= 8388608;
                        this.m = zVar.E;
                    }
                    if (!zVar.r.isEmpty()) {
                        if (this.n.isEmpty()) {
                            this.n = zVar.r;
                            this.b &= -16777217;
                        } else {
                            B();
                            this.n.addAll(zVar.r);
                        }
                    }
                    if (zVar.M()) {
                        b(zVar.s);
                    }
                    this.a = this.a.a(zVar.u);
                }
                return this;
            }

            public final boolean c() {
                for (int i = 0; i < f(); i++) {
                    if (!c(i).c()) {
                        return false;
                    }
                }
                return true;
            }

            public final a a(long j) {
                this.b |= 1;
                this.p = j;
                return this;
            }

            public final a a(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 2;
                this.q = str;
                return this;
            }

            public final a a(int i) {
                this.b |= 4;
                this.r = i;
                return this;
            }

            public final a b(int i) {
                this.b |= 8;
                this.s = i;
                return this;
            }

            public final void e() {
                if ((this.b & 16) != 16) {
                    this.c = new ArrayList(this.c);
                    this.b |= 16;
                }
            }

            public final int f() {
                return this.c.size();
            }

            public final t c(int i) {
                return (t) this.c.get(i);
            }

            public final a d(int i) {
                this.b |= 32;
                this.t = i;
                return this;
            }

            public final a b(long j) {
                this.b |= 64;
                this.u = j;
                return this;
            }

            public final a c(long j) {
                this.b |= 128;
                this.v = j;
                return this;
            }

            public final a d(long j) {
                this.b |= 256;
                this.w = j;
                return this;
            }

            public final a b(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 512;
                this.x = str;
                return this;
            }

            public final a e(int i) {
                this.b |= 1024;
                this.y = i;
                return this;
            }

            public final a a(double d) {
                this.b |= 2048;
                this.z = d;
                return this;
            }

            public final a e(long j) {
                this.b |= 4096;
                this.d = j;
                return this;
            }

            public final a b(double d) {
                this.b |= 8192;
                this.e = d;
                return this;
            }

            public final a c(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 16384;
                this.A = str;
                return this;
            }

            public final a g() {
                this.b &= -16385;
                this.A = z.d().v();
                return this;
            }

            public final a a(boolean z) {
                this.b |= 32768;
                this.B = z;
                return this;
            }

            public final a h() {
                this.b &= -32769;
                this.B = false;
                return this;
            }

            public final boolean i() {
                return (this.b & 65536) == 65536;
            }

            public final a d(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 65536;
                this.f = str;
                return this;
            }

            public final a j() {
                this.b &= -65537;
                this.f = z.d().y();
                return this;
            }

            public final boolean k() {
                return (this.b & 131072) == 131072;
            }

            public final a f(int i) {
                this.b |= 131072;
                this.g = i;
                return this;
            }

            public final a l() {
                this.b &= -131073;
                this.g = 0;
                return this;
            }

            public final boolean m() {
                return (this.b & 262144) == 262144;
            }

            public final a g(int i) {
                this.b |= 262144;
                this.h = i;
                return this;
            }

            public final a n() {
                this.b &= -262145;
                this.h = 0;
                return this;
            }

            public final boolean o() {
                return (this.b & 524288) == 524288;
            }

            public final a e(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 524288;
                this.i = str;
                return this;
            }

            public final a p() {
                this.b &= -524289;
                this.i = z.d().C();
                return this;
            }

            public final boolean q() {
                return (this.b & 1048576) == 1048576;
            }

            public final a f(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 1048576;
                this.j = str;
                return this;
            }

            public final a r() {
                this.b &= -1048577;
                this.j = z.d().E();
                return this;
            }

            public final boolean s() {
                return (this.b & 2097152) == 2097152;
            }

            public final a g(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 2097152;
                this.k = str;
                return this;
            }

            public final a t() {
                this.b &= -2097153;
                this.k = z.d().G();
                return this;
            }

            public final boolean u() {
                return (this.b & 4194304) == 4194304;
            }

            public final a h(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 4194304;
                this.l = str;
                return this;
            }

            public final a v() {
                this.b &= -4194305;
                this.l = z.d().I();
                return this;
            }

            public final boolean w() {
                return (this.b & 8388608) == 8388608;
            }

            public final a i(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.b |= 8388608;
                this.m = str;
                return this;
            }

            public final a x() {
                this.b &= -8388609;
                this.m = z.d().K();
                return this;
            }

            private void B() {
                if ((this.b & 16777216) != 16777216) {
                    this.n = new dr(this.n);
                    this.b |= 16777216;
                }
            }

            public final a a(Iterable iterable) {
                B();
                com.tapjoy.internal.dh.a.a(iterable, this.n);
                return this;
            }

            public final a y() {
                this.n = dr.a;
                this.b &= -16777217;
                return this;
            }

            public final a b(boolean z) {
                this.b |= 33554432;
                this.o = z;
                return this;
            }

            private a A() {
                return new a().a(d());
            }
        }

        private z(com.tapjoy.internal.do.a aVar) {
            super((byte) 0);
            this.F = (byte) -1;
            this.G = -1;
            this.u = aVar.a;
        }

        private z() {
            this.F = (byte) -1;
            this.G = -1;
            this.u = dk.b;
        }

        public static z d() {
            return t;
        }

        private z(dl dlVar, dn dnVar) {
            int a;
            dq e;
            IOException e2;
            int i = 0;
            this.F = (byte) -1;
            this.G = -1;
            X();
            OutputStream g = dk.g();
            dm a2 = dm.a(g, 4096);
            int i2 = 0;
            while (i2 == 0) {
                try {
                    a = dlVar.a();
                    dk d;
                    switch (a) {
                        case TwitterResponse.NONE /*0*/:
                            i2 = 1;
                            break;
                        case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                            this.v |= 1;
                            this.c = dlVar.f();
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*18*/:
                            d = dlVar.d();
                            this.v |= 2;
                            this.w = d;
                            break;
                        case R.styleable.Toolbar_navigationIcon /*24*/:
                            this.v |= 512;
                            this.k = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_actionModeCutDrawable /*32*/:
                            this.v |= 2048;
                            this.m = dlVar.f();
                            break;
                        case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                            d = dlVar.d();
                            this.v |= 32768;
                            this.z = d;
                            break;
                        case R.styleable.AppCompatTheme_spinnerDropDownItemStyle /*48*/:
                            this.v |= 65536;
                            this.p = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_dividerVertical /*56*/:
                            this.v |= 131072;
                            this.q = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
                            d = dlVar.d();
                            this.v |= 262144;
                            this.A = d;
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemPaddingRight /*74*/:
                            d = dlVar.d();
                            this.v |= 524288;
                            this.B = d;
                            break;
                        case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator /*82*/:
                            d = dlVar.d();
                            this.v |= 1048576;
                            this.C = d;
                            break;
                        case R.styleable.AppCompatTheme_colorSwitchThumbNormal /*90*/:
                            d = dlVar.d();
                            this.v |= 2097152;
                            this.D = d;
                            break;
                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                            d = dlVar.d();
                            this.v |= 4194304;
                            this.E = d;
                            break;
                        case R.styleable.AppCompatTheme_checkboxStyle /*104*/:
                            this.v |= 4;
                            this.d = dlVar.e();
                            break;
                        case R.styleable.AppCompatTheme_spinnerStyle /*112*/:
                            this.v |= 8;
                            this.e = dlVar.e();
                            break;
                        case 122:
                            if ((i & 16) != 16) {
                                this.f = new ArrayList();
                                a = i | 16;
                            } else {
                                a = i;
                            }
                            try {
                                this.f.add(dlVar.a(t.b, dnVar));
                                i = a;
                                break;
                            } catch (dq e3) {
                                e = e3;
                                break;
                            } catch (IOException e4) {
                                e2 = e4;
                                break;
                            }
                        case 128:
                            this.v |= 16;
                            this.g = dlVar.e();
                            break;
                        case 136:
                            this.v |= 32;
                            this.h = dlVar.b();
                            break;
                        case 144:
                            this.v |= 64;
                            this.i = dlVar.b();
                            break;
                        case 152:
                            this.v |= 128;
                            this.j = dlVar.b();
                            break;
                        case 162:
                            d = dlVar.d();
                            this.v |= 256;
                            this.x = d;
                            break;
                        case 169:
                            this.v |= 1024;
                            this.l = Double.longBitsToDouble(dlVar.g());
                            break;
                        case 177:
                            this.v |= 4096;
                            this.n = Double.longBitsToDouble(dlVar.g());
                            break;
                        case 186:
                            d = dlVar.d();
                            this.v |= 8192;
                            this.y = d;
                            break;
                        case 192:
                            this.v |= 16384;
                            this.o = dlVar.c();
                            break;
                        case HttpResponseCode.OK /*200*/:
                            this.v |= 8388608;
                            this.s = dlVar.c();
                            break;
                        case 210:
                            d = dlVar.d();
                            if ((i & 16777216) != 16777216) {
                                this.r = new dr();
                                i |= 16777216;
                            }
                            this.r.a(d);
                            break;
                        default:
                            if (!dlVar.a(a, a2)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (dq e5) {
                    dq dqVar = e5;
                    a = i;
                    e = dqVar;
                } catch (IOException e6) {
                    IOException iOException = e6;
                    a = i;
                    e2 = iOException;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    a = i;
                    Throwable th3 = th2;
                }
            }
            if ((i & 16) == 16) {
                this.f = Collections.unmodifiableList(this.f);
            }
            if ((i & 16777216) == 16777216) {
                this.r = this.r.b();
            }
            try {
                a2.a();
            } catch (IOException e7) {
            } finally {
                this.u = g.a();
            }
            return;
            dq dqVar2 = new dq(e2.getMessage());
            dqVar2.a = this;
            throw dqVar2;
            try {
                e.a = this;
                throw e;
            } catch (Throwable th4) {
                th3 = th4;
                if ((a & 16) == 16) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                if ((a & 16777216) == 16777216) {
                    this.r = this.r.b();
                }
                try {
                    a2.a();
                } catch (IOException e8) {
                    throw th3;
                } finally {
                    this.u = g.a();
                }
                throw th3;
            }
        }

        static {
            z zVar = new z();
            t = zVar;
            zVar.X();
        }

        public final boolean e() {
            return (this.v & 1) == 1;
        }

        public final boolean f() {
            return (this.v & 2) == 2;
        }

        public final String g() {
            Object obj = this.w;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.w = e;
            }
            return e;
        }

        private dk O() {
            Object obj = this.w;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.w = a;
            return a;
        }

        public final boolean h() {
            return (this.v & 4) == 4;
        }

        public final boolean i() {
            return (this.v & 8) == 8;
        }

        public final int j() {
            return this.f.size();
        }

        public final boolean k() {
            return (this.v & 16) == 16;
        }

        public final boolean l() {
            return (this.v & 32) == 32;
        }

        public final boolean m() {
            return (this.v & 64) == 64;
        }

        public final boolean n() {
            return (this.v & 128) == 128;
        }

        public final boolean o() {
            return (this.v & 256) == 256;
        }

        public final String p() {
            Object obj = this.x;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.x = e;
            }
            return e;
        }

        private dk P() {
            Object obj = this.x;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.x = a;
            return a;
        }

        public final boolean q() {
            return (this.v & 512) == 512;
        }

        public final boolean r() {
            return (this.v & 1024) == 1024;
        }

        public final boolean s() {
            return (this.v & 2048) == 2048;
        }

        public final boolean t() {
            return (this.v & 4096) == 4096;
        }

        public final boolean u() {
            return (this.v & 8192) == 8192;
        }

        public final String v() {
            Object obj = this.y;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.y = e;
            }
            return e;
        }

        private dk Q() {
            Object obj = this.y;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.y = a;
            return a;
        }

        public final boolean w() {
            return (this.v & 16384) == 16384;
        }

        public final boolean x() {
            return (this.v & 32768) == 32768;
        }

        public final String y() {
            Object obj = this.z;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.z = e;
            }
            return e;
        }

        private dk R() {
            Object obj = this.z;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.z = a;
            return a;
        }

        public final boolean z() {
            return (this.v & 65536) == 65536;
        }

        public final boolean A() {
            return (this.v & 131072) == 131072;
        }

        public final boolean B() {
            return (this.v & 262144) == 262144;
        }

        public final String C() {
            Object obj = this.A;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.A = e;
            }
            return e;
        }

        private dk S() {
            Object obj = this.A;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.A = a;
            return a;
        }

        public final boolean D() {
            return (this.v & 524288) == 524288;
        }

        public final String E() {
            Object obj = this.B;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.B = e;
            }
            return e;
        }

        private dk T() {
            Object obj = this.B;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.B = a;
            return a;
        }

        public final boolean F() {
            return (this.v & 1048576) == 1048576;
        }

        public final String G() {
            Object obj = this.C;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.C = e;
            }
            return e;
        }

        private dk U() {
            Object obj = this.C;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.C = a;
            return a;
        }

        public final boolean H() {
            return (this.v & 2097152) == 2097152;
        }

        public final String I() {
            Object obj = this.D;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.D = e;
            }
            return e;
        }

        private dk V() {
            Object obj = this.D;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.D = a;
            return a;
        }

        public final boolean J() {
            return (this.v & 4194304) == 4194304;
        }

        public final String K() {
            Object obj = this.E;
            if (obj instanceof String) {
                return (String) obj;
            }
            dk dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                this.E = e;
            }
            return e;
        }

        private dk W() {
            Object obj = this.E;
            if (!(obj instanceof String)) {
                return (dk) obj;
            }
            dk a = dk.a((String) obj);
            this.E = a;
            return a;
        }

        public final int L() {
            return this.r.size();
        }

        public final boolean M() {
            return (this.v & 8388608) == 8388608;
        }

        private void X() {
            this.c = 0;
            this.w = BuildConfig.FLAVOR;
            this.d = 0;
            this.e = 0;
            this.f = Collections.emptyList();
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.x = BuildConfig.FLAVOR;
            this.k = 0;
            this.l = 0.0d;
            this.m = 0;
            this.n = 0.0d;
            this.y = BuildConfig.FLAVOR;
            this.o = false;
            this.z = BuildConfig.FLAVOR;
            this.p = 0;
            this.q = 0;
            this.A = BuildConfig.FLAVOR;
            this.B = BuildConfig.FLAVOR;
            this.C = BuildConfig.FLAVOR;
            this.D = BuildConfig.FLAVOR;
            this.E = BuildConfig.FLAVOR;
            this.r = dr.a;
            this.s = false;
        }

        public final boolean c() {
            byte b = this.F;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < j()) {
                if (((t) this.f.get(i)).c()) {
                    i++;
                } else {
                    this.F = (byte) 0;
                    return false;
                }
            }
            this.F = (byte) 1;
            return true;
        }

        public final void a(dm dmVar) {
            int i = 0;
            b();
            if ((this.v & 1) == 1) {
                dmVar.a(1, this.c);
            }
            if ((this.v & 2) == 2) {
                dmVar.a(2, O());
            }
            if ((this.v & 512) == 512) {
                dmVar.a(3, this.k);
            }
            if ((this.v & 2048) == 2048) {
                dmVar.a(4, this.m);
            }
            if ((this.v & 32768) == 32768) {
                dmVar.a(5, R());
            }
            if ((this.v & 65536) == 65536) {
                dmVar.a(6, this.p);
            }
            if ((this.v & 131072) == 131072) {
                dmVar.a(7, this.q);
            }
            if ((this.v & 262144) == 262144) {
                dmVar.a(8, S());
            }
            if ((this.v & 524288) == 524288) {
                dmVar.a(9, T());
            }
            if ((this.v & 1048576) == 1048576) {
                dmVar.a(10, U());
            }
            if ((this.v & 2097152) == 2097152) {
                dmVar.a(11, V());
            }
            if ((this.v & 4194304) == 4194304) {
                dmVar.a(12, W());
            }
            if ((this.v & 4) == 4) {
                dmVar.a(13, this.d);
            }
            if ((this.v & 8) == 8) {
                dmVar.a(14, this.e);
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                dmVar.a(15, (du) this.f.get(i2));
            }
            if ((this.v & 16) == 16) {
                dmVar.a(16, this.g);
            }
            if ((this.v & 32) == 32) {
                dmVar.a(17, this.h);
            }
            if ((this.v & 64) == 64) {
                dmVar.a(18, this.i);
            }
            if ((this.v & 128) == 128) {
                dmVar.a(19, this.j);
            }
            if ((this.v & 256) == 256) {
                dmVar.a(20, P());
            }
            if ((this.v & 1024) == 1024) {
                dmVar.a(21, this.l);
            }
            if ((this.v & 4096) == 4096) {
                dmVar.a(22, this.n);
            }
            if ((this.v & 8192) == 8192) {
                dmVar.a(23, Q());
            }
            if ((this.v & 16384) == 16384) {
                dmVar.a(24, this.o);
            }
            if ((this.v & 8388608) == 8388608) {
                dmVar.a(25, this.s);
            }
            while (i < this.r.size()) {
                dmVar.a(26, this.r.a(i));
                i++;
            }
            dmVar.c(this.u);
        }

        public final int b() {
            int i = 0;
            int i2 = this.G;
            if (i2 != -1) {
                return i2;
            }
            if ((this.v & 1) == 1) {
                i2 = dm.b(1, this.c) + 0;
            } else {
                i2 = 0;
            }
            if ((this.v & 2) == 2) {
                i2 += dm.b(2, O());
            }
            if ((this.v & 512) == 512) {
                i2 += dm.b(3, this.k);
            }
            if ((this.v & 2048) == 2048) {
                i2 += dm.b(4, this.m);
            }
            if ((this.v & 32768) == 32768) {
                i2 += dm.b(5, R());
            }
            if ((this.v & 65536) == 65536) {
                i2 += dm.b(6, this.p);
            }
            if ((this.v & 131072) == 131072) {
                i2 += dm.b(7, this.q);
            }
            if ((this.v & 262144) == 262144) {
                i2 += dm.b(8, S());
            }
            if ((this.v & 524288) == 524288) {
                i2 += dm.b(9, T());
            }
            if ((this.v & 1048576) == 1048576) {
                i2 += dm.b(10, U());
            }
            if ((this.v & 2097152) == 2097152) {
                i2 += dm.b(11, V());
            }
            if ((this.v & 4194304) == 4194304) {
                i2 += dm.b(12, W());
            }
            if ((this.v & 4) == 4) {
                i2 += dm.b(13, this.d);
            }
            if ((this.v & 8) == 8) {
                i2 += dm.b(14, this.e);
            }
            int i3 = i2;
            for (int i4 = 0; i4 < this.f.size(); i4++) {
                i3 += dm.b(15, (du) this.f.get(i4));
            }
            if ((this.v & 16) == 16) {
                i3 += dm.b(16, this.g);
            }
            if ((this.v & 32) == 32) {
                i3 += dm.b(17, this.h);
            }
            if ((this.v & 64) == 64) {
                i3 += dm.b(18, this.i);
            }
            if ((this.v & 128) == 128) {
                i3 += dm.b(19, this.j);
            }
            if ((this.v & 256) == 256) {
                i3 += dm.b(20, P());
            }
            if ((this.v & 1024) == 1024) {
                i3 += dm.d(21) + 8;
            }
            if ((this.v & 4096) == 4096) {
                i3 += dm.d(22) + 8;
            }
            if ((this.v & 8192) == 8192) {
                i3 += dm.b(23, Q());
            }
            if ((this.v & 16384) == 16384) {
                i3 += dm.d(24) + 1;
            }
            if ((this.v & 8388608) == 8388608) {
                i3 += dm.d(25) + 1;
            }
            i2 = 0;
            while (i < this.r.size()) {
                i2 += dm.b(this.r.a(i));
                i++;
            }
            i2 = ((i2 + i3) + (this.r.size() * 2)) + this.u.a();
            this.G = i2;
            return i2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof z)) {
                return super.equals(obj);
            }
            z zVar = (z) obj;
            boolean z = e() == zVar.e();
            if (e()) {
                z = z && this.c == zVar.c;
            }
            if (z && f() == zVar.f()) {
                z = true;
            } else {
                z = false;
            }
            if (f()) {
                if (z && g().equals(zVar.g())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && h() == zVar.h()) {
                z = true;
            } else {
                z = false;
            }
            if (h()) {
                if (z && this.d == zVar.d) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && i() == zVar.i()) {
                z = true;
            } else {
                z = false;
            }
            if (i()) {
                if (z && this.e == zVar.e) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.f.equals(zVar.f)) {
                z = true;
            } else {
                z = false;
            }
            if (z && k() == zVar.k()) {
                z = true;
            } else {
                z = false;
            }
            if (k()) {
                if (z && this.g == zVar.g) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && l() == zVar.l()) {
                z = true;
            } else {
                z = false;
            }
            if (l()) {
                if (z && this.h == zVar.h) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && m() == zVar.m()) {
                z = true;
            } else {
                z = false;
            }
            if (m()) {
                if (z && this.i == zVar.i) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && n() == zVar.n()) {
                z = true;
            } else {
                z = false;
            }
            if (n()) {
                if (z && this.j == zVar.j) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && o() == zVar.o()) {
                z = true;
            } else {
                z = false;
            }
            if (o()) {
                if (z && p().equals(zVar.p())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && q() == zVar.q()) {
                z = true;
            } else {
                z = false;
            }
            if (q()) {
                if (z && this.k == zVar.k) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && r() == zVar.r()) {
                z = true;
            } else {
                z = false;
            }
            if (r()) {
                if (z && Double.doubleToLongBits(this.l) == Double.doubleToLongBits(zVar.l)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && s() == zVar.s()) {
                z = true;
            } else {
                z = false;
            }
            if (s()) {
                if (z && this.m == zVar.m) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && t() == zVar.t()) {
                z = true;
            } else {
                z = false;
            }
            if (t()) {
                if (z && Double.doubleToLongBits(this.n) == Double.doubleToLongBits(zVar.n)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && u() == zVar.u()) {
                z = true;
            } else {
                z = false;
            }
            if (u()) {
                if (z && v().equals(zVar.v())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && w() == zVar.w()) {
                z = true;
            } else {
                z = false;
            }
            if (w()) {
                if (z && this.o == zVar.o) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && x() == zVar.x()) {
                z = true;
            } else {
                z = false;
            }
            if (x()) {
                if (z && y().equals(zVar.y())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && z() == zVar.z()) {
                z = true;
            } else {
                z = false;
            }
            if (z()) {
                if (z && this.p == zVar.p) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && A() == zVar.A()) {
                z = true;
            } else {
                z = false;
            }
            if (A()) {
                if (z && this.q == zVar.q) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && B() == zVar.B()) {
                z = true;
            } else {
                z = false;
            }
            if (B()) {
                if (z && C().equals(zVar.C())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && D() == zVar.D()) {
                z = true;
            } else {
                z = false;
            }
            if (D()) {
                if (z && E().equals(zVar.E())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && F() == zVar.F()) {
                z = true;
            } else {
                z = false;
            }
            if (F()) {
                if (z && G().equals(zVar.G())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && H() == zVar.H()) {
                z = true;
            } else {
                z = false;
            }
            if (H()) {
                if (z && I().equals(zVar.I())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && J() == zVar.J()) {
                z = true;
            } else {
                z = false;
            }
            if (J()) {
                if (z && K().equals(zVar.K())) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z && this.r.equals(zVar.r)) {
                z = true;
            } else {
                z = false;
            }
            if (z && M() == zVar.M()) {
                z = true;
            } else {
                z = false;
            }
            if (!M()) {
                return z;
            }
            if (z && this.s == zVar.s) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            if (this.a != 0) {
                return this.a;
            }
            int hashCode = z.class.hashCode() + 779;
            if (e()) {
                hashCode = (((hashCode * 37) + 1) * 53) + dp.a(this.c);
            }
            if (f()) {
                hashCode = (((hashCode * 37) + 2) * 53) + g().hashCode();
            }
            if (h()) {
                hashCode = (((hashCode * 37) + 13) * 53) + this.d;
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 14) * 53) + this.e;
            }
            if (j() > 0) {
                hashCode = (((hashCode * 37) + 15) * 53) + this.f.hashCode();
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 16) * 53) + this.g;
            }
            if (l()) {
                hashCode = (((hashCode * 37) + 17) * 53) + dp.a(this.h);
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 18) * 53) + dp.a(this.i);
            }
            if (n()) {
                hashCode = (((hashCode * 37) + 19) * 53) + dp.a(this.j);
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 20) * 53) + p().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 3) * 53) + this.k;
            }
            if (r()) {
                hashCode = (((hashCode * 37) + 21) * 53) + dp.a(Double.doubleToLongBits(this.l));
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 4) * 53) + dp.a(this.m);
            }
            if (t()) {
                hashCode = (((hashCode * 37) + 22) * 53) + dp.a(Double.doubleToLongBits(this.n));
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 23) * 53) + v().hashCode();
            }
            if (w()) {
                hashCode = (((hashCode * 37) + 24) * 53) + dp.a(this.o);
            }
            if (x()) {
                hashCode = (((hashCode * 37) + 5) * 53) + y().hashCode();
            }
            if (z()) {
                hashCode = (((hashCode * 37) + 6) * 53) + this.p;
            }
            if (A()) {
                hashCode = (((hashCode * 37) + 7) * 53) + this.q;
            }
            if (B()) {
                hashCode = (((hashCode * 37) + 8) * 53) + C().hashCode();
            }
            if (D()) {
                hashCode = (((hashCode * 37) + 9) * 53) + E().hashCode();
            }
            if (F()) {
                hashCode = (((hashCode * 37) + 10) * 53) + G().hashCode();
            }
            if (H()) {
                hashCode = (((hashCode * 37) + 11) * 53) + I().hashCode();
            }
            if (J()) {
                hashCode = (((hashCode * 37) + 12) * 53) + K().hashCode();
            }
            if (L() > 0) {
                hashCode = (((hashCode * 37) + 26) * 53) + this.r.hashCode();
            }
            if (M()) {
                hashCode = (((hashCode * 37) + 25) * 53) + dp.a(this.s);
            }
            hashCode = (hashCode * 29) + this.u.hashCode();
            this.a = hashCode;
            return hashCode;
        }

        public static a N() {
            return new a();
        }

        public static a a(z zVar) {
            return new a().a(zVar);
        }
    }
}
