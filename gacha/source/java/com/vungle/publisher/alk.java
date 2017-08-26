package com.vungle.publisher;

import java.util.Arrays;

/* compiled from: vungle */
public final class alk<T> extends ahu<T> {
    boolean b;
    private final ahu<? super T> c;

    public alk(ahu<? super T> com_vungle_publisher_ahu__super_T) {
        super(com_vungle_publisher_ahu__super_T);
        this.c = com_vungle_publisher_ahu__super_T;
    }

    public final void a() {
        aid com_vungle_publisher_aid;
        if (!this.b) {
            this.b = true;
            try {
                this.c.a();
                try {
                    this.a.b();
                } catch (Throwable th) {
                    alp.a(th);
                    com_vungle_publisher_aid = new aid(th.getMessage(), th);
                }
            } catch (Throwable th2) {
                try {
                    this.a.b();
                } catch (Throwable th3) {
                    alp.a(th3);
                    com_vungle_publisher_aid = new aid(th3.getMessage(), th3);
                }
            }
        }
    }

    public final void a(Throwable th) {
        aia com_vungle_publisher_aia;
        ahx.b(th);
        if (!this.b) {
            this.b = true;
            alp.a(th);
            try {
                this.c.a(th);
                try {
                    this.a.b();
                } catch (Throwable th2) {
                    alp.a(th2);
                    com_vungle_publisher_aia = new aia(th2);
                }
            } catch (aib e) {
                this.a.b();
                throw e;
            } catch (Throwable th3) {
                alp.a(th3);
                aia com_vungle_publisher_aia2 = new aia("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new ahw(Arrays.asList(new Throwable[]{th, th2, th3}), (byte) 0));
            }
        }
    }

    public final void a(T t) {
        try {
            if (!this.b) {
                this.c.a((Object) t);
            }
        } catch (Throwable th) {
            ahx.a(th, (ahq) this);
        }
    }
}
