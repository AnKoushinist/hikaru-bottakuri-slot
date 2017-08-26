package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJConnectListener;
import java.util.Hashtable;

final class dw extends dv {
    private final fa b = new fa(this) {
        final /* synthetic */ dw a;

        {
            this.a = r1;
        }

        protected final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
            return super.a(context, str, hashtable, tJConnectListener);
        }
    };

    dw() {
    }

    public final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        return this.b.b(context, str, hashtable, tJConnectListener);
    }
}
