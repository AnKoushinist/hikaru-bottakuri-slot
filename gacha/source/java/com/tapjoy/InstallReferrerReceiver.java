package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import com.d.a.a.c;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.l;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InstallReferrerReceiver extends l {
    public void onReceive(Context context, Intent intent) {
        String a = fm.a(context, intent);
        int forward = forward(context, intent);
        if (intent.getBooleanExtra("fiverocks:verify", false) && isOrderedBroadcast()) {
            setResultCode(forward + 1);
            if (a != null) {
                try {
                    setResultData("http://play.google.com/store/apps/details?id=" + context.getPackageName() + "&referrer=" + URLEncoder.encode(a, c.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
    }
}
