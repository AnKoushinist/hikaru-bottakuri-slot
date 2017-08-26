package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.pd;
import com.google.android.gms.internal.ph;
import java.util.ArrayList;
import java.util.List;

class zza extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zza> CREATOR = new a();
    final int a;
    final List<String> b;
    final List<String> c;

    public zza(int i, List<String> list, List<String> list2) {
        this.a = i;
        this.b = list;
        this.c = list2;
    }

    public static pd a(zza com_google_firebase_database_connection_idl_zza) {
        List arrayList = new ArrayList(com_google_firebase_database_connection_idl_zza.b.size());
        for (String a : com_google_firebase_database_connection_idl_zza.b) {
            arrayList.add(ph.a(a));
        }
        return new pd(arrayList, com_google_firebase_database_connection_idl_zza.c);
    }

    public void writeToParcel(Parcel parcel, int i) {
        a.a(this, parcel, i);
    }
}
