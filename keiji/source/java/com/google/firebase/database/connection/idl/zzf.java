package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.pi;

class zzf extends zza {
    public static final Creator<zzf> CREATOR = new c();
    final int a;
    final String b;
    final String c;
    final boolean d;

    public zzf(int i, String str, String str2, boolean z) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = z;
    }

    public static pi a(zzf com_google_firebase_database_connection_idl_zzf) {
        return new pi(com_google_firebase_database_connection_idl_zzf.b, com_google_firebase_database_connection_idl_zzf.c, com_google_firebase_database_connection_idl_zzf.d);
    }

    public void writeToParcel(Parcel parcel, int i) {
        c.a(this, parcel, i);
    }
}
