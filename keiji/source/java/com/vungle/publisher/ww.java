package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ww implements MembersInjector<wv> {
    static final /* synthetic */ boolean a = (!ww.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        wv wvVar = (wv) obj;
        if (wvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wvVar.c = (a) this.b.get();
        wvVar.d = (wn) this.c.get();
        wvVar.h = (bt) this.d.get();
    }

    public static void a(wv wvVar, Provider<bt> provider) {
        wvVar.h = (bt) provider.get();
    }
}
