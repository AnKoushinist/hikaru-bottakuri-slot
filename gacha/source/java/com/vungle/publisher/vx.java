package com.vungle.publisher;

import com.vungle.publisher.vs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vx<T extends vs> implements MembersInjector<a<T>> {
    static final /* synthetic */ boolean a = (!vx.class.desiredAssertionStatus());
    private final Provider<pn> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.b = (pn) this.b.get();
    }

    public static <T extends vs> void a(a<T> aVar, Provider<pn> provider) {
        aVar.b = (pn) provider.get();
    }
}
