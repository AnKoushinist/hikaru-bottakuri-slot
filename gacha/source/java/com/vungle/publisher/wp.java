package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wp implements MembersInjector<wn> {
    static final /* synthetic */ boolean a = (!wp.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        wn wnVar = (wn) obj;
        if (wnVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wnVar.a = (a) this.b.get();
    }

    private wp(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<wn> a(Provider<a> provider) {
        return new wp(provider);
    }
}
