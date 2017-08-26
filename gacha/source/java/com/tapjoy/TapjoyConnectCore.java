package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.br;
import com.tapjoy.internal.cr;
import com.tapjoy.internal.da;
import com.tapjoy.internal.el;
import com.tapjoy.internal.eo;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.y;
import com.tapjoy.mediation.TJMediationNetwork;
import com.tapjoy.mediation.TJMediationSettings;
import com.unity3d.ads.adunit.AdUnitActivity;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;
import org.w3c.dom.Document;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class TapjoyConnectCore {
    private static int A = 1;
    private static float B = DEFAULT_CURRENCY_MULTIPLIER;
    private static int C = 1;
    private static String D = BuildConfig.FLAVOR;
    public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0f;
    private static boolean E = false;
    private static String F = BuildConfig.FLAVOR;
    private static String G = BuildConfig.FLAVOR;
    private static String H = BuildConfig.FLAVOR;
    private static String I = BuildConfig.FLAVOR;
    private static String J = BuildConfig.FLAVOR;
    private static String K = BuildConfig.FLAVOR;
    private static String L = BuildConfig.FLAVOR;
    private static String M = BuildConfig.FLAVOR;
    private static String N = BuildConfig.FLAVOR;
    private static String O = BuildConfig.FLAVOR;
    private static String P = TapjoyConstants.TJC_PLUGIN_NATIVE;
    private static String Q = BuildConfig.FLAVOR;
    private static String R = BuildConfig.FLAVOR;
    private static float S = DEFAULT_CURRENCY_MULTIPLIER;
    private static boolean T = false;
    private static String U = BuildConfig.FLAVOR;
    private static String V = BuildConfig.FLAVOR;
    private static String W = BuildConfig.FLAVOR;
    private static String X = BuildConfig.FLAVOR;
    private static String Y = null;
    protected static int a = 0;
    private static Set aA;
    private static int aB;
    private static int aC;
    private static int aD;
    private static long aE;
    private static long aF;
    private static long aG;
    private static String aH;
    private static int aI;
    private static double aJ;
    private static double aK;
    private static long aL;
    private static int aM;
    private static int aN;
    private static int aO;
    private static String aP;
    private static String aQ;
    private static String aR;
    private static long ac = 0;
    private static boolean ae;
    private static PackageManager af;
    private static Hashtable ah = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    private static String ai = BuildConfig.FLAVOR;
    private static Map aj = new ConcurrentHashMap();
    private static String ak;
    private static String al;
    private static String am;
    private static String an;
    private static int ao;
    private static String ap;
    private static String aq;
    private static long ar;
    private static String as;
    private static int at;
    private static int au;
    private static String av;
    private static String aw;
    private static String ax;
    private static String ay;
    private static String az;
    protected static int b = 0;
    protected static String c = BuildConfig.FLAVOR;
    protected static boolean d;
    protected static String e = BuildConfig.FLAVOR;
    protected static String f = BuildConfig.FLAVOR;
    private static Context g = null;
    private static String h = null;
    private static TapjoyConnectCore i = null;
    private static TapjoyURLConnection j = null;
    private static TJConnectListener k = null;
    private static TJSetUserIDListener l = null;
    private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
    private static String n = BuildConfig.FLAVOR;
    private static String o = BuildConfig.FLAVOR;
    private static String p = BuildConfig.FLAVOR;
    private static String q = BuildConfig.FLAVOR;
    private static String r = BuildConfig.FLAVOR;
    private static String s = BuildConfig.FLAVOR;
    private static String t = BuildConfig.FLAVOR;
    private static String u = BuildConfig.FLAVOR;
    private static String v = BuildConfig.FLAVOR;
    private static String w = BuildConfig.FLAVOR;
    private static String x = BuildConfig.FLAVOR;
    private static String y = BuildConfig.FLAVOR;
    private static String z = BuildConfig.FLAVOR;
    private long Z = 0;
    private Timer aa = null;
    private boolean ab = false;
    private boolean ad = false;
    private TapjoyGpsHelper ag;

    public class PPAThread implements Runnable {
        final /* synthetic */ TapjoyConnectCore a;
        private Map b;

        public PPAThread(TapjoyConnectCore tapjoyConnectCore, Map map) {
            this.a = tapjoyConnectCore;
            this.b = map;
        }

        public void run() {
            TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, this.b);
            if (responseFromURL.response != null) {
                TapjoyConnectCore.c(responseFromURL.response);
            }
        }
    }

    class a extends TimerTask {
        final /* synthetic */ TapjoyConnectCore a;

        private a(TapjoyConnectCore tapjoyConnectCore) {
            this.a = tapjoyConnectCore;
        }

        public final void run() {
            TapjoyConnectCore.b(this.a);
            TapjoyLog.d("TapjoyConnect", "elapsed_time: " + this.a.Z + " (" + ((this.a.Z / 1000) / 60) + "m " + ((this.a.Z / 1000) % 60) + "s)");
            Editor edit = TapjoyConnectCore.g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
            edit.putLong(TapjoyConstants.PREF_ELAPSED_TIME, this.a.Z);
            edit.commit();
            if (this.a.Z >= TapjoyConstants.PAID_APP_TIME) {
                TapjoyLog.d("TapjoyConnect", "timer done...");
                if (TapjoyConnectCore.Y != null && TapjoyConnectCore.Y.length() > 0) {
                    TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
                    this.a.actionComplete(TapjoyConnectCore.Y);
                }
                cancel();
            }
        }
    }

    static /* synthetic */ boolean a(String str) {
        Document buildDocument = TapjoyUtil.buildDocument(str);
        if (buildDocument != null) {
            String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("PackageNames"));
            if (nodeTrimValue != null && nodeTrimValue.length() > 0) {
                List vector = new Vector();
                int i = 0;
                while (true) {
                    int indexOf = nodeTrimValue.indexOf(44, i);
                    if (indexOf == -1) {
                        break;
                    }
                    TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i, indexOf).trim());
                    vector.add(nodeTrimValue.substring(i, indexOf).trim());
                    i = indexOf + 1;
                }
                TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i).trim());
                vector.add(nodeTrimValue.substring(i).trim());
                a(vector);
            }
            String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
            if (nodeTrimValue2 == null || !nodeTrimValue2.equals(TapjoyConstants.TJC_TRUE)) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ long b(TapjoyConnectCore tapjoyConnectCore) {
        long j = tapjoyConnectCore.Z + RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS;
        tapjoyConnectCore.Z = j;
        return j;
    }

    public static TapjoyConnectCore getInstance() {
        return i;
    }

    public static void requestTapjoyConnect(Context context, String str) {
        requestTapjoyConnect(context, str, null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable) {
        requestTapjoyConnect(context, str, hashtable, null);
    }

    public static void requestTapjoyConnect(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        try {
            el elVar = new el(str);
            if (elVar.a != com.tapjoy.internal.el.a.SDK_ANDROID) {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
            h = str;
            w = elVar.b;
            N = elVar.c;
            O = elVar.d;
            fm.a(context).j = str;
            if (hashtable != null) {
                ah.putAll(hashtable);
            }
            k = tJConnectListener;
            i = new TapjoyConnectCore(context);
        } catch (IllegalArgumentException e) {
            throw new TapjoyIntegrationException(e.getMessage());
        }
    }

    public TapjoyConnectCore(Context context) {
        g = context;
        j = new TapjoyURLConnection();
        af = g.getPackageManager();
        this.ag = new TapjoyGpsHelper(g);
        try {
            String networkOperator;
            boolean z;
            if (ah == null) {
                ah = new Hashtable();
            }
            if (getConnectFlagValue(TapjoyConnectFlag.ENABLE_LOGGING) != null && getConnectFlagValue(TapjoyConnectFlag.ENABLE_LOGGING).equals(TapjoyConstants.TJC_TRUE)) {
                TapjoyLog.setDebugEnabled(true);
            }
            k();
            int identifier = g.getResources().getIdentifier("raw/tapjoy_config", null, g.getPackageName());
            Properties properties = new Properties();
            try {
                properties.load(g.getResources().openRawResource(identifier));
                a(properties);
            } catch (Exception e) {
            }
            if (getConnectFlagValue("unit_test_mode") == BuildConfig.FLAVOR) {
                l();
            }
            String string = Secure.getString(g.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            n = string;
            if (string != null) {
                n = n.toLowerCase();
            }
            x = af.getPackageInfo(g.getPackageName(), 0).versionName;
            u = RFLConstants.kRFLAPI_H1_Parameter_Platform_AND;
            F = RFLConstants.kRFLAPI_H1_Parameter_Platform_AND;
            s = Build.MODEL;
            t = Build.MANUFACTURER;
            v = VERSION.RELEASE;
            y = com.tapjoy.mraid.BuildConfig.VERSION_NAME;
            z = TapjoyConstants.TJC_BRIDGE_VERSION_NUMBER;
            try {
                if (VERSION.SDK_INT > 3) {
                    TapjoyDisplayMetricsUtil tapjoyDisplayMetricsUtil = new TapjoyDisplayMetricsUtil(g);
                    A = tapjoyDisplayMetricsUtil.getScreenDensityDPI();
                    B = tapjoyDisplayMetricsUtil.getScreenDensityScale();
                    C = tapjoyDisplayMetricsUtil.getScreenLayoutSize();
                }
            } catch (Exception e2) {
                TapjoyLog.e("TapjoyConnect", "Error getting screen density/dimensions/layout: " + e2.toString());
            }
            if (e("android.permission.ACCESS_WIFI_STATE")) {
                try {
                    WifiManager wifiManager = (WifiManager) g.getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
                    if (wifiManager != null) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            string = connectionInfo.getMacAddress();
                            q = string;
                            if (string != null) {
                                q = q.replace(":", BuildConfig.FLAVOR).toLowerCase();
                            }
                        }
                    }
                } catch (Exception e22) {
                    TapjoyLog.e("TapjoyConnect", "Error getting device mac address: " + e22.toString());
                }
            } else {
                TapjoyLog.d("TapjoyConnect", "*** ignore macAddress");
            }
            TelephonyManager telephonyManager = (TelephonyManager) g.getSystemService("phone");
            if (telephonyManager != null) {
                G = telephonyManager.getNetworkOperatorName();
                H = telephonyManager.getNetworkCountryIso();
                networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator != null && (networkOperator.length() == 5 || networkOperator.length() == 6)) {
                    I = networkOperator.substring(0, 3);
                    J = networkOperator.substring(3);
                }
                if (e("android.permission.READ_PHONE_STATE")) {
                    try {
                        if (getConnectFlagValue(TapjoyConnectFlag.DEBUG_DEVICE_ID) == null || getConnectFlagValue(TapjoyConnectFlag.DEBUG_DEVICE_ID).length() <= 0) {
                            p = telephonyManager.getDeviceId();
                        } else {
                            p = getConnectFlagValue(TapjoyConnectFlag.DEBUG_DEVICE_ID);
                        }
                        TapjoyLog.d("TapjoyConnect", "deviceID: " + p);
                        if (p == null) {
                            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Device ID is NULL"));
                            z = false;
                        } else if (p.length() == 0 || p.equals("000000000000000") || p.equals("0")) {
                            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Device ID is empty or an emulator"));
                            z = false;
                        } else {
                            p = p.toLowerCase(Locale.getDefault());
                            z = true;
                        }
                        TapjoyLog.i("TapjoyConnect", "ANDROID SDK VERSION: " + VERSION.SDK_INT);
                        if (VERSION.SDK_INT >= 9) {
                            TapjoyLog.d("TapjoyConnect", "TRYING TO GET SERIAL OF 2.3+ DEVICE...");
                            networkOperator = getSerial();
                            if (!z) {
                                p = networkOperator;
                            }
                            if (p == null) {
                                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Device serial number is NULL"));
                            } else if (p.length() == 0 || p.equals("000000000000000") || p.equals("0") || p.equals("unknown")) {
                                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Device serial number is empty or an emulator"));
                            } else {
                                p = p.toLowerCase(Locale.getDefault());
                            }
                        }
                    } catch (Exception e222) {
                        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Cannot get deviceID -- " + e222.toString()));
                        p = null;
                    }
                } else {
                    TapjoyLog.d("TapjoyConnect", "*** ignore deviceID");
                }
            }
            SharedPreferences sharedPreferences = g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
            networkOperator = sharedPreferences.getString(TapjoyConstants.PREF_INSTALL_ID, BuildConfig.FLAVOR);
            r = networkOperator;
            if (networkOperator == null || r.length() == 0) {
                try {
                    r = TapjoyUtil.SHA256(UUID.randomUUID().toString() + System.currentTimeMillis());
                    Editor edit = sharedPreferences.edit();
                    edit.putString(TapjoyConstants.PREF_INSTALL_ID, r);
                    edit.commit();
                } catch (Exception e2222) {
                    TapjoyLog.e("TapjoyConnect", "Error generating install id: " + e2222.toString());
                }
            }
            try {
                E = a("android.hardware.location", "android.permission.ACCESS_FINE_LOCATION");
            } catch (Exception e22222) {
                TapjoyLog.e("TapjoyConnect", "Error trying to detect capabilities on devicee: " + e22222.toString());
            }
            if (getConnectFlagValue(TapjoyConnectFlag.STORE_NAME) != null && getConnectFlagValue(TapjoyConnectFlag.STORE_NAME).length() > 0) {
                M = getConnectFlagValue(TapjoyConnectFlag.STORE_NAME);
                if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(M)) {
                    TapjoyLog.w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + M);
                }
            }
            try {
                string = M;
                Intent intent = new Intent("android.intent.action.VIEW");
                if (string.length() <= 0) {
                    intent.setData(Uri.parse("market://details"));
                    if (af.queryIntentActivities(intent, 0).size() > 0) {
                        z = true;
                    }
                    z = false;
                } else if (string.equals(TapjoyConnectFlag.STORE_GFAN)) {
                    z = d("com.mappn.gfan");
                } else {
                    if (string.equals(TapjoyConnectFlag.STORE_SKT)) {
                        z = d("com.skt.skaf.TSCINSTALL");
                    }
                    z = false;
                }
                T = z;
            } catch (Exception e222222) {
                TapjoyLog.e("TapjoyConnect", "Error trying to detect store intent on devicee: " + e222222.toString());
            }
            i();
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).length() > 0) {
                f = getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).length() > 0) {
                e = getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.USER_ID) != null && getConnectFlagValue(TapjoyConnectFlag.USER_ID).length() > 0) {
                TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getConnectFlagValue(TapjoyConnectFlag.USER_ID));
                setUserID(getConnectFlagValue(TapjoyConnectFlag.USER_ID), null);
            }
            R = TapjoyUtil.getRedirectDomain(getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL));
            String str = "TapjoyConnect";
            StringBuilder append = new StringBuilder("deviceID: ").append(p);
            string = (getConnectFlagValue(TapjoyConnectFlag.DEBUG_DEVICE_ID) == null || getConnectFlagValue(TapjoyConnectFlag.DEBUG_DEVICE_ID).length() <= 0) ? BuildConfig.FLAVOR : " *debug_device_id*";
            TapjoyLog.d(str, append.append(string).toString());
            if (ah != null) {
                j();
            }
            callConnect();
            this.ad = true;
        } catch (NameNotFoundException e3) {
            throw new TapjoyException(e3.getMessage());
        } catch (TapjoyIntegrationException e4) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, e4.getMessage()));
            f();
            eo.b.notifyObservers(Boolean.FALSE);
        } catch (TapjoyException e5) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, e5.getMessage()));
            f();
            eo.b.notifyObservers(Boolean.FALSE);
        }
    }

    private static void f() {
        if (!cr.c(O)) {
            fm.a().a(g, h, com.tapjoy.mraid.BuildConfig.VERSION_NAME, TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, O, N);
        }
        if (k != null) {
            k.onConnectFailure();
        }
    }

    public void callConnect() {
        fetchAdvertisingID();
    }

    public void fetchAdvertisingID() {
        new Thread(new Runnable(this) {
            final /* synthetic */ TapjoyConnectCore a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.ag.loadAdvertisingId();
                if (this.a.ag.isGooglePlayServicesAvailable() && this.a.ag.isGooglePlayManifestConfigured()) {
                    TapjoyConnectCore.b = this.a.ag.getDeviceGooglePlayServicesVersion();
                    TapjoyConnectCore.a = this.a.ag.getPackagedGooglePlayServicesVersion();
                }
                if (this.a.ag.isAdIdAvailable()) {
                    TapjoyConnectCore.d = this.a.ag.isAdTrackingEnabled();
                    TapjoyConnectCore.c = this.a.ag.getAdvertisingId();
                }
                if (TapjoyConnectCore.o()) {
                    TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
                }
                this.a.completeConnectCall();
            }
        }).start();
    }

    public void appPause() {
        this.ab = true;
    }

    public void appResume() {
        if (this.ab) {
            p();
            this.ab = false;
        }
    }

    public static Map getURLParams() {
        Map genericURLParams = getGenericURLParams();
        genericURLParams.putAll(getTimeStampAndVerifierParams());
        return genericURLParams;
    }

    public static Map getGenericURLParams() {
        Map g = g();
        TapjoyUtil.safePut(g, TapjoyConstants.TJC_APP_ID, w, true);
        return g;
    }

    private static Map g() {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Map hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PLUGIN, P, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_SDK_TYPE, Q, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_ID, w, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_VERSION, y, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_REVISION, TapjoyRevision.GIT_REVISION, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_BRIDGE_VERSION, z, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_VERSION_NAME, x, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_NAME, s, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PLATFORM, F, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, v, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_MANUFACTURER, t, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_TYPE_NAME, u, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_SCREEN_LAYOUT_SIZE, C, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_LOCATION, String.valueOf(E), true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE_NAME, M, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE_VIEW, String.valueOf(T), true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CARRIER_NAME, G, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CARRIER_COUNTRY_CODE, H, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MOBILE_NETWORK_CODE, J, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MOBILE_COUNTRY_CODE, I, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, Locale.getDefault().getCountry(), true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_LANGUAGE, Locale.getDefault().getLanguage(), true);
        K = getConnectionType();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CONNECTION_TYPE, K, true);
        L = getConnectionSubType();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CONNECTION_SUBTYPE, L, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, A, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        if (n()) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ADVERTISING_ID, c, true);
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_AD_TRACKING_ENABLED, String.valueOf(d), true);
        }
        if (!o()) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ANDROID_ID, n, true);
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_ID_NAME, p, true);
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, q, true);
        }
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_INSTALL_ID, r, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_USER_ID, D, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ADVERTISING_ID_CHECK_DISABLED, e, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PERSISTENT_ID_DISABLED, f, true);
        if (a != 0) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGED_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(a), true);
        }
        if (b != 0) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(b), true);
        }
        if (o == null || o.length() == 0 || System.currentTimeMillis() - ac > TapjoyConstants.SESSION_ID_INACTIVITY_TIME) {
            o = p();
        } else {
            ac = System.currentTimeMillis();
        }
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_SESSION_ID, o, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_GROUP_ID, U, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE, V, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ANALYTICS_API_KEY, W, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MANAGED_DEVICE_ID, X, true);
        hashMap2.putAll(hashMap3);
        if (!(TapjoyCache.getInstance() == null || TapjoyCache.getInstance().getCachedOfferIDs() == null || TapjoyCache.getInstance().getCachedOfferIDs().length() <= 0)) {
            TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CACHED_OFFERS, TapjoyCache.getInstance().getCachedOfferIDs(), true);
        }
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CURRENCY_MULTIPLIER, Float.toString(S), true);
        hashMap.putAll(hashMap2);
        hashMap2 = new HashMap();
        i();
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ANALYTICS_ID, ak, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGE_ID, al, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGE_SIGN, am, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY, aM, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH, aN, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT, aO, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_COUNTRY_SIM, aP, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_TIMEZONE, aQ, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGE_VERSION, an, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGE_REVISION, ao, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGE_DATA_VERSION, ap, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_INSTALLER, aq, true);
        if (cr.c(M)) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE_NAME, aR, true);
        }
        hashMap2.putAll(hashMap3);
        hashMap2.putAll(h());
        hashMap.putAll(hashMap2);
        return hashMap;
    }

    public static Map getTimeStampAndVerifierParams() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String a = a(currentTimeMillis);
        Map hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_TIMESTAMP, String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_VERIFIER, a, true);
        return hashMap;
    }

    private static Map h() {
        Map hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_INSTALLED, ar, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_REFERRER, as, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_LEVEL, at, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_FRIEND_COUNT, au, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_1, av, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_2, aw, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_3, ax, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_4, ay, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_5, az, true);
        int i = 0;
        for (String safePut : aA) {
            int i2 = i + 1;
            TapjoyUtil.safePut(hashMap, "user_tags[" + i + "]", safePut, true);
            i = i2;
        }
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY, aB, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY, aC, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_COUNT, aD, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_LENGTH, aE, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_AT, aF, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_LENGTH, aG, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_CURRENCY, aH, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_COUNT, aI, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_PRICE, aJ, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_PRICE, aK, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_AT, aL, true);
        return hashMap;
    }

    private static void i() {
        n a = fm.a(g).a(true);
        ak = a.c.h();
        al = a.c.y();
        am = a.c.A();
        aM = a.c.c;
        aN = a.c.d;
        aO = a.c.e;
        aP = a.c.E();
        aQ = a.c.w();
        an = a.d.f();
        ao = a.d.c;
        ap = a.d.i();
        aq = a.d.k();
        aR = a.d.m();
        ar = a.e.c;
        as = a.e.g();
        at = a.e.p;
        au = a.e.q;
        av = a.e.C();
        aw = a.e.E();
        ax = a.e.G();
        ay = a.e.I();
        az = a.e.K();
        aA = new HashSet(a.e.r);
        aB = a.e.d;
        aC = a.e.e;
        aD = a.e.g;
        aE = a.e.h;
        aF = a.e.i;
        aG = a.e.j;
        aH = a.e.p();
        aI = a.e.k;
        aJ = a.e.l;
        aK = a.e.n;
        aL = a.e.m;
    }

    private static void j() {
        TapjoyLog.i("TapjoyConnect", "Connect Flags:");
        TapjoyLog.i("TapjoyConnect", "--------------------");
        for (Entry entry : ah.entrySet()) {
            TapjoyLog.i("TapjoyConnect", "key: " + ((String) entry.getKey()) + ", value: " + Uri.encode(entry.getValue().toString()));
        }
        TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL) + "]");
        TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + R + "]");
        TapjoyLog.i("TapjoyConnect", "--------------------");
    }

    private static void k() {
        try {
            if (af != null) {
                ApplicationInfo applicationInfo = af.getApplicationInfo(g.getPackageName(), 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    TapjoyLog.d("TapjoyConnect", "No metadata present.");
                    return;
                }
                for (String str : TapjoyConnectFlag.FLAG_ARRAY) {
                    String string = applicationInfo.metaData.getString("tapjoy." + str);
                    if (string != null) {
                        TapjoyLog.d("TapjoyConnect", "Found manifest flag: " + str + ", " + string);
                        b(str, string);
                    }
                }
                TapjoyLog.d("TapjoyConnect", "Metadata successfully loaded");
            }
        } catch (Exception e) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + e.toString()));
        }
    }

    private static void a(Properties properties) {
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            try {
                String str = (String) keys.nextElement();
                b(str, (String) properties.get(str));
            } catch (ClassCastException e) {
                TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
            }
        }
    }

    private void l() {
        int indexOf;
        try {
            List<ActivityInfo> asList = Arrays.asList(af.getPackageInfo(g.getPackageName(), 1).activities);
            if (asList != null) {
                for (ActivityInfo activityInfo : asList) {
                    if (m.contains(activityInfo.name)) {
                        indexOf = m.indexOf(activityInfo.name);
                        Class.forName((String) m.get(indexOf));
                        Vector vector = new Vector();
                        if ((activityInfo.configChanges & 128) != 128) {
                            vector.add(AdUnitActivity.EXTRA_ORIENTATION);
                        }
                        if ((activityInfo.configChanges & 32) != 32) {
                            vector.add("keyboardHidden");
                        }
                        if (vector.size() == 0) {
                            if (VERSION.SDK_INT >= 13 && (activityInfo.configChanges & 1024) != 1024) {
                                TapjoyLog.w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + ((String) m.get(indexOf)));
                            }
                            if (VERSION.SDK_INT < 11 || !activityInfo.name.equals("com.tapjoy.TJAdUnitActivity") || (activityInfo.flags & 512) == 512) {
                                m.remove(indexOf);
                            } else {
                                throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + ((String) m.get(indexOf)));
                            }
                        } else if (vector.size() == 1) {
                            throw new TapjoyIntegrationException(vector.toString() + " property is not specified in manifest configChanges for " + ((String) m.get(indexOf)));
                        } else {
                            throw new TapjoyIntegrationException(vector.toString() + " properties are not specified in manifest configChanges for " + ((String) m.get(indexOf)));
                        }
                    }
                }
            }
            if (m.size() == 0) {
                m();
                try {
                    try {
                        Class.forName("com.tapjoy.TJAdUnitJSBridge").getMethod(String.CLOSE_REQUESTED, new Class[]{Boolean.class});
                        String str = (String) TapjoyUtil.getResource("mraid.js");
                        if (str == null) {
                            str = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", g);
                        }
                        if (str == null) {
                            throw new TapjoyIntegrationException("ClassNotFoundException: mraid.js was not found.");
                        } else if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) == null || !getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).equals(TapjoyConstants.TJC_TRUE)) {
                            this.ag.checkGooglePlayIntegration();
                        } else {
                            TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
                        }
                    } catch (NoSuchMethodException e) {
                        throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
                    }
                } catch (ClassNotFoundException e2) {
                    throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
                }
            } else if (m.size() == 1) {
                throw new TapjoyIntegrationException("Missing " + m.size() + " dependency class in manifest: " + m.toString());
            } else {
                throw new TapjoyIntegrationException("Missing " + m.size() + " dependency classes in manifest: " + m.toString());
            }
        } catch (ClassNotFoundException e3) {
            throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + ((String) m.get(indexOf)));
        } catch (NameNotFoundException e4) {
            throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
        }
    }

    private static void m() {
        int i = 0;
        Vector vector = new Vector();
        for (String str : TapjoyConstants.dependencyPermissions) {
            if (!e(str)) {
                vector.add(str);
            }
        }
        if (vector.size() == 0) {
            Vector vector2 = new Vector();
            String[] strArr = TapjoyConstants.optionalPermissions;
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (!e(str2)) {
                    vector2.add(str2);
                }
                i++;
            }
            if (vector2.size() == 0) {
                return;
            }
            if (vector2.size() == 1) {
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
            } else {
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
            }
        } else if (vector.size() == 1) {
            throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + vector.toString());
        } else {
            throw new TapjoyIntegrationException("Missing " + vector.size() + " permissions in manifest: " + vector.toString());
        }
    }

    private static boolean n() {
        return c != null && c.length() > 0;
    }

    private static boolean o() {
        return n() && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).equals(TapjoyConstants.TJC_TRUE);
    }

    private static boolean a(String str, boolean z) {
        Closeable b;
        IOException e;
        Throwable th;
        RuntimeException e2;
        Closeable closeable = null;
        try {
            b = br.b(str);
            try {
                Map d = b.d();
                String a = cr.a((String) d.get(TapjoyConstants.TJC_APP_GROUP_ID));
                String a2 = cr.a((String) d.get(TapjoyConstants.TJC_STORE));
                String a3 = cr.a((String) d.get(TapjoyConstants.TJC_ANALYTICS_API_KEY));
                String a4 = cr.a((String) d.get(TapjoyConstants.TJC_MANAGED_DEVICE_ID));
                String a5 = cr.a((String) d.get(TapjoyConstants.TJC_PACKAGE_NAMES));
                Object obj = d.get("cache_max_age");
                el elVar = new el(a3);
                if (elVar.a != com.tapjoy.internal.el.a.RPC_ANALYTICS) {
                    throw new IOException("Invalid analytics_api_key");
                }
                String str2 = elVar.b;
                if (str2.regionMatches(13, "-8000-8000-", 0, 11)) {
                    String str3;
                    String stringBuffer = new StringBuffer().append(str2.substring(0, 8)).append(str2.substring(24, 30)).append(str2.substring(9, 13)).append(str2.substring(30)).toString();
                    String str4 = elVar.c;
                    if (a == null) {
                        str3 = stringBuffer;
                    } else {
                        str3 = a;
                    }
                    fm.a().a(g, a3, com.tapjoy.mraid.BuildConfig.VERSION_NAME, TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, stringBuffer, str4);
                    U = str3;
                    V = a2;
                    W = a3;
                    X = a4;
                    List arrayList = new ArrayList();
                    if (a5 != null) {
                        for (String trim : a5.split(",")) {
                            String trim2 = trim2.trim();
                            if (trim2.length() > 0) {
                                arrayList.add(trim2);
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        a(arrayList);
                    }
                    b.close();
                    closeable = null;
                    if (!z) {
                        long j = 0;
                        if (obj instanceof String) {
                            try {
                                j = Long.parseLong(((String) obj).trim());
                            } catch (NumberFormatException e3) {
                            }
                        } else if (obj instanceof Number) {
                            try {
                                j = ((Number) obj).longValue();
                            } catch (NumberFormatException e4) {
                            }
                        }
                        if (j <= 0) {
                            TapjoyAppSettings.getInstance().removeConnectResult();
                        } else {
                            TapjoyAppSettings.getInstance().saveConnectResultAndParams(str, s(), (j * 1000) + y.b());
                        }
                    }
                    da.a(null);
                    return true;
                }
                throw new IllegalArgumentException("The given UUID did not come from 5Rocks.");
            } catch (IOException e5) {
                e = e5;
                closeable = b;
                try {
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    da.a(closeable);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    b = closeable;
                    da.a(b);
                    throw th;
                }
            } catch (RuntimeException e6) {
                e2 = e6;
                try {
                    TapjoyLog.v("TapjoyConnect", e2.getMessage());
                    da.a(b);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    da.a(b);
                    throw th;
                }
            }
        } catch (IOException e7) {
            e = e7;
            TapjoyLog.v("TapjoyConnect", e.getMessage());
            da.a(closeable);
            return false;
        } catch (RuntimeException e8) {
            e2 = e8;
            b = closeable;
            TapjoyLog.v("TapjoyConnect", e2.getMessage());
            da.a(b);
            return false;
        } catch (Throwable th4) {
            th = th4;
            b = closeable;
            da.a(b);
            throw th;
        }
    }

    private static synchronized void a(List list) {
        synchronized (TapjoyConnectCore.class) {
            ai = BuildConfig.FLAVOR;
            for (ApplicationInfo applicationInfo : af.getInstalledApplications(0)) {
                if ((applicationInfo.flags & 1) != 1 && list.contains(applicationInfo.packageName)) {
                    TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + applicationInfo.packageName);
                    if (ai.length() > 0) {
                        ai += ",";
                    }
                    ai += applicationInfo.packageName;
                }
            }
        }
    }

    private static boolean c(String str) {
        Closeable b;
        IOException e;
        Throwable th;
        RuntimeException e2;
        try {
            b = br.b(str);
            try {
                if (b.a()) {
                    b.s();
                    TapjoyLog.d("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
                    b.close();
                    da.a(null);
                    return true;
                }
                b.close();
                da.a(null);
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                return false;
            } catch (IOException e3) {
                e = e3;
                try {
                    TapjoyLog.v("TapjoyConnect", e.getMessage());
                    da.a(b);
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    da.a(b);
                    throw th;
                }
            } catch (RuntimeException e4) {
                e2 = e4;
                TapjoyLog.v("TapjoyConnect", e2.getMessage());
                da.a(b);
                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                return false;
            }
        } catch (IOException e5) {
            e = e5;
            b = null;
            TapjoyLog.v("TapjoyConnect", e.getMessage());
            da.a(b);
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
            return false;
        } catch (RuntimeException e6) {
            e2 = e6;
            b = null;
            TapjoyLog.v("TapjoyConnect", e2.getMessage());
            da.a(b);
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
            return false;
        } catch (Throwable th3) {
            th = th3;
            b = null;
            da.a(b);
            throw th;
        }
    }

    public void release() {
        i = null;
        j = null;
        TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
    }

    public static String getAppID() {
        return w;
    }

    public static String getDeviceID() {
        return p;
    }

    public static String getUserID() {
        return D;
    }

    public static String getHostURL() {
        return getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL);
    }

    public static String getPlacementURL() {
        return getConnectFlagValue(TapjoyConnectFlag.PLACEMENT_URL);
    }

    public static String getConnectURL() {
        return TapjoyConfig.TJC_CONNECT_SERVICE_URL;
    }

    public static String getRedirectDomain() {
        return R;
    }

    public static String getCarrierName() {
        return G;
    }

    public String getSerial() {
        String obj;
        Exception e;
        try {
            Field declaredField = Class.forName("android.os.Build").getDeclaredField("SERIAL");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            obj = declaredField.get(Build.class).toString();
            try {
                TapjoyLog.d("TapjoyConnect", "serial: " + obj);
            } catch (Exception e2) {
                e = e2;
                TapjoyLog.e("TapjoyConnect", e.toString());
                return obj;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            obj = null;
            e = exception;
            TapjoyLog.e("TapjoyConnect", e.toString());
            return obj;
        }
        return obj;
    }

    public static String getConnectionType() {
        String str = BuildConfig.FLAVOR;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                switch (connectivityManager.getActiveNetworkInfo().getType()) {
                    case TwitterResponse.READ /*1*/:
                    case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
                        break;
                    default:
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
                        break;
                }
                TapjoyLog.d("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                TapjoyLog.d("TapjoyConnect", "connection_type: " + str);
            }
            return str;
        } catch (Exception e) {
            Exception exception = e;
            String str2 = str;
            TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + exception.toString());
            return str2;
        }
    }

    public static String getConnectionSubType() {
        String subtypeName;
        Exception e;
        String str = BuildConfig.FLAVOR;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) g.getSystemService("connectivity");
            if (connectivityManager == null) {
                return str;
            }
            subtypeName = connectivityManager.getActiveNetworkInfo().getSubtypeName();
            try {
                TapjoyLog.d("TapjoyConnect", "connection_sub_type: " + subtypeName);
                return subtypeName;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            subtypeName = str;
            e = exception;
            TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + e.toString());
            return subtypeName;
        }
    }

    private static boolean d(String str) {
        for (ApplicationInfo applicationInfo : af.getInstalledApplications(0)) {
            if (applicationInfo.packageName.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        FeatureInfo[] systemAvailableFeatures = af.getSystemAvailableFeatures();
        int length = systemAvailableFeatures.length;
        int i = 0;
        while (i < length) {
            if (!systemAvailableFeatures[i].name.matches(str)) {
                i++;
            } else if (af.checkPermission(str2, g.getPackageName()) == 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static String p() {
        String SHA256;
        Exception e;
        TapjoyLog.i("TapjoyConnect", "generating sessionID...");
        try {
            SHA256 = TapjoyUtil.SHA256((System.currentTimeMillis() / 1000) + w + p);
            try {
                ac = System.currentTimeMillis();
            } catch (Exception e2) {
                e = e2;
                TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + e.toString());
                return SHA256;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            SHA256 = null;
            e = exception;
            TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + e.toString());
            return SHA256;
        }
        return SHA256;
    }

    public static Context getContext() {
        return g;
    }

    private static String q() {
        Object obj = 1;
        if (o()) {
            return c;
        }
        Object obj2;
        if (p == null || p.length() <= 0) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return p;
        }
        if (q == null || q.length() <= 0) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return q;
        }
        if (n()) {
            return c;
        }
        if (n == null || n.length() <= 0) {
            obj = null;
        }
        if (obj != null) {
            return n;
        }
        TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
        return null;
    }

    private static String a(long j) {
        String str = BuildConfig.FLAVOR;
        try {
            str = TapjoyUtil.SHA256(w + ":" + q() + ":" + j + ":" + N);
        } catch (Exception e) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing verifier value -- " + e.toString()));
        }
        return str;
    }

    public static String getAwardCurrencyVerifier(long j, int i, String str) {
        String str2 = BuildConfig.FLAVOR;
        try {
            str2 = TapjoyUtil.SHA256(w + ":" + q() + ":" + j + ":" + N + ":" + i + ":" + str);
        } catch (Exception e) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing awardCurrencyVerifier -- " + e.toString()));
        }
        return str2;
    }

    private static String a(long j, String str) {
        String str2 = BuildConfig.FLAVOR;
        try {
            str2 = TapjoyUtil.SHA256(w + ":" + q() + ":" + j + ":" + N + ":" + str);
        } catch (Exception e) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing packageNamesVerifier -- " + e.toString()));
        }
        return str2;
    }

    public boolean isInitialized() {
        return this.ad;
    }

    public static void setPlugin(String str) {
        P = str;
    }

    public static void setSDKType(String str) {
        Q = str;
    }

    public static void setUserID(String str, TJSetUserIDListener tJSetUserIDListener) {
        D = str;
        l = tJSetUserIDListener;
        TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
        new Thread(new Runnable() {
            public final void run() {
                TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.D);
                TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_USER_ID_URL_PATH, TapjoyConnectCore.getURLParams());
                boolean z = false;
                if (responseFromURL.response != null) {
                    z = TapjoyConnectCore.a(responseFromURL.response);
                }
                TapjoyConnectCore.a(z);
            }
        }).start();
    }

    public static void viewDidClose(String str) {
        aj.remove(str);
        eo.e.notifyObservers();
    }

    public static void viewWillOpen(String str, int i) {
        aj.put(str, Integer.valueOf(i));
    }

    public static boolean isViewOpen() {
        return !aj.isEmpty();
    }

    public static boolean isFullScreenViewOpen() {
        for (Integer intValue : aj.values()) {
            switch (intValue.intValue()) {
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                    return true;
                default:
            }
        }
        return false;
    }

    public static void setViewShowing(boolean z) {
        if (z) {
            aj.put(BuildConfig.FLAVOR, Integer.valueOf(1));
        } else {
            aj.clear();
        }
    }

    private static void b(String str, String str2) {
        if ((str.equals(TapjoyConnectFlag.SERVICE_URL) || str.equals(TapjoyConnectFlag.PLACEMENT_URL)) && !str2.endsWith(Operation.DIVISION)) {
            str2 = str2 + Operation.DIVISION;
        }
        ah.put(str, str2);
    }

    private static boolean e(String str) {
        if (af.checkPermission(str, g.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public void actionComplete(String str) {
        TapjoyLog.i("TapjoyConnect", "actionComplete: " + str);
        Map g = g();
        TapjoyUtil.safePut(g, TapjoyConstants.TJC_APP_ID, str, true);
        g.putAll(getTimeStampAndVerifierParams());
        TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + g);
        new Thread(new PPAThread(this, g)).start();
    }

    public void enablePaidAppWithActionID(String str) {
        TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + str);
        Y = str;
        this.Z = g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).getLong(TapjoyConstants.PREF_ELAPSED_TIME, 0);
        TapjoyLog.d("TapjoyConnect", "paidApp elapsed: " + this.Z);
        if (this.Z >= TapjoyConstants.PAID_APP_TIME) {
            if (Y != null && Y.length() > 0) {
                TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
                actionComplete(Y);
            }
        } else if (this.aa == null) {
            this.aa = new Timer();
            this.aa.schedule(new a(), RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS, RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        }
    }

    public void completeConnectCall() {
        boolean z;
        TapjoyHttpURLResponse responseFromURL;
        TapjoyLog.d("TapjoyConnect", "starting connect call...");
        String str = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            str = getHostURL();
        }
        if (!isConnected()) {
            String connectResult = TapjoyAppSettings.getInstance().getConnectResult(s(), y.b());
            if (connectResult != null && a(connectResult, true)) {
                TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
                ae = true;
                r();
                if (k != null) {
                    k.onConnectSuccess();
                }
                eo.a.notifyObservers();
                z = true;
                responseFromURL = j.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, getURLParams());
                if (responseFromURL == null && responseFromURL.statusCode == HttpResponseCode.OK) {
                    if (a(responseFromURL.response, false)) {
                        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
                        ae = true;
                        r();
                        for (Entry entry : getGenericURLParams().entrySet()) {
                            TapjoyLog.d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
                        }
                        if (!z) {
                            if (k != null) {
                                k.onConnectSuccess();
                            }
                            eo.a.notifyObservers();
                        }
                        eo.b.notifyObservers(Boolean.TRUE);
                    } else {
                        if (!z) {
                            f();
                        }
                        eo.b.notifyObservers(Boolean.FALSE);
                    }
                    if (ai.length() > 0) {
                        Map genericURLParams = getGenericURLParams();
                        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_PACKAGE_NAMES, ai, true);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        String a = a(currentTimeMillis, ai);
                        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_TIMESTAMP, String.valueOf(currentTimeMillis), true);
                        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, a, true);
                        responseFromURL = new TapjoyURLConnection().getResponseFromURL(getHostURL() + TapjoyConstants.TJC_SDK_LESS_CONNECT, genericURLParams);
                        if (responseFromURL != null && responseFromURL.statusCode == HttpResponseCode.OK) {
                            TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (!z) {
                    f();
                }
                eo.b.notifyObservers(Boolean.FALSE);
            }
        }
        z = false;
        responseFromURL = j.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, getURLParams());
        if (responseFromURL == null) {
        }
        if (z) {
            f();
        }
        eo.b.notifyObservers(Boolean.FALSE);
    }

    private void r() {
        TapjoyLog.d("TapjoyConnect", "Initializing mediation params");
        try {
            ArrayList arrayList = (ArrayList) ah.get(TapjoyConnectFlag.MEDIATION_CONFIGS);
            if (arrayList != null) {
                TapjoyLog.i("TapjoyConnect", "Initializing mediation partners with configs");
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    final TJMediationNetwork tJMediationNetwork = (TJMediationNetwork) it.next();
                    new Thread(this) {
                        final /* synthetic */ TapjoyConnectCore b;

                        public final void run() {
                            if (tJMediationNetwork != null) {
                                tJMediationNetwork.init();
                            } else {
                                TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Failed to cast mediation connect flag into TJMediationNetwork type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
                            }
                        }
                    }.run();
                }
            }
        } catch (ClassCastException e) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
        }
        String connectFlagValue = getConnectFlagValue(TapjoyConnectFlag.MEDIATION_TIMEOUT);
        if (connectFlagValue != null && !connectFlagValue.isEmpty()) {
            TJMediationSettings.getInstance().setTimeout(connectFlagValue);
            TapjoyLog.i("TapjoyConnect", "Setting mediation timeout to " + connectFlagValue + "s");
        }
    }

    public void setCurrencyMultiplier(float f) {
        TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + f);
        S = f;
    }

    public float getCurrencyMultiplier() {
        return S;
    }

    public static String getConnectFlagValue(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (ah == null || ah.get(str) == null) {
            return str2;
        }
        return ah.get(str).toString();
    }

    public static String getSecretKey() {
        return N;
    }

    public static String getAndroidID() {
        return n;
    }

    public static String getSha1MacAddress() {
        String str = null;
        try {
            str = TapjoyUtil.SHA1(q);
        } catch (Exception e) {
            TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + e.toString());
        }
        return str;
    }

    public static String getMacAddress() {
        return q;
    }

    public static float getDeviceScreenDensityScale() {
        return B;
    }

    public static boolean isConnected() {
        return ae;
    }

    public static boolean isUnitTestMode() {
        return getConnectFlagValue("unit_test_mode") == TapjoyConstants.TJC_TRUE;
    }

    private static String s() {
        String str = w + x + y + c + p + r;
        try {
            str = TapjoyUtil.SHA1(str);
        } catch (Exception e) {
        }
        return str;
    }

    static /* synthetic */ void a(boolean z) {
        if (z) {
            TapjoyLog.i("TapjoyConnect", "Set userID is successful");
            if (l != null) {
                l.onSetUserIDSuccess();
                return;
            }
            return;
        }
        String str = "Failed to set userID";
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, str));
        if (l != null) {
            l.onSetUserIDFailure(str);
        }
    }
}
