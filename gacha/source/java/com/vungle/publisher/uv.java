package com.vungle.publisher;

import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class uv extends wu {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.wu.a<uv> {
        @Inject
        Provider<uv> a;

        protected final /* synthetic */ vs b() {
            return (uv) this.a.get();
        }

        @Inject
        a() {
        }

        public final uv a(ly lyVar) {
            uv uvVar = (uv) d();
            uvVar.d = lyVar.d();
            return uvVar;
        }

        protected final String a() {
            return "installedApps";
        }
    }

    @Inject
    uv() {
    }

    protected final c a() {
        return c.appFingerprint;
    }
}
