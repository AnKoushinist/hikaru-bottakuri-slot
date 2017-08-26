package com.vungle.publisher;

import com.vungle.publisher.vy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wk implements MembersInjector<wi> {
    static final /* synthetic */ boolean a = (!wk.class.desiredAssertionStatus());
    private final Provider<wl> b;
    private final Provider<a> c;
    private final Provider<vt.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        wi wiVar = (wi) obj;
        if (wiVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wiVar.a = (wl) this.b.get();
        wiVar.b = (a) this.c.get();
        wiVar.c = (vt.a) this.d.get();
    }

    private wk(Provider<wl> provider, Provider<a> provider2, Provider<vt.a> provider3) {
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

    public static MembersInjector<wi> a(Provider<wl> provider, Provider<a> provider2, Provider<vt.a> provider3) {
        return new wk(provider, provider2, provider3);
    }
}
