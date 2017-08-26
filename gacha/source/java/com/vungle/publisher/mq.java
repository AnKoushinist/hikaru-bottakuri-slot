package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class mq implements c<mp> {
    static final /* synthetic */ boolean a = (!mq.class.desiredAssertionStatus());
    private final MembersInjector<mp> b;

    public final /* synthetic */ Object get() {
        return (mp) d.a(this.b, new mp());
    }

    private mq(MembersInjector<mp> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<mp> a(MembersInjector<mp> membersInjector) {
        return new mq(membersInjector);
    }
}
