package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class fj extends jn<fj, fe, fk, eo, en> implements ec {
    @Inject
    a v;
    @Inject
    a w;
    public do x;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jn.a<fj, fe, fk, eo, en, adr> {
        @Inject
        com.vungle.publisher.eo.a c;
        @Inject
        a e;
        @Inject
        Provider<fj> f;
        @Inject
        a g;

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
            return this.c;
        }

        protected final /* bridge */ /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            return a((fj) dlVar, cursor, false);
        }

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new fj[i];
        }

        protected final /* synthetic */ dl c_() {
            return (fj) this.f.get();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
            return this.e;
        }

        @Inject
        protected a() {
        }

        public final j f() {
            return j.vungle_local;
        }

        private fj a(fj fjVar, Cursor cursor, boolean z) {
            super.a((cq) fjVar, cursor, z);
            fjVar.o = cb.e(cursor, "download_end_millis");
            return fjVar;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.v;
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cq.a b() {
        return this.v;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
        return this.w;
    }

    @Inject
    protected fj() {
    }

    public final void a_(Long l) {
        this.x.a(l);
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.x.a(a);
        return a;
    }

    public final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "download_end_millis", this.o, false);
        return m;
    }
}
