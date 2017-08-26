package com.vungle.publisher;

import com.vungle.publisher.adf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class adh implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!adh.class.desiredAssertionStatus());
    private final Provider<j.a> b;
    private final Provider<agt> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (j.a) this.b.get();
        aVar.b = (agt) this.c.get();
        aVar.c = (j.a) this.b.get();
    }

    private adh(Provider<j.a> provider, Provider<agt> provider2) {
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

    public static MembersInjector<a> a(Provider<j.a> provider, Provider<agt> provider2) {
        return new adh(provider, provider2);
    }
}
