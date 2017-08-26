package com.onesignal;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.ad.b;
import android.support.v4.app.ad.c;
import android.support.v4.app.ad.d;
import android.support.v4.app.ad.g;
import android.support.v4.app.an;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;
import com.onesignal.NotificationExtenderService.OverrideSettings;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.tapjoy.TJAdUnitConstants.String;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GenerateNotification {
    private static Resources contextResources = null;
    private static Context currentContext = null;
    private static Class<?> notificationOpenedClass;
    private static boolean openerIsBroadcast;
    private static String packageName = null;

    static void setStatics(Context context) {
        currentContext = context;
        packageName = currentContext.getPackageName();
        contextResources = currentContext.getResources();
        PackageManager packageManager = currentContext.getPackageManager();
        Intent intent = new Intent(currentContext, NotificationOpenedReceiver.class);
        intent.setPackage(currentContext.getPackageName());
        if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
            openerIsBroadcast = true;
            notificationOpenedClass = NotificationOpenedReceiver.class;
            return;
        }
        notificationOpenedClass = NotificationOpenedActivity.class;
    }

    static void fromJsonPayload(Context context, boolean z, int i, JSONObject jSONObject, boolean z2, OverrideSettings overrideSettings) {
        setStatics(context);
        if (z || !z2 || ActivityLifecycleHandler.curActivity == null) {
            showNotification(i, z, jSONObject, overrideSettings);
        } else {
            showNotificationAsAlert(jSONObject, ActivityLifecycleHandler.curActivity, i);
        }
    }

    private static void showNotificationAsAlert(final JSONObject jSONObject, final Activity activity, final int i) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Builder builder = new Builder(activity);
                builder.setTitle(GenerateNotification.getTitle(jSONObject));
                builder.setMessage(jSONObject.optString("alert"));
                List arrayList = new ArrayList();
                final List arrayList2 = new ArrayList();
                GenerateNotification.addAlertButtons(activity, jSONObject, arrayList, arrayList2);
                final Intent access$200 = GenerateNotification.getNewBaseIntent(i);
                access$200.putExtra("action_button", true);
                access$200.putExtra("from_alert", true);
                access$200.putExtra("onesignal_data", jSONObject.toString());
                if (jSONObject.has("grp")) {
                    access$200.putExtra("grp", jSONObject.optString("grp"));
                }
                OnClickListener anonymousClass1 = new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int i2 = i + 3;
                        if (arrayList2.size() > 1) {
                            try {
                                JSONObject jSONObject = new JSONObject(jSONObject.toString());
                                jSONObject.put("actionSelected", arrayList2.get(i2));
                                access$200.putExtra("onesignal_data", jSONObject.toString());
                                NotificationOpenedProcessor.processIntent(activity, access$200);
                                return;
                            } catch (Throwable th) {
                                return;
                            }
                        }
                        NotificationOpenedProcessor.processIntent(activity, access$200);
                    }
                };
                builder.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        NotificationOpenedProcessor.processIntent(activity, access$200);
                    }
                });
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == 0) {
                        builder.setNeutralButton((CharSequence) arrayList.get(i), anonymousClass1);
                    } else if (i == 1) {
                        builder.setNegativeButton((CharSequence) arrayList.get(i), anonymousClass1);
                    } else if (i == 2) {
                        builder.setPositiveButton((CharSequence) arrayList.get(i), anonymousClass1);
                    }
                }
                AlertDialog create = builder.create();
                create.setCanceledOnTouchOutside(false);
                create.show();
            }
        });
    }

    private static CharSequence getTitle(JSONObject jSONObject) {
        CharSequence optString = jSONObject.optString(String.TITLE, null);
        return optString != null ? optString : currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
    }

    private static PendingIntent getNewActionPendingIntent(int i, Intent intent) {
        if (openerIsBroadcast) {
            return PendingIntent.getBroadcast(currentContext, i, intent, 134217728);
        }
        return PendingIntent.getActivity(currentContext, i, intent, 134217728);
    }

    private static Intent getNewBaseIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", i);
        return openerIsBroadcast ? putExtra : putExtra.addFlags(603979776);
    }

    private static Intent getNewBaseDeleteIntent(int i) {
        Intent putExtra = new Intent(currentContext, notificationOpenedClass).putExtra("notificationId", i).putExtra("dismissed", true);
        return openerIsBroadcast ? putExtra : putExtra.addFlags(402718720);
    }

    private static d getBaseNotificationCompatBuilder(JSONObject jSONObject) {
        int i;
        int i2 = 1;
        int smallIconId = getSmallIconId(jSONObject);
        if (OneSignal.getVibrate(currentContext)) {
            i = 2;
        } else {
            i = 0;
        }
        CharSequence optString = jSONObject.optString("alert", null);
        d c = new d(currentContext).b(true).a(smallIconId).a(getTitle(jSONObject)).a(new c().a(optString)).b(optString).c(optString);
        try {
            BigInteger accentColor = getAccentColor(jSONObject);
            if (accentColor != null) {
                c.e(accentColor.intValue());
            }
        } catch (Throwable th) {
        }
        if (jSONObject.has("ledc")) {
            try {
                c.a(new BigInteger(jSONObject.optString("ledc"), 16).intValue(), 2000, 5000);
            } catch (Throwable th2) {
                i |= 4;
            }
        } else {
            i |= 4;
        }
        try {
            if (jSONObject.has("vis")) {
                i2 = Integer.parseInt(jSONObject.optString("vis"));
            }
            c.f(i2);
        } catch (Throwable th3) {
        }
        Bitmap largeIcon = getLargeIcon(jSONObject);
        if (largeIcon != null) {
            c.a(largeIcon);
        }
        largeIcon = getBitmap(jSONObject.optString("bicon", null));
        if (largeIcon != null) {
            c.a(new b().a(largeIcon).a(optString));
        }
        if (isSoundEnabled(jSONObject)) {
            Uri customSound = getCustomSound(jSONObject);
            if (customSound != null) {
                c.a(customSound);
            } else {
                i |= 1;
            }
        }
        c.c(i);
        if (jSONObject.optInt("pri", 0) > 9) {
            c.d(2);
        }
        return c;
    }

    private static void removeNotifyOptions(d dVar) {
        dVar.c(0).a(null).a(null).c(null);
    }

    static void showNotification(int i, boolean z, JSONObject jSONObject, OverrideSettings overrideSettings) {
        Random random = new Random();
        String optString = jSONObject.optString("grp", null);
        d baseNotificationCompatBuilder = getBaseNotificationCompatBuilder(jSONObject);
        addNotificationActionButtons(jSONObject, baseNotificationCompatBuilder, i, null);
        try {
            addBackgroundImage(jSONObject, baseNotificationCompatBuilder);
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Could not set background notification image!", th);
        }
        if (!(overrideSettings == null || overrideSettings.extender == null)) {
            baseNotificationCompatBuilder.a(overrideSettings.extender);
        }
        if (optString != null) {
            baseNotificationCompatBuilder.a(getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(i).putExtra("onesignal_data", jSONObject.toString()).putExtra("grp", optString)));
            baseNotificationCompatBuilder.b(getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(i).putExtra("grp", optString)));
            baseNotificationCompatBuilder.a(optString);
            createSummaryNotification(z, jSONObject);
        } else {
            baseNotificationCompatBuilder.a(getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(i).putExtra("onesignal_data", jSONObject.toString())));
            baseNotificationCompatBuilder.b(getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(i)));
        }
        if (z) {
            removeNotifyOptions(baseNotificationCompatBuilder);
        }
        if (optString == null || VERSION.SDK_INT > 17) {
            an.a(currentContext).a(i, baseNotificationCompatBuilder.a());
        }
    }

    private static void createSummaryNotification(boolean z, JSONObject jSONObject) {
        createSummaryNotification(null, z, jSONObject);
    }

    static void createSummaryNotification(Context context, boolean z, JSONObject jSONObject) {
        int i;
        String string;
        Notification a;
        if (z && context != null) {
            setStatics(context);
        }
        String optString = jSONObject.optString("grp", null);
        Random random = new Random();
        PendingIntent newActionPendingIntent = getNewActionPendingIntent(random.nextInt(), getNewBaseDeleteIntent(0).putExtra("summary", optString));
        OneSignalDbHelper instance = OneSignalDbHelper.getInstance(currentContext);
        Cursor query = instance.getReadableDatabase().query("notification", new String[]{"android_notification_id", "full_data", "is_summary", String.TITLE, String.MESSAGE}, "group_id = ? AND dismissed = 0 AND opened = 0", new String[]{optString}, null, null, "_id DESC");
        int nextInt = random.nextInt();
        try {
            Collection collection;
            if (query.moveToFirst()) {
                String str;
                Collection arrayList = new ArrayList();
                i = nextInt;
                String str2 = null;
                while (true) {
                    String str3;
                    if (query.getInt(query.getColumnIndex("is_summary")) == 1) {
                        str3 = str2;
                        nextInt = query.getInt(query.getColumnIndex("android_notification_id"));
                        str = str3;
                    } else {
                        string = query.getString(query.getColumnIndex(String.TITLE));
                        if (string == null) {
                            string = BuildConfig.FLAVOR;
                        } else {
                            string = string + " ";
                        }
                        SpannableString spannableString = new SpannableString(string + query.getString(query.getColumnIndex(String.MESSAGE)));
                        if (string.length() > 0) {
                            spannableString.setSpan(new StyleSpan(1), 0, string.length(), 0);
                        }
                        arrayList.add(spannableString);
                        if (str2 == null) {
                            nextInt = i;
                            str = query.getString(query.getColumnIndex("full_data"));
                        } else {
                            str3 = str2;
                            nextInt = i;
                            str = str3;
                        }
                    }
                    if (!query.moveToNext()) {
                        break;
                    }
                    str3 = str;
                    i = nextInt;
                    str2 = str3;
                }
                if (z && str != null) {
                    JSONObject jSONObject2 = new JSONObject(str);
                    i = nextInt;
                    jSONObject = jSONObject2;
                    collection = arrayList;
                }
                i = nextInt;
                collection = arrayList;
            } else {
                i = nextInt;
                collection = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            if (!(query == null || query.isClosed())) {
                query.close();
            }
        }
        if (!(query == null || query.isClosed())) {
            query.close();
        }
        if (r3 == null || (z && r3.size() <= 1)) {
            SQLiteDatabase writableDatabase = instance.getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("android_notification_id", Integer.valueOf(i));
                contentValues.put("group_id", optString);
                contentValues.put("is_summary", Integer.valueOf(1));
                writableDatabase.insertOrThrow("notification", null, contentValues);
                writableDatabase.setTransactionSuccessful();
            } catch (Throwable e2) {
                OneSignal.Log(LOG_LEVEL.ERROR, "Error adding summary notification record! ", e2);
            } finally {
                writableDatabase.endTransaction();
            }
            d baseNotificationCompatBuilder = getBaseNotificationCompatBuilder(jSONObject);
            if (z) {
                removeNotifyOptions(baseNotificationCompatBuilder);
            }
            PendingIntent newActionPendingIntent2 = getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(i).putExtra("onesignal_data", jSONObject.toString()).putExtra("summary", optString));
            addNotificationActionButtons(jSONObject, baseNotificationCompatBuilder, i, optString);
            baseNotificationCompatBuilder.a(newActionPendingIntent2).b(newActionPendingIntent).a(z).a(optString).d(true);
            a = baseNotificationCompatBuilder.a();
        } else {
            CharSequence charSequence;
            int size = r3.size() + (z ? 0 : 1);
            string = jSONObject.optString("grp_msg", null);
            if (string == null) {
                charSequence = size + " new messages";
            } else {
                Object replace = string.replace("$[notif_count]", BuildConfig.FLAVOR + size);
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("alert", charSequence);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            PendingIntent newActionPendingIntent3 = getNewActionPendingIntent(random.nextInt(), getNewBaseIntent(i).putExtra("summary", optString).putExtra("onesignal_data", jSONObject3.toString()));
            d baseNotificationCompatBuilder2 = getBaseNotificationCompatBuilder(jSONObject);
            if (z) {
                removeNotifyOptions(baseNotificationCompatBuilder2);
            }
            baseNotificationCompatBuilder2.a(newActionPendingIntent3).b(newActionPendingIntent).a(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).b(charSequence).b(size).a(z).a(optString).d(true);
            if (!z) {
                baseNotificationCompatBuilder2.c(charSequence);
            }
            g gVar = new g();
            if (!z) {
                string = jSONObject.optString(String.TITLE, null);
                if (string == null) {
                    string = BuildConfig.FLAVOR;
                } else {
                    string = string + " ";
                }
                CharSequence spannableString2 = new SpannableString(string + jSONObject.optString("alert"));
                if (string.length() > 0) {
                    spannableString2.setSpan(new StyleSpan(1), 0, string.length(), 0);
                }
                gVar.b(spannableString2);
            }
            for (SpannableString b : r3) {
                gVar.b(b);
            }
            gVar.a(charSequence);
            baseNotificationCompatBuilder2.a(gVar);
            a = baseNotificationCompatBuilder2.a();
        }
        an.a(currentContext).a(i, a);
    }

    private static void addBackgroundImage(JSONObject jSONObject, d dVar) {
        if (VERSION.SDK_INT >= 16) {
            Bitmap bitmap;
            JSONObject jSONObject2;
            Bitmap bitmapFromAssetsOrResourceName;
            String optString = jSONObject.optString("bg_img", null);
            if (optString != null) {
                JSONObject jSONObject3 = new JSONObject(optString);
                JSONObject jSONObject4 = jSONObject3;
                bitmap = getBitmap(jSONObject3.optString("img", null));
                jSONObject2 = jSONObject4;
            } else {
                jSONObject2 = null;
                Object obj = null;
            }
            if (bitmap == null) {
                bitmapFromAssetsOrResourceName = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
            } else {
                bitmapFromAssetsOrResourceName = bitmap;
            }
            if (bitmapFromAssetsOrResourceName != null) {
                Object string;
                RemoteViews remoteViews = new RemoteViews(currentContext.getPackageName(), R.layout.onesignal_bgimage_notif_layout);
                remoteViews.setTextViewText(R.id.os_bgimage_notif_title, getTitle(jSONObject));
                remoteViews.setTextViewText(R.id.os_bgimage_notif_body, jSONObject.optString("alert"));
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
                setTextColor(remoteViews, jSONObject2, R.id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
                if (jSONObject2 == null || !jSONObject2.has("img_align")) {
                    int identifier = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", packageName);
                    string = identifier != 0 ? contextResources.getString(identifier) : null;
                } else {
                    string = jSONObject2.getString("img_align");
                }
                if ("right".equals(string)) {
                    remoteViews.setViewPadding(R.id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage_right_aligned, bitmapFromAssetsOrResourceName);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage_right_aligned, 0);
                    remoteViews.setViewVisibility(R.id.os_bgimage_notif_bgimage, 2);
                } else {
                    remoteViews.setImageViewBitmap(R.id.os_bgimage_notif_bgimage, bitmapFromAssetsOrResourceName);
                }
                dVar.a(remoteViews);
                dVar.a(null);
            }
        }
    }

    private static void setTextColor(RemoteViews remoteViews, JSONObject jSONObject, int i, String str, String str2) {
        Integer safeGetColorFromHex = safeGetColorFromHex(jSONObject, str);
        if (safeGetColorFromHex != null) {
            remoteViews.setTextColor(i, safeGetColorFromHex.intValue());
            return;
        }
        int identifier = contextResources.getIdentifier(str2, "color", packageName);
        if (identifier != 0) {
            remoteViews.setTextColor(i, ContextCompat.getColor(currentContext, identifier));
        }
    }

    private static Integer safeGetColorFromHex(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return Integer.valueOf(new BigInteger(jSONObject.optString(str), 16).intValue());
                }
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private static boolean isValidResourceName(String str) {
        return (str == null || str.matches("^[0-9]")) ? false : true;
    }

    private static Bitmap getLargeIcon(JSONObject jSONObject) {
        if (VERSION.SDK_INT < 11) {
            return null;
        }
        Bitmap bitmap = getBitmap(jSONObject.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        try {
            int dimension = (int) contextResources.getDimension(17104902);
            int dimension2 = (int) contextResources.getDimension(17104901);
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            if (width > dimension2 || height > dimension) {
                if (height > width) {
                    dimension2 = (int) ((((float) width) / ((float) height)) * ((float) dimension));
                } else if (width > height) {
                    dimension = (int) ((((float) height) / ((float) width)) * ((float) dimension2));
                }
                return Bitmap.createScaledBitmap(bitmap, dimension2, dimension, true);
            }
        } catch (Throwable th) {
        }
        return bitmap;
    }

    private static Bitmap getBitmapFromAssetsOrResourceName(String str) {
        Bitmap decodeStream;
        try {
            decodeStream = BitmapFactory.decodeStream(currentContext.getAssets().open(str));
        } catch (Throwable th) {
            decodeStream = null;
        }
        if (decodeStream != null) {
            return decodeStream;
        }
        try {
            Bitmap bitmap = decodeStream;
            for (String str2 : Arrays.asList(new String[]{".png", ".webp", ".jpg", ".gif", ".bmp"})) {
                try {
                    decodeStream = BitmapFactory.decodeStream(currentContext.getAssets().open(str + str2));
                } catch (Throwable th2) {
                    decodeStream = bitmap;
                }
                if (decodeStream != null) {
                    return decodeStream;
                }
                bitmap = decodeStream;
            }
            int resourceIcon = getResourceIcon(str);
            if (resourceIcon != 0) {
                return BitmapFactory.decodeResource(contextResources, resourceIcon);
            }
        } catch (Throwable th3) {
        }
        return null;
    }

    private static Bitmap getBitmapFromURL(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
        } catch (Throwable th) {
            return null;
        }
    }

    private static Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return getBitmapFromURL(str);
        }
        return getBitmapFromAssetsOrResourceName(str);
    }

    private static int getResourceIcon(String str) {
        int i = 0;
        if (!isValidResourceName(str)) {
            return i;
        }
        int drawableId = getDrawableId(str);
        if (drawableId != 0) {
            return drawableId;
        }
        try {
            return drawable.class.getField(str).getInt(null);
        } catch (Throwable th) {
            return i;
        }
    }

    private static int getSmallIconId(JSONObject jSONObject) {
        int resourceIcon = getResourceIcon(jSONObject.optString("sicon", null));
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        resourceIcon = getDrawableId("ic_stat_onesignal_default");
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        resourceIcon = getDrawableId("corona_statusbar_icon_default");
        if (resourceIcon != 0) {
            return resourceIcon;
        }
        resourceIcon = getDrawableId("ic_os_notification_fallback_white_24dp");
        return resourceIcon == 0 ? 17301598 : resourceIcon;
    }

    private static int getDrawableId(String str) {
        return contextResources.getIdentifier(str, "drawable", packageName);
    }

    private static boolean isSoundEnabled(JSONObject jSONObject) {
        String optString = jSONObject.optString("sound", null);
        if ("null".equals(optString) || "nil".equals(optString)) {
            return false;
        }
        return OneSignal.getSoundEnabled(currentContext);
    }

    private static Uri getCustomSound(JSONObject jSONObject) {
        int identifier;
        String optString = jSONObject.optString("sound", null);
        if (isValidResourceName(optString)) {
            identifier = contextResources.getIdentifier(optString, "raw", packageName);
            if (identifier != 0) {
                return Uri.parse("android.resource://" + packageName + "/" + identifier);
            }
        }
        identifier = contextResources.getIdentifier("onesignal_default_sound", "raw", packageName);
        if (identifier != 0) {
            return Uri.parse("android.resource://" + packageName + "/" + identifier);
        }
        return null;
    }

    private static BigInteger getAccentColor(JSONObject jSONObject) {
        try {
            if (jSONObject.has("bgac")) {
                return new BigInteger(jSONObject.optString("bgac", null), 16);
            }
        } catch (Throwable th) {
        }
        try {
            String manifestMeta = OSUtils.getManifestMeta(currentContext, "com.onesignal.NotificationAccentColor.DEFAULT");
            if (manifestMeta != null) {
                return new BigInteger(manifestMeta, 16);
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    private static void addNotificationActionButtons(JSONObject jSONObject, d dVar, int i, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom"));
            if (jSONObject2.has("a")) {
                jSONObject2 = jSONObject2.getJSONObject("a");
                if (jSONObject2.has("actionButtons")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("actionButtons");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        int resourceIcon;
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        jSONObject2 = new JSONObject(jSONObject.toString());
                        Intent newBaseIntent = getNewBaseIntent(i);
                        newBaseIntent.setAction(BuildConfig.FLAVOR + i2);
                        newBaseIntent.putExtra("action_button", true);
                        jSONObject2.put("actionSelected", optJSONObject.optString("id"));
                        newBaseIntent.putExtra("onesignal_data", jSONObject2.toString());
                        if (str != null) {
                            newBaseIntent.putExtra("summary", str);
                        } else if (jSONObject.has("grp")) {
                            newBaseIntent.putExtra("grp", jSONObject.optString("grp"));
                        }
                        PendingIntent newActionPendingIntent = getNewActionPendingIntent(i, newBaseIntent);
                        if (optJSONObject.has("icon")) {
                            resourceIcon = getResourceIcon(optJSONObject.optString("icon"));
                        } else {
                            resourceIcon = 0;
                        }
                        dVar.a(resourceIcon, optJSONObject.optString("text"), newActionPendingIntent);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void addAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom"));
            if (jSONObject2.has("a")) {
                jSONObject2 = jSONObject2.getJSONObject("a");
                if (jSONObject2.has("actionButtons")) {
                    JSONArray optJSONArray = jSONObject2.optJSONArray("actionButtons");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                        list.add(jSONObject3.optString("text"));
                        list2.add(jSONObject3.optString("id"));
                    }
                }
            }
            if (list.size() < 3) {
                list.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
                list2.add("__DEFAULT__");
            }
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Failed to parse buttons for alert dialog.", th);
        }
    }
}
