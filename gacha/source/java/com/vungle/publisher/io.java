package com.vungle.publisher;

import com.vungle.publisher.cs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class io implements MembersInjector<if> {
    static final /* synthetic */ boolean a = (!io.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<agt> d;
    private final Provider<if.a> e;
    private final Provider<a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        if ifVar = (if) obj;
        if (ifVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ifVar.u = (cf) this.b.get();
        ifVar.q = (a) this.c.get();
        ifVar.r = (agt) this.d.get();
        ifVar.v = (if.a) this.e.get();
        ifVar.w = (a) this.f.get();
    }

    private io(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<if.a> provider4, Provider<a> provider5) {
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

    public static MembersInjector<if> a(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<if.a> provider4, Provider<a> provider5) {
        return new io(provider, provider2, provider3, provider4, provider5);
    }
}
