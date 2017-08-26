package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class iq implements c<ip> {
    static final /* synthetic */ boolean a = (!iq.class.desiredAssertionStatus());
    private final MembersInjector<ip> b;

    public final /* synthetic */ Object get() {
        return (ip) d.a(this.b, new ip());
    }

    private iq(MembersInjector<ip> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ip> a(MembersInjector<ip> membersInjector) {
        return new iq(membersInjector);
    }
}
