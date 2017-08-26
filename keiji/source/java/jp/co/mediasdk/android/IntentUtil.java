package jp.co.mediasdk.android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class IntentUtil extends IntentUtilRequestSupport {

    public interface IntentUtilExtrasFileListener {
    }

    public static boolean a(Intent intent) {
        if (ResourceContextSupport.j()) {
            Context i = ResourceContextSupport.i();
            LoggerBase.b(IntentUtil.class, "startActivity", "context is '%s'.", i);
            if (i instanceof Application) {
                intent.setFlags(268435456);
            }
            i.startActivity(intent);
            return true;
        }
        LoggerBase.a(IntentUtil.class, "startActivity", "failed to get context.", new Object[0]);
        return false;
    }
}
