package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeListener;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import org.cocos2dx.lib.BuildConfig;

public abstract class GNSMediatorAbstract {
    protected boolean a = false;
    protected boolean b;
    protected Activity c;
    protected String d;
    protected String e;
    protected GNSZoneInfo f;
    protected GNSZoneInfo g;
    protected Handler h;
    protected GNSVideoMediator i;
    protected ArrayList<GNSAdaptee> j;
    protected LinkedList<GNSAdaptee> k;
    protected ArrayList<GNSZoneInfoSource> l;
    protected GNSRewardVideoAdListener m;
    protected GNSAdapteeListener n;
    protected GNSLogger o;

    protected abstract void a(GNSAdaptee gNSAdaptee);

    protected GNSAdaptee a(GNSZoneInfoSource gNSZoneInfoSource) {
        try {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                GNSAdaptee gNSAdaptee = (GNSAdaptee) it.next();
                if (gNSAdaptee.getAdnetworkName().equals(gNSZoneInfoSource.k)) {
                    return gNSAdaptee;
                }
            }
        } catch (Exception e) {
            this.o.f("Abst", "findWorkerList");
            this.o.f("Abst", e.getMessage());
        }
        return null;
    }

    protected GNSAdaptee b(GNSZoneInfoSource gNSZoneInfoSource) {
        try {
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                GNSAdaptee gNSAdaptee = (GNSAdaptee) it.next();
                if (gNSAdaptee.getAdnetworkName().equals(gNSZoneInfoSource.k)) {
                    return gNSAdaptee;
                }
            }
        } catch (Exception e) {
            this.o.f("Abst", "findPlayableList");
            this.o.f("Abst", e.getMessage());
        }
        return null;
    }

    protected GNSAdaptee h() {
        Iterator it = this.f.a.iterator();
        while (it.hasNext()) {
            GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
            if (gNSZoneInfoSource != null) {
                Iterator it2 = this.k.iterator();
                while (it2.hasNext()) {
                    GNSAdaptee gNSAdaptee = (GNSAdaptee) it2.next();
                    if (gNSAdaptee == null) {
                        continue;
                        break;
                    } else if (gNSZoneInfoSource.k.equals(gNSAdaptee.getAdnetworkName())) {
                        return gNSAdaptee;
                    }
                }
            }
            break;
        }
        return null;
    }

    protected void i() {
        if (this.o.b() <= 3) {
            String str = BuildConfig.FLAVOR;
            Iterator it = this.f.a.iterator();
            String str2 = str;
            while (it.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
                if (gNSZoneInfoSource == null) {
                    break;
                }
                Object obj;
                str2 = str2 + gNSZoneInfoSource.k;
                Iterator it2 = this.j.iterator();
                while (it2.hasNext()) {
                    GNSAdaptee gNSAdaptee = (GNSAdaptee) it2.next();
                    if (gNSAdaptee != null) {
                        if (gNSZoneInfoSource.k.equals(gNSAdaptee.getAdnetworkName())) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    break;
                }
                obj = null;
                if (obj != null) {
                    str = str2 + "(o),";
                } else {
                    str = str2 + "(x),";
                }
                str2 = str;
            }
            this.o.d("Abst", "TmpAD(" + this.j.size() + ") \u512a\u5148 " + str2 + " \u52a3\u5f8c");
        }
    }

    protected void j() {
        if (this.o.b() <= 3) {
            String str = BuildConfig.FLAVOR;
            Iterator it = this.f.a.iterator();
            String str2 = str;
            while (it.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
                if (gNSZoneInfoSource == null) {
                    break;
                }
                Object obj;
                str2 = str2 + gNSZoneInfoSource.k;
                Iterator it2 = this.k.iterator();
                while (it2.hasNext()) {
                    GNSAdaptee gNSAdaptee = (GNSAdaptee) it2.next();
                    if (gNSAdaptee != null) {
                        if (gNSZoneInfoSource.k.equals(gNSAdaptee.getAdnetworkName())) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    break;
                }
                obj = null;
                if (obj != null) {
                    str = str2 + "(o),";
                } else {
                    str = str2 + "(x),";
                }
                str2 = str;
            }
            this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD(" + this.k.size() + ") \u512a\u5148 " + str2 + " \u52a3\u5f8c");
        }
    }

    protected void k() {
        if (this.o.b() <= 3) {
            String str = BuildConfig.FLAVOR;
            Iterator it = this.f.a.iterator();
            String str2 = str;
            while (it.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
                if (gNSZoneInfoSource == null) {
                    break;
                }
                Object obj;
                str2 = str2 + gNSZoneInfoSource.k;
                Iterator it2 = this.l.iterator();
                while (it2.hasNext()) {
                    GNSZoneInfoSource gNSZoneInfoSource2 = (GNSZoneInfoSource) it2.next();
                    if (gNSZoneInfoSource2 != null) {
                        if (gNSZoneInfoSource.k.equals(gNSZoneInfoSource2.k)) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    break;
                }
                obj = null;
                if (obj != null) {
                    str = str2 + "(o),";
                } else {
                    str = str2 + "(x),";
                }
                str2 = str;
            }
            this.o.d("Abst", "\u672a\u51e6\u7406AD(" + this.l.size() + ") \u512a\u5148 " + str2 + " \u52a3\u5f8c");
        }
    }

    protected void a(boolean z) {
        this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u53d6\u5f97\u51e6\u7406\u30d5\u30e9\u30b0 set " + z);
        this.a = z;
    }

    protected boolean e() {
        this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u53d6\u5f97\u51e6\u7406\u30d5\u30e9\u30b0 " + this.a);
        return this.a;
    }

    protected void l() {
        if (this.f != null) {
            this.o.d("Abst", "\u672a\u51e6\u7406AD\u3092\u521d\u671f\u5316");
            this.l.clear();
            Iterator it = this.f.a.iterator();
            while (it.hasNext()) {
                this.l.add((GNSZoneInfoSource) it.next());
            }
        }
    }

    protected GNSAdaptee c(GNSZoneInfoSource gNSZoneInfoSource) {
        GNSAdaptee createWorker = GNSAdaptee.createWorker(gNSZoneInfoSource.k);
        if (createWorker == null || !createWorker.isEnable()) {
            this.o.d("Abst", "TmpAD\u751f\u6210\u5931\u6557 " + gNSZoneInfoSource.k);
            if (createWorker == null) {
                this.o.d("Abst", "TmpAD\u304cnull " + gNSZoneInfoSource.k);
            } else if (!createWorker.isEnable()) {
                this.o.d("Abst", "TmpAD\u304c\u5b58\u5728\u3057\u306a\u3044 " + gNSZoneInfoSource.k);
            }
            return null;
        } else if (n()) {
            return null;
        } else {
            createWorker.setRewardVideoAdListener(this.m);
            createWorker.setAdapterWorkerListener(this.n);
            createWorker.setUp(this.c, this.d, this.f, gNSZoneInfoSource, this.e);
            createWorker.start();
            createWorker.resume(this.c);
            this.j.add(createWorker);
            this.o.d("Abst", "TmpAD\u751f\u6210 " + gNSZoneInfoSource.k);
            return createWorker;
        }
    }

    protected boolean m() {
        this.o.d("Abst", "\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u306e\u53d6\u5f97\u5224\u5b9a");
        if (this.k.size() == 0) {
            this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u304c\u307e\u3060\u306a\u3044");
            return false;
        }
        if (this.k.size() > 0 && this.l.size() > 0) {
            GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) this.l.get(0);
            GNSAdaptee h = h();
            if (!(gNSZoneInfoSource == null || h == null)) {
                this.o.d("Abst", "\u6b21\u306e\u672a\u51e6\u7406AD " + gNSZoneInfoSource.k);
                Iterator it = this.f.a.iterator();
                while (it.hasNext()) {
                    GNSZoneInfoSource gNSZoneInfoSource2 = (GNSZoneInfoSource) it.next();
                    if (gNSZoneInfoSource2.k.equals(h.getAdnetworkName())) {
                        this.o.d("Abst", "\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3067\u304d\u3066\u308b " + h.getAdnetworkName());
                        j();
                        return true;
                    } else if (gNSZoneInfoSource2.k.equals(gNSZoneInfoSource.k)) {
                        this.o.d("Abst", "\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3067\u304d\u3066\u306a\u3044 " + gNSZoneInfoSource.k);
                        j();
                        return false;
                    }
                }
            }
        }
        if (this.k.size() <= 0 || this.l.size() != 0) {
            this.o.d("Abst", "\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u3092\u53d6\u5f97\u3067\u304d\u3066\u306a\u3044");
            return false;
        }
        this.o.d("Abst", "\u672a\u51e6\u7406AD\u304c\u306a\u3044\u306e\u3067\u512a\u5148\u5ea6\u306e\u9ad8\u3044AD\u304c\u53d6\u5f97\u3067\u304d\u3066\u308b");
        return true;
    }

    protected void b(GNSAdaptee gNSAdaptee) {
        Object obj = null;
        this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u8ffd\u52a0\u51e6\u7406 " + gNSAdaptee.getAdnetworkName());
        k();
        i();
        j();
        if (gNSAdaptee != null) {
            try {
                if (gNSAdaptee.canShow()) {
                    Iterator it = this.k.iterator();
                    while (it.hasNext()) {
                        Object obj2;
                        if (gNSAdaptee.getAdnetworkName().equals(((GNSAdaptee) it.next()).getAdnetworkName())) {
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        obj = obj2;
                    }
                    if (obj == null) {
                        this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u306b\u8ffd\u52a0 " + gNSAdaptee.getAdnetworkName());
                        this.k.add(gNSAdaptee);
                    } else {
                        this.o.d("Abst", "\u65e2\u306b\u8ffd\u52a0\u6e08\u307f\u306a\u306e\u3067\u518d\u751f\u5f85\u6a5fAD\u306b\u8ffd\u52a0\u3057\u306a\u3044 " + gNSAdaptee.getAdnetworkName());
                    }
                }
            } catch (Exception e) {
                this.o.f("Abst", "\u518d\u751f\u5f85\u6a5fAD\u8ffd\u52a0\u51e6\u7406\u30a8\u30e9\u30fc");
                this.o.f("Abst", e.getMessage());
            }
        }
        Iterator it2 = this.l.iterator();
        while (it2.hasNext()) {
            GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it2.next();
            if (gNSZoneInfoSource != null && gNSAdaptee != null && gNSAdaptee.getAdnetworkName().equals(gNSZoneInfoSource.k)) {
                this.o.d("Abst", "\u51e6\u7406\u6e08\u307f\u306a\u306e\u3067\u672a\u51e6\u7406AD\u304b\u3089\u524a\u9664 " + gNSZoneInfoSource.k);
                this.l.remove(gNSZoneInfoSource);
                break;
            }
        }
        this.o.d("Abst", "\u30ed\u30fc\u30c9\u901a\u77e5\u304c\u5fc5\u8981:" + this.b);
        k();
        i();
        j();
        if (this.b && this.k.size() >= 1 && this.m != null) {
            if (n()) {
                this.o.d("Abst", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u3067\u306f\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f\u3057\u3066\u3082\u901a\u77e5\u3057\u306a\u3044\u3002");
            } else {
                this.i.a(MediatorNotifyStatus.SUCCESS, new GNSVideoRewardException("Geniee", 0));
            }
        }
        this.o.d("Abst", "\u518d\u751f\u5f85\u6a5fAD\u8ffd\u52a0\u51e6\u7406\u5f8c");
        k();
        i();
        j();
    }

    protected void b(GNSVideoRewardException gNSVideoRewardException) {
        Iterator it;
        int i;
        int i2;
        GNSAdapteeStatus status;
        int i3;
        this.o.d("Abst", "loadRewardVideoAdwithError " + gNSVideoRewardException.a());
        k();
        i();
        j();
        try {
            Iterator it2 = this.l.iterator();
            while (it2.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it2.next();
                if (gNSZoneInfoSource != null && gNSZoneInfoSource.k.equals(gNSVideoRewardException.a())) {
                    it = this.j.iterator();
                    while (it.hasNext()) {
                        GNSAdaptee gNSAdaptee = (GNSAdaptee) it.next();
                        if (gNSAdaptee != null && gNSAdaptee.getAdnetworkName().equals(gNSVideoRewardException.a())) {
                            if (gNSAdaptee.canShow()) {
                                this.o.d("Abst", "\u5728\u5eab\u30b3\u30fc\u30eb\u30d0\u30c3\u30af\u304c\u306a\u304b\u3063\u305f\u304c\u5728\u5eab\u3042\u308a(\u6551\u6e08\u63aa\u7f6e) " + gNSZoneInfoSource.k);
                                a(gNSAdaptee);
                                return;
                            }
                            this.o.d("Abst", "\u51e6\u7406\u3067\u304d\u306a\u304b\u3063\u305f\u306e\u3067\u672a\u51e6\u7406AD\u304b\u3089\u524a\u9664 " + gNSZoneInfoSource.k);
                            this.l.remove(gNSZoneInfoSource);
                            this.o.d("Abst", "\u51e6\u7406\u3067\u304d\u306a\u304b\u3063\u305f\u306e\u3067TmpAD\u304b\u3089\u524a\u9664 " + gNSAdaptee.getAdnetworkName());
                            this.j.remove(gNSAdaptee);
                            k();
                            i();
                            it = this.j.iterator();
                            i = 0;
                            i2 = 0;
                            while (it.hasNext()) {
                                status = ((GNSAdaptee) it.next()).getStatus();
                                if (status == GNSAdapteeStatus.INIT) {
                                    i3 = i;
                                    i = i2 + 1;
                                } else if (status != GNSAdapteeStatus.EXISTS) {
                                    i3 = i + 1;
                                    i = i2;
                                } else {
                                    i3 = i;
                                    i = i2;
                                }
                                i2 = i;
                                i = i3;
                            }
                            this.o.d("Abst", "TmpAD\u5185\u306e\u672a\u51e6\u7406ADNW\u6570=" + i2);
                            this.o.d("Abst", "TmpAD\u5185\u306e\u518d\u751f\u53ef\u80fdADNW\u6570=" + i);
                            k();
                            i();
                            if (i2 == 0 && i == 0 && this.l.size() == 0) {
                                if (n()) {
                                    this.o.d("Abst", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u3067\u306f\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557\u3057\u3066\u3082\u901a\u77e5\u3057\u306a\u3044\u3002");
                                } else {
                                    this.i.a(MediatorNotifyStatus.FAIL, gNSVideoRewardException);
                                    this.o.d("Abst", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off");
                                    this.i.a(Boolean.valueOf(false));
                                }
                                a(false);
                                return;
                            }
                            return;
                        }
                    }
                    continue;
                }
            }
        } catch (Exception e) {
            this.o.f("Abst", "\u672a\u51e6\u7406AD&TmpAD\u524a\u9664\u30a8\u30e9\u30fc");
            this.o.f("Abst", e.getMessage());
        }
        it = this.j.iterator();
        i = 0;
        i2 = 0;
        while (it.hasNext()) {
            status = ((GNSAdaptee) it.next()).getStatus();
            if (status == GNSAdapteeStatus.INIT) {
                i3 = i;
                i = i2 + 1;
            } else if (status != GNSAdapteeStatus.EXISTS) {
                i3 = i;
                i = i2;
            } else {
                i3 = i + 1;
                i = i2;
            }
            i2 = i;
            i = i3;
        }
        this.o.d("Abst", "TmpAD\u5185\u306e\u672a\u51e6\u7406ADNW\u6570=" + i2);
        this.o.d("Abst", "TmpAD\u5185\u306e\u518d\u751f\u53ef\u80fdADNW\u6570=" + i);
        k();
        i();
        if (i2 == 0) {
        }
    }

    protected boolean n() {
        if (this.i == null) {
            this.o.d("Abst", "needTaskStop() mGNSVideoMediator does not exist.");
        }
        return this.i == null || this.i.c();
    }

    protected void c() {
        this.o.d("Abst", "stop");
        if (this.i.d()) {
            this.o.d("Abst", "******\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u306b\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u3078\u79fb\u52d5******");
            this.i.b(Boolean.valueOf(true));
            this.o.d("Abst", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u304con\u306a\u306e\u3067\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u3067\u51e6\u7406\u3092\u7d99\u7d9a\u3059\u308b\u304b\u5224\u5b9a\u3059\u308b");
            if (this.b) {
                this.o.d("Abst", "\u6210\u529f\u672a\u901a\u77e5\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u306fon\u306e\u307e\u307e");
                return;
            }
            this.o.d("Abst", "\u6210\u529f\u901a\u77e5\u6e08\u307f\u306a\u306e\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0off");
            this.i.a(Boolean.valueOf(false));
            return;
        }
        this.o.d("Abst", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u3078\u79fb\u52d5\u3057\u305f\u304c\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u3067\u306f\u306a\u3044");
    }
}
