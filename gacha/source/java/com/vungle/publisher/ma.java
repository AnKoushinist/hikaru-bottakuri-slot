package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ma implements c<lz> {
    static final /* synthetic */ boolean a = (!ma.class.desiredAssertionStatus());
    private final MembersInjector<lz> b;

    public final /* synthetic */ Object get() {
        return (lz) d.a(this.b, new lz());
    }

    private ma(MembersInjector<lz> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<lz> a(MembersInjector<lz> membersInjector) {
        return new ma(membersInjector);
    }
}
