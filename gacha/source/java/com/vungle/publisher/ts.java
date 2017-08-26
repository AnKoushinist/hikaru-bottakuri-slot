package com.vungle.publisher;

import com.vungle.publisher.tp.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ts implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ts.class.desiredAssertionStatus());
    private final Provider<tp> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private ts(Provider<tp> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<tp> provider) {
        return new ts(provider);
    }
}
