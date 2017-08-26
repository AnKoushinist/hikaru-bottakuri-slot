package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class oc implements c<ob> {
    static final /* synthetic */ boolean a = (!oc.class.desiredAssertionStatus());
    private final MembersInjector<ob> b;

    public final /* synthetic */ Object get() {
        return (ob) d.a(this.b, new ob());
    }

    private oc(MembersInjector<ob> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ob> a(MembersInjector<ob> membersInjector) {
        return new oc(membersInjector);
    }
}
