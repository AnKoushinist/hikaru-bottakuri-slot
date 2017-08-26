package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bs implements MembersInjector<br> {
    static final /* synthetic */ boolean a = (!bs.class.desiredAssertionStatus());
    private final Provider<bt> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        br brVar = (br) obj;
        if (brVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        brVar.a = (bt) this.b.get();
    }

    public static void a(br brVar, Provider<bt> provider) {
        brVar.a = (bt) provider.get();
    }
}
