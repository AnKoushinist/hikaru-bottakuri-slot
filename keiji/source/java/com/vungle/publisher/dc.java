package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class dc implements c<db> {
    static final /* synthetic */ boolean a = (!dc.class.desiredAssertionStatus());
    private final MembersInjector<db> b;

    public final /* synthetic */ Object get() {
        return (db) d.a(this.b, new db());
    }

    private dc(MembersInjector<db> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<db> a(MembersInjector<db> membersInjector) {
        return new dc(membersInjector);
    }
}
