package com.vungle.publisher;

/* compiled from: vungle */
public final class aiu<T, R> implements com.vungle.publisher.ahp.a<R> {
    final ahp<T> a;
    final aii<? super T, ? extends R> b;

    /* compiled from: vungle */
    static final class a<T, R> extends ahu<T> {
        final ahu<? super R> b;
        final aii<? super T, ? extends R> c;
        boolean d;

        public a(ahu<? super R> com_vungle_publisher_ahu__super_R, aii<? super T, ? extends R> com_vungle_publisher_aii__super_T___extends_R) {
            this.b = com_vungle_publisher_ahu__super_R;
            this.c = com_vungle_publisher_aii__super_T___extends_R;
        }

        public final void a(T t) {
            try {
                this.b.a(this.c.a(t));
            } catch (Throwable th) {
                ahx.b(th);
                this.a.b();
                a(aic.a(th, t));
            }
        }

        public final void a(Throwable th) {
            if (this.d) {
                alp.a(th);
                return;
            }
            this.d = true;
            this.b.a(th);
        }

        public final void a() {
            if (!this.d) {
                this.b.a();
            }
        }

        public final void a(ahr com_vungle_publisher_ahr) {
            this.b.a(com_vungle_publisher_ahr);
        }
    }

    public final /* synthetic */ void a(Object obj) {
        ahu com_vungle_publisher_ahu = (ahu) obj;
        ahu aVar = new a(com_vungle_publisher_ahu, this.b);
        com_vungle_publisher_ahu.a((ahv) aVar);
        this.a.a(aVar);
    }

    public aiu(ahp<T> com_vungle_publisher_ahp_T, aii<? super T, ? extends R> com_vungle_publisher_aii__super_T___extends_R) {
        this.a = com_vungle_publisher_ahp_T;
        this.b = com_vungle_publisher_aii__super_T___extends_R;
    }
}
