package com.adcolony.sdk;

import android.os.Bundle;
import java.util.HashMap;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import twitter4j.TwitterResponse;

class t {
    private static int a;
    private static HashMap<String, Integer> b = new HashMap();
    private static HashMap<String, Integer> c = new HashMap();

    static boolean a(int i, Bundle bundle) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                if (currentTimeMillis - a < 5) {
                    return true;
                }
                a = currentTimeMillis;
                return false;
            case TwitterResponse.READ /*1*/:
                if (bundle == null) {
                    return false;
                }
                String string = bundle.getString(GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME);
                if (b.get(string) == null) {
                    b.put(string, Integer.valueOf(currentTimeMillis));
                }
                if (c.get(string) == null) {
                    c.put(string, Integer.valueOf(0));
                }
                if (currentTimeMillis - ((Integer) b.get(string)).intValue() > 1) {
                    c.put(string, Integer.valueOf(1));
                    b.put(string, Integer.valueOf(currentTimeMillis));
                    return false;
                }
                int intValue = ((Integer) c.get(string)).intValue() + 1;
                c.put(string, Integer.valueOf(intValue));
                if (intValue > 3) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
