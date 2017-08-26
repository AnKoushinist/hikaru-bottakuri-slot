package com.tapjoy.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class l extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        forward(context, intent);
    }

    public final int forward(Context context, Intent intent) {
        try {
            Bundle bundle = context.getPackageManager().getReceiverInfo(new ComponentName(context, getClass()), 128).metaData;
            if (bundle == null) {
                return 0;
            }
            int i = 0;
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                if (string != null) {
                    try {
                        Object newInstance = Class.forName(string).newInstance();
                        if (newInstance instanceof BroadcastReceiver) {
                            BroadcastReceiver broadcastReceiver = (BroadcastReceiver) newInstance;
                            try {
                                Intent intent2 = new Intent(intent);
                                intent2.setComponent(new ComponentName(context, string));
                                broadcastReceiver.onReceive(context, intent2);
                                i++;
                            } catch (Throwable th) {
                            }
                        }
                    } catch (ClassNotFoundException e) {
                    } catch (IllegalAccessException e2) {
                    } catch (InstantiationException e3) {
                    }
                } else {
                    try {
                        new Object[1][0] = str;
                    } catch (NameNotFoundException e4) {
                        return i;
                    }
                }
            }
            return i;
        } catch (NameNotFoundException e5) {
            return 0;
        }
    }
}
