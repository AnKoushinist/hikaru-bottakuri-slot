package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class us implements c<ur> {
    static final /* synthetic */ boolean a = (!us.class.desiredAssertionStatus());
    private final MembersInjector<ur> b;

    public final /* synthetic */ Object get() {
        return (ur) d.a(this.b, new ur());
    }

    private us(MembersInjector<ur> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ur> a(MembersInjector<ur> membersInjector) {
        return new us(membersInjector);
    }
}
