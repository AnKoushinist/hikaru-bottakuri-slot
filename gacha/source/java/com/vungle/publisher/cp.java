package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: vungle */
public abstract class cp<T extends cq<T, P, E, ?>, P extends cp<T, P, E>, E extends cr<P>> extends dl<Integer> {
    public T a;
    public Integer b;
    public Long c;
    List<E> d;

    /* compiled from: vungle */
    public static abstract class a<T extends cq<T, P, E, ?>, P extends cp<T, P, E>, E extends cr<P>> extends com.vungle.publisher.dl.a<P, Integer> {
        protected final /* synthetic */ dl a(dl dlVar, Cursor cursor) {
            cp cpVar = (cp) dlVar;
            cpVar.t = cb.d(cursor, "id");
            cpVar.b = cb.d(cursor, "watched_millis");
            cpVar.c = cb.e(cursor, "start_millis");
            return cpVar;
        }

        protected a() {
        }

        protected final P a(T t) {
            cp cpVar = (cp) c_();
            cpVar.a = t;
            return cpVar;
        }

        protected final List<P> b(T t) {
            if (t == null) {
                throw new IllegalArgumentException("null ad_report");
            }
            if (((Integer) t.w()) == null) {
                throw new IllegalArgumentException("null report_id");
            }
            List<P> a = a("report_id = ?", new String[]{((Integer) t.w()).toString()}, "start_millis ASC", null);
            for (P p : a) {
                p.a = t;
            }
            return a;
        }

        protected final String c() {
            return "ad_play";
        }
    }

    protected abstract com.vungle.publisher.cr.a<P, E> b();

    protected cp() {
    }

    public final void a(ji jiVar, Object obj) {
        List f = f();
        if (f.size() >= GameControllerDelegate.THUMBSTICK_LEFT_X) {
            Logger.w(Logger.REPORT_TAG, "ignoring report event " + jiVar + " because the event buffer is full!");
            return;
        }
        Logger.d(Logger.REPORT_TAG, "adding report event " + jiVar + (obj == null ? BuildConfig.FLAVOR : ", value " + obj + " for " + z()));
        cr a = b().a(this, jiVar, obj);
        a.v();
        f.add(a);
    }

    public final E[] e() {
        List f = f();
        return (cr[]) f.toArray(b().a(f.size()));
    }

    private List<E> f() {
        List<E> list = this.d;
        if (list != null) {
            return list;
        }
        list = b().a(this);
        this.d = list;
        return list;
    }

    protected final String c() {
        return "ad_play";
    }

    private Integer g() {
        return this.a == null ? null : (Integer) this.a.w();
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("report_id", g());
        } else {
            contentValues.put("start_millis", this.c);
            contentValues.put("watched_millis", this.b);
        }
        return contentValues;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "report_id", g(), false);
        dl.a(m, "start_millis", this.c, false);
        dl.a(m, "watched_millis", this.b, false);
        return m;
    }
}
