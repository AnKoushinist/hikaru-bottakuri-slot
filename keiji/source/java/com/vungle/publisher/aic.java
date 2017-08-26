package com.vungle.publisher;

import java.util.HashSet;
import java.util.Set;

/* compiled from: vungle */
public final class aic extends RuntimeException {

    /* compiled from: vungle */
    public static class a extends RuntimeException {
        final Object a;

        /* compiled from: vungle */
        static final class a {
            static final Set<Class<?>> a;

            static {
                Set hashSet = new HashSet();
                hashSet.add(Boolean.class);
                hashSet.add(Character.class);
                hashSet.add(Byte.class);
                hashSet.add(Short.class);
                hashSet.add(Integer.class);
                hashSet.add(Long.class);
                hashSet.add(Float.class);
                hashSet.add(Double.class);
                a = hashSet;
            }
        }

        public a(Object obj) {
            String str;
            StringBuilder stringBuilder = new StringBuilder("OnError while emitting onNext value: ");
            if (obj == null) {
                str = "null";
            } else if (a.a.contains(obj.getClass())) {
                str = obj.toString();
            } else if (obj instanceof String) {
                str = (String) obj;
            } else if (obj instanceof Enum) {
                str = ((Enum) obj).name();
            } else {
                als.a().b();
                str = obj.getClass().getName() + ".class";
            }
            super(stringBuilder.append(str).toString());
            this.a = obj;
        }
    }

    public static Throwable a(Throwable th, Object obj) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable c = ahx.c(th);
        if (!((c instanceof a) && ((a) c).a == obj)) {
            ahx.a(th, new a(obj));
        }
        return th;
    }
}
