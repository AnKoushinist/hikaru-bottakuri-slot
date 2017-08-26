package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.publisher.gh.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class dg extends jp<js> implements b<js> {
    gh a;
    String b;
    String c;
    jc d;
    @Inject
    com.vungle.publisher.js.a e;
    @Inject
    a f;

    @Singleton
    /* compiled from: vungle */
    static class a extends com.vungle.publisher.jp.a<js, dg, aef> {
        @Inject
        Provider<dg> a;
        @Inject
        a b;
        @Inject
        com.vungle.publisher.jc.a c;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new dg[i];
        }

        protected final /* synthetic */ dl c_() {
            dg dgVar = (dg) this.a.get();
            dgVar.a = this.b.a(dgVar);
            return dgVar;
        }

        @Inject
        a() {
        }

        public final List<dg> a(js jsVar, aef com_vungle_publisher_aef) {
            Collection<aet> values = com_vungle_publisher_aef.l.a.values();
            List arrayList = new ArrayList();
            for (aet com_vungle_publisher_aet : values) {
                dg dgVar = null;
                if (com_vungle_publisher_aef != null) {
                    if (com_vungle_publisher_aet != null) {
                        dgVar = (dg) super.a((cj) jsVar, (ade) com_vungle_publisher_aef);
                        dgVar.r = el.b.asset;
                        String str = com_vungle_publisher_aet.b;
                        String str2 = com_vungle_publisher_aet.a;
                        String str3 = com_vungle_publisher_aet.c;
                        if (str == null) {
                            throw new IllegalArgumentException("asset object must have a non-null url");
                        } else if (str2 == null) {
                            throw new IllegalArgumentException("asset object must have a non-null extension");
                        } else if (str3 == null) {
                            throw new IllegalArgumentException("asset object must have a non-null name");
                        } else {
                            dgVar.c = str3;
                            dgVar.b = str2;
                            dgVar.a.b = str;
                        }
                    } else {
                        throw new IllegalArgumentException("cannot create asset with null url");
                    }
                }
                dgVar.d = this.c.a((String) jsVar.w(), dgVar.c, dgVar.a.c());
                arrayList.add(dgVar);
            }
            return arrayList;
        }

        private dg a(dg dgVar, Cursor cursor, boolean z) {
            super.a((jp) dgVar, cursor, z);
            dgVar.b = cb.f(cursor, "extension");
            dgVar.c = cb.f(cursor, MediationMetaData.KEY_NAME);
            dgVar.a.a(cursor);
            return dgVar;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.f;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a r() {
        return this.e;
    }

    public final /* synthetic */ Object v() {
        return q();
    }

    @Inject
    dg() {
    }

    public final String e() {
        return this.c + "." + this.b;
    }

    public final String f() {
        return this.a.b;
    }

    public final void a(Integer num) {
        this.a.c = num;
    }

    public final String g() {
        return this.a.c();
    }

    public final boolean h() {
        return this.a.e();
    }

    public final boolean i() {
        return this.a.f();
    }

    public final boolean j() {
        return this.a.b();
    }

    public final int k() {
        return super.n();
    }

    public final boolean o() {
        return true;
    }

    public final boolean p() {
        return true;
    }

    public final Integer q() {
        Integer num = (Integer) super.v();
        if (this.d != null) {
            this.d.v();
        }
        return num;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        this.a.a(a);
        a.put(MediationMetaData.KEY_NAME, this.c);
        a.put("extension", this.b);
        return a;
    }
}
