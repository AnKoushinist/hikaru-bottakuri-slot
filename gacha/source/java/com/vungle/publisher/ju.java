package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ju implements c<jt> {
    static final /* synthetic */ boolean a = (!ju.class.desiredAssertionStatus());
    private final MembersInjector<jt> b;

    public final /* synthetic */ Object get() {
        return (jt) d.a(this.b, new jt());
    }

    private ju(MembersInjector<jt> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<jt> a(MembersInjector<jt> membersInjector) {
        return new ju(membersInjector);
    }
}
