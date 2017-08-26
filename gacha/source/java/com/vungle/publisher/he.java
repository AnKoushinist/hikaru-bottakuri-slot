package com.vungle.publisher;

import android.database.Cursor;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class he extends cr<gy> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cr.a<gy, he> {
        @Inject
        Provider<he> a;

        public final /* bridge */ /* synthetic */ List a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            return (he) this.a.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        protected final ji a(Cursor cursor) {
            return new hn(cb.f(cursor, TapjoyConstants.TJC_SDK_TYPE_DEFAULT));
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.e;
    }

    @Inject
    he() {
    }
}
