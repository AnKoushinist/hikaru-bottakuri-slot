package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;

class c {
    private static AppMeasurement a(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    public static void a(Context context, Intent intent) {
        a(context, "_nr", intent);
    }

    private static void a(Context context, String str, Intent intent) {
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        stringExtra = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra != null) {
            bundle.putString("_nmn", stringExtra);
        }
        stringExtra = intent.getStringExtra("from");
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            stringExtra = null;
        }
        if (stringExtra != null) {
            bundle.putString("_nt", stringExtra);
        }
        try {
            bundle.putInt("_nmt", Integer.valueOf(intent.getStringExtra("google.c.a.ts")).intValue());
        } catch (Throwable e) {
            Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
        }
        if (intent.hasExtra("google.c.a.udt")) {
            try {
                bundle.putInt("_ndt", Integer.valueOf(intent.getStringExtra("google.c.a.udt")).intValue());
            } catch (Throwable e2) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e2);
            }
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            String valueOf = String.valueOf(bundle);
            Log.d("FirebaseMessaging", new StringBuilder((String.valueOf(str).length() + 22) + String.valueOf(valueOf).length()).append("Sending event=").append(str).append(" params=").append(valueOf).toString());
        }
        AppMeasurement a = a(context);
        if (a != null) {
            a.logEventInternal("fcm", str, bundle);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    public static void b(Context context, Intent intent) {
        e(context, intent);
        a(context, "_no", intent);
    }

    public static void c(Context context, Intent intent) {
        a(context, "_nd", intent);
    }

    public static void d(Context context, Intent intent) {
        a(context, "_nf", intent);
    }

    private static void e(Context context, Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                AppMeasurement a = a(context);
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (a != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    a.a("fcm", "_ln", stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString("source", "Firebase");
                    bundle.putString("medium", "notification");
                    bundle.putString("campaign", stringExtra);
                    a.logEventInternal("fcm", "_cmp", bundle);
                    return;
                }
                Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
            } else if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
    }
}
