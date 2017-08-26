package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ky implements c<kx> {
    static final /* synthetic */ boolean a = (!ky.class.desiredAssertionStatus());
    private final MembersInjector<kx> b;

    public final /* synthetic */ Object get() {
        return (kx) d.a(this.b, new kx());
    }

    private ky(MembersInjector<kx> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<kx> a(MembersInjector<kx> membersInjector) {
        return new ky(membersInjector);
    }
}
