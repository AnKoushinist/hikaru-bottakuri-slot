package com.vungle.publisher;

import com.vungle.publisher.j.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class l implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!l.class.desiredAssertionStatus());
    private final Provider<gm.a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (gm.a) this.b.get();
    }

    private l(Provider<gm.a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<gm.a> provider) {
        return new l(provider);
    }
}
