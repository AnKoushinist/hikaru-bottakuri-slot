package com.vungle.publisher;

import com.vungle.publisher.vt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!vw.class.desiredAssertionStatus());
    private final Provider<vt> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private vw(Provider<vt> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<vt> provider) {
        return new vw(provider);
    }
}
