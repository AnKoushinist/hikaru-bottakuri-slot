package jp.co.mediasdk.mscore.ui.adformat;

import android.app.Activity;
import android.content.Intent;
import jp.co.mediasdk.android.IntentUtil;
import jp.co.mediasdk.android.ResourceContextSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSAdSupport extends MSAdParameterSupport {

    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;

        public void run() {
            MSAdParameterSupport.b();
            String format = String.format("https://car.mobadme.jp/spg/spnew/%s/%s/sdk/video?%s", new Object[]{MSParameterSupport.a(MSAdParameterSupport.a), MSParameterSupport.a(MSAdParameterSupport.b), MSAdParameterSupport.e()});
            Intent intent = new Intent(ResourceContextSupport.i(), MSAdFormatActivity.class);
            intent.putExtra("ad_url", format);
            intent.setFlags(536870912);
            if (this.a != null) {
                this.a.startActivity(intent);
            } else {
                IntentUtil.a(intent);
            }
        }
    }
}
