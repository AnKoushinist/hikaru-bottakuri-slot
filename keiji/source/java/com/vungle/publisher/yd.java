package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class yd implements c<yc> {
    static final /* synthetic */ boolean a = (!yd.class.desiredAssertionStatus());
    private final MembersInjector<yc> b;

    public final /* synthetic */ Object get() {
        return (yc) d.a(this.b, new yc());
    }

    private yd(MembersInjector<yc> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<yc> a(MembersInjector<yc> membersInjector) {
        return new yd(membersInjector);
    }
}
