package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class il implements c<if> {
    static final /* synthetic */ boolean a = (!il.class.desiredAssertionStatus());
    private final MembersInjector<if> b;

    public final /* synthetic */ Object get() {
        return (if) d.a(this.b, new if());
    }

    private il(MembersInjector<if> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<if> a(MembersInjector<if> membersInjector) {
        return new il(membersInjector);
    }
}
