package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class agx implements c<agw> {
    static final /* synthetic */ boolean a = (!agx.class.desiredAssertionStatus());
    private final MembersInjector<agw> b;

    public final /* synthetic */ Object get() {
        return (agw) d.a(this.b, new agw());
    }

    private agx(MembersInjector<agw> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<agw> a(MembersInjector<agw> membersInjector) {
        return new agx(membersInjector);
    }
}
