package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.e.a.a.a.b;
import com.e.a.a.a.c;
import com.e.a.a.a.d;
import com.e.a.a.a.e;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.cr;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.MraidView.PLACEMENT_TYPE;
import com.unity3d.ads.adunit.AdUnitActivity;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge {
    private TJWebViewJSInterface a;
    public boolean allowRedirect;
    private TJAdUnitJSBridge b;
    private Context c;
    public boolean closeRequested;
    public boolean customClose;
    private TJAdUnit d;
    public boolean didLaunchOtherActivity;
    private WebView e;
    private ProgressDialog f;
    private LocationManager g;
    private LocationListener h;
    private View i;
    private boolean j;
    private d k;
    private HashMap l;
    private Handler m;
    public String otherActivityCallbackID;

    public interface AdUnitAsyncTaskListner {
        void onComplete(boolean z);
    }

    @TargetApi(11)
    class a extends AsyncTask {
        WebView a;
        final /* synthetic */ TJAdUnitJSBridge b;

        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return (Boolean[]) objArr;
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Boolean[] boolArr = (Boolean[]) obj;
            final boolean booleanValue = boolArr[0].booleanValue();
            final boolean booleanValue2 = boolArr[1].booleanValue();
            if (this.b.c instanceof Activity) {
                ((Activity) this.b.c).runOnUiThread(new Runnable(this) {
                    final /* synthetic */ a c;

                    public final void run() {
                        if (booleanValue) {
                            this.c.a.setVisibility(0);
                            if (booleanValue2) {
                                if (this.c.a.getParent() instanceof RelativeLayout) {
                                    ((RelativeLayout) this.c.a.getParent()).getBackground().setAlpha(0);
                                    ((RelativeLayout) this.c.a.getParent()).setBackgroundColor(0);
                                }
                                if (VERSION.SDK_INT >= 11) {
                                    this.c.a.setLayerType(1, null);
                                    return;
                                }
                                return;
                            }
                            if (this.c.a.getParent() instanceof RelativeLayout) {
                                ((RelativeLayout) this.c.a.getParent()).getBackground().setAlpha(255);
                                ((RelativeLayout) this.c.a.getParent()).setBackgroundColor(-1);
                            }
                            if (VERSION.SDK_INT >= 11) {
                                this.c.a.setLayerType(0, null);
                                return;
                            }
                            return;
                        }
                        this.c.a.setVisibility(4);
                        if (this.c.a.getParent() instanceof RelativeLayout) {
                            ((RelativeLayout) this.c.a.getParent()).getBackground().setAlpha(0);
                            ((RelativeLayout) this.c.a.getParent()).setBackgroundColor(0);
                        }
                    }
                });
            } else {
                TapjoyLog.e("TJAdUnitJSBridge", "Unable to present offerwall. No Activity context provided.");
            }
        }

        public a(TJAdUnitJSBridge tJAdUnitJSBridge, WebView webView) {
            this.b = tJAdUnitJSBridge;
            this.a = webView;
        }
    }

    public TJAdUnitJSBridge(Context context, TJAdUnit tJAdUnit) {
        this(context, tJAdUnit.getWebView());
        this.d = tJAdUnit;
    }

    public TJAdUnitJSBridge(Context context, WebView webView) {
        this.i = null;
        this.didLaunchOtherActivity = false;
        this.allowRedirect = true;
        this.otherActivityCallbackID = null;
        this.customClose = false;
        this.closeRequested = false;
        TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
        this.c = context;
        this.e = webView;
        this.b = this;
        if (this.e == null) {
            TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
            return;
        }
        this.a = new TJWebViewJSInterface(this.e, new TJWebViewJSInterfaceListener(this) {
            final /* synthetic */ TJAdUnitJSBridge a;

            {
                this.a = r1;
            }

            public final void onDispatchMethod(String str, JSONObject jSONObject) {
                if (this.a.j) {
                    String string;
                    String str2 = null;
                    try {
                        string = jSONObject.getString(String.CALLBACK_ID);
                    } catch (Exception e) {
                        string = str2;
                    }
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(String.DATA);
                        Method method = TJAdUnitJSBridge.class.getMethod(str, new Class[]{JSONObject.class, String.class});
                        TapjoyLog.d("TJAdUnitJSBridge", "Dispatching method with method name=" + method + ";data=" + jSONObject2 + ";callbackID=" + string);
                        method.invoke(this.a.b, new Object[]{jSONObject2, string});
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.a.invokeJSCallback(string, Boolean.FALSE);
                    }
                }
            }
        });
        this.e.addJavascriptInterface(this.a, TJAdUnitConstants.JAVASCRIPT_INTERFACE_ID);
        this.j = true;
    }

    public void alert(JSONObject jSONObject, final String str) {
        CharSequence charSequence;
        JSONArray jSONArray;
        Exception e;
        JSONArray jSONArray2;
        Object obj;
        AlertDialog create;
        TapjoyLog.d("TJAdUnitJSBridge", "alert_method: " + jSONObject);
        CharSequence charSequence2 = BuildConfig.FLAVOR;
        String str2 = BuildConfig.FLAVOR;
        JSONArray jSONArray3 = null;
        String string;
        try {
            charSequence2 = jSONObject.getString(String.TITLE);
            string = jSONObject.getString(String.MESSAGE);
            try {
                charSequence = string;
                jSONArray = jSONObject.getJSONArray(String.BUTTONS);
            } catch (Exception e2) {
                e = e2;
                invokeJSCallback(str, Boolean.FALSE);
                e.printStackTrace();
                jSONArray2 = jSONArray3;
                obj = string;
                jSONArray = jSONArray2;
                create = new Builder(this.c).setTitle(charSequence2).setMessage(charSequence).create();
                if (jSONArray != null) {
                }
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            string = str2;
            e = exception;
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
            jSONArray2 = jSONArray3;
            obj = string;
            jSONArray = jSONArray2;
            create = new Builder(this.c).setTitle(charSequence2).setMessage(charSequence).create();
            if (jSONArray != null) {
            }
            invokeJSCallback(str, Boolean.FALSE);
            return;
        }
        create = new Builder(this.c).setTitle(charSequence2).setMessage(charSequence).create();
        if (jSONArray != null || jSONArray.length() == 0) {
            invokeJSCallback(str, Boolean.FALSE);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2;
            switch (i) {
                case TwitterResponse.NONE /*0*/:
                    i2 = -2;
                    break;
                case TwitterResponse.READ /*1*/:
                    i2 = -3;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            create.setButton(i2, (CharSequence) arrayList.get(i), new OnClickListener(this) {
                final /* synthetic */ TJAdUnitJSBridge b;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    int i2 = 0;
                    switch (i) {
                        case -3:
                            i2 = 1;
                            break;
                        case Constants.PREF_TESTMODE_NOSETTING /*-1*/:
                            i2 = 2;
                            break;
                    }
                    try {
                        this.b.invokeJSCallback(str, Integer.valueOf(i2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        create.setCancelable(false);
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    public void checkAppInstalled(JSONObject jSONObject, String str) {
        String str2 = BuildConfig.FLAVOR;
        try {
            str2 = jSONObject.getString(String.BUNDLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str2 != null && str2.length() > 0) {
            for (ApplicationInfo applicationInfo : this.c.getPackageManager().getInstalledApplications(0)) {
                if (applicationInfo.packageName.equals(str2)) {
                    invokeJSCallback(str, Boolean.TRUE);
                    return;
                }
            }
        }
        invokeJSCallback(str, Boolean.FALSE);
    }

    public void getInstalledAppData(JSONObject jSONObject, String str) {
        PackageManager packageManager = this.c.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        JSONArray jSONArray = new JSONArray();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if ((applicationInfo.flags & 1) != 1) {
                Map hashMap = new HashMap();
                String str2 = applicationInfo.packageName;
                hashMap.put("packageName", str2);
                hashMap.put("targetSdk", Integer.valueOf(applicationInfo.targetSdkVersion));
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(str2, 4096);
                    hashMap.put("installTime", new Date(packageInfo.firstInstallTime));
                    hashMap.put("updateTime", new Date(packageInfo.lastUpdateTime));
                    hashMap.put("versionName", packageInfo.versionName);
                    hashMap.put("versionCode", Integer.valueOf(packageInfo.versionCode));
                    jSONArray.put(new JSONObject(hashMap));
                } catch (Exception e) {
                }
            }
        }
        invokeJSCallback(str, jSONArray);
    }

    public void closeRequested(Boolean bool) {
        this.closeRequested = true;
        Map hashMap = new HashMap();
        hashMap.put(String.FORCE_CLOSE, bool);
        invokeJSAdunitMethod(String.CLOSE_REQUESTED, hashMap);
    }

    public void getVolume(JSONObject jSONObject, String str) {
        Map volumeArgs = getVolumeArgs();
        if (volumeArgs != null) {
            invokeJSCallback(str, volumeArgs);
            return;
        }
        invokeJSCallback(str, Boolean.valueOf(false));
    }

    public void onVolumeChanged() {
        invokeJSAdunitMethod(String.VOLUME_CHANGED, getVolumeArgs());
    }

    public HashMap getVolumeArgs() {
        if (this.d == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            return null;
        }
        String volume = this.d.getVolume();
        boolean isMuted = this.d.isMuted();
        TapjoyLog.d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + volume + "; isMuted=" + isMuted);
        HashMap hashMap = new HashMap();
        hashMap.put(String.CURRENT_VOLUME, volume);
        hashMap.put(String.IS_MUTED, Boolean.valueOf(isMuted));
        return hashMap;
    }

    public void destroy() {
        if (this.h != null && this.g != null) {
            this.g.removeUpdates(this.h);
            this.g = null;
            this.h = null;
        }
    }

    public void dismiss(JSONObject jSONObject, String str) {
        if (this.c instanceof TJAdUnitActivity) {
            invokeJSCallback(str, Boolean.valueOf(true));
            ((Activity) this.c).finish();
            return;
        }
        invokeJSCallback(str, Boolean.valueOf(false));
    }

    public void display() {
        invokeJSAdunitMethod(String.DISPLAY, new Object[0]);
    }

    public void displayRichMedia(final JSONObject jSONObject, String str) {
        boolean z;
        String string;
        try {
            z = jSONObject.getBoolean(String.INLINE);
        } catch (Exception e) {
            z = false;
        }
        try {
            string = jSONObject.getString(ApiAccessUtil.WEBAPI_KEY_HTML);
        } catch (Exception e2) {
            e2.printStackTrace();
            string = null;
        }
        if (string == null) {
            invokeJSCallback(str, Boolean.FALSE);
        } else if (z) {
            ((Activity) this.c).runOnUiThread(new Runnable(this) {
                final /* synthetic */ TJAdUnitJSBridge b;

                public final void run() {
                    String string;
                    try {
                        string = jSONObject.getString(ApiAccessUtil.WEBAPI_KEY_HTML);
                    } catch (Exception e) {
                        e.printStackTrace();
                        string = null;
                    }
                    if (!(this.b.i == null || this.b.i.getParent() == null)) {
                        ((ViewGroup) this.b.i.getParent()).removeView(this.b.i);
                    }
                    View mraidView = new MraidView(this.b.c);
                    this.b.e.getSettings().setJavaScriptEnabled(true);
                    mraidView.setPlacementType(PLACEMENT_TYPE.INLINE);
                    mraidView.setLayoutParams(new LayoutParams(640, 100));
                    mraidView.setInitialScale(100);
                    mraidView.setBackgroundColor(0);
                    mraidView.loadDataWithBaseURL(null, string, "text/html", "utf-8", null);
                    int width = ((WindowManager) ((Activity) this.b.c).getSystemService("window")).getDefaultDisplay().getWidth();
                    this.b.i = TapjoyUtil.scaleDisplayAd(mraidView, width);
                    ((Activity) this.b.c).addContentView(this.b.i, new LayoutParams(width, (int) (100.0d * (((double) width) / 640.0d))));
                }
            });
        } else {
            Serializable tJPlacementData = new TJPlacementData(TapjoyConnectCore.getHostURL(), string, str);
            Intent intent = new Intent(this.c, TJAdUnitActivity.class);
            intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJPlacementData);
            ((Activity) this.c).startActivityForResult(intent, TJAdUnitConstants.MRAID_REQUEST_CODE);
        }
    }

    public void displayStoreURL(JSONObject jSONObject, String str) {
        displayURL(jSONObject, str);
    }

    public void displayURL(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString(String.URL);
            this.didLaunchOtherActivity = true;
            this.otherActivityCallbackID = str;
            ((Activity) this.c).startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void clearCache(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            TapjoyCache.getInstance().clearTapjoyCache();
            invokeJSCallback(str, Boolean.TRUE);
            return;
        }
        invokeJSCallback(str, Boolean.FALSE);
    }

    public void setPrerenderLimit(JSONObject jSONObject, String str) {
        try {
            TJPlacementManager.setPreRenderedPlacementLimit(jSONObject.getInt(String.TJC_PLACEMENT_PRE_RENDERED_LIMIT));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void setEventPreloadLimit(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            try {
                TJPlacementManager.setCachedPlacementLimit(jSONObject.getInt(String.TJC_PLACEMENT_CACHE_LIMIT));
                invokeJSCallback(str, Boolean.TRUE);
                return;
            } catch (Exception e) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
        }
        invokeJSCallback(str, Boolean.FALSE);
    }

    public void removeAssetFromCache(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString(String.URL);
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(string)));
                return;
            }
            invokeJSCallback(str, Boolean.FALSE);
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void cacheAsset(JSONObject jSONObject, String str) {
        String str2 = BuildConfig.FLAVOR;
        Long valueOf = Long.valueOf(0);
        try {
            String string = jSONObject.getString(String.URL);
            try {
                str2 = jSONObject.getString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID);
            } catch (Exception e) {
            }
            try {
                valueOf = Long.valueOf(jSONObject.getLong(TapjoyConstants.TJC_TIME_TO_LIVE));
            } catch (Exception e2) {
            }
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().cacheAssetFromURL(string, str2, valueOf.longValue()));
                return;
            }
            invokeJSCallback(str, Boolean.FALSE);
        } catch (Exception e3) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(str, Boolean.FALSE);
        }
    }

    public void cachePathForURL(JSONObject jSONObject, String str) {
        try {
            String string = jSONObject.getString(String.URL);
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(str, TapjoyCache.getInstance().getPathOfCachedURL(string));
                return;
            }
            invokeJSCallback(str, BuildConfig.FLAVOR);
        } catch (Exception e) {
            invokeJSCallback(str, BuildConfig.FLAVOR);
        }
    }

    public void getCachedAssets(JSONObject jSONObject, String str) {
        if (TapjoyCache.getInstance() != null) {
            invokeJSCallback(str, TapjoyCache.getInstance().cachedAssetsToJSON());
            return;
        }
        invokeJSCallback(str, BuildConfig.FLAVOR);
    }

    public void contentReady(JSONObject jSONObject, String str) {
        if (this.d != null) {
            this.d.fireContentReady();
            invokeJSCallback(str, Boolean.valueOf(true));
            return;
        }
        invokeJSCallback(str, Boolean.valueOf(false));
    }

    public void setOrientation(JSONObject jSONObject, String str) {
        if (this.d == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(str, Boolean.valueOf(false));
            return;
        }
        try {
            String string = jSONObject.getString(AdUnitActivity.EXTRA_ORIENTATION);
            int i = (string.equals(String.LANDSCAPE) || string.equals(String.LANDSCAPE_LEFT)) ? 0 : string.equals(String.LANDSCAPE_RIGHT) ? 8 : 1;
            this.d.setOrientation(i);
            invokeJSCallback(str, Boolean.valueOf(true));
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.valueOf(false));
        }
    }

    public void setBackgroundColor(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString(String.BACKGROUND_COLOR);
            if (this.d != null) {
                this.d.setBackgroundColor(string, new AdUnitAsyncTaskListner(this) {
                    final /* synthetic */ TJAdUnitJSBridge b;

                    public final void onComplete(boolean z) {
                        this.b.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
                return;
            }
            invokeJSCallback(str, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
            invokeJSCallback(str, Boolean.valueOf(false));
        }
    }

    public void setBackgroundWebViewContent(JSONObject jSONObject, final String str) {
        TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
        try {
            String string = jSONObject.getString(String.BACKGROUND_CONTENT);
            if (this.d != null) {
                this.d.setBackgroundContent(string, new AdUnitAsyncTaskListner(this) {
                    final /* synthetic */ TJAdUnitJSBridge b;

                    public final void onComplete(boolean z) {
                        this.b.invokeJSCallback(str, Boolean.valueOf(z));
                    }
                });
                return;
            }
            invokeJSCallback(str, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
            invokeJSCallback(str, Boolean.valueOf(false));
        }
    }

    public void displayVideo(JSONObject jSONObject, final String str) {
        try {
            String string = jSONObject.getString(String.URL);
            if (string.length() <= 0 || string == BuildConfig.FLAVOR) {
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
            this.d.loadVideoUrl(string, new AdUnitAsyncTaskListner(this) {
                final /* synthetic */ TJAdUnitJSBridge b;

                public final void onComplete(boolean z) {
                    this.b.invokeJSCallback(str, Boolean.valueOf(z));
                }
            });
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void playVideo(JSONObject jSONObject, String str) {
        if (this.d != null) {
            invokeJSCallback(str, Boolean.valueOf(this.d.playVideo()));
        }
    }

    public void pauseVideo(JSONObject jSONObject, String str) {
        if (this.d != null) {
            invokeJSCallback(str, Boolean.valueOf(this.d.pauseVideo()));
        }
    }

    public void clearVideo(JSONObject jSONObject, final String str) {
        if (this.d != null) {
            this.d.clearVideo(new AdUnitAsyncTaskListner(this) {
                final /* synthetic */ TJAdUnitJSBridge b;

                public final void onComplete(boolean z) {
                    this.b.invokeJSCallback(str, Boolean.valueOf(z));
                }
            });
        }
    }

    public void getLocation(JSONObject jSONObject, String str) {
        boolean booleanValue;
        float f = 100.0f;
        try {
            f = Float.valueOf(jSONObject.getString(String.GPS_ACCURACY)).floatValue();
        } catch (Exception e) {
        }
        try {
            booleanValue = Boolean.valueOf(jSONObject.getString(String.ENABLED)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            booleanValue = false;
        }
        this.g = (LocationManager) this.c.getSystemService("location");
        String bestProvider = this.g.getBestProvider(new Criteria(), false);
        if (this.h == null) {
            this.h = new LocationListener(this) {
                final /* synthetic */ TJAdUnitJSBridge a;

                {
                    this.a = r1;
                }

                public final void onLocationChanged(Location location) {
                    if (this.a.c == null || this.a.e == null) {
                        if (this.a.g != null && this.a.h != null) {
                            TapjoyLog.d("TJAdUnitJSBridge", "stopping updates");
                            this.a.g.removeUpdates(this.a.h);
                        }
                    } else if (location != null) {
                        Map hashMap = new HashMap();
                        hashMap.put(String.LAT, Double.valueOf(location.getLatitude()));
                        hashMap.put(String.LONG, Double.valueOf(location.getLongitude()));
                        hashMap.put(String.ALTITUDE, Double.valueOf(location.getAltitude()));
                        hashMap.put(TapjoyConstants.TJC_TIMESTAMP, Long.valueOf(location.getTime()));
                        hashMap.put(String.SPEED, Float.valueOf(location.getSpeed()));
                        hashMap.put(String.COURSE, Float.valueOf(location.getBearing()));
                        this.a.invokeJSAdunitMethod(String.LOCATION_UPDATED, hashMap);
                    }
                }

                public final void onProviderEnabled(String str) {
                }

                public final void onProviderDisabled(String str) {
                }

                public final void onStatusChanged(String str, int i, Bundle bundle) {
                }
            };
        }
        if (booleanValue) {
            if (bestProvider != null) {
                this.g.requestLocationUpdates(bestProvider, 0, f, this.h);
            } else {
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
        } else if (!(this.g == null || this.h == null)) {
            this.g.removeUpdates(this.h);
        }
        invokeJSCallback(str, Boolean.TRUE);
    }

    public void log(JSONObject jSONObject, String str) {
        try {
            TapjoyLog.d("TJAdUnitJSBridge", "Logging message=" + jSONObject.getString(String.MESSAGE));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void openApp(JSONObject jSONObject, String str) {
        try {
            this.c.startActivity(this.c.getPackageManager().getLaunchIntentForPackage(jSONObject.getString(String.BUNDLE)));
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(str, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    @TargetApi(19)
    public void nativeEval(final JSONObject jSONObject, final String str) {
        ((Activity) this.c).runOnUiThread(new Runnable(this) {
            final /* synthetic */ TJAdUnitJSBridge c;

            public final void run() {
                try {
                    if (VERSION.SDK_INT >= 19) {
                        this.c.e.evaluateJavascript(jSONObject.getString(String.COMMAND), null);
                    } else {
                        this.c.e.loadUrl("javascript:" + jSONObject.getString(String.COMMAND));
                    }
                    this.c.invokeJSCallback(str, Boolean.TRUE);
                } catch (Exception e) {
                    this.c.invokeJSCallback(str, Boolean.FALSE);
                }
            }
        });
    }

    public void present(JSONObject jSONObject, String str) {
        try {
            Boolean.valueOf(false);
            Boolean valueOf = Boolean.valueOf(false);
            Boolean valueOf2 = Boolean.valueOf(jSONObject.getString(String.VISIBLE));
            try {
                valueOf = Boolean.valueOf(jSONObject.getString(String.TRANSPARENT));
            } catch (Exception e) {
            }
            try {
                this.customClose = Boolean.valueOf(jSONObject.getString(String.CUSTOM_CLOSE)).booleanValue();
            } catch (Exception e2) {
            }
            new a(this, this.e).execute(new Boolean[]{valueOf2, valueOf});
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e3) {
            invokeJSCallback(str, Boolean.FALSE);
            e3.printStackTrace();
        }
    }

    public void triggerEvent(JSONObject jSONObject, String str) {
        if (this.d != null) {
            try {
                String string = jSONObject.getString(String.TRIGGERED_EVENT_NAME);
                if (string.equals(String.VIDEO_START)) {
                    this.d.fireOnVideoStart();
                } else if (string.equals(String.VIDEO_COMPLETE)) {
                    this.d.fireOnVideoComplete();
                } else if (string.equals(String.VIDEO_ERROR)) {
                    this.d.fireOnVideoError("Error while trying to play video.");
                }
            } catch (Exception e) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
            }
        }
    }

    public void invokeJSAdunitMethod(String str, Object... objArr) {
        this.a.callback(new ArrayList(Arrays.asList(objArr)), str, null);
    }

    public void invokeJSAdunitMethod(String str, Map map) {
        this.a.callback(map, str, null);
    }

    public void invokeJSCallback(String str, Object... objArr) {
        if (cr.c(str)) {
            TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
            return;
        }
        this.a.callback(new ArrayList(Arrays.asList(objArr)), BuildConfig.FLAVOR, str);
    }

    public void invokeJSCallback(String str, Map map) {
        this.a.callback(map, BuildConfig.FLAVOR, str);
    }

    public void flushMessageQueue() {
        this.a.flushMessageQueue();
    }

    public void setAllowRedirect(JSONObject jSONObject, String str) {
        boolean z;
        try {
            z = jSONObject.getBoolean(String.ENABLED);
        } catch (Exception e) {
            z = true;
        }
        this.allowRedirect = z;
        invokeJSCallback(str, Boolean.TRUE);
    }

    public void setContext(Context context) {
        this.c = context;
    }

    public void setSpinnerVisible(JSONObject jSONObject, String str) {
        String str2 = TJAdUnitConstants.SPINNER_TITLE;
        CharSequence charSequence = BuildConfig.FLAVOR;
        try {
            CharSequence string;
            boolean z = jSONObject.getBoolean(String.VISIBLE);
            try {
                str2 = jSONObject.getString(String.TITLE);
                charSequence = str2;
                string = jSONObject.getString(String.MESSAGE);
            } catch (Exception e) {
                CharSequence charSequence2 = charSequence;
                Object obj = str2;
                string = charSequence2;
            }
            if (z) {
                this.f = ProgressDialog.show(this.c, charSequence, string);
            } else if (this.f != null) {
                this.f.dismiss();
            }
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e2) {
            invokeJSCallback(str, Boolean.FALSE);
            e2.printStackTrace();
        }
    }

    public void setCloseButtonVisible(JSONObject jSONObject, String str) {
        if (this.c instanceof TJAdUnitActivity) {
            try {
                final boolean z = jSONObject.getBoolean(String.VISIBLE);
                ((Activity) this.c).runOnUiThread(new Runnable(this) {
                    final /* synthetic */ TJAdUnitJSBridge b;

                    public final void run() {
                        ((TJAdUnitActivity) this.b.c).setCloseButtonVisibility(z);
                    }
                });
                invokeJSCallback(str, Boolean.valueOf(true));
                return;
            } catch (Exception e) {
                invokeJSCallback(str, Boolean.valueOf(false));
                e.printStackTrace();
                return;
            }
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Not a TJAdUnitActivity");
        invokeJSCallback(str, Boolean.valueOf(false));
    }

    public void shouldClose(JSONObject jSONObject, String str) {
        try {
            Boolean.valueOf(false);
            if (Boolean.valueOf(jSONObject.getString(String.CLOSE)).booleanValue() && (this.c instanceof Activity)) {
                ((Activity) this.c).finish();
            }
            invokeJSCallback(str, Boolean.TRUE);
        } catch (Exception e) {
            Exception exception = e;
            invokeJSCallback(str, Boolean.FALSE);
            ((Activity) this.c).finish();
            exception.printStackTrace();
        }
        this.closeRequested = false;
    }

    public void setLoggingLevel(JSONObject jSONObject, String str) {
        try {
            TapjoyAppSettings.getInstance().saveLoggingLevel(String.valueOf(jSONObject.getString(String.LOGGING_LEVEL)));
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "setLoggingLevel exception " + e.getLocalizedMessage());
            invokeJSCallback(str, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void clearLoggingLevel(JSONObject jSONObject, String str) {
        TapjoyAppSettings.getInstance().clearLoggingLevel();
    }

    public void attachVolumeListener(JSONObject jSONObject, String str) {
        try {
            boolean z = jSONObject.getBoolean(String.ATTACH);
            int optInt = jSONObject.optInt(String.INTERVAL, HttpResponseCode.INTERNAL_SERVER_ERROR);
            if (optInt > 0) {
                this.d.attachVolumeListener(z, optInt);
                invokeJSCallback(str, Boolean.valueOf(true));
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + optInt);
            invokeJSCallback(str, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "attachVolumeListener exception " + e.toString());
            invokeJSCallback(str, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void initMoatVideoTracker(JSONObject jSONObject, String str) {
        if (this.c instanceof TJAdUnitActivity) {
            try {
                this.k = (d) c.a((TJAdUnitActivity) this.c).a(new e(jSONObject.getString(String.PARTNER_CODE)));
                if (this.l == null) {
                    TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
                    this.l = new HashMap();
                    this.l.put(String.VIDEO_FIRST_QUARTILE, b.AD_EVT_FIRST_QUARTILE);
                    this.l.put(String.VIDEO_MIDPOINT, b.AD_EVT_MID_POINT);
                    this.l.put(String.VIDEO_THIRD_QUARTILE, b.AD_EVT_THIRD_QUARTILE);
                    this.l.put(String.VIDEO_COMPLETE, b.AD_EVT_COMPLETE);
                    this.l.put(String.VIDEO_PAUSED, b.AD_EVT_PAUSED);
                    this.l.put(String.VIDEO_PLAYING, b.AD_EVT_PLAYING);
                    this.l.put(String.VIDEO_START, b.AD_EVT_START);
                    this.l.put(String.VIDEO_STOPPED, b.AD_EVT_STOPPED);
                    this.l.put(String.VIDEO_SKIPPED, b.AD_EVT_SKIPPED);
                    this.l.put(String.VOLUME_CHANGED, b.AD_EVT_VOLUME_CHANGE);
                    this.l.put(String.ENTER_FULL_SCREEN, b.AD_EVT_ENTER_FULLSCREEN);
                    this.l.put(String.EXIT_FULL_SCREEN, b.AD_EVT_EXIT_FULLSCREEN);
                }
                this.m = new Handler(Looper.getMainLooper());
                invokeJSCallback(str, Boolean.valueOf(true));
                return;
            } catch (Exception e) {
                TapjoyLog.d("TJAdUnitJSBridge", "initMoatVideoTracker exception " + e.toString());
                invokeJSCallback(str, Boolean.valueOf(false));
                return;
            }
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- not a TJAdUnitActivity");
        invokeJSCallback(str, Boolean.valueOf(false));
    }

    public void startMoatVideoTracker(JSONObject jSONObject, final String str) {
        try {
            final Integer valueOf = Integer.valueOf(jSONObject.getInt(String.VIDEO_LENGTH));
            final Map hashMap = new HashMap();
            JSONObject jSONObject2 = jSONObject.getJSONObject(String.AD_IDS);
            if (jSONObject2 != null) {
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    hashMap.put(str2, jSONObject2.getString(str2));
                }
            }
            this.m.post(new Runnable(this) {
                final /* synthetic */ TJAdUnitJSBridge d;

                public final void run() {
                    boolean a;
                    if (this.d.k != null) {
                        a = this.d.k.a(hashMap, valueOf, this.d.e);
                    } else {
                        a = false;
                    }
                    this.d.invokeJSCallback(str, Boolean.valueOf(a));
                }
            });
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "startMoatVideoTracker exception " + e.toString());
            invokeJSCallback(str, Boolean.valueOf(false));
        }
    }

    public void triggerMoatVideoEvent(JSONObject jSONObject, String str) {
        try {
            Integer valueOf = Integer.valueOf(jSONObject.getInt(String.CURRENT_VIDEO_TIME));
            String string = jSONObject.getString(String.TRIGGERED_EVENT_NAME);
            b bVar = null;
            if (this.l != null) {
                bVar = (b) this.l.get(string);
            }
            if (bVar == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "eventName:" + string + " has no matching MOAT event");
                invokeJSCallback(str, Boolean.valueOf(false));
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Sending MOAT event: " + bVar);
            final com.e.a.a.a.a aVar = new com.e.a.a.a.a(bVar, valueOf);
            this.m.post(new Runnable(this) {
                final /* synthetic */ TJAdUnitJSBridge b;

                public final void run() {
                    if (this.b.k != null) {
                        this.b.k.a(aVar);
                    }
                }
            });
            invokeJSCallback(str, Boolean.valueOf(true));
        } catch (Exception e) {
            TapjoyLog.d("TJAdUnitJSBridge", "triggerMoatVideoEvent exception " + e.toString());
            invokeJSCallback(str, Boolean.valueOf(false));
        }
    }

    public void disable() {
        this.j = false;
    }

    public void onVideoReady(int i, int i2, int i3) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_READY_EVENT);
        hashMap.put(String.VIDEO_DURATION, Integer.valueOf(i));
        hashMap.put(String.VIDEO_WIDTH, Integer.valueOf(i2));
        hashMap.put(String.VIDEO_HEIGHT, Integer.valueOf(i3));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoStarted(int i) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_START_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoProgress(int i) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_PROGRESS_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoPaused(int i) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_PAUSE_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(i));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoCompletion() {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_COMPLETE_EVENT);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoInfo(String str) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_INFO_EVENT);
        hashMap.put(String.VIDEO_INFO, str);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoError(String str) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_ERROR_EVENT);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }
}
