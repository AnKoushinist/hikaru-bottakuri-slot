package jp.co.mediasdk.mscore.ui.hybrid;

import android.app.Activity;
import android.content.Intent;
import com.tapjoy.TJAdUnitConstants.String;
import jp.co.mediasdk.android.ResourceContextSupport;
import jp.co.mediasdk.mscore.ui.MSWebViewActivity;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSWebSupport {

    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;

        public void run() {
            MSWebParameterSupport.b();
            String a = MSParameterSupport.a(MSWebParameterSupport.a);
            String a2 = MSParameterSupport.a(MSWebParameterSupport.b);
            a = String.format("https://spr-adv.jp/spg/spnew/%s/%s/video.php?%s", new Object[]{a, a2, MSWebParameterSupport.d()});
            Intent intent = new Intent(ResourceContextSupport.i(), MSWebViewActivity.class);
            intent.putExtra(String.URL, a);
            intent.setFlags(536870912);
            this.a.startActivity(intent);
        }
    }
}
