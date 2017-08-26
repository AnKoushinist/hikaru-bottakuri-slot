package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class vm implements c<vl> {
    static final /* synthetic */ boolean a = (!vm.class.desiredAssertionStatus());
    private final MembersInjector<vl> b;

    public final /* synthetic */ Object get() {
        return (vl) d.a(this.b, new vl());
    }

    private vm(MembersInjector<vl> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<vl> a(MembersInjector<vl> membersInjector) {
        return new vm(membersInjector);
    }
}
