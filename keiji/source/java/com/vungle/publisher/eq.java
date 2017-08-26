package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class eq implements c<ep> {
    static final /* synthetic */ boolean a = (!eq.class.desiredAssertionStatus());
    private final MembersInjector<ep> b;

    public final /* synthetic */ Object get() {
        return (ep) d.a(this.b, new ep());
    }

    private eq(MembersInjector<ep> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ep> a(MembersInjector<ep> membersInjector) {
        return new eq(membersInjector);
    }
}
