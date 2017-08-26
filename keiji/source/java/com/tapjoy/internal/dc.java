package com.tapjoy.internal;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class dc {
    static final Logger a = Logger.getLogger(dc.class.getName());

    private dc() {
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                try {
                    a.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
                } catch (Throwable e2) {
                    a.log(Level.SEVERE, "IOException should not have been thrown.", e2);
                }
            }
        }
    }
}
