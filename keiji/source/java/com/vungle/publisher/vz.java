package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.gm.a;
import java.net.HttpURLConnection;
import javax.inject.Inject;
import twitter4j.HttpResponseCode;

/* compiled from: vungle */
public abstract class vz {
    @Inject
    public a c;
    @Inject
    protected wn d;

    protected final void b(we weVar, vy vyVar) {
        try {
            if (a(vyVar.b)) {
                try {
                    a(weVar, vyVar);
                    return;
                } catch (Throwable e) {
                    Logger.d(Logger.NETWORK_TAG, e);
                    vyVar.b = 603;
                } catch (Throwable e2) {
                    this.c.b(Logger.NETWORK_TAG, "IOException while handling response: " + vyVar, e2);
                    vyVar.b = 600;
                } catch (Throwable e22) {
                    this.c.b(Logger.NETWORK_TAG, "JSONException while handling response: " + vyVar, e22);
                    vyVar.b = 604;
                }
            }
            c(weVar, vyVar);
        } catch (Throwable e222) {
            this.c.a(Logger.NETWORK_TAG, "error while handling response: " + vyVar, e222);
            d(weVar, vyVar);
        }
    }

    protected void c(we weVar, vy vyVar) {
        d(weVar, vyVar);
    }

    public void a(we weVar, vy vyVar) {
    }

    public void d(we weVar, vy vyVar) {
        Logger.w(Logger.NETWORK_TAG, weVar.a + " failed permanently with response code " + vyVar.b);
    }

    protected static boolean a(int i) {
        return i == HttpResponseCode.OK;
    }

    public final String a(HttpURLConnection httpURLConnection) {
        return this.d.a(httpURLConnection);
    }
}
