package com.vungle.publisher;

import com.vungle.publisher.ba.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bc implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!bc.class.desiredAssertionStatus());
    private final Provider<lr> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (lr) this.b.get();
    }

    private bc(Provider<lr> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<lr> provider) {
        return new bc(provider);
    }
}
