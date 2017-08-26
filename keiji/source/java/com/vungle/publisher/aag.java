package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class aag implements c<aaf> {
    static final /* synthetic */ boolean a = (!aag.class.desiredAssertionStatus());
    private final MembersInjector<aaf> b;

    public final /* synthetic */ Object get() {
        return (aaf) d.a(this.b, new aaf());
    }

    private aag(MembersInjector<aaf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<aaf> a(MembersInjector<aaf> membersInjector) {
        return new aag(membersInjector);
    }
}
