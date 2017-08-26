package com.vungle.publisher;

import com.vungle.publisher.lg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lj implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!lj.class.desiredAssertionStatus());
    private final Provider<lg> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private lj(Provider<lg> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<lg> provider) {
        return new lj(provider);
    }
}
