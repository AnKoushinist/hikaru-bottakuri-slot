package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xc implements c<xb> {
    static final /* synthetic */ boolean a = (!xc.class.desiredAssertionStatus());
    private final MembersInjector<xb> b;

    public final /* synthetic */ Object get() {
        return (xb) d.a(this.b, new xb());
    }

    private xc(MembersInjector<xb> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xb> a(MembersInjector<xb> membersInjector) {
        return new xc(membersInjector);
    }
}
