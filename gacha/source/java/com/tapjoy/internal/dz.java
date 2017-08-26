package com.tapjoy.internal;

import java.util.List;

public final class dz extends RuntimeException {
    private final List a = null;

    public dz() {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
