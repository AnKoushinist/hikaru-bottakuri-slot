package com.adcolony.sdk;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tapjoy.TapjoyConstants;

class s {
    final int a = 30;
    String b = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  x          xxxxxxx                          xxxx x                          xxxxx";
    String c = "0123456789ABCDEF";
    String d = "0123456789abcdef";

    s() {
    }

    boolean a() {
        if (!n.d()) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) n.c().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.getType() == 1;
        } catch (SecurityException e) {
            bd.g.b("SecurityException - please ensure you added the ACCESS_NETWORK_STATE permission: " + e.toString());
            return false;
        }
    }

    boolean b() {
        if (!n.d()) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) n.c().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            int type = activeNetworkInfo.getType();
            boolean z = type == 0 || type >= 2;
            return z;
        } catch (SecurityException e) {
            bd.g.b("SecurityException - please ensure you added the ACCESS_NETWORK_STATE permission: " + e.toString());
            return false;
        }
    }

    String c() {
        if (a()) {
            return TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
        }
        if (b()) {
            return "cell";
        }
        return "none";
    }
}
