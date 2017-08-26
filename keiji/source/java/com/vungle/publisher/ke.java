package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ke implements c<kd> {
    static final /* synthetic */ boolean a = (!ke.class.desiredAssertionStatus());
    private final MembersInjector<kd> b;

    public final /* synthetic */ Object get() {
        return (kd) d.a(this.b, new kd());
    }

    private ke(MembersInjector<kd> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<kd> a(MembersInjector<kd> membersInjector) {
        return new ke(membersInjector);
    }
}
