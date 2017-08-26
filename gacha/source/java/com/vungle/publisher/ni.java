package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ni implements c<nh> {
    static final /* synthetic */ boolean a = (!ni.class.desiredAssertionStatus());
    private final MembersInjector<nh> b;

    public final /* synthetic */ Object get() {
        return (nh) d.a(this.b, new nh());
    }

    private ni(MembersInjector<nh> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<nh> a(MembersInjector<nh> membersInjector) {
        return new ni(membersInjector);
    }
}
