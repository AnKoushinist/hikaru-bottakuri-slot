package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class gi implements c<gh> {
    static final /* synthetic */ boolean a = (!gi.class.desiredAssertionStatus());
    private final MembersInjector<gh> b;

    public final /* synthetic */ Object get() {
        return (gh) d.a(this.b, new gh());
    }

    private gi(MembersInjector<gh> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<gh> a(MembersInjector<gh> membersInjector) {
        return new gi(membersInjector);
    }
}
