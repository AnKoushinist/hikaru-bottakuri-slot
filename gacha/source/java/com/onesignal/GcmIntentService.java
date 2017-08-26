package com.onesignal;

import android.app.IntentService;
import android.content.Intent;

public class GcmIntentService extends IntentService {
    public GcmIntentService() {
        super("GcmIntentService");
        setIntentRedelivery(true);
    }

    protected void onHandleIntent(Intent intent) {
        NotificationBundleProcessor.ProcessFromGCMIntentService(this, intent.getExtras(), null);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
