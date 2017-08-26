package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class agc implements c<agb> {
    static final /* synthetic */ boolean a = (!agc.class.desiredAssertionStatus());
    private final MembersInjector<agb> b;

    public final /* synthetic */ Object get() {
        return (agb) d.a(this.b, new agb());
    }

    private agc(MembersInjector<agb> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<agb> a(MembersInjector<agb> membersInjector) {
        return new agc(membersInjector);
    }
}
