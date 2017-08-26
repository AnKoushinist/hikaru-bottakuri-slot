package com.google.firebase;

import com.google.android.gms.common.internal.c;

public class b extends Exception {
    @Deprecated
    protected b() {
    }

    public b(String str) {
        super(c.a(str, "Detail message must not be empty"));
    }

    public b(String str, Throwable th) {
        super(c.a(str, "Detail message must not be empty"), th);
    }
}
