package com.vungle.publisher;

import com.vungle.publisher.ft.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fx implements MembersInjector<ft> {
    static final /* synthetic */ boolean a = (!fx.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        ft ftVar = (ft) obj;
        if (ftVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ftVar.a = (a) this.b.get();
    }

    private fx(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ft> a(Provider<a> provider) {
        return new fx(provider);
    }
}
