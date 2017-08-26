package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class yk implements c<yj> {
    static final /* synthetic */ boolean a = (!yk.class.desiredAssertionStatus());
    private final MembersInjector<yj> b;

    public final /* synthetic */ Object get() {
        return (yj) d.a(this.b, new yj());
    }

    private yk(MembersInjector<yj> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<yj> a(MembersInjector<yj> membersInjector) {
        return new yk(membersInjector);
    }
}
