package com.vungle.publisher;

import com.vungle.publisher.jp.b;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class jq implements c<b> {
    static final /* synthetic */ boolean a = (!jq.class.desiredAssertionStatus());
    private final MembersInjector<b> b;

    public final /* synthetic */ Object get() {
        return (b) d.a(this.b, new b());
    }

    private jq(MembersInjector<b> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<b> a(MembersInjector<b> membersInjector) {
        return new jq(membersInjector);
    }
}
