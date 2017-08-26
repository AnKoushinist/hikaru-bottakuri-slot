package com.vungle.publisher;

import com.vungle.publisher.abp.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class abs implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!abs.class.desiredAssertionStatus());
    private final Provider<abp> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private abs(Provider<abp> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<abp> provider) {
        return new abs(provider);
    }
}
