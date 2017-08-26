package com.onesignal.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import com.onesignal.shortcutbadger.impl.AdwHomeBadger;
import com.onesignal.shortcutbadger.impl.ApexHomeBadger;
import com.onesignal.shortcutbadger.impl.AsusHomeLauncher;
import com.onesignal.shortcutbadger.impl.DefaultBadger;
import com.onesignal.shortcutbadger.impl.HuaweiHomeBadger;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import com.onesignal.shortcutbadger.impl.NovaHomeBadger;
import com.onesignal.shortcutbadger.impl.SonyHomeBadger;
import com.onesignal.shortcutbadger.impl.XiaomiHomeBadger;
import java.util.LinkedList;
import java.util.List;

public final class ShortcutBadger {
    private static final List<Class<? extends Badger>> BADGERS = new LinkedList();
    private static ComponentName sComponentName;
    private static Badger sShortcutBadger;

    static {
        BADGERS.add(AdwHomeBadger.class);
        BADGERS.add(ApexHomeBadger.class);
        BADGERS.add(NewHtcHomeBadger.class);
        BADGERS.add(NovaHomeBadger.class);
        BADGERS.add(SonyHomeBadger.class);
        BADGERS.add(XiaomiHomeBadger.class);
        BADGERS.add(AsusHomeLauncher.class);
        BADGERS.add(HuaweiHomeBadger.class);
    }

    public static void applyCountOrThrow(Context context, int i) {
        if (sShortcutBadger != null || initBadger(context)) {
            try {
                sShortcutBadger.executeBadge(context, sComponentName, i);
                return;
            } catch (Exception e) {
                throw new ShortcutBadgeException("Unable to execute badge", e);
            }
        }
        throw new ShortcutBadgeException("No default launcher available");
    }

    private static boolean initBadger(Context context) {
        sComponentName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
            return false;
        }
        String str = resolveActivity.activityInfo.packageName;
        for (Class newInstance : BADGERS) {
            Badger badger;
            try {
                badger = (Badger) newInstance.newInstance();
            } catch (Exception e) {
                badger = null;
            }
            if (badger != null && badger.getSupportLaunchers().contains(str)) {
                sShortcutBadger = badger;
                break;
            }
        }
        if (sShortcutBadger == null) {
            if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                sShortcutBadger = new XiaomiHomeBadger();
            } else {
                sShortcutBadger = new DefaultBadger();
            }
        }
        return true;
    }
}
