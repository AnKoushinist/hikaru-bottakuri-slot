package com.vungle.publisher;

/* compiled from: vungle */
public final class hn implements ji {
    final String a;

    public final String toString() {
        return this.a;
    }

    public hn(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hn) {
            return this.a.equals(obj.toString());
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean a() {
        return true;
    }
}
