package com.tapjoy.internal;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class fq {
    private final File a;

    public fq(File file) {
        this.a = file;
    }

    public final synchronized boolean a() {
        boolean z = false;
        synchronized (this) {
            if (b() == null) {
                try {
                    bk.a(this.a, UUID.randomUUID().toString());
                    if (b() != null) {
                        z = true;
                    }
                } catch (IOException e) {
                    this.a.delete();
                    throw e;
                } catch (IOException e2) {
                }
            }
        }
        return z;
    }

    final String b() {
        if (this.a.exists()) {
            try {
                String a = bk.a(this.a, ap.c);
                if (a.length() > 0) {
                    return a;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}
