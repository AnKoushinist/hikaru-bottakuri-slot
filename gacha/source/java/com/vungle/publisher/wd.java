package com.vungle.publisher;

import com.vungle.publisher.vy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wd implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!wd.class.desiredAssertionStatus());
    private final Provider<vy> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private wd(Provider<vy> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<vy> provider) {
        return new wd(provider);
    }
}
