package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.vungle.publisher.gh.b;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class en extends jj<eo> implements b<eo> {
    String a;
    public gh b;
    @Inject
    com.vungle.publisher.eo.a c;
    @Inject
    a d;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jj.a<eo, en, adr> {
        private static final el.b c = el.b.localVideo;
        @Inject
        Provider<en> a;
        @Inject
        a b;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new en[i];
        }

        protected final /* synthetic */ dl c_() {
            en enVar = (en) this.a.get();
            enVar.b = this.b.a(enVar);
            return enVar;
        }

        @Inject
        protected a() {
        }

        protected final el.b a() {
            return c;
        }

        private en a(eo eoVar, adr com_vungle_publisher_adr) {
            en enVar = (en) super.a((jk) eoVar, (aed) com_vungle_publisher_adr);
            if (enVar != null) {
                enVar.a = com_vungle_publisher_adr.m;
                enVar.a(com_vungle_publisher_adr.l);
                enVar.b.b = com_vungle_publisher_adr.j();
                enVar.r = c;
            }
            return enVar;
        }

        private en a(en enVar, Cursor cursor, boolean z) {
            super.a((jj) enVar, cursor, z);
            enVar.b.a(cursor);
            enVar.a = cb.f(cursor, "checksum");
            return enVar;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.d;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a r() {
        return this.c;
    }

    @Inject
    protected en() {
    }

    public final String e() {
        return t() + ".mp4";
    }

    public final String f() {
        return this.b.b;
    }

    public final void a(Integer num) {
        this.b.c = num;
    }

    public final String g() {
        return this.b.c();
    }

    public final Uri q() {
        return Uri.fromFile(new File(this.b.c()));
    }

    public final boolean h() {
        return this.b.e();
    }

    public final boolean o() {
        return this.b.f();
    }

    public final boolean i() {
        return this.b.f();
    }

    public final boolean p() {
        return this.b.g();
    }

    public final int n() {
        return this.b.d();
    }

    public final boolean j() {
        return this.b.h();
    }

    public final int k() {
        return super.n();
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.b.a(a);
        a.put("checksum", this.a);
        return a;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        this.b.a(m);
        dl.a(m, "checksum", this.a, false);
        return m;
    }
}
