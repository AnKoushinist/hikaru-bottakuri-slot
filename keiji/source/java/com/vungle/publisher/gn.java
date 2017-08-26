package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class gn implements c<gm> {
    static final /* synthetic */ boolean a = (!gn.class.desiredAssertionStatus());
    private final MembersInjector<gm> b;

    public final /* synthetic */ Object get() {
        return (gm) d.a(this.b, new gm());
    }

    private gn(MembersInjector<gm> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<gm> a(MembersInjector<gm> membersInjector) {
        return new gn(membersInjector);
    }
}
