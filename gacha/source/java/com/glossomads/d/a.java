package com.glossomads.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.b.f;
import com.glossomads.View.t;
import com.glossomads.s;

public class a extends BroadcastReceiver {
    private a a;

    public interface a {
        void a(t tVar, b bVar);
    }

    public enum b {
        AD_START,
        AD_PAUSE,
        AD_RESUME,
        AD_FINISH,
        AD_CLOSE,
        AD_ERROR
    }

    public void onReceive(Context context, Intent intent) {
        t tVar = (t) intent.getSerializableExtra("sugarHolder");
        b bVar = (b) intent.getSerializableExtra("adViewReceiverType");
        if (this.a != null && tVar != null) {
            this.a.a(tVar, bVar);
        }
    }

    public static void a(t tVar, b bVar) {
        Intent intent = new Intent("adViewReceiver");
        intent.putExtra("sugarHolder", tVar);
        intent.putExtra("adViewReceiverType", bVar);
        f.a(s.f()).a(intent);
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
