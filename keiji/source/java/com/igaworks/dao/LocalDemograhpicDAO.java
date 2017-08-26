package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalDemograhpicDAO {
    private static LocalDemograhpicDAO singleton;
    private SharedPreferences LocalDemograhpicSP;
    private Editor editor = this.LocalDemograhpicSP.edit();

    private LocalDemograhpicDAO(Context context) {
        this.LocalDemograhpicSP = context.getSharedPreferences("LocalDemograhpicDAO", 0);
    }

    public static LocalDemograhpicDAO getInstance(Context context) {
        if (singleton == null) {
            synchronized (LocalDemograhpicDAO.class) {
                if (singleton == null) {
                    singleton = new LocalDemograhpicDAO(context);
                }
            }
        }
        return singleton;
    }

    public void save_demographic_local(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.equals(BuildConfig.FLAVOR)) {
                    this.editor.putString(str, str2);
                    this.editor.apply();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject convertDemographicInfoFromSP2JSONObject(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : context.getSharedPreferences("LocalDemograhpicDAO", 0).getAll().entrySet()) {
                jSONObject.put((String) entry.getKey(), (String) entry.getValue());
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
