package com.vungle.publisher;

import com.vungle.publisher.ahp.a;

/* compiled from: vungle */
public class aly<T, R> extends alz<T, R> {
    private final all<T> b;
    private final alz<T, R> c;

    /* compiled from: vungle */
    class AnonymousClass1 implements a<R> {
        final /* synthetic */ alz a;

        AnonymousClass1(alz com_vungle_publisher_alz) {
            this.a = com_vungle_publisher_alz;
        }

        public final /* bridge */ /* synthetic */ void a(Object obj) {
            this.a.a((ahu) obj);
        }
    }

    public aly(alz<T, R> com_vungle_publisher_alz_T__R) {
        super(new AnonymousClass1(com_vungle_publisher_alz_T__R));
        this.c = com_vungle_publisher_alz_T__R;
        this.b = new all(com_vungle_publisher_alz_T__R);
    }

    public final void a() {
        this.b.a();
    }

    public final void a(Throwable th) {
        this.b.a(th);
    }

    public final void a(T t) {
        this.b.a((Object) t);
    }
}
