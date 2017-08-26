package com.vungle.publisher;

import com.vungle.publisher.abg.a.b.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class abk implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!abk.class.desiredAssertionStatus());
    private final Provider<sr> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (sr) this.b.get();
    }

    private abk(Provider<sr> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<sr> provider) {
        return new abk(provider);
    }
}
