package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class iy implements c<ht> {
    static final /* synthetic */ boolean a = (!iy.class.desiredAssertionStatus());
    private final MembersInjector<ht> b;

    public final /* synthetic */ Object get() {
        return (ht) d.a(this.b, new ht());
    }

    private iy(MembersInjector<ht> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ht> a(MembersInjector<ht> membersInjector) {
        return new iy(membersInjector);
    }
}
