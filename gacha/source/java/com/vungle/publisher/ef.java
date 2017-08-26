package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ef implements c<ee> {
    static final /* synthetic */ boolean a = (!ef.class.desiredAssertionStatus());
    private final MembersInjector<ee> b;

    public final /* synthetic */ Object get() {
        return (ee) d.a(this.b, new ee());
    }

    private ef(MembersInjector<ee> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ee> a(MembersInjector<ee> membersInjector) {
        return new ef(membersInjector);
    }
}
