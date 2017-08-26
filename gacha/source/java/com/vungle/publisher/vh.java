package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vh implements MembersInjector<vf> {
    static final /* synthetic */ boolean a = (!vh.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        vf vfVar = (vf) obj;
        if (vfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vfVar.c = (a) this.b.get();
        vfVar.d = (wn) this.c.get();
        vfVar.h = (bt) this.d.get();
    }

    private vh(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
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

    public static MembersInjector<vf> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
        return new vh(provider, provider2, provider3);
    }
}
