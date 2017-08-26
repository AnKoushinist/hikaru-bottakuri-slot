package jp.co.geniee.gnadsdk.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.d;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: GNRequest */
public class c implements LocationListener {
    private int a = 0;
    private String b = BuildConfig.FLAVOR;
    private boolean c = false;
    private final HashMap<String, String> d = new HashMap();
    private String e = BuildConfig.FLAVOR;
    private boolean f = false;
    private LocationManager g = null;
    private String h = BuildConfig.FLAVOR;
    private String i = BuildConfig.FLAVOR;
    private Context j = null;
    private String k;
    private String l;
    private String m = BuildConfig.FLAVOR;
    private String n = BuildConfig.FLAVOR;
    private String o = BuildConfig.FLAVOR;
    private String p = BuildConfig.FLAVOR;
    private final a q;

    public c(a aVar, Context context, String str) {
        this.q = aVar;
        this.j = context;
        this.b = str;
        this.d.putAll(b.b());
        e();
        d();
        b();
    }

    private final String c() {
        if (this.c) {
            return this.l;
        }
        return this.k;
    }

    public String a() {
        String str = c() + "?ver=" + "2.1.4" + "&zoneid=" + this.b;
        if (this.d == null) {
            return str;
        }
        String str2 = str;
        for (Entry entry : this.d.entrySet()) {
            str2 = new StringBuilder(String.valueOf(str2)).append("&").append((String) entry.getKey()).append(Operation.EQUALS).append((String) entry.getValue()).toString();
        }
        return str2;
    }

    private void d() {
        ApplicationInfo applicationInfo;
        this.n = this.j.getApplicationContext().getPackageName();
        this.o = BuildConfig.FLAVOR;
        try {
            this.o = URLEncoder.encode(this.n, "utf-8");
        } catch (UnsupportedEncodingException e) {
            this.o = BuildConfig.FLAVOR;
        }
        if (this.o != null && this.o.length() > 0) {
            this.d.put("ap_id", this.o);
        }
        String str = BuildConfig.FLAVOR;
        PackageManager packageManager = this.j.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(this.n, 0);
        } catch (NameNotFoundException e2) {
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            str = (String) packageManager.getApplicationLabel(applicationInfo);
        } else if (this.n != null && this.n.length() > 0) {
            str = this.n.substring(this.n.lastIndexOf(46) + 1);
        }
        this.p = BuildConfig.FLAVOR;
        try {
            this.p = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e3) {
            this.p = BuildConfig.FLAVOR;
        }
        if (this.p != null && this.p.length() > 0) {
            this.d.put("ap_name", this.p);
        }
        str = d.a(this.j.getApplicationContext());
        String str2 = BuildConfig.FLAVOR;
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e4) {
            str = BuildConfig.FLAVOR;
        }
        if (str != null && str.length() > 0) {
            this.d.put("ua", str);
        }
        str = this.j.getResources().getConfiguration().locale.getLanguage();
        str2 = BuildConfig.FLAVOR;
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e5) {
            str = BuildConfig.FLAVOR;
        }
        if (str != null && str.length() > 0) {
            this.d.put("lan", str);
        }
        str = ((TelephonyManager) this.j.getSystemService("phone")).getSimOperator();
        if (str != null && str.length() > 0) {
            this.d.put("carrie", str);
        }
        str = Build.MODEL;
        str2 = BuildConfig.FLAVOR;
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e6) {
            str = BuildConfig.FLAVOR;
        }
        if (str != null && str.length() > 0) {
            this.d.put("dv_model", str);
        }
    }

    private void e() {
        Object obj = 1;
        if (this.f) {
            String packageName = this.j.getApplicationContext().getPackageName();
            PackageManager packageManager = this.j.getPackageManager();
            if (!(packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0 || packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0)) {
                obj = null;
            }
            if (obj != null) {
                this.g = (LocationManager) this.j.getSystemService("location");
                String str = "network";
                Location lastKnownLocation = this.g.getLastKnownLocation(str);
                if (lastKnownLocation != null) {
                    onLocationChanged(lastKnownLocation);
                }
                this.g.requestLocationUpdates(str, TapjoyConstants.SESSION_ID_INACTIVITY_TIME, 100.0f, this);
            }
        }
    }

    public void onLocationChanged(Location location) {
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        if (valueOf != null && valueOf2 != null && valueOf.length() > 0 && valueOf2.length() > 0) {
            if (!valueOf.equals(this.h) || !valueOf2.equals(this.i)) {
                this.h = valueOf;
                this.d.put("geo_lat", valueOf);
                this.i = valueOf2;
                this.d.put("geo_lng", valueOf2);
            }
        }
    }

    public void b() {
        new Thread(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                Info advertisingIdInfo;
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a.j);
                } catch (IOException e) {
                    this.a.q.b("GNRequest", e.toString());
                    advertisingIdInfo = null;
                } catch (com.google.android.gms.common.c e2) {
                    this.a.q.b("GNRequest", e2.toString());
                    advertisingIdInfo = null;
                } catch (IllegalStateException e3) {
                    this.a.q.b("GNRequest", e3.toString());
                    advertisingIdInfo = null;
                } catch (d e4) {
                    this.a.q.b("GNRequest", e4.toString());
                    advertisingIdInfo = null;
                } catch (Exception e5) {
                    this.a.q.b("GNRequest", e5.toString());
                    advertisingIdInfo = null;
                }
                if (advertisingIdInfo != null) {
                    String id = advertisingIdInfo.getId();
                    boolean isLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                    if (id != null && id.length() > 0) {
                        this.a.d.put("i_adid", id);
                        if (isLimitAdTrackingEnabled) {
                            this.a.d.put("ad_track", "0");
                        } else {
                            this.a.d.put("ad_track", "1");
                        }
                    }
                }
            }
        }).start();
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void a(String str) {
        this.l = str;
    }

    public void b(String str) {
        this.k = str;
    }
}
