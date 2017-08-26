package com.vungle.publisher;

import com.vungle.publisher.jy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kb implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!kb.class.desiredAssertionStatus());
    private final Provider<jy> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (jy) this.b.get();
    }

    private kb(Provider<jy> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<jy> provider) {
        return new kb(provider);
    }
}
