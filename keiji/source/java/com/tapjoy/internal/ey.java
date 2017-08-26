package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import java.util.Observable;
import java.util.Observer;

abstract class ey {
    volatile a b;

    class a implements TJPlacementListener, Observer {
        final /* synthetic */ ey a;
        private final Object b;
        private final el c;
        private volatile boolean d;
        private TJPlacement e;

        a(ey eyVar, Object obj) {
            this(eyVar, obj, new el(TapjoyConstants.TIMER_INCREMENT));
        }

        a(ey eyVar, Object obj, el elVar) {
            this.a = eyVar;
            this.b = obj;
            this.c = elVar;
        }

        final void a() {
            synchronized (this) {
                if (this.d) {
                } else if (this.c.a()) {
                    a("Timed out");
                } else {
                    if (!TapjoyConnectCore.isConnected()) {
                        et.a.addObserver(this);
                        if (TapjoyConnectCore.isConnected()) {
                            et.a.deleteObserver(this);
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
                et.a.deleteObserver(this);
                et.e.deleteObserver(this);
                et.c.deleteObserver(this);
            }
            ey eyVar = this.a;
            synchronized (eyVar) {
                if (eyVar.b == this) {
                    eyVar.b = null;
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

    ey() {
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
            et.e.addObserver(observer);
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                return false;
            }
            et.e.deleteObserver(observer);
        }
        if (!fz.a().d()) {
            et.c.addObserver(observer);
            if (!fz.a().d()) {
                return false;
            }
            et.c.deleteObserver(observer);
        }
        return true;
    }
}
