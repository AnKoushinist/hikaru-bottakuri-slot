package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.cj.b;
import com.vungle.publisher.cj.c;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class afc implements aii<ade, ahp<dn<cj>>> {
    @Inject
    b a;
    @Inject
    dx.b b;
    @Inject
    a c;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.failed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.preparing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.viewed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[c.invalid.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[c.deleting.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    @Inject
    afc() {
    }

    private ahp<dn<cj>> a(ade com_vungle_publisher_ade) {
        Object obj;
        Throwable e;
        dw a = this.a.a(com_vungle_publisher_ade.e);
        String str = com_vungle_publisher_ade.f;
        dn a2 = a.a(str);
        if (a2 != null) {
            try {
                a.b(a2.h_(), com_vungle_publisher_ade);
            } catch (Throwable e2) {
                Logger.w(Logger.PREPARE_TAG, "error updating ad " + str, e2);
            }
            c g = a2.g();
            str = "received " + a2.z() + " in status " + g;
            dn dnVar;
            switch (AnonymousClass1.a[g.ordinal()]) {
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    Logger.i(Logger.PREPARE_TAG, str);
                    dnVar = a2;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    Logger.w(Logger.PREPARE_TAG, str + " - retrying");
                    throw new RuntimeException("received invalid ad in status: " + g);
                default:
                    Logger.i(Logger.PREPARE_TAG, str + " - ignoring");
                    dnVar = a2;
                    break;
            }
        }
        try {
            new dx.b.a(this.b) {
                final /* synthetic */ b a;

                {
                    this.a = r2;
                }

                final int a(dx<?, ?, ?> dxVar) {
                    return dxVar.b();
                }
            }.a();
            obj = (dn) a.a(com_vungle_publisher_ade);
            try {
                Logger.i(Logger.PREPARE_TAG, "received new " + obj.z());
                obj.h_().v();
            } catch (Exception e3) {
                e = e3;
                this.c.a(Logger.PREPARE_TAG, "error preparing ad " + str + ", retrying", e);
                ahx.a(e);
                return akc.a(obj);
            }
        } catch (Throwable e22) {
            Throwable th = e22;
            obj = a2;
            e = th;
            this.c.a(Logger.PREPARE_TAG, "error preparing ad " + str + ", retrying", e);
            ahx.a(e);
            return akc.a(obj);
        }
        return akc.a(obj);
    }
}
