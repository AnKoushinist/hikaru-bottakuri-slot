package com.tapjoy.internal;

public final class at {

    public static final class a implements aw {
        private final au a;

        public a(au auVar) {
            this.a = auVar;
        }

        public final Object a(Object obj) {
            synchronized (this.a) {
                as a = this.a.a(obj, false);
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
                as a = this.a.a(obj, true);
            }
            synchronized (a) {
                a.a(obj2);
            }
        }
    }
}
