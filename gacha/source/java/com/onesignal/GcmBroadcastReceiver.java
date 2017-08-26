package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.b.g;

public class GcmBroadcastReceiver extends g {
    private static boolean isGcmMessage(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return false;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null || "gcm".equals(stringExtra)) {
            return true;
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && !"google.com/iid".equals(extras.getString("from"))) {
            processOrderBroadcast(context, intent, extras);
            setResultCode(-1);
        }
    }

    private static void processOrderBroadcast(Context context, Intent intent, Bundle bundle) {
        if (isGcmMessage(intent) && !NotificationBundleProcessor.processBundle(context, bundle)) {
            Intent intent2 = new Intent();
            intent2.putExtra("json_payload", NotificationBundleProcessor.bundleAsJSONObject(bundle).toString());
            intent2.setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName()));
            startWakefulService(context, intent2);
        }
    }
}
