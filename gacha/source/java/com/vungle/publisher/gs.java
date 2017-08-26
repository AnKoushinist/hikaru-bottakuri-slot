package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class gs extends dm implements dn<gs> {
    String q;
    @Inject
    a r;
    @Inject
    com.vungle.publisher.ln.a v;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cj.a<gs, adu> implements dw<gs, adu> {
        @Inject
        Provider<gs> c;
        @Inject
        com.vungle.publisher.gt.a e;

        protected final /* bridge */ /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            return a((gs) dlVar, cursor, false);
        }

        protected final /* synthetic */ dl c_() {
            return (gs) this.c.get();
        }

        public final /* synthetic */ dx i_() {
            return e();
        }

        @Inject
        a() {
        }

        private gs a(gs gsVar, Cursor cursor, boolean z) {
            super.a(gsVar, cursor, z);
            gsVar.q = cb.f(cursor, "html_content");
            return gsVar;
        }

        protected final j a() {
            return j.third_party_mraid;
        }

        public final com.vungle.publisher.cj.a<gs, adu> j_() {
            return this;
        }

        public final int a(List<gs> list) {
            return e().a(list);
        }

        private gt e() {
            return (gt) this.e.a(this);
        }
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
        return this.r;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.r;
    }

    public final /* synthetic */ String d() {
        return (String) super.w();
    }

    public final /* bridge */ /* synthetic */ cj h_() {
        return this;
    }

    public final /* synthetic */ lk p() {
        return this.v.a(this.q);
    }

    @Inject
    gs() {
    }

    protected final boolean b() {
        return true;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("html_content", this.q);
        return a;
    }

    public final boolean g_() {
        return true;
    }

    public final List<gg<gs>> f_() {
        return new ArrayList();
    }
}
