package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class vg implements c<vf> {
    static final /* synthetic */ boolean a = (!vg.class.desiredAssertionStatus());
    private final MembersInjector<vf> b;

    public final /* synthetic */ Object get() {
        return (vf) d.a(this.b, new vf());
    }

    private vg(MembersInjector<vf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<vf> a(MembersInjector<vf> membersInjector) {
        return new vg(membersInjector);
    }
}
