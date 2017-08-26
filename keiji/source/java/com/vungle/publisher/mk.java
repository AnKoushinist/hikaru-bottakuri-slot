package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class mk implements c<mj> {
    static final /* synthetic */ boolean a = (!mk.class.desiredAssertionStatus());
    private final MembersInjector<mj> b;

    public final /* synthetic */ Object get() {
        return (mj) d.a(this.b, new mj());
    }

    private mk(MembersInjector<mj> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<mj> a(MembersInjector<mj> membersInjector) {
        return new mk(membersInjector);
    }
}
