package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class kp implements c<ko> {
    static final /* synthetic */ boolean a = (!kp.class.desiredAssertionStatus());
    private final MembersInjector<ko> b;

    public final /* synthetic */ Object get() {
        return (ko) d.a(this.b, new ko());
    }

    private kp(MembersInjector<ko> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ko> a(MembersInjector<ko> membersInjector) {
        return new kp(membersInjector);
    }
}
