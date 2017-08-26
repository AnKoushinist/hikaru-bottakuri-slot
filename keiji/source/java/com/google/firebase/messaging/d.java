package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import twitter4j.TwitterResponse;

public class d implements Creator<RemoteMessage> {
    static void a(RemoteMessage remoteMessage, Parcel parcel, int i) {
        int a = b.a(parcel);
        b.a(parcel, 1, remoteMessage.a);
        b.a(parcel, 2, remoteMessage.b, false);
        b.a(parcel, a);
    }

    public RemoteMessage a(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case TwitterResponse.READ /*1*/:
                    i = a.e(parcel, a);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    bundle = a.o(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new RemoteMessage(i, bundle);
        }
        throw new a.a("Overread allowed size end=" + b, parcel);
    }

    public RemoteMessage[] a(int i) {
        return new RemoteMessage[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
