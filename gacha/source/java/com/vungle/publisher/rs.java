package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class rs implements c<Class> {
    static final /* synthetic */ boolean a = (!rs.class.desiredAssertionStatus());
    private final re b;

    public final /* synthetic */ Object get() {
        re reVar = this.b;
        return (Class) e.a(reVar.c == null ? VideoFullScreenAdActivity.class : reVar.c, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rs(re reVar) {
        if (a || reVar != null) {
            this.b = reVar;
            return;
        }
        throw new AssertionError();
    }

    public static c<Class> a(re reVar) {
        return new rs(reVar);
    }
}
