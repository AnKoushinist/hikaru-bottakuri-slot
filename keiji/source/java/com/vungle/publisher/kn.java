package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class kn extends cq<kn, ki, ko, js> implements ec {
    public do v;
    public String w;
    @Inject
    a x;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cq.a<kn, ki, ko, js, aef> {
        @Inject
        com.vungle.publisher.js.a c;
        @Inject
        com.vungle.publisher.ki.a e;
        @Inject
        Provider<kn> f;
        @Inject
        a g;

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
            return this.c;
        }

        protected final /* bridge */ /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            return a((kn) dlVar, cursor, false);
        }

        protected final /* synthetic */ dl c_() {
            return (kn) this.f.get();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
            return this.e;
        }

        @Inject
        a() {
        }

        private kn a(kn knVar, Cursor cursor, boolean z) {
            super.a((cq) knVar, cursor, z);
            knVar.o = cb.e(cursor, "download_end_millis");
            knVar.w = cb.f(cursor, "template_id");
            return knVar;
        }

        public final j f() {
            return j.vungle_mraid;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.x;
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cq.a b() {
        return this.x;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
        return this.x.e;
    }

    @Inject
    kn() {
    }

    public final void a_(Long l) {
        this.v.a(l);
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.v.a(a);
        if (z) {
            a.put("template_id", this.w);
        }
        return a;
    }
}
