package com.vungle.publisher;

import android.os.SystemClock;
import com.vungle.log.Logger;
import javax.inject.Inject;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: vungle */
public abstract class wv extends vz {
    private int a = 2000;
    private int b = Constants.IP_RETRY_TIME;
    public int e = 10;
    public int f = 5;
    int g;
    @Inject
    protected bt h;

    protected final void c(we weVar, vy vyVar) {
        xk xkVar = weVar.c;
        int i = xkVar.b;
        int i2 = (this.e <= 0 || i <= this.e) ? 0 : 1;
        if (i2 == 0) {
            long j = xkVar.a;
            if (this.g <= 0 || SystemClock.elapsedRealtime() - j < ((long) this.g)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 == 0) {
                int i3 = vyVar.b;
                if (vz.a(i3) || i3 == 601) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                if (i2 != 0) {
                    i2 = xkVar.c;
                    if (i3 == 408 || i3 == 603) {
                        i3 = 0;
                    } else {
                        i3 = 1;
                    }
                    if (i3 == 0) {
                        i2 = xkVar.c - 1;
                        xkVar.c = i2;
                        if (i2 < 0) {
                            Logger.d(Logger.NETWORK_TAG, "Attempted to decrement softRetryCount < 0");
                            xkVar.c = 0;
                        }
                        i2 = xkVar.c;
                    }
                    if (this.f <= 0 || r0 <= this.f) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        i2 = agv.a(i, this.a, this.b);
                        Logger.d(Logger.NETWORK_TAG, "Retrying " + weVar + " in " + (i2 / GameControllerDelegate.THUMBSTICK_LEFT_X) + " seconds");
                        this.h.a(new xj(weVar), weVar.d, (long) i2);
                        return;
                    }
                }
            }
        }
        super.c(weVar, vyVar);
    }
}
