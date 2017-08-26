package com.vungle.publisher;

import com.vungle.publisher.uv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ve implements MembersInjector<vc> {
    static final /* synthetic */ boolean a = (!ve.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<uz> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        vc vcVar = (vc) obj;
        if (vcVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vcVar.c = this.b;
        vcVar.a = (a) this.c.get();
        vcVar.b = (uz) this.d.get();
    }

    private ve(Provider<we> provider, Provider<a> provider2, Provider<uz> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<vc> a(Provider<we> provider, Provider<a> provider2, Provider<uz> provider3) {
        return new ve(provider, provider2, provider3);
    }
}
