package com.vungle.publisher;

/* compiled from: vungle */
final class ahl {
    final Object a;
    final ahj b;
    final int c = 0;
    volatile boolean d = true;

    ahl(Object obj, ahj com_vungle_publisher_ahj) {
        this.a = obj;
        this.b = com_vungle_publisher_ahj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ahl)) {
            return false;
        }
        ahl com_vungle_publisher_ahl = (ahl) obj;
        if (this.a == com_vungle_publisher_ahl.a && this.b.equals(com_vungle_publisher_ahl.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() + this.b.d.hashCode();
    }
}
