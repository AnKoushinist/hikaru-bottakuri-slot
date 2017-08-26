package com.vungle.publisher;

import android.net.Uri;
import android.net.Uri.Builder;
import com.tapjoy.TapjoyConstants;
import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class aao extends yf {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<aao> {
        @Inject
        pn g;
        @Inject
        com.vungle.publisher.adn.a h;

        public final /* synthetic */ yf a() {
            return d();
        }

        protected final /* synthetic */ vs b() {
            return new aao();
        }

        public final /* synthetic */ vs c() {
            return d();
        }

        @Inject
        a() {
        }

        public final aao d() {
            aao com_vungle_publisher_aao = (aao) super.a();
            Builder appendQueryParameter = Uri.parse(this.d + "new").buildUpon().appendQueryParameter(TapjoyConstants.TJC_APP_ID, this.c.b());
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
            com_vungle_publisher_aao.b = appendQueryParameter.toString();
            return com_vungle_publisher_aao;
        }
    }

    protected aao() {
    }

    protected final c a() {
        return c.trackInstall;
    }

    protected final b b() {
        return b.POST;
    }
}
