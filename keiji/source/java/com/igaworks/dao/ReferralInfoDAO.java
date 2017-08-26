package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.igaworks.core.IgawLogger;
import org.cocos2dx.lib.BuildConfig;

public class ReferralInfoDAO {
    public static int getReferralInfo_conversionKey(Context context) {
        int i = -1;
        SharedPreferences sharedPreferences = context.getSharedPreferences("referral_info", 0);
        try {
            i = sharedPreferences.getInt("conversion_key", -1);
        } catch (Exception e) {
            try {
                i = Integer.parseInt(sharedPreferences.getString("conversion_key", "-1"));
            } catch (Exception e2) {
            }
        }
        return i;
    }

    public static long getReferralInfo_session_no(Context context) {
        long j = -1;
        SharedPreferences sharedPreferences = context.getSharedPreferences("referral_info", 0);
        try {
            j = sharedPreferences.getLong("session_no", -1);
        } catch (Exception e) {
            try {
                j = Long.parseLong(sharedPreferences.getString("session_no", "-1"));
            } catch (Exception e2) {
            }
        }
        return j;
    }

    public static String getReferralInfo_referrer_params(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("referral_info", 0);
        String str = BuildConfig.FLAVOR;
        try {
            str = sharedPreferences.getString("referrer_param", BuildConfig.FLAVOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void clearOnReceiveReferralFlag(Context context) {
        try {
            Editor edit = context.getSharedPreferences("referral_info", 0).edit();
            edit.putBoolean("onReceiveReferral", false);
            edit.putBoolean("success_send_cpi_referrer", true);
            edit.commit();
            IgawLogger.Logging(context, "IGAW_QA", "ReferralInfoDAO >> clearOnReceiveReferralFlag", 3, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getOnReceiveReferralFlag(Context context) {
        boolean z = false;
        try {
            z = context.getSharedPreferences("referral_info", z).getBoolean("onReceiveReferral", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    public static boolean isSentRefferrerSuccess2Adbrix(Context context) {
        boolean z = false;
        try {
            z = context.getSharedPreferences("referral_info", z).getBoolean("success_send_cpi_referrer", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }
}
