package com.vungle.publisher;

/* compiled from: vungle */
public final class air<T> implements com.vungle.publisher.ahp.a<T> {
    final ahp<T> a;
    final aii<? super T, Boolean> b;

    /* compiled from: vungle */
    static final class a<T> extends ahu<T> {
        final ahu<? super T> b;
        final aii<? super T, Boolean> c;
        boolean d;

        public a(ahu<? super T> com_vungle_publisher_ahu__super_T, aii<? super T, Boolean> com_vungle_publisher_aii__super_T__java_lang_Boolean) {
            this.b = com_vungle_publisher_ahu__super_T;
            this.c = com_vungle_publisher_aii__super_T__java_lang_Boolean;
            a(0);
        }

        public final void a(T t) {
            try {
                if (((Boolean) this.c.a(t)).booleanValue()) {
                    this.b.a((Object) t);
                } else {
                    a(1);
                }
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
            super.a(com_vungle_publisher_ahr);
            this.b.a(com_vungle_publisher_ahr);
        }
    }

    public final /* synthetic */ void a(Object obj) {
        ahu com_vungle_publisher_ahu = (ahu) obj;
        ahu aVar = new a(com_vungle_publisher_ahu, this.b);
        com_vungle_publisher_ahu.a((ahv) aVar);
        this.a.a(aVar);
    }

    public air(ahp<T> com_vungle_publisher_ahp_T, aii<? super T, Boolean> com_vungle_publisher_aii__super_T__java_lang_Boolean) {
        this.a = com_vungle_publisher_ahp_T;
        this.b = com_vungle_publisher_aii__super_T__java_lang_Boolean;
    }
}
