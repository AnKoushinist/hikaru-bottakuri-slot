package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class zo implements c<zn> {
    static final /* synthetic */ boolean a = (!zo.class.desiredAssertionStatus());
    private final MembersInjector<zn> b;

    public final /* synthetic */ Object get() {
        return (zn) d.a(this.b, new zn());
    }

    private zo(MembersInjector<zn> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<zn> a(MembersInjector<zn> membersInjector) {
        return new zo(membersInjector);
    }
}
