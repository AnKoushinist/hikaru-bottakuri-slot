package com.onesignal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

class OneSignalStateSynchronizer {
    private static final String[] LOCATION_FIELDS = new String[]{String.LAT, String.LONG, "loc_acc", "loc_type"};
    private static final Set<String> LOCATION_FIELDS_SET = new HashSet(Arrays.asList(LOCATION_FIELDS));
    private static Context appContext;
    private static UserState currentUserState;
    private static final Object networkHandlerSyncLock = new Object() {
    };
    static HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap();
    private static boolean nextSyncIsSession = false;
    static boolean serverSuccess;
    private static final Object syncLock = new Object() {
    };
    private static UserState toSyncUserState;
    private static boolean waitingForSessionResponse = false;

    static class GetTagsResult {
        public JSONObject result;
        public boolean serverSuccess;

        GetTagsResult(boolean z, JSONObject jSONObject) {
            this.serverSuccess = z;
            this.result = jSONObject;
        }
    }

    static class NetworkHandlerThread extends HandlerThread {
        int currentRetry;
        Handler mHandler = null;
        int mType;

        NetworkHandlerThread(int i) {
            super("NetworkHandlerThread");
            this.mType = i;
            start();
            this.mHandler = new Handler(getLooper());
        }

        public void runNewJob() {
            this.currentRetry = 0;
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(getNewRunnable(), RFLConstants.GPS_TIME);
        }

        private Runnable getNewRunnable() {
            switch (this.mType) {
                case TwitterResponse.NONE /*0*/:
                    return new Runnable() {
                        public void run() {
                            OneSignalStateSynchronizer.syncUserState(false);
                        }
                    };
                default:
                    return null;
            }
        }

        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages(null);
        }

