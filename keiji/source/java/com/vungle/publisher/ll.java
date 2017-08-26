package com.vungle.publisher;

import com.vungle.publisher.lk.b;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ll implements c<b> {
    static final /* synthetic */ boolean a = (!ll.class.desiredAssertionStatus());
    private final MembersInjector<b> b;

    public final /* synthetic */ Object get() {
        return (b) d.a(this.b, new b());
    }

    private ll(MembersInjector<b> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<b> a(MembersInjector<b> membersInjector) {
        return new ll(membersInjector);
    }
}
