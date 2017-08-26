package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class wo implements c<wn> {
    static final /* synthetic */ boolean a = (!wo.class.desiredAssertionStatus());
    private final MembersInjector<wn> b;

    public final /* synthetic */ Object get() {
        return (wn) d.a(this.b, new wn());
    }

    private wo(MembersInjector<wn> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<wn> a(MembersInjector<wn> membersInjector) {
        return new wo(membersInjector);
    }
}
