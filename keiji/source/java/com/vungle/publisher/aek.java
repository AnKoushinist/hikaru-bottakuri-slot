package com.vungle.publisher;

import com.vungle.publisher.aei.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aek implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aek.class.desiredAssertionStatus());
    private final Provider<ael.a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (ael.a) this.b.get();
    }

    private aek(Provider<ael.a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ael.a> provider) {
        return new aek(provider);
    }
}
