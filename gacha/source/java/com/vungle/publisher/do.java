package com.vungle.publisher;

import android.content.ContentValues;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class do {
    cq<?, ?, ?, ?> a;

    @Singleton
    /* compiled from: vungle */
    static class a {
        @Inject
        Provider<do> a;

        @Inject
        a() {
        }

        public final do a(cq<?, ?, ?, ?> cqVar) {
            do doVar = (do) this.a.get();
            doVar.a = cqVar;
            return doVar;
        }
    }

    @Inject
    do() {
    }

    public final int a() {
        Long l = this.a.o;
        Long l2 = this.a.d;
        String z = this.a.z();
        if (l == null) {
            Logger.w(Logger.REPORT_TAG, "download end millis null for " + z);
            return -1;
        } else if (l.longValue() < 0) {
            return 0;
        } else {
            if (l2 != null) {
                return (int) (l.longValue() - l2.longValue());
            }
            Logger.w(Logger.REPORT_TAG, "insert timestamp millis null for " + z);
            return -1;
        }
    }

    public final void a(Long l) {
        this.a.o = l;
        Logger.d(Logger.REPORT_TAG, "setting ad download end millis " + l + " (duration " + a() + " ms) for " + this.a.z());
        this.a.y();
    }

    protected final ContentValues a(ContentValues contentValues) {
        contentValues.put("download_end_millis", this.a.o);
        return contentValues;
    }
}
