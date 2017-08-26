package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.publisher.el.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ht extends jj<hu> {
    @Inject
    com.vungle.publisher.hu.a a;
    @Inject
    a b;
    public String c;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jj.a<hu, ht, aea> {
        private static final b b = b.streamingVideo;
        @Inject
        Provider<ht> a;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new ht[i];
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* synthetic */ dl c_() {
            return (ht) this.a.get();
        }

        @Inject
        protected a() {
        }

        protected final b a() {
            return b;
        }

        private ht a(hu huVar, aea com_vungle_publisher_aea) {
            ht htVar = (ht) super.a((jk) huVar, (aed) com_vungle_publisher_aea);
            if (htVar != null) {
                htVar.c = com_vungle_publisher_aea.j();
                htVar.r = b;
            }
            return htVar;
        }

        private ht a(ht htVar, Cursor cursor, boolean z) {
            super.a((jj) htVar, cursor, z);
            htVar.c = cb.f(cursor, String.URL);
            return htVar;
        }

        protected final Integer[] d(int i) {
            return new Integer[i];
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.b;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a r() {
        return this.a;
    }

    @Inject
    protected ht() {
    }

    public final Uri q() {
        return Uri.parse(this.c);
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put(String.URL, this.c);
        return a;
    }

    protected final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, String.URL, this.c, false);
        return m;
    }
}
