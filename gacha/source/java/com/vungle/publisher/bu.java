package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class bu implements c<bt> {
    static final /* synthetic */ boolean a = (!bu.class.desiredAssertionStatus());
    private final MembersInjector<bt> b;

    public final /* synthetic */ Object get() {
        return (bt) d.a(this.b, new bt());
    }

    private bu(MembersInjector<bt> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<bt> a(MembersInjector<bt> membersInjector) {
        return new bu(membersInjector);
    }
}
