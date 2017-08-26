package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ib implements c<ia> {
    static final /* synthetic */ boolean a = (!ib.class.desiredAssertionStatus());
    private final MembersInjector<ia> b;

    public final /* synthetic */ Object get() {
        return (ia) d.a(this.b, new ia());
    }

    private ib(MembersInjector<ia> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ia> a(MembersInjector<ia> membersInjector) {
        return new ib(membersInjector);
    }
}
