package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ws implements c<wr> {
    static final /* synthetic */ boolean a = (!ws.class.desiredAssertionStatus());
    private final MembersInjector<wr> b;

    public final /* synthetic */ Object get() {
        return (wr) d.a(this.b, new wr());
    }

    private ws(MembersInjector<wr> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<wr> a(MembersInjector<wr> membersInjector) {
        return new ws(membersInjector);
    }
}
