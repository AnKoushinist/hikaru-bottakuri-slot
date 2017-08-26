package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.onesignal.shortcutbadger.Badger;
import java.util.Arrays;
import java.util.List;

public class HuaweiHomeBadger implements Badger {
    private static final String LOG_TAG = HuaweiHomeBadger.class.getSimpleName();

    public void executeBadge(Context context, ComponentName componentName, int i) {
        String className = componentName.getClassName();
        if (className == null) {
            Log.d(LOG_TAG, "Main activity is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        bundle.putString("class", className);
        bundle.putInt("badgenumber", i);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.huawei.android.launcher"});
    }
}
