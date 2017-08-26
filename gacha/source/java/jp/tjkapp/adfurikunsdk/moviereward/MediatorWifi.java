package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import jp.tjkapp.adfurikunsdk.moviereward.AdnetworkWorker.AdnetworkWorkerListener;

class MediatorWifi implements IMediator {
    Activity a;
    String b;
    String c;
    AdInfo d;
    AdInfo e;
    Handler f;
    MovieMediater g;
    ArrayList<AdnetworkWorker> h;
    LinkedList<AdnetworkWorker> i;
    AdfurikunMovieListener j;
    AdnetworkWorkerListener k;
    LogUtil l;
    private boolean m;
    private boolean n;
    private int o;
    private Runnable p = new Runnable(this) {
        final /* synthetic */ MediatorWifi a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.l.detail(Constants.TAG, "start: mCheckPrepareTask");
            if (this.a.a()) {
                this.a.l.detail(Constants.TAG, "\u30a2\u30d7\u30ea\u505c\u6b62\u4e2d: CheckPrepareTask\u3092\u7d42\u4e86");
                this.a.m = false;
                return;
            }
            this.a.m = true;
            int i = 0;
            boolean z = false;
            while (i < this.a.h.size()) {
                try {
                    boolean z2;
                    AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.h.get(i);
                    if (this.a.i.contains(adnetworkWorker)) {
                        z2 = z;
                    } else if (adnetworkWorker == null || !adnetworkWorker.isPrepared()) {
                        if (adnetworkWorker != null && this.a.o % 5 == 0) {
                            adnetworkWorker.preload();
                        }
                        z2 = true;
                    } else {
                        this.a.l.debug(Constants.TAG, "\u518d\u751f\u5f85\u3061\u306b\u8ffd\u52a0: " + adnetworkWorker.getAdnetworkKey());
                        this.a.i.add(adnetworkWorker);
                        if (this.a.n && this.a.i.size() == 1 && this.a.j != null) {
                            this.a.n = false;
                            this.a.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.j.onPrepareSuccess();
                                }
                            });
                            z2 = z;
                        } else {
                            z2 = z;
                        }
                    }
                    i++;
                    z = z2;
                } catch (Exception e) {
                    this.a.l.detail_e(Constants.TAG, "Wifi: CheckPrepareTask");
                    this.a.l.detail_e(Constants.TAG, e.getMessage());
                    z = true;
                }
            }
            if (z) {
                long j = this.a.o < 10 ? 3000 : 60000;
                this.a.f.postDelayed(this.a.p, j);
                this.a.l.detail(Constants.TAG, (j / 1000) + "\u79d2\u5f8c\u306b\u30ea\u30c8\u30e9\u30a4");
                this.a.o = this.a.o + 1;
            } else {
                this.a.o = 0;
                this.a.m = false;
            }
            this.a.l.debug(Constants.TAG, "\u4f5c\u6210\u6e08\u307f\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u6570: " + this.a.h.size());
            this.a.l.debug(Constants.TAG, "\u518d\u751f\u5f85\u3061\u6570: " + this.a.i.size());
        }
    };
    private Runnable q = new Runnable(this) {
        final /* synthetic */ MediatorWifi a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.l.detail(Constants.TAG, "start: mSetupWorkerTask");
            if (this.a.a()) {
                this.a.l.detail(Constants.TAG, "\u30a2\u30d7\u30ea\u505c\u6b62\u4e2d: SetupWorkerTask\u3092\u7d42\u4e86");
                return;
            }
            AdInfoDetail adInfoDetail;
            Iterator it = this.a.d.adInfoDetailArray.iterator();
            while (it.hasNext()) {
                Object obj;
                AdInfoDetail adInfoDetail2 = (AdInfoDetail) it.next();
                int i = 0;
                while (i < this.a.h.size()) {
                    try {
                        AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.h.get(i);
                        if (adnetworkWorker != null && adInfoDetail2.adnetworkKey.equals(adnetworkWorker.getAdnetworkKey())) {
                            obj = 1;
                            continue;
                            break;
                        }
                        i++;
                    } catch (Exception e) {
                        this.a.l.detail_e(Constants.TAG, "Wifi: mSetupWorkerTask");
                        this.a.l.detail_e(Constants.TAG, e.getMessage());
                        int i2 = 1;
                        continue;
                    }
                }
                obj = null;
                continue;
                if (obj == null) {
                    adInfoDetail = adInfoDetail2;
                    break;
                }
            }
            adInfoDetail = null;
            if (adInfoDetail != null) {
                long j;
                AdnetworkWorker a = AdnetworkWorker.a(adInfoDetail.adnetworkKey, this.a.d.bannerKind);
                if (a == null || !a.isEnable()) {
                    if (a != null) {
                        this.a.l.debug(Constants.TAG, "\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u4f5c\u6210\u4e0d\u80fd: " + a.getAdnetworkKey());
                    }
                    this.a.d.adInfoDetailArray.remove(adInfoDetail);
                    j = 0;
                } else {
                    try {
                        if (!this.a.a()) {
                            this.a.h.add(a);
                            this.a.l.debug(Constants.TAG, "\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u4f5c\u6210: " + a.getAdnetworkKey());
                            a.setAdfurikunMovieListener(this.a.j);
                            a.setAdnetworkWorkerListener(this.a.k);
                            a.init(this.a.a, this.a.b, adInfoDetail, this.a.c, this.a.f);
                            a.start();
                            a.resume(this.a.a);
                            a.preload();
                            j = 4000;
                        } else {
                            return;
                        }
                    } catch (Exception e2) {
                        this.a.l.detail_e(Constants.TAG, "Wifi: mSetupWorkerTask");
                        this.a.l.detail_e(Constants.TAG, e2.getMessage());
                        j = 4000;
                    }
                }
                this.a.l.detail(Constants.TAG, "\u6b21\u306e\u30a2\u30c9\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u4f5c\u6210\u3092\u30ea\u30af\u30a8\u30b9\u30c8");
                this.a.f.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a.runOnUiThread(this.a.a.q);
                    }
                }, j);
            }
            this.a.b();
        }
    };

    MediatorWifi() {
    }

    private synchronized void a(AdInfo adInfo) {
        if (adInfo != null) {
            if (this.d != adInfo) {
                this.d = adInfo;
                this.l.detail(Constants.TAG, "GetInfo\u3092\u66f4\u65b0");
                this.d.sortOnWeighting();
                this.a.runOnUiThread(this.q);
            }
        }
    }

    private boolean a() {
        return this.g == null || this.g.needTaskStop();
    }

    public void init(Activity activity, String str, String str2, Handler handler, ArrayList<AdnetworkWorker> arrayList, LinkedList<AdnetworkWorker> linkedList, AdnetworkWorkerListener adnetworkWorkerListener, MovieMediater movieMediater) {
        this.a = activity;
        this.b = str;
        this.c = str2;
        this.f = handler;
        this.h = arrayList;
        this.i = linkedList;
        this.k = adnetworkWorkerListener;
        this.g = movieMediater;
        this.l = LogUtil.getInstance(this.a);
    }

    public synchronized void start() {
        this.l.detail_e(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u958b\u59cb: Wifi");
        if (this.e != null) {
            a(this.e);
            this.e = null;
        } else if (this.d != null) {
            if (this.d.adInfoDetailArray.size() > this.h.size()) {
                this.d.sortOnWeighting();
                this.a.runOnUiThread(this.q);
            } else {
                b();
            }
        }
        this.o = 0;
    }

    public void stop() {
        this.l.detail_i(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u505c\u6b62: Wifi");
        this.f.removeCallbacks(this.p);
        this.m = false;
    }

    public void destroy() {
        this.l.detail_i(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u7834\u68c4: Wifi");
    }

    private void b() {
        this.l.detail_i(Constants.TAG, "requestCheckPrepare: Wifi");
        this.o = 0;
        this.l.detail(Constants.TAG, "mCheckPrepareRunnning: " + this.m);
        if (!this.m) {
            this.m = true;
            this.f.postDelayed(this.p, 3000);
        }
    }

    public void setAdInfo(AdInfo adInfo) {
        if (a()) {
            this.e = adInfo;
        } else {
            a(adInfo);
        }
    }

    public void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener) {
        this.j = adfurikunMovieListener;
    }

    public void setNeedNotify(boolean z) {
        this.n = z;
    }
}
