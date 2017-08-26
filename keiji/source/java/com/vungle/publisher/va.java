package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class va implements c<uz> {
    static final /* synthetic */ boolean a = (!va.class.desiredAssertionStatus());
    private final MembersInjector<uz> b;

    public final /* synthetic */ Object get() {
        return (uz) d.a(this.b, new uz());
    }

    private va(MembersInjector<uz> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<uz> a(MembersInjector<uz> membersInjector) {
        return new va(membersInjector);
    }
}
