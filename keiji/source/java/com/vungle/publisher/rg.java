package com.vungle.publisher;

import android.content.Context;
import dagger.a.c;
import dagger.a.e;

/* compiled from: vungle */
public final class rg implements c<Context> {
    static final /* synthetic */ boolean a = (!rg.class.desiredAssertionStatus());
    private final re b;

    public final /* synthetic */ Object get() {
        return (Context) e.a(this.b.a, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rg(re reVar) {
        if (a || reVar != null) {
            this.b = reVar;
            return;
        }
        throw new AssertionError();
    }

    public static c<Context> a(re reVar) {
        return new rg(reVar);
    }
}
