package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.ViewGroup;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;

public class AdColony {
    static boolean b = true;
    static boolean c;
    boolean a = false;

    private static class a extends AsyncTask<Void, Void, Void> {
        Activity a;
        String b = BuildConfig.FLAVOR;
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

        protected Void a(Void... voidArr) {
            try {
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
                this.b = advertisingIdInfo.getId();
                this.c = advertisingIdInfo.isLimitAdTrackingEnabled();
            } catch (NoClassDefFoundError e) {
                l.d.b((Object) "Google Play Services SDK not installed! Collecting Android Id instead of Advertising Id.");
            } catch (NoSuchMethodError e2) {
                l.d.b((Object) "Google Play Services SDK is out of date! Collecting Android Id instead of Advertising Id.");
            } catch (Exception e3) {
                if (!Build.MANUFACTURER.equals("Amazon")) {
                    l.d.b((Object) "Advertising Id not available! Collecting Android Id instead of Advertising Id.");
                    e3.printStackTrace();
                }
            }
            return null;
        }

        protected void a(Void voidR) {
            g.a = this.b;
            g.b = this.c;
            AdColony.c = true;
        }
    }

    public static void disable() {
        a.y = true;
    }

    public static void configure(Activity activity, String str, String str2, String... strArr) {
        c = false;
        if (b) {
            b = false;
            if (VERSION.SDK_INT >= 11) {
                new a(activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                new a(activity).execute(new Void[0]);
            }
            a.aq.clear();
            Handler handler = new Handler();
            Runnable anonymousClass1 = new Runnable() {
                public void run() {
                    a.H = false;
                }
            };
            if (!a.H || a.I) {
                if (!a.y) {
                    if (str2 == null) {
                        a.a("Null App ID - disabling AdColony.");
                        return;
                    } else if (strArr == null) {
                        a.a("Null Zone IDs array - disabling AdColony.");
                        return;
                    } else if (strArr.length == 0) {
                        a.a("No Zone IDs provided - disabling AdColony.");
                        return;
                    } else {
                        a.b(activity);
                        a.l.a(str, str2, strArr);
                        a.w = true;
                        a.H = true;
                        handler.postDelayed(anonymousClass1, 120000);
                    }
                } else {
                    return;
                }
            }
            if (a.U == null) {
                a.E = true;
            }
            a.ao.clear();
            a.ap.clear();
            a.ar = new HashMap();
            for (Object put : strArr) {
                a.ar.put(put, Boolean.valueOf(false));
            }
            return;
        }
        a.ao.clear();
        a.ap.clear();
    }

    public static void setCustomID(String str) {
        if (!str.equals(a.l.a.y) && a.l != null && a.l.b != null) {
            a.l.a.y = str;
            if (a.x) {
                a.l.b.h();
            }
        }
    }

    public static String getCustomID() {
        return a.l.a.y;
    }

    public static boolean isConfigured() {
        return !b;
    }

    public static void setDeviceID(String str) {
        if (!str.equals(a.l.a.z)) {
            a.l.a.z = str;
            a.H = false;
            a.l.b.d = true;
            a.l.b.b = false;
            a.l.b.c = true;
        }
    }

    public static String getDeviceID() {
        return a.l.a.z;
    }

    public static boolean isTablet() {
        return g.i();
    }

    public static void resume(final Activity activity) {
        l.c.b((Object) "[ADC] AdColony resume called.");
        a.B = false;
        a.r = false;
        a.a(activity);
        a.A = false;
        a.h();
        if (activity == null) {
            l.d.b((Object) "Activity reference is null. Disabling AdColony.");
            disable();
            return;
        }
        if (a.v != null) {
            a.W.a(a.v);
            a.v = null;
        }
        new Thread(new Runnable() {
            public void run() {
                activity.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        for (int i = 0; i < a.aq.size(); i++) {
                            AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) a.aq.get(i);
                            if (!(adColonyNativeAdView == null || a.b() != adColonyNativeAdView.d || adColonyNativeAdView.u)) {
                                adColonyNativeAdView.A = false;
                                adColonyNativeAdView.invalidate();
                                if (adColonyNativeAdView.T != null) {
                                    adColonyNativeAdView.T.a = false;
                                    adColonyNativeAdView.T.invalidate();
                                }
                            }
                        }
                    }
                });
            }
        }).start();
        a.M = false;
    }

    public static void pause() {
        l.c.b((Object) "[ADC] AdColony pause called.");
        a.r = true;
        a.B = true;
        for (int i = 0; i < a.aq.size(); i++) {
            if (a.aq.get(i) != null) {
                AdColonyNativeAdView adColonyNativeAdView = (AdColonyNativeAdView) a.aq.get(i);
                adColonyNativeAdView.A = true;
                if (!(adColonyNativeAdView.ag == null || adColonyNativeAdView.u || !adColonyNativeAdView.ag.isPlaying())) {
                    if (a.E) {
                        adColonyNativeAdView.T.setVisibility(0);
                    }
                    adColonyNativeAdView.c();
                }
            }
        }
    }

    public static void onBackPressed() {
        int i = 0;
        if (a.S == null) {
            return;
        }
        if ((a.S instanceof ab) || (a.S instanceof ac)) {
            ((ViewGroup) a.S.getParent()).removeView(a.S);
            a.E = true;
            a.S.G.c(false);
            while (i < a.an.size()) {
                ((Bitmap) a.an.get(i)).recycle();
                i++;
            }
            a.an.clear();
            a.S = null;
        }
    }

    public static Activity activity() {
        return a.b();
    }

    public static boolean isZoneV4VC(String str) {
        if (a.l == null || a.l.b == null || a.l.b.i == null || a.l.b.i.n == null) {
            return false;
        }
        return a.l.b.a(str, false);
    }

    public static boolean isZoneNative(String str) {
        if (a.l == null || a.l.b == null || a.l.b.i == null || a.l.b.i.n == null || a.l.b.i.n.a(str) == null || a.l.b.i.n.a(str).m == null || a.l.b.i.n.a(str).m.a == null) {
            return false;
        }
        for (int i = 0; i < a.l.b.i.n.a(str).m.a.size(); i++) {
            if (a.l.b.i.n.a(str).m.a(i).A.a) {
                return true;
            }
        }
        return false;
    }

    public static void addV4VCListener(AdColonyV4VCListener adColonyV4VCListener) {
        if (!a.ao.contains(adColonyV4VCListener)) {
            a.ao.add(adColonyV4VCListener);
        }
    }

    public static void removeV4VCListener(AdColonyV4VCListener adColonyV4VCListener) {
        a.ao.remove(adColonyV4VCListener);
    }

    public static void addAdAvailabilityListener(AdColonyAdAvailabilityListener adColonyAdAvailabilityListener) {
        if (!a.ap.contains(adColonyAdAvailabilityListener)) {
            a.ap.add(adColonyAdAvailabilityListener);
        }
    }

    public static void removeAdAvailabilityListener(AdColonyAdAvailabilityListener adColonyAdAvailabilityListener) {
        a.ap.remove(adColonyAdAvailabilityListener);
    }

    public static void notifyIAPComplete(String str, String str2) {
        notifyIAPComplete(str, str2, null, 0.0d);
    }

    public static void notifyIAPComplete(String str, String str2, String str3, double d) {
        l.c.b((Object) "notifyIAPComplete() called.");
        i gVar = new g();
        gVar.b("product_id", str);
        if (d != 0.0d) {
            gVar.b("price", d);
        }
        gVar.b("trans_id", str2);
        gVar.b("quantity", 1);
        if (str3 != null) {
            gVar.b("price_currency_code", str3);
        }
        if (a.O) {
            a.l.d.a("in_app_purchase", (g) gVar);
        } else {
            a.aj.a(gVar);
        }
    }

    public static void cancelVideo() {
        if (a.U != null) {
            a.U.finish();
            a.ak = true;
            a.W.b(null);
        }
    }

    public static String statusForZone(String str) {
        if (a.l == null || a.l.b == null || a.l.b.i == null || a.l.b.i.n == null) {
            return "unknown";
        }
        if (a.y) {
            return "unknown";
        }
        ad a = a.l.b.i.n.a(str);
        if (a != null) {
            if (!a.g) {
                return "off";
            }
            if (a.h && a.l.b.c(str, true)) {
                return "active";
            }
            return "loading";
        } else if (a.x) {
            return "invalid";
        } else {
            return "unknown";
        }
    }

    public static void get_images(String str) {
        a.l.a.b(str);
    }

    public static void disableDECOverride() {
        a.e = null;
    }

    public static void forceMobileCache() {
        if (!a.N) {
            a.N = true;
            a.H = false;
            a.l.b.d = true;
            a.l.b.b = false;
            a.l.b.c = true;
        }
    }
}
