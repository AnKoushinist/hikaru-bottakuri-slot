package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xx implements c<xw> {
    static final /* synthetic */ boolean a = (!xx.class.desiredAssertionStatus());
    private final MembersInjector<xw> b;

    public final /* synthetic */ Object get() {
        return (xw) d.a(this.b, new xw());
    }

    private xx(MembersInjector<xw> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xw> a(MembersInjector<xw> membersInjector) {
        return new xx(membersInjector);
    }
}
