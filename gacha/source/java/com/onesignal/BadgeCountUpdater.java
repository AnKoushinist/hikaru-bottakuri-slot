package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.onesignal.shortcutbadger.ShortcutBadger;
import org.cocos2dx.lib.BuildConfig;

class BadgeCountUpdater {
    private static int badgesEnabled = -1;

    private static boolean isBadgesEnabled(Context context) {
        if (badgesEnabled == -1) {
            try {
                badgesEnabled = "DISABLE".equals(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.onesignal.BadgeCount")) ? 0 : 1;
            } catch (Throwable th) {
                badgesEnabled = 1;
                OneSignal.Log(LOG_LEVEL.ERROR, BuildConfig.FLAVOR, th);
            }
            if (badgesEnabled != 1) {
                return false;
            }
            return true;
        } else if (badgesEnabled == 1) {
            return true;
        } else {
            return false;
        }
    }

    static void update(SQLiteDatabase sQLiteDatabase, Context context) {
        if (isBadgesEnabled(context)) {
            Cursor query = sQLiteDatabase.query("notification", null, "dismissed = 0 AND opened = 0 AND is_summary = 0 ", null, null, null, null);
            updateCount(query.getCount(), context);
            query.close();
        }
    }

    static void updateCount(int i, Context context) {
        if (isBadgesEnabled(context)) {
            try {
                ShortcutBadger.applyCountOrThrow(context, i);
            } catch (Throwable th) {
            }
        }
    }
}
