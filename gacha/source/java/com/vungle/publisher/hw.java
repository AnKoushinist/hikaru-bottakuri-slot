package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class hw implements c<hv> {
    static final /* synthetic */ boolean a = (!hw.class.desiredAssertionStatus());
    private final MembersInjector<hv> b;

    public final /* synthetic */ Object get() {
        return (hv) d.a(this.b, new hv());
    }

    private hw(MembersInjector<hv> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<hv> a(MembersInjector<hv> membersInjector) {
        return new hw(membersInjector);
    }
}
