package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ct implements c<cs> {
    static final /* synthetic */ boolean a = (!ct.class.desiredAssertionStatus());
    private final MembersInjector<cs> b;

    public final /* synthetic */ Object get() {
        return (cs) d.a(this.b, new cs());
    }

    private ct(MembersInjector<cs> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<cs> a(MembersInjector<cs> membersInjector) {
        return new ct(membersInjector);
    }
}
