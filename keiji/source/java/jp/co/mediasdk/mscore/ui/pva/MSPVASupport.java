package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.Intent;
import jp.co.mediasdk.a;
import jp.co.mediasdk.android.IntentUtil;
import jp.co.mediasdk.android.ResourceContextSupport;

public class MSPVASupport {
    public static boolean a(final Activity activity) {
        a.a(new Runnable() {
            public void run() {
                MSPVAParamater.d();
                Intent intent = new Intent(ResourceContextSupport.i(), MSPVAActivity.class);
                intent.setFlags(536870912);
                if (activity != null) {
                    activity.startActivity(intent);
                } else {
                    IntentUtil.a(intent);
                }
            }
        });
        return true;
    }
}
