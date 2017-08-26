package com.vungle.publisher;

import android.content.Context;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rp implements c<String> {
    static final /* synthetic */ boolean a = (!rp.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        if (((Context) this.c.get()).getExternalCacheDir() == null) {
            throw new qs();
        }
        return (String) e.a(qx.a(((Context) this.c.get()).getExternalCacheDir(), ".VungleCacheDir"), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rp(re reVar, Provider<Context> provider) {
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

    public static c<String> a(re reVar, Provider<Context> provider) {
        return new rp(reVar, provider);
    }
}
