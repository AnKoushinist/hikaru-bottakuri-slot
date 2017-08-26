package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class b implements Creator<zzc> {
    static void a(zzc com_google_firebase_database_connection_idl_zzc, Parcel parcel, int i) {
        int a = com.google.android.gms.common.internal.safeparcel.b.a(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, com_google_firebase_database_connection_idl_zzc.a);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, com_google_firebase_database_connection_idl_zzc.b, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, com_google_firebase_database_connection_idl_zzc.c);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, com_google_firebase_database_connection_idl_zzc.d, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, com_google_firebase_database_connection_idl_zzc.e);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, com_google_firebase_database_connection_idl_zzc.f, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, com_google_firebase_database_connection_idl_zzc.g, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, a);
    }

    public zzc a(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        String str2 = null;
        List list = null;
        int i = 0;
        zzf com_google_firebase_database_connection_idl_zzf = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case TwitterResponse.READ /*1*/:
                    i2 = a.e(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    com_google_firebase_database_connection_idl_zzf = (zzf) a.a(parcel, a, zzf.CREATOR);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    i = a.e(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    list = a.v(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    z = a.c(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.m(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.m(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzc(i2, com_google_firebase_database_connection_idl_zzf, i, list, z, str2, str);
        }
        throw new a.a("Overread allowed size end=" + b, parcel);
    }

    public zzc[] a(int i) {
        return new zzc[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
