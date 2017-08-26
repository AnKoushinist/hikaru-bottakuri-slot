package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.log.Logger;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class gh {
    b<?> a;
    public String b;
    Integer c;
    @Inject
    pn d;
    @Inject
    py e;

    /* compiled from: vungle */
    public interface b<A extends cj> extends gg<A> {
        String e();

        boolean o();

        boolean p();
    }

    @Singleton
    /* compiled from: vungle */
    static class a {
        @Inject
        Provider<gh> a;

        @Inject
        a() {
        }

        final gh a(b<?> bVar) {
            gh ghVar = (gh) this.a.get();
            ghVar.a = bVar;
            return ghVar;
        }
    }

    @Inject
    gh() {
    }

    private String i() {
        return this.a.l();
    }

    final File a() {
        String c = c();
        return c == null ? null : new File(c);
    }

    final boolean b() {
        File a = a();
        if (a == null) {
            Logger.w(Logger.PREPARE_TAG, "null " + this.a.t() + " file for ad " + i());
            return false;
        } else if (a.exists()) {
            Logger.v(Logger.PREPARE_TAG, a.getAbsolutePath() + " exists, " + a.length() + " bytes");
            return true;
        } else {
            Logger.w(Logger.PREPARE_TAG, a.getAbsolutePath() + " missing ");
            return false;
        }
    }

    final String c() {
        return qx.a(this.a.d(), this.a.e());
    }

    private com.vungle.publisher.el.b j() {
        return this.a.t();
    }

    final int d() {
        this.a.j();
        return this.a.k();
    }

    final boolean e() {
        boolean o = this.a.o();
        if (o) {
            com.vungle.publisher.el.a aVar = com.vungle.publisher.el.a.ready;
            Logger.i(Logger.PREPARE_TAG, j() + " " + aVar + " for ad_id " + i());
            this.a.b(aVar);
        } else {
            Object obj = !TextUtils.isEmpty(agl.a("com.vungle.debug")) ? 1 : null;
            if (obj != null) {
                Logger.d(Logger.AD_TAG, "in debug mode");
            } else {
                Logger.v(Logger.AD_TAG, "not in debug mode");
            }
            if (obj != null) {
                Logger.i(Logger.PREPARE_TAG, "debug mode: post-processing failed for " + this.a.z() + " - not deleting " + c());
            } else {
                Logger.d(Logger.PREPARE_TAG, "post-processing failed for " + this.a.z() + " - deleting " + c());
                this.a.j();
            }
            this.a.b(com.vungle.publisher.el.a.aware);
        }
        return o;
    }

    final boolean f() {
        com.vungle.publisher.el.a aVar;
        boolean p = this.a.p();
        String i = i();
        com.vungle.publisher.el.b j = j();
        if (p) {
            Logger.i(Logger.PREPARE_TAG, j + " verified for ad_id " + i);
            aVar = com.vungle.publisher.el.a.ready;
        } else {
            Logger.w(Logger.PREPARE_TAG, j + " failed verification; reprocessing ad_id " + i);
            aVar = com.vungle.publisher.el.a.aware;
        }
        this.a.b(aVar);
        return p;
    }

    final boolean g() {
        if (this.d.o()) {
            String i = i();
            com.vungle.publisher.el.b j = j();
            if (this.c == null) {
                Logger.d(Logger.PREPARE_TAG, j + " size " + this.c + " for ad_id: " + i);
                return true;
            }
            File a = a();
            int length = a == null ? 0 : (int) a.length();
            if (length == this.c.intValue()) {
                Logger.d(Logger.PREPARE_TAG, j + " disk size matched size " + this.c + " for ad_id: " + i);
                return true;
            }
            Logger.d(Logger.PREPARE_TAG, j + " disk size " + length + " failed to match size " + this.c + " for ad_id: " + i);
            if (!b()) {
                return false;
            }
            Logger.d(Logger.PREPARE_TAG, "ignoring " + j + " size mismatch - file exists");
            return true;
        }
        throw new qs();
    }

    final boolean h() {
        File a = a();
        Logger.d(Logger.PREPARE_TAG, "deleting " + a);
        return a != null && a.delete();
    }

    final void a(ContentValues contentValues) {
        contentValues.put(String.URL, this.b);
        contentValues.put("size", this.c);
    }

    final void a(Cursor cursor) {
        this.b = cb.f(cursor, String.URL);
        this.c = cb.d(cursor, "size");
    }

    final void a(StringBuilder stringBuilder) {
        dl.a(stringBuilder, String.URL, this.b, false);
        dl.a(stringBuilder, "size", this.c, false);
    }
}
