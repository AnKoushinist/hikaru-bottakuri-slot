package com.vungle.publisher;

import com.vungle.publisher.qf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qi implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!qi.class.desiredAssertionStatus());
    private final Provider<qf> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private qi(Provider<qf> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<qf> provider) {
        return new qi(provider);
    }
}
