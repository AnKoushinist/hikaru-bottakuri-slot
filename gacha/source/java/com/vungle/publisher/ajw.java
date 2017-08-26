package com.vungle.publisher;

import java.util.List;

/* compiled from: vungle */
public enum ajw {
    ;
    
    public static final e a = null;
    public static final c b = null;
    public static final h c = null;
    static final g d = null;
    public static final d e = null;
    static final b f = null;
    public static final aif<Throwable> g = null;
    public static final com.vungle.publisher.ahp.b<Boolean, Object> h = null;

    /* compiled from: vungle */
    static final class a implements aif<Throwable> {
        a() {
        }

        public final /* synthetic */ void a(Object obj) {
            throw new aib((Throwable) obj);
        }
    }

    /* compiled from: vungle */
    static final class b implements aii<aho<?>, Throwable> {
        b() {
        }

        public final /* bridge */ /* synthetic */ Object a(Object obj) {
            return ((aho) obj).b;
        }
    }

    /* compiled from: vungle */
    public static final class c implements aij<Object, Object, Boolean> {
        c() {
        }

        public final /* synthetic */ Object a(Object obj, Object obj2) {
            boolean z = obj == obj2 || (obj != null && obj.equals(obj2));
            return Boolean.valueOf(z);
        }
    }

    /* compiled from: vungle */
    public static final class d implements aij<Integer, Object, Integer> {
        d() {
        }

        public final /* synthetic */ Object a(Object obj, Object obj2) {
            return Integer.valueOf(((Integer) obj).intValue() + 1);
        }
    }

    /* compiled from: vungle */
    public static final class e implements aij<Long, Object, Long> {
        e() {
        }

        public final /* synthetic */ Object a(Object obj, Object obj2) {
            return Long.valueOf(((Long) obj).longValue() + 1);
        }
    }

    /* compiled from: vungle */
    static final class f implements aii<ahp<? extends aho<?>>, ahp<?>> {
        final aii<? super ahp<? extends Throwable>, ? extends ahp<?>> a;

        public final /* synthetic */ Object a(Object obj) {
            return (ahp) this.a.a(((ahp) obj).b(ajw.f));
        }

        public f(aii<? super ahp<? extends Throwable>, ? extends ahp<?>> com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_) {
            this.a = com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_;
        }
    }

    /* compiled from: vungle */
    static final class g implements aii<Object, Void> {
        g() {
        }

        public final /* bridge */ /* synthetic */ Object a(Object obj) {
            return null;
        }
    }

    /* compiled from: vungle */
    public static final class h implements aii<List<? extends ahp<?>>, ahp<?>[]> {
        h() {
        }

        public final /* synthetic */ Object a(Object obj) {
            List list = (List) obj;
            return (ahp[]) list.toArray(new ahp[list.size()]);
        }
    }

    static {
        a = new e();
        b = new c();
        c = new h();
        d = new g();
        e = new d();
        f = new b();
        g = new a();
        h = new aiy(a.a);
    }

    public static aii<ahp<? extends aho<?>>, ahp<?>> a(aii<? super ahp<? extends Throwable>, ? extends ahp<?>> com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_) {
        return new f(com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_);
    }
}
