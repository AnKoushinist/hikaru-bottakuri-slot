package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyLog;
import java.util.Observable;
import java.util.Observer;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;

abstract class er {
    volatile a b;

    class a implements TJPlacementListener, Observer {
        final /* synthetic */ er a;
        private final Object b;
        private final eg c;
        private volatile boolean d;
        private TJPlacement e;

        a(er erVar, Object obj) {
            this(erVar, obj, new eg(RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS));
        }

        a(er erVar, Object obj, eg egVar) {
            this.a = erVar;
            this.b = obj;
            this.c = egVar;
        }

        final void a() {
            synchronized (this) {
                if (this.d) {
                } else if (this.c.a()) {
                    a("Timed out");
                } else {
                    if (!TapjoyConnectCore.isConnected()) {
                        eo.a.addObserver(this);
                        if (TapjoyConnectCore.isConnected()) {
                            eo.a.deleteObserver(this);
                        } else {
                            return;
                        }
                    }
                    if (this.e == null) {
                        if (this.a.a()) {
                            this.e = this.a.a(TapjoyConnectCore.getContext(), this, this.b);
                            this.e.requestContent();
                            return;
                        }
                        a("Cannot request");
                    } else if (!this.e.isContentReady()) {
                    } else if (this.a.a((Observer) this)) {
                        this.e.showContent();
                        a(null);
                    }
                }
            }
        }

        private void a(String str) {
            synchronized (this) {
                String a = this.a.a(this.b);
                if (str == null) {
                    TapjoyLog.i("SystemPlacement", "Placement " + a + " is presented now");
                } else {
                    TapjoyLog.i("SystemPlacement", "Cannot show placement " + a + " now (" + str + ")");
                }
                this.d = true;
                this.e = null;
                eo.a.deleteObserver(this);
                eo.e.deleteObserver(this);
                eo.c.deleteObserver(this);
            }
            er erVar = this.a;
            synchronized (erVar) {
                if (erVar.b == this) {
                    erVar.b = null;
                }
            }
        }

        public final void update(Observable observable, Object obj) {
            a();
        }

        public final void onRequestSuccess(TJPlacement tJPlacement) {
        }

        public final void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
            a(tJError.message);
        }

        public final void onContentReady(TJPlacement tJPlacement) {
            a();
        }

        public final void onContentShow(TJPlacement tJPlacement) {
        }

        public final void onContentDismiss(TJPlacement tJPlacement) {
        }

        public final void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
        }

        public final void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
        }
    }

    protected abstract TJPlacement a(Context context, TJPlacementListener tJPlacementListener, Object obj);

    protected abstract String a(Object obj);

    er() {
    }

    public final boolean c(Object obj) {
        if (!a()) {
            return false;
        }
        a aVar = null;
        synchronized (this) {
            if (this.b == null) {
                aVar = b(obj);
                this.b = aVar;
            }
        }
        if (aVar == null) {
            return false;
        }
        aVar.a();
        return true;
    }

    protected a b(Object obj) {
        return new a(this, obj);
    }

    protected boolean a() {
        return !TapjoyConnectCore.isFullScreenViewOpen();
    }

    protected boolean a(Observer observer) {
        if (TapjoyConnectCore.isFullScreenViewOpen()) {
            eo.e.addObserver(observer);
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                return false;
            }
            eo.e.deleteObserver(observer);
        }
        if (!fm.a().d()) {
            eo.c.addObserver(observer);
            if (!fm.a().d()) {
                return false;
            }
            eo.c.deleteObserver(observer);
        }
        return true;
    }
}
