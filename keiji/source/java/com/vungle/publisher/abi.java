package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.abg.a.a;
import com.vungle.publisher.abg.a.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class abi implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!abi.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<Demographic> c;
    private final Provider<b.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.b.get();
        aVar.b = (Demographic) this.c.get();
        aVar.c = (b.a) this.d.get();
    }

    private abi(Provider<Context> provider, Provider<Demographic> provider2, Provider<b.a> provider3) {
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

    public static MembersInjector<a> a(Provider<Context> provider, Provider<Demographic> provider2, Provider<b.a> provider3) {
        return new abi(provider, provider2, provider3);
    }
}
