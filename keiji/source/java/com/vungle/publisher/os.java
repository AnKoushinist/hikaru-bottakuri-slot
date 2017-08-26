package com.vungle.publisher;

import com.vungle.publisher.op.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class os implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!os.class.desiredAssertionStatus());
    private final Provider<op> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private os(Provider<op> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<op> provider) {
        return new os(provider);
    }
}
