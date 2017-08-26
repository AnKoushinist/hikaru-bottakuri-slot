package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class nr implements c<nq> {
    static final /* synthetic */ boolean a = (!nr.class.desiredAssertionStatus());
    private final MembersInjector<nq> b;

    public final /* synthetic */ Object get() {
        return (nq) d.a(this.b, new nq());
    }

    private nr(MembersInjector<nq> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<nq> a(MembersInjector<nq> membersInjector) {
        return new nr(membersInjector);
    }
}
