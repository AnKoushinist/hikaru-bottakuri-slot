package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.os.Handler;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.LinkedList;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeListener;

class GNSMediatorImt extends GNSMediatorAbstract implements GNSIMediator {
    private Runnable p = new Runnable(this) {
        final /* synthetic */ GNSMediatorImt a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.o.d("Imt", "TmpAD\u751f\u6210\u30bf\u30b9\u30af\u958b\u59cb");
            if (this.a.n()) {
                this.a.o.d("Imt", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u306b\u79fb\u52d5\u3057\u305f\u306e\u3067\u4e00\u65e6TmpAD\u751f\u6210\u30bf\u30b9\u30af\u7d42\u4e86");
            } else if (this.a.f.a.size() <= this.a.k.size()) {
                this.a.o.d("Imt", "\u518d\u751f\u5f85\u6a5f\u4e0a\u9650\u306b\u9054\u3057\u305f\u306e\u3067\u7d42\u4e86\u3002\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                this.a.i.a(Boolean.valueOf(false));
            } else if (this.a.m()) {
                this.a.o.d("Imt", "\u65e2\u306b\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3057\u305f\u306e\u3067\u7d42\u4e86\u3002\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                GNSAdaptee h = this.a.h();
                if (h != null) {
                    this.a.a(h);
                }
            } else {
                this.a.o.d("Imt", "\u6b8b\u308a\u672a\u51e6\u7406AD\u6570 " + this.a.l.size());
                if (this.a.l.size() > 0) {
                    GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) this.a.l.get(0);
                    this.a.o.d("Imt", "TmpAD\u751f\u6210\u51e6\u7406\u958b\u59cb " + gNSZoneInfoSource.k);
                    GNSAdaptee b = this.a.b(gNSZoneInfoSource);
                    if (b != null) {
                        this.a.j.add(b);
                        this.a.o.d("Imt", "\u65e2\u306b\u518d\u751f\u5f85\u6a5fAD\u306b\u3042\u308b\u306e\u3067\u305d\u3053\u304b\u3089TmpAD\u4f5c\u6210 " + gNSZoneInfoSource.k);
                        this.a.a(b);
                        return;
                    }
                    b = this.a.a(gNSZoneInfoSource);
                    if (b == null) {
                        this.a.o.d("Imt", "TmpAD\u65b0\u898f\u4f5c\u6210 " + gNSZoneInfoSource.k);
                        b = this.a.c(gNSZoneInfoSource);
                    }
                    if (b == null) {
                        this.a.o.d("Imt", "\u4f5c\u6210\u306b\u5931\u6557\u3057\u305f\u306e\u3067\u672a\u51e6\u7406AD\u304b\u3089\u524a\u9664 " + gNSZoneInfoSource.k);
                        this.a.l.remove(gNSZoneInfoSource);
                        this.a.a(new GNSVideoRewardException(gNSZoneInfoSource.k, 80001));
                        return;
                    } else if (b.canShow()) {
                        this.a.o.d("Imt", "\u65e2\u306bAD\u5728\u5eab\u3042\u308a " + b.getAdnetworkName());
                        this.a.a(b);
                        return;
                    } else {
                        this.a.o.d("Imt", "preload\u958b\u59cb " + b.getAdnetworkName());
                        b.preload();
                        return;
                    }
                }
                this.a.o.d("Imt", "TmpAD\u751f\u6210\u5168\u51e6\u7406\u7d42\u4e86");
                this.a.a(false);
            }
        }
    };

    GNSMediatorImt() {
    }

    private synchronized void b(GNSZoneInfo gNSZoneInfo) {
        if (gNSZoneInfo != null) {
            if (this.f != gNSZoneInfo) {
                this.f = gNSZoneInfo;
                this.o.d("Imt", "ZoneInfo update");
                this.o.d("Imt", "ZoneInfo adSource\u6570=" + this.f.a.size());
                l();
                o();
            }
        }
    }

    public void a(GNSAdaptee gNSAdaptee) {
        this.o.d("Imt", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f " + gNSAdaptee.getAdnetworkName());
        if (!this.i.d()) {
            this.o.d("Imt", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u3055\u308c\u3066\u3044\u306a\u3044\u306e\u3067\u518d\u751f\u5f85\u6a5fAD\u751f\u6210\u51e6\u7406\u3092\u505c\u6b62 " + gNSAdaptee.getAdnetworkName());
        } else if (gNSAdaptee.canShow()) {
            b(gNSAdaptee);
            if (m()) {
                a(false);
                if (n()) {
                    this.o.d("Imt", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u306b\u3057\u306a\u3044");
                    return;
                }
                this.o.d("Imt", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                this.i.a(Boolean.valueOf(false));
                return;
            }
            this.o.d("Imt", "\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u304c\u53d6\u5f97\u3067\u304d\u3066\u306a\u3044\u306e\u3067\u6b21\u306eTmpAD\u3092\u751f\u6210\u3059\u308b");
            o();
        } else {
            this.o.c("Imt", "\u5728\u5eab\u304c\u306a\u3044\u306e\u3067\u518d\u751f\u5f85\u6a5fAD\u751f\u6210\u51e6\u7406\u3092\u505c\u6b62\u3002\u30e2\u30c3\u30af\u3067\u767a\u751f\u3059\u308b\u4ee5\u5916\u3001\u3053\u306e\u30d1\u30bf\u30fc\u30f3\u306f\u901a\u5e38\u5b58\u5728\u3057\u306a\u3044 " + gNSAdaptee.getAdnetworkName());
        }
    }

    public void a(GNSVideoRewardException gNSVideoRewardException) {
        this.o.d("Imt", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 " + gNSVideoRewardException.a() + " " + gNSVideoRewardException.b() + ":" + gNSVideoRewardException.getMessage());
        if (this.i.d()) {
            this.o.d("Imt", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 " + gNSVideoRewardException.a());
            b(gNSVideoRewardException);
            if (this.l.size() <= 0 || m()) {
                a(false);
                if (!n()) {
                    this.o.d("Imt", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                    if (m()) {
                        GNSAdaptee h = h();
                        if (h != null) {
                            a(h);
                            return;
                        }
                        return;
                    }
                    this.i.a(Boolean.valueOf(false));
                    return;
                } else if (this.b) {
                    this.o.d("Imt", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9 && \u6210\u529f\u672a\u901a\u77e5\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u306b\u3057\u306a\u3044");
                    return;
                } else {
                    this.o.d("Imt", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9 && \u6210\u529f\u901a\u77e5\u6e08\u307f\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off\u3078");
                    this.i.a(Boolean.valueOf(false));
                    return;
                }
            }
            this.o.d("Imt", "\u672a\u51e6\u7406AD\u304c\u3042\u308a\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u304c\u53d6\u5f97\u3067\u304d\u3066\u306a\u3044\u306e\u3067\u6b21\u306eTmpAD\u3092\u751f\u6210\u3059\u308b");
            o();
            return;
        }
        this.o.d("Imt", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u3055\u308c\u3066\u3044\u306a\u3044\u306e\u3067\u30ed\u30fc\u30c9\u51e6\u7406\u3092\u505c\u6b62");
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
        this.o.d("Imt", String.VIDEO_START);
        if (this.g != null) {
            b(this.g);
            this.g = null;
        } else if (this.f != null) {
            o();
        }
        a(true);
    }

    public void b() {
        this.o.d("Imt", "pause");
    }

    public void c() {
        super.c();
    }

    public void d() {
        this.o.d("Imt", "destroy");
    }

    private void o() {
        this.o.d("Imt", "TmpAD\u6e96\u5099\u5b9f\u884c\u30ea\u30af\u30a8\u30b9\u30c8");
        if (e()) {
            this.o.d("Imt", "TmpAD\u6e96\u5099\u5b9f\u884c");
            this.c.runOnUiThread(this.p);
        }
    }

    public void a(boolean z) {
        super.a(z);
    }

    public boolean e() {
        return super.e();
    }

    public void a(GNSZoneInfo gNSZoneInfo) {
        if (n()) {
            this.o.d("Imt", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u304b\u3089\u5fa9\u5e30\u5f8czone\u60c5\u5831\u66f4\u65b0");
            this.g = gNSZoneInfo;
            return;
        }
        this.o.d("Imt", "zone\u60c5\u5831\u5373\u66f4\u65b0");
        b(gNSZoneInfo);
    }

    public void f() {
        this.o.d("Imt", "Zone\u60c5\u5831reset");
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
