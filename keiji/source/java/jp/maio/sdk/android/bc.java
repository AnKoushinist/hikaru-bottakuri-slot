package jp.maio.sdk.android;

import android.util.Log;

class bc {
    static void a(String str, String str2) {
        Log.d("maio SDK ADS ERROR", "[ErrorLog]" + str + " Message:" + str2);
    }

    static void a(String str, String str2, String str3, Throwable th) {
        if (!ay.a()) {
        }
    }

    static void a(String str, String str2, Throwable th) {
        if (ay.a() && th != null) {
            th.printStackTrace();
        }
    }
}
