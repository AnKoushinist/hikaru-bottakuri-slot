package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class pg implements c<pf> {
    static final /* synthetic */ boolean a = (!pg.class.desiredAssertionStatus());
    private final MembersInjector<pf> b;

    public final /* synthetic */ Object get() {
        return (pf) d.a(this.b, new pf());
    }

    private pg(MembersInjector<pf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<pf> a(MembersInjector<pf> membersInjector) {
        return new pg(membersInjector);
    }
}
