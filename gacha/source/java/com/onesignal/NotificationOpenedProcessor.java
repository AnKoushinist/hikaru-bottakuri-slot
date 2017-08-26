package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ai;
import com.onesignal.OneSignal.LOG_LEVEL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationOpenedProcessor {
    private static Context context;
    private static Intent intent;

    public static void processFromActivity(Context context, Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            ai.a(context).a(intent.getIntExtra("notificationId", 0));
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
        processIntent(context, intent);
    }

    static void processIntent(Context context, Intent intent) {
        context = context;
        intent = intent;
        String stringExtra = intent.getStringExtra("summary");
        boolean booleanExtra = intent.getBooleanExtra("dismissed", false);
        JSONArray jSONArray = null;
        if (!booleanExtra) {
            try {
                JSONObject jSONObject = new JSONObject(intent.getStringExtra("onesignal_data"));
                jSONObject.put("notificationId", intent.getIntExtra("notificationId", 0));
                intent.putExtra("onesignal_data", jSONObject.toString());
                jSONArray = NotificationBundleProcessor.newJsonArray(new JSONObject(intent.getStringExtra("onesignal_data")));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        SQLiteDatabase writableDatabase = OneSignalDbHelper.getInstance(context).getWritableDatabase();
        writableDatabase.beginTransaction();
        if (!(booleanExtra || stringExtra == null)) {
            try {
                addChildNotifications(jSONArray, stringExtra, writableDatabase);
            } catch (Throwable th2) {
                OneSignal.Log(LOG_LEVEL.ERROR, "Error processing notification open or dismiss record! ", th2);
            } finally {
                writableDatabase.endTransaction();
            }
        }
        markNotificationsConsumed(writableDatabase);
        if (stringExtra == null && intent.getStringExtra("grp") != null) {
            updateSummaryNotification(writableDatabase);
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        if (!booleanExtra) {
            OneSignal.handleNotificationOpen(context, jSONArray, intent.getBooleanExtra("from_alert", false));
        }
    }

    private static void addChildNotifications(JSONArray jSONArray, String str, SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("notification", new String[]{"full_data"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, null, null, null);
        if (query.getCount() > 1) {
            query.moveToFirst();
            do {
                try {
                    jSONArray.put(new JSONObject(query.getString(query.getColumnIndex("full_data"))));
                } catch (Throwable th) {
                    OneSignal.Log(LOG_LEVEL.ERROR, "Could not parse JSON of sub notification in group: " + str);
                }
            } while (query.moveToNext());
        }
        query.close();
    }

    private static void markNotificationsConsumed(SQLiteDatabase sQLiteDatabase) {
        String str;
        String[] strArr = null;
        if (intent.getStringExtra("summary") != null) {
            str = "group_id = ?";
            strArr = new String[]{intent.getStringExtra("summary")};
        } else {
            str = "android_notification_id = " + intent.getIntExtra("notificationId", 0);
        }
        sQLiteDatabase.update("notification", newContentValuesWithConsumed(), str, strArr);
        BadgeCountUpdater.update(sQLiteDatabase, context);
    }

    private static void updateSummaryNotification(SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("notification", new String[]{"android_notification_id"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{intent.getStringExtra("grp")}, null, null, null);
        if (query.getCount() == 0) {
            sQLiteDatabase.update("notification", newContentValuesWithConsumed(), "group_id = ?", new String[]{r8});
        } else {
            try {
                GenerateNotification.createSummaryNotification(context, true, new JSONObject("{\"grp\": \"" + r8 + "\"}"));
            } catch (JSONException e) {
            }
        }
        query.close();
    }

    private static ContentValues newContentValuesWithConsumed() {
        ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra("dismissed", false)) {
            contentValues.put("dismissed", Integer.valueOf(1));
        } else {
            contentValues.put("opened", Integer.valueOf(1));
        }
        return contentValues;
    }
}
