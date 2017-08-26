package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ps implements c<pp> {
    static final /* synthetic */ boolean a = (!ps.class.desiredAssertionStatus());
    private final MembersInjector<pp> b;

    public final /* synthetic */ Object get() {
        return (pp) d.a(this.b, new pp());
    }

    private ps(MembersInjector<pp> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<pp> a(MembersInjector<pp> membersInjector) {
        return new ps(membersInjector);
    }
}
