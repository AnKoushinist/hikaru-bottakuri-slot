package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class abq implements c<abp> {
    static final /* synthetic */ boolean a = (!abq.class.desiredAssertionStatus());
    private final MembersInjector<abp> b;

    public final /* synthetic */ Object get() {
        return (abp) d.a(this.b, new abp());
    }

    private abq(MembersInjector<abp> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<abp> a(MembersInjector<abp> membersInjector) {
        return new abq(membersInjector);
    }
}
