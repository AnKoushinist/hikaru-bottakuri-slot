package com.vungle.publisher;

/* compiled from: vungle */
public final class alm {

    /* compiled from: vungle */
    public static class AnonymousClass1 extends ahu<T> {
        final /* synthetic */ ahq b;

        public AnonymousClass1(ahq com_vungle_publisher_ahq) {
            this.b = com_vungle_publisher_ahq;
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

    public static <T> ahu<T> a(final ahu<? super T> com_vungle_publisher_ahu__super_T) {
        return new ahu<T>(com_vungle_publisher_ahu__super_T) {
            public final void a() {
                com_vungle_publisher_ahu__super_T.a();
            }

            public final void a(Throwable th) {
                com_vungle_publisher_ahu__super_T.a(th);
            }

            public final void a(T t) {
                com_vungle_publisher_ahu__super_T.a((Object) t);
            }
        };
    }
}
