package com.vungle.publisher;

import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class yg<O extends ace, T extends cq<?, ?, ?, ?>> extends yf {
    cj e;
    Integer f;
    O g;

    /* compiled from: vungle */
    public static abstract class a<O extends ace, H extends yg<O, T>, T extends cq<T, ?, ?, ?>> extends com.vungle.publisher.yf.a<H> {
        protected abstract com.vungle.publisher.ace.a<?, O, T, ?> d();

        protected abstract H e();

        protected a() {
        }

        protected /* synthetic */ vs b() {
            return e();
        }

        public final H a(T t) {
            try {
                yg ygVar = (yg) a();
                ygVar.e = t == null ? null : t.h();
                ygVar.b = this.d + "reportAd";
                ygVar.a("Content-Encoding", "gzip");
                ygVar.a("Content-Type", "application/json");
                ygVar.f = (Integer) t.w();
                ygVar.b = this.d + "reportAd";
                ace a = d().a(t);
                ygVar.g = a;
                if (a != null) {
                    ygVar.d = a.d();
                }
                return ygVar;
            } catch (Throwable e) {
                throw new sd(e);
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b {
        @Inject
        a a;
        @Inject
        a b;
        @Inject
        a c;
        @Inject
        a d;

        /* compiled from: vungle */
        class AnonymousClass1 extends o<yg<?, ?>> {
            final /* synthetic */ cq a;
            final /* synthetic */ b b;

            AnonymousClass1(b bVar, cq cqVar) {
                this.b = bVar;
                this.a = cqVar;
            }

            protected final /* bridge */ /* synthetic */ Object a() {
                return this.b.a.a((fj) this.a);
            }

            protected final /* synthetic */ Object b() {
                return this.b.b.a((if) this.a);
            }

            protected final /* synthetic */ Object c() {
                return this.b.c.a((kn) this.a);
            }

            protected final /* synthetic */ Object d() {
                return this.b.d.a((hd) this.a);
            }
        }

        @Inject
        b() {
        }
    }

    protected yg() {
    }

    protected final c a() {
        return c.reportAd;
    }

    protected final com.vungle.publisher.vs.b b() {
        return com.vungle.publisher.vs.b.POST;
    }
}
