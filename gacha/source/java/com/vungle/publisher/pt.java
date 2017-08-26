package com.vungle.publisher;

import android.content.SharedPreferences;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pt implements MembersInjector<pp> {
    static final /* synthetic */ boolean a = (!pt.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<bt> c;
    private final Provider<a> d;
    private final Provider<SharedPreferences> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        pp ppVar = (pp) obj;
        if (ppVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ppVar.eventBus = (ql) this.b.get();
        ppVar.a = (bt) this.c.get();
        ppVar.b = (a) this.d.get();
        ppVar.c = (SharedPreferences) this.e.get();
    }

    private pt(Provider<ql> provider, Provider<bt> provider2, Provider<a> provider3, Provider<SharedPreferences> provider4) {
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

    public static MembersInjector<pp> a(Provider<ql> provider, Provider<bt> provider2, Provider<a> provider3, Provider<SharedPreferences> provider4) {
        return new pt(provider, provider2, provider3, provider4);
    }
}
