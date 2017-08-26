package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class c implements Creator<zzf> {
    static void a(zzf com_google_firebase_database_connection_idl_zzf, Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, com_google_firebase_database_connection_idl_zzf.a);
        b.a(parcel, 2, com_google_firebase_database_connection_idl_zzf.b, false);
        b.a(parcel, 3, com_google_firebase_database_connection_idl_zzf.c, false);
        b.a(parcel, 4, com_google_firebase_database_connection_idl_zzf.d);
        b.a(parcel, a);
    }

    public zzf a(Parcel parcel) {
        String str = null;
        boolean z = false;
        int b = a.b(parcel);
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case TwitterResponse.READ /*1*/:
                    i = a.e(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    str2 = a.m(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    str = a.m(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzf(i, str2, str, z);
        }
        throw new a.a("Overread allowed size end=" + b, parcel);
    }

    public zzf[] a(int i) {
        return new zzf[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
