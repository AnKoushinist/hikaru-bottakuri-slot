package com.jirbo.adcolony;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tapjoy.TapjoyConstants;

class q {
    public static final int a = 30;
    public static String b = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  x          xxxxxxx                          xxxx x                          xxxxx";
    public static String c = "0123456789ABCDEF";
    public static String d = "0123456789abcdef";

    q() {
    }

    static boolean a() {
        return a(null);
    }

    static boolean a(Activity activity) {
        if (activity == null) {
            activity = a.P;
        }
        if (activity == null) {
            l.a.b((Object) "Null Activity");
            return false;
        } else if (a.N) {
            return true;
        } else {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.getType() == 1;
        }
    }

    static boolean b() {
        return b(null);
    }

    static boolean b(Activity activity) {
        if (activity == null) {
            activity = a.P;
        }
        if (activity == null || a.N) {
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        boolean z = type == 0 || type >= 2;
        return z;
    }

    static boolean c() {
        return c(null);
    }

    static boolean c(Activity activity) {
        return a(activity) || b(activity);
    }

    public static String d() {
        if (a()) {
            return TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
        }
        if (b()) {
            return "cell";
        }
        return "offline";
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= '\u0080' || b.charAt(charAt) != ' ') {
                stringBuilder.append('%');
                int i2 = (charAt >> 4) & 15;
                int i3 = charAt & 15;
                if (i2 < 10) {
                    stringBuilder.append((char) (i2 + 48));
                } else {
                    stringBuilder.append((char) ((i2 + 65) - 10));
                }
                if (i3 < 10) {
                    stringBuilder.append((char) (i3 + 48));
                } else {
                    stringBuilder.append((char) ((i3 + 65) - 10));
                }
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }

    public static int a(char c) {
        int indexOf = c.indexOf(c);
        if (indexOf >= 0) {
            return indexOf;
        }
        indexOf = d.indexOf(c);
        return indexOf < 0 ? 0 : indexOf;
    }

    public static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = str.charAt(i);
            if (charAt == '%') {
                char charAt2;
                if (i + 1 < length) {
                    charAt2 = str.charAt(i + 1);
                } else {
                    charAt2 = '0';
                }
                if (i + 2 < length) {
                    charAt = str.charAt(i + 2);
                } else {
                    charAt = '0';
                }
                i += 2;
                stringBuilder.append((char) (a(charAt) | (a(charAt2) << 8)));
                i2 = i;
            } else {
                stringBuilder.append(charAt);
                i2 = i;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }
}
