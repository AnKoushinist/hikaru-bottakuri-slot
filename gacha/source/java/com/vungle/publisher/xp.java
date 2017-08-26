package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xp implements c<xo> {
    static final /* synthetic */ boolean a = (!xp.class.desiredAssertionStatus());
    private final MembersInjector<xo> b;

    public final /* synthetic */ Object get() {
        return (xo) d.a(this.b, new xo());
    }

    private xp(MembersInjector<xo> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xo> a(MembersInjector<xo> membersInjector) {
        return new xp(membersInjector);
    }
}
