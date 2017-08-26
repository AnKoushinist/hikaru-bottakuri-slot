package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qj implements MembersInjector<qf> {
    static final /* synthetic */ boolean a = (!qj.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<bt> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        qf qfVar = (qf) obj;
        if (qfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        qfVar.eventBus = (ql) this.b.get();
        qfVar.c = (bt) this.c.get();
        qfVar.d = (a) this.d.get();
    }

    private qj(Provider<ql> provider, Provider<bt> provider2, Provider<a> provider3) {
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

    public static MembersInjector<qf> a(Provider<ql> provider, Provider<bt> provider2, Provider<a> provider3) {
        return new qj(provider, provider2, provider3);
    }
}
