package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ro implements c<uq> {
    static final /* synthetic */ boolean a = (!ro.class.desiredAssertionStatus());
    private final re b;
    private final Provider<uk> c;

    public final /* synthetic */ Object get() {
        return (uq) e.a((uk) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    private ro(re reVar, Provider<uk> provider) {
        if (a || reVar != null) {
            this.b = reVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<uq> a(re reVar, Provider<uk> provider) {
        return new ro(reVar, provider);
    }
}
