package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class h implements c<c> {
    static final /* synthetic */ boolean a = (!h.class.desiredAssertionStatus());
    private final MembersInjector<c> b;

    public final /* synthetic */ Object get() {
        return (c) d.a(this.b, new c());
    }

    private h(MembersInjector<c> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<c> a(MembersInjector<c> membersInjector) {
        return new h(membersInjector);
    }
}
