package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class sl implements c<sk> {
    static final /* synthetic */ boolean a = (!sl.class.desiredAssertionStatus());
    private final MembersInjector<sk> b;

    public final /* synthetic */ Object get() {
        return (sk) d.a(this.b, new sk());
    }

    private sl(MembersInjector<sk> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<sk> a(MembersInjector<sk> membersInjector) {
        return new sl(membersInjector);
    }
}
