package com.tapjoy.internal;

import java.io.Writer;

public final class bq implements bp {
    public final String a;

    public bq(String str) {
        this.a = str;
    }

    public final void a(Writer writer) {
        writer.write(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bq)) {
            return false;
        }
        return this.a.equals(((bq) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
