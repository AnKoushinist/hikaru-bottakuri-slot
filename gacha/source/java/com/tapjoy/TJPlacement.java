package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.tapjoy.TJAdUnit.TJAdUnitVideoListener;
import com.tapjoy.TJAdUnit.TJAdUnitWebViewListener;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.cf;
import com.tapjoy.internal.cr;
import com.tapjoy.internal.ek;
import com.tapjoy.internal.ff;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fj;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fr;
import com.tapjoy.internal.ft;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gq;
import com.tapjoy.internal.gq.a;
import com.tapjoy.mediation.TJCustomPlacement;
import com.tapjoy.mediation.TJCustomPlacementListener;
import com.tapjoy.mediation.TJMediatedPlacementData;
import com.tapjoy.mediation.TJMediationSettings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class TJPlacement implements TJCustomPlacementListener {
    TJAdUnit a;
    TJPlacementData b;
    TJPlacementListener c;
    boolean d = false;
    private Context e;
    private Map f;
    private TJPlacementListener g;
    private TJPlacementVideoListener h;
    private boolean i = false;
    public boolean initiatedBySdk;
    private boolean j = false;
    private boolean k = false;
    private gq l = null;
    private ft m = null;
    private boolean n = false;
    private boolean o;
    private String p;
    public String pushId;
    private String q;
    private String r;
    private String s;
    private TJCustomPlacement t;
    private TJMediatedPlacementData u;
    private Handler v;
    private Runnable w;

    class AnonymousClass6 implements TJCacheListener {
        final /* synthetic */ TJCacheListener a;
        final /* synthetic */ TJPlacement b;

        AnonymousClass6(TJPlacement tJPlacement, TJCacheListener tJCacheListener) {
            this.b = tJPlacement;
            this.a = tJCacheListener;
        }

        public final void onCachingComplete(int i) {
            this.a.onCachingComplete(i);
        }
    }

    public TJPlacement(Context context, String str, TJPlacementListener tJPlacementListener) {
        TJPlacementListener tJPlacementListener2 = null;
        this.e = context;
        this.g = tJPlacementListener;
        if (tJPlacementListener != null) {
            tJPlacementListener2 = (TJPlacementListener) ek.a(tJPlacementListener, TJPlacementListener.class);
        }
        this.c = tJPlacementListener2;
        this.b = new TJPlacementData(f());
        this.b.setPlacementName(str);
        TJPlacementManager.put(this.b.getGuid(), this);
        this.a = new TJAdUnit(context);
        this.a.setWebViewListener(new TJAdUnitWebViewListener(this) {
            final /* synthetic */ TJPlacement a;

            {
                this.a = r1;
            }

            public final void onContentReady() {
                this.a.c();
            }

            public final void onClosed() {
                if (this.a.d) {
                    TJPlacementManager.decrementPlacementCacheCount();
                    this.a.d = false;
                }
                if (this.a.n) {
                    TJPlacementManager.decrementPlacementPreRenderCount();
                    this.a.n = false;
                }
            }
        });
        this.a.setVideoListener(new TJAdUnitVideoListener(this) {
            final /* synthetic */ TJPlacement a;

            {
                this.a = r1;
            }

            public final void onVideoStart() {
                if (this.a.getVideoListener() != null) {
                    this.a.getVideoListener().onVideoStart(this.a);
                }
            }

            public final void onVideoCompleted() {
                if (this.a.getVideoListener() != null) {
                    this.a.getVideoListener().onVideoComplete(this.a);
                }
            }

            public final void onVideoError(String str) {
                if (this.a.getVideoListener() != null) {
                    this.a.getVideoListener().onVideoError(this.a, str);
                }
            }
        });
        FiveRocksIntegration.addPlacementCallback(str, this);
        this.w = new Runnable(this) {
            final /* synthetic */ TJPlacement a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    TapjoyLog.i("TJPlacement", "Custom placement adapter request timed out");
                    this.a.e();
                } catch (TapjoyException e) {
                    String str = e.getMessage() + " for placement " + this.a.b.getPlacementName();
                    TapjoyLog.i("TJPlacement", str);
                    this.a.g.onRequestFailure(this.a, new TJError(HttpResponseCode.INTERNAL_SERVER_ERROR, str));
                }
            }
        };
    }

    public TJPlacementListener getListener() {
        return this.g;
    }

    public void setVideoListener(TJPlacementVideoListener tJPlacementVideoListener) {
        this.h = tJPlacementVideoListener;
    }

    public TJPlacementVideoListener getVideoListener() {
        return this.h;
    }

    public String getName() {
        return this.b.getPlacementName();
    }

    public String getGUID() {
        return this.b.getGuid();
    }

    public boolean isContentReady() {
        return this.j;
    }

    public boolean isContentAvailable() {
        return this.i;
    }

    public void setMediationId(String str) {
        this.s = str;
    }

    public void requestContent() {
        if (!TapjoyConnectCore.isConnected()) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Can not call requestContent for TJPlacement because Tapjoy SDK has not successfully connected."));
            if (this.g != null) {
                this.g.onRequestFailure(this, new TJError(0, "SDK not connected -- connect must be called first with a successful callback"));
            }
        } else if (this.e == null) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Can not call requestContent for TJPlacement because context is null"));
            if (this.g != null) {
                this.g.onRequestFailure(this, new TJError(0, "Context is null -- TJPlacement requires a valid Context."));
            }
        } else if (cr.c(this.b.getPlacementName())) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Can not call send for TJPlacement because name is null or empty"));
            if (this.g != null) {
                this.g.onRequestFailure(this, new TJError(0, "Invalid placement name -- TJPlacement requires a valid placement name."));
            }
        } else if (this.o) {
            TapjoyLog.i("TJPlacement", "Placement " + getName() + " is already requesting content");
        } else if (cr.c(this.r)) {
            b();
        } else {
            Map hashMap = new HashMap();
            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_AGENT, this.r);
            hashMap.put(TJAdUnitConstants.PARAM_PLACEMENT_MEDIATION_ID, this.s);
            a(this.b.getMediationURL(), hashMap);
        }
    }

    private synchronized void b() {
        String url = this.b.getUrl();
        if (cr.c(url)) {
            url = f();
            if (cr.c(url)) {
                url = "TJPlacement is missing APP_ID";
                TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.SDK_ERROR, url));
                this.g.onRequestFailure(this, new TJError(0, url));
            } else {
                this.b.updateUrl(url);
            }
        }
        TapjoyLog.d("TJPlacement", "sendContentRequest -- URL: " + url + " name: " + this.b.getPlacementName());
        a(url);
    }

    private synchronized void a(String str) {
        a(str, null);
    }

    private synchronized void a(final String str, Map map) {
        String str2;
        this.b.resetPlacementRequestData();
        this.o = false;
        this.k = false;
        this.i = false;
        this.j = false;
        this.o = true;
        this.f = TapjoyConnectCore.getGenericURLParams();
        this.f.putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
        TapjoyUtil.safePut(this.f, TJAdUnitConstants.PARAM_PLACEMENT_NAME, this.b.getPlacementName(), true);
        TapjoyUtil.safePut(this.f, TJAdUnitConstants.PARAM_PLACEMENT_PRELOAD, TapjoyConstants.TJC_TRUE, true);
        TapjoyUtil.safePut(this.f, TapjoyConstants.TJC_DEBUG, Boolean.toString(fj.a), true);
        final fm a = fm.a();
        Map map2 = this.f;
        String str3 = TJAdUnitConstants.PARAM_ACTION_ID_EXCLUSION;
        if (a.b == null) {
            str2 = null;
        } else {
            fv fvVar = a.b;
            fvVar.b();
            str2 = fvVar.b.a();
        }
        TapjoyUtil.safePut(map2, str3, str2, true);
        TapjoyUtil.safePut(this.f, TJAdUnitConstants.PARAM_PLACEMENT_BY_SDK, String.valueOf(this.initiatedBySdk), true);
        TapjoyUtil.safePut(this.f, TJAdUnitConstants.PARAM_PUSH_ID, this.pushId, true);
        TapjoyUtil.safePut(this.f, TapjoyConstants.TJC_MEDIATION_SOURCE, this.p, true);
        TapjoyUtil.safePut(this.f, TapjoyConstants.TJC_ADAPTER_VERSION, this.q, true);
        if (map != null) {
            this.f.putAll(map);
        }
        new Thread(this) {
            final /* synthetic */ TJPlacement c;

            public final void run() {
                if (TJPlacementManager.a(this.c.b.getPlacementName(), this.c.initiatedBySdk)) {
                    TapjoyLog.i("TJPlacement", "Content request handled by negative cache for placement " + this.c.b.getPlacementName());
                    this.c.g.onRequestSuccess(this.c);
                    return;
                }
                TapjoyLog.i("TJPlacement", "Sending content request for placement " + this.c.b.getPlacementName());
                TJPlacement tJPlacement = this.c;
                fm fmVar = a;
                String placementName = this.c.b.getPlacementName();
                Context h = this.c.e;
                fu fuVar = fmVar.a;
                n a = fuVar.a.a(false);
                tJPlacement.l = new gq(fuVar.a, a.c, a.d, a.e, placementName, h);
                TapjoyHttpURLResponse responseFromURL = new TapjoyURLConnection().getResponseFromURL(str, null, null, this.c.f);
                this.c.b.setHttpStatusCode(responseFromURL.statusCode);
                this.c.b.setHttpResponse(responseFromURL.response);
                if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_PRERENDER_HEADER).equals("0")) {
                    this.c.b.setPrerenderingRequested(true);
                }
                String headerFieldAsString = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DEBUG_HEADER);
                if (headerFieldAsString != null) {
                    TapjoyLog.v("TJPlacement", "Tapjoy-Server-Debug: " + headerFieldAsString);
                }
                if (cr.c(responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_MEDIATION_HEADER))) {
                    if (!(responseFromURL == null || this.c.g == null)) {
                        switch (responseFromURL.statusCode) {
                            case TwitterResponse.NONE /*0*/:
                                TapjoyLog.i("TJPlacement", "Send request failed for placement " + this.c.b.getPlacementName());
                                this.c.g.onRequestFailure(this.c, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                break;
                            case HttpResponseCode.OK /*200*/:
                                headerFieldAsString = responseFromURL.getHeaderFieldAsString("Content-Type");
                                if (!cr.c(headerFieldAsString) && headerFieldAsString.contains("json")) {
                                    if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DISABLE_PRELOAD_HEADER).equals("1")) {
                                        if (!this.c.b(responseFromURL.response)) {
                                            TapjoyLog.i("TJPlacement", "Content request delivered successfully for placement " + this.c.b.getPlacementName() + ", contentAvailable: " + this.c.i + ", mediationAgent: " + this.c.r);
                                            this.c.g.onRequestSuccess(this.c);
                                            break;
                                        }
                                        this.c.a();
                                        this.c.c();
                                        break;
                                    }
                                    try {
                                        TJPlacement.a(this.c, responseFromURL.response);
                                        break;
                                    } catch (TapjoyException e) {
                                        headerFieldAsString = e.getMessage() + " for placement " + this.c.b.getPlacementName();
                                        TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, headerFieldAsString));
                                        this.c.g.onRequestFailure(this.c, new TJError(responseFromURL.statusCode, headerFieldAsString));
                                        break;
                                    }
                                }
                                this.c.a();
                                TJPlacement tJPlacement2 = this.c;
                                TJCacheListener anonymousClass1 = new TJCacheListener(this) {
                                    final /* synthetic */ AnonymousClass5 a;

                                    {
                                        this.a = r1;
                                    }

                                    public final void onCachingComplete(int i) {
                                        this.a.c.n = this.a.c.a.preload(this.a.c.b);
                                    }
                                };
                                TapjoyLog.i("TJPlacement", "Checking if there is content to cache for placement " + tJPlacement2.b.getPlacementName());
                                String headerFieldAsString2 = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_CACHE_HEADER);
                                try {
                                    if (!TJPlacementManager.canCachePlacement()) {
                                        TapjoyLog.i("TJPlacement", "Placement caching limit reached. No content will be cached for placement " + tJPlacement2.b.getPlacementName());
                                        anonymousClass1.onCachingComplete(2);
                                        break;
                                    }
                                    JSONArray jSONArray = new JSONArray(headerFieldAsString2);
                                    if (jSONArray.length() <= 0) {
                                        anonymousClass1.onCachingComplete(1);
                                        break;
                                    }
                                    TapjoyLog.i("TJPlacement", "Begin caching content for placement " + tJPlacement2.b.getPlacementName());
                                    TJPlacementManager.incrementPlacementCacheCount();
                                    tJPlacement2.d = true;
                                    TapjoyCache.getInstance().cacheAssetGroup(jSONArray, new AnonymousClass6(tJPlacement2, anonymousClass1));
                                    break;
                                } catch (JSONException e2) {
                                    anonymousClass1.onCachingComplete(2);
                                    break;
                                }
                                break;
                            default:
                                TapjoyLog.i("TJPlacement", "Content request delivered successfully for placement " + this.c.b.getPlacementName() + ", contentAvailable: " + this.c.i + ", mediationAgent: " + this.c.r);
                                if (responseFromURL.expires > 0) {
                                    TJPlacementManager.a(this.c.b.getPlacementName(), this.c.initiatedBySdk, responseFromURL.expires, responseFromURL.date);
                                }
                                this.c.g.onRequestSuccess(this.c);
                                break;
                        }
                    }
                }
                try {
                    this.c.u = new TJMediatedPlacementData(responseFromURL.response);
                    TJPlacement.j(this.c);
                } catch (TapjoyException e3) {
                    headerFieldAsString = e3.getMessage() + " for placement " + this.c.b.getPlacementName();
                    TapjoyLog.i("TJPlacement", headerFieldAsString);
                    this.c.g.onRequestFailure(this.c, new TJError(responseFromURL.statusCode, headerFieldAsString));
                }
                this.c.o = false;
            }
        }.start();
    }

    protected final void a() {
        this.i = true;
        TapjoyLog.i("TJPlacement", "Content request delivered successfully for placement " + this.b.getPlacementName() + ", contentAvailable: " + this.i + ", mediationAgent: " + this.r);
        this.g.onRequestSuccess(this);
    }

    private void c() {
        if (!this.k) {
            this.j = true;
            TapjoyLog.i("TJPlacement", "Content is ready for placement " + this.b.getPlacementName());
            if (this.g != null) {
                this.g.onContentReady(this);
                this.k = true;
            }
        }
    }

    public void showContent() {
        int i = 1;
        TapjoyLog.i("TJPlacement", "showPlacementContent() called for placement " + this.b.getPlacementName());
        if (!this.i) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "No placement content available. Can not show content for non-200 placement."));
        } else if (this.g == null) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "TJPlacementListener is null"));
        } else if (TapjoyConnectCore.isFullScreenViewOpen()) {
            TapjoyLog.w("TJPlacement", "Only one view can be presented at a time.");
        } else {
            if (TapjoyConnectCore.isViewOpen()) {
                TapjoyLog.w("TJPlacement", "Will close N2E content.");
                TJPlacementManager.dismissContentShowing(false);
            }
            if (this.t != null) {
                this.t.showContent();
            } else if (this.m != null) {
                String guid = getGUID();
                if (this.m != null) {
                    i = this.m instanceof fi ? 3 : this.m instanceof fr ? 2 : 0;
                }
                TapjoyConnectCore.viewWillOpen(guid, i);
                this.m.j = new ff(this) {
                    final /* synthetic */ TJPlacement a;

                    {
                        this.a = r1;
                    }

                    public final void a(Context context, String str, String str2) {
                        TapjoyConnectCore.viewWillOpen(this.a.getGUID(), 1);
                        if (str2 == null) {
                            this.a.b.setRedirectURL(str);
                        } else {
                            this.a.b.setBaseURL(str);
                            this.a.b.setHttpResponse(str2);
                        }
                        this.a.b.setHasProgressSpinner(true);
                        Intent intent = new Intent(this.a.e, TJAdUnitActivity.class);
                        intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, this.a.b);
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                    }
                };
                fm.a(new Runnable(this) {
                    final /* synthetic */ TJPlacement a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        this.a.m.a(fm.a().p);
                    }
                });
            } else {
                TapjoyConnectCore.viewWillOpen(getGUID(), 1);
                Intent intent = new Intent(this.e, TJAdUnitActivity.class);
                intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, this.b);
                intent.setFlags(268435456);
                this.e.startActivity(intent);
            }
            this.i = false;
            this.j = false;
        }
    }

    private void d() {
        this.v.removeCallbacks(this.w);
    }

    private void e() {
        TapjoyLog.i("TJPlacement", "Custom placement call failed, retrying Tapjoy request");
        if (this.u == null) {
            throw new TapjoyException("Mediation data is null");
        }
        JSONObject nextCall = this.u.getNextCall();
        this.t = null;
        this.u = null;
        try {
            a(this.b.getUrl(), TapjoyUtil.jsonToStringMap(nextCall));
        } catch (JSONException e) {
            TapjoyLog.i("TJPlacement", "Failed to load next call parameters for mediated placement " + this.b.getPlacementName());
            throw new TapjoyException("TJPlacement request failed due to custom placement fallback failure");
        }
    }

    private boolean b(String str) {
        try {
            a aVar = (a) this.l.a(URI.create(this.b.getUrl()), new ByteArrayInputStream(str.getBytes()));
            this.m = aVar.a;
            aVar.a.a();
            if (aVar.a.b()) {
                return true;
            }
            TapjoyLog.e("TJPlacement", "Failed to load fiverocks placement");
            return false;
        } catch (IOException e) {
            TapjoyLog.e("TJPlacement", e.toString());
            e.printStackTrace();
            return false;
        } catch (cf e2) {
            TapjoyLog.e("TJPlacement", e2.toString());
            e2.printStackTrace();
            return false;
        }
    }

    private static String f() {
        String appID = TapjoyConnectCore.getAppID();
        if (cr.c(appID)) {
            return BuildConfig.FLAVOR;
        }
        return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + appID + "/content?";
    }

    public void setAdapterVersion(String str) {
        this.q = str;
    }

    public void onCustomPlacementLoad() {
        if (this.t != null) {
            d();
            this.i = true;
            if (!(this.u == null || this.u.getFillURL() == null)) {
                final String fillURL = this.u.getFillURL();
                new Thread(this) {
                    final /* synthetic */ TJPlacement b;

                    public final void run() {
                        TapjoyLog.d("TJPlacement", "onCustomPlacementLoad -- fillUrl=" + fillURL);
                        new TapjoyURLConnection().getResponseFromURL(fillURL);
                    }
                }.start();
            }
            a();
            c();
        }
    }

    public void onCustomPlacementFailure(Error error) {
        if (this.t != null) {
            d();
            final String noFillURL;
            try {
                if (!(this.u == null || this.u.getNoFillURL() == null)) {
                    noFillURL = this.u.getNoFillURL();
                    new Thread(this) {
                        final /* synthetic */ TJPlacement b;

                        public final void run() {
                            TapjoyLog.d("TJPlacement", "onCustomPlacementFailure -- noFillUrl=" + noFillURL);
                            new TapjoyURLConnection().getResponseFromURL(noFillURL);
                        }
                    }.start();
                }
                e();
            } catch (TapjoyException e) {
                noFillURL = e.getMessage() + " for placement " + this.b.getPlacementName();
                TapjoyLog.i("TJPlacement", noFillURL);
                this.g.onRequestFailure(this, new TJError(HttpResponseCode.INTERNAL_SERVER_ERROR, noFillURL));
            }
        }
    }

    public void onCustomPlacementContentShown() {
        this.g.onContentShow(this);
    }

    public void onCustomPlacementContentDismiss() {
        this.t = null;
        this.u = null;
        this.g.onContentDismiss(this);
    }

    public void onCustomPlacementReward(final String str, int i) {
        this.g.onRewardRequest(this, new TJActionRequest(this) {
            final /* synthetic */ TJPlacement b;

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

    public static void dismissContent() {
        boolean z = false;
        if (TapjoyConstants.TJC_TRUE.equals(TapjoyConnectCore.getConnectFlagValue("TJC_OPTION_DISMISS_CONTENT_ALL"))) {
            z = true;
        }
        TJPlacementManager.dismissContentShowing(z);
    }

    public void setMediationName(String str) {
        this.p = str;
        this.r = str;
        this.b.setMediationURL(TapjoyConnectCore.getPlacementURL() + "v1/apps/" + TapjoyConnectCore.getAppID() + "/mediation_content?");
    }

    static /* synthetic */ void j(TJPlacement tJPlacement) {
        try {
            Class cls = Class.forName(tJPlacement.u.getClassname());
            if (TJCustomPlacement.class.isAssignableFrom(cls)) {
                tJPlacement.t = (TJCustomPlacement) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                if (tJPlacement.v == null) {
                    tJPlacement.v = new Handler(Looper.getMainLooper());
                }
                tJPlacement.v.postDelayed(tJPlacement.w, TJMediationSettings.getInstance().getTimeout());
                tJPlacement.t.requestContentWithCustomPlacementParams(tJPlacement.e, tJPlacement, tJPlacement.u.getExtras());
                return;
            }
            TapjoyLog.e("TJPlacement", tJPlacement.u.getClassname() + " does not implement TJCustomPlacement.");
            tJPlacement.e();
        } catch (Exception e) {
            TapjoyLog.e("TJPlacement", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Failed to load custom class " + tJPlacement.u.getClassname() + " for placement " + tJPlacement.b.getPlacementName()));
            tJPlacement.e();
        }
    }

    static /* synthetic */ void a(TJPlacement tJPlacement, String str) {
        if (str != null) {
            try {
                TapjoyLog.d("TJPlacement", "Disable preload flag is set for placement " + tJPlacement.b.getPlacementName());
                tJPlacement.b.setRedirectURL(new JSONObject(str).getString(TapjoyConstants.TJC_REDIRECT_URL));
                tJPlacement.b.setPreloadDisabled(true);
                tJPlacement.b.setHasProgressSpinner(true);
                TapjoyLog.d("TJPlacement", "redirect_url:" + tJPlacement.b.getRedirectURL());
                tJPlacement.a();
                tJPlacement.c();
                return;
            } catch (JSONException e) {
                throw new TapjoyException("TJPlacement request failed, malformed server response");
            }
        }
        throw new TapjoyException("TJPlacement request failed due to null response");
    }
}
