package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lx implements MembersInjector<lv> {
    static final /* synthetic */ boolean a = (!lx.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<ql> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        lv lvVar = (lv) obj;
        if (lvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lvVar.a = (Context) this.b.get();
        lvVar.b = (ql) this.c.get();
    }

    private lx(Provider<Context> provider, Provider<ql> provider2) {
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

    public static MembersInjector<lv> a(Provider<Context> provider, Provider<ql> provider2) {
        return new lx(provider, provider2);
    }
}
