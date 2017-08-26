package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.igaworks.core.AdvertisingIdClient.ADIDCallbackListener;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import org.cocos2dx.lib.BuildConfig;

public class CoreIDDAO {
    private static boolean isIntializing = false;
    private static CoreIDDAO mGoogleAdIdDAO;
    private String IMEI = BuildConfig.FLAVOR;
    private SharedPreferences coreIdSP;
    private Editor editor_coreIdSP;
    private String googleAdId = BuildConfig.FLAVOR;

    private CoreIDDAO() {
    }

    public static CoreIDDAO getInstance() {
        if (mGoogleAdIdDAO == null) {
            mGoogleAdIdDAO = new CoreIDDAO();
        }
        return mGoogleAdIdDAO;
    }

    public void initialize(final Context context) {
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (CoreIDDAO.isIntializing) {
                            IgawLogger.Logging(context, "IGAW_QA", "CoreIDDAO is called already.", 3, true);
                            return;
                        }
                        CoreIDDAO.isIntializing = true;
                        IgawLogger.Logging(context, "IGAW_QA", "Initialzing CoreIDDAO", 3, true);
                        DeviceIDManger instance = DeviceIDManger.getInstance(context);
                        Context context = context;
                        final Context context2 = context;
                        instance.getAndroidADID(context, new ADIDCallbackListener() {
                            public void onResult(AdInfo adInfo) {
                                if (adInfo != null) {
                                    CoreIDDAO.this.setGoogleAdId(adInfo.getId());
                                    CoreIDDAO.this.setGoogleAdId2SP(context2, adInfo.getId());
                                } else {
                                    IgawLogger.Logging(context2, "IGAW_QA", "CoreIDDAO > Fail to get google advertising ID >> adidInfo is Null ", 3, true);
                                }
                                CoreIDDAO.isIntializing = false;
                            }
                        });
                    } catch (Exception e) {
                        CoreIDDAO.isIntializing = false;
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getIMEIFromSP(Context context) {
        return getSharedPreferences(context).getString("Igaw_puid", BuildConfig.FLAVOR);
    }

    private String getGoogleAdIdFromSP(Context context) {
        return getSharedPreferences(context).getString("Igaw_google_advertising_id", BuildConfig.FLAVOR);
    }

    private void setGoogleAdId2SP(Context context, String str) {
        getEditor(context).putString("Igaw_google_advertising_id", str);
        getEditor(context).commit();
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (this.coreIdSP == null) {
            this.coreIdSP = context.getSharedPreferences("IgawCoreId", 0);
        }
        return this.coreIdSP;
    }

    private Editor getEditor(Context context) {
        if (this.editor_coreIdSP == null) {
            this.editor_coreIdSP = getSharedPreferences(context).edit();
        }
        return this.editor_coreIdSP;
    }

    public String getIMEI(Context context) {
        if (this.IMEI.equals(BuildConfig.FLAVOR)) {
            this.IMEI = getIMEIFromSP(context);
        }
        return this.IMEI;
    }

    public String getGoogleAdId(Context context) {
        if (this.googleAdId.equals(BuildConfig.FLAVOR)) {
            this.googleAdId = getGoogleAdIdFromSP(context);
        }
        if (this.googleAdId.equals(BuildConfig.FLAVOR)) {
            initialize(context);
        }
        return this.googleAdId;
    }

    public void setGoogleAdId(String str) {
        this.googleAdId = str;
    }
}
