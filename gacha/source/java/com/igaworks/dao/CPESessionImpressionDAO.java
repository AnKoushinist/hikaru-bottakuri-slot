package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import twitter4j.TwitterResponse;

public class CPESessionImpressionDAO extends AbstractCPEImpressionDAO {
    protected static Editor adspaceEditor;
    protected static SharedPreferences adspaceSP;
    protected static Editor engagementEditor;
    protected static SharedPreferences engagementSP;
    protected static Editor promotionEditor;
    protected static SharedPreferences promotionSP;
    protected static AbstractCPEImpressionDAO singleton;

    private CPESessionImpressionDAO() {
    }

    public static AbstractCPEImpressionDAO getInstance() {
        if (singleton == null) {
            singleton = new CPESessionImpressionDAO();
        }
        return singleton;
    }

    private SharedPreferences getSharedPreference(Context context, int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                if (engagementSP == null) {
                    engagementSP = context.getSharedPreferences("session_cpe_counter", 0);
                }
                return engagementSP;
            case TwitterResponse.READ /*1*/:
                if (promotionSP == null) {
                    promotionSP = context.getSharedPreferences("session_promotion_counter", 0);
                }
                return promotionSP;
            case TwitterResponse.READ_WRITE /*2*/:
                if (adspaceSP == null) {
                    adspaceSP = context.getSharedPreferences("session_ad_space_counter", 0);
                }
                return adspaceSP;
            default:
                return null;
        }
    }

    private Editor getEditor(Context context, int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                if (engagementEditor == null) {
                    engagementEditor = getSharedPreference(context, i).edit();
                }
                return engagementEditor;
            case TwitterResponse.READ /*1*/:
                if (promotionEditor == null) {
                    promotionEditor = getSharedPreference(context, i).edit();
                }
                return promotionEditor;
            case TwitterResponse.READ_WRITE /*2*/:
                if (adspaceEditor == null) {
                    adspaceEditor = getSharedPreference(context, i).edit();
                }
                return adspaceEditor;
            default:
                return null;
        }
    }

    public void increaseImpressionData(Context context, int i, String str, String str2) {
        final Context context2 = context;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        new Thread(new Runnable() {
            public void run() {
                SharedPreferences access$0 = CPESessionImpressionDAO.this.getSharedPreference(context2, i2);
                Editor access$1 = CPESessionImpressionDAO.this.getEditor(context2, i2);
                access$1.putString(str3 + "::--::" + str4, new StringBuilder(String.valueOf(Integer.parseInt(access$0.getString(str3 + "::--::" + str4, "0")) + 1)).toString());
                access$1.commit();
            }
        }).start();
    }

    public String getImpressionData(Context context, int i, String str, String str2) {
        return getSharedPreference(context, i).getString(new StringBuilder(String.valueOf(str)).append("::--::").append(str2).toString(), "0");
    }

    public void clearImpressionData(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                Editor access$1 = CPESessionImpressionDAO.this.getEditor(context, 0);
                access$1.clear();
                access$1.commit();
                access$1 = CPESessionImpressionDAO.this.getEditor(context, 1);
                access$1.clear();
                access$1.commit();
            }
        }).start();
    }

    public void setImpressionData(Context context, int i, String str, String str2, String str3) {
    }
}
