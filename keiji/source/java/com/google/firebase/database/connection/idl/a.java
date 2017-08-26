package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.List;
import twitter4j.TwitterResponse;

public class a implements Creator<zza> {
    static void a(zza com_google_firebase_database_connection_idl_zza, Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, com_google_firebase_database_connection_idl_zza.a);
        b.b(parcel, 2, com_google_firebase_database_connection_idl_zza.b, false);
        b.b(parcel, 3, com_google_firebase_database_connection_idl_zza.c, false);
        b.a(parcel, a);
    }

    public zza a(Parcel parcel) {
        List list = null;
        int b = com.google.android.gms.common.internal.safeparcel.a.b(parcel);
        int i = 0;
        List list2 = null;
        while (parcel.dataPosition() < b) {
            int a = com.google.android.gms.common.internal.safeparcel.a.a(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.a(a)) {
                case TwitterResponse.READ /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.e(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    list2 = com.google.android.gms.common.internal.safeparcel.a.v(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.v(parcel, a);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zza(i, list2, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + b, parcel);
    }

    public zza[] a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
