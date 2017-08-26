package com.b.a.a;

import android.app.Activity;
import java.util.Collections;
import java.util.Map;

/* compiled from: SessionEvent */
final class s {
    public final t a;
    public final long b;
    public final b c;
    public final Map<String, String> d;
    public final String e;
    public final Map<String, Object> f;
    public final String g;
    public final Map<String, Object> h;
    private String i;

    /* compiled from: SessionEvent */
    static class a {
        final b a;
        final long b = System.currentTimeMillis();
        Map<String, String> c = null;
        String d = null;
        Map<String, Object> e = null;
        String f = null;
        Map<String, Object> g = null;

        public a(b bVar) {
            this.a = bVar;
        }

        public a a(Map<String, String> map) {
            this.c = map;
            return this;
        }

        public s a(t tVar) {
            return new s(tVar, this.b, this.a, this.c, this.d, this.e, this.f, this.g);
        }
    }

    /* compiled from: SessionEvent */
    enum b {
        START,
        RESUME,
        PAUSE,
        STOP,
        CRASH,
        INSTALL,
        CUSTOM,
        PREDEFINED
    }

    public static a a(b bVar, Activity activity) {
        return new a(bVar).a(Collections.singletonMap("activity", activity.getClass().getName()));
    }

    public static a a() {
        return new a(b.INSTALL);
    }

    public static a a(String str) {
        return new a(b.CRASH).a(Collections.singletonMap("sessionId", str));
    }

    private s(t tVar, long j, b bVar, Map<String, String> map, String str, Map<String, Object> map2, String str2, Map<String, Object> map3) {
        this.a = tVar;
        this.b = j;
        this.c = bVar;
        this.d = map;
        this.e = str;
        this.f = map2;
        this.g = str2;
        this.h = map3;
    }

    public String toString() {
        if (this.i == null) {
            this.i = "[" + getClass().getSimpleName() + ": " + "timestamp=" + this.b + ", type=" + this.c + ", details=" + this.d + ", customType=" + this.e + ", customAttributes=" + this.f + ", predefinedType=" + this.g + ", predefinedAttributes=" + this.h + ", metadata=[" + this.a + "]]";
        }
        return this.i;
    }
}
