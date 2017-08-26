package com.vungle.publisher;

import com.vungle.publisher.jc.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jg implements MembersInjector<jc> {
    static final /* synthetic */ boolean a = (!jg.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        jc jcVar = (jc) obj;
        if (jcVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jcVar.u = (cf) this.b.get();
        jcVar.a = (a) this.c.get();
    }

    private jg(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<jc> a(Provider<cf> provider, Provider<a> provider2) {
        return new jg(provider, provider2);
    }
}
