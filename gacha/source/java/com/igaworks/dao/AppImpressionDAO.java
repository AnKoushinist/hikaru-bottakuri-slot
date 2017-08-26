package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.igaworks.impl.InternalAction;
import java.util.Date;
import org.cocos2dx.lib.BuildConfig;

public class AppImpressionDAO {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ long val$basetime;
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context, long j) {
            this.val$context = context;
            this.val$basetime = j;
        }

        public void run() {
            Editor edit = AppImpressionDAO.getSharedPreferencesForFirstStart(this.val$context).edit();
            edit.putLong("ServerBaseTimeOffset", this.val$basetime);
            edit.commit();
        }
    }

    public static void addFirstStartToSP(Context context) {
        Editor edit = getSharedPreferencesForFirstStart(context).edit();
        edit.putBoolean("fts", true);
        edit.commit();
    }

    public static SharedPreferences getSharedPreferencesForFirstStart(Context context) {
        return context.getSharedPreferences("firstStart", 0);
    }

    public static void addRequestPermissionAlready(Context context) {
        Editor edit = getSharedPreferencesForFirstStart(context).edit();
        edit.putBoolean("RequestPermissionAlready", true);
        edit.commit();
    }

    public static boolean getRequestPermisisonAlready(Context context) {
        return getSharedPreferencesForFirstStart(context).getBoolean("RequestPermissionAlready", false);
    }

    public static void setServerBaseTimeOffset(Context context, long j) {
        InternalAction.NETWORK_EXECUTOR.execute(new AnonymousClass1(context, j));
    }

    public static long getServerBaseTimeOffset(Context context) {
        return getSharedPreferencesForFirstStart(context).getLong("ServerBaseTimeOffset", 0);
    }

    public static void setLastDailyRentionDate(Context context) {
        Editor edit = getSharedPreferencesForFirstStart(context).edit();
        edit.putString("LastDailyRentionDate", AdbrixDB_v2.DB_DATE_FORMAT.format(new Date()));
        edit.commit();
    }

    public static String getLastDailyRentionDate(Context context) {
        return context.getSharedPreferences("firstStart", 0).getString("LastDailyRentionDate", BuildConfig.FLAVOR);
    }
}
