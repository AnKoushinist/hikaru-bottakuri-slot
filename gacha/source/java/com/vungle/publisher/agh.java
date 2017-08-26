package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class agh implements c<agg> {
    static final /* synthetic */ boolean a = (!agh.class.desiredAssertionStatus());
    private final MembersInjector<agg> b;

    public final /* synthetic */ Object get() {
        return (agg) d.a(this.b, new agg());
    }

    private agh(MembersInjector<agg> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<agg> a(MembersInjector<agg> membersInjector) {
        return new agh(membersInjector);
    }
}
