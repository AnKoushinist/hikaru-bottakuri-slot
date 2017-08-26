package com.vungle.publisher;

import com.vungle.publisher.ahp.a;
import com.vungle.publisher.ahp.b;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: vungle */
public final class alp {
    static volatile aif<Throwable> a = new aif<Throwable>() {
        public final /* synthetic */ void a(Object obj) {
            als.a().b();
        }
    };
    static volatile aii<a, a> b = new aii<a, a>() {
        public final /* synthetic */ Object a(Object obj) {
            a aVar = (a) obj;
            als.a().c();
            return alq.a(aVar);
        }
    };
    static volatile aii<aht.a, aht.a> c = new aii<aht.a, aht.a>() {
        public final /* synthetic */ Object a(Object obj) {
            aht.a aVar = (aht.a) obj;
            als.a().d();
            return alu.a(aVar);
        }
    };
    static volatile aii<ahn.a, ahn.a> d = new aii<ahn.a, ahn.a>() {
        public final /* synthetic */ Object a(Object obj) {
            ahn.a aVar = (ahn.a) obj;
            als.a().e();
            return aln.a(aVar);
        }
    };
    static volatile aij<ahp, a, a> e = new aij<ahp, a, a>() {
        public final /* synthetic */ Object a(Object obj, Object obj2) {
            a aVar = (a) obj2;
            als.a().c();
            return alq.b(aVar);
        }
    };
    static volatile aij<aht, a, a> f = new aij<aht, a, a>() {
        public final /* synthetic */ Object a(Object obj, Object obj2) {
            a aVar = (a) obj2;
            als.a().d();
            return alu.a(aVar);
        }
    };
    static volatile aij<ahn, ahn.a, ahn.a> g = new aij<ahn, ahn.a, ahn.a>() {
        public final /* synthetic */ Object a(Object obj, Object obj2) {
            ahn.a aVar = (ahn.a) obj2;
            als.a().e();
            return aln.b(aVar);
        }
    };
    static volatile aii<ahs, ahs> h;
    static volatile aii<ahs, ahs> i;
    static volatile aii<aie, aie> j = new aii<aie, aie>() {
        public final /* synthetic */ Object a(Object obj) {
            aie com_vungle_publisher_aie = (aie) obj;
            als.a().f();
            return alt.a(com_vungle_publisher_aie);
        }
    };
    static volatile aii<ahv, ahv> k = new aii<ahv, ahv>() {
        public final /* synthetic */ Object a(Object obj) {
            ahv com_vungle_publisher_ahv = (ahv) obj;
            als.a().c();
            return alq.a(com_vungle_publisher_ahv);
        }
    };
    static volatile aii<ahv, ahv> l = new aii<ahv, ahv>() {
        public final /* synthetic */ Object a(Object obj) {
            ahv com_vungle_publisher_ahv = (ahv) obj;
            als.a().d();
            return alu.a(com_vungle_publisher_ahv);
        }
    };
    static volatile aih<? extends ScheduledExecutorService> m;
    static volatile aii<Throwable, Throwable> n = new aii<Throwable, Throwable>() {
        public final /* synthetic */ Object a(Object obj) {
            Throwable th = (Throwable) obj;
            als.a().c();
            return alq.a(th);
        }
    };
    static volatile aii<Throwable, Throwable> o = new aii<Throwable, Throwable>() {
        public final /* synthetic */ Object a(Object obj) {
            Throwable th = (Throwable) obj;
            als.a().d();
            return alu.a(th);
        }
    };
    static volatile aii<Throwable, Throwable> p = new aii<Throwable, Throwable>() {
        public final /* synthetic */ Object a(Object obj) {
            Throwable th = (Throwable) obj;
            als.a().e();
            return aln.a(th);
        }
    };
    static volatile aii<b, b> q = new aii<b, b>() {
        public final /* synthetic */ Object a(Object obj) {
            b bVar = (b) obj;
            als.a().c();
            return alq.a(bVar);
        }
    };
    static volatile aii<b, b> r = new aii<b, b>() {
        public final /* synthetic */ Object a(Object obj) {
            b bVar = (b) obj;
            als.a().d();
            return alu.a(bVar);
        }
    };
    static volatile aii<ahn.b, ahn.b> s = new aii<ahn.b, ahn.b>() {
        public final /* synthetic */ Object a(Object obj) {
            ahn.b bVar = (ahn.b) obj;
            als.a().e();
            return aln.a(bVar);
        }
    };

    public static void a(Throwable th) {
        aif com_vungle_publisher_aif = a;
        if (com_vungle_publisher_aif != null) {
            try {
                com_vungle_publisher_aif.a(th);
                return;
            } catch (Throwable th2) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                c(th2);
            }
        }
        c(th);
    }

    private static void c(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static <T> a<T> a(a<T> aVar) {
        aii com_vungle_publisher_aii = b;
        if (com_vungle_publisher_aii != null) {
            return (a) com_vungle_publisher_aii.a(aVar);
        }
        return aVar;
    }

    public static ahs a(ahs com_vungle_publisher_ahs) {
        aii com_vungle_publisher_aii = h;
        if (com_vungle_publisher_aii != null) {
            return (ahs) com_vungle_publisher_aii.a(com_vungle_publisher_ahs);
        }
        return com_vungle_publisher_ahs;
    }

    public static ahs b(ahs com_vungle_publisher_ahs) {
        aii com_vungle_publisher_aii = i;
        if (com_vungle_publisher_aii != null) {
            return (ahs) com_vungle_publisher_aii.a(com_vungle_publisher_ahs);
        }
        return com_vungle_publisher_ahs;
    }

    public static aie a(aie com_vungle_publisher_aie) {
        aii com_vungle_publisher_aii = j;
        if (com_vungle_publisher_aii != null) {
            return (aie) com_vungle_publisher_aii.a(com_vungle_publisher_aie);
        }
        return com_vungle_publisher_aie;
    }

    public static <T> a<T> a(ahp<T> com_vungle_publisher_ahp_T, a<T> aVar) {
        aij com_vungle_publisher_aij = e;
        if (com_vungle_publisher_aij != null) {
            return (a) com_vungle_publisher_aij.a(com_vungle_publisher_ahp_T, aVar);
        }
        return aVar;
    }

    public static ahv a(ahv com_vungle_publisher_ahv) {
        aii com_vungle_publisher_aii = k;
        if (com_vungle_publisher_aii != null) {
            return (ahv) com_vungle_publisher_aii.a(com_vungle_publisher_ahv);
        }
        return com_vungle_publisher_ahv;
    }

    public static Throwable b(Throwable th) {
        aii com_vungle_publisher_aii = n;
        if (com_vungle_publisher_aii != null) {
            return (Throwable) com_vungle_publisher_aii.a(th);
        }
        return th;
    }

    public static <T, R> b<R, T> a(b<R, T> bVar) {
        aii com_vungle_publisher_aii = q;
        if (com_vungle_publisher_aii != null) {
            return (b) com_vungle_publisher_aii.a(bVar);
        }
        return bVar;
    }

    public static aih<? extends ScheduledExecutorService> a() {
        return m;
    }
}
