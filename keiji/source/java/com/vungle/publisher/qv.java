package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class qv implements c<qu> {
    static final /* synthetic */ boolean a = (!qv.class.desiredAssertionStatus());
    private final MembersInjector<qu> b;

    public final /* synthetic */ Object get() {
        return (qu) d.a(this.b, new qu());
    }

    private qv(MembersInjector<qu> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<qu> a(MembersInjector<qu> membersInjector) {
        return new qv(membersInjector);
    }
}
