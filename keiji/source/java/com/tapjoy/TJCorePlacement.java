package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.tapjoy.TJAdUnit.TJAdUnitVideoListener;
import com.tapjoy.TJAdUnit.TJAdUnitWebViewListener;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.be;
import com.tapjoy.internal.cg;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.d;
import com.tapjoy.internal.ee;
import com.tapjoy.internal.el;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.ff;
import com.tapjoy.internal.ff.a;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fs;
import com.tapjoy.internal.fw;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gg;
import com.tapjoy.internal.gh;
import com.tapjoy.internal.gi;
import com.tapjoy.internal.hj;
import com.tapjoy.internal.y;
import com.tapjoy.mediation.TJCustomPlacement;
import com.tapjoy.mediation.TJCustomPlacementListener;
import com.tapjoy.mediation.TJMediatedPlacementData;
import com.tapjoy.mediation.TJMediationSettings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class TJCorePlacement implements TJCustomPlacementListener {
    static final String a = TJCorePlacement.class.getSimpleName();
    private TJAdUnitVideoListener A = new TJAdUnitVideoListener(this) {
        final /* synthetic */ TJCorePlacement a;

        {
            this.a = r1;
        }

        public final void onVideoStart() {
            TJPlacement a = this.a.a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoStart(a);
            }
        }

        public final void onVideoCompleted() {
            TJPlacement a = this.a.a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoComplete(a);
            }
        }

        public final void onVideoError(String str) {
            TJPlacement a = this.a.a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoError(a, str);
            }
        }
    };
    Context b = d.c();
    TJPlacementData c;
    String d;
    long e;
    final ev f = new ev();
    TJAdUnit g;
    boolean h = false;
    gg i = null;
    public boolean initiatedBySdk;
    volatile boolean j = false;
    volatile boolean k = false;
    volatile boolean l = false;
    TJCustomPlacement m;
    String n;
    String o;
    String p;
    String q;
    private be r = be.a();
    private Map s;
    private boolean t = false;
    private hj u = null;
    private volatile boolean v = false;
    private TJMediatedPlacementData w;
    private Handler x;
    private Runnable y;
    private TJAdUnitWebViewListener z = new TJAdUnitWebViewListener(this) {
        final /* synthetic */ TJCorePlacement a;

        {
            this.a = r1;
        }

        public final void onContentReady() {
            this.a.d();
        }

        public final void onClosed() {
            if (this.a.h) {
                TJPlacementManager.decrementPlacementCacheCount();
                this.a.h = false;
            }
            if (this.a.t) {
                TJPlacementManager.decrementPlacementPreRenderCount();
                this.a.t = false;
            }
        }
    };

    class AnonymousClass7 implements fs {
        final /* synthetic */ String a;
        final /* synthetic */ TJCorePlacement b;

        AnonymousClass7(TJCorePlacement tJCorePlacement, String str) {
            this.b = tJCorePlacement;
            this.a = str;
        }

        public final void a(Context context, String str, String str2) {
            if (str2 == null) {
                this.b.c.setRedirectURL(str);
            } else {
                this.b.c.setBaseURL(str);
                this.b.c.setHttpResponse(str2);
            }
            this.b.c.setHasProgressSpinner(true);
            this.b.c.setContentViewId(this.a);
            Intent intent = new Intent(this.b.b, TJAdUnitActivity.class);
            intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, this.b.c);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    class AnonymousClass9 implements TJCacheListener {
        final /* synthetic */ TJCacheListener a;
        final /* synthetic */ TJCorePlacement b;

        AnonymousClass9(TJCorePlacement tJCorePlacement, TJCacheListener tJCacheListener) {
            this.b = tJCorePlacement;
            this.a = tJCacheListener;
        }

        public final void onCachingComplete(int i) {
            this.a.onCachingComplete(i);
        }
    }

    TJCorePlacement(String str, String str2) {
        if (this.b == null) {
            TapjoyLog.d(a, "getVisibleActivity() is NULL. Activity can be explicitly set via `Tapjoy.setActivity(Activity)`");
        }
        this.c = new TJPlacementData(str2, getPlacementContentUrl());
        this.c.setPlacementName(str);
        this.d = UUID.randomUUID().toString();
        this.g = new TJAdUnit(this.b);
        this.g.setWebViewListener(this.z);
        this.g.setVideoListener(this.A);
        this.y = new Runnable(this) {
            final /* synthetic */ TJCorePlacement a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    TapjoyLog.i(TJCorePlacement.a, "Custom placement adapter request timed out");
                    this.a.i();
                } catch (TapjoyException e) {
                    this.a.a(ErrorType.SERVER_ERROR, new TJError(HttpResponseCode.INTERNAL_SERVER_ERROR, e.getMessage() + " for placement " + this.a.c.getPlacementName()));
                }
            }
        };
    }

    final synchronized void a() {
        String url = this.c.getUrl();
        if (ct.c(url)) {
            url = getPlacementContentUrl();
            if (ct.c(url)) {
                url = "TJPlacement is missing APP_ID";
                ff.b("TJPlacement.requestContent").a(url).c();
                a(ErrorType.SDK_ERROR, new TJError(0, url));
            } else {
                this.c.updateUrl(url);
            }
        }
        TapjoyLog.d(a, "sendContentRequest -- URL: " + url + " name: " + this.c.getPlacementName());
        a(url, null, true);
    }

    final synchronized void a(String str, Map map, boolean z) {
        String str2 = null;
        synchronized (this) {
            if (this.v) {
                TapjoyLog.i(a, "Placement " + this.c.getPlacementName() + " is already requesting content");
                ff.b("TJPlacement.requestContent").b("already doing").c();
            } else {
                el elVar;
                fi d;
                this.c.resetPlacementRequestData();
                ev evVar = this.f;
                evVar.a = null;
                evVar.c = null;
                this.g.resetContentLoadState();
                this.v = false;
                this.j = false;
                this.k = false;
                this.l = false;
                this.v = true;
                final TJPlacement a = a("REQUEST");
                this.s = TapjoyConnectCore.getGenericURLParams();
                this.s.putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_NAME, this.c.getPlacementName(), true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_PRELOAD, TapjoyConstants.TJC_TRUE, true);
                TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_DEBUG, Boolean.toString(fw.a), true);
                fz a2 = fz.a();
                Map map2 = this.s;
                String str3 = TJAdUnitConstants.PARAM_ACTION_ID_EXCLUSION;
                if (a2.b != null) {
                    gi giVar = a2.b;
                    giVar.b();
                    str2 = giVar.b.a();
                }
                TapjoyUtil.safePut(map2, str3, str2, true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PLACEMENT_BY_SDK, String.valueOf(this.initiatedBySdk), true);
                TapjoyUtil.safePut(this.s, TJAdUnitConstants.PARAM_PUSH_ID, a.pushId, true);
                TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_MEDIATION_SOURCE, this.n, true);
                TapjoyUtil.safePut(this.s, TapjoyConstants.TJC_ADAPTER_VERSION, this.o, true);
                if (map != null) {
                    this.s.putAll(map);
                }
                this.g.setContext(this.b);
                if (z) {
                    elVar = new el(ez.b().c("placement_request_content_retry_timeout"));
                    d = ez.b().d("placement_request_content_retry_backoff");
                } else {
                    elVar = el.a;
                    d = fi.a;
                }
                final a d2 = ff.d("TJPlacement.requestContent");
                final String str4 = str;
                new Thread(this) {
                    final /* synthetic */ TJCorePlacement f;

                    public final void run() {
                        ff.a("TJPlacement.requestContent", d2);
                        int i = 0;
                        while (!a()) {
                            i++;
                            this.f.s.put(TapjoyConstants.TJC_RETRY, Integer.toString(i));
                            if (i == 1) {
                                d2.a("retry_timeout", Long.valueOf(elVar.b));
                            }
                            d2.a("retry_count", (long) i);
                        }
                    }

                    private boolean a() {
                        long b;
                        TapjoyLog.i(TJCorePlacement.a, "Sending content request for placement " + this.f.c.getPlacementName());
                        TJCorePlacement tJCorePlacement = this.f;
                        fz a = fz.a();
                        String f = this.f.c.getPlacementName();
                        Context h = this.f.b;
                        gh ghVar = a.a;
                        ee a2 = ghVar.a.a(false);
                        tJCorePlacement.u = new hj(ghVar.a, a2.d, a2.e, a2.f, f, h);
                        TapjoyHttpURLResponse responseFromURL = new TapjoyURLConnection().getResponseFromURL(str4, null, null, this.f.s);
                        this.f.c.setHttpStatusCode(responseFromURL.statusCode);
                        this.f.c.setHttpResponse(responseFromURL.response);
                        if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_PRERENDER_HEADER).equals("0")) {
                            this.f.c.setPrerenderingRequested(true);
                        }
                        String headerFieldAsString = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DEBUG_HEADER);
                        if (headerFieldAsString != null) {
                            TapjoyLog.v(TJCorePlacement.a, "Tapjoy-Server-Debug: " + headerFieldAsString);
                        }
                        if (responseFromURL.expires > 0) {
                            b = responseFromURL.expires - (responseFromURL.date > 0 ? responseFromURL.date : y.b());
                            if (b > 0) {
                                this.f.e = b + SystemClock.elapsedRealtime();
                            }
                        } else {
                            this.f.e = 0;
                        }
                        if (ct.c(responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_MEDIATION_HEADER))) {
                            if (!(responseFromURL == null || a.getListener() == null)) {
                                switch (responseFromURL.statusCode) {
                                    case TwitterResponse.NONE /*0*/:
                                        if (elVar.a(d.e)) {
                                            ff.b("TJPlacement.requestContent").a("network error").a("retry_timeout", Long.valueOf(elVar.b)).c();
                                            this.f.a(a, ErrorType.NETWORK_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                            break;
                                        }
                                        fi fiVar = d;
                                        long j = fiVar.e;
                                        b = (long) (((double) fiVar.e) * fiVar.d);
                                        if (b < fiVar.b) {
                                            b = fiVar.b;
                                        } else if (b > fiVar.c) {
                                            b = fiVar.c;
                                        }
                                        fiVar.e = b;
                                        if (j > 0) {
                                            synchronized (fiVar) {
                                                try {
                                                    fiVar.wait(j);
                                                } catch (InterruptedException e) {
                                                }
                                            }
                                        }
                                        return false;
                                    case HttpResponseCode.OK /*200*/:
                                        headerFieldAsString = responseFromURL.getHeaderFieldAsString("Content-Type");
                                        if (!ct.c(headerFieldAsString) && headerFieldAsString.contains("json")) {
                                            if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DISABLE_PRELOAD_HEADER).equals("1")) {
                                                if (!this.f.b(responseFromURL.response)) {
                                                    ff.b("TJPlacement.requestContent").a("asset error").c();
                                                    this.f.a(a, ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                                    break;
                                                }
                                                ff.b("TJPlacement.requestContent").a("content_type", (Object) "mm").c();
                                                this.f.j();
                                                this.f.d();
                                                break;
                                            }
                                            try {
                                                TJCorePlacement.a(this.f, responseFromURL.response);
                                                ff.b("TJPlacement.requestContent").a("content_type", (Object) "ad").c();
                                                this.f.j();
                                                this.f.d();
                                                break;
                                            } catch (TapjoyException e2) {
                                                headerFieldAsString = e2.getMessage() + " for placement " + this.f.c.getPlacementName();
                                                ff.b("TJPlacement.requestContent").a("server error").c();
                                                this.f.a(a, ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, headerFieldAsString));
                                                break;
                                            }
                                        }
                                        ff.b("TJPlacement.requestContent").a("content_type", (Object) "ad").c();
                                        this.f.j();
                                        TJCorePlacement tJCorePlacement2 = this.f;
                                        TJCacheListener anonymousClass1 = new TJCacheListener(this) {
                                            final /* synthetic */ AnonymousClass6 a;

                                            {
                                                this.a = r1;
                                            }

                                            public final void onCachingComplete(int i) {
                                                this.a.f.t = this.a.f.getAdUnit().preload(this.a.f.c);
                                            }
                                        };
                                        TapjoyLog.i(TJCorePlacement.a, "Checking if there is content to cache for placement " + tJCorePlacement2.c.getPlacementName());
                                        String headerFieldAsString2 = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_CACHE_HEADER);
                                        try {
                                            if (!TJPlacementManager.canCachePlacement()) {
                                                TapjoyLog.i(TJCorePlacement.a, "Placement caching limit reached. No content will be cached for placement " + tJCorePlacement2.c.getPlacementName());
                                                anonymousClass1.onCachingComplete(2);
                                                break;
                                            }
                                            JSONArray jSONArray = new JSONArray(headerFieldAsString2);
                                            if (jSONArray.length() <= 0) {
                                                anonymousClass1.onCachingComplete(1);
                                                break;
                                            }
                                            TapjoyLog.i(TJCorePlacement.a, "Begin caching content for placement " + tJCorePlacement2.c.getPlacementName());
                                            TJPlacementManager.incrementPlacementCacheCount();
                                            tJCorePlacement2.h = true;
                                            TapjoyCache.getInstance().cacheAssetGroup(jSONArray, new AnonymousClass9(tJCorePlacement2, anonymousClass1));
                                            break;
                                        } catch (JSONException e3) {
                                            anonymousClass1.onCachingComplete(2);
                                            break;
                                        }
                                        break;
                                    default:
                                        ff.b("TJPlacement.requestContent").a("content_type", (Object) "none").a("code", Integer.valueOf(responseFromURL.statusCode)).c();
                                        this.f.a(a);
                                        break;
                                }
                            }
                        }
                        try {
                            this.f.w = new TJMediatedPlacementData(this.f.c.getHttpResponse());
                            TJCorePlacement.j(this.f);
                        } catch (TapjoyException e22) {
                            headerFieldAsString = e22.getMessage() + " for placement " + this.f.c.getPlacementName();
                            ff.b("TJPlacement.requestContent").a("mediation error").c();
                            this.f.a(a, ErrorType.SDK_ERROR, new TJError(this.f.c.getHttpStatusCode(), headerFieldAsString));
                        }
                        this.f.v = false;
                        return true;
                    }
                }.start();
            }
        }
    }

    final String b() {
        String str = this.p;
        if (str != null) {
            return str;
        }
        return this.initiatedBySdk ? "sdk" : "app";
    }

    private void h() {
        this.x.removeCallbacks(this.y);
    }

    private void i() {
        TapjoyLog.i(a, "Custom placement call failed, retrying Tapjoy request");
        if (this.w == null) {
            throw new TapjoyException("Mediation data is null");
        }
        JSONObject nextCall = this.w.getNextCall();
        this.m = null;
        this.w = null;
        try {
            a(this.c.getUrl(), TapjoyUtil.jsonToStringMap(nextCall), false);
        } catch (JSONException e) {
            TapjoyLog.i(a, "Failed to load next call parameters for mediated placement " + this.c.getPlacementName());
            throw new TapjoyException("TJPlacement request failed due to custom placement fallback failure");
        }
    }

    private boolean b(String str) {
        try {
            hj.a aVar = (hj.a) this.u.a(URI.create(this.c.getUrl()), new ByteArrayInputStream(str.getBytes()));
            this.i = aVar.a;
            aVar.a.a();
            if (aVar.a.b()) {
                return true;
            }
            TapjoyLog.e(a, "Failed to load fiverocks placement");
            return false;
        } catch (IOException e) {
            TapjoyLog.e(a, e.toString());
            e.printStackTrace();
            return false;
        } catch (cg e2) {
            TapjoyLog.e(a, e2.toString());
            e2.printStackTrace();
            return false;
        }
    }

    public Context getContext() {
        return this.b;
    }

    public void setContext(Context context) {
        this.b = context;
    }

    public TJAdUnit getAdUnit() {
        return this.g;
    }

    public TJPlacementData getPlacementData() {
        return this.c;
    }

    public boolean isContentReady() {
        return this.l;
    }

    public boolean isContentAvailable() {
        return this.k;
    }

    public String getPlacementContentUrl() {
        String appID = TapjoyConnectCore.getAppID();
        if (ct.c(appID)) {
            return BuildConfig.FLAVOR;
        }
        return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + appID + "/content?";
    }

    final String c() {
        TJMediatedPlacementData tJMediatedPlacementData = this.w;
        if (this.m != null && tJMediatedPlacementData != null) {
            return tJMediatedPlacementData.getName();
        }
        if (this.i != null) {
            return "mm";
        }
        if (this.k) {
            return "ad";
        }
        return "none";
    }

    final void a(String str, TJPlacement tJPlacement) {
        synchronized (this.r) {
            this.r.put(str, tJPlacement);
            if (tJPlacement != null) {
                TapjoyLog.d(a, "Setting " + str + " placement: " + tJPlacement.getGUID());
            }
        }
    }

    final TJPlacement a(String str) {
        TJPlacement tJPlacement;
        synchronized (this.r) {
            tJPlacement = (TJPlacement) this.r.get(str);
            if (tJPlacement != null) {
                TapjoyLog.d(a, "Returning " + str + " placement: " + tJPlacement.getGUID());
            }
        }
        return tJPlacement;
    }

    private void j() {
        this.k = true;
        a(a("REQUEST"));
    }

    final void a(TJPlacement tJPlacement) {
        ev evVar = this.f;
        Object placementName = this.c.getPlacementName();
        Object b = b();
        Object c = c();
        evVar.b = 0;
        evVar.a = ff.e("PlacementContent.funnel").a().a("placement", placementName).a("placement_type", b).a("content_type", c).a("state", Integer.valueOf(evVar.b));
        evVar.a.c();
        if (!"none".equals(c)) {
            evVar.d = ff.e("PlacementContent.ready").a().a("placement", placementName).a("placement_type", b).a("content_type", c);
        }
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            TapjoyLog.i(a, "Content request delivered successfully for placement " + this.c.getPlacementName() + ", contentAvailable: " + isContentAvailable() + ", mediationAgent: " + this.p);
            tJPlacement.getListener().onRequestSuccess(tJPlacement);
        }
    }

    final void a(ErrorType errorType, TJError tJError) {
        a(a("REQUEST"), errorType, tJError);
    }

    final void a(TJPlacement tJPlacement, ErrorType errorType, TJError tJError) {
        TapjoyLog.e(a, new TapjoyErrorMessage(errorType, "Content request failed for placement " + this.c.getPlacementName() + "; Reason= " + tJError.message));
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            tJPlacement.getListener().onRequestFailure(tJPlacement, tJError);
        }
    }

    final void d() {
        if (!this.j) {
            ev evVar;
            this.l = true;
            TapjoyLog.i(a, "Content is ready for placement " + this.c.getPlacementName());
            if (this.g.isPrerendered()) {
                evVar = this.f;
                String str = "prerendered";
                Object valueOf = Boolean.valueOf(true);
                a aVar = evVar.a;
                if (aVar != null) {
                    aVar.a(str, valueOf);
                }
                a aVar2 = evVar.d;
                if (aVar2 != null) {
                    aVar2.a(str, valueOf);
                }
            }
            evVar = this.f;
            a aVar3 = evVar.d;
            if (aVar3 != null) {
                evVar.d = null;
                aVar3.b().c();
            }
            TJPlacement a = a("REQUEST");
            if (a != null && a.getListener() != null) {
                a.getListener().onContentReady(a);
                this.j = true;
            }
        }
    }

    final void e() {
        TJPlacement a = a("SHOW");
        if (a != null && a.getListener() != null) {
            TapjoyLog.i(a, "Content dismissed for placement " + this.c.getPlacementName());
            if (a != null && a.a != null) {
                a.a.onContentDismiss(a);
            }
        }
    }

    final void f() {
        TapjoyLog.i(a, "Content shown for placement " + this.c.getPlacementName());
        this.f.a();
        TJPlacement a = a("SHOW");
        if (a != null && a.getListener() != null) {
            a.getListener().onContentShow(a);
        }
    }

    public void onCustomPlacementLoad() {
        if (this.m != null) {
            h();
            this.k = true;
            if (!(this.w == null || this.w.getFillURL() == null)) {
                final String fillURL = this.w.getFillURL();
                new Thread(this) {
                    final /* synthetic */ TJCorePlacement b;

                    public final void run() {
                        TapjoyLog.d(TJCorePlacement.a, "onCustomPlacementLoad -- fillUrl=" + fillURL);
                        new TapjoyURLConnection().getResponseFromURL(fillURL);
                    }
                }.start();
            }
            ff.b("TJPlacement.requestContent").a("content_type", c()).c();
            j();
            d();
        }
    }

    public void onCustomPlacementFailure(Error error) {
        if (this.m != null) {
            h();
            try {
                if (!(this.w == null || this.w.getNoFillURL() == null)) {
                    final String noFillURL = this.w.getNoFillURL();
                    new Thread(this) {
                        final /* synthetic */ TJCorePlacement b;

                        public final void run() {
                            TapjoyLog.d(TJCorePlacement.a, "onCustomPlacementFailure -- noFillUrl=" + noFillURL);
                            new TapjoyURLConnection().getResponseFromURL(noFillURL);
                        }
                    }.start();
                }
                i();
            } catch (TapjoyException e) {
                a(ErrorType.SERVER_ERROR, new TJError(HttpResponseCode.INTERNAL_SERVER_ERROR, e.getMessage() + " for placement " + this.c.getPlacementName()));
            }
        }
    }

    public void onCustomPlacementContentShown() {
        f();
    }

    public void onCustomPlacementContentDismiss() {
        this.m = null;
        this.w = null;
        e();
    }

    public void onCustomPlacementReward(final String str, int i) {
        TJPlacement a = a("SHOW");
        if (a != null && a.getListener() != null) {
            a.getListener().onRewardRequest(a, new TJActionRequest(this) {
                final /* synthetic */ TJCorePlacement b;

                public final String getRequestId() {
                    return str;
                }

                public final String getToken() {
                    return null;
                }

                public final void completed() {
                }

                public final void cancelled() {
                }
            }, str, i);
        }
    }

    static /* synthetic */ void j(TJCorePlacement tJCorePlacement) {
        try {
            Class cls = Class.forName(tJCorePlacement.w.getClassname());
            if (TJCustomPlacement.class.isAssignableFrom(cls)) {
                tJCorePlacement.m = (TJCustomPlacement) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                if (tJCorePlacement.x == null) {
                    tJCorePlacement.x = new Handler(Looper.getMainLooper());
                }
                tJCorePlacement.x.postDelayed(tJCorePlacement.y, TJMediationSettings.getInstance().getTimeout());
                final a d = ff.d("TJPlacement.requestContent");
                tJCorePlacement.m.requestContentWithCustomPlacementParams(tJCorePlacement.b, new TJCustomPlacementListener(tJCorePlacement) {
                    final /* synthetic */ TJCorePlacement b;

                    public final void onCustomPlacementLoad() {
                        ff.a("TJPlacement.requestContent", d);
                        this.b.onCustomPlacementLoad();
                    }

                    public final void onCustomPlacementFailure(Error error) {
                        ff.a("TJPlacement.requestContent", d);
                        this.b.onCustomPlacementFailure(error);
                    }

                    public final void onCustomPlacementContentShown() {
                        this.b.onCustomPlacementContentShown();
                    }

                    public final void onCustomPlacementContentDismiss() {
                        this.b.onCustomPlacementContentDismiss();
                    }

                    public final void onCustomPlacementReward(String str, int i) {
                        this.b.onCustomPlacementReward(str, i);
                    }
                }, tJCorePlacement.w.getExtras());
                return;
            }
            TapjoyLog.e(a, tJCorePlacement.w.getClassname() + " does not implement TJCustomPlacement.");
            tJCorePlacement.i();
        } catch (Exception e) {
            TapjoyLog.e(a, new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Failed to load custom class " + tJCorePlacement.w.getClassname() + " for placement " + tJCorePlacement.c.getPlacementName()));
            tJCorePlacement.i();
        }
    }

    static /* synthetic */ void a(TJCorePlacement tJCorePlacement, String str) {
        if (str != null) {
            try {
                TapjoyLog.d(a, "Disable preload flag is set for placement " + tJCorePlacement.c.getPlacementName());
                tJCorePlacement.c.setRedirectURL(new JSONObject(str).getString(TapjoyConstants.TJC_REDIRECT_URL));
                tJCorePlacement.c.setPreloadDisabled(true);
                tJCorePlacement.c.setHasProgressSpinner(true);
                TapjoyLog.d(a, "redirect_url:" + tJCorePlacement.c.getRedirectURL());
                return;
            } catch (JSONException e) {
                throw new TapjoyException("TJPlacement request failed, malformed server response");
            }
        }
        throw new TapjoyException("TJPlacement request failed due to null response");
    }
}
