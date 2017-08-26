package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.te.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tg implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!tg.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<nb> c;
    private final Provider<agw> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.b.get();
        aVar.b = (nb) this.c.get();
        aVar.c = (agw) this.d.get();
    }

    private tg(Provider<Context> provider, Provider<nb> provider2, Provider<agw> provider3) {
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

    public static MembersInjector<a> a(Provider<Context> provider, Provider<nb> provider2, Provider<agw> provider3) {
        return new tg(provider, provider2, provider3);
    }
}
