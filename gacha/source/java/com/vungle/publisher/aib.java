package com.vungle.publisher;

/* compiled from: vungle */
public final class aib extends RuntimeException {
    public aib(String str, Throwable th) {
        super(str, th);
    }

    public aib(Throwable th) {
        String message = th != null ? th.getMessage() : null;
        if (th == null) {
            th = new NullPointerException();
        }
        super(message, th);
    }
}
