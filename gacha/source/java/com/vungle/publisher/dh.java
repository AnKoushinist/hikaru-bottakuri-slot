package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class dh implements c<dg> {
    static final /* synthetic */ boolean a = (!dh.class.desiredAssertionStatus());
    private final MembersInjector<dg> b;

    public final /* synthetic */ Object get() {
        return (dg) d.a(this.b, new dg());
    }

    private dh(MembersInjector<dg> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<dg> a(MembersInjector<dg> membersInjector) {
        return new dh(membersInjector);
    }
}
