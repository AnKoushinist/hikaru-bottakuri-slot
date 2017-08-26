package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.el.b;
import com.vungle.publisher.vi.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class afl implements aii<gg<?>, ahp<? extends gg<?>>> {
    @Inject
    a a;
    @Inject
    xr b;
    @Inject
    xz c;
    @Inject
    afo d;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[el.a.values().length];

        static {
            try {
                a[el.a.ready.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[el.a.downloaded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[el.a.aware.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[el.a.failed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public final /* synthetic */ Object a(Object obj) {
        obj = (gg) obj;
        b t = obj.t();
        el.a s = obj.s();
        String l = obj.l();
        Logger.d(Logger.PREPARE_TAG, "preparing viewable: " + obj);
        ahp a = akc.a(obj);
        switch (AnonymousClass3.a[s.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return a;
            case TwitterResponse.READ_WRITE /*2*/:
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                Logger.d(Logger.PREPARE_TAG, t + " will begin downloading for ad_id " + l);
                a = a.a(this.a).a(this.c).a(akc.a(obj), this.b);
                break;
            default:
                throw new IllegalStateException("unexpected " + t + " status: " + s);
        }
        return a.a(new aii<gg<?>, ahp<? extends gg<?>>>(this) {
            final /* synthetic */ afl a;

            {
                this.a = r1;
            }

            public final /* synthetic */ Object a(Object obj) {
                obj = (gg) obj;
                if (obj.h()) {
                    return akc.a(obj);
                }
                throw new RuntimeException(obj.t() + " post processing failed for ad_id " + obj.l());
            }
        }).a(new aif<Throwable>(this) {
            final /* synthetic */ afl b;

            public final /* synthetic */ void a(Object obj) {
                Logger.i(Logger.PREPARE_TAG, "viewable prep error, update status to failed for " + obj);
                obj.b(el.a.failed);
            }
        }).c(this.d.a(3, "viewable prep failed"));
    }

    @Inject
    afl() {
    }
}
