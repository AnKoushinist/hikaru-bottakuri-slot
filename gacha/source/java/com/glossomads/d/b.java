package com.glossomads.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class b extends BroadcastReceiver {
    public a a;

    public interface a {
        void a(b bVar);
    }

    public enum b {
        NORMAL,
        SILENT,
        VIBRATE
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.RINGER_MODE_CHANGED")) {
            b bVar;
            if (intent.getIntExtra("android.media.EXTRA_RINGER_MODE", -1) == 1) {
                bVar = b.VIBRATE;
            } else if (intent.getIntExtra("android.media.EXTRA_RINGER_MODE", -1) == 0) {
                bVar = b.SILENT;
            } else {
                bVar = b.NORMAL;
            }
            if (this.a != null) {
                this.a.a(bVar);
            }
        }
    }

    public static boolean a(Context context) {
        if (((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return false;
        }
        return true;
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
