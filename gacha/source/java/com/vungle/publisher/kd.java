package com.vungle.publisher;

import android.database.Cursor;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class kd extends ed {
    @Inject
    a d;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<kd, abp, aef> {
        @Inject
        Provider<kd> a;

        final /* synthetic */ Map a(String str, aeo com_vungle_publisher_aeo) {
            abp com_vungle_publisher_abp = (abp) com_vungle_publisher_aeo;
            if (com_vungle_publisher_abp == null) {
                return null;
            }
            Map hashMap = new HashMap();
            for (String str2 : com_vungle_publisher_abp.c()) {
                a(str, hashMap, new hn(str2), com_vungle_publisher_abp.a(str2));
            }
            return hashMap;
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            return (kd) this.a.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        private kd a(kd kdVar, Cursor cursor, boolean z) {
            super.a(kdVar, cursor, z);
            kdVar.c = new hn(cb.f(cursor, TapjoyConstants.TJC_SDK_TYPE_DEFAULT));
            return kdVar;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.d;
    }

    @Inject
    kd() {
    }
}
