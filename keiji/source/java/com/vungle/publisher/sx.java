package com.vungle.publisher;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.jc.b;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class sx extends mu {
    b a;
    public boolean b = true;
    public boolean c = false;
    @Inject
    com.vungle.publisher.gm.a d;
    @Inject
    ql e;
    @Inject
    th f;
    @Inject
    com.vungle.publisher.tl.a g;
    @Inject
    bw h;
    @Inject
    pn i;
    private n j;
    private boolean k = false;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[tl.values().length];

        static {
            try {
                a[tl.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[tl.PROPERTIES_SET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[tl.USE_CUSTOM_CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[tl.USE_CUSTOM_PRIVACY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[tl.OPEN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[tl.PRIVACY_PAGE_EVENT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[tl.SET_ORIENTATION_PROPERTIES.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[tl.TPAT_EVENT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[tl.USER_ACTION_EVENT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[tl.USER_VALUE_ACTION_EVENT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[tl.ERROR_EVENT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[tl.SUCCESSFUL_VIEW_EVENT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[tl.THROW_INCENTIVIZED_DIALOG.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[tl.PLAY_HTML_VIDEO_EVENT.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[tl.UNSPECIFIED.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<sx> a;
        @Inject
        com.vungle.publisher.jc.a b;

        @Inject
        a() {
        }
    }

    @Inject
    sx() {
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.d.a(Logger.AD_TAG, "received error in WebViewClient: " + str, new RuntimeException());
        this.e.a(new ub("100"));
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Logger.e(Logger.AD_TAG, "received ssl error: " + sslError.getPrimaryError());
        this.e.a(new ub("101"));
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        Logger.v(Logger.AD_TAG, "mraid page started loading: " + str);
    }

    public final void onPageFinished(WebView webView, String str) {
        Logger.v(Logger.AD_TAG, "mraid page finished loading: " + str);
        if (this.b) {
            Logger.d(Logger.AD_TAG, "mraid webview finished loading");
            th thVar = this.f;
            n nVar = this.j;
            tp a = thVar.d.a();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("sms", false);
                jSONObject.put("tel", false);
                jSONObject.put("calendar", false);
                jSONObject.put("storePicture", false);
                jSONObject.put("inlineVideo", false);
            } catch (Throwable e) {
                Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid supports properties", e);
            }
            a.h = jSONObject;
            a.a(true);
            a.c();
            a.m = Boolean.valueOf(nVar.isIncentivized());
            a.n = Boolean.valueOf(nVar.isBackButtonImmediatelyEnabled());
            a.j = tu.interstitial;
            try {
                th.a(webView, a.a(), true);
            } catch (Throwable e2) {
                thVar.b.b(Logger.AD_TAG, "could not update mraid properties", e2);
            }
        } else {
            if (this.c && !"about:blank".equalsIgnoreCase(str)) {
                Logger.v(Logger.AD_TAG, "clear history");
                this.c = false;
                webView.clearHistory();
            }
            this.e.a(new ui(te.b.visible));
            this.e.a(new ug(to.NONE, true));
        }
        super.onPageFinished(webView, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean shouldOverrideUrlLoading(final android.webkit.WebView r9, java.lang.String r10) {
        /*
        r8 = this;
        r1 = 1;
        r0 = "VungleAd";
        r2 = new java.lang.StringBuilder;
        r3 = "mraid attempted to navigate to url: ";
        r2.<init>(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        com.vungle.log.Logger.v(r0, r2);
        r2 = android.net.Uri.parse(r10);	 Catch:{ Exception -> 0x0060 }
        r0 = r2.getScheme();
        r3 = r2.getHost();
        r4 = "mraid";
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x025d;
    L_0x0029:
        r4 = new java.util.HashMap;	 Catch:{ Exception -> 0x004a }
        r4.<init>();	 Catch:{ Exception -> 0x004a }
        r0 = com.vungle.publisher.ags.a(r2);	 Catch:{ Exception -> 0x004a }
        r5 = r0.iterator();	 Catch:{ Exception -> 0x004a }
    L_0x0036:
        r0 = r5.hasNext();	 Catch:{ Exception -> 0x004a }
        if (r0 == 0) goto L_0x0083;
    L_0x003c:
        r0 = r5.next();	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r6 = r2.getQueryParameter(r0);	 Catch:{ Exception -> 0x004a }
        r4.put(r0, r6);	 Catch:{ Exception -> 0x004a }
        goto L_0x0036;
    L_0x004a:
        r0 = move-exception;
        r2 = r8.d;
        r3 = "VungleAd";
        r4 = "exception while overriding mraid url";
        r2.a(r3, r4, r0);
        r0 = r8.e;
        r2 = new com.vungle.publisher.uc;
        r2.<init>();
        r0.a(r2);
    L_0x005e:
        r0 = r1;
    L_0x005f:
        return r0;
    L_0x0060:
        r0 = move-exception;
        r2 = r8.d;
        r3 = "VungleAd";
        r4 = new java.lang.StringBuilder;
        r5 = "Invalid URL: ";
        r4.<init>(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        r2.b(r3, r4, r0);
        r0 = r8.e;
        r2 = new com.vungle.publisher.uc;
        r2.<init>();
        r0.a(r2);
        r0 = r1;
        goto L_0x005f;
    L_0x0083:
        r0 = com.vungle.publisher.tl.a.a(r3);	 Catch:{ Exception -> 0x004a }
        r2 = "VungleAd";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004a }
        r5 = "received MRAID event from js: ";
        r3.<init>(r5);	 Catch:{ Exception -> 0x004a }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x004a }
        r3 = r3.toString();	 Catch:{ Exception -> 0x004a }
        com.vungle.log.Logger.d(r2, r3);	 Catch:{ Exception -> 0x004a }
        r2 = com.vungle.publisher.sx.AnonymousClass2.a;	 Catch:{ Exception -> 0x004a }
        r3 = r0.ordinal();	 Catch:{ Exception -> 0x004a }
        r2 = r2[r3];	 Catch:{ Exception -> 0x004a }
        switch(r2) {
            case 1: goto L_0x00c3;
            case 2: goto L_0x00ce;
            case 3: goto L_0x012b;
            case 4: goto L_0x0143;
            case 5: goto L_0x015b;
            case 6: goto L_0x0193;
            case 7: goto L_0x01a7;
            case 8: goto L_0x01cb;
            case 9: goto L_0x01db;
            case 10: goto L_0x01db;
            case 11: goto L_0x01f3;
            case 12: goto L_0x0207;
            case 13: goto L_0x00ba;
            case 14: goto L_0x0213;
            default: goto L_0x00a6;
        };	 Catch:{ Exception -> 0x004a }
    L_0x00a6:
        r2 = "VungleAd";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004a }
        r4 = "Unknown MRAID Javascript command: ";
        r3.<init>(r4);	 Catch:{ Exception -> 0x004a }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x004a }
        r0 = r0.toString();	 Catch:{ Exception -> 0x004a }
        com.vungle.log.Logger.w(r2, r0);	 Catch:{ Exception -> 0x004a }
    L_0x00ba:
        r0 = "notifyCommandComplete";
        r2 = 0;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x004a }
        com.vungle.publisher.ss.a(r9, r0, r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x005e;
    L_0x00c3:
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.ty;	 Catch:{ Exception -> 0x004a }
        r2.<init>();	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x00ce:
        r0 = r8.k;	 Catch:{ Exception -> 0x004a }
        if (r0 != 0) goto L_0x005e;
    L_0x00d2:
        r0 = r8.a;	 Catch:{ JSONException -> 0x0120 }
        r2 = "VungleAd";
        r3 = "inject tokens into js and notify ready";
        com.vungle.log.Logger.d(r2, r3);	 Catch:{ JSONException -> 0x0120 }
        r0 = r0.a();	 Catch:{ JSONException -> 0x0120 }
        r2 = r0.length();	 Catch:{ JSONException -> 0x0120 }
        if (r2 <= 0) goto L_0x0117;
    L_0x00e5:
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0120 }
        r2 = "VungleAd";
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0120 }
        r4 = "tokens: ";
        r3.<init>(r4);	 Catch:{ JSONException -> 0x0120 }
        r3 = r3.append(r0);	 Catch:{ JSONException -> 0x0120 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0120 }
        com.vungle.log.Logger.v(r2, r3);	 Catch:{ JSONException -> 0x0120 }
        r2 = "notifyReadyEvent";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x0120 }
        r4 = 0;
        r3[r4] = r0;	 Catch:{ JSONException -> 0x0120 }
        com.vungle.publisher.ss.a(r9, r2, r3);	 Catch:{ JSONException -> 0x0120 }
    L_0x0108:
        r0 = 1;
        r8.k = r0;	 Catch:{ Exception -> 0x004a }
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.uf;	 Catch:{ Exception -> 0x004a }
        r2.<init>();	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x005e;
    L_0x0117:
        r0 = "notifyReadyEvent";
        r2 = 0;
        r2 = new java.lang.String[r2];	 Catch:{ JSONException -> 0x0120 }
        com.vungle.publisher.ss.a(r9, r0, r2);	 Catch:{ JSONException -> 0x0120 }
        goto L_0x0108;
    L_0x0120:
        r0 = move-exception;
        r2 = r8.d;	 Catch:{ Exception -> 0x004a }
        r3 = "VungleAd";
        r4 = "failed to inject JSON tokens";
        r2.b(r3, r4, r0);	 Catch:{ Exception -> 0x004a }
        goto L_0x0108;
    L_0x012b:
        r0 = "sdkCloseButton";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r0 = com.vungle.publisher.te.b.valueOf(r0);	 Catch:{ Exception -> 0x004a }
        r2 = r8.e;	 Catch:{ Exception -> 0x004a }
        r3 = new com.vungle.publisher.ui;	 Catch:{ Exception -> 0x004a }
        r3.<init>(r0);	 Catch:{ Exception -> 0x004a }
        r2.a(r3);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x0143:
        r0 = "useCustomPrivacy";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r0 = com.vungle.publisher.ags.b(r0);	 Catch:{ Exception -> 0x004a }
        r2 = r8.e;	 Catch:{ Exception -> 0x004a }
        r3 = new com.vungle.publisher.uj;	 Catch:{ Exception -> 0x004a }
        r3.<init>(r0);	 Catch:{ Exception -> 0x004a }
        r2.a(r3);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x015b:
        r0 = "url";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r0 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x004a }
        r2 = r8.a(r0);	 Catch:{ Exception -> 0x004a }
        if (r2 != 0) goto L_0x00ba;
    L_0x016d:
        r2 = "about:blank";
        r9.loadUrl(r2);	 Catch:{ Exception -> 0x004a }
        r2 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r9.setBackgroundColor(r2);	 Catch:{ Exception -> 0x004a }
        r2 = 0;
        r8.b = r2;	 Catch:{ Exception -> 0x004a }
        r2 = 1;
        r8.c = r2;	 Catch:{ Exception -> 0x004a }
        r0 = r0.toString();	 Catch:{ Exception -> 0x004a }
        r9.loadUrl(r0);	 Catch:{ Exception -> 0x004a }
        r9.clearHistory();	 Catch:{ Exception -> 0x004a }
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.ue;	 Catch:{ Exception -> 0x004a }
        r2.<init>();	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x0193:
        r2 = r8.e;	 Catch:{ Exception -> 0x004a }
        r3 = new com.vungle.publisher.ak;	 Catch:{ Exception -> 0x004a }
        r0 = "url";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r3.<init>(r0);	 Catch:{ Exception -> 0x004a }
        r2.a(r3);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x01a7:
        r0 = "allowOrientationChange";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r2 = com.vungle.publisher.ags.b(r0);	 Catch:{ Exception -> 0x004a }
        r0 = "forceOrientation";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r0 = com.vungle.publisher.ags.d(r0);	 Catch:{ Exception -> 0x004a }
        r3 = r8.e;	 Catch:{ Exception -> 0x004a }
        r4 = new com.vungle.publisher.ug;	 Catch:{ Exception -> 0x004a }
        r4.<init>(r0, r2);	 Catch:{ Exception -> 0x004a }
        r3.a(r4);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x01cb:
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.tv;	 Catch:{ Exception -> 0x004a }
        r3 = a(r4);	 Catch:{ Exception -> 0x004a }
        r2.<init>(r3);	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x01db:
        r0 = "value";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r2 = r8.e;	 Catch:{ Exception -> 0x004a }
        r3 = new com.vungle.publisher.tx;	 Catch:{ Exception -> 0x004a }
        r4 = a(r4);	 Catch:{ Exception -> 0x004a }
        r3.<init>(r4, r0);	 Catch:{ Exception -> 0x004a }
        r2.a(r3);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x01f3:
        r0 = "code";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r2 = r8.e;	 Catch:{ Exception -> 0x004a }
        r3 = new com.vungle.publisher.ub;	 Catch:{ Exception -> 0x004a }
        r3.<init>(r0);	 Catch:{ Exception -> 0x004a }
        r2.a(r3);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x0207:
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.as;	 Catch:{ Exception -> 0x004a }
        r2.<init>();	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x0213:
        r0 = "selector";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x0231 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0231 }
        r2 = "UTF-8";
        r0 = java.net.URLDecoder.decode(r0, r2);	 Catch:{ Exception -> 0x0231 }
        r2 = r8.h;	 Catch:{ Exception -> 0x0231 }
        r3 = new com.vungle.publisher.sx$1;	 Catch:{ Exception -> 0x0231 }
        r3.<init>(r8, r9, r0);	 Catch:{ Exception -> 0x0231 }
        r0 = r2.a;	 Catch:{ Exception -> 0x0231 }
        r6 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r0.postDelayed(r3, r6);	 Catch:{ Exception -> 0x0231 }
        goto L_0x00ba;
    L_0x0231:
        r0 = move-exception;
        r2 = r0;
        r3 = r8.d;	 Catch:{ Exception -> 0x004a }
        r5 = "VungleAd";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004a }
        r0 = "invalid mraid video selector: ";
        r6.<init>(r0);	 Catch:{ Exception -> 0x004a }
        r0 = "selector";
        r0 = r4.get(r0);	 Catch:{ Exception -> 0x004a }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x004a }
        r0 = r6.append(r0);	 Catch:{ Exception -> 0x004a }
        r0 = r0.toString();	 Catch:{ Exception -> 0x004a }
        r3.a(r5, r0, r2);	 Catch:{ Exception -> 0x004a }
        r0 = r8.e;	 Catch:{ Exception -> 0x004a }
        r2 = new com.vungle.publisher.uc;	 Catch:{ Exception -> 0x004a }
        r2.<init>();	 Catch:{ Exception -> 0x004a }
        r0.a(r2);	 Catch:{ Exception -> 0x004a }
        goto L_0x00ba;
    L_0x025d:
        r0 = r8.a(r2);
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.sx.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }

    private boolean a(Uri uri) {
        if (tk.a.contains(uri.getScheme())) {
            return false;
        }
        Logger.d(Logger.AD_TAG, "navigating to external location: " + uri.toString());
        this.e.a(new ud(uri));
        return true;
    }

    private static hn a(Map<String, String> map) {
        return new hn((String) map.get(TapjoyConstants.TJC_SDK_TYPE_DEFAULT));
    }
}
