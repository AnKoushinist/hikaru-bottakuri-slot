package com.vungle.publisher;

import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.cj.c;
import com.vungle.publisher.jp.b;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class eo extends jk<eo, en, adr> implements dn<eo> {
    ep q;
    public ds r;
    boolean v;
    @Inject
    a w;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jk.a<eo, en, adr> implements dw<eo, adr> {
        @Inject
        Provider<String> c;
        @Inject
        com.vungle.publisher.ep.a e;
        @Inject
        b f;
        @Inject
        Provider<eo> g;
        @Inject
        com.vungle.publisher.en.a h;
        @Inject
        bt i;
        @Inject
        com.vungle.publisher.eu.a j;
        @Inject
        com.vungle.publisher.ds.a k;
        @Inject
        com.vungle.publisher.ft.a l;

        protected final /* synthetic */ dl c_() {
            return (eo) this.g.get();
        }

        protected final /* bridge */ /* synthetic */ a e() {
            return this.l;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.jj.a f() {
            return this.h;
        }

        public final /* synthetic */ dx i_() {
            return g();
        }

        public final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a j_() {
            return this;
        }

        @Inject
        a() {
        }

        private eo a(adr com_vungle_publisher_adr) {
            eo eoVar = (eo) super.a((aed) com_vungle_publisher_adr);
            eoVar.a((String) this.c.get());
            eoVar.q = this.e.a(eoVar, com_vungle_publisher_adr, el.b.postRoll);
            eoVar.r = this.k.a(eoVar);
            eoVar.a(c.aware);
            return eoVar;
        }

        protected final j a() {
            return j.vungle_local;
        }

        public final int a(List<eo> list) {
            return g().a(list);
        }

        private eo a(eo eoVar, Cursor cursor, boolean z) {
            super.a((jk) eoVar, cursor, z);
            eoVar.a(cb.f(cursor, "parent_path"));
            eoVar.r = this.k.a(eoVar);
            if (z) {
                a(eoVar, z);
            }
            return eoVar;
        }

        final ep a(eo eoVar, boolean z) {
            if (eoVar.v) {
                return eoVar.q;
            }
            ep epVar = (ep) this.e.a((String) eoVar.t, el.b.postRoll, z);
            eoVar.q = epVar;
            eoVar.v = true;
            return epVar;
        }

        private eu g() {
            return (eu) this.j.a(this);
        }
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
        return this.w;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.w;
    }

    public final /* synthetic */ String d() {
        return (String) super.w();
    }

    public final /* bridge */ /* synthetic */ cj h_() {
        return this;
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.jk.a r() {
        return this.w;
    }

    public final /* synthetic */ Object v() {
        return q();
    }

    @Inject
    protected eo() {
    }

    public final ep p() {
        return this.w.a(this, false);
    }

    public final void a(c cVar) {
        c cVar2 = this.e;
        super.a(cVar);
        this.r.a(cVar2, cVar);
    }

    public final List<gg<eo>> f_() {
        List<gg<eo>> arrayList = new ArrayList();
        en enVar = (en) u();
        if (enVar != null) {
            arrayList.add(enVar);
        }
        ep p = p();
        if (p != null) {
            arrayList.add(p);
        }
        return arrayList;
    }

    public final boolean g_() {
        boolean z;
        boolean z2 = false;
        en enVar = (en) u();
        em p = p();
        boolean z3 = enVar != null;
        if (p != null) {
            z = true;
        } else {
            z = false;
        }
        if (z3 || z) {
            z2 = true;
        }
        String z4 = z();
        if (z2) {
            if (z3) {
                Logger.v(Logger.PREPARE_TAG, z4 + " has " + el.b.localVideo + ": " + enVar.b.b);
            }
            if (z) {
                Logger.v(Logger.PREPARE_TAG, z4 + " has " + el.b.postRoll + ": " + p.g.b);
            }
        } else {
            a(c.invalid);
        }
        return z2;
    }

    public final boolean b() {
        return qx.a(h());
    }

    public final String q() {
        String q = super.q();
        if (this.q != null) {
            this.q.v();
        }
        return q;
    }

    public final int b_() {
        int b_ = super.b_();
        if (b_ == 1 && this.q != null) {
            this.q.b_();
        }
        return b_;
    }
}
