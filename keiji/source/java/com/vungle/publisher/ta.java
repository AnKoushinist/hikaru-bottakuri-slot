package com.vungle.publisher;

import com.vungle.publisher.sx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ta implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ta.class.desiredAssertionStatus());
    private final Provider<sx> b;
    private final Provider<jc.a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
        aVar.b = (jc.a) this.c.get();
    }

    private ta(Provider<sx> provider, Provider<jc.a> provider2) {
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

    public static MembersInjector<a> a(Provider<sx> provider, Provider<jc.a> provider2) {
        return new ta(provider, provider2);
    }
}
