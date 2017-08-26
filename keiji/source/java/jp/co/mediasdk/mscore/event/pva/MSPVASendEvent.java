package jp.co.mediasdk.mscore.event.pva;

import java.util.Map;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.mscore.event.util.EventParameterCoordinator;
import org.cocos2dx.lib.BuildConfig;

public class MSPVASendEvent extends MSPVASendEventInThreadSupport {
    public static boolean a(String str, Map<String, ?> map) {
        HashMapEX hashMapEX = new HashMapEX();
        hashMapEX.c("event_id", str);
        hashMapEX.c("params", BuildConfig.FLAVOR + EventParameterCoordinator.a((Map) map));
        return MSPVASendEventInThreadSupport.a(hashMapEX);
    }
}
