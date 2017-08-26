package com.tapjoy.internal;

public final class au {

    public static final class a implements ax {
        private final av a;

        public a(av avVar) {
            this.a = avVar;
        }

        public final Object a(Object obj) {
            synchronized (this.a) {
                at a = this.a.a(obj, false);
            }
            if (a == null) {
                return null;
            }
            Object a2;
            synchronized (a) {
                a2 = a.a();
            }
            return a2;
        }

        public final void a(Object obj, Object obj2) {
            synchronized (this.a) {
                at a = this.a.a(obj, true);
            }
            synchronized (a) {
                a.a(obj2);
            }
        }
    }
}
