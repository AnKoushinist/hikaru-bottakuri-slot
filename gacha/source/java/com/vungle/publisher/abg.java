package com.vungle.publisher;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import com.applovin.sdk.AppLovinEventTypes;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.Demographic.Gender;
import javax.inject.Inject;
import javax.inject.Singleton;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class abg extends abe {
    protected String a;
    protected String b;
    protected a c;
    protected b d;
    protected Boolean e;
    protected String f;

    /* compiled from: vungle */
    public static class a extends abe {
        protected Integer a;
        protected Gender b;
        protected b c;

        @Singleton
        /* compiled from: vungle */
        public static class a extends abw<a> {
            @Inject
            Context a;
            @Inject
            protected Demographic b;
            @Inject
            protected a c;

            protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new a[i];
            }

            protected final /* synthetic */ Object b() {
                return new a();
            }

            @Inject
            a() {
            }

            protected final a a() {
                Demographic demographic = this.b;
                a aVar = new a();
                aVar.a = demographic.getAge();
                aVar.b = demographic.getGender();
                if ((this.a.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 ? 1 : null) != null) {
                    aVar.c = this.c.a();
                }
                return aVar;
            }
        }

        /* compiled from: vungle */
        public static class b extends abe {
            protected Float a;
            protected Double b;
            protected Double c;
            protected Float d;
            protected Long e;

            @Singleton
            /* compiled from: vungle */
            public static class a extends abw<b> {
                @Inject
                sr a;

                protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                    return new b[i];
                }

                protected final /* synthetic */ Object b() {
                    return new b();
                }

                @Inject
                a() {
                }

                protected final b a() {
                    Location b = this.a.b();
                    if (b == null) {
                        Logger.d(Logger.PROTOCOL_TAG, "detailed location not available");
                        return null;
                    }
                    b bVar = new b();
                    bVar.a = Float.valueOf(b.getAccuracy());
                    bVar.b = Double.valueOf(b.getLatitude());
                    bVar.c = Double.valueOf(b.getLongitude());
                    bVar.d = Float.valueOf(b.getSpeed());
                    bVar.e = Long.valueOf(b.getTime());
                    return bVar;
                }
            }

            public final /* synthetic */ Object b() {
                return a();
            }

            protected b() {
            }

            public final JSONObject a() {
                JSONObject a = super.a();
                a.putOpt("accuracyMeters", this.a);
                a.putOpt(String.LAT, this.b);
                a.putOpt(String.LONG, this.c);
                a.putOpt("speedMetersPerSecond", this.d);
                a.putOpt("timestampMillis", this.e);
                return a;
            }
        }

        public final /* synthetic */ Object b() {
            return a();
        }

        protected a() {
        }

        public final JSONObject a() {
            JSONObject a = super.a();
            a.putOpt("age", this.a);
            a.putOpt("gender", this.b);
            a.putOpt("location", se.a(this.c));
            return a;
        }
    }

    /* compiled from: vungle */
    public static class b extends abe {
        protected un a;
        protected String b;
        protected String c;
        protected Float d;
        protected String e;
        protected Boolean f;
        protected Boolean g;
        protected a h;
        protected Boolean i;
        protected Boolean j;
        protected String k;
        protected String l;
        protected String m;
        protected String n;
        protected c o;
        protected Float p;
        protected String q;
        protected Long r;

        /* compiled from: vungle */
        public static class a extends abe {
            protected Integer a;
            protected Integer b;

            @Singleton
            /* compiled from: vungle */
            public static class a extends abw<a> {
                @Inject
                protected pn a;

                protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                    return new a[i];
                }

                protected final /* synthetic */ Object b() {
                    return new a();
                }

                @Inject
                a() {
                }

                protected final a a() {
                    DisplayMetrics h = this.a.h();
                    if (h.heightPixels <= 0 && h.widthPixels <= 0) {
                        return null;
                    }
                    a aVar = new a();
                    aVar.a = Integer.valueOf(h.heightPixels);
                    aVar.b = Integer.valueOf(h.widthPixels);
                    return aVar;
                }
            }

            public final /* synthetic */ Object b() {
                return a();
            }

            protected a() {
            }

            public final JSONObject a() {
                JSONObject a = super.a();
                a.putOpt("height", this.a);
                a.putOpt("width", this.b);
                return a;
            }
        }

        @Singleton
        /* compiled from: vungle */
        public static class b extends abw<b> {
            @Inject
            Context a;
            @Inject
            protected AdConfig b;
            @Inject
            protected pn c;
            @Inject
            protected a d;
            @Inject
            protected uq e;
            @Inject
            protected pu f;
            private PowerManager g;
            private Intent h;

            protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new b[i];
            }

            protected final /* synthetic */ Object b() {
                return new b();
            }

            @Inject
            b() {
            }

            protected final b a() {
                b bVar = new b();
                uo e = this.e.e();
                bVar.a = e.s;
                bVar.b = e.r;
                bVar.h = this.d.a();
                bVar.i = Boolean.valueOf(this.c.o());
                bVar.j = Boolean.valueOf(this.b.isSoundEnabled());
                bVar.k = this.c.j();
                bVar.l = this.c.m();
                bVar.m = this.e.b();
                bVar.n = this.c.g();
                bVar.o = c.a;
                bVar.p = this.c.n();
                bVar.q = this.c.r();
                bVar.r = this.c.s();
                if (agl.a(pj.NOUGAT)) {
                    bVar.c = this.e.c().f;
                }
                if (agl.a(pj.JELLY_BEAN)) {
                    bVar.g = Boolean.valueOf(this.e.d());
                }
                try {
                    if (this.a != null) {
                        if (agl.a(pj.LOLLIPOP)) {
                            this.g = (PowerManager) this.a.getSystemService("power");
                            bVar.f = new Boolean(this.g.isPowerSaveMode());
                        }
                        if (this.h == null) {
                            this.h = this.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                        }
                        if (this.h != null) {
                            lu luVar = lu.a;
                            int intExtra = this.h.getIntExtra("status", -1);
                            if (intExtra == 2 || intExtra == 5) {
                                switch (this.h.getIntExtra("plugged", -1)) {
                                    case TwitterResponse.READ /*1*/:
                                        luVar = lu.d;
                                        break;
                                    case TwitterResponse.READ_WRITE /*2*/:
                                        luVar = lu.c;
                                        break;
                                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                                        luVar = lu.e;
                                        break;
                                    default:
                                        luVar = lu.f;
                                        break;
                                }
                            }
                            luVar = lu.b;
                            bVar.e = luVar.toString();
                            intExtra = this.h.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1);
                            int intExtra2 = this.h.getIntExtra("scale", -1);
                            if (intExtra > 0 && intExtra2 > 0) {
                                bVar.d = Float.valueOf(((float) intExtra) / ((float) intExtra2));
                            }
                        }
                    }
                } catch (Exception e2) {
                    Logger.v(Logger.PROTOCOL_TAG, "Exception while collecting battery stats: " + e2);
                }
                return bVar;
            }
        }

        /* compiled from: vungle */
        public enum c {
            ;

            private c(String str) {
            }
        }

        public final /* synthetic */ Object b() {
            return a();
        }

        protected b() {
        }

        public final JSONObject a() {
            Object obj;
            JSONObject a = super.a();
            a.putOpt("connection", this.a);
            a.putOpt("connectionDetail", this.b);
            a.putOpt("dataSaverStatus", this.c);
            a.putOpt("isNetworkMetered", this.g);
            a.putOpt("batteryLevel", this.d);
            a.putOpt("batteryState", this.e);
            a.putOpt("isBatterySaverEnabled", this.f);
            a.putOpt("dim", se.a(this.h));
            String str = "isSdCardAvailable";
            Boolean bool = this.i;
            if (bool == null) {
                obj = null;
            } else {
                obj = Integer.valueOf(bool.booleanValue() ? 1 : 0);
            }
            a.putOpt(str, obj);
            a.putOpt("soundEnabled", this.j);
            a.putOpt("mac", this.k);
            a.putOpt("model", this.l);
            a.putOpt("networkOperator", this.m);
            a.putOpt("osVersion", this.n);
            a.putOpt(TapjoyConstants.TJC_PLATFORM, this.o);
            a.putOpt("volume", this.p);
            a.putOpt("userAgent", this.q);
            a.putOpt("bytesAvailable", this.r);
            return a;
        }
    }

    /* compiled from: vungle */
    public static abstract class c<T extends abg> extends abw<T> {
        @Inject
        protected a a;
        @Inject
        pn b;
        @Inject
        pv c;
        @Inject
        protected b d;
        @Inject
        protected pu e;

        protected T a() {
            abg com_vungle_publisher_abg = (abg) b();
            com_vungle_publisher_abg.a = this.b.c();
            com_vungle_publisher_abg.b = this.b.a();
            com_vungle_publisher_abg.c = this.a.a();
            com_vungle_publisher_abg.d = this.d.a();
            com_vungle_publisher_abg.e = Boolean.valueOf(this.b.i());
            com_vungle_publisher_abg.f = this.e.b();
            return com_vungle_publisher_abg;
        }
    }

    public /* synthetic */ Object b() {
        return a();
    }

    public JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("isu", this.a);
        a.putOpt("ifa", this.b);
        a.putOpt("demo", se.a(this.c));
        a.putOpt("deviceInfo", se.a(this.d));
        a.putOpt("adTrackingEnabled", this.e);
        a.putOpt("pubAppId", this.f);
        return a;
    }
}
