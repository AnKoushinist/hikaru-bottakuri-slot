package com.vungle.publisher;

import com.vungle.publisher.oy.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pd implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!pd.class.desiredAssertionStatus());
    private final Provider<oy.a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (oy.a) this.b.get();
    }

    private pd(Provider<oy.a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<oy.a> provider) {
        return new pd(provider);
    }
}
