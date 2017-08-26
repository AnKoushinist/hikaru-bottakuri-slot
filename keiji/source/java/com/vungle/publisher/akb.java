package com.vungle.publisher;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class akb extends AtomicLong implements ThreadFactory {
    public static final ThreadFactory a = new ThreadFactory() {
        public final Thread newThread(Runnable runnable) {
            throw new AssertionError("No threads allowed.");
        }
    };
    final String b;

    public akb(String str) {
        this.b = str;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.b + incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
