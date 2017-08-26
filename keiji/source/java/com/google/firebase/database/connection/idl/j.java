package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.List;
import twitter4j.TwitterResponse;

public class j implements Creator<zzn> {
    static void a(zzn com_google_firebase_database_connection_idl_zzn, Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, com_google_firebase_database_connection_idl_zzn.a);
        b.b(parcel, 2, com_google_firebase_database_connection_idl_zzn.b, false);
        b.b(parcel, 3, com_google_firebase_database_connection_idl_zzn.c, false);
        b.a(parcel, a);
    }

    public zzn a(Parcel parcel) {
        List list = null;
        int b = a.b(parcel);
        int i = 0;
        List list2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case TwitterResponse.READ /*1*/:
                    i = a.e(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    list2 = a.v(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    list = a.v(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzn(i, list2, list);
        }
        throw new a.a("Overread allowed size end=" + b, parcel);
    }

    public zzn[] a(int i) {
        return new zzn[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
