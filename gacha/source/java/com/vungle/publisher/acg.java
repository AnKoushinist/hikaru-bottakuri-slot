package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class acg implements c<acf> {
    static final /* synthetic */ boolean a = (!acg.class.desiredAssertionStatus());
    private final MembersInjector<acf> b;

    public final /* synthetic */ Object get() {
        return (acf) d.a(this.b, new acf());
    }

    private acg(MembersInjector<acf> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<acf> a(MembersInjector<acf> membersInjector) {
        return new acg(membersInjector);
    }
}
