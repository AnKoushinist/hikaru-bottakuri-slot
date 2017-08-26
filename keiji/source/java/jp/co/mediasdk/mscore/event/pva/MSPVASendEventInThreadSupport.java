package jp.co.mediasdk.mscore.event.pva;

import java.util.HashMap;
import java.util.Map.Entry;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.a;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.NetUtil;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVAParamater;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class MSPVASendEventInThreadSupport {
    protected static String a() {
        String str = "https://tracker.gratefulvideo.jp/user";
        if (StringUtilEqualsSupport.a("1", MSParameterSupport.a("Debug"))) {
            return "https://tracker-pre.gratefulvideo.jp/user";
        }
        return str;
    }

    protected static boolean a(final HashMapEX hashMapEX) {
        a.a(new Runnable() {
            public void run() {
                MSPVASendEventInThreadSupport.b(hashMapEX);
            }
        });
        return true;
    }

    protected static boolean b(HashMapEX hashMapEX) {
        String a = MSParameterSupport.a("media_user_id");
        if (a == null || StringUtilEqualsSupport.a(BuildConfig.FLAVOR, a)) {
            return false;
        }
        a = MSParameterSupport.a(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME);
        if (a == null || StringUtilEqualsSupport.a(BuildConfig.FLAVOR, a)) {
            return false;
        }
        HandlerManager.a();
        HashMapEX hashMapEX2 = new HashMapEX();
        HashMap b = MSPVAParamater.b();
        if (b != null) {
            for (Entry entry : b.entrySet()) {
                if (StringUtilEqualsSupport.a("media_user_attributes", ((String) entry.getKey()).toString())) {
                    hashMapEX2.c(JSONObject.quote((String) entry.getKey()), entry.getValue().toString());
                } else if (entry.getValue() instanceof String) {
                    hashMapEX2.c(JSONObject.quote((String) entry.getKey()), JSONObject.quote(entry.getValue().toString()));
                } else {
                    hashMapEX2.c(JSONObject.quote((String) entry.getKey()), entry.getValue().toString());
                }
            }
        }
        if (hashMapEX != null) {
            for (Entry entry2 : hashMapEX.entrySet()) {
                if (StringUtilEqualsSupport.a((String) entry2.getKey(), "event_id")) {
                    hashMapEX2.c(JSONObject.quote((String) entry2.getKey()), JSONObject.quote(((String) entry2.getValue()).toString()));
                } else {
                    hashMapEX2.c(JSONObject.quote((String) entry2.getKey()), ((String) entry2.getValue()).toString());
                }
            }
        }
        NetUtil netUtil = new NetUtil();
        netUtil.a(false);
        netUtil.b(true);
        netUtil.a(hashMapEX2);
        netUtil.b(a(), new NetUtilJSONCallback() {
            public void a(NetUtil netUtil, HashMapEX hashMapEX) {
            }
        });
        return true;
    }
}
