package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.pm;
import java.util.List;

class zzn extends zza {
    public static final Creator<zzn> CREATOR = new j();
    final int a;
    final List<String> b;
    final List<String> c;

    public zzn(int i, List<String> list, List<String> list2) {
        this.a = i;
        this.b = list;
        this.c = list2;
    }

    public static pm a(zzn com_google_firebase_database_connection_idl_zzn, Object obj) {
        return new pm(com_google_firebase_database_connection_idl_zzn.b, com_google_firebase_database_connection_idl_zzn.c, obj);
    }

    public static zzn a(pm pmVar) {
        return new zzn(1, pmVar.a(), pmVar.b());
    }

    public void writeToParcel(Parcel parcel, int i) {
        j.a(this, parcel, i);
    }
}
