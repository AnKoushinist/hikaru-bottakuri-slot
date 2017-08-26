package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ly.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class me implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!me.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<agt> c;
    private final Provider<Context> d;
    private final Provider<ly> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (pn) this.b.get();
        aVar.b = (agt) this.c.get();
        aVar.c = (Context) this.d.get();
        aVar.d = this.e;
    }

    private me(Provider<pn> provider, Provider<agt> provider2, Provider<Context> provider3, Provider<ly> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<pn> provider, Provider<agt> provider2, Provider<Context> provider3, Provider<ly> provider4) {
        return new me(provider, provider2, provider3, provider4);
    }
}
