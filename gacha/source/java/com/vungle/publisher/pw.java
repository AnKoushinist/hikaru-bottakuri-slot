package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class pw implements c<pv> {
    static final /* synthetic */ boolean a = (!pw.class.desiredAssertionStatus());
    private final MembersInjector<pv> b;

    public final /* synthetic */ Object get() {
        return (pv) d.a(this.b, new pv());
    }

    private pw(MembersInjector<pv> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<pv> a(MembersInjector<pv> membersInjector) {
        return new pw(membersInjector);
    }
}
