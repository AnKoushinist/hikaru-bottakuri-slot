package com.igaworks.net;

import android.content.Context;
import com.igaworks.core.IgawLogger;

public class HttpManager {
    public static String cpn_domain = "http://campaign.ad-brix.com/v1/";
    public static String cvr_domain = "http://cvr.ad-brix.com/v1/";
    public static String dl_domain = "http://api.ab4c.ad-brix.com/v1/";
    public static String ref_domain = "http://ref.ad-brix.com/v1/";
    public static String trk_domain = "http://tracking.ad-brix.com/v1/";
    public String DEEP_LINK_CONVERSION_FOR_ADBrix = (dl_domain + "tracking/conversions");
    public String DEFERRERLINK_REQUEST_URL_FOR_ADBrix = "http://iga.revitour.com/Deferrer";
    public String DEMOGRAPHIC_REQUEST_URL_FOR_ADBrix = (trk_domain + "tracking/SetUserDemographic");
    public String REFERRER_REQUEST_URL_FOR_ADBrix = (cvr_domain + "conversion/GetReferral");
    public String TRACKING_REQUEST_URL_FOR_ADBrix = (trk_domain + "tracking");

    public static boolean isLive(Context context) {
        try {
            if (((String) context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("igaw_release_mode")).equals("stage")) {
                IgawLogger.Logging(context, "IGAW_QA", "Release Mode : stage mode", 3, true);
                return false;
            }
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "Release Mode : live mode", 3, true);
        }
        return true;
    }
}
