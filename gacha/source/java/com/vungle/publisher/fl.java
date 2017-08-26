package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class fl implements c<fk> {
    static final /* synthetic */ boolean a = (!fl.class.desiredAssertionStatus());
    private final MembersInjector<fk> b;

    public final /* synthetic */ Object get() {
        return (fk) d.a(this.b, new fk());
    }

    private fl(MembersInjector<fk> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<fk> a(MembersInjector<fk> membersInjector) {
        return new fl(membersInjector);
    }
}
