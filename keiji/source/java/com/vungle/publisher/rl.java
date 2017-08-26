package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rl implements c<sj> {
    static final /* synthetic */ boolean a = (!rl.class.desiredAssertionStatus());
    private final re b;
    private final Provider<sk> c;

    public final /* synthetic */ Object get() {
        return (sj) e.a((sk) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rl(re reVar, Provider<sk> provider) {
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

    public static c<sj> a(re reVar, Provider<sk> provider) {
        return new rl(reVar, provider);
    }
}
