package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.fo;

public class GCMReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean a = fo.b(context).a(intent);
        if (isOrderedBroadcast()) {
            setResult(-1, null, null);
            if (a) {
                abortBroadcast();
            }
        }
    }
}
