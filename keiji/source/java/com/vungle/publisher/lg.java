package com.vungle.publisher;

import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class lg extends lk {
    String a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.lk.a {
        @Inject
        Provider<lg> a;

        @Inject
        a() {
        }

        public final lg a(String str) {
            lg lgVar = (lg) this.a.get();
            lgVar.a = str;
            return lgVar;
        }
    }

    @Inject
    lg() {
    }

    public final boolean a() {
        return this.a != null;
    }

    public final String b() {
        return this.a;
    }

    public final void a(Bundle bundle) {
        bundle.putString("webViewRootContentIndexFile", this.a);
    }
}
