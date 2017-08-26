package com.vungle.publisher;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public final class te extends RelativeLayout {
    private ImageView a;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.visible.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.invisible.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.gone.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public Context a;
        @Inject
        public nb b;
        @Inject
        public agw c;

        @Inject
        a() {
        }
    }

    /* compiled from: vungle */
    public enum b {
        visible,
        invisible,
        gone
    }

    private te(Context context) {
        super(context);
    }

    public final void setCloseVisibility(b bVar) {
        switch (AnonymousClass1.a[bVar.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                this.a.setVisibility(0);
                setVisibility(0);
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                this.a.setVisibility(4);
                setVisibility(0);
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setVisibility(8);
                return;
            default:
                return;
        }
    }
}
