package com.vungle.publisher;

import com.vungle.publisher.vi.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vk implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!vk.class.desiredAssertionStatus());
    private final Provider<pn> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.b = (pn) this.b.get();
    }

    private vk(Provider<pn> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<pn> provider) {
        return new vk(provider);
    }
}
