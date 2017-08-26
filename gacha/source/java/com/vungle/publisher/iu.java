package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class iu implements c<hu> {
    static final /* synthetic */ boolean a = (!iu.class.desiredAssertionStatus());
    private final MembersInjector<hu> b;

    public final /* synthetic */ Object get() {
        return (hu) d.a(this.b, new hu());
    }

    private iu(MembersInjector<hu> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<hu> a(MembersInjector<hu> membersInjector) {
        return new iu(membersInjector);
    }
}
