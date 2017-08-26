package com.tapjoy.internal;

import java.util.Iterator;

public abstract class cx implements Iterator {
    protected cx() {
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
