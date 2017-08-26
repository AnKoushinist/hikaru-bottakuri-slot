package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.px.a;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class zzc extends zza {
    public static final Creator<zzc> CREATOR = new b();
    final int a;
    final zzf b;
    final int c;
    final List<String> d;
    final boolean e;
    final String f;
    final String g;

    public zzc(int i, zzf com_google_firebase_database_connection_idl_zzf, int i2, List<String> list, boolean z, String str, String str2) {
        this.a = i;
        this.b = com_google_firebase_database_connection_idl_zzf;
        this.c = i2;
        this.d = list;
        this.e = z;
        this.f = str;
        this.g = str2;
    }

    public a a() {
        switch (this.c) {
            case TwitterResponse.NONE /*0*/:
                return a.e;
            case TwitterResponse.READ /*1*/:
                return a.a;
            case TwitterResponse.READ_WRITE /*2*/:
                return a.b;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return a.c;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return a.d;
            default:
                return a.e;
        }
    }

    public List<String> b() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        b.a(this, parcel, i);
    }
}
