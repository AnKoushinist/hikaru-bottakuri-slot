package com.vungle.publisher;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.regex.Pattern;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class vs {
    public static final Pattern a = Pattern.compile("^https://");
    public String b;
    public Bundle c;
    public String d;

    /* compiled from: vungle */
    public static abstract class a<T extends vs> {
        @Inject
        protected pn b;

        public abstract T b();

        public T c() {
            T b = b();
            Bundle bundle = new Bundle();
            Object r = this.b.r();
            if (!TextUtils.isEmpty(r)) {
                bundle.putString("User-Agent", r);
            }
            b.c = bundle;
            return b;
        }
    }

    /* compiled from: vungle */
    public enum b {
        GET,
        HEAD,
        POST
    }

    /* compiled from: vungle */
    public enum c {
        download,
        reportAd,
        requestConfig,
        requestLocalAd,
        requestStreamingAd,
        sessionEnd,
        sessionStart,
        trackEvent,
        trackInstall,
        unfilledAd,
        reportExceptions,
        appFingerprint
    }

    public abstract c a();

    public abstract b b();

    public final void a(String str, String str2) {
        this.c.putString(str, str2);
    }

    public String toString() {
        return "{" + a() + "}";
    }
}
