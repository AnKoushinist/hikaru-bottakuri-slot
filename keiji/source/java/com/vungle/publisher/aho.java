package com.vungle.publisher;

/* compiled from: vungle */
public final class aho<T> {
    private static final aho<Void> d = new aho(a.OnCompleted, null);
    public final a a;
    public final Throwable b;
    private final T c = null;

    /* compiled from: vungle */
    public enum a {
        OnNext,
        OnError,
        OnCompleted
    }

    public static <T> aho<T> a() {
        return new aho(a.OnNext, null);
    }

    public static <T> aho<T> a(Throwable th) {
        return new aho(a.OnError, th);
    }

    public static <T> aho<T> b() {
        return d;
    }

    private aho(a aVar, Throwable th) {
        this.b = th;
        this.a = aVar;
    }

    private boolean d() {
        return (this.a == a.OnNext) && this.c != null;
    }

    private boolean e() {
        return c() && this.b != null;
    }

    public final boolean c() {
        return this.a == a.OnError;
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(64).append('[').append(super.toString()).append(' ').append(this.a);
        if (d()) {
            append.append(' ').append(this.c);
        }
        if (e()) {
            append.append(' ').append(this.b.getMessage());
        }
        append.append(']');
        return append.toString();
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode();
        if (d()) {
            hashCode = (hashCode * 31) + this.c.hashCode();
        }
        if (e()) {
            return (hashCode * 31) + this.b.hashCode();
        }
        return hashCode;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        aho com_vungle_publisher_aho = (aho) obj;
        if (com_vungle_publisher_aho.a != this.a) {
            return false;
        }
        if (this.c != com_vungle_publisher_aho.c && (this.c == null || !this.c.equals(com_vungle_publisher_aho.c))) {
            return false;
        }
        if (this.b == com_vungle_publisher_aho.b || (this.b != null && this.b.equals(com_vungle_publisher_aho.b))) {
            return true;
        }
        return false;
    }
}
