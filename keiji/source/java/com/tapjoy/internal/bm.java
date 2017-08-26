package com.tapjoy.internal;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public final class bm implements bq {
    private final StringWriter a = new StringWriter();
    private final by b = new by(this.a);

    public final String toString() {
        try {
            this.b.a.flush();
            return this.a.toString();
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final void a(Writer writer) {
        try {
            this.b.a.flush();
            writer.write(this.a.toString());
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a() {
        try {
            this.b.a();
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm b() {
        try {
            this.b.b();
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm c() {
        try {
            this.b.c();
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm d() {
        try {
            this.b.d();
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(String str) {
        try {
            this.b.a(str);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(bq bqVar) {
        try {
            this.b.a(bqVar);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm b(String str) {
        try {
            this.b.b(str);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(long j) {
        try {
            this.b.a(j);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(Number number) {
        try {
            this.b.a(number);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    private bm b(Object obj) {
        try {
            this.b.a(obj);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(Collection collection) {
        try {
            this.b.a(collection);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public final bm a(Map map) {
        try {
            this.b.a(map);
            return this;
        } catch (Throwable e) {
            throw cu.a(e);
        }
    }

    public static String a(Object obj) {
        return new bm().b(obj).toString();
    }
}
