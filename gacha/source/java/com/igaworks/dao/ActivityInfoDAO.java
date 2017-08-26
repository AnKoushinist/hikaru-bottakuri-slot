package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.igaworks.core.IgawLogger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityInfoDAO {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        public void run() {
            Editor edit = ActivityInfoDAO.getActivityForReferralSP(this.val$context).edit();
            edit.clear();
            edit.commit();
        }
    }

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ String val$key;
        private final /* synthetic */ String val$val;

        AnonymousClass2(Context context, String str, String str2) {
            this.val$context = context;
            this.val$key = str;
            this.val$val = str2;
        }

        public void run() {
            Editor edit = ActivityInfoDAO.getActivityForReferralSP(this.val$context).edit();
            edit.putString(this.val$key, this.val$val);
            edit.commit();
        }
    }

    class AnonymousClass4 implements Runnable {
        private final /* synthetic */ ArrayList val$activity_info_list;
        private final /* synthetic */ Context val$context;

        AnonymousClass4(ArrayList arrayList, Context context) {
            this.val$activity_info_list = arrayList;
            this.val$context = context;
        }

        public void run() {
            try {
                if (this.val$activity_info_list != null) {
                    Editor edit = ActivityInfoDAO.getActivityForReferralSP(this.val$context).edit();
                    for (int i = 0; i < this.val$activity_info_list.size(); i++) {
                        String str = (String) this.val$activity_info_list.get(i);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            edit.putString(new StringBuilder(String.valueOf(Calendar.getInstance().getTime().getTime())).append("_").append(jSONObject.getString("group")).append("_").append(jSONObject.getString("activity")).toString(), str);
                        } catch (JSONException e) {
                            edit.putString(str, str);
                            IgawLogger.Logging(this.val$context, "IGAW_QA", "error occurred during callbackReferralTrackingADBrix : " + e.toString(), 0);
                        }
                    }
                    edit.commit();
                }
            } catch (Exception e2) {
                IgawLogger.Logging(this.val$context, "IGAW_QA", "error occurred during restoreReferralTrackingInfo : " + e2.toString() + " / " + e2.getMessage(), 0, false);
            }
        }
    }

    public static SharedPreferences getActivityForReferralSP(Context context) {
        return context.getSharedPreferences("referralActivityForTracking", 0);
    }

    public static ArrayList<String> getActivityInfoForReferral(Context context) {
        try {
            ArrayList<String> arrayList = new ArrayList();
            Collection<String> values = getActivityForReferralSP(context).getAll().values();
            if (values.size() != 0) {
                for (String str : values) {
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > start session >> send activity for referral: " + str, 3);
                    arrayList.add(str);
                }
            }
            new Thread(new AnonymousClass1(context)).start();
            return arrayList;
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "error occurred during restoreReferralTrackingInfo : " + e.toString() + " / " + e.getMessage(), 0, false);
            return new ArrayList();
        }
    }

    public static void addActivityInfoForReferral(Context context, String str, String str2) {
        new Thread(new AnonymousClass2(context, str, str2)).start();
    }

    public static void restoreReferralTrackingInfo(Context context, ArrayList<String> arrayList) {
        new Thread(new AnonymousClass4(arrayList, context)).start();
    }
}
