package com.vungle.publisher;

import com.vungle.publisher.yg.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yo implements MembersInjector<ym> {
    static final /* synthetic */ boolean a = (!yo.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<b> c;
    private final Provider<yj> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        ym ymVar = (ym) obj;
        if (ymVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(ymVar, this.b);
        ymVar.a = (b) this.c.get();
        ymVar.b = this.d;
    }

    private yo(Provider<we> provider, Provider<b> provider2, Provider<yj> provider3) {
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

    public static MembersInjector<ym> a(Provider<we> provider, Provider<b> provider2, Provider<yj> provider3) {
        return new yo(provider, provider2, provider3);
    }
}
