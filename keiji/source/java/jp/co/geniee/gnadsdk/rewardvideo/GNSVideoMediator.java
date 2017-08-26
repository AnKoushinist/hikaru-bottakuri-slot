package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeListener;
import jp.co.geniee.gnadsdk.rewardvideo.GNSZoneGetCommand.GNSZoneGetCommandListener;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.TwitterResponse;

public class GNSVideoMediator {
    public boolean a;
    private ArrayList<GNSAdaptee> b;
    private LinkedList<GNSAdaptee> c;
    private Activity d;
    private String e;
    private String f;
    private GNSZoneGetCommand g;
    private GNSIMediator h;
    private HashMap<Integer, GNSIMediator> i;
    private GNSLogger j;
    private Handler k;
    private GNSRewardVideoAdListener l;
    private WorkerListener m;
    private int n;
    private boolean o;
    private GNSZoneInfo p = new GNSZoneInfo();
    private boolean q;
    private boolean r = false;
    private MediatorCycleState s;
    private GNSZoneGetCommandListener t = new GNSZoneGetCommandListener(this) {
        final /* synthetic */ GNSVideoMediator a;

        {
            this.a = r1;
        }

        public void a(GNSZoneInfo gNSZoneInfo) {
            if (gNSZoneInfo == null) {
                return;
            }
            if (GNSVideoTerm.a(this.a.d)) {
                try {
                    throw new GNSVideoRewardException("Geniee", 10531);
                } catch (GNSVideoRewardException e) {
                    this.a.a(MediatorNotifyStatus.FAIL, e);
                }
            } else if (gNSZoneInfo.b == 0) {
                try {
                    throw new GNSVideoRewardException("Geniee", 10502);
                } catch (GNSVideoRewardException e2) {
                    this.a.a(MediatorNotifyStatus.FAIL, e2);
                }
            } else if (this.a.h != null) {
                this.a.p = gNSZoneInfo;
                this.a.j.d("Mediator", "API\u304b\u3089ZoneInfo\u53d6\u5f97");
                this.a.h.a(gNSZoneInfo);
            }
        }

        public void a(int i, String str, Exception exception) {
            this.a.j.a("Mediator", "Zone\u30c7\u30fc\u30bf\u304c\u3042\u308a\u307e\u305b\u3093\u3002" + this.a.n + " " + str);
            if (this.a.n > 5) {
                try {
                    throw new GNSVideoRewardException("Geniee", 10501);
                } catch (GNSVideoRewardException e) {
                    this.a.a(MediatorNotifyStatus.FAIL, e);
                }
            } else if (!this.a.c()) {
                this.a.j.a("Mediator", "ZoneInfo\u518d\u53d6\u5f97\u958b\u59cb");
                this.a.n = this.a.n + 1;
                this.a.k.postDelayed(this.a.u, (long) (this.a.n * GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
        }
    };
    private Runnable u = new Runnable(this) {
        final /* synthetic */ GNSVideoMediator a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.c()) {
                this.a.j.a("Mediator", "ZoneInfoRetryTask\u3092\u7d42\u4e86");
            } else if (this.a.g != null) {
                this.a.g.c();
            }
        }
    };

    enum MediatorCycleState {
        INIT,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    enum MediatorNotifyStatus {
        SUCCESS,
        FAIL
    }

    private class WorkerListener implements GNSAdapteeListener {
        final /* synthetic */ GNSVideoMediator a;

        private WorkerListener(GNSVideoMediator gNSVideoMediator) {
            this.a = gNSVideoMediator;
        }

        public void a(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData) {
            this.a.j.d("Mediator", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f " + gNSAdaptee.getAdnetworkName());
            this.a.h.a(gNSAdaptee);
        }

        public void a(GNSVideoRewardException gNSVideoRewardException) {
            this.a.j.d("Mediator", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 " + gNSVideoRewardException.a());
            this.a.h.a(gNSVideoRewardException);
        }

        public void b(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
            this.a.j.d("Mediator", "\u52d5\u753b\u5e83\u544a\u306e\u518d\u751f\u958b\u59cb " + gNSAdaptee.getAdnetworkName());
            if (this.a.l != null) {
                this.a.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ WorkerListener c;

                    public void run() {
                        this.c.a.j.a("Mediator", gNSAdaptee.getAdnetworkName() + ":\u52d5\u753b\u5e83\u544a\u306e\u518d\u751f\u958b\u59cb \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5");
                        this.c.a.l.a(gNSVideoRewardData);
                    }
                });
            }
        }

        public void c(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
            this.a.j.d("Mediator", "\u30e6\u30fc\u30b6\u306b\u30ea\u30ef\u30fc\u30c9\u3092\u4ed8\u4e0e " + gNSAdaptee.getAdnetworkName());
            if (this.a.l != null) {
                this.a.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ WorkerListener c;

                    public void run() {
                        this.c.a.j.a("Mediator", gNSAdaptee.getAdnetworkName() + ":\u30e6\u30fc\u30b6\u306b\u30ea\u30ef\u30fc\u30c9\u3092\u4ed8\u4e0e\u3059\u308b \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5");
                        this.c.a.l.b(gNSVideoRewardData);
                    }
                });
            }
        }

        public void d(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
            this.a.j.d("Mediator", "\u52d5\u753b\u5e83\u544a\u304c\u9589\u3058\u3089\u308c\u305f " + gNSAdaptee.getAdnetworkName());
            if (this.a.l != null) {
                this.a.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ WorkerListener c;

                    public void run() {
                        this.c.a.j.a("Mediator", gNSAdaptee.getAdnetworkName() + ":\u52d5\u753b\u5e83\u544a\u304c\u9589\u3058\u3089\u308c\u305f \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5");
                        this.c.a.l.c(gNSVideoRewardData);
                    }
                });
            }
        }
    }

    public GNSVideoMediator(Activity activity, String str, String str2) {
        this.d = activity;
        this.e = str;
        this.f = str2;
        this.j = GNSLogger.a();
        this.g = new GNSZoneGetCommand(this.d, this.e, this.f);
        HandlerThread handlerThread = new HandlerThread("gns_reward_video_ad");
        handlerThread.start();
        this.k = new Handler(handlerThread.getLooper());
        this.b = new ArrayList();
        this.c = new LinkedList();
        this.i = new HashMap();
        this.m = new WorkerListener();
        this.o = true;
        this.s = MediatorCycleState.INIT;
        a(Boolean.valueOf(false));
        this.j.d("Mediator", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092off GNSVideoMediator()");
    }

    public void a(GNSRewardVideoAdListener gNSRewardVideoAdListener) {
        this.l = gNSRewardVideoAdListener;
        for (int i = 0; i < this.b.size(); i++) {
            GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
            if (gNSAdaptee != null) {
                gNSAdaptee.setRewardVideoAdListener(this.l);
            }
        }
        if (this.h != null) {
            this.h.a(this.l);
        }
    }

    public LinkedList<GNSAdaptee> a() {
        return this.c;
    }

    public GNSAdaptee b() {
        if (!this.c.isEmpty()) {
            this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5f\u304b\u3089AD\u53d6\u51fa api\u304b\u3089\u53d7\u4fe1\u3057\u305fAD\u6570=" + this.p.a.size());
            this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5f\u304b\u3089AD\u53d6\u51fa \u518d\u751f\u5f85\u6a5fAD\u6570=" + this.c.size());
            Iterator it = this.p.a.iterator();
            while (it.hasNext()) {
                GNSZoneInfoSource gNSZoneInfoSource = (GNSZoneInfoSource) it.next();
                this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5f\u304b\u3089AD\u53d6\u51fa api\u304b\u3089\u53d7\u4fe1\u3057\u305fAD=" + gNSZoneInfoSource.k);
                for (int i = 0; i < this.c.size(); i++) {
                    if (gNSZoneInfoSource.k.equals(((GNSAdaptee) this.c.get(i)).getAdnetworkName())) {
                        this.o = true;
                        this.h.b(this.o);
                        this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5f\u304b\u3089AD\u53d6\u51fa " + ((GNSAdaptee) this.c.get(i)).getAdnetworkName());
                        return (GNSAdaptee) this.c.remove(i);
                    }
                }
            }
        }
        return null;
    }

    private Object m() {
        this.j.d("Mediator", "mediator choose start");
        int o = o();
        GNSIMediator gNSIMediator = (GNSIMediator) this.i.get(Integer.valueOf(o));
        if (gNSIMediator != null) {
            return gNSIMediator;
        }
        Object gNSMediatorImt;
        if (GNSEnv.a().e() == null) {
            switch (o) {
                case TwitterResponse.READ /*1*/:
                    this.j.a("Mediator", "wifi\u63a5\u7d9a");
                    if (!GNSPrefUtil.a(this.d)) {
                        this.j.d("Mediator", "adnetwork\u306b\u540c\u6642\u30a2\u30af\u30bb\u30b9\u3057\u306a\u3044");
                        gNSMediatorImt = new GNSMediatorImt();
                        break;
                    }
                    this.j.d("Mediator", "adnetwork\u306b\u540c\u6642\u30a2\u30af\u30bb\u30b9\u3059\u308b");
                    gNSMediatorImt = new GNSMediatorWifi();
                    break;
                default:
                    gNSMediatorImt = new GNSMediatorImt();
                    this.j.a("Mediator", "carrier\u63a5\u7d9a");
                    break;
            }
        }
        String e = GNSEnv.a().e();
        Object obj = -1;
        switch (e.hashCode()) {
            case 104400:
                if (e.equals("imt")) {
                    obj = null;
                    break;
                }
                break;
            case 3649301:
                if (e.equals(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                gNSIMediator = new GNSMediatorImt();
                this.j.a("Mediator", "carrier\u63a5\u7d9a");
                break;
            case TwitterResponse.READ /*1*/:
                gNSIMediator = new GNSMediatorWifi();
                this.j.a("Mediator", "wifi\u63a5\u7d9a");
                break;
        }
        gNSMediatorImt = gNSIMediator;
        ((GNSIMediator) gNSMediatorImt).a(this.d, this.e, this.f, this.k, this.b, this.c, this.m, this);
        this.i.put(Integer.valueOf(o), (GNSIMediator) gNSMediatorImt);
        return gNSMediatorImt;
    }

    private void a(Object obj) {
        if (this.h == obj) {
            this.j.d("Mediator", "mediator no change, ad request");
            this.h.b(this.o);
            this.h.a(this.l);
            if (!this.h.e()) {
                this.g.b();
            }
            this.h.a();
        } else if (this.h != null) {
            this.j.d("Mediator", "mediator stop");
            this.h.c();
            this.j.d("Mediator", "mediator change");
            this.h = (GNSIMediator) obj;
            this.h.b(this.o);
            this.h.a(this.l);
            if (!this.h.e()) {
                this.g.b();
            }
            this.j.d("Mediator", "mediator start");
            this.h.a();
        } else {
            this.j.d("Mediator", "mediator create");
            this.h = (GNSIMediator) obj;
            this.h.b(this.o);
            this.h.a(this.l);
            if (!this.h.e()) {
                this.g.b();
            }
            this.j.d("Mediator", "mediator start");
            this.h.a();
        }
    }

    private synchronized void n() {
        a(m());
    }

    private int o() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.d.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? 0 : 1;
    }

    public boolean c() {
        if (this.s == MediatorCycleState.STOP) {
            this.j.d("Mediator", "status STOP");
        } else if (this.s == MediatorCycleState.DESTROY) {
            this.j.d("Mediator", "status DESTROY");
        }
        return this.s == MediatorCycleState.STOP || this.s == MediatorCycleState.DESTROY;
    }

    public void a(Boolean bool) {
        this.j.d("Mediator", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0 set " + bool);
        this.q = bool.booleanValue();
    }

    public boolean d() {
        this.j.d("Mediator", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0 " + this.q);
        return this.q;
    }

    public void b(Boolean bool) {
        this.j.d("Mediator", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u518d\u5b9f\u884c set " + bool);
        this.r = bool.booleanValue();
    }

    public boolean e() {
        this.j.d("Mediator", "\u30d5\u30a9\u30a2\u30b0\u30e9\u30a6\u30f3\u30c9\u3067\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u518d\u5b9f\u884c " + this.r);
        return this.r;
    }

    public void f() {
        this.j.d("Mediator", "\u5e83\u544a\u30ea\u30af\u30a8\u30b9\u30c8");
        if (d()) {
            this.j.d("Mediator", "2\u91cd\u30ed\u30fc\u30c9\u9632\u6b62");
            return;
        }
        try {
            if (GNSVideoTerm.a() > GNSPrefUtil.b(this.d)) {
                throw new GNSVideoRewardException("Geniee", 10541);
            }
            this.j.d("Mediator", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0\u3092on loadRequest()");
            a(Boolean.valueOf(true));
            b(Boolean.valueOf(false));
            p();
            this.n = 0;
            n();
        } catch (GNSVideoRewardException e) {
            this.j.a("Mediator", e.a() + ":\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5 " + e.b() + ":" + e.getMessage());
            this.l.a(e);
        } catch (Exception e2) {
            this.j.e("Mediator", "An Exception has occurred.");
            this.j.e("Mediator", e2.getMessage());
        }
    }

    public void g() {
        a(Boolean.valueOf(false));
        this.j.d("Mediator", "\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u4e2d\u30d5\u30e9\u30b0off show()");
        this.h.a(false);
    }

    public void h() {
        this.s = MediatorCycleState.START;
        this.a = false;
        this.g.a(this.t);
        for (int i = 0; i < this.b.size(); i++) {
            GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
            if (gNSAdaptee != null) {
                gNSAdaptee.start();
            }
        }
    }

    public void i() {
        this.s = MediatorCycleState.RESUME;
        this.a = false;
        for (int i = 0; i < this.b.size(); i++) {
            GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
            if (gNSAdaptee != null) {
                gNSAdaptee.resume(this.d);
            }
        }
        if (d()) {
            this.j.d("Mediator", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u304b\u3089\u5fa9\u5e30\u3057\u3001\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u518d\u958b\u78ba\u8a8d");
            if (e()) {
                this.j.d("Mediator", "******\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u304b\u3089\u5fa9\u5e30\u3057\u3001\u30ed\u30fc\u30c9\u30ea\u30af\u30a8\u30b9\u30c8\u518d\u958b******");
                b(Boolean.valueOf(false));
                p();
                n();
                return;
            }
            this.j.d("Mediator", "\u30d0\u30c3\u30af\u30b0\u30e9\u30a6\u30f3\u30c9\u304b\u3089\u306e\u5fa9\u5e30\u3067\u306f\u306a\u3044");
        }
    }

    public void j() {
        this.s = MediatorCycleState.PAUSE;
        if (this.h != null) {
            this.h.b();
        }
        for (int i = 0; i < this.b.size(); i++) {
            GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
            if (gNSAdaptee != null) {
                gNSAdaptee.pause();
            }
        }
    }

    public void k() {
        this.s = MediatorCycleState.STOP;
        if (this.g != null) {
            this.g.a((GNSZoneGetCommandListener) null);
        }
        if (this.h != null) {
            this.h.c();
        }
        for (int i = 0; i < this.b.size(); i++) {
            GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
            if (gNSAdaptee != null) {
                gNSAdaptee.stop();
            }
        }
    }

    public void l() {
        this.s = MediatorCycleState.DESTROY;
        try {
            if (this.g != null) {
                this.g.a();
            }
            if (this.h != null) {
                this.h.d();
            }
            for (int i = 0; i < this.b.size(); i++) {
                GNSAdaptee gNSAdaptee = (GNSAdaptee) this.b.get(i);
                if (gNSAdaptee != null) {
                    gNSAdaptee.destroy();
                }
            }
            this.c.clear();
            this.b.clear();
        } catch (Exception e) {
        }
    }

    private void p() {
        this.j.d("Mediator", "clearWorkerMediatorList()");
        if (this.b.size() != 0) {
            this.j.d("Mediator", "API\u3092\u518d\u5ea6\u8aad\u307f\u8fbc\u3080\u305f\u3081\u4e00\u65e6\u30ef\u30fc\u30af\u30ea\u30b9\u30c8\u3092\u30af\u30ea\u30a2");
            if (this.h != null) {
                if (this.h.e()) {
                    this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5fAD\u51e6\u7406\u4e2d\u306a\u306e\u3067Zone\u60c5\u5831\u3092\u30af\u30ea\u30a2\u3057\u306a\u3044");
                } else {
                    this.j.d("Mediator", "\u518d\u751f\u5f85\u6a5fAD\u51e6\u7406\u4e2d\u3067\u306a\u3044\u306e\u3067API\u3092\u518d\u5ea6\u8aad\u307f\u8fbc\u3080\u305f\u3081Zone\u60c5\u5831\u3092\u30af\u30ea\u30a2");
                    this.h.f();
                }
            }
            this.b.clear();
        }
    }

    public synchronized void a(MediatorNotifyStatus mediatorNotifyStatus, final GNSVideoRewardException gNSVideoRewardException) {
        this.j.d("Mediator", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f/\u5931\u6557 \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5\u51e6\u7406");
        if (this.h.g()) {
            this.h.b(false);
            if (mediatorNotifyStatus == MediatorNotifyStatus.SUCCESS) {
                this.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ GNSVideoMediator a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.j.a("Mediator", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5");
                        this.a.l.a();
                    }
                });
            } else if (mediatorNotifyStatus == MediatorNotifyStatus.FAIL) {
                this.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ GNSVideoMediator b;

                    public void run() {
                        this.b.j.a("Mediator", gNSVideoRewardException.a() + ":\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557 \u30a2\u30d7\u30ea\u5074\u3078\u901a\u77e5 " + gNSVideoRewardException.b() + ":" + gNSVideoRewardException.getMessage());
                        this.b.l.a(gNSVideoRewardException);
                    }
                });
                this.h.a(false);
                a(Boolean.valueOf(false));
            } else {
                this.j.f("Mediator", "\u901a\u77e5\u30b9\u30c6\u30fc\u30bf\u30b9\u9055\u53cd\u3001\u3053\u3053\u306b\u306f\u3053\u306a\u3044\u306f\u305a");
            }
        } else if (mediatorNotifyStatus == MediatorNotifyStatus.SUCCESS) {
            this.j.d("Mediator", "\uff12\u91cd\u901a\u77e5\u9632\u6b62\u3001\u65e2\u306b\u901a\u77e5\u6e08\u307f \u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f");
        } else if (mediatorNotifyStatus == MediatorNotifyStatus.FAIL) {
            this.j.d("Mediator", "\uff12\u91cd\u901a\u77e5\u9632\u6b62\u3001\u65e2\u306b\u901a\u77e5\u6e08\u307f \u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557");
            this.h.a(false);
            a(Boolean.valueOf(false));
        } else {
            this.j.f("Mediator", "\uff12\u91cd\u901a\u77e5\u9632\u6b62\u3001\u65e2\u306b\u901a\u77e5\u6e08\u307f \u901a\u77e5\u30b9\u30c6\u30fc\u30bf\u30b9\u9055\u53cd\u3001\u3053\u3053\u306b\u306f\u3053\u306a\u3044\u306f\u305a");
        }
    }
}
