package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class r implements c<q> {
    static final /* synthetic */ boolean a = (!r.class.desiredAssertionStatus());
    private final MembersInjector<q> b;

    public final /* synthetic */ Object get() {
        return (q) d.a(this.b, new q());
    }

    private r(MembersInjector<q> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<q> a(MembersInjector<q> membersInjector) {
        return new r(membersInjector);
    }
}
