package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class FullScreenAdActivity_Factory implements c<FullScreenAdActivity> {
    static final /* synthetic */ boolean a = (!FullScreenAdActivity_Factory.class.desiredAssertionStatus());
    private final MembersInjector<FullScreenAdActivity> b;

    public FullScreenAdActivity_Factory(MembersInjector<FullScreenAdActivity> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final FullScreenAdActivity get() {
        return (FullScreenAdActivity) d.a(this.b, new FullScreenAdActivity());
    }

    public static c<FullScreenAdActivity> create(MembersInjector<FullScreenAdActivity> membersInjector) {
        return new FullScreenAdActivity_Factory(membersInjector);
    }
}
