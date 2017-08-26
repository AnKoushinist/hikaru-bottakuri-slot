package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ou implements c<c> {
    static final /* synthetic */ boolean a = (!ou.class.desiredAssertionStatus());
    private final MembersInjector<c> b;

    public final /* synthetic */ Object get() {
        return (c) d.a(this.b, new c());
    }

    private ou(MembersInjector<c> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<c> a(MembersInjector<c> membersInjector) {
        return new ou(membersInjector);
    }
}
