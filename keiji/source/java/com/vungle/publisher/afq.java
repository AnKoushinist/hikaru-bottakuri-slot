package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import javax.inject.Inject;

/* compiled from: vungle */
public final class afq {

    /* compiled from: vungle */
    public static class a implements aif<adf> {
        @Inject
        py a;
        @Inject
        pp b;
        @Inject
        ql c;
        @Inject
        bt d;

        public final /* synthetic */ void a(Object obj) {
            adf com_vungle_publisher_adf = (adf) obj;
            this.a.a(0);
            if (com_vungle_publisher_adf.k) {
                pp ppVar = this.b;
                Integer num = com_vungle_publisher_adf.g;
                if (num == null) {
                    Logger.v(Logger.AD_TAG, "ignoring set null min ad delay seconds");
                    return;
                }
                int intValue = num.intValue();
                Logger.d(Logger.AD_TAG, "setting min ad delay seconds: " + intValue);
                ppVar.c.edit().putInt("VgAdDelayDuration", intValue).apply();
                return;
            }
            long longValue = com_vungle_publisher_adf.b().longValue();
            this.a.a(longValue);
            this.d.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    this.a.c.a(new qo());
                }
            }, b.sleepWakeup, longValue);
        }

        @Inject
        a() {
        }
    }
}
