package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rk implements c<SharedPreferences> {
    static final /* synthetic */ boolean a = (!rk.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        return (SharedPreferences) e.a(((Context) this.c.get()).getSharedPreferences("VUNGLE_PUB_APP_INFO", 0), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rk(re reVar, Provider<Context> provider) {
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

    public static c<SharedPreferences> a(re reVar, Provider<Context> provider) {
        return new rk(reVar, provider);
    }
}
