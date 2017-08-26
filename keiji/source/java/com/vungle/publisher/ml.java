package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ml implements MembersInjector<mj> {
    static final /* synthetic */ boolean a = (!ml.class.desiredAssertionStatus());
    private final Provider<ql> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        mj mjVar = (mj) obj;
        if (mjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        mjVar.a = (ql) this.b.get();
    }

    private ml(Provider<ql> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<mj> a(Provider<ql> provider) {
        return new ml(provider);
    }
}
