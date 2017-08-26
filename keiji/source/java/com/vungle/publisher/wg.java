package com.vungle.publisher;

import com.vungle.publisher.we.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wg implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!wg.class.desiredAssertionStatus());
    private final Provider<we> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.c = this.b;
    }

    public static void a(a aVar, Provider<we> provider) {
        aVar.c = provider;
    }
}
