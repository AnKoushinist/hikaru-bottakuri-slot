package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class uw implements c<uv> {
    static final /* synthetic */ boolean a = (!uw.class.desiredAssertionStatus());
    private final MembersInjector<uv> b;

    public final /* synthetic */ Object get() {
        return (uv) d.a(this.b, new uv());
    }

    private uw(MembersInjector<uv> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<uv> a(MembersInjector<uv> membersInjector) {
        return new uw(membersInjector);
    }
}
