package jp.co.mediasdk;

import android.app.Activity;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import jp.co.mediasdk.android.DateUtil;
import jp.co.mediasdk.android.ResourceBase;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.pref.PreferenceUtilContextSupport;
import jp.co.mediasdk.mscore.event.pva.MSPVASendEvent;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListener;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListenerManager;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVANotifyListener;
import jp.co.mediasdk.mscore.ui.pva.MSPVAParamater;
import jp.co.mediasdk.mscore.ui.pva.MSPVASupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVAVast;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: MediaSDKPVA */
public class d extends b {
    private static boolean a = false;

    public static void a(boolean z) {
        a = z;
    }

    public static boolean a(final Activity activity) {
        MSParameterSupport.a("incentive", BuildConfig.FLAVOR);
        if (MSPVAVast.a().containsKey(TapjoyConstants.TJC_TIMESTAMP)) {
            if (DateUtil.a() - MSPVAVast.a().h(TapjoyConstants.TJC_TIMESTAMP) > 60) {
                MSPVAVast.a(new MSPVANotifyListener() {
                    public void a(String str) {
                        d.b(activity);
                    }
                });
            } else {
                b(activity);
            }
            MSPVAVast.a().remove(TapjoyConstants.TJC_TIMESTAMP);
        } else {
            MSPVAVast.a(new MSPVANotifyListener() {
                public void a(String str) {
                    d.b(activity);
                }
            });
        }
        return true;
    }

    public static boolean b(Activity activity) {
        if (!MSPVAVast.a().a("Ad")) {
            return false;
        }
        if (!MSPVAVast.a().a("MediaFile") || ((String) MSPVAVast.a().get("MediaFile")).isEmpty()) {
            return false;
        }
        a(true);
        if (ResourceBase.g() == null) {
            ResourceBase.a(activity.getApplicationContext(), MSParameterSupport.a("packagename"));
        }
        if (!PreferenceUtilContextSupport.a()) {
            PreferenceUtilContextSupport.a(activity.getApplicationContext());
        }
        return MSPVASupport.a(activity);
    }

    public static void a(MSPVAListener mSPVAListener) {
        MSPVAListenerManager.a(mSPVAListener);
    }

    public static boolean a(String str, Map<String, ?> map) {
        return MSPVASendEvent.a(str, map);
    }

    public static void b() {
        if (!a) {
            Map hashMap = new HashMap();
            hashMap.put(MoatAdEvent.EVENT_TYPE, String.VIDEO_START);
            a("session", hashMap);
        }
        a = false;
    }

    public static void c() {
        if (!a) {
            Map hashMap = new HashMap();
            hashMap.put(MoatAdEvent.EVENT_TYPE, "end");
            a("session", hashMap);
        }
    }

    public static void d() {
        MSPVAParamater.d();
        MSPVAVast.a(new MSPVANotifyListener() {
            public void a(String str) {
                if (MSPVAVast.a().a("Ad")) {
                    MSPVAVast.a().b(TapjoyConstants.TJC_TIMESTAMP, DateUtil.a());
                }
                MSPVAListenerManager.a(str);
            }
        });
    }

    public static String e() {
        String a = Util.a();
        MSParameterSupport.a("media_user_id", a);
        return a;
    }
}
