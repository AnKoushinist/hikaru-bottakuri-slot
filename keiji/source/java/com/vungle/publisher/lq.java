package com.vungle.publisher;

import com.vungle.publisher.ln.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lq implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!lq.class.desiredAssertionStatus());
    private final Provider<ln> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private lq(Provider<ln> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ln> provider) {
        return new lq(provider);
    }
}
