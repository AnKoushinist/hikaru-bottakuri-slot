package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class pz implements c<py> {
    static final /* synthetic */ boolean a = (!pz.class.desiredAssertionStatus());
    private final MembersInjector<py> b;

    public final /* synthetic */ Object get() {
        return (py) d.a(this.b, new py());
    }

    private pz(MembersInjector<py> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<py> a(MembersInjector<py> membersInjector) {
        return new pz(membersInjector);
    }
}
