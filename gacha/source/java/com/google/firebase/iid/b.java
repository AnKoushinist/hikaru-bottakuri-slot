package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.b.g;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class b extends Service {
    private final Object a = new Object();
    private int b;
    MessengerCompat c = new MessengerCompat(new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ b a;

        public void handleMessage(Message message) {
            int a = MessengerCompat.a(message);
            f.a(this.a);
            this.a.getPackageManager();
            if (a == f.c || a == f.b) {
                this.a.b((Intent) message.obj);
                return;
            }
            int i = f.b;
            Log.w("FirebaseInstanceId", "Message from unexpected caller " + a + " mine=" + i + " appid=" + f.c);
        }
    });
    final ExecutorService d = Executors.newSingleThreadExecutor();
    private int e = 0;

    private void d(Intent intent) {
        if (intent != null) {
            g.completeWakefulIntent(intent);
        }
        synchronized (this.a) {
            this.e--;
            if (this.e == 0) {
                b(this.b);
            }
        }
    }

    public boolean a(Intent intent) {
        return false;
    }

    public abstract void b(Intent intent);

    boolean b(int i) {
        return stopSelfResult(i);
    }

    protected abstract Intent c(Intent intent);

    public final IBinder onBind(Intent intent) {
        return (intent == null || !"com.google.firebase.INSTANCE_ID_EVENT".equals(intent.getAction())) ? null : this.c.a();
    }

    public final int onStartCommand(final Intent intent, int i, int i2) {
        synchronized (this.a) {
            this.b = i2;
            this.e++;
        }
        final Intent c = c(intent);
        if (c == null) {
            d(intent);
            return 2;
        } else if (a(c)) {
            d(intent);
            return 2;
        } else {
            this.d.execute(new Runnable(this) {
                final /* synthetic */ b c;

                public void run() {
                    this.c.b(c);
                    this.c.d(intent);
                }
            });
            return 3;
        }
    }
}
