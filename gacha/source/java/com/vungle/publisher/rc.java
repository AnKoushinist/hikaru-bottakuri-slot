package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class rc implements c<rb> {
    static final /* synthetic */ boolean a = (!rc.class.desiredAssertionStatus());
    private final MembersInjector<rb> b;

    public final /* synthetic */ Object get() {
        return (rb) d.a(this.b, new rb());
    }

    private rc(MembersInjector<rb> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<rb> a(MembersInjector<rb> membersInjector) {
        return new rc(membersInjector);
    }
}
