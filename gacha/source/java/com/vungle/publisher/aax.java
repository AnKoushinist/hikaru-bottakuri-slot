package com.vungle.publisher;

import android.net.Uri;
import android.net.Uri.Builder;
import com.tapjoy.TapjoyConstants;
import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class aax extends yf {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<aax> {
        @Inject
        pn g;
        @Inject
        com.vungle.publisher.adn.a h;

        protected final /* synthetic */ vs b() {
            return new aax();
        }

        @Inject
        a() {
        }

        public final aax a(long j) {
            aax com_vungle_publisher_aax = (aax) super.a();
            Builder appendQueryParameter = Uri.parse(this.d + "unfilled").buildUpon().appendQueryParameter(TapjoyConstants.TJC_APP_ID, this.c.b());
            String a = this.g.a();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("ifa", a);
            }
            a = this.g.c();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("isu", a);
            }
            a = this.g.j();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("mac", a);
            }
            appendQueryParameter.appendQueryParameter("ut", String.valueOf(j));
            com_vungle_publisher_aax.b = appendQueryParameter.toString();
            return com_vungle_publisher_aax;
        }
    }

    protected aax() {
    }

    protected final c a() {
        return c.unfilledAd;
    }

    protected final b b() {
        return b.POST;
    }
}
