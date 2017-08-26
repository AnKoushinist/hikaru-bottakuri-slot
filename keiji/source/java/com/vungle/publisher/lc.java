package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class lc implements c<js> {
    static final /* synthetic */ boolean a = (!lc.class.desiredAssertionStatus());
    private final MembersInjector<js> b;

    public final /* synthetic */ Object get() {
        return (js) d.a(this.b, new js());
    }

    private lc(MembersInjector<js> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<js> a(MembersInjector<js> membersInjector) {
        return new lc(membersInjector);
    }
}
