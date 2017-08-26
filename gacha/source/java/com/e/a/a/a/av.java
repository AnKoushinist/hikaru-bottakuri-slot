package com.e.a.a.a;

import android.os.Handler;
import android.os.Looper;
import com.e.a.a.a.a.b.a;

class av implements Runnable {
    final /* synthetic */ aq a;

    av(aq aqVar) {
        this.a = aqVar;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new aw(this));
        } catch (Exception e) {
            a.a(e);
        }
    }
}
