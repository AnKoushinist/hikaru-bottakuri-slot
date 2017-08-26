package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class jd implements c<jc> {
    static final /* synthetic */ boolean a = (!jd.class.desiredAssertionStatus());
    private final MembersInjector<jc> b;

    public final /* synthetic */ Object get() {
        return (jc) d.a(this.b, new jc());
    }

    private jd(MembersInjector<jc> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<jc> a(MembersInjector<jc> membersInjector) {
        return new jd(membersInjector);
    }
}
