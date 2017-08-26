package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class lw implements c<lv> {
    static final /* synthetic */ boolean a = (!lw.class.desiredAssertionStatus());
    private final MembersInjector<lv> b;

    public final /* synthetic */ Object get() {
        return (lv) d.a(this.b, new lv());
    }

    private lw(MembersInjector<lv> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<lv> a(MembersInjector<lv> membersInjector) {
        return new lw(membersInjector);
    }
}
