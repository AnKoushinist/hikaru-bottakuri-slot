package com.vungle.publisher;

import android.database.Cursor;
import com.tapjoy.TapjoyConstants;

/* compiled from: vungle */
public abstract class jo<P extends jm<?, P, ?>> extends cr<P> {

    /* compiled from: vungle */
    public static abstract class b<E extends jo<P>, P extends jm<?, P, E>> extends com.vungle.publisher.cr.a<P, E> {
        protected b() {
        }

        protected final ji a(Cursor cursor) {
            return (ji) cb.a(cursor, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, a.class);
        }
    }

    /* compiled from: vungle */
    public enum a implements ji {
        back,
        close,
        custom,
        download,
        cta,
        muted,
        replay,
        unmuted,
        videoerror,
        videoreset,
        volume,
        volumedown,
        volumeup;
        
        private final String n;

        private a(byte b) {
            this.n = null;
        }

        public final String toString() {
            return this.n == null ? name() : this.n;
        }

        public final boolean a() {
            return false;
        }
    }
}
