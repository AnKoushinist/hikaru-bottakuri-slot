package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class sg implements c<sf> {
    static final /* synthetic */ boolean a = (!sg.class.desiredAssertionStatus());
    private final MembersInjector<sf> b;

    public final /* synthetic */ Object get() {
        return (sf) d.a(this.b, new sf());
    }

    private sg(MembersInjector<sf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<sf> a(MembersInjector<sf> membersInjector) {
        return new sg(membersInjector);
    }
}
