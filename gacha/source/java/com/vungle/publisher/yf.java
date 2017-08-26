package com.vungle.publisher;

import android.os.Bundle;
import com.vungle.publisher.env.WrapperFramework;
import javax.inject.Inject;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public abstract class yf extends vs {

    /* compiled from: vungle */
    public static abstract class a<T extends yf> extends com.vungle.publisher.vs.a<T> {
        @Inject
        sr a;
        @Inject
        pu c;
        @Inject
        String d;
        @Inject
        protected WrapperFramework e;
        @Inject
        protected String f;
        private String g;

        protected a() {
        }

        protected /* synthetic */ vs c() {
            return a();
        }

        protected T a() {
            yf yfVar = (yf) super.c();
            Bundle bundle = yfVar.c;
            bundle.putString("X-VUNGLE-BUNDLE-ID", this.c.a());
            bundle.putString("X-VUNGLE-LANGUAGE", this.a.a());
            bundle.putString("X-VUNGLE-TIMEZONE", this.a.c());
            String str = "User-Agent";
            String str2 = this.g;
            if (str2 == null) {
                Object obj;
                StringBuilder stringBuilder = new StringBuilder(VunglePub.VERSION);
                WrapperFramework wrapperFramework = this.e;
                String str3 = this.f;
                if (wrapperFramework == null || wrapperFramework.equals(WrapperFramework.none)) {
                    obj = null;
                } else {
                    obj = 1;
                }
                Object obj2 = (str3 == null || BuildConfig.FLAVOR.equals(str3)) ? null : 1;
                if (!(obj == null && obj2 == null)) {
                    stringBuilder.append(';');
                    if (obj != null) {
                        stringBuilder.append(wrapperFramework);
                    }
                    if (obj2 != null) {
                        stringBuilder.append('/');
                        stringBuilder.append(str3);
                    }
                }
                str2 = stringBuilder.toString();
                this.g = str2;
            }
            bundle.putString(str, str2);
            if (yf.a(yfVar)) {
                bundle.putLong("X-VUNG-DATE", System.currentTimeMillis());
            }
            return yfVar;
        }
    }

    static /* synthetic */ boolean a(yf yfVar) {
        return yfVar.b != null && vs.a.matcher(yfVar.b).find();
    }

    protected yf() {
    }
}
