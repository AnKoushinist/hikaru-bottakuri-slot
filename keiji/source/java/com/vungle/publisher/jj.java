package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.vungle.publisher.el.b;

/* compiled from: vungle */
public abstract class jj<A extends cj> extends jp<A> {
    public Float e;
    public Integer f;
    public Integer g;
    public Boolean h;
    public Boolean i;
    public Integer j;
    public Integer k;
    public Integer l;
    Integer m;
    public Integer n;

    /* compiled from: vungle */
    public static abstract class a<A extends jk<A, V, R>, V extends jj<A>, R extends aed> extends com.vungle.publisher.jp.a<A, V, R> {
        protected abstract b a();

        protected a() {
        }

        protected V a(A a, R r) {
            jj jjVar = (jj) super.a((cj) a, (ade) r);
            if (jjVar != null) {
                a(jjVar, (aed) r);
            }
            return jjVar;
        }

        static V a(V v, aed com_vungle_publisher_aed) {
            v.g = com_vungle_publisher_aed.i();
            v.k = com_vungle_publisher_aed.f();
            v.l = com_vungle_publisher_aed.g();
            v.m = com_vungle_publisher_aed.h();
            v.n = com_vungle_publisher_aed.k();
            com.vungle.publisher.aed.a d = com_vungle_publisher_aed.d();
            if (d != null) {
                v.e = d.c();
                v.f = d.h();
                v.h = d.e();
                v.i = d.f();
                v.j = d.g();
            }
            return v;
        }

        protected final V a(String str, boolean z) {
            return (jj) a(str, a(), z);
        }

        protected V a(V v, Cursor cursor, boolean z) {
            super.a((jp) v, cursor, z);
            v.e = cb.b(cursor, "cta_clickable_percent");
            v.f = cb.d(cursor, "enable_cta_delay_seconds");
            v.g = cb.d(cursor, "height");
            v.h = cb.a(cursor, "is_cta_enabled");
            v.i = cb.a(cursor, "is_cta_shown_on_touch");
            v.j = cb.d(cursor, "show_cta_delay_seconds");
            v.k = cb.d(cursor, "show_close_delay_incentivized_seconds");
            v.l = cb.d(cursor, "show_close_delay_interstitial_seconds");
            v.m = cb.d(cursor, "show_countdown_delay_seconds");
            v.n = cb.d(cursor, "width");
            return v;
        }
    }

    public abstract Uri q();

    protected jj() {
    }

    protected ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("cta_clickable_percent", this.e);
        a.put("enable_cta_delay_seconds", this.f);
        a.put("height", this.g);
        a.put("is_cta_enabled", this.h);
        a.put("is_cta_shown_on_touch", this.i);
        a.put("show_cta_delay_seconds", this.j);
        a.put("show_close_delay_incentivized_seconds", this.k);
        a.put("show_close_delay_interstitial_seconds", this.l);
        a.put("show_countdown_delay_seconds", this.m);
        a.put("width", this.n);
        return a;
    }

    protected StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "cta_clickable_percent", this.e, false);
        dl.a(m, "enable_cta_delay_seconds", this.f, false);
        dl.a(m, "height", this.g, false);
        dl.a(m, "is_cta_enabled", this.h, false);
        dl.a(m, "is_cta_shown_on_touch", this.i, false);
        dl.a(m, "show_cta_delay_seconds", this.j, false);
        dl.a(m, "show_close_delay_incentivized_seconds", this.k, false);
        dl.a(m, "show_close_delay_interstitial_seconds", this.l, false);
        dl.a(m, "show_countdown_delay_seconds", this.m, false);
        dl.a(m, "width", this.n, false);
        return m;
    }
}
