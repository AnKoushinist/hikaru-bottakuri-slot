package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.cj.c;
import com.vungle.publisher.el.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class js extends dm implements dn<js> {
    ds A;
    String B;
    @Inject
    a C;
    @Inject
    com.vungle.publisher.lg.a D;
    boolean q = false;
    boolean r = false;
    boolean v = false;
    List<dg> w;
    jt x;
    List<jc> y;
    kx z;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cj.a<js, aef> implements dw<js, aef> {
        @Inject
        Provider<js> c;
        @Inject
        agt e;
        @Inject
        a f;
        @Inject
        Provider<String> g;
        @Inject
        com.vungle.publisher.jy.a h;
        @Inject
        com.vungle.publisher.jt.a i;
        @Inject
        com.vungle.publisher.kd.a j;
        @Inject
        com.vungle.publisher.kx.a k;
        @Inject
        com.vungle.publisher.jc.a l;
        @Inject
        com.vungle.publisher.ds.a m;

        public final /* synthetic */ cj a(ade com_vungle_publisher_ade) {
            aef com_vungle_publisher_aef = (aef) com_vungle_publisher_ade;
            js jsVar = (js) super.a((ade) com_vungle_publisher_aef);
            jsVar.l = com_vungle_publisher_aef.c;
            jsVar.a((String) this.g.get());
            jsVar.w = this.f.a(jsVar, com_vungle_publisher_aef);
            jsVar.x = this.i.a(jsVar, com_vungle_publisher_aef, b.template);
            jsVar.z = (kx) this.k.a(jsVar, com_vungle_publisher_aef);
            jsVar.A = this.m.a(jsVar);
            jsVar.B = com_vungle_publisher_aef.n;
            JSONObject jSONObject = com_vungle_publisher_aef.k;
            if (jSONObject != null) {
                jsVar.y = this.l.a((String) jsVar.t, jSONObject);
            }
            jsVar.a(c.aware);
            return jsVar;
        }

        public final /* synthetic */ int b(cj cjVar, ade com_vungle_publisher_ade) {
            js jsVar = (js) cjVar;
            aef com_vungle_publisher_aef = (aef) com_vungle_publisher_ade;
            jsVar.l = com_vungle_publisher_aef.c;
            jsVar.z.a((ade) com_vungle_publisher_aef);
            return super.b(jsVar, com_vungle_publisher_aef);
        }

        protected final /* synthetic */ dl c_() {
            return (js) this.c.get();
        }

        public final /* synthetic */ dx i_() {
            return e();
        }

        public final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a j_() {
            return this;
        }

        @Inject
        a() {
        }

        public final int a(List<js> list) {
            return e().a(list);
        }

        private js a(js jsVar, Cursor cursor, boolean z) {
            super.a(jsVar, cursor, z);
            jsVar.k = cb.c(cursor, "delete_local_content_attempts");
            jsVar.l = cb.e(cursor, "expiration_timestamp_seconds");
            jsVar.a(cb.f(cursor, "parent_path"));
            jsVar.n = cb.c(cursor, "prepare_retry_count");
            jsVar.o = System.currentTimeMillis();
            jsVar.z = (kx) this.k.a(jsVar);
            jsVar.A = this.m.a(jsVar);
            jsVar.B = cb.f(cursor, "template_id");
            if (z) {
                a(jsVar);
                a(jsVar, z);
            }
            return jsVar;
        }

        final jt a(js jsVar, boolean z) {
            if (jsVar.r) {
                return jsVar.x;
            }
            jt jtVar = (jt) this.i.a((String) jsVar.t, b.template, z);
            jsVar.x = jtVar;
            jsVar.r = true;
            return jtVar;
        }

        final List<dg> a(js jsVar) {
            if (jsVar.q) {
                return jsVar.w;
            }
            com.vungle.publisher.jp.a aVar = this.f;
            b bVar = b.asset;
            List<dg> a = aVar.a("ad_id = ? AND type = ?", new String[]{(String) jsVar.t, bVar.toString()});
            jsVar.w = a;
            jsVar.q = true;
            return a;
        }

        protected final j a() {
            return j.vungle_mraid;
        }

        private jy e() {
            return (jy) this.h.a(this);
        }
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
        return this.C;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.C;
    }

    public final /* synthetic */ String d() {
        return (String) super.w();
    }

    public final /* bridge */ /* synthetic */ cj h_() {
        return this;
    }

    public final /* synthetic */ lk p() {
        return q();
    }

    public final /* synthetic */ Object v() {
        String str = (String) super.v();
        if (this.z != null) {
            this.z.b();
        }
        if (this.w != null) {
            for (dg q : this.w) {
                q.q();
            }
        }
        if (this.x != null) {
            this.x.v();
        }
        if (this.y != null) {
            for (jc v : this.y) {
                v.v();
            }
        }
        return str;
    }

    @Inject
    js() {
    }

    private lg q() {
        Throwable e;
        this.C.a(this, false);
        String a = tk.a();
        FileOutputStream fileOutputStream;
        try {
            File file = new File(qx.a(this.x.u(), "mraid.js"));
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, true);
            try {
                fileOutputStream.write(("\n" + a + "\n").getBytes(Charset.forName(com.d.a.a.c.DEFAULT_CHARSET)));
                try {
                    fileOutputStream.close();
                } catch (Throwable e2) {
                    Logger.d(Logger.DATABASE_TAG, "error closing output file", e2);
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Logger.e(Logger.DATABASE_TAG, "Failed writing to the mraid js file", e2);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            Logger.d(Logger.DATABASE_TAG, "error closing output file", e22);
                        }
                    }
                    return this.D.a(this.x.B().toURI().toString());
                } catch (Throwable th) {
                    e22 = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e4) {
                            Logger.d(Logger.DATABASE_TAG, "error closing output file", e4);
                        }
                    }
                    throw e22;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileOutputStream = null;
            Logger.e(Logger.DATABASE_TAG, "Failed writing to the mraid js file", e22);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return this.D.a(this.x.B().toURI().toString());
        } catch (Throwable th2) {
            e22 = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e22;
        }
        return this.D.a(this.x.B().toURI().toString());
    }

    public final List<String> a(ji jiVar) {
        return this.z.a(jiVar);
    }

    public final boolean b() {
        return qx.a(h());
    }

    public final void a(c cVar) {
        c cVar2 = this.e;
        super.a(cVar);
        this.A.a(cVar2, cVar);
    }

    public final int b_() {
        int b_ = super.b_();
        if (b_ == 1) {
            if (this.w != null) {
                for (dg b_2 : this.w) {
                    b_2.b_();
                }
            }
            if (this.x != null) {
                this.x.b_();
            }
            if (this.y != null) {
                for (jc b_3 : this.y) {
                    b_3.b_();
                }
            }
        }
        return b_;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        if (z) {
            a.put("template_id", this.B);
        }
        return a;
    }

    public final boolean g_() {
        boolean z = true;
        this.C.a(this, true);
        if (this.x == null) {
            z = false;
        }
        String z2 = z();
        if (z) {
            Logger.v(Logger.PREPARE_TAG, z2 + " has " + b.template + ": " + this.x.g.b);
        } else {
            Logger.w(Logger.PREPARE_TAG, "vungle mraid ad is invalid. template = " + this.x);
            a(c.invalid);
        }
        return z;
    }

    public final List<gg<js>> f_() {
        List<gg<js>> arrayList = new ArrayList();
        a aVar = this.C;
        aVar.a(this);
        if (this.w != null) {
            arrayList.addAll(this.w);
        } else {
            Logger.d(Logger.DATABASE_TAG, "vungle mraid ad assets are null");
        }
        aVar.a(this, true);
        if (this.x != null) {
            arrayList.add(this.x);
        } else {
            Logger.w(Logger.DATABASE_TAG, "vungle mraid ad template is null");
        }
        return arrayList;
    }
}
