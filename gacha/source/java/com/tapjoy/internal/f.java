package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.TapjoyConstants;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public final class f extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        String a = a(intent);
        if (a != null) {
            a(context, "install_referrer", a);
        }
    }

    public static String a(Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            return intent.getStringExtra(TapjoyConstants.TJC_REFERRER);
        }
        return null;
    }

    private static boolean a(Context context, String str, String str2) {
        Closeable closeable = null;
        try {
            closeable = context.openFileOutput(str, 0);
            bk.a((OutputStream) closeable, str2);
            closeable.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e2) {
            da.a(closeable);
            context.deleteFile("install_referrer");
            return false;
        }
    }
}
