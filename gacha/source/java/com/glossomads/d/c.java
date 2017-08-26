package com.glossomads.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

public class c extends BroadcastReceiver {
    public a a;
    private boolean b;

    public interface a {
        void a(b bVar);
    }

    public enum b {
        NONE,
        WIFI,
        MOBILE,
        ERROR
    }

    public void onReceive(Context context, Intent intent) {
        b bVar;
        boolean z = true;
        b bVar2 = b.NONE;
        try {
            NetworkInfo c = d.c(context);
            if (c != null && c.getType() == 1 && c.isConnected()) {
                try {
                    bVar = b.WIFI;
                } catch (Exception e) {
                    bVar = b.ERROR;
                    if (this.b != z) {
                        this.b = z;
                        if (this.a != null) {
                            this.a.a(bVar);
                        }
                    }
                }
                if (this.b != z) {
                    this.b = z;
                    if (this.a != null) {
                        this.a.a(bVar);
                    }
                }
            }
            if (c != null) {
                if (c.isConnected()) {
                    bVar = b.MOBILE;
                    if (this.b != z) {
                        this.b = z;
                        if (this.a != null) {
                            this.a.a(bVar);
                        }
                    }
                }
            }
            z = false;
            bVar = bVar2;
            if (this.b != z) {
                this.b = z;
                if (this.a != null) {
                    this.a.a(bVar);
                }
            }
        } catch (Exception e2) {
            z = false;
            bVar = b.ERROR;
            if (this.b != z) {
                this.b = z;
                if (this.a != null) {
                    this.a.a(bVar);
                }
            }
        }
    }

    public static boolean a(Context context) {
        try {
            NetworkInfo c = d.c(context);
            if (c != null && c.isConnected() && c.getType() == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo c = d.c(context);
            if (c != null && c.isConnected() && c.getType() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
