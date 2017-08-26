package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.ValueCallback;
import com.tapjoy.TapjoyConstants;

class p implements ValueCallback {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public void a(String str) {
        if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase(TapjoyConstants.TJC_FALSE)) {
            if (this.a.d.b()) {
                Log.d("MoatJavaScriptBridge", "Received value is:" + (str == null ? "null" : "(String)" + str));
            }
            if (this.a.e == -1 || this.a.e == 50) {
                this.a.g();
            }
            this.a.e = this.a.e + 1;
        } else if (str.equalsIgnoreCase(TapjoyConstants.TJC_TRUE)) {
            this.a.e = -1;
            this.a.e();
        } else if (this.a.d.b()) {
            Log.d("MoatJavaScriptBridge", "Received unusual value from Javascript:" + str);
        }
    }

    public /* synthetic */ void onReceiveValue(Object obj) {
        a((String) obj);
    }
}
