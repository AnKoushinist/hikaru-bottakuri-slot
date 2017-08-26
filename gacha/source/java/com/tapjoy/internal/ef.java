package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJConnectListener;
import java.util.Hashtable;

final class ef extends ee {
    private final es b = new es(this) {
        final /* synthetic */ ef a;

        {
            this.a = r1;
        }

        protected final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
            return super.a(context, str, hashtable, tJConnectListener);
        }
    };

    ef() {
    }

    public final boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        return this.b.b(context, str, hashtable, tJConnectListener);
    }
}
