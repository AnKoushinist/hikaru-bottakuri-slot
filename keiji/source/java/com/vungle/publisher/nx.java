package com.vungle.publisher;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class nx extends oa {
    boolean a;
    lr b;
    ql c;
    private Bitmap d;
    private Bitmap e;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Context a;
        @Inject
        agw b;
        @Inject
        lr c;
        @Inject
        ql d;

        /* compiled from: vungle */
        class AnonymousClass1 implements OnClickListener {
            final /* synthetic */ nx a;
            final /* synthetic */ a b;

            AnonymousClass1(a aVar, nx nxVar) {
                this.b = aVar;
                this.a = nxVar;
            }

            public final void onClick(View view) {
                Logger.d(Logger.AD_TAG, (this.a.a() ? BuildConfig.FLAVOR : "un") + "mute clicked");
                nx nxVar = this.a;
                boolean z = !nxVar.a();
                nxVar.setAndCacheSoundEnabled(z);
                if (z && nxVar.b.b() == 0) {
                    nxVar.setVolume((int) (0.4f * ((float) nxVar.b.a())));
                }
                nxVar.a(z);
            }
        }

        @Inject
        a() {
        }
    }

    private nx(Context context) {
        super(context);
    }

    final boolean a() {
        return this.a && this.b.b() > 0;
    }

    final void setAndCacheSoundEnabled(boolean z) {
        this.a = z;
        setSoundEnabled(z);
    }

    final void setSoundEnabled(boolean z) {
        this.b.a(z);
        b();
    }

    final void b() {
        setImageBitmap(a() ? this.e : this.d);
    }

    final void setVolume(int i) {
        this.b.a.setStreamVolume(3, i, 0);
        b();
    }

    final void a(boolean z) {
        if (z) {
            this.c.a(new ay(false));
        } else {
            this.c.a(new ay(true));
        }
    }
}
