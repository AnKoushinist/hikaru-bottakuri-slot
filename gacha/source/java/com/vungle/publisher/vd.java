package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class vd implements c<vc> {
    static final /* synthetic */ boolean a = (!vd.class.desiredAssertionStatus());
    private final MembersInjector<vc> b;

    public final /* synthetic */ Object get() {
        return (vc) d.a(this.b, new vc());
    }

    private vd(MembersInjector<vc> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<vc> a(MembersInjector<vc> membersInjector) {
        return new vd(membersInjector);
    }
}
