package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class rn implements c<Class> {
    static final /* synthetic */ boolean a = (!rn.class.desiredAssertionStatus());
    private final re b;

    public final /* synthetic */ Object get() {
        re reVar = this.b;
        return (Class) e.a(reVar.d == null ? MraidFullScreenAdActivity.class : reVar.d, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rn(re reVar) {
        if (a || reVar != null) {
            this.b = reVar;
            return;
        }
        throw new AssertionError();
    }

    public static c<Class> a(re reVar) {
        return new rn(reVar);
    }
}
