package com.vungle.publisher;

import com.vungle.publisher.hu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jb implements MembersInjector<ht> {
    static final /* synthetic */ boolean a = (!jb.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<ht.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        ht htVar = (ht) obj;
        if (htVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        htVar.u = (cf) this.b.get();
        htVar.a = (a) this.c.get();
        htVar.b = (ht.a) this.d.get();
    }

    private jb(Provider<cf> provider, Provider<a> provider2, Provider<ht.a> provider3) {
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

    public static MembersInjector<ht> a(Provider<cf> provider, Provider<a> provider2, Provider<ht.a> provider3) {
        return new jb(provider, provider2, provider3);
    }
}
