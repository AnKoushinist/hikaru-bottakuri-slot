package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.qf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class px implements MembersInjector<pv> {
    static final /* synthetic */ boolean a = (!px.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<Context> c;
    private final Provider<SharedPreferences> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        pv pvVar = (pv) obj;
        if (pvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        pvVar.h = (a) this.b.get();
        pvVar.i = (Context) this.c.get();
        pvVar.j = (SharedPreferences) this.d.get();
    }

    private px(Provider<a> provider, Provider<Context> provider2, Provider<SharedPreferences> provider3) {
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

    public static MembersInjector<pv> a(Provider<a> provider, Provider<Context> provider2, Provider<SharedPreferences> provider3) {
        return new px(provider, provider2, provider3);
    }
}
