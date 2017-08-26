package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;

public class UserProfileChangeRequest extends zza {
    public static final Creator<UserProfileChangeRequest> CREATOR = new c();
    public final int a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private Uri f;

    UserProfileChangeRequest(int i, String str, String str2, boolean z, boolean z2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = z;
        this.e = z2;
        this.f = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        c.a(this, parcel, i);
    }
}
