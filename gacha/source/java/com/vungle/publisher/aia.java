package com.vungle.publisher;

/* compiled from: vungle */
public final class aia extends RuntimeException {
    public aia(String str, Throwable th) {
        super(str, th);
    }

    public aia(Throwable th) {
        super(th.getMessage(), th);
    }
}
