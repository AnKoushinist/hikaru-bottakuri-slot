package com.onesignal.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.onesignal.shortcutbadger.Badger;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class XiaomiHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int i) {
        try {
            Object obj;
            Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
            Field declaredField = newInstance.getClass().getDeclaredField("messageCount");
            declaredField.setAccessible(true);
            if (i == 0) {
                obj = BuildConfig.FLAVOR;
            } else {
                obj = Integer.valueOf(i);
            }
            declaredField.set(newInstance, String.valueOf(obj));
        } catch (Exception e) {
            Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            intent.putExtra("android.intent.extra.update_application_component_name", componentName.getPackageName() + Operation.DIVISION + componentName.getClassName());
            intent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(i == 0 ? BuildConfig.FLAVOR : Integer.valueOf(i)));
            if (BroadcastHelper.canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent);
                return;
            }
            throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
        }
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2"});
    }
}
