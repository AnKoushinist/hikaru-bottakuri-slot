package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.tapjoy.internal.ed;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class Tapjoy {
    public static final String INTENT_EXTRA_PUSH_PAYLOAD = "com.tapjoy.PUSH_PAYLOAD";

    public static String getVersion() {
        return ed.a().b();
    }

    public static void setDebugEnabled(boolean z) {
        ed.a().a(z);
    }

    public static boolean connect(Context context, String str) {
        return ed.a().a(context, str);
    }

    public static boolean connect(Context context, String str, Hashtable hashtable) {
        return ed.a().a(context, str, hashtable, null);
    }

    public static synchronized boolean connect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        boolean a;
        synchronized (Tapjoy.class) {
            a = ed.a().a(context, str, hashtable, tJConnectListener);
        }
        return a;
    }

    public static void getCurrencyBalance(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        ed.a().a(tJGetCurrencyBalanceListener);
    }

    public static void spendCurrency(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        ed.a().a(i, tJSpendCurrencyListener);
    }

    public static void awardCurrency(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        ed.a().a(i, tJAwardCurrencyListener);
    }

    public static void setEarnedCurrencyListener(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        ed.a().a(tJEarnedCurrencyListener);
    }

    @Deprecated
    public static void setCurrencyMultiplier(float f) {
        ed.a().a(f);
    }

    @Deprecated
    public static float getCurrencyMultiplier() {
        return ed.a().c();
    }

    public static void trackPurchase(String str, String str2, double d, String str3) {
        ed.a().a(str, str2, d, str3);
    }

    public static void trackPurchase(String str, String str2, String str3, String str4) {
        ed.a().a(str, str2, str3, str4);
    }

    @Deprecated
    public static void trackPurchase(String str, String str2) {
        ed.a().a(str, str2);
    }

    public static void trackEvent(String str) {
        ed.a().a(str);
    }

    public static void trackEvent(String str, long j) {
        ed.a().a(str, j);
    }

    public static void trackEvent(String str, String str2, long j) {
        ed.a().a(str, str2, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4) {
        ed.a().b(str, str2, str3, str4);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, long j) {
        ed.a().a(str, str2, str3, str4, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j) {
        ed.a().a(str, str2, str3, str4, str5, j);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        ed.a().a(str, str2, str3, str4, str5, j, str6, j2);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        ed.a().a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    public static void trackEvent(String str, String str2, String str3, String str4, Map map) {
        ed.a().a(str, str2, str3, str4, map);
    }

    public static void startSession() {
        ed.a().d();
    }

    public static void endSession() {
        ed.a().e();
    }

    public static void onActivityStart(Activity activity) {
        ed.a().a(activity);
    }

    public static void onActivityStop(Activity activity) {
        ed.a().b(activity);
    }

    public static void setUserID(String str) {
        setUserID(str, null);
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        ed.a().a(str, tJSetUserIDListener);
    }

    public static void setUserLevel(int i) {
        ed.a().a(i);
    }

    public static void setUserFriendCount(int i) {
        ed.a().b(i);
    }

    public static void setAppDataVersion(String str) {
        ed.a().b(str);
    }

    public static void setUserCohortVariable(int i, String str) {
        ed.a().a(i, str);
    }

    public static Set getUserTags() {
        return ed.a().f();
    }

    public static void setUserTags(Set set) {
        ed.a().a(set);
    }

    public static void clearUserTags() {
        ed.a().g();
    }

    public static void addUserTag(String str) {
        ed.a().c(str);
    }

    public static void removeUserTag(String str) {
        ed.a().d(str);
    }

    public static void setVideoListener(TJVideoListener tJVideoListener) {
        ed.a().a(tJVideoListener);
    }

    public static void enablePaidAppWithActionID(String str) {
        ed.a().e(str);
    }

    public static void actionComplete(String str) {
        ed.a().f(str);
    }

    public static void setGcmSender(String str) {
        ed.a().g(str);
    }

    public static boolean isPushNotificationDisabled() {
        return ed.a().h();
    }

    public static void setPushNotificationDisabled(boolean z) {
        ed.a().b(z);
    }

    public static void loadSharedLibrary() {
        try {
            System.loadLibrary("tapjoy");
        } catch (UnsatisfiedLinkError e) {
        }
    }

    public static void setGLSurfaceView(GLSurfaceView gLSurfaceView) {
        ed.a().a(gLSurfaceView);
    }

    public static boolean isConnected() {
        return ed.a().i();
    }
}
