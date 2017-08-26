package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class gu implements c<gt> {
    static final /* synthetic */ boolean a = (!gu.class.desiredAssertionStatus());
    private final MembersInjector<gt> b;

    public final /* synthetic */ Object get() {
        return (gt) d.a(this.b, new gt());
    }

    private gu(MembersInjector<gt> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<gt> a(MembersInjector<gt> membersInjector) {
        return new gu(membersInjector);
    }
}
