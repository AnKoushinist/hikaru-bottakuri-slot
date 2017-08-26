package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class gz implements c<gy> {
    static final /* synthetic */ boolean a = (!gz.class.desiredAssertionStatus());
    private final MembersInjector<gy> b;

    public final /* synthetic */ Object get() {
        return (gy) d.a(this.b, new gy());
    }

    private gz(MembersInjector<gy> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<gy> a(MembersInjector<gy> membersInjector) {
        return new gz(membersInjector);
    }
}
