package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class wf implements c<we> {
    static final /* synthetic */ boolean a = (!wf.class.desiredAssertionStatus());
    private final MembersInjector<we> b;

    public final /* synthetic */ Object get() {
        return (we) d.a(this.b, new we());
    }

    private wf(MembersInjector<we> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<we> a(MembersInjector<we> membersInjector) {
        return new wf(membersInjector);
    }
}
