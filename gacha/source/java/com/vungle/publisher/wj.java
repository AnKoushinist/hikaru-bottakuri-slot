package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class wj implements c<wi> {
    static final /* synthetic */ boolean a = (!wj.class.desiredAssertionStatus());
    private final MembersInjector<wi> b;

    public final /* synthetic */ Object get() {
        return (wi) d.a(this.b, new wi());
    }

    private wj(MembersInjector<wi> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<wi> a(MembersInjector<wi> membersInjector) {
        return new wj(membersInjector);
    }
}
