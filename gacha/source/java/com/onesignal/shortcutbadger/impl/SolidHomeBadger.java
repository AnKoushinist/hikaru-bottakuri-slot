package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import java.util.Arrays;
import java.util.List;

public class SolidHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int i) {
        Intent intent = new Intent("com.majeur.launcher.intent.action.UPDATE_BADGE");
        intent.putExtra("com.majeur.launcher.intent.extra.BADGE_PACKAGE", componentName.getPackageName());
        intent.putExtra("com.majeur.launcher.intent.extra.BADGE_COUNT", i);
        intent.putExtra("com.majeur.launcher.intent.extra.BADGE_CLASS", componentName.getClassName());
        context.sendBroadcast(intent);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.majeur.launcher"});
    }
}
