package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class jz implements c<jy> {
    static final /* synthetic */ boolean a = (!jz.class.desiredAssertionStatus());
    private final MembersInjector<jy> b;

    public final /* synthetic */ Object get() {
        return (jy) d.a(this.b, new jy());
    }

    private jz(MembersInjector<jy> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<jy> a(MembersInjector<jy> membersInjector) {
        return new jz(membersInjector);
    }
}
