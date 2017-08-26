package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.os.Handler;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeListener;

class GNSMediatorWifi extends GNSMediatorAbstract implements GNSIMediator {
    private Runnable p = new Runnable(this) {
        final /* synthetic */ GNSMediatorWifi a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.o.d("Wifi", "TmpAD\u751f\u6210\u958b\u59cb");
            this.a.i();
            this.a.j();
            if (this.a.n()) {
                this.a.o.d("Wifi", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u306b\u79fb\u52d5\u3057\u305f\u306e\u3067\u4e00\u65e6TmpAD\u751f\u6210\u30bf\u30b9\u30af\u7d42\u4e86");
                return;
            }
            Iterator it = this.a.f.a.iterator();
            while (it.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
                GNSAdaptee b = this.a.b(gNSZoneInfoSource);
                if (b != null) {
                    this.a.j.add(b);
                    this.a.o.d("Wifi", "\u65e2\u306b\u518d\u751f\u5f85\u6a5fAD\u306b\u3042\u308b\u306e\u3067\u305d\u3053\u304b\u3089TmpAD\u4f5c\u6210 " + gNSZoneInfoSource.k);
                    this.a.a(b);
                } else {
                    b = this.a.a(gNSZoneInfoSource);
                    if (b == null) {
                        this.a.o.d("Wifi", "TmpAD\u65b0\u898f\u4f5c\u6210 " + gNSZoneInfoSource.k);
                        b = this.a.c(gNSZoneInfoSource);
                    }
                    if (b == null) {
                        this.a.o.d("Wifi", "\u4f5c\u6210\u306b\u5931\u6557\u3057\u305f\u306e\u3067\u672a\u51e6\u7406AD\u304b\u3089\u524a\u9664 " + gNSZoneInfoSource.k);
                        this.a.l.remove(gNSZoneInfoSource);
                        this.a.a(new GNSVideoRewardException(gNSZoneInfoSource.k, 80001));
                    } else if (b.canShow()) {
                        this.a.o.d("Wifi", "\u65e2\u306bAD\u5728\u5eab\u3042\u308a " + b.getAdnetworkName());
                        this.a.a(b);
                    } else {
                        this.a.o.d("Wifi", "preload\u958b\u59cb " + b.getAdnetworkName());
                        b.preload();
                    }
                }
            }
            this.a.o.d("Wifi", "TmpAD\u751f\u6210\u7d42\u4e86");
            this.a.i();
            this.a.j();
        }
    };

    GNSMediatorWifi() {
    }

    private synchronized void b(GNSZoneInfo gNSZoneInfo) {
        if (gNSZoneInfo != null) {
            this.f = gNSZoneInfo;
            this.o.d("Wifi", "ZoneInfo update");
            l();
            this.c.runOnUiThread(this.p);
        }
    }

    public void a(GNSAdaptee gNSAdaptee) {
        this.o.d("Wifi", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f " + gNSAdaptee.getAdnetworkName());
        if (!this.i.d()) {
            this.o.d("Wifi", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u3055\u308c\u3066\u3044\u306a\u3044\u306e\u3067\u518d\u751f\u5f85\u6a5fAD\u751f\u6210\u51e6\u7406\u3092\u505c\u6b62 " + gNSAdaptee.getAdnetworkName());
        } else if (gNSAdaptee.canShow()) {
            b(gNSAdaptee);
            this.o.d("Wifi", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f\u304b\u3089\u306e\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3067\u304d\u305f\u304b\u5224\u65ad " + gNSAdaptee.getAdnetworkName());
            if (m()) {
                a(false);
                if (n()) {
                    this.o.d("Wifi", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u306b\u3057\u306a\u3044");
                    return;
                }
                this.o.d("Wifi", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                this.i.a(Boolean.valueOf(false));
            }
        } else {
            this.o.c("Wifi", "\u5728\u5eab\u304c\u306a\u3044\u306e\u3067\u518d\u751f\u5f85\u6a5fAD\u751f\u6210\u51e6\u7406\u3092\u505c\u6b62\u3002\u30e2\u30c3\u30af\u3067\u767a\u751f\u3059\u308b\u4ee5\u5916\u3001\u3053\u306e\u30d1\u30bf\u30fc\u30f3\u306f\u901a\u5e38\u5b58\u5728\u3057\u306a\u3044 " + gNSAdaptee.getAdnetworkName());
        }
    }

    public void a(GNSVideoRewardException gNSVideoRewardException) {
        this.o.d("Wifi", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 " + gNSVideoRewardException.a() + " " + gNSVideoRewardException.b() + ":" + gNSVideoRewardException.getMessage());
        if (this.i.d()) {
            this.o.d("Wifi", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 " + gNSVideoRewardException.a());
            b(gNSVideoRewardException);
            this.o.d("Wifi", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557\u304b\u3089\u306e\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3067\u304d\u305f\u304b\u5224\u65ad " + gNSVideoRewardException.a());
            if (this.l.size() == 0 || m()) {
                a(false);
                if (!n()) {
                    this.o.d("Wifi", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                    this.i.a(Boolean.valueOf(false));
                    return;
                } else if (this.b) {
                    this.o.d("Wifi", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9 && \u6210\u529f\u672a\u901a\u77e5\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u306b\u3057\u306a\u3044");
                    return;
                } else {
                    this.o.d("Wifi", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9 && \u6210\u529f\u901a\u77e5\u6e08\u307f\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                    this.i.a(Boolean.valueOf(false));
                    return;
                }
            }
            return;
        }
        this.o.d("Wifi", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u3055\u308c\u3066\u3044\u306a\u3044\u306e\u3067\u30ed\u30fc\u30c9\u51e6\u7406\u3092\u505c\u6b62");
    }

    public void a(Activity activity, String str, String str2, Handler handler, ArrayList<GNSAdaptee> arrayList, LinkedList<GNSAdaptee> linkedList, GNSAdapteeListener gNSAdapteeListener, GNSVideoMediator gNSVideoMediator) {
        this.c = activity;
        this.d = str;
        this.e = str2;
        this.h = handler;
        this.j = arrayList;
        this.k = linkedList;
        this.n = gNSAdapteeListener;
        this.i = gNSVideoMediator;
        this.o = GNSLogger.a();
        this.l = new ArrayList();
    }

    public synchronized void a() {
        this.o.d("Wifi", String.VIDEO_START);
        if (this.g != null) {
            this.o.d("Wifi", "start New ZoneInfo worker");
            b(this.g);
            this.g = null;
        } else if (this.f != null) {
            this.o.d("Wifi", "start ZoneInfo task");
            if (e()) {
                b(this.f);
            }
        }
        a(true);
    }

    public void b() {
        this.o.d("Wifi", "pause");
    }

    public void c() {
        super.c();
    }

    public void d() {
        this.o.d("Wifi", "destroy");
    }

    public void a(boolean z) {
        super.a(z);
    }

    public boolean e() {
        return super.e();
    }

    public void a(GNSZoneInfo gNSZoneInfo) {
        if (n()) {
            this.g = gNSZoneInfo;
        } else {
            b(gNSZoneInfo);
        }
    }

    public void f() {
        this.o.d("Wifi", "Zone\u60c5\u5831reset");
        this.f = new GNSZoneInfo();
    }

    public void a(GNSRewardVideoAdListener gNSRewardVideoAdListener) {
        this.m = gNSRewardVideoAdListener;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean g() {
        return this.b;
    }
}
