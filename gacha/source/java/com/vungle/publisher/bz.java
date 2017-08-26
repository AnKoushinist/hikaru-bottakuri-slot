package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class bz implements c<by> {
    static final /* synthetic */ boolean a = (!bz.class.desiredAssertionStatus());
    private final MembersInjector<by> b;

    public final /* synthetic */ Object get() {
        return (by) d.a(this.b, new by());
    }

    private bz(MembersInjector<by> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<by> a(MembersInjector<by> membersInjector) {
        return new bz(membersInjector);
    }
}
