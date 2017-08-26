package com.onesignal.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.onesignal.shortcutbadger.Badger;
import java.util.Arrays;
import java.util.List;

public class SonyHomeBadger implements Badger {
    private final Uri BADGE_CONTENT_URI = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
    private AsyncQueryHandler mQueryHandler;

    public void executeBadge(Context context, ComponentName componentName, int i) {
        if (sonyBadgeContentProviderExists(context)) {
            executeBadgeByContentProvider(context, componentName, i);
        } else {
            executeBadgeByBroadcast(context, componentName, i);
        }
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.sonyericsson.home"});
    }

    private static void executeBadgeByBroadcast(Context context, ComponentName componentName, int i) {
        Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", componentName.getPackageName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", componentName.getClassName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(i));
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", i > 0);
        context.sendBroadcast(intent);
    }

    private void executeBadgeByContentProvider(Context context, ComponentName componentName, int i) {
        if (i >= 0) {
            if (this.mQueryHandler == null) {
                this.mQueryHandler = new AsyncQueryHandler(context.getApplicationContext().getContentResolver()) {
                };
            }
            insertBadgeAsync(i, componentName.getPackageName(), componentName.getClassName());
        }
    }

    private void insertBadgeAsync(int i, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("badge_count", Integer.valueOf(i));
        contentValues.put("package_name", str);
        contentValues.put("activity_name", str2);
        this.mQueryHandler.startInsert(0, null, this.BADGE_CONTENT_URI, contentValues);
    }

    private static boolean sonyBadgeContentProviderExists(Context context) {
        if (context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null) {
            return true;
        }
        return false;
    }
}
