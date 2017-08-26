package com.vungle.publisher;

import com.vungle.publisher.jt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!jw.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<j.a> c;
    private final Provider<a> d;
    private final Provider<jt> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.e = (j.a) this.c.get();
        aVar.a = (a) this.d.get();
        aVar.b = this.e;
    }

    private jw(Provider<cf> provider, Provider<j.a> provider2, Provider<a> provider3, Provider<jt> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<j.a> provider2, Provider<a> provider3, Provider<jt> provider4) {
        return new jw(provider, provider2, provider3, provider4);
    }
}
