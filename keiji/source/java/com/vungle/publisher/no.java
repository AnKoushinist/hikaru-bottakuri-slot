package com.vungle.publisher;

import com.vungle.publisher.nh.b;
import com.vungle.publisher.nh.b.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class no implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!no.class.desiredAssertionStatus());
    private final Provider<b> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (b) this.b.get();
    }

    private no(Provider<b> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<b> provider) {
        return new no(provider);
    }
}
