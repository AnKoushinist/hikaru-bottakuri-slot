package com.vungle.publisher;

/* compiled from: vungle */
public final class ajv<T> implements ahq<T> {
    final aif<? super T> a;
    final aif<Throwable> b;
    final aie c;

    public ajv(aif<? super T> com_vungle_publisher_aif__super_T, aif<Throwable> com_vungle_publisher_aif_java_lang_Throwable, aie com_vungle_publisher_aie) {
        this.a = com_vungle_publisher_aif__super_T;
        this.b = com_vungle_publisher_aif_java_lang_Throwable;
        this.c = com_vungle_publisher_aie;
    }

    public final void a(T t) {
        this.a.a(t);
    }

    public final void a(Throwable th) {
        this.b.a(th);
    }

    public final void a() {
        this.c.d();
    }
}
