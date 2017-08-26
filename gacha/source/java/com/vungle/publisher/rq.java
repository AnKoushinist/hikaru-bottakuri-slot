package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.WrapperFramework;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rq implements c<pu> {
    static final /* synthetic */ boolean a = (!rq.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;
    private final Provider<WrapperFramework> d;

    public final /* synthetic */ Object get() {
        return (pu) e.a(new pm(((Context) this.c.get()).getPackageName(), this.b.b, (WrapperFramework) this.d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private rq(re reVar, Provider<Context> provider, Provider<WrapperFramework> provider2) {
        if (a || reVar != null) {
            this.b = reVar;
            if (a || provider != null) {
                this.c = provider;
                if (a || provider2 != null) {
                    this.d = provider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<pu> a(re reVar, Provider<Context> provider, Provider<WrapperFramework> provider2) {
        return new rq(reVar, provider, provider2);
    }
}
