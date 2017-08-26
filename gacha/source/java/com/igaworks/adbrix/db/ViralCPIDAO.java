package com.igaworks.adbrix.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ViralCPIDAO {
    private static Activity activity;
    private static boolean restoreViralCPI = false;
    private static ViralCPIDAO singleton;

    private ViralCPIDAO() {
    }

    public static ViralCPIDAO getInstance() {
        if (singleton == null) {
            singleton = new ViralCPIDAO();
        }
        return singleton;
    }

    public static boolean isRestoreViralCPI() {
        return restoreViralCPI;
    }

    public static void setRestoreViralCPI(boolean z) {
        restoreViralCPI = z;
    }

    public static void saveRestoreViralDialog(Activity activity) {
        activity = activity;
        restoreViralCPI = true;
    }

    public static Activity getActivity() {
        return activity;
    }

    private SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("viral_cpi_sp", 0);
    }

    private Editor getEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    public void saveDoNotShow(Context context, int i) {
        Editor editor = getEditor(context);
        editor.putInt(new StringBuilder(String.valueOf(i)).toString(), i);
        editor.commit();
    }

    public void removeDoNotShow(Context context, int i) {
        Editor editor = getEditor(context);
        editor.remove(new StringBuilder(String.valueOf(i)).toString());
        editor.commit();
    }

    public boolean isDoNotShow(Context context, int i) {
        return getSharedPreference(context).contains(new StringBuilder(String.valueOf(i)).toString());
    }
}
