package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class vp implements c<vo> {
    static final /* synthetic */ boolean a = (!vp.class.desiredAssertionStatus());
    private final MembersInjector<vo> b;

    public final /* synthetic */ Object get() {
        return (vo) d.a(this.b, new vo());
    }

    private vp(MembersInjector<vo> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<vo> a(MembersInjector<vo> membersInjector) {
        return new vp(membersInjector);
    }
}
