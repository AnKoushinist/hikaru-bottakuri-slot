package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class gc implements c<en> {
    static final /* synthetic */ boolean a = (!gc.class.desiredAssertionStatus());
    private final MembersInjector<en> b;

    public final /* synthetic */ Object get() {
        return (en) d.a(this.b, new en());
    }

    private gc(MembersInjector<en> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<en> a(MembersInjector<en> membersInjector) {
        return new gc(membersInjector);
    }
}
