package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class afj implements c<afi> {
    static final /* synthetic */ boolean a = (!afj.class.desiredAssertionStatus());
    private final MembersInjector<afi> b;

    public final /* synthetic */ Object get() {
        return (afi) d.a(this.b, new afi());
    }

    private afj(MembersInjector<afi> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<afi> a(MembersInjector<afi> membersInjector) {
        return new afj(membersInjector);
    }
}
