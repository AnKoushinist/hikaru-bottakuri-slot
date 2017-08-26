package com.vungle.publisher;

import com.vungle.publisher.ft.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!fw.class.desiredAssertionStatus());
    private final Provider<ft> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
        aVar.b = (a) this.c.get();
    }

    private fw(Provider<ft> provider, Provider<a> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ft> provider, Provider<a> provider2) {
        return new fw(provider, provider2);
    }
}
