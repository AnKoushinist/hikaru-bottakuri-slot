package com.igaworks.commerce.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DemographicDAO {
    public static String getDemographic(Context context, String str) {
        String str2 = null;
        if (context == null) {
            try {
                Log.e("IGAW_QA", "save demo error >> context is null.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences("persistantDemoForTracking", 0);
            if (sharedPreferences.contains(str)) {
                str2 = sharedPreferences.getString(str, null);
            }
        }
        return str2;
    }
}
