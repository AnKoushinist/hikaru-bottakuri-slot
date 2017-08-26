package com.onesignal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.onesignal.OSNotification.DisplayType;
import com.onesignal.OSNotificationAction.ActionType;
import com.onesignal.PushRegistrator.RegisteredHandler;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONArray;
import org.json.JSONObject;

public class OneSignal {
    private static int androidParamsReties = 0;
    static Context appContext;
    static String appId;
    private static JSONObject awl;
    private static boolean awlFired;
    private static int deviceType;
    private static boolean foreground;
    private static boolean getTagsCall;
    private static IdsAvailableHandler idsAvailableHandler;
    static boolean initDone;
    private static Float lastLocAcc;
    private static Double lastLocLat;
    private static Double lastLocLong;
    private static Integer lastLocType;
    private static String lastRegistrationId;
    private static long lastTrackedFocusTime = 1;
    private static boolean locationFired;
    private static LOG_LEVEL logCatLevel = LOG_LEVEL.WARN;
    static String mGoogleProjectNumber;
    static Builder mInitBuilder;
    private static AdvertisingIdentifierProvider mainAdIdProvider = new AdvertisingIdProviderGPS();
    private static OSUtils osUtils;
    private static GetTagsHandler pendingGetTagsHandler;
    private static boolean promptedLocation;
    private static boolean registerForPushFired;
    public static String sdkType = TapjoyConstants.TJC_PLUGIN_NATIVE;
    private static boolean sendAsSession;
    private static boolean shareLocation = true;
    static boolean startedSyncService;
    private static int subscribableStatus;
    private static TrackAmazonPurchase trackAmazonPurchase;
    private static TrackGooglePurchase trackGooglePurchase;
    private static long unSentActiveTime = -1;
    static Collection<JSONArray> unprocessedOpenedNotifis = new ArrayList();
    private static String userId = null;
    private static LOG_LEVEL visualLogLevel = LOG_LEVEL.NONE;
    private static boolean waitingToPostStateSync;

    public static class Builder {
        Context mContext;
        boolean mDisableGmsMissingPrompt;
        OSInFocusDisplayOption mDisplayOption;
        NotificationOpenedHandler mNotificationOpenedHandler;
        NotificationReceivedHandler mNotificationReceivedHandler;
        boolean mPromptLocation;

        private Builder() {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
        }

        private Builder(Context context) {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
            this.mContext = context;
        }

        public void init() {
            OneSignal.init(this);
        }
    }

    public interface GetTagsHandler {
        void tagsAvailable(JSONObject jSONObject);
    }

    public interface IdsAvailableHandler {
        void idsAvailable(String str, String str2);
    }

    public enum LOG_LEVEL {
        NONE,
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        VERBOSE
    }

    public interface NotificationOpenedHandler {
        void notificationOpened(OSNotificationOpenResult oSNotificationOpenResult);
    }

    public interface NotificationReceivedHandler {
        void notificationReceived(OSNotification oSNotification);
    }

    public enum OSInFocusDisplayOption {
        None,
        InAppAlert,
        Notification
    }

    static /* synthetic */ int access$1208() {
        int i = androidParamsReties;
        androidParamsReties = i + 1;
        return i;
    }

    public static Builder startInit(Context context) {
        return new Builder(context);
    }

