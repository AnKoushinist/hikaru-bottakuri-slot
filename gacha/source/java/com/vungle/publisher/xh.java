package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xh implements c<xg> {
    static final /* synthetic */ boolean a = (!xh.class.desiredAssertionStatus());
    private final MembersInjector<xg> b;

    public final /* synthetic */ Object get() {
        return (xg) d.a(this.b, new xg());
    }

    private xh(MembersInjector<xg> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xg> a(MembersInjector<xg> membersInjector) {
        return new xh(membersInjector);
    }
}
