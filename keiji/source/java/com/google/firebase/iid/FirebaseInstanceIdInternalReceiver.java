package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.a.h;
import android.util.Log;

public final class FirebaseInstanceIdInternalReceiver extends h {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (parcelableExtra instanceof Intent) {
                g.a().a(context, intent.getAction(), (Intent) parcelableExtra);
            } else {
                Log.w("FirebaseInstanceId", "Missing or invalid wrapped intent");
            }
        }
    }
}
