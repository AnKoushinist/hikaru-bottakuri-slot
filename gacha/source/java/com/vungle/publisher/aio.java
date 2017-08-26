package com.vungle.publisher;

import java.io.Serializable;

/* compiled from: vungle */
public final class aio<T> {
    private static final aio a = new aio();
    private static final Object b = new Serializable() {
        public final String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object c = new Serializable() {
        public final String toString() {
            return "Notification=>NULL";
        }
    };

    /* compiled from: vungle */
    static final class a implements Serializable {
        final Throwable a;

        public a(Throwable th) {
            this.a = th;
        }

        public final String toString() {
            return "Notification=>Error:" + this.a;
        }
    }

    private aio() {
    }

    public static <T> aio<T> a() {
        return a;
    }

    public static Object a(T t) {
        if (t == null) {
            return c;
        }
        return t;
    }

    public static Object b() {
        return b;
    }

    public static Object a(Throwable th) {
        return new a(th);
    }

    public static boolean a(ahq<? super T> com_vungle_publisher_ahq__super_T, Object obj) {
        if (obj == b) {
            com_vungle_publisher_ahq__super_T.a();
            return true;
        } else if (obj == c) {
            com_vungle_publisher_ahq__super_T.a(null);
            return false;
        } else if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj.getClass() == a.class) {
            com_vungle_publisher_ahq__super_T.a(((a) obj).a);
            return true;
        } else {
            com_vungle_publisher_ahq__super_T.a(obj);
            return false;
        }
    }

    public static boolean b(Object obj) {
        return obj == b;
    }

    public static T c(Object obj) {
        return obj == c ? null : obj;
    }
}
