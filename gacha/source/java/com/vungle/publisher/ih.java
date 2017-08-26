package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ih implements c<ig> {
    static final /* synthetic */ boolean a = (!ih.class.desiredAssertionStatus());
    private final MembersInjector<ig> b;

    public final /* synthetic */ Object get() {
        return (ig) d.a(this.b, new ig());
    }

    private ih(MembersInjector<ig> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ig> a(MembersInjector<ig> membersInjector) {
        return new ih(membersInjector);
    }
}
