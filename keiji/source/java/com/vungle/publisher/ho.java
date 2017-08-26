package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ho implements c<gs> {
    static final /* synthetic */ boolean a = (!ho.class.desiredAssertionStatus());
    private final MembersInjector<gs> b;

    public final /* synthetic */ Object get() {
        return (gs) d.a(this.b, new gs());
    }

    private ho(MembersInjector<gs> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<gs> a(MembersInjector<gs> membersInjector) {
        return new ho(membersInjector);
    }
}
