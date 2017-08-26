package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.common.util.q;

public final class b {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;

    private b(String str, String str2, String str3, String str4, String str5, String str6) {
        c.a(!q.a(str), "ApplicationId must be set.");
        this.b = str;
        this.a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
    }

    public static b a(Context context) {
        g gVar = new g(context);
        Object a = gVar.a("google_app_id");
        return TextUtils.isEmpty(a) ? null : new b(a, gVar.a("google_api_key"), gVar.a("firebase_database_url"), gVar.a("ga_trackingId"), gVar.a("gcm_defaultSenderId"), gVar.a("google_storage_bucket"));
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return com.google.android.gms.common.internal.b.a(this.b, bVar.b) && com.google.android.gms.common.internal.b.a(this.a, bVar.a) && com.google.android.gms.common.internal.b.a(this.c, bVar.c) && com.google.android.gms.common.internal.b.a(this.d, bVar.d) && com.google.android.gms.common.internal.b.a(this.e, bVar.e) && com.google.android.gms.common.internal.b.a(this.f, bVar.f);
    }

    public int hashCode() {
        return com.google.android.gms.common.internal.b.a(new Object[]{this.b, this.a, this.c, this.d, this.e, this.f});
    }

    public String toString() {
        return com.google.android.gms.common.internal.b.a(this).a("applicationId", this.b).a("apiKey", this.a).a("databaseUrl", this.c).a("gcmSenderId", this.e).a("storageBucket", this.f).toString();
    }
}
