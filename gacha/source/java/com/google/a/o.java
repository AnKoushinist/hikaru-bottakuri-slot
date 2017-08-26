package com.google.a;

import com.google.a.b.g;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: JsonObject */
public final class o extends l {
    private final g<String, l> a = new g();

    public void a(String str, l lVar) {
        if (lVar == null) {
            lVar = n.a;
        }
        this.a.put(str, lVar);
    }

    public Set<Entry<String, l>> o() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof o) && ((o) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
