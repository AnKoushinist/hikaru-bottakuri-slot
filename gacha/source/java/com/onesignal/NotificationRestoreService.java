package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.b.g;

public class NotificationRestoreService extends IntentService {
    public NotificationRestoreService() {
        super("NotificationRestoreService");
        setIntentRedelivery(true);
    }

    protected void onHandleIntent(Intent intent) {
        NotificationRestorer.restore(this);
        g.completeWakefulIntent(intent);
    }
}