    private static void init(Builder builder) {
        mInitBuilder = builder;
        Context context = mInitBuilder.mContext;
        mInitBuilder.mContext = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            String string = bundle.getString("onesignal_google_project_number");
            if (string != null && string.length() > 4) {
                string = string.substring(4);
            }
            init(context, string, bundle.getString("onesignal_app_id"), mInitBuilder.mNotificationOpenedHandler, mInitBuilder.mNotificationReceivedHandler);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void init(Context context, String str, String str2, NotificationOpenedHandler notificationOpenedHandler, NotificationReceivedHandler notificationReceivedHandler) {
        if (mInitBuilder == null) {
            mInitBuilder = new Builder();
        }
        mInitBuilder.mNotificationOpenedHandler = notificationOpenedHandler;
        mInitBuilder.mNotificationReceivedHandler = notificationReceivedHandler;
        mGoogleProjectNumber = str;
        osUtils = new OSUtils();
        deviceType = osUtils.getDeviceType();
        subscribableStatus = osUtils.initializationChecker(deviceType, str2);
        if (subscribableStatus != -999) {
            if (initDone) {
                if (context != null) {
                    appContext = context.getApplicationContext();
                }
                if (mInitBuilder.mNotificationOpenedHandler != null) {
                    fireCallbackForOpenedNotifications();
                    return;
                }
                return;
            }
            boolean z = context instanceof Activity;
            foreground = z;
            appId = str2;
            appContext = context.getApplicationContext();
            if (z) {
                ActivityLifecycleHandler.curActivity = (Activity) context;
                NotificationRestorer.asyncRestore(appContext);
                startSyncService();
            } else {
                ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
            }
            lastTrackedFocusTime = SystemClock.elapsedRealtime();
            OneSignalStateSynchronizer.initUserState(appContext);
            if (VERSION.SDK_INT > 13) {
                ((Application) appContext).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
            } else {
                ActivityLifecycleListenerCompat.startListener();
            }
            try {
                Class.forName("com.amazon.device.iap.PurchasingListener");
                trackAmazonPurchase = new TrackAmazonPurchase(appContext);
            } catch (ClassNotFoundException e) {
            }
            String savedAppId = getSavedAppId();
            if (savedAppId == null) {
                BadgeCountUpdater.updateCount(0, appContext);
                SaveAppId(appId);
            } else if (!savedAppId.equals(appId)) {
                Log(LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
                SaveAppId(appId);
                OneSignalStateSynchronizer.resetCurrentState();
            }
            if (foreground || getUserId() == null) {
                sendAsSession = isPastOnSessionTime();
                setLastSessionTime(System.currentTimeMillis());
                startRegistrationOrOnSession();
            }
            if (mInitBuilder.mNotificationOpenedHandler != null) {
                fireCallbackForOpenedNotifications();
            }
            if (TrackGooglePurchase.CanTrack(appContext)) {
                trackGooglePurchase = new TrackGooglePurchase(appContext);
            }
            initDone = true;
        }
    }

    private static void startRegistrationOrOnSession() {
        boolean z = false;
        if (!waitingToPostStateSync) {
            waitingToPostStateSync = true;
            registerForPushFired = false;
            if (sendAsSession) {
                locationFired = false;
            }
            startLocationUpdate();
            makeAndroidParamsRequest();
            if (promptedLocation || mInitBuilder.mPromptLocation) {
                z = true;
            }
            promptedLocation = z;
        }
    }

    private static void startLocationUpdate() {
        boolean z = true;
        if (shareLocation) {
            Context context = appContext;
            if (!mInitBuilder.mPromptLocation || promptedLocation) {
                z = false;
            }
            LocationGMS.getLocation(context, z, new LocationHandler() {
                public void complete(Double d, Double d2, Float f, Integer num) {
                    OneSignal.lastLocLat = d;
                    OneSignal.lastLocLong = d2;
                    OneSignal.lastLocAcc = f;
                    OneSignal.lastLocType = num;
                    OneSignal.locationFired = true;
                    OneSignal.registerUser();
                }
            });
            return;
        }
        locationFired = true;
        registerUser();
    }

    private static void registerForPushToken() {
        PushRegistrator pushRegistratorADM;
        if (deviceType == 2) {
            pushRegistratorADM = new PushRegistratorADM();
        } else {
            pushRegistratorADM = new PushRegistratorGPS();
        }
        pushRegistratorADM.registerForPush(appContext, mGoogleProjectNumber, new RegisteredHandler() {
            public void complete(String str, int i) {
                if (i < 1) {
                    if (OneSignalStateSynchronizer.getRegistrationId() == null && (OneSignal.subscribableStatus == 1 || OneSignal.subscribableStatus < -6)) {
                        OneSignal.subscribableStatus = i;
                    }
                } else if (OneSignal.subscribableStatus < -6) {
                    OneSignal.subscribableStatus = i;
                }
                OneSignal.lastRegistrationId = str;
                OneSignal.registerForPushFired = true;
                OneSignal.registerUser();
            }
        });
    }

    private static void makeAndroidParamsRequest() {
        if (awlFired) {
            registerForPushToken();
            return;
        }
        ResponseHandler anonymousClass3 = new ResponseHandler() {
            void onFailure(int i, String str, Throwable th) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep((long) ((OneSignal.androidParamsReties * 10000) + 30000));
                        } catch (Throwable th) {
                        }
                        OneSignal.access$1208();
                        OneSignal.makeAndroidParamsRequest();
                    }
                });
            }

            void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("android_sender_id")) {
                        OneSignal.mGoogleProjectNumber = jSONObject.getString("android_sender_id");
                    }
                    OneSignal.awl = jSONObject.getJSONObject("awl_list");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                OneSignal.awlFired = true;
                OneSignal.registerForPushToken();
            }
        };
        String str = "apps/" + appId + "/android_params.js";
        String userId = getUserId();
        if (userId != null) {
            str = str + "?player_id=" + userId;
        }
        OneSignalRestClient.get(str, anonymousClass3);
    }

    private static void fireCallbackForOpenedNotifications() {
        for (JSONArray runNotificationOpenedCallback : unprocessedOpenedNotifis) {
            runNotificationOpenedCallback(runNotificationOpenedCallback, true, false);
        }
        unprocessedOpenedNotifis.clear();
    }

    private static boolean atLogLevel(LOG_LEVEL log_level) {
        return log_level.compareTo(visualLogLevel) < 1 || log_level.compareTo(logCatLevel) < 1;
    }

    static void Log(LOG_LEVEL log_level, String str) {
        Log(log_level, str, null);
    }

    static void Log(final LOG_LEVEL log_level, String str, Throwable th) {
        if (log_level.compareTo(logCatLevel) < 1) {
            if (log_level == LOG_LEVEL.VERBOSE) {
                Log.v("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.DEBUG) {
                Log.d("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.INFO) {
                Log.i("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.WARN) {
                Log.w("OneSignal", str, th);
            } else if (log_level == LOG_LEVEL.ERROR || log_level == LOG_LEVEL.FATAL) {
                Log.e("OneSignal", str, th);
            }
        }
        if (log_level.compareTo(visualLogLevel) < 1 && ActivityLifecycleHandler.curActivity != null) {
            try {
                String str2 = str + "\n";
                if (th != null) {
                    str2 = str2 + th.getMessage();
                    Writer stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    str2 = str2 + stringWriter.toString();
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (ActivityLifecycleHandler.curActivity != null) {
                            new android.app.AlertDialog.Builder(ActivityLifecycleHandler.curActivity).setTitle(log_level.toString()).setMessage(str2).show();
                        }
                    }
                });
            } catch (Throwable th2) {
                Log.e("OneSignal", "Error showing logging message.", th2);
            }
        }
    }

    private static void logHttpError(String str, int i, Throwable th, String str2) {
        String str3 = BuildConfig.FLAVOR;
        if (str2 != null && atLogLevel(LOG_LEVEL.INFO)) {
            str3 = "\n" + str2 + "\n";
        }
        Log(LOG_LEVEL.WARN, "HTTP code: " + i + " " + str + str3, th);
    }

    static void onAppLostFocus(boolean z) {
        foreground = false;
        if (initDone) {
            if (trackAmazonPurchase != null) {
                trackAmazonPurchase.checkListener();
            }
            if (lastTrackedFocusTime != -1) {
                long elapsedRealtime = (long) ((((double) (SystemClock.elapsedRealtime() - lastTrackedFocusTime)) / 1000.0d) + 0.5d);
                lastTrackedFocusTime = SystemClock.elapsedRealtime();
                if (elapsedRealtime >= 0 && elapsedRealtime <= 86400) {
                    if (appContext == null) {
                        Log(LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
                        return;
                    }
                    setLastSessionTime(System.currentTimeMillis());
                    elapsedRealtime += GetUnsentActiveTime();
                    if (z || elapsedRealtime < 60 || getUserId() == null) {
                        SaveUnsentActiveTime(elapsedRealtime);
                    } else {
                        sendOnFocus(elapsedRealtime, true);
                    }
                }
            }
        }
    }

    static void sendOnFocus(long j, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, appId);
            jSONObject.put(MoatAdEvent.EVENT_TYPE, 1);
            jSONObject.put("state", "ping");
            jSONObject.put("active_time", j);
            addNetType(jSONObject);
            String str = "players/" + getUserId() + "/on_focus";
            ResponseHandler anonymousClass5 = new ResponseHandler() {
                void onFailure(int i, String str, Throwable th) {
                    OneSignal.logHttpError("sending on_focus Failed", i, th, str);
                }

                void onSuccess(String str) {
                    OneSignal.SaveUnsentActiveTime(0);
                }
            };
            if (z) {
                OneSignalRestClient.postSync(str, jSONObject, anonymousClass5);
            } else {
                OneSignalRestClient.post(str, jSONObject, anonymousClass5);
            }
        } catch (Throwable th) {
            Log(LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", th);
        }
    }

    static void onAppFocus() {
        startSyncService();
        foreground = true;
        lastTrackedFocusTime = SystemClock.elapsedRealtime();
        sendAsSession = isPastOnSessionTime();
        setLastSessionTime(System.currentTimeMillis());
        startRegistrationOrOnSession();
        if (trackGooglePurchase != null) {
            trackGooglePurchase.trackIAP();
        }
        NotificationRestorer.asyncRestore(appContext);
    }

    static boolean isForeground() {
        return foreground;
    }

    private static void addNetType(JSONObject jSONObject) {
        try {
            jSONObject.put("net_type", osUtils.getNetType());
        } catch (Throwable th) {
        }
    }

    private static int getTimeZoneOffset() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        int rawOffset = timeZone.getRawOffset();
        if (timeZone.inDaylightTime(new Date())) {
            rawOffset += timeZone.getDSTSavings();
        }
        return rawOffset / GameControllerDelegate.THUMBSTICK_LEFT_X;
    }

    private static void registerUser() {
        Log(LOG_LEVEL.DEBUG, "registerUser: registerForPushFired:" + registerForPushFired + ", locationFired: " + locationFired + ", awlFired: " + awlFired);
        if (registerForPushFired && locationFired && awlFired) {
            new Thread(new Runnable() {
                public void run() {
                    UserState newUserState = OneSignalStateSynchronizer.getNewUserState();
                    String packageName = OneSignal.appContext.getPackageName();
                    PackageManager packageManager = OneSignal.appContext.getPackageManager();
                    newUserState.set(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, OneSignal.appId);
                    newUserState.set("identifier", OneSignal.lastRegistrationId);
                    String identifier = OneSignal.mainAdIdProvider.getIdentifier(OneSignal.appContext);
                    if (identifier != null) {
                        newUserState.set("ad_id", identifier);
                    }
                    newUserState.set("device_os", VERSION.RELEASE);
                    newUserState.set(TapjoyConstants.TJC_DEVICE_TIMEZONE, Integer.valueOf(OneSignal.getTimeZoneOffset()));
                    newUserState.set("language", OSUtils.getCorrectedLanguage());
                    newUserState.set("sdk", "030401");
                    newUserState.set(TapjoyConstants.TJC_SDK_TYPE, OneSignal.sdkType);
                    newUserState.set("android_package", packageName);
                    newUserState.set("device_model", Build.MODEL);
                    newUserState.set(TapjoyConstants.TJC_DEVICE_TYPE_NAME, Integer.valueOf(OneSignal.deviceType));
                    newUserState.setState("subscribableStatus", Integer.valueOf(OneSignal.subscribableStatus));
                    try {
                        newUserState.set("game_version", Integer.valueOf(packageManager.getPackageInfo(packageName, 0).versionCode));
                    } catch (NameNotFoundException e) {
                    }
                    try {
                        List installedPackages = packageManager.getInstalledPackages(0);
                        JSONArray jSONArray = new JSONArray();
                        MessageDigest instance = MessageDigest.getInstance("SHA-256");
                        for (int i = 0; i < installedPackages.size(); i++) {
                            instance.update(((PackageInfo) installedPackages.get(i)).packageName.getBytes());
                            packageName = Base64.encodeToString(instance.digest(), 2);
                            if (OneSignal.awl.has(packageName)) {
                                jSONArray.put(packageName);
                            }
                        }
                        newUserState.set("pkgs", jSONArray);
                    } catch (Throwable th) {
                    }
                    newUserState.set("net_type", OneSignal.osUtils.getNetType());
                    newUserState.set("carrier", OneSignal.osUtils.getCarrierName());
                    newUserState.set("rooted", Boolean.valueOf(RootToolsInternalMethods.isRooted()));
                    newUserState.set(String.LAT, OneSignal.lastLocLat);
                    newUserState.set(String.LONG, OneSignal.lastLocLong);
                    newUserState.set("loc_acc", OneSignal.lastLocAcc);
                    newUserState.set("loc_type", OneSignal.lastLocType);
                    OneSignalStateSynchronizer.postUpdate(newUserState, OneSignal.sendAsSession);
                    OneSignal.waitingToPostStateSync = false;
                }
            }).start();
        }
    }

    private static void internalFireGetTagsCallback(final GetTagsHandler getTagsHandler) {
        if (getTagsHandler != null) {
            new Thread(new Runnable() {
                public void run() {
                    GetTagsResult tags = OneSignalStateSynchronizer.getTags(!OneSignal.getTagsCall);
                    if (tags.serverSuccess) {
                        OneSignal.getTagsCall = true;
                    }
                    if (tags.result == null || tags.toString().equals("{}")) {
                        getTagsHandler.tagsAvailable(null);
                    } else {
                        getTagsHandler.tagsAvailable(tags.result);
                    }
                }
            }).start();
        }
    }

    public static void idsAvailable(IdsAvailableHandler idsAvailableHandler) {
        idsAvailableHandler = idsAvailableHandler;
        if (getUserId() != null) {
            internalFireIdsAvailableCallback();
        }
    }

    static void fireIdsAvailableCallback() {
        if (idsAvailableHandler != null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    OneSignal.internalFireIdsAvailableCallback();
                }
            });
        }
    }

    private static void internalFireIdsAvailableCallback() {
        if (idsAvailableHandler != null) {
            String registrationId = OneSignalStateSynchronizer.getRegistrationId();
            if (!OneSignalStateSynchronizer.getSubscribed()) {
                registrationId = null;
            }
            String userId = getUserId();
            if (userId != null) {
                idsAvailableHandler.idsAvailable(userId, registrationId);
                if (registrationId != null) {
                    idsAvailableHandler = null;
                }
            }
        }
    }

    static void sendPurchases(JSONArray jSONArray, boolean z, ResponseHandler responseHandler) {
        if (getUserId() != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, appId);
                if (z) {
                    jSONObject.put("existing", true);
                }
                jSONObject.put("purchases", jSONArray);
                OneSignalRestClient.post("players/" + getUserId() + "/on_purchase", jSONObject, responseHandler);
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", th);
            }
        }
    }

    private static boolean openURLFromNotification(Context context, JSONArray jSONArray) {
        boolean z = false;
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("custom")) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom"));
                    if (jSONObject2.has("u")) {
                        String optString = jSONObject2.optString("u", null);
                        if (!optString.contains("://")) {
                            optString = "http://" + optString;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(optString));
                        intent.addFlags(1476919296);
                        context.startActivity(intent);
                        z = true;
                    }
                }
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i + "/" + length + " for launching a web URL.", th);
            }
        }
        return z;
    }

    private static void runNotificationOpenedCallback(JSONArray jSONArray, boolean z, boolean z2) {
        if (mInitBuilder == null || mInitBuilder.mNotificationOpenedHandler == null) {
            unprocessedOpenedNotifis.add(jSONArray);
        } else {
            fireNotificationOpenedHandler(generateOsNotificationOpenResult(jSONArray, z, z2));
        }
    }

    private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray jSONArray, boolean z, boolean z2) {
        String optString;
        Throwable th;
        String str;
        int i;
        String str2;
        String str3 = null;
        int length = jSONArray.length();
        int i2 = 1;
        OSNotificationOpenResult oSNotificationOpenResult = new OSNotificationOpenResult();
        OSNotification oSNotification = new OSNotification();
        oSNotification.isAppInFocus = isAppActive();
        oSNotification.shown = z;
        oSNotification.androidNotificationId = jSONArray.optJSONObject(0).optInt("notificationId");
        int i3 = 0;
        while (i3 < length) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                if (jSONObject.has("custom")) {
                    oSNotification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(jSONObject);
                    if (str3 == null && jSONObject.has("actionSelected")) {
                        optString = jSONObject.optString("actionSelected", null);
                        if (i2 == 0) {
                            i2 = 0;
                        } else {
                            try {
                                if (oSNotification.groupedNotifications == null) {
                                    oSNotification.groupedNotifications = new ArrayList();
                                }
                                oSNotification.groupedNotifications.add(oSNotification.payload);
                            } catch (Throwable th2) {
                                th = th2;
                                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i3 + "/" + length + " for callback.", th);
                                str = optString;
                                i = i2;
                                str2 = str;
                                i3++;
                                str3 = str2;
                                i2 = i;
                            }
                        }
                        str = optString;
                        i = i2;
                        str2 = str;
                        i3++;
                        str3 = str2;
                        i2 = i;
                    }
                }
                optString = str3;
                if (i2 == 0) {
                    if (oSNotification.groupedNotifications == null) {
                        oSNotification.groupedNotifications = new ArrayList();
                    }
                    oSNotification.groupedNotifications.add(oSNotification.payload);
                } else {
                    i2 = 0;
                }
                str = optString;
                i = i2;
                str2 = str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                optString = str3;
                th = th4;
                Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + i3 + "/" + length + " for callback.", th);
                str = optString;
                i = i2;
                str2 = str;
                i3++;
                str3 = str2;
                i2 = i;
            }
            i3++;
            str3 = str2;
            i2 = i;
        }
        oSNotificationOpenResult.notification = oSNotification;
        oSNotificationOpenResult.action = new OSNotificationAction();
        oSNotificationOpenResult.action.actionID = str3;
        oSNotificationOpenResult.action.type = str3 != null ? ActionType.ActionTaken : ActionType.Opened;
        if (z2) {
            oSNotificationOpenResult.notification.displayType = DisplayType.InAppAlert;
        } else {
            oSNotificationOpenResult.notification.displayType = DisplayType.Notification;
        }
        return oSNotificationOpenResult;
    }

    private static void fireNotificationOpenedHandler(final OSNotificationOpenResult oSNotificationOpenResult) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mInitBuilder.mNotificationOpenedHandler.notificationOpened(oSNotificationOpenResult);
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    OneSignal.mInitBuilder.mNotificationOpenedHandler.notificationOpened(oSNotificationOpenResult);
                }
            });
        }
    }

    static void handleNotificationReceived(JSONArray jSONArray, boolean z, boolean z2) {
        if (mInitBuilder != null && mInitBuilder.mNotificationReceivedHandler != null) {
            mInitBuilder.mNotificationReceivedHandler.notificationReceived(generateOsNotificationOpenResult(jSONArray, z, z2).notification);
        }
    }

    public static void handleNotificationOpen(Context context, JSONArray jSONArray, boolean z) {
        notificationOpenedRESTCall(context, jSONArray);
        boolean z2 = false;
        boolean equals = "DISABLE".equals(OSUtils.getManifestMeta(context, "com.onesignal.NotificationOpened.DEFAULT"));
        if (!equals) {
            z2 = openURLFromNotification(context, jSONArray);
        }
        runNotificationOpenedCallback(jSONArray, true, z);
        if (!z && !r0 && !equals) {
            fireIntentFromNotificationOpen(context);
        }
    }

    private static void fireIntentFromNotificationOpen(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setFlags(268566528);
            context.startActivity(launchIntentForPackage);
        }
    }

    private static void notificationOpenedRESTCall(Context context, JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("custom")) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.optString("custom", null));
                    if (jSONObject2.has("i")) {
                        String optString = jSONObject2.optString("i", null);
                        jSONObject2 = new JSONObject();
                        jSONObject2.put(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, getSavedAppId(context));
                        jSONObject2.put("player_id", getSavedUserId(context));
                        jSONObject2.put("opened", true);
                        OneSignalRestClient.put("notifications/" + optString, jSONObject2, new ResponseHandler() {
                            void onFailure(int i, String str, Throwable th) {
                                OneSignal.logHttpError("sending Notification Opened Failed", i, th, str);
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", th);
            }
        }
    }

    private static void SaveAppId(String str) {
        if (appContext != null) {
            Editor edit = getGcmPreferences(appContext).edit();
            edit.putString("GT_APP_ID", str);
            edit.commit();
        }
    }

    static String getSavedAppId() {
        return getSavedAppId(appContext);
    }

    private static String getSavedAppId(Context context) {
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        return getGcmPreferences(context).getString("GT_APP_ID", null);
    }

    private static String getSavedUserId(Context context) {
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        return getGcmPreferences(context).getString("GT_PLAYER_ID", null);
    }

    static String getUserId() {
        if (userId == null && appContext != null) {
            userId = getGcmPreferences(appContext).getString("GT_PLAYER_ID", null);
        }
        return userId;
    }

    static void saveUserId(String str) {
        userId = str;
        if (appContext != null) {
            Editor edit = getGcmPreferences(appContext).edit();
            edit.putString("GT_PLAYER_ID", userId);
            edit.commit();
        }
    }

    static void updateUserIdDependents(String str) {
        saveUserId(str);
        fireIdsAvailableCallback();
        internalFireGetTagsCallback(pendingGetTagsHandler);
    }

    static boolean getVibrate(Context context) {
        return getGcmPreferences(context).getBoolean("GT_VIBRATE_ENABLED", true);
    }

    static boolean getSoundEnabled(Context context) {
        return getGcmPreferences(context).getBoolean("GT_SOUND_ENABLED", true);
    }

    static void setLastSessionTime(long j) {
        Editor edit = getGcmPreferences(appContext).edit();
        edit.putLong("OS_LAST_SESSION_TIME", j);
        edit.apply();
    }

    static long getLastSessionTime(Context context) {
        return getGcmPreferences(context).getLong("OS_LAST_SESSION_TIME", -31000);
    }

    static boolean getNotificationsWhenActiveEnabled() {
        if (mInitBuilder == null || mInitBuilder.mDisplayOption == OSInFocusDisplayOption.Notification) {
            return true;
        }
        return false;
    }

    static boolean getInAppAlertNotificationEnabled() {
        if (mInitBuilder != null && mInitBuilder.mDisplayOption == OSInFocusDisplayOption.InAppAlert) {
            return true;
        }
        return false;
    }

    public static void setSubscription(boolean z) {
        if (appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not set subscription.");
        } else {
            OneSignalStateSynchronizer.setSubscription(z);
        }
    }

    static long GetUnsentActiveTime() {
        if (unSentActiveTime == -1 && appContext != null) {
            unSentActiveTime = getGcmPreferences(appContext).getLong("GT_UNSENT_ACTIVE_TIME", 0);
        }
        Log(LOG_LEVEL.INFO, "GetUnsentActiveTime: " + unSentActiveTime);
        return unSentActiveTime;
    }

    private static void SaveUnsentActiveTime(long j) {
        unSentActiveTime = j;
        if (appContext != null) {
            Log(LOG_LEVEL.INFO, "SaveUnsentActiveTime: " + unSentActiveTime);
            Editor edit = getGcmPreferences(appContext).edit();
            edit.putLong("GT_UNSENT_ACTIVE_TIME", j);
            edit.commit();
        }
    }

    static SharedPreferences getGcmPreferences(Context context) {
        return context.getSharedPreferences(OneSignal.class.getSimpleName(), 0);
    }

    static boolean isDuplicateNotification(String str, Context context) {
        if (str == null || BuildConfig.FLAVOR.equals(str)) {
            return false;
        }
        Cursor query = OneSignalDbHelper.getInstance(context).getReadableDatabase().query("notification", new String[]{TapjoyConstants.TJC_NOTIFICATION_ID}, "notification_id = ?", new String[]{str}, null, null, null);
        boolean moveToFirst = query.moveToFirst();
        query.close();
        if (!moveToFirst) {
            return false;
        }
        Log(LOG_LEVEL.DEBUG, "Duplicate GCM message received, skip processing of " + str);
        return true;
    }

    static void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    static boolean notValidOrDuplicated(Context context, JSONObject jSONObject) {
        String notificationIdFromGCMJsonPayload = getNotificationIdFromGCMJsonPayload(jSONObject);
        return notificationIdFromGCMJsonPayload == null || isDuplicateNotification(notificationIdFromGCMJsonPayload, context);
    }

    static String getNotificationIdFromGCMBundle(Bundle bundle) {
        if (bundle.isEmpty()) {
            return null;
        }
        try {
            if (bundle.containsKey("custom")) {
                JSONObject jSONObject = new JSONObject(bundle.getString("custom"));
                if (jSONObject.has("i")) {
                    return jSONObject.optString("i", null);
                }
                Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
                return null;
            }
            Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
            return null;
        } catch (Throwable th) {
            Log(LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", th);
            return null;
        }
    }

    static String getNotificationIdFromGCMJsonPayload(JSONObject jSONObject) {
        String str = null;
        try {
            str = new JSONObject(jSONObject.optString("custom")).optString("i", null);
        } catch (Throwable th) {
        }
        return str;
    }

    static boolean isAppActive() {
        return initDone && isForeground();
    }

    static void updateOnSessionDependents() {
        sendAsSession = false;
        setLastSessionTime(System.currentTimeMillis());
    }

    private static boolean isPastOnSessionTime() {
        return (System.currentTimeMillis() - getLastSessionTime(appContext)) / 1000 >= 30;
    }

    private static void startSyncService() {
        if (!startedSyncService) {
            startedSyncService = true;
            appContext.startService(new Intent(appContext, SyncService.class));
        }
    }
}
