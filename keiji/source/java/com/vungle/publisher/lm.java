package com.vungle.publisher;

import com.vungle.publisher.lg.a;
import com.vungle.publisher.lk.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lm implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!lm.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<ln.a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.b.get();
        bVar.b = (ln.a) this.c.get();
    }

    private lm(Provider<a> provider, Provider<ln.a> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> a(Provider<a> provider, Provider<ln.a> provider2) {
        return new lm(provider, provider2);
    }
}
