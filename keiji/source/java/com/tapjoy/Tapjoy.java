package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.tapjoy.internal.du;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class Tapjoy {
    public static final String INTENT_EXTRA_PUSH_PAYLOAD = "com.tapjoy.PUSH_PAYLOAD";

    public static String getVersion() {
        return du.a().b();
    }

    public static void setDebugEnabled(boolean z) {
        du.a().a(z);
    }

    public static boolean connect(Context context, String str) {
        return du.a().a(context, str);
    }

    public static boolean connect(Context context, String str, Hashtable hashtable) {
        return du.a().a(context, str, hashtable, null);
    }

    public static synchronized boolean connect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        boolean a;
        synchronized (Tapjoy.class) {
            a = du.a().a(context, str, hashtable, tJConnectListener);
        }
        return a;
    }

    public static TJPlacement getPlacement(String str, TJPlacementListener tJPlacementListener) {
        return du.a().a(str, tJPlacementListener);
    }

    public static void setActivity(Activity activity) {
        du.a().a(activity);
    }

    public static void getCurrencyBalance(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        du.a().a(tJGetCurrencyBalanceListener);
    }

    public static void spendCurrency(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        du.a().a(i, tJSpendCurrencyListener);
    }

    public static void awardCurrency(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        du.a().a(i, tJAwardCurrencyListener);
    }

    public static void setEarnedCurrencyListener(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        du.a().a(tJEarnedCurrencyListener);
    }

    @Deprecated
    public static void setCurrencyMultiplier(float f) {
        du.a().a(f);
    }

    @Deprecated
    public static float getCurrencyMultiplier() {
        return du.a().c();
    }

    public static void trackPurchase(String str, String str2, double d, String str3) {
        du.a().a(str, str2, d, str3);
    }

    public static void trackPurchase(String str, String str2, String str3, String str4) {
        du.a().a(str, str2, str3, str4);
    }

    @Deprecated
    public static void trackPurchase(String str, String str2) {
        du.a().a(str, str2);
    }

    public static void trackEvent(String str) {
        du.a().a(str);
    }

    public static void trackEvent(String str, long j) {
        du.a().a(str, j);
    }

    public static void trackEvent(String str, String str2, long j) {
        du.a().a(str, str2, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4) {
        du.a().b(str, str2, str3, str4);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, long j) {
        du.a().a(str, str2, str3, str4, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j) {
        du.a().a(str, str2, str3, str4, str5, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        du.a().a(str, str2, str3, str4, str5, j, str6, j2);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        du.a().a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, Map map) {
        du.a().a(str, str2, str3, str4, map);
    }

    public static void startSession() {
        du.a().d();
    }

    public static void endSession() {
        du.a().e();
    }

    public static void onActivityStart(Activity activity) {
        du.a().b(activity);
    }

    public static void onActivityStop(Activity activity) {
        du.a().c(activity);
    }

    public static void setUserID(String str) {
        setUserID(str, null);
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        du.a().a(str, tJSetUserIDListener);
    }

    public static void setUserLevel(int i) {
        du.a().a(i);
    }

    public static void setUserFriendCount(int i) {
        du.a().b(i);
    }

    public static void setAppDataVersion(String str) {
        du.a().b(str);
    }

    public static void setUserCohortVariable(int i, String str) {
        du.a().a(i, str);
    }

    public static Set getUserTags() {
        return du.a().f();
    }

    public static void setUserTags(Set set) {
        du.a().a(set);
    }

    public static void clearUserTags() {
        du.a().g();
    }

    public static void addUserTag(String str) {
        du.a().c(str);
    }

    public static void removeUserTag(String str) {
        du.a().d(str);
    }

    public static void setVideoListener(TJVideoListener tJVideoListener) {
        du.a().a(tJVideoListener);
    }

    public static void enablePaidAppWithActionID(String str) {
        du.a().e(str);
    }

    public static void actionComplete(String str) {
        du.a().f(str);
    }

    public static void setGcmSender(String str) {
        du.a().g(str);
    }

    public static boolean isPushNotificationDisabled() {
        return du.a().h();
    }

    public static void setPushNotificationDisabled(boolean z) {
        du.a().b(z);
    }

    public static void loadSharedLibrary() {
        try {
            System.loadLibrary("tapjoy");
        } catch (UnsatisfiedLinkError e) {
        }
    }

    public static void setGLSurfaceView(GLSurfaceView gLSurfaceView) {
        du.a().a(gLSurfaceView);
    }

    public static String getSupportURL() {
        return du.a().h(null);
    }

    public static String getSupportURL(String str) {
        return du.a().h(str);
    }

    public static boolean isConnected() {
        return du.a().i();
    }
}
