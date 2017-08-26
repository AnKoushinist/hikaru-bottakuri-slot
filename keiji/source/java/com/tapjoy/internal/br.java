package com.tapjoy.internal;

import java.io.Writer;

public final class br implements bq {
    public final String a;

    public br(String str) {
        this.a = str;
    }

    public final void a(Writer writer) {
        writer.write(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof br)) {
            return false;
        }
        return this.a.equals(((br) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a;
    }
}
