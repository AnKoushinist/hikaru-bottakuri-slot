package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class sy implements c<sx> {
    static final /* synthetic */ boolean a = (!sy.class.desiredAssertionStatus());
    private final MembersInjector<sx> b;

    public final /* synthetic */ Object get() {
        return (sx) d.a(this.b, new sx());
    }

    private sy(MembersInjector<sx> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<sx> a(MembersInjector<sx> membersInjector) {
        return new sy(membersInjector);
    }
}
