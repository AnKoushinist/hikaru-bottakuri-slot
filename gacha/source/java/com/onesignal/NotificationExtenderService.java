package com.onesignal;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.z.f;

public abstract class NotificationExtenderService extends IntentService {

    public static class OverrideSettings {
        public Integer androidNotificationId;
        public f extender;
    }

    static Intent getIntent(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent().setAction("com.onesignal.NotificationExtender").setPackage(context.getPackageName());
        if (packageManager.queryIntentServices(intent, 128).size() < 1) {
            return null;
        }
        return intent;
    }
}
