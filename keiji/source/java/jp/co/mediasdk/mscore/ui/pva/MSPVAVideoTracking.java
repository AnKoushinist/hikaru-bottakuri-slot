package jp.co.mediasdk.mscore.ui.pva;

import java.util.ArrayList;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.NetUtil;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import jp.co.mediasdk.android.URIUtilBase;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

public class MSPVAVideoTracking {
    private int a;
    private String b;
    private ArrayList<Integer> c = new ArrayList();
    private boolean d = false;

    public void a() {
        this.a = -1;
        this.c.clear();
        this.b = BuildConfig.FLAVOR;
    }

    public void b() {
        this.c = new ArrayList(MSPVAVast.b());
        if (this.c.size() > 0) {
            this.a = ((Integer) this.c.get(0)).intValue() * GameControllerDelegate.THUMBSTICK_LEFT_X;
            this.b = MSPVAVast.e().a(((Integer) this.c.get(0)).intValue());
            this.c.remove(0);
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean c() {
        return this.d;
    }

    public boolean a(int i) {
        if (this.a != -1 && i > this.a) {
            return true;
        }
        return false;
    }

    public void d() {
        HandlerManager.a();
        final String j = MSPVAVast.a().j("Impression");
        new NetUtil().a(j, new NetUtilJSONCallback(this) {
            final /* synthetic */ MSPVAVideoTracking b;

            public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                if (!netUtil.e()) {
                    if (!MSPVATrackingCheck.b().contains(j)) {
                        MSPVATrackingCheck.a(j);
                    }
                    MSPVATrackingCheck.d();
                }
                netUtil.j();
            }
        });
    }

    public void b(final int i) {
        HandlerManager.a();
        for (int i2 = 0; i2 < MSPVAVast.c().size(); i2++) {
            final String a = a((String) MSPVAVast.c().get(i2), i);
            new NetUtil().a(a, new NetUtilJSONCallback(this) {
                final /* synthetic */ MSPVAVideoTracking c;

                public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                    if (!netUtil.e() && i == 0) {
                        if (!MSPVATrackingCheck.b().contains(a)) {
                            MSPVATrackingCheck.a(a);
                        }
                        MSPVATrackingCheck.d();
                    }
                    netUtil.j();
                }
            });
        }
    }

    public void c(final int i) {
        HandlerManager.a();
        NetUtil netUtil = new NetUtil();
        this.b = a(this.b, i);
        final String str = this.b;
        netUtil.a(this.b, new NetUtilJSONCallback(this) {
            final /* synthetic */ MSPVAVideoTracking c;

            public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                if (!netUtil.e() && i == 0) {
                    if (!MSPVATrackingCheck.b().contains(str)) {
                        MSPVATrackingCheck.a(str);
                    }
                    MSPVATrackingCheck.d();
                }
                netUtil.j();
            }
        });
        if (this.c.size() > 0) {
            this.a = ((Integer) this.c.get(0)).intValue() * GameControllerDelegate.THUMBSTICK_LEFT_X;
            this.b = MSPVAVast.e().a(((Integer) this.c.get(0)).intValue());
            this.c.remove(0);
            return;
        }
        this.a = -1;
    }

    public void d(final int i) {
        HandlerManager.a();
        for (int i2 = 0; i2 < MSPVAVast.d().size(); i2++) {
            final String a = a((String) MSPVAVast.d().get(i2), i);
            new NetUtil().a(a, new NetUtilJSONCallback(this) {
                final /* synthetic */ MSPVAVideoTracking c;

                public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                    if (!netUtil.e() && i == 0) {
                        if (!MSPVATrackingCheck.b().contains(a)) {
                            MSPVATrackingCheck.a(a);
                        }
                        MSPVATrackingCheck.d();
                    }
                    netUtil.j();
                }
            });
        }
    }

    public static String a(String str, int i) {
        if (str.indexOf("repeat") != -1) {
            HashMapEX hashMapEX = new HashMapEX();
            hashMapEX.i(URIUtilBase.c(str));
            if (!hashMapEX.a("repeat")) {
                return str;
            }
            return str.replace("repeat=" + Integer.valueOf(hashMapEX.j("repeat")), "repeat=" + String.valueOf(Integer.valueOf(hashMapEX.j("repeat")).intValue() + i));
        } else if (str.indexOf("?") != -1) {
            return str + "&repeat=" + i;
        } else {
            return str + "?repeat=" + i;
        }
    }
}
