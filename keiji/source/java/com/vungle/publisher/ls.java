package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class ls implements c<lr> {
    static final /* synthetic */ boolean a = (!ls.class.desiredAssertionStatus());
    private final MembersInjector<lr> b;

    public final /* synthetic */ Object get() {
        return (lr) d.a(this.b, new lr());
    }

    private ls(MembersInjector<lr> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<lr> a(MembersInjector<lr> membersInjector) {
        return new ls(membersInjector);
    }
}
