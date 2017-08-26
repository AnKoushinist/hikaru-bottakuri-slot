package com.vungle.publisher;

import com.vungle.publisher.j.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dj implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!dj.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<dg> d;
    private final Provider<a> e;
    private final Provider<jc.a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.e = (a) this.c.get();
        aVar.a = this.d;
        aVar.b = (a) this.e.get();
        aVar.c = (jc.a) this.f.get();
    }

    private dj(Provider<cf> provider, Provider<a> provider2, Provider<dg> provider3, Provider<a> provider4, Provider<jc.a> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<cf> provider, Provider<a> provider2, Provider<dg> provider3, Provider<a> provider4, Provider<jc.a> provider5) {
        return new dj(provider, provider2, provider3, provider4, provider5);
    }
}
