package com.vungle.publisher;

import com.vungle.publisher.env.AndroidDevice;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rj implements c<pn> {
    static final /* synthetic */ boolean a = (!rj.class.desiredAssertionStatus());
    private final re b;
    private final Provider<AndroidDevice> c;

    public final /* synthetic */ Object get() {
        return (pn) e.a((AndroidDevice) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rj(re reVar, Provider<AndroidDevice> provider) {
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

    public static c<pn> a(re reVar, Provider<AndroidDevice> provider) {
        return new rj(reVar, provider);
    }
}
