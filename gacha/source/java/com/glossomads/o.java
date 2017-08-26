package com.glossomads;

import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.c.e;
import com.glossomads.d.d;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;

public class o implements com.glossomads.c.d.b {
    private static b a = null;
    private static b b;
    private static int c = 30;
    private JSONObject d;

    private static class a {
        private static final o a = new o();
    }

    private enum b {
        IDLE,
        RUNNING,
        PAUSE
    }

    public static o a() {
        return a.a;
    }

    private o() {
        f();
    }

    private void f() {
        if (a == null) {
            a = b.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, s.a().d().getApplicationContext());
            b = b.IDLE;
        }
    }

    private static String d(String str) {
        return str.replace("__SDK_TIME__", SugarUtil.encode(String.valueOf(System.currentTimeMillis() / 1000))).replace("__USER_AGENT__", SugarUtil.encode(m.p())).replace("__SUGAR_SDK_VERSION__", SugarUtil.encode(m.w())).replace("__SCREEN_WIDTH__", SugarUtil.encode(String.valueOf(m.q().y))).replace("__SCREEN_HEIGHT__", SugarUtil.encode(String.valueOf(m.q().x))).replace("__OS_VERSION__", SugarUtil.encode(m.r())).replace("__OS__", SugarUtil.encode(m.g())).replace("__MODEL__", SugarUtil.encode(m.j())).replace("__HARDWARE_VERSION__", SugarUtil.encode(String.valueOf(1))).replace("__MAKER__", SugarUtil.encode(m.k())).replace("__LANGUAGE__", SugarUtil.encode(m.s())).replace("__IFA__", SugarUtil.encode(SugarUtil.isNotEmptyValue(m.a()) ? m.a() : BuildConfig.FLAVOR)).replace("__DEVICE_TYPE__", SugarUtil.encode(String.valueOf(m.B()))).replace("__COUNTRY__", SugarUtil.encode(m.u())).replace("__CONNECTION_TYPE__", SugarUtil.encode(String.valueOf(m.h()))).replace("__CARRIER_NAME__", SugarUtil.encode(m.D())).replace("__APP_VERSION__", SugarUtil.encode(m.v())).replace("__APP_BUNDLE__", SugarUtil.encode(m.A()));
    }

    public void a(String str) {
        Map hashMap = new HashMap();
        hashMap.put(String.URL, d(str));
        a.a(new JSONObject(hashMap));
        b();
    }

    public synchronized void b() {
        if (!(b.equals(b.RUNNING) || b.equals(b.PAUSE))) {
            b = b.IDLE;
            this.d = g();
            if (this.d != null) {
                b = b.RUNNING;
                String optString = this.d.optString(String.URL, null);
                if (d.m()) {
                    b(optString);
                }
            }
        }
    }

    private JSONObject g() {
        if (a.size() > 0) {
            return a.e();
        }
        return null;
    }

    private void h() {
        b = b.IDLE;
        q.b("SugareventRetry", 0);
        if (this.d != null) {
            a.remove(this.d);
            this.d = null;
        }
        b();
    }

    public void b(String str) {
        synchronized (o.class) {
            b = b.RUNNING;
            if (!SugarUtil.isNotEmptyValue(str)) {
                h();
            } else if (!com.glossomads.c.d.a(this, str, com.glossomads.c.d.a.GET, Constants.IP_RETRY_TIME, Constants.IP_RETRY_TIME)) {
                b = b.IDLE;
                b();
            }
        }
    }

    public void a(e eVar) {
        if (eVar.b || !eVar.a) {
            String c = eVar.c();
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.eventSendFailed, SugarUtil.decode(eVar.a()), c);
            c(eVar.a());
            return;
        }
        com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.eventSend, SugarUtil.decode(eVar.a()));
        h();
    }

    public void c(String str) {
        if (SugarUtil.isNotEmptyValue(str)) {
            int a = q.a("SugareventRetry", 0);
            if (a < 5) {
                if (d.m()) {
                    try {
                        int i = ((a * a) * c) * GameControllerDelegate.THUMBSTICK_LEFT_X;
                        SugarDebugLogger.d("wait before retry event(" + (i / GameControllerDelegate.THUMBSTICK_LEFT_X) + " sec)");
                        Thread.sleep((long) i);
                    } catch (InterruptedException e) {
                    }
                    a++;
                    q.b("SugareventRetry", a);
                    SugarDebugLogger.d("retry event request (url = " + SugarUtil.decode(str) + "," + " count = " + a + ")");
                    b(str);
                    return;
                }
                b = b.IDLE;
                return;
            }
        }
        h();
    }

    public boolean c() {
        if (q.a("SugareventRetry", 0) > 0) {
            return true;
        }
        return false;
    }

    public void d() {
        b = b.PAUSE;
    }

    public void e() {
        if (!c() || this.d == null) {
            h();
            return;
        }
        b = b.RUNNING;
        c(this.d.optString(String.URL, null));
    }

    public static void a(boolean z) {
        if (z) {
            a().e();
        } else {
            a().d();
        }
    }
}
