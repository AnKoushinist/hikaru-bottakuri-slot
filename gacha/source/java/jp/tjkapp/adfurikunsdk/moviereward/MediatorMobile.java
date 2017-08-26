package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.tjkapp.adfurikunsdk.moviereward.AdnetworkWorker.AdnetworkWorkerListener;

class MediatorMobile implements IMediator {
    Activity a;
    String b;
    String c;
    AdInfo d;
    AdInfo e;
    Handler f;
    MovieMediater g;
    ArrayList<AdnetworkWorker> h;
    LinkedList<AdnetworkWorker> i;
    ArrayList<AdInfoDetail> j;
    ArrayList<AdInfoDetail> k;
    int l;
    AdfurikunMovieListener m;
    AdnetworkWorkerListener n;
    LogUtil o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private Runnable t = new Runnable(this) {
        final /* synthetic */ MediatorMobile a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.o.debug(Constants.TAG, "\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u306e\u4f5c\u6210\u72b6\u6cc1\u78ba\u8a8d: start");
            this.a.q = false;
            if (this.a.b()) {
                this.a.o.detail(Constants.TAG, "\u30a2\u30d7\u30ea\u505c\u6b62\u4e2d: \u4f5c\u6210\u72b6\u6cc1\u78ba\u8a8d\u3092\u7d42\u4e86");
            } else if (2 <= this.a.i.size()) {
                this.a.o.debug(Constants.TAG, "\u518d\u751f\u5f85\u3061\u30ea\u30b9\u30c8\u304c\u6700\u5927\u6570: \u4f5c\u6210\u72b6\u6cc1\u78ba\u8a8d\u3092\u7d42\u4e86");
            } else {
                if (this.a.h.size() >= this.a.j.size() + this.a.k.size()) {
                    this.a.o.debug(Constants.TAG, "\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u3092\u5168\u3066\u4f5c\u6210\u6e08\u307f: \u4f5c\u6210\u72b6\u6cc1\u78ba\u8a8d\u3092\u7d42\u4e86");
                    this.a.c();
                    return;
                }
                int i;
                AdInfoDetail adInfoDetail;
                AdnetworkWorker a;
                this.a.q = true;
                int size = 2 - this.a.i.size();
                this.a.o.detail(Constants.TAG, "\u6e96\u5099\u8a66\u884c\u6570: " + size);
                int i2 = size;
                for (i = 0; i < this.a.j.size() && i2 > 0; i++) {
                    adInfoDetail = (AdInfoDetail) this.a.j.get(i);
                    a = this.a.a(adInfoDetail);
                    if (a != null) {
                        a.preload();
                    } else if (this.a.b(adInfoDetail)) {
                        i2--;
                    }
                }
                if (i2 > 0) {
                    this.a.o.detail(Constants.TAG, "\u901a\u5e38\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u306e\u6e96\u5099\u8a66\u884c\u958b\u59cb");
                    i = i2;
                    i2 = 0;
                    while (i2 < 2 && i > 0 && this.a.k.size() != 0) {
                        if (this.a.l >= this.a.k.size()) {
                            this.a.l = 0;
                        }
                        ArrayList arrayList = this.a.k;
                        MediatorMobile mediatorMobile = this.a;
                        int i3 = mediatorMobile.l;
                        mediatorMobile.l = i3 + 1;
                        adInfoDetail = (AdInfoDetail) arrayList.get(i3);
                        a = this.a.a(adInfoDetail);
                        if (a != null) {
                            this.a.o.debug(Constants.TAG, "preload\u3092\u958b\u59cb: " + a.getAdnetworkKey());
                            a.preload();
                            size = i;
                        } else if (this.a.b(adInfoDetail)) {
                            this.a.o.debug(Constants.TAG, "\u65b0\u898f\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u3092\u8ffd\u52a0: " + adInfoDetail.adnetworkKey);
                            size = i - 1;
                        } else {
                            this.a.k.remove(adInfoDetail);
                            size = i;
                        }
                        i2++;
                        i = size;
                    }
                }
                this.a.c();
                this.a.q = false;
            }
        }
    };
    private Runnable u = new Runnable(this) {
        final /* synthetic */ MediatorMobile a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.o.debug(Constants.TAG, "\u52d5\u753b\u306e\u6e96\u5099\u72b6\u6cc1\u3092\u78ba\u8a8d: start");
            if (this.a.b()) {
                this.a.o.detail(Constants.TAG, "\u30a2\u30d7\u30ea\u505c\u6b62\u4e2d: CheckPrepareTask\u3092\u7d42\u4e86");
                this.a.p = false;
                return;
            }
            this.a.p = true;
            int i = 0;
            while (i < this.a.h.size()) {
                try {
                    AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.h.get(i);
                    if (!(this.a.i.contains(adnetworkWorker) || adnetworkWorker == null || !adnetworkWorker.isPrepared())) {
                        this.a.o.debug(Constants.TAG, "\u518d\u751f\u5f85\u3061\u306b\u8ffd\u52a0: " + adnetworkWorker.getAdnetworkKey());
                        this.a.i.add(adnetworkWorker);
                        if (this.a.r && this.a.i.size() == 1 && this.a.m != null) {
                            this.a.r = false;
                            this.a.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.m.onPrepareSuccess();
                                }
                            });
                        }
                    }
                    i++;
                } catch (Exception e) {
                    this.a.o.detail_e(Constants.TAG, "\u30ad\u30e3\u30ea\u30a2: CheckPrepareTask");
                    this.a.o.detail_e(Constants.TAG, e.getMessage());
                }
            }
            i = this.a.k.size() + this.a.j.size();
            this.a.o.debug(Constants.TAG, "\u6709\u52b9\u306a\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u6570: " + i);
            this.a.o.debug(Constants.TAG, "\u4f5c\u6210\u6e08\u307f\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u6570: " + this.a.h.size());
            this.a.o.debug(Constants.TAG, "\u518d\u751f\u5f85\u3061\u6570: " + this.a.i.size());
            int i2 = i >= 2 ? 2 : i;
            if (this.a.i.size() >= i2 || i < i2) {
                this.a.s = 0;
                this.a.p = false;
                this.a.o.detail(Constants.TAG, "\u78ba\u8a8d\u51e6\u7406\u3092\u505c\u6b62");
                return;
            }
            this.a.f.postDelayed(this.a.u, RFLConstants.GPS_TIME);
            if (this.a.s % 2 == 1) {
                this.a.o.detail(Constants.TAG, "\u6b21\u306e\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u4f5c\u6210\u3092\u30ea\u30af\u30a8\u30b9\u30c8");
                this.a.d();
            }
            this.a.s = this.a.s + 1;
        }
    };

    MediatorMobile() {
    }

    private AdnetworkWorker a(AdInfoDetail adInfoDetail) {
        int i = 0;
        while (i < this.h.size()) {
            try {
                AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.h.get(i);
                if (adnetworkWorker != null && adnetworkWorker.getAdnetworkKey().equals(adInfoDetail.adnetworkKey)) {
                    return adnetworkWorker;
                }
                i++;
            } catch (Exception e) {
                this.o.detail_e(Constants.TAG, "\u30ad\u30e3\u30ea\u30a2: findWorkerList");
                this.o.detail_e(Constants.TAG, e.getMessage());
            }
        }
        return null;
    }

    private boolean b(AdInfoDetail adInfoDetail) {
        this.o.debug_w(Constants.TAG, "\u4f5c\u6210\u5bfe\u8c61: " + adInfoDetail.adnetworkKey);
        AdnetworkWorker a = AdnetworkWorker.a(adInfoDetail.adnetworkKey, this.d.bannerKind);
        if (a == null || !a.isEnable()) {
            this.o.detail_i(Constants.TAG, "\u4f5c\u6210\u3067\u304d\u306a\u3044: " + adInfoDetail.adnetworkKey);
            return false;
        } else if (b()) {
            return false;
        } else {
            a.setAdfurikunMovieListener(this.m);
            a.setAdnetworkWorkerListener(this.n);
            a.init(this.a, this.b, adInfoDetail, this.c, this.f);
            a.start();
            a.resume(this.a);
            a.preload();
            this.h.add(a);
            this.o.detail_i(Constants.TAG, "\u4f5c\u6210\u3057\u305f: " + adInfoDetail.adnetworkKey);
            return true;
        }
    }

    private void a() {
        if (this.d != null) {
            this.o.detail_i(Constants.TAG, "PriorityList\u3092\u521d\u671f\u5316");
            this.j.clear();
            this.k.clear();
            Iterator it = this.d.adInfoDetailArray.iterator();
            while (it.hasNext()) {
                this.k.add((AdInfoDetail) it.next());
            }
        }
    }

    private synchronized void a(AdInfo adInfo) {
        if (adInfo != null) {
            if (this.d != adInfo) {
                this.d = adInfo;
                this.o.detail_i(Constants.TAG, "GetInfo\u3092\u66f4\u65b0");
                this.d.sortOnWeighting();
                a();
                d();
            }
        }
    }

    private boolean b() {
        return this.g == null || this.g.needTaskStop();
    }

    public void init(Activity activity, String str, String str2, Handler handler, ArrayList<AdnetworkWorker> arrayList, LinkedList<AdnetworkWorker> linkedList, AdnetworkWorkerListener adnetworkWorkerListener, MovieMediater movieMediater) {
        this.a = activity;
        this.b = str;
        this.c = str2;
        this.f = handler;
        this.h = arrayList;
        this.i = linkedList;
        this.n = adnetworkWorkerListener;
        this.g = movieMediater;
        this.o = LogUtil.getInstance(this.a);
        this.j = new ArrayList(1);
        this.k = new ArrayList(4);
    }

    public synchronized void start() {
        this.o.detail_e(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u958b\u59cb: \u30ad\u30e3\u30ea\u30a2");
        if (this.e != null) {
            a(this.e);
            this.e = null;
        } else if (this.d != null) {
            d();
            c();
        }
        this.s = 0;
    }

    public void stop() {
        this.o.detail_i(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u505c\u6b62: \u30ad\u30e3\u30ea\u30a2");
    }

    public void destroy() {
        this.o.detail_i(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u7834\u68c4: \u30ad\u30e3\u30ea\u30a2");
    }

    private void c() {
        this.o.detail_i(Constants.TAG, "requestCheckPrepare call: \u30ad\u30e3\u30ea\u30a2");
        if (!this.p) {
            this.s = 0;
            this.p = true;
            this.f.post(this.u);
        }
    }

    private void d() {
        this.o.detail_i(Constants.TAG, "requestNextPrepare call: \u30ad\u30e3\u30ea\u30a2");
        this.o.detail_i(Constants.TAG, "mNextPrepareRunnning: \u30ad\u30e3\u30ea\u30a2 -> " + this.q);
        if (!this.q) {
            this.q = true;
            this.a.runOnUiThread(this.t);
        }
    }

    public void setAdInfo(AdInfo adInfo) {
        if (b()) {
            this.e = adInfo;
        } else {
            a(adInfo);
        }
    }

    public void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener) {
        this.m = adfurikunMovieListener;
    }

    public void setNeedNotify(boolean z) {
        this.r = z;
    }
}
