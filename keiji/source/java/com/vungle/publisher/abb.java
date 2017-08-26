package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class abb implements c<aba> {
    static final /* synthetic */ boolean a = (!abb.class.desiredAssertionStatus());
    private final MembersInjector<aba> b;

    public final /* synthetic */ Object get() {
        return (aba) d.a(this.b, new aba());
    }

    private abb(MembersInjector<aba> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aba> a(MembersInjector<aba> membersInjector) {
        return new abb(membersInjector);
    }
}
