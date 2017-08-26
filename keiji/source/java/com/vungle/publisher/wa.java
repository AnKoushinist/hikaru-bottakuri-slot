package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wa implements MembersInjector<vz> {
    static final /* synthetic */ boolean a = (!wa.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        vz vzVar = (vz) obj;
        if (vzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vzVar.c = (a) this.b.get();
        vzVar.d = (wn) this.c.get();
    }

    public static void a(vz vzVar, Provider<a> provider) {
        vzVar.c = (a) provider.get();
    }

    public static void b(vz vzVar, Provider<wn> provider) {
        vzVar.d = (wn) provider.get();
    }
}
