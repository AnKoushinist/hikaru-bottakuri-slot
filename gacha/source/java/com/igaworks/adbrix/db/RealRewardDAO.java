package com.igaworks.adbrix.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.igaworks.core.IgawLogger;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RealRewardDAO {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static RealRewardDAO singleton;

    private RealRewardDAO() {
    }

    public static RealRewardDAO getInstance() {
        if (singleton == null) {
            singleton = new RealRewardDAO();
        }
        return singleton;
    }

    private SharedPreferences getSharedPreference(Context context, String str) {
        return context.getSharedPreferences("real_reward_sp" + str, 0);
    }

    private Editor getEditor(Context context, String str) {
        return getSharedPreference(context, str).edit();
    }

    public void saveSessionNo(Context context, int i, long j) {
        IgawLogger.Logging(context, "IGAW_QA", "save session : rrk/sn = " + i + Operation.DIVISION + j, 3);
        Editor editor = getEditor(context, "_ss");
        editor.putLong(new StringBuilder(String.valueOf(i)).toString(), j);
        editor.commit();
        editor = getEditor(context, "_st");
        editor.putLong(new StringBuilder(String.valueOf(j)).toString(), new Date().getTime());
        editor.commit();
    }

    public long getSessionNo(Context context, int i) {
        IgawLogger.Logging(context, "IGAW_QA", "get session : " + getSharedPreference(context, "_ss").contains(new StringBuilder(String.valueOf(i)).toString()), 3);
        if (getSharedPreference(context, "_ss").contains(new StringBuilder(String.valueOf(i)).toString())) {
            return getSharedPreference(context, "_ss").getLong(new StringBuilder(String.valueOf(i)).toString(), -1);
        }
        return -1;
    }

    public Map<String, Long> getActiveSessionNo(Context context) {
        return getSharedPreference(context, "_ss").getAll();
    }

    public long getSessionTime(Context context, long j) {
        return getSharedPreference(context, "_st").getLong(new StringBuilder(String.valueOf(j)).toString(), -1);
    }

    public void clearSessions(Context context, int i) {
        IgawLogger.Logging(context, "IGAW_QA", "clearSessions", 3);
        long j = getSharedPreference(context, "_ss").getLong(new StringBuilder(String.valueOf(i)).toString(), -1);
        Editor editor = getEditor(context, "_ss");
        editor.remove(new StringBuilder(String.valueOf(i)).toString());
        editor.commit();
        editor = getEditor(context, "_st");
        editor.remove(new StringBuilder(String.valueOf(j)).toString());
        editor.commit();
    }

    public void saveCompletedRealRewardKey(Context context, int i) {
        Editor editor = getEditor(context, "_cp");
        editor.putInt(new StringBuilder(String.valueOf(i)).toString(), i);
        editor.commit();
    }

    public boolean isCompetedRealReward(Context context, int i) {
        if (getSharedPreference(context, "_cp").contains(new StringBuilder(String.valueOf(i)).toString())) {
            return true;
        }
        return false;
    }

    public Map<String, Long> getRetryCompleteCache(Context context) {
        return getSharedPreference(context, "_rc").getAll();
    }

    public void clearRetryCompleteCache(Context context, int i) {
        Editor editor = getEditor(context, "_rc");
        editor.remove(new StringBuilder(String.valueOf(i)).toString());
        editor.commit();
    }

    public void saveRetryRedeemCache(Context context, int i, long j) {
        Editor editor = getEditor(context, "_rr");
        editor.putLong(new StringBuilder(String.valueOf(i)).toString(), j);
        editor.commit();
    }

    public Map<String, Long> getRetryRedeemCache(Context context) {
        return getSharedPreference(context, "_rr").getAll();
    }

    public void clearRetryRedeemCache(Context context, int i) {
        Editor editor = getEditor(context, "_rr");
        editor.remove(new StringBuilder(String.valueOf(i)).toString());
        editor.commit();
    }

    private String getDay() {
        return sdf.format(new Date());
    }

    public void saveDailyCompletion(Context context, int i) {
        Editor editor = getEditor(context, "_dc");
        editor.putInt(getDay() + "_" + i, i);
        editor.commit();
    }

    public boolean hasCompleteToday(Context context, int i) {
        return getSharedPreference(context, "_dc").contains(getDay() + "_" + i);
    }
}
