package com.vungle.publisher;

import java.lang.reflect.Method;

/* compiled from: vungle */
final class ahj {
    final Method a;
    final ahm b;
    final Class<?> c;
    String d;

    ahj(Method method, ahm com_vungle_publisher_ahm, Class<?> cls) {
        this.a = method;
        this.b = com_vungle_publisher_ahm;
        this.c = cls;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ahj)) {
            return false;
        }
        a();
        ahj com_vungle_publisher_ahj = (ahj) obj;
        com_vungle_publisher_ahj.a();
        return this.d.equals(com_vungle_publisher_ahj.d);
    }

    private synchronized void a() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.a.getName());
            stringBuilder.append('(').append(this.c.getName());
            this.d = stringBuilder.toString();
        }
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
