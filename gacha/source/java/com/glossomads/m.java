package com.glossomads;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.webkit.WebView;
import com.glossomads.Logger.SugarDebugLogger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public class m {
    private static String a = BuildConfig.FLAVOR;
    private static Boolean b = Boolean.valueOf(false);
    private static Boolean c = Boolean.valueOf(true);
    private static String d = "Android";
    private static String e = BuildConfig.FLAVOR;
    private static boolean f = false;
    private static Boolean g = Boolean.valueOf(false);
    private static Boolean h = Boolean.valueOf(true);
    private static String i = "1.0.4";
    private static String j = BuildConfig.FLAVOR;
    private static String k = BuildConfig.FLAVOR;
    private static String l = BuildConfig.FLAVOR;
    private static String m = BuildConfig.FLAVOR;
    private static String n = BuildConfig.FLAVOR;
    private static String o = BuildConfig.FLAVOR;
    private List<String> p;

    private static class a extends AsyncTask<Void, Void, Void> {
        Activity a;
        String b = null;
        boolean c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        a(Activity activity) {
            this.a = activity;
        }

        protected Void a(Void[] voidArr) {
            try {
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
                this.b = advertisingIdInfo.getId();
                if (SugarUtil.isEmptyValue(m.a) || !m.a.equals(this.b)) {
                    com.glossomads.Logger.a.f(com.glossomads.Logger.a.a.getIfa, this.b);
                }
                this.c = advertisingIdInfo.isLimitAdTrackingEnabled();
                if (this.c) {
                    com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.getIfaTracking, this.b);
                }
            } catch (NoClassDefFoundError e) {
                com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.getIfaFailed, "Google Play Services SDK not installed!");
            } catch (Exception e2) {
                com.glossomads.Logger.a.e(com.glossomads.Logger.a.a.getIfaFailed, "Advertising Id not available!");
            }
            return null;
        }

        protected void a(Void voidR) {
            if (SugarUtil.isNotEmptyValue(this.b)) {
                m.a = this.b;
            }
            m.c = Boolean.valueOf(this.c);
            m.g = Boolean.valueOf(true);
            m.b = Boolean.valueOf(false);
            if (m.f) {
                v.l();
            } else {
                m.f = true;
                v.c(m.c.booleanValue());
            }
            q.b(this.b, this.c);
            if (m.h.booleanValue()) {
                if (SugarUtil.isEmptyValue(this.b)) {
                    com.glossomads.Logger.a.f(com.glossomads.Logger.a.a.advertisingIdIsNull, new String[0]);
                } else {
                    com.glossomads.Logger.a.f(com.glossomads.Logger.a.a.setTestDeviceInfo, this.b);
                }
            }
            m.h = Boolean.valueOf(false);
            v.a().d();
        }
    }

    private static class b {
        private static final m a = new m();
    }

    public static String a() {
        return a;
    }

    private m() {
    }

    public static m b() {
        return b.a;
    }

    public static void c() {
        n();
        o();
        l();
    }

    public static void d() {
        if (!b.booleanValue()) {
            C();
        }
        o();
    }

    public static int e() {
        if (c.booleanValue()) {
            return 1;
        }
        return 0;
    }

    public static boolean f() {
        return c.booleanValue();
    }

    public static String g() {
        return d;
    }

    public static int h() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) s.a().d().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 0;
            }
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return 2;
            }
            return type == 0 ? 3 : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String i() {
        if (e.equals(BuildConfig.FLAVOR)) {
            e = s.a().d().getPackageName();
        }
        return e;
    }

    public static String j() {
        return Build.MODEL;
    }

    public static String k() {
        return Build.MANUFACTURER;
    }

    public static void l() {
        String a = q.a("SugarAdvertisingId");
        if (SugarUtil.isNotEmptyValue(a)) {
            a = a;
            c = Boolean.valueOf(q.a("SugarLimitAdTracking", false));
        }
    }

    public static boolean m() {
        return SugarUtil.isNotEmptyValue(q.a("SugarAdvertisingId"));
    }

    public static void n() {
        n = BuildConfig.FLAVOR;
        try {
            n = new WebView(s.a().d()).getSettings().getUserAgentString();
        } catch (Exception e) {
        }
    }

    public static void o() {
        o = com.glossomads.a.a.a(s.a().d().getResources().getConfiguration().locale.getCountry());
    }

    public static String p() {
        b();
        if (SugarUtil.isEmptyValue(n)) {
            n();
        }
        return n;
    }

    public static Point q() {
        return a(s.a().d());
    }

    public static Point a(Activity activity) {
        Point point = new Point();
        if (activity != null) {
            try {
                activity.getWindowManager().getDefaultDisplay().getSize(point);
            } catch (Exception e) {
                SugarDebugLogger.printStackTrace(e);
            }
        }
        return point;
    }

    public static String r() {
        return VERSION.RELEASE;
    }

    public static String s() {
        return s.a().d().getResources().getConfiguration().locale.getLanguage();
    }

    public static String t() {
        return K();
    }

    public static String u() {
        if (SugarUtil.isEmptyValue(o)) {
            o();
        }
        return o;
    }

    public static String v() {
        Activity d = s.a().d();
        try {
            return d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return BuildConfig.FLAVOR;
        } catch (Exception e2) {
            return BuildConfig.FLAVOR;
        }
    }

    public static String w() {
        return i;
    }

    public static String x() {
        return j;
    }

    public static void a(String str) {
        j = str;
    }

    public static String y() {
        return k;
    }

    public static void b(String str) {
        k = str;
    }

    public static String z() {
        return l;
    }

    public static void c(String str) {
        l = str;
    }

    public static String A() {
        if (SugarUtil.isEmptyValue(m)) {
            int identifier = s.a().d().getResources().getIdentifier("app_name", "string", s.a().d().getPackageName());
            if (identifier != 0) {
                m = s.a().d().getResources().getString(identifier);
            }
        }
        return m;
    }

    public static void d(String str) {
        m = str;
    }

    public static int B() {
        String p = p();
        if (p.indexOf("Android") > 0 && p.indexOf("Mobile") == -1) {
            return 5;
        }
        if (p.indexOf("Android") <= 0 || p.indexOf("Mobile") <= 0) {
            return 2;
        }
        return 4;
    }

    public static void C() {
        b = Boolean.valueOf(true);
        if (VERSION.SDK_INT >= 11) {
            new a(s.a().d()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new a(s.a().d()).execute(new Void[0]);
        }
    }

    public static String D() {
        try {
            String simOperator = ((TelephonyManager) s.a().d().getSystemService("phone")).getSimOperator();
            if (simOperator == null) {
                simOperator = BuildConfig.FLAVOR;
            }
            Object obj = -1;
            switch (simOperator.hashCode()) {
                case 49619888:
                    if (simOperator.equals("44000")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 49619919:
                    if (simOperator.equals("44010")) {
                        obj = null;
                        break;
                    }
                    break;
                case 49619950:
                    if (simOperator.equals("44020")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 49619951:
                    if (simOperator.equals("44021")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 49620043:
                    if (simOperator.equals("44050")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 49620044:
                    if (simOperator.equals("44051")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 49620045:
                    if (simOperator.equals("44052")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 49620046:
                    if (simOperator.equals("44053")) {
                        obj = 8;
                        break;
                    }
                    break;
                case 49620047:
                    if (simOperator.equals("44054")) {
                        obj = 9;
                        break;
                    }
                    break;
                case 49620105:
                    if (simOperator.equals("44070")) {
                        obj = 10;
                        break;
                    }
                    break;
                case 49620106:
                    if (simOperator.equals("44071")) {
                        obj = 11;
                        break;
                    }
                    break;
                case 49620107:
                    if (simOperator.equals("44072")) {
                        obj = 12;
                        break;
                    }
                    break;
                case 49620108:
                    if (simOperator.equals("44073")) {
                        obj = 13;
                        break;
                    }
                    break;
                case 49620109:
                    if (simOperator.equals("44074")) {
                        obj = 14;
                        break;
                    }
                    break;
                case 49620110:
                    if (simOperator.equals("44075")) {
                        obj = 15;
                        break;
                    }
                    break;
                case 49620111:
                    if (simOperator.equals("44076")) {
                        obj = 16;
                        break;
                    }
                    break;
                case 49620850:
                    if (simOperator.equals("44101")) {
                        obj = 4;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case TwitterResponse.NONE /*0*/:
                    return "DOCOMO";
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    return "SOFTBANK";
                case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                case AdInfo.BANNER_KIND_WALL1 /*10*/:
                case AdInfo.BANNER_KIND_NATIVE1 /*11*/:
                case Constants.MOVIE_REWARD_TYPE /*12*/:
                case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                case Constants.MOVIE_INTER_TYPE /*14*/:
                case R.styleable.Toolbar_titleMarginStart /*15*/:
                case R.styleable.Toolbar_titleMarginEnd /*16*/:
                    return "KDDI";
                default:
                    return simOperator;
            }
        } catch (Exception e) {
            return BuildConfig.FLAVOR;
        }
    }

    private static String K() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    public static boolean E() {
        return Build.MANUFACTURER.equals("Amazon") && (Build.MODEL.equals("Kindle Fire") || Build.MODEL.startsWith("KF"));
    }

    public static boolean b(Activity activity) {
        if (VERSION.SDK_INT < 24 || !activity.isInMultiWindowMode()) {
            return false;
        }
        return true;
    }

    public static void e(String str) {
        m b = b();
        if (SugarUtil.isEmptyCollection(b.p)) {
            b.p = new ArrayList();
        }
        if (!b.p.contains(str)) {
            b.p.add(str);
        }
    }

    public static boolean F() {
        m b = b();
        if (SugarUtil.isEmptyCollection(b.p) || SugarUtil.isEmptyValue(a())) {
            return false;
        }
        return b.p.contains(a());
    }
}
