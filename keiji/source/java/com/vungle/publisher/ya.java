package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ya implements c<xz> {
    static final /* synthetic */ boolean a = (!ya.class.desiredAssertionStatus());
    private final MembersInjector<xz> b;

    public final /* synthetic */ Object get() {
        return (xz) d.a(this.b, new xz());
    }

    private ya(MembersInjector<xz> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<xz> a(MembersInjector<xz> membersInjector) {
        return new ya(membersInjector);
    }
}
