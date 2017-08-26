package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aas implements c<aar> {
    static final /* synthetic */ boolean a = (!aas.class.desiredAssertionStatus());
    private final MembersInjector<aar> b;

    public final /* synthetic */ Object get() {
        return (aar) d.a(this.b, new aar());
    }

    private aas(MembersInjector<aar> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aar> a(MembersInjector<aar> membersInjector) {
        return new aas(membersInjector);
    }
}
