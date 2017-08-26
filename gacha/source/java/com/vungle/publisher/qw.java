package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qw implements MembersInjector<qu> {
    static final /* synthetic */ boolean a = (!qw.class.desiredAssertionStatus());
    private final Provider<String> b;
    private final Provider<String> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        qu quVar = (qu) obj;
        if (quVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        quVar.a = this.b;
        quVar.b = this.c;
    }

    private qw(Provider<String> provider, Provider<String> provider2) {
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

    public static MembersInjector<qu> a(Provider<String> provider, Provider<String> provider2) {
        return new qw(provider, provider2);
    }
}
