package com.tapjoy.internal;

import java.io.InterruptedIOException;

public class id {
    public static final id a = new id() {
        public final void a() {
        }
    };
    private boolean b;
    private long c;

    public void a() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.b && this.c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
