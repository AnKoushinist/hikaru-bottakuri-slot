package com.vungle.publisher;

import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: vungle */
public abstract class jh<D extends jh<D, A, R, E, F, T>, A extends cj, R extends ade, E extends ed, F extends a<E, T, R>, T extends aeo> {
    protected Map<ji, List<E>> b;
    protected cj c;

    /* compiled from: vungle */
    static abstract class a<D extends jh<D, A, R, E, F, T>, A extends cj, R extends ade, E extends ed, F extends a<E, T, R>, T extends aeo> {
        protected abstract F a();

        protected abstract D b();

        a() {
        }

        public final D a(A a, R r) {
            D b = b();
            b.c = a;
            b.b = a().a((String) a.t, r.d);
            return b;
        }

        public final D a(A a) {
            D b = b();
            b.c = a;
            Map a2 = a().a((String) a.t);
            if (a2 != null) {
                b.b = a2;
                Logger.d(Logger.REPORT_TAG, "got " + a2.size() + " event trackings by adId: " + ((String) a.t));
            } else {
                Logger.d(Logger.REPORT_TAG, "no event trackings for adId: " + ((String) a.t));
            }
            return b;
        }
    }

    protected abstract a<D, A, R, E, F, T> a();

    public final void b() {
        if (this.b != null) {
            for (List<ed> it : this.b.values()) {
                for (ed v : it) {
                    v.v();
                }
            }
        }
    }

    public final List<String> a(ji jiVar) {
        Map map = this.b;
        if (map != null) {
            List<ed> list = (List) map.get(jiVar);
            if (list != null && list.size() > 0) {
                List<String> arrayList = new ArrayList();
                for (ed edVar : list) {
                    arrayList.add(edVar.a);
                }
                return arrayList;
            }
        }
        return null;
    }

    public final void a(StringBuilder stringBuilder) {
        dl.a(stringBuilder, "eventTrackings", this.b == null ? null : Integer.valueOf(this.b.size()), false);
    }

    public final Map<ji, List<E>> a(R r) {
        a a = a().a();
        if (a != null) {
            this.b = a.a((ade) r);
        }
        return this.b;
    }
}
