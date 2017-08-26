package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class wu extends vs {

    /* compiled from: vungle */
    public static abstract class a<T extends wu> extends com.vungle.publisher.vs.a<T> {
        @Inject
        String c;

        protected abstract String a();

        protected final /* synthetic */ vs c() {
            return d();
        }

        protected a() {
        }

        protected final T d() {
            wu wuVar = (wu) super.c();
            wuVar.a("Content-Encoding", "gzip");
            wuVar.a("Content-Type", "application/json");
            wuVar.b = this.c + a();
            return wuVar;
        }
    }

    protected final b b() {
        return b.POST;
    }
}
