package com.tapjoy.internal;

public final class cf extends RuntimeException {
    public final int a;

    public cf(int i, String str) {
        super(str);
        this.a = i;
    }
}
