package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class oz implements c<oy> {
    static final /* synthetic */ boolean a = (!oz.class.desiredAssertionStatus());
    private final MembersInjector<oy> b;

    public final /* synthetic */ Object get() {
        return (oy) d.a(this.b, new oy());
    }

    private oz(MembersInjector<oy> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<oy> a(MembersInjector<oy> membersInjector) {
        return new oz(membersInjector);
    }
}
