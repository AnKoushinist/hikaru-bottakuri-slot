package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.b.g;

public class UpgradeReceiver extends g {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        startWakefulService(context, intent2);
    }
}
