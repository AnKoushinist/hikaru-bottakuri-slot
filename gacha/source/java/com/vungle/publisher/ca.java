package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ba.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ca implements MembersInjector<by> {
    static final /* synthetic */ boolean a = (!ca.class.desiredAssertionStatus());
    private final Provider<lr> b;
    private final Provider<a> c;
    private final Provider<ql> d;
    private final Provider<Context> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        by byVar = (by) obj;
        if (byVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        byVar.c = (lr) this.b.get();
        byVar.d = (a) this.c.get();
        byVar.e = (ql) this.d.get();
        byVar.f = (Context) this.e.get();
    }

    private ca(Provider<lr> provider, Provider<a> provider2, Provider<ql> provider3, Provider<Context> provider4) {
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

    public static MembersInjector<by> a(Provider<lr> provider, Provider<a> provider2, Provider<ql> provider3, Provider<Context> provider4) {
        return new ca(provider, provider2, provider3, provider4);
    }
}
