package com.vungle.publisher;

/* compiled from: vungle */
final class ahh {
    private ahg a;
    private ahg b;

    ahh() {
    }

    final synchronized void a(ahg com_vungle_publisher_ahg) {
        if (com_vungle_publisher_ahg == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.b != null) {
            this.b.c = com_vungle_publisher_ahg;
            this.b = com_vungle_publisher_ahg;
        } else if (this.a == null) {
            this.b = com_vungle_publisher_ahg;
            this.a = com_vungle_publisher_ahg;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    final synchronized ahg a() {
        ahg com_vungle_publisher_ahg;
        com_vungle_publisher_ahg = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return com_vungle_publisher_ahg;
    }

    final synchronized ahg b() {
        if (this.a == null) {
            wait(1000);
        }
        return a();
    }
}