        void doRetry() {
            if (this.currentRetry < 3 && !this.mHandler.hasMessages(0)) {
                this.currentRetry++;
                this.mHandler.postDelayed(getNewRunnable(), (long) (this.currentRetry * 15000));
            }
        }
    }

    class UserState {
        private final int UNSUBSCRIBE_VALUE;
        JSONObject dependValues;
        private String persistKey;
        JSONObject syncValues;

        private UserState(String str, boolean z) {
            this.UNSUBSCRIBE_VALUE = -2;
            this.persistKey = str;
            if (z) {
                loadState();
                return;
            }
            this.dependValues = new JSONObject();
            this.syncValues = new JSONObject();
        }

        private UserState deepClone(String str) {
            UserState userState = new UserState(str, false);
            try {
                userState.dependValues = new JSONObject(this.dependValues.toString());
                userState.syncValues = new JSONObject(this.syncValues.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return userState;
        }

        private void addDependFields() {
            try {
                this.syncValues.put("notification_types", getNotificationTypes());
            } catch (JSONException e) {
            }
        }

        private int getNotificationTypes() {
            int optInt = this.dependValues.optInt("subscribableStatus", 1);
            boolean optBoolean = this.dependValues.optBoolean("userSubscribePref", true);
            if (optInt < -2) {
                return optInt;
            }
            return optBoolean ? 1 : -2;
        }

        private Set<String> getGroupChangeField(JSONObject jSONObject, JSONObject jSONObject2) {
            try {
                if (jSONObject.getDouble(String.LAT) == jSONObject2.getDouble(String.LAT) && jSONObject.getDouble(String.LONG) == jSONObject2.getDouble(String.LONG) && jSONObject.getDouble("loc_acc") == jSONObject2.getDouble("loc_acc") && jSONObject.getDouble("loc_type") == jSONObject2.getDouble("loc_type")) {
                    return null;
                }
                return OneSignalStateSynchronizer.LOCATION_FIELDS_SET;
            } catch (Throwable th) {
                return OneSignalStateSynchronizer.LOCATION_FIELDS_SET;
            }
        }

        private JSONObject generateJsonDiff(UserState userState, boolean z) {
            addDependFields();
            userState.addDependFields();
            JSONObject access$200 = OneSignalStateSynchronizer.generateJsonDiff(this.syncValues, userState.syncValues, null, getGroupChangeField(this.syncValues, userState.syncValues));
            if (!z && access$200.toString().equals("{}")) {
                return null;
            }
            try {
                if (!access$200.has(TapjoyConstants.TJC_APP_ID)) {
                    access$200.put(TapjoyConstants.TJC_APP_ID, (String) this.syncValues.opt(TapjoyConstants.TJC_APP_ID));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return access$200;
        }

        void set(String str, Object obj) {
            try {
                this.syncValues.put(str, obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        void setState(String str, Object obj) {
            try {
                this.dependValues.put(str, obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void loadState() {
            int i = 1;
            SharedPreferences gcmPreferences = OneSignal.getGcmPreferences(OneSignalStateSynchronizer.appContext);
            String string = gcmPreferences.getString("ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, null);
            if (string == null) {
                this.dependValues = new JSONObject();
                try {
                    int i2;
                    boolean z;
                    if (this.persistKey.equals("CURRENT_STATE")) {
                        i2 = gcmPreferences.getInt("ONESIGNAL_SUBSCRIPTION", 1);
                    } else {
                        i2 = gcmPreferences.getInt("ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
                    }
                    if (i2 == -2) {
                        z = false;
                    } else {
                        i = i2;
                        z = true;
                    }
                    this.dependValues.put("subscribableStatus", i);
                    this.dependValues.put("userSubscribePref", z);
                } catch (JSONException e) {
                }
            } else {
                try {
                    this.dependValues = new JSONObject(string);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            string = gcmPreferences.getString("ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, null);
            if (string == null) {
                try {
                    this.syncValues = new JSONObject();
                    this.syncValues.put("identifier", gcmPreferences.getString("GT_REGISTRATION_ID", null));
                    return;
                } catch (JSONException e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            this.syncValues = new JSONObject(string);
        }

        private void persistState() {
            synchronized (OneSignalStateSynchronizer.syncLock) {
                modifySyncValuesJsonArray("pkgs");
                Editor edit = OneSignal.getGcmPreferences(OneSignalStateSynchronizer.appContext).edit();
                edit.putString("ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
                edit.putString("ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
                edit.commit();
            }
        }

        private void modifySyncValuesJsonArray(String str) {
            int i = 0;
            if (this.syncValues.has(str + "_d") || !this.syncValues.has(str + "_d")) {
                try {
                    JSONArray jSONArray;
                    if (this.syncValues.has(str)) {
                        jSONArray = this.syncValues.getJSONArray(str);
                    } else {
                        jSONArray = new JSONArray();
                    }
                    JSONArray jSONArray2 = new JSONArray();
                    if (this.syncValues.has(str + "_d")) {
                        String access$500 = OneSignalStateSynchronizer.toStringNE(this.syncValues.getJSONArray(str + "_d"));
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            if (!access$500.contains(jSONArray.getString(i2))) {
                                jSONArray2.put(jSONArray.get(i2));
                            }
                        }
                    } else {
                        jSONArray2 = jSONArray;
                    }
                    if (this.syncValues.has(str + "_a")) {
                        jSONArray = this.syncValues.getJSONArray(str + "_a");
                        while (i < jSONArray.length()) {
                            jSONArray2.put(jSONArray.get(i));
                            i++;
                        }
                    }
                    this.syncValues.put(str, jSONArray2);
                    this.syncValues.remove(str + "_a");
                    this.syncValues.remove(str + "_d");
                } catch (Throwable th) {
                }
            }
        }

        private void persistStateAfterSync(JSONObject jSONObject, JSONObject jSONObject2) {
            if (jSONObject != null) {
                OneSignalStateSynchronizer.generateJsonDiff(this.dependValues, jSONObject, this.dependValues, null);
            }
            if (jSONObject2 != null) {
                OneSignalStateSynchronizer.generateJsonDiff(this.syncValues, jSONObject2, this.syncValues, null);
                mergeTags(jSONObject2, null);
            }
            if (jSONObject != null || jSONObject2 != null) {
                persistState();
            }
        }

        void mergeTags(JSONObject jSONObject, JSONObject jSONObject2) {
            synchronized (OneSignalStateSynchronizer.syncLock) {
                if (jSONObject.has("tags")) {
                    JSONObject jSONObject3;
                    if (this.syncValues.has("tags")) {
                        try {
                            jSONObject3 = new JSONObject(this.syncValues.optString("tags"));
                        } catch (JSONException e) {
                            jSONObject3 = new JSONObject();
                        }
                    } else {
                        jSONObject3 = new JSONObject();
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("tags");
                    Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        if (BuildConfig.FLAVOR.equals(optJSONObject.optString(str))) {
                            jSONObject3.remove(str);
                        } else {
                            if (jSONObject2 != null) {
                                try {
                                    if (jSONObject2.has(str)) {
                                    }
                                } catch (Throwable th) {
                                }
                            }
                            jSONObject3.put(str, optJSONObject.optString(str));
                        }
                    }
                    if (jSONObject3.toString().equals("{}")) {
                        this.syncValues.remove("tags");
                    } else {
                        this.syncValues.put("tags", jSONObject3);
                    }
                }
            }
        }
    }

    OneSignalStateSynchronizer() {
    }

    private static JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject synchronizedGenerateJsonDiff;
        synchronized (syncLock) {
            synchronizedGenerateJsonDiff = synchronizedGenerateJsonDiff(jSONObject, jSONObject2, jSONObject3, set);
        }
        return synchronizedGenerateJsonDiff;
    }

    private static JSONObject synchronizedGenerateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject2 == null) {
            return jSONObject3;
        }
        JSONObject jSONObject4;
        Iterator keys = jSONObject2.keys();
        if (jSONObject3 != null) {
            jSONObject4 = jSONObject3;
        } else {
            jSONObject4 = new JSONObject();
        }
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                Object obj = jSONObject2.get(str);
                if (jSONObject.has(str)) {
                    if (obj instanceof JSONObject) {
                        JSONObject jSONObject5;
                        JSONObject jSONObject6 = jSONObject.getJSONObject(str);
                        if (jSONObject3 == null || !jSONObject3.has(str)) {
                            jSONObject5 = null;
                        } else {
                            jSONObject5 = jSONObject3.getJSONObject(str);
                        }
                        String jSONObject7 = synchronizedGenerateJsonDiff(jSONObject6, (JSONObject) obj, jSONObject5, set).toString();
                        if (!jSONObject7.equals("{}")) {
                            jSONObject4.put(str, new JSONObject(jSONObject7));
                        }
                    } else if (obj instanceof JSONArray) {
                        handleJsonArray(str, (JSONArray) obj, jSONObject.getJSONArray(str), jSONObject4);
                    } else if (set == null || !set.contains(str)) {
                        Object obj2 = jSONObject.get(str);
                        if (!obj.equals(obj2)) {
                            if (!(obj2 instanceof Integer) || BuildConfig.FLAVOR.equals(obj)) {
                                jSONObject4.put(str, obj);
                            } else if (((Number) obj2).doubleValue() != ((Number) obj).doubleValue()) {
                                jSONObject4.put(str, obj);
                            }
                        }
                    } else {
                        jSONObject4.put(str, obj);
                    }
                } else if (obj instanceof JSONObject) {
                    jSONObject4.put(str, new JSONObject(obj.toString()));
                } else if (obj instanceof JSONArray) {
                    handleJsonArray(str, (JSONArray) obj, null, jSONObject4);
                } else {
                    jSONObject4.put(str, obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject4;
    }

    private static void handleJsonArray(String str, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) {
        int i = 0;
        if (str.endsWith("_a") || str.endsWith("_d")) {
            jSONObject.put(str, jSONArray);
            return;
        }
        String toStringNE = toStringNE(jSONArray);
        JSONArray jSONArray3 = new JSONArray();
        JSONArray jSONArray4 = new JSONArray();
        String toStringNE2 = jSONArray2 == null ? null : toStringNE(jSONArray2);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String str2 = (String) jSONArray.get(i2);
            if (jSONArray2 == null || !toStringNE2.contains(str2)) {
                jSONArray3.put(str2);
            }
        }
        if (jSONArray2 != null) {
            while (i < jSONArray2.length()) {
                CharSequence string = jSONArray2.getString(i);
                if (!toStringNE.contains(string)) {
                    jSONArray4.put(string);
                }
                i++;
            }
        }
        if (!jSONArray3.toString().equals("[]")) {
            jSONObject.put(str + "_a", jSONArray3);
        }
        if (!jSONArray4.toString().equals("[]")) {
            jSONObject.put(str + "_d", jSONArray4);
        }
    }

    private static String toStringNE(JSONArray jSONArray) {
        String str = "[";
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                str = str + "\"" + jSONArray.getString(i) + "\"";
                i++;
            } catch (Throwable th) {
            }
        }
        return str + "]";
    }

    private static JSONObject getTagsWithoutDeletedKeys(JSONObject jSONObject) {
        if (!jSONObject.has("tags")) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        synchronized (syncLock) {
            JSONObject optJSONObject = jSONObject.optJSONObject("tags");
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    Object obj = optJSONObject.get(str);
                    if (!BuildConfig.FLAVOR.equals(obj)) {
                        jSONObject2.put(str, obj);
                    }
                } catch (Throwable th) {
                }
            }
        }
        return jSONObject2;
    }

    public static void stopAndPersist() {
        for (Entry value : networkHandlerThreads.entrySet()) {
            ((NetworkHandlerThread) value.getValue()).stopScheduledRunnable();
        }
        if (toSyncUserState != null) {
            toSyncUserState.persistState();
        }
    }

    static void initUserState(Context context) {
        appContext = context;
        synchronized (syncLock) {
            if (currentUserState == null) {
                OneSignalStateSynchronizer oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
                oneSignalStateSynchronizer.getClass();
                currentUserState = new UserState("CURRENT_STATE", true);
            }
            if (toSyncUserState == null) {
                oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
                oneSignalStateSynchronizer.getClass();
                toSyncUserState = new UserState("TOSYNC_STATE", true);
            }
        }
    }

    static UserState getNewUserState() {
        OneSignalStateSynchronizer oneSignalStateSynchronizer = new OneSignalStateSynchronizer();
        oneSignalStateSynchronizer.getClass();
        return new UserState("nonPersist", false);
    }

    static void syncUserState(boolean z) {
        String userId = OneSignal.getUserId();
        boolean z2 = userId == null || (nextSyncIsSession && !waitingForSessionResponse);
        final JSONObject access$700 = currentUserState.generateJsonDiff(toSyncUserState, z2);
        final JSONObject generateJsonDiff = generateJsonDiff(currentUserState.dependValues, toSyncUserState.dependValues, null, null);
        if (access$700 == null) {
            currentUserState.persistStateAfterSync(generateJsonDiff, null);
            return;
        }
        toSyncUserState.persistState();
        if (userId == null && !nextSyncIsSession) {
            return;
        }
        if (!z2 || z) {
            OneSignalRestClient.putSync("players/" + userId, access$700, new ResponseHandler() {
                void onFailure(int i, String str, Throwable th) {
                    OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + i + "\nresponse: " + str);
                    if (OneSignalStateSynchronizer.response400WithErrorsContaining(i, str, "No user with this id found")) {
                        OneSignalStateSynchronizer.handlePlayerDeletedFromServer();
                    } else {
                        OneSignalStateSynchronizer.getNetworkHandlerThread(Integer.valueOf(0)).doRetry();
                    }
                }

                void onSuccess(String str) {
                    OneSignalStateSynchronizer.currentUserState.persistStateAfterSync(generateJsonDiff, access$700);
                }
            });
            return;
        }
        String str;
        if (userId == null) {
            str = "players";
        } else {
            str = "players/" + userId + "/on_session";
        }
        waitingForSessionResponse = true;
        OneSignalRestClient.postSync(str, access$700, new ResponseHandler() {
            void onFailure(int i, String str, Throwable th) {
                OneSignalStateSynchronizer.waitingForSessionResponse = false;
                OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + i + "\nresponse: " + str);
                if (OneSignalStateSynchronizer.response400WithErrorsContaining(i, str, "not a valid device_type")) {
                    OneSignalStateSynchronizer.handlePlayerDeletedFromServer();
                } else {
                    OneSignalStateSynchronizer.getNetworkHandlerThread(Integer.valueOf(0)).doRetry();
                }
            }

            void onSuccess(String str) {
                OneSignalStateSynchronizer.nextSyncIsSession = OneSignalStateSynchronizer.waitingForSessionResponse = false;
                OneSignalStateSynchronizer.currentUserState.persistStateAfterSync(generateJsonDiff, access$700);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("id")) {
                        String optString = jSONObject.optString("id");
                        OneSignal.updateUserIdDependents(optString);
                        OneSignal.Log(LOG_LEVEL.INFO, "Device registered, UserId = " + optString);
                    } else {
                        OneSignal.Log(LOG_LEVEL.INFO, "session sent, UserId = " + OneSignal.getUserId());
                    }
                    OneSignal.updateOnSessionDependents();
                } catch (Throwable th) {
                    OneSignal.Log(LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", th);
                }
            }
        });
    }

    private static boolean response400WithErrorsContaining(int i, String str, String str2) {
        if (i != HttpResponseCode.BAD_REQUEST || str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("errors") && jSONObject.optString("errors").contains(str2)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static NetworkHandlerThread getNetworkHandlerThread(Integer num) {
        NetworkHandlerThread networkHandlerThread;
        synchronized (networkHandlerSyncLock) {
            if (!networkHandlerThreads.containsKey(num)) {
                networkHandlerThreads.put(num, new NetworkHandlerThread(num.intValue()));
            }
            networkHandlerThread = (NetworkHandlerThread) networkHandlerThreads.get(num);
        }
        return networkHandlerThread;
    }

    private static UserState getUserStateForModification() {
        if (toSyncUserState == null) {
            toSyncUserState = currentUserState.deepClone("TOSYNC_STATE");
        }
        postNewSyncUserState();
        return toSyncUserState;
    }

    private static void postNewSyncUserState() {
        getNetworkHandlerThread(Integer.valueOf(0)).runNewJob();
    }

    static void postUpdate(UserState userState, boolean z) {
        JSONObject jSONObject = getUserStateForModification().syncValues;
        generateJsonDiff(jSONObject, userState.syncValues, jSONObject, null);
        jSONObject = getUserStateForModification().dependValues;
        generateJsonDiff(jSONObject, userState.dependValues, jSONObject, null);
        boolean z2 = nextSyncIsSession || z || OneSignal.getUserId() == null;
        nextSyncIsSession = z2;
    }

    static void setSubscription(boolean z) {
        try {
            getUserStateForModification().dependValues.put("userSubscribePref", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static boolean getSubscribed() {
        return toSyncUserState.getNotificationTypes() > 0;
    }

    static String getRegistrationId() {
        return toSyncUserState.syncValues.optString("identifier", null);
    }

    static GetTagsResult getTags(boolean z) {
        if (z) {
            OneSignalRestClient.getSync("players/" + OneSignal.getUserId(), new ResponseHandler() {
                void onSuccess(String str) {
                    OneSignalStateSynchronizer.serverSuccess = true;
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.has("tags")) {
                            JSONObject access$200 = OneSignalStateSynchronizer.generateJsonDiff(OneSignalStateSynchronizer.currentUserState.syncValues.optJSONObject("tags"), OneSignalStateSynchronizer.toSyncUserState.syncValues.optJSONObject("tags"), null, null);
                            OneSignalStateSynchronizer.currentUserState.syncValues.put("tags", jSONObject.optJSONObject("tags"));
                            OneSignalStateSynchronizer.currentUserState.persistState();
                            OneSignalStateSynchronizer.toSyncUserState.mergeTags(jSONObject, access$200);
                            OneSignalStateSynchronizer.toSyncUserState.persistState();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return new GetTagsResult(serverSuccess, getTagsWithoutDeletedKeys(toSyncUserState.syncValues));
    }

    static void resetCurrentState() {
        OneSignal.saveUserId(null);
        currentUserState.syncValues = new JSONObject();
        currentUserState.persistState();
        OneSignal.setLastSessionTime(-3660);
    }

    static void handlePlayerDeletedFromServer() {
        resetCurrentState();
        nextSyncIsSession = true;
        postNewSyncUserState();
    }
}
