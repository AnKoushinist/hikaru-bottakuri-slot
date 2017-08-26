package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class lo implements c<ln> {
    static final /* synthetic */ boolean a = (!lo.class.desiredAssertionStatus());
    private final MembersInjector<ln> b;

    public final /* synthetic */ Object get() {
        return (ln) d.a(this.b, new ln());
    }

    private lo(MembersInjector<ln> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ln> a(MembersInjector<ln> membersInjector) {
        return new lo(membersInjector);
    }
}
