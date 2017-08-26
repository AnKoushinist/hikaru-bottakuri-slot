package com.vungle.publisher;

import dagger.a.c;
import dagger.a.e;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class rv implements c<String> {
    static final /* synthetic */ boolean a = (!rv.class.desiredAssertionStatus());
    private final re b;

    public final /* synthetic */ Object get() {
        re reVar = this.b;
        return (String) e.a(reVar.f == null ? BuildConfig.FLAVOR : reVar.f, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rv(re reVar) {
        if (a || reVar != null) {
            this.b = reVar;
            return;
        }
        throw new AssertionError();
    }

    public static c<String> a(re reVar) {
        return new rv(reVar);
    }
}
