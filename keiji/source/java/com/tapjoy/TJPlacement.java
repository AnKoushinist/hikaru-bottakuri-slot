package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.tapjoy.TJCorePlacement.AnonymousClass7;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.ep;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.ff;
import com.tapjoy.internal.ff.a;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.ge;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;

public class TJPlacement {
    TJPlacementListener a;
    private TJCorePlacement b;
    private TJPlacementListener c;
    private TJPlacementVideoListener d;
    private String e;
    public String pushId;

    @Deprecated
    public TJPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        TJCorePlacement a = TJPlacementManager.a(str);
        if (a == null) {
            a = TJPlacementManager.a(str, BuildConfig.FLAVOR, BuildConfig.FLAVOR, false);
        }
        a.setContext(context);
        a(a, tJPlacementListener);
    }

    TJPlacement(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        a(tJCorePlacement, tJPlacementListener);
    }

    private void a(TJCorePlacement tJCorePlacement, TJPlacementListener tJPlacementListener) {
        this.b = tJCorePlacement;
        this.e = UUID.randomUUID().toString();
        this.c = tJPlacementListener;
        this.a = tJPlacementListener != null ? (TJPlacementListener) ep.a(tJPlacementListener, TJPlacementListener.class) : null;
        FiveRocksIntegration.addPlacementCallback(getName(), this);
    }

    public TJPlacementListener getListener() {
        return this.c;
    }

    public void setVideoListener(TJPlacementVideoListener tJPlacementVideoListener) {
        this.d = tJPlacementVideoListener;
    }

    public TJPlacementVideoListener getVideoListener() {
        return this.d;
    }

    public String getName() {
        return this.b.getPlacementData() != null ? this.b.getPlacementData().getPlacementName() : BuildConfig.FLAVOR;
    }

    public boolean isContentReady() {
        boolean isContentReady = this.b.isContentReady();
        ev evVar = this.b.f;
        if (isContentReady) {
            evVar.a(4);
        } else {
            evVar.a(2);
        }
        return isContentReady;
    }

    public boolean isContentAvailable() {
        this.b.f.a(1);
        return this.b.isContentAvailable();
    }

    public void setMediationId(String str) {
        this.b.q = str;
    }

    public void requestContent() {
        Object name = getName();
        TapjoyLog.i("TJPlacement", "requestContent() called for placement " + name);
        ff.a("TJPlacement.requestContent").a("placement", name).a("placement_type", this.b.b());
        if (!TapjoyConnectCore.isConnected()) {
            ff.b("TJPlacement.requestContent").b("not connected").c();
            a(new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
        } else if (this.b.getContext() == null) {
            ff.b("TJPlacement.requestContent").b("no context").c();
            a(new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
        } else if (ct.c(name)) {
            ff.b("TJPlacement.requestContent").b("invalid name").c();
            a(new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
        } else {
            try {
                TJCorePlacement tJCorePlacement = this.b;
                if (this == null) {
                    tJCorePlacement.a(ErrorType.SDK_ERROR, new TJError(0, "Cannot request content from a NULL placement"));
                } else {
                    tJCorePlacement.a("REQUEST", this);
                    if (tJCorePlacement.e - SystemClock.elapsedRealtime() > 0) {
                        TapjoyLog.d(TJCorePlacement.a, "Content has not expired yet for " + tJCorePlacement.c.getPlacementName());
                        if (tJCorePlacement.k) {
                            ff.b("TJPlacement.requestContent").a("content_type", tJCorePlacement.c()).a("from", (Object) "cache").c();
                            tJCorePlacement.j = false;
                            tJCorePlacement.a(this);
                            tJCorePlacement.d();
                        } else {
                            ff.b("TJPlacement.requestContent").a("content_type", (Object) "none").a("from", (Object) "cache").c();
                            tJCorePlacement.a(this);
                        }
                    } else {
                        if (tJCorePlacement.k) {
                            ff.c("TJPlacement.requestContent").a("was_available", Boolean.valueOf(true));
                        }
                        if (tJCorePlacement.l) {
                            ff.c("TJPlacement.requestContent").a("was_ready", Boolean.valueOf(true));
                        }
                        if (ct.c(tJCorePlacement.p)) {
                            tJCorePlacement.a();
                        } else {
                            Map hashMap = new HashMap();
                            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_AGENT, tJCorePlacement.p);
                            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_ID, tJCorePlacement.q);
                            tJCorePlacement.a(tJCorePlacement.c.getMediationURL(), hashMap, true);
                        }
                    }
                }
                ff.d("TJPlacement.requestContent");
            } catch (Throwable th) {
                ff.d("TJPlacement.requestContent");
            }
        }
    }

    public void showContent() {
        int i = 1;
        Object name = getName();
        TapjoyLog.i("TJPlacement", "showContent() called for placement " + name);
        this.b.f.a(8);
        ff.a("TJPlacement.showContent").a("placement", name).a("placement_type", this.b.b()).a("content_type", this.b.c());
        if (this.b.isContentAvailable()) {
            try {
                TJCorePlacement tJCorePlacement = this.b;
                if (this == null) {
                    tJCorePlacement.a(ErrorType.SDK_ERROR, new TJError(0, "Cannot show content from a NULL placement"));
                } else if (TapjoyConnectCore.isFullScreenViewOpen()) {
                    TapjoyLog.w(TJCorePlacement.a, "Only one view can be presented at a time.");
                    ff.b("TJPlacement.showContent").b("another content showing").c();
                } else {
                    if (TapjoyConnectCore.isViewOpen()) {
                        TapjoyLog.w(TJCorePlacement.a, "Will close N2E content.");
                        TJPlacementManager.dismissContentShowing(false);
                    }
                    tJCorePlacement.a("SHOW", this);
                    a d = ff.d("TJPlacement.showContent");
                    if (tJCorePlacement.g.isPrerendered()) {
                        d.a("prerendered", Boolean.valueOf(true));
                    }
                    if (tJCorePlacement.isContentReady()) {
                        d.a("content_ready", Boolean.valueOf(true));
                    }
                    tJCorePlacement.f.c = d;
                    String uuid = UUID.randomUUID().toString();
                    if (tJCorePlacement.m != null) {
                        tJCorePlacement.m.showContent();
                    } else if (tJCorePlacement.i != null) {
                        tJCorePlacement.i.k = uuid;
                        if (tJCorePlacement.i != null) {
                            i = tJCorePlacement.i instanceof fv ? 3 : tJCorePlacement.i instanceof ge ? 2 : 0;
                        }
                        TapjoyConnectCore.viewWillOpen(uuid, i);
                        tJCorePlacement.i.j = new AnonymousClass7(tJCorePlacement, uuid);
                        fz.a(new Runnable(tJCorePlacement) {
                            final /* synthetic */ TJCorePlacement a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                this.a.i.a(fz.a().p, this.a.f);
                            }
                        });
                    } else {
                        tJCorePlacement.c.setContentViewId(uuid);
                        Intent intent = new Intent(tJCorePlacement.b, TJAdUnitActivity.class);
                        intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJCorePlacement.c);
                        intent.setFlags(268435456);
                        tJCorePlacement.b.startActivity(intent);
                    }
                    tJCorePlacement.e = 0;
                    tJCorePlacement.k = false;
                    tJCorePlacement.l = false;
                }
                ff.d("TJPlacement.showContent");
            } catch (Throwable th) {
                ff.d("TJPlacement.showContent");
            }
        } else {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
            ff.b("TJPlacement.showContent").b("no content").c();
        }
    }

    public void setMediationName(String str) {
        TapjoyLog.d("TJPlacement", "setMediationName=" + str);
        if (!ct.c(str)) {
            Context context = this.b != null ? this.b.getContext() : null;
            this.b = TJPlacementManager.a(getName(), str, BuildConfig.FLAVOR, false);
            TJCorePlacement tJCorePlacement = this.b;
            tJCorePlacement.p = str;
            tJCorePlacement.n = str;
            tJCorePlacement.c.setMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + TapjoyConnectCore.getAppID() + "/mediation_content?");
            if (context != null) {
                this.b.setContext(context);
            }
        }
    }

    public void setAdapterVersion(String str) {
        this.b.o = str;
    }

    public static void dismissContent() {
        boolean z = false;
        if (TapjoyConstants.TJC_TRUE.equals(TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL"))) {
            z = true;
        }
        TJPlacementManager.dismissContentShowing(z);
    }

    public String getGUID() {
        return this.e;
    }

    private void a(TJError tJError) {
        this.b.a(this, ErrorType.INTEGRATION_ERROR, tJError);
    }
}
