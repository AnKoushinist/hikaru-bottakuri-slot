package com.vungle.publisher;

/* compiled from: vungle */
final class aha implements Runnable {
    final ahh a = new ahh();
    private final ahc b;

    aha(ahc com_vungle_publisher_ahc) {
        this.b = com_vungle_publisher_ahc;
    }

    public final void run() {
        ahg a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
