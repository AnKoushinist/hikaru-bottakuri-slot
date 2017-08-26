package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rm implements c<sr> {
    static final /* synthetic */ boolean a = (!rm.class.desiredAssertionStatus());
    private final re b;
    private final Provider<sf> c;

    public final /* synthetic */ Object get() {
        return (sr) e.a((sf) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rm(re reVar, Provider<sf> provider) {
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

    public static c<sr> a(re reVar, Provider<sf> provider) {
        return new rm(reVar, provider);
    }
}
