package com.vungle.publisher;

import java.util.Arrays;

/* compiled from: vungle */
public final class aiq<T> implements com.vungle.publisher.ahp.a<T> {
    private final ahq<? super T> a;
    private final ahp<T> b;

    /* compiled from: vungle */
    static final class a<T> extends ahu<T> {
        private final ahu<? super T> b;
        private final ahq<? super T> c;
        private boolean d;

        a(ahu<? super T> com_vungle_publisher_ahu__super_T, ahq<? super T> com_vungle_publisher_ahq__super_T) {
            super(com_vungle_publisher_ahu__super_T);
            this.b = com_vungle_publisher_ahu__super_T;
            this.c = com_vungle_publisher_ahq__super_T;
        }

        public final void a() {
            if (!this.d) {
                try {
                    this.c.a();
                    this.d = true;
                    this.b.a();
                } catch (Throwable th) {
                    ahx.a(th, (ahq) this);
                }
            }
        }

        public final void a(Throwable th) {
            if (this.d) {
                alp.a(th);
                return;
            }
            this.d = true;
            try {
                this.c.a(th);
                this.b.a(th);
            } catch (Throwable th2) {
                ahx.b(th2);
                this.b.a(new ahw(Arrays.asList(new Throwable[]{th, th2}), (byte) 0));
            }
        }

        public final void a(T t) {
            if (!this.d) {
                try {
                    this.c.a((Object) t);
                    this.b.a((Object) t);
                } catch (Throwable th) {
                    ahx.a(th, this, t);
                }
            }
        }
    }

    public final /* synthetic */ void a(Object obj) {
        this.b.a(new a((ahu) obj, this.a));
    }

    public aiq(ahp<T> com_vungle_publisher_ahp_T, ahq<? super T> com_vungle_publisher_ahq__super_T) {
        this.b = com_vungle_publisher_ahp_T;
        this.a = com_vungle_publisher_ahq__super_T;
    }
}
