package com.vungle.publisher;

import com.vungle.publisher.cj.c;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class hu extends jk<hu, ht, aea> {
    @Inject
    a q;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jk.a<hu, ht, aea> {
        @Inject
        Provider<hu> c;
        @Inject
        com.vungle.publisher.ht.a e;
        @Inject
        com.vungle.publisher.ip.a f;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new String[i];
        }

        protected final /* synthetic */ dl c_() {
            return (hu) this.c.get();
        }

        protected final /* bridge */ /* synthetic */ a e() {
            return this.f;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.jj.a f() {
            return this.e;
        }

        @Inject
        a() {
        }

        public final hu a(aea com_vungle_publisher_aea) {
            hu huVar = (hu) super.a((aed) com_vungle_publisher_aea);
            huVar.a(c.ready);
            return huVar;
        }

        protected final j a() {
            return j.vungle_streaming;
        }

        protected final String c() {
            return "ad";
        }

        protected final String[] a_(int i) {
            return new String[i];
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
        return this.q;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.q;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.jk.a r() {
        return this.q;
    }

    @Inject
    protected hu() {
    }

    protected final boolean b() {
        return true;
    }
}
