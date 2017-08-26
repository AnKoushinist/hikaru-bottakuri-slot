package com.tapjoy.internal;

import java.io.IOException;

public final class bz extends IOException {
    public bz(Throwable th) {
        super(th.getMessage());
    }
}
