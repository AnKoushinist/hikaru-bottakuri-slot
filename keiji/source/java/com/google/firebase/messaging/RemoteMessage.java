package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class RemoteMessage extends zza {
    public static final Creator<RemoteMessage> CREATOR = new d();
    final int a;
    Bundle b;

    RemoteMessage(int i, Bundle bundle) {
        this.a = i;
        this.b = bundle;
    }

    RemoteMessage(Bundle bundle) {
        this(1, bundle);
    }

    public void writeToParcel(Parcel parcel, int i) {
        d.a(this, parcel, i);
    }
}
