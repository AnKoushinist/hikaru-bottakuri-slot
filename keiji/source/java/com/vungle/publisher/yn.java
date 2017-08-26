package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class yn implements c<ym> {
    static final /* synthetic */ boolean a = (!yn.class.desiredAssertionStatus());
    private final MembersInjector<ym> b;

    public final /* synthetic */ Object get() {
        return (ym) d.a(this.b, new ym());
    }

    private yn(MembersInjector<ym> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ym> a(MembersInjector<ym> membersInjector) {
        return new yn(membersInjector);
    }
}
