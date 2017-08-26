package jp.co.mediasdk.mscore.ui.pva;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.NetUtil;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import org.cocos2dx.lib.GameControllerDelegate;

public class MSPVATrackingCheck {
    private static int a = 0;
    private static Handler b = new Handler();
    private static Timer c = null;
    private static TrackingApiCheckTask d = null;
    private static ArrayList<String> e = null;

    private static class TrackingApiCheckTask extends TimerTask {
        private TrackingApiCheckTask() {
        }

        public void run() {
            MSPVATrackingCheck.b.post(new Runnable(this) {
                final /* synthetic */ TrackingApiCheckTask a;

                {
                    this.a = r1;
                }

                public void run() {
                    MSPVATrackingCheck.c();
                    MSPVATrackingCheck.f();
                }
            });
        }
    }

    static /* synthetic */ int f() {
        int i = a;
        a = i + 1;
        return i;
    }

    public static void a() {
        a = 0;
    }

    public static ArrayList<String> b() {
        if (e == null) {
            e = new ArrayList();
        }
        return e;
    }

    public static void a(String str) {
        if (e == null) {
            e = new ArrayList();
        }
        if (!e.contains(str)) {
            e.add(str);
        }
    }

    public static void c() {
        if (a > 50) {
            e.clear();
            e();
            a = 0;
            return;
        }
        for (int i = 0; i < e.size(); i++) {
            b((String) e.get(i));
        }
    }

    public static void b(final String str) {
        HandlerManager.a();
        new NetUtil().a(str, new NetUtilJSONCallback() {
            public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                if (netUtil.e()) {
                    if (MSPVATrackingCheck.e.contains(str)) {
                        MSPVATrackingCheck.e.remove(str);
                    }
                    if (MSPVATrackingCheck.e.size() == 0) {
                        MSPVATrackingCheck.e();
                    }
                } else {
                    if (!MSPVATrackingCheck.e.contains(str)) {
                        MSPVATrackingCheck.e.add(str);
                    }
                    MSPVATrackingCheck.d();
                }
                netUtil.j();
            }
        });
    }

    public static void d() {
        e();
        c = new Timer(false);
        if (d == null) {
            d = new TrackingApiCheckTask();
        }
        c.schedule(d, (long) (((a + 1) * GameControllerDelegate.THUMBSTICK_LEFT_X) * 60), (long) (((a + 1) * GameControllerDelegate.THUMBSTICK_LEFT_X) * 60));
    }

    public static void e() {
        if (c != null) {
            c.cancel();
            c.purge();
            c = null;
            d = null;
        }
    }
}
