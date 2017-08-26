package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class c implements Creator<UserProfileChangeRequest> {
    static void a(UserProfileChangeRequest userProfileChangeRequest, Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, userProfileChangeRequest.a);
        b.a(parcel, 2, userProfileChangeRequest.a(), false);
        b.a(parcel, 3, userProfileChangeRequest.b(), false);
        b.a(parcel, 4, userProfileChangeRequest.c());
        b.a(parcel, 5, userProfileChangeRequest.d());
        b.a(parcel, a);
    }

    public UserProfileChangeRequest a(Parcel parcel) {
        String str = null;
        boolean z = false;
        int b = a.b(parcel);
        boolean z2 = false;
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
                    z2 = a.c(parcel, a);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    z = a.c(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new UserProfileChangeRequest(i, str2, str, z2, z);
        }
        throw new a.a("Overread allowed size end=" + b, parcel);
    }

    public UserProfileChangeRequest[] a(int i) {
        return new UserProfileChangeRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
