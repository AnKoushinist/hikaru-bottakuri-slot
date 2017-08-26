package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.b;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.common.util.r;

public final class c {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    private c(String str, String str2, String str3, String str4, String str5, String str6) {
        com.google.android.gms.common.internal.c.a(!r.a(str), "ApplicationId must be set.");
        this.b = str;
        this.a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
    }

    public static c a(Context context) {
        g gVar = new g(context);
        Object a = gVar.a("google_app_id");
        return TextUtils.isEmpty(a) ? null : new c(a, gVar.a("google_api_key"), gVar.a("firebase_database_url"), gVar.a("ga_trackingId"), gVar.a("gcm_defaultSenderId"), gVar.a("google_storage_bucket"));
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return b.a(this.b, cVar.b) && b.a(this.a, cVar.a) && b.a(this.c, cVar.c) && b.a(this.d, cVar.d) && b.a(this.e, cVar.e) && b.a(this.f, cVar.f);
    }

    public int hashCode() {
        return b.a(new Object[]{this.b, this.a, this.c, this.d, this.e, this.f});
    }

    public String toString() {
        return b.a(this).a("applicationId", this.b).a("apiKey", this.a).a("databaseUrl", this.c).a("gcmSenderId", this.e).a("storageBucket", this.f).toString();
    }
}
