package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.a.h;

public class BootUpReceiver extends h {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        startWakefulService(context, intent2);
    }
}
