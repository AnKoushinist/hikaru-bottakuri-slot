package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class cl implements c<ck> {
    static final /* synthetic */ boolean a = (!cl.class.desiredAssertionStatus());
    private final MembersInjector<ck> b;

    public final /* synthetic */ Object get() {
        return (ck) d.a(this.b, new ck());
    }

    private cl(MembersInjector<ck> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<ck> a(MembersInjector<ck> membersInjector) {
        return new cl(membersInjector);
    }
}
