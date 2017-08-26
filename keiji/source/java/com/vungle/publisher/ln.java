package com.vungle.publisher;

import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ln extends lk {
    String a;
    private String b;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.lk.a {
        @Inject
        Provider<ln> a;

        @Inject
        a() {
        }

        public final ln a(String str) {
            ln lnVar = (ln) this.a.get();
            lnVar.a = str;
            return lnVar;
        }
    }

    @Inject
    ln() {
    }

    public final boolean c() {
        return this.a != null;
    }

    public final String d() {
        if (this.b == null) {
            this.b = ags.c(this.a);
        }
        return this.b;
    }

    public final void a(Bundle bundle) {
        bundle.putString("webViewRootContentString", this.a);
    }
}
