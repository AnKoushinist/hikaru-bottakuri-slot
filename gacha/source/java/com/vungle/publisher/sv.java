package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class sv implements c<su> {
    static final /* synthetic */ boolean a = (!sv.class.desiredAssertionStatus());
    private final MembersInjector<su> b;

    public final /* synthetic */ Object get() {
        return (su) d.a(this.b, new su());
    }

    private sv(MembersInjector<su> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<su> a(MembersInjector<su> membersInjector) {
        return new sv(membersInjector);
    }
}
