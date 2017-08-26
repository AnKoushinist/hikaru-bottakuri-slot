package com.vungle.sdk;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class VungleAdvert_Factory implements c<VungleAdvert> {
    static final /* synthetic */ boolean a = (!VungleAdvert_Factory.class.desiredAssertionStatus());
    private final MembersInjector<VungleAdvert> b;

    public VungleAdvert_Factory(MembersInjector<VungleAdvert> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final VungleAdvert get() {
        return (VungleAdvert) d.a(this.b, new VungleAdvert());
    }

    public static c<VungleAdvert> create(MembersInjector<VungleAdvert> membersInjector) {
        return new VungleAdvert_Factory(membersInjector);
    }
}
