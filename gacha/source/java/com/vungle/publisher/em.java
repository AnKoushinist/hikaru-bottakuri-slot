package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.gh.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class em<A extends cj, V extends em<A, V>> extends jp<A> implements b<A> {
    db[] a;
    boolean b;
    boolean c;
    boolean d;
    @Inject
    com.vungle.publisher.gm.a e;
    @Inject
    com.vungle.publisher.db.a f;
    @Inject
    gh g;

    /* compiled from: vungle */
    static abstract class a<A extends cj, V extends em<A, V>, R extends ade> extends com.vungle.publisher.jp.a<A, V, R> {
        @Inject
        a a;

        protected final /* bridge */ /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            return a((em) dlVar, cursor, false);
        }

        protected a() {
        }

        V a(A a, R r, el.b bVar) {
            em emVar = (em) super.a((cj) a, (ade) r);
            if (emVar != null) {
                emVar.r = bVar;
            }
            return emVar;
        }

        private V a(V v, Cursor cursor, boolean z) {
            super.a((jp) v, cursor, z);
            v.g.a(cursor);
            if (z) {
                v.q();
            }
            return v;
        }
    }

    public final String e() {
        return t() + ".zip";
    }

    public final String f() {
        return this.g.b;
    }

    public final void a(String str) {
        this.g.b = str;
    }

    public final void a(Integer num) {
        this.g.c = num;
    }

    public final db[] q() {
        if (!this.b) {
            a(this.f.a(this), false);
        }
        return this.a;
    }

    private void a(db[] dbVarArr, boolean z) {
        this.a = dbVarArr;
        this.c = z;
        this.b = true;
    }

    final String u() {
        return qx.a(d(), this.r);
    }

    public final File B() {
        return new File(C());
    }

    public String C() {
        return qx.a(u(), "index.html");
    }

    public final String g() {
        return this.g.c();
    }

    public final boolean h() {
        return this.g.e();
    }

    public final boolean o() {
        if (this.g.g() && E()) {
            return p();
        }
        return false;
    }

    private boolean E() {
        File a = this.g.a();
        try {
            final List arrayList = new ArrayList();
            ra.a(a, new File(u()), new com.vungle.publisher.ra.a(this) {
                final /* synthetic */ em b;

                public final void a(File file, String str, long j) {
                    Logger.v(Logger.PREPARE_TAG, "extracted " + file + ": " + j + " bytes");
                    List list = arrayList;
                    com.vungle.publisher.db.a aVar = this.b.f;
                    em emVar = this.b;
                    int i = (int) j;
                    db a = aVar.a();
                    a.a = emVar;
                    a.b = str;
                    a.c = Integer.valueOf(i);
                    list.add(a);
                }
            });
            a((db[]) arrayList.toArray(new db[arrayList.size()]), true);
            return true;
        } catch (Throwable e) {
            this.e.b(Logger.PREPARE_TAG, "error extracting " + a, e);
            return false;
        }
    }

    public final boolean i() {
        return this.g.f();
    }

    public final boolean p() {
        db[] q = q();
        int length = q.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            db dbVar = q[i];
            String a = qx.a(dbVar.a.u(), dbVar.b);
            File file = a == null ? null : new File(a);
            if (dbVar.c == null) {
                Logger.w(Logger.PREPARE_TAG, file + " size is null");
                z = false;
            } else {
                int length2 = (int) file.length();
                int intValue = dbVar.c.intValue();
                boolean z2 = length2 == intValue;
                if (z2) {
                    Logger.v(Logger.PREPARE_TAG, file + " size verified " + length2);
                    z = z2;
                } else {
                    Logger.d(Logger.PREPARE_TAG, file + " size " + length2 + " doesn't match expected " + intValue);
                    z = file.exists();
                }
            }
            if (!z) {
                return false;
            }
            i++;
            z = true;
        }
        return z;
    }

    public final int n() {
        F();
        return this.g.d();
    }

    public final boolean j() {
        return this.g.h() & F();
    }

    private boolean F() {
        String u = u();
        Logger.d(Logger.DATABASE_TAG, "deleting " + this.r + " directory " + u);
        boolean a = qx.a(u());
        if (a) {
            Logger.v(Logger.DATABASE_TAG, "deleting " + this.r + " directory " + u);
            this.a = null;
            this.d = true;
        } else {
            Logger.w(Logger.DATABASE_TAG, "failed to delete " + this.r + " directory " + u);
        }
        return a;
    }

    public final int k() {
        return super.n();
    }

    public final int b_() {
        int b_ = super.b_();
        if (b_ == 1) {
            if (this.d) {
                this.f.a((Integer) this.t);
                F();
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesDeleted = false");
                this.d = false;
            } else if (this.c) {
                com.vungle.publisher.dl.a.a(this.a);
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesNew = false");
                this.c = false;
            }
        }
        return b_;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.g.a(a);
        return a;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        this.g.a(m);
        return m;
    }
}
