package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class cd implements c<cc> {
    static final /* synthetic */ boolean a = (!cd.class.desiredAssertionStatus());
    private final MembersInjector<cc> b;

    public final /* synthetic */ Object get() {
        return (cc) d.a(this.b, new cc());
    }

    private cd(MembersInjector<cc> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<cc> a(MembersInjector<cc> membersInjector) {
        return new cd(membersInjector);
    }
}
