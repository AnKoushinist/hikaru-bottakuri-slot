package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class jk<A extends jk<A, V, R>, V extends jj<A>, R extends aed> extends cj {
    protected jh<?, A, R, jl, ?, ?> A;
    protected boolean B;
    @Inject
    ql C;
    protected String x;
    protected String y;
    protected V z;

    /* compiled from: vungle */
    public static abstract class a<A extends jk<A, V, R>, V extends jj<A>, R extends aed> extends com.vungle.publisher.cj.a<A, R> {
        protected abstract a<?, A, R, jl, ?, ?> e();

        protected abstract com.vungle.publisher.jj.a<A, V, R> f();

        protected a() {
        }

        protected final /* synthetic */ cj a(cj cjVar, ade com_vungle_publisher_ade) {
            return b((jk) cjVar, (aed) com_vungle_publisher_ade);
        }

        public final /* synthetic */ int b(cj cjVar, ade com_vungle_publisher_ade) {
            return a((jk) cjVar, (aed) com_vungle_publisher_ade);
        }

        public A a(R r) {
            jk jkVar = (jk) super.a((ade) r);
            jkVar.A = e().a(jkVar, r);
            jkVar.z = f().a(jkVar, (aed) r);
            return jkVar;
        }

        public final int a(A a, R r) {
            b((jk) a, (aed) r);
            com.vungle.publisher.jj.a.a(a.u(), (aed) r).b_();
            a.A.a((ade) r);
            return super.b(a, r);
        }

        private A b(A a, R r) {
            super.a((cj) a, (ade) r);
            Object c = r.c();
            String e = r.e();
            if (TextUtils.isEmpty(c)) {
                a.x = e;
            } else {
                a.x = c;
                a.y = e;
            }
            return a;
        }

        protected A a(A a, Cursor cursor, boolean z) {
            super.a(a, cursor, z);
            a.x = cb.f(cursor, "call_to_action_final_url");
            a.y = cb.f(cursor, "call_to_action_url");
            a.A = e().a(a);
            if (z) {
                a((jk) a, z);
            }
            return a;
        }

        protected final V a(A a, boolean z) {
            if (a.B) {
                return a.z;
            }
            V a2 = f().a((String) a.t, z);
            a.z = a2;
            a.B = true;
            return a2;
        }
    }

    protected abstract a<A, V, R> r();

    protected /* synthetic */ com.vungle.publisher.cj.a a() {
        return r();
    }

    public /* synthetic */ Object v() {
        return q();
    }

    public final String s() {
        return this.x;
    }

    public final String t() {
        return this.y;
    }

    public final List<String> a(ji jiVar) {
        return this.A.a(jiVar);
    }

    public final V u() {
        return r().a(this, false);
    }

    public String q() {
        String str = (String) super.v();
        if (this.A != null) {
            this.A.b();
        }
        if (this.z != null) {
            this.z.v();
        }
        return str;
    }

    public int b_() {
        int b_ = super.b_();
        if (b_ == 1 && this.z != null) {
            this.z.b_();
        }
        return b_;
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("call_to_action_final_url", this.x);
        a.put("call_to_action_url", this.y);
        return a;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "call_to_action_final_url", this.x, false);
        dl.a(m, "call_to_action_url", this.y, false);
        this.A.a(m);
        return m;
    }
}
