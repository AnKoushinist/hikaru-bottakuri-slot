package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.a.h;
import com.onesignal.NotificationExtenderService.OverrideSettings;
import com.onesignal.OSNotificationPayload.ActionButton;
import com.onesignal.OSNotificationPayload.BackgroundImageLayout;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Random;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationBundleProcessor {
    static void ProcessFromGCMIntentService(Context context, Bundle bundle, OverrideSettings overrideSettings) {
        try {
            boolean z = bundle.getBoolean("restoring", false);
            String string = bundle.getString("json_payload");
            if (string == null) {
                OneSignal.Log(LOG_LEVEL.ERROR, "json_payload key is nonexistent from bundle passed to ProcessFromGCMIntentService: " + bundle);
                return;
            }
            JSONObject jSONObject = new JSONObject(string);
            if (z || !OneSignal.notValidOrDuplicated(context, jSONObject)) {
                if (bundle.containsKey("android_notif_id")) {
                    if (overrideSettings == null) {
                        overrideSettings = new OverrideSettings();
                    }
                    overrideSettings.androidNotificationId = Integer.valueOf(bundle.getInt("android_notif_id"));
                }
                Process(context, z, jSONObject, overrideSettings);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static int Process(Context context, boolean z, JSONObject jSONObject, OverrideSettings overrideSettings) {
        int nextInt;
        boolean z2 = true;
        if (!(OneSignal.getInAppAlertNotificationEnabled() && OneSignal.isAppActive())) {
            z2 = false;
        }
        if (overrideSettings == null || overrideSettings.androidNotificationId == null) {
            nextInt = new Random().nextInt();
        } else {
            nextInt = overrideSettings.androidNotificationId.intValue();
        }
        GenerateNotification.fromJsonPayload(context, z, nextInt, jSONObject, z2, overrideSettings);
        if (!z) {
            saveNotification(context, jSONObject, false, nextInt);
            try {
                JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                jSONObject2.put("notificationId", nextInt);
                OneSignal.handleNotificationReceived(newJsonArray(jSONObject2), true, z2);
            } catch (Throwable th) {
            }
        }
        return nextInt;
    }

    static JSONArray bundleAsJsonArray(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(bundleAsJSONObject(bundle));
        return jSONArray;
    }

    static void saveNotification(Context context, Bundle bundle, boolean z, int i) {
        saveNotification(context, bundleAsJSONObject(bundle), z, i);
    }

    static void saveNotification(Context context, JSONObject jSONObject, boolean z, int i) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom"));
            SQLiteDatabase writableDatabase = OneSignalDbHelper.getInstance(context).getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                deleteOldNotifications(writableDatabase);
                ContentValues contentValues = new ContentValues();
                contentValues.put(TapjoyConstants.TJC_NOTIFICATION_ID, jSONObject2.optString("i"));
                if (jSONObject.has("grp")) {
                    contentValues.put("group_id", jSONObject.optString("grp"));
                }
                contentValues.put("opened", Integer.valueOf(z ? 1 : 0));
                if (!z) {
                    contentValues.put("android_notification_id", Integer.valueOf(i));
                }
                if (jSONObject.has(String.TITLE)) {
                    contentValues.put(String.TITLE, jSONObject.optString(String.TITLE));
                }
                contentValues.put(String.MESSAGE, jSONObject.optString("alert"));
                contentValues.put("full_data", jSONObject.toString());
                writableDatabase.insertOrThrow("notification", null, contentValues);
                if (!z) {
                    BadgeCountUpdater.update(writableDatabase, context);
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable e) {
                OneSignal.Log(LOG_LEVEL.ERROR, "Error saving notification record! ", e);
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static void deleteOldNotifications(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("notification", "created_time < " + ((System.currentTimeMillis() / 1000) - 2419200), null);
    }

    static JSONObject bundleAsJSONObject(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (Throwable e) {
                OneSignal.Log(LOG_LEVEL.ERROR, "bundleAsJSONObject error for key: " + str, e);
            }
        }
        return jSONObject;
    }

    static void prepareBundle(Bundle bundle) {
        if (bundle.containsKey("o")) {
            try {
                JSONObject jSONObject;
                JSONObject jSONObject2 = new JSONObject(bundle.getString("custom"));
                if (jSONObject2.has("a")) {
                    jSONObject = jSONObject2.getJSONObject("a");
                } else {
                    jSONObject = new JSONObject();
                }
                JSONArray jSONArray = new JSONArray(bundle.getString("o"));
                bundle.remove("o");
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object string;
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    String string2 = jSONObject3.getString("n");
                    jSONObject3.remove("n");
                    if (jSONObject3.has("i")) {
                        string = jSONObject3.getString("i");
                        jSONObject3.remove("i");
                    } else {
                        String str = string2;
                    }
                    jSONObject3.put("id", string);
                    jSONObject3.put("text", string2);
                    if (jSONObject3.has("p")) {
                        jSONObject3.put("icon", jSONObject3.getString("p"));
                        jSONObject3.remove("p");
                    }
                }
                jSONObject.put("actionButtons", jSONArray);
                jSONObject.put("actionSelected", "__DEFAULT__");
                if (!jSONObject2.has("a")) {
                    jSONObject2.put("a", jSONObject);
                }
                bundle.putString("custom", jSONObject2.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    static OSNotificationPayload OSNotificationPayloadFrom(JSONObject jSONObject) {
        OSNotificationPayload oSNotificationPayload = new OSNotificationPayload();
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom"));
            oSNotificationPayload.notificationID = jSONObject2.optString("i");
            oSNotificationPayload.rawPayload = jSONObject.toString();
            oSNotificationPayload.additionalData = jSONObject2.optJSONObject("a");
            oSNotificationPayload.launchURL = jSONObject2.optString("u", null);
            oSNotificationPayload.body = jSONObject.optString("alert", null);
            oSNotificationPayload.title = jSONObject.optString(String.TITLE, null);
            oSNotificationPayload.smallIcon = jSONObject.optString("sicon", null);
            oSNotificationPayload.bigPicture = jSONObject.optString("bicon", null);
            oSNotificationPayload.largeIcon = jSONObject.optString("licon", null);
            oSNotificationPayload.sound = jSONObject.optString("sound", null);
            oSNotificationPayload.groupKey = jSONObject.optString("grp", null);
            oSNotificationPayload.groupMessage = jSONObject.optString("grp_msg", null);
            oSNotificationPayload.smallIconAccentColor = jSONObject.optString("bgac", null);
            oSNotificationPayload.ledColor = jSONObject.optString("ledc", null);
            String optString = jSONObject.optString("vis", null);
            if (optString != null) {
                oSNotificationPayload.lockScreenVisibility = Integer.parseInt(optString);
            }
            oSNotificationPayload.fromProjectNumber = jSONObject.optString("from", null);
            oSNotificationPayload.priority = jSONObject.optInt("pri", 0);
            optString = jSONObject.optString("collapse_key", null);
            if (!"do_not_collapse".equals(optString)) {
                oSNotificationPayload.collapseId = optString;
            }
            setActionButtons(oSNotificationPayload);
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload values!", th);
        }
        try {
            setBackgroundImageLayout(oSNotificationPayload, jSONObject);
        } catch (Throwable th2) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Error assigning OSNotificationPayload.backgroundImageLayout values!", th2);
        }
        return oSNotificationPayload;
    }

    private static void setActionButtons(OSNotificationPayload oSNotificationPayload) {
        if (oSNotificationPayload.additionalData != null && oSNotificationPayload.additionalData.has("actionButtons")) {
            JSONArray jSONArray = oSNotificationPayload.additionalData.getJSONArray("actionButtons");
            oSNotificationPayload.actionButtons = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                ActionButton actionButton = new ActionButton();
                actionButton.id = jSONObject.optString("id", null);
                actionButton.text = jSONObject.optString("text", null);
                actionButton.icon = jSONObject.optString("icon", null);
                oSNotificationPayload.actionButtons.add(actionButton);
            }
            oSNotificationPayload.additionalData.remove("actionSelected");
            oSNotificationPayload.additionalData.remove("actionButtons");
        }
    }

    private static void setBackgroundImageLayout(OSNotificationPayload oSNotificationPayload, JSONObject jSONObject) {
        String optString = jSONObject.optString("bg_img", null);
        if (optString != null) {
            JSONObject jSONObject2 = new JSONObject(optString);
            oSNotificationPayload.backgroundImageLayout = new BackgroundImageLayout();
            oSNotificationPayload.backgroundImageLayout.image = jSONObject2.optString("img");
            oSNotificationPayload.backgroundImageLayout.titleTextColor = jSONObject2.optString("tc");
            oSNotificationPayload.backgroundImageLayout.bodyTextColor = jSONObject2.optString("bc");
        }
    }

    static boolean processBundle(Context context, final Bundle bundle) {
        if (OneSignal.getNotificationIdFromGCMBundle(bundle) == null) {
            return true;
        }
        prepareBundle(bundle);
        Intent intent = NotificationExtenderService.getIntent(context);
        if (intent != null) {
            intent.putExtra("json_payload", bundleAsJSONObject(bundle).toString());
            h.startWakefulService(context, intent);
            return true;
        }
        boolean z = (bundle.getString("alert") == null || BuildConfig.FLAVOR.equals(bundle.getString("alert"))) ? false : true;
        z = shouldDisplay(z);
        if (!z) {
            if (OneSignal.notValidOrDuplicated(context, bundleAsJSONObject(bundle))) {
                return true;
            }
            saveNotification(context, bundle, true, -1);
            new Thread(new Runnable() {
                public void run() {
                    OneSignal.handleNotificationReceived(NotificationBundleProcessor.bundleAsJsonArray(bundle), false, false);
                }
            }).start();
        }
        if (z) {
            return false;
        }
        return true;
    }

    static boolean shouldDisplay(boolean z) {
        return z && (OneSignal.getNotificationsWhenActiveEnabled() || OneSignal.getInAppAlertNotificationEnabled() || !OneSignal.isAppActive());
    }

    static JSONArray newJsonArray(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }
}
