package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import jp.co.geniee.gnadsdk.a.d;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAladdinApiUtil.WebAPIResult;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class GNSZoneGetCommand {
    private Context a;
    private String b;
    private GNSZoneInfo c;
    private GNSZoneGetCommandUpdateTask d;
    private GNSZoneGetCommandListener e;
    private Lock f = new ReentrantLock();
    private Condition g;
    private GNSLogger h;
    private String i;
    private d j;

    public interface GNSZoneGetCommandListener {
        void a(int i, String str, Exception exception);

        void a(GNSZoneInfo gNSZoneInfo);
    }

    private class GNSZoneGetCommandUpdateTask extends Thread {
        final /* synthetic */ GNSZoneGetCommand a;
        private int b;
        private String c;
        private Exception d;
        private boolean e = false;

        public GNSZoneGetCommandUpdateTask(GNSZoneGetCommand gNSZoneGetCommand) {
            this.a = gNSZoneGetCommand;
        }

        public void a() {
            this.e = true;
        }

        public void run() {
            try {
                b();
            } finally {
                this.a.d = null;
            }
        }

        private void b() {
            int i = 0;
            while (i < 10) {
                try {
                    if (!this.e) {
                        boolean a;
                        WebAPIResult a2 = GNSAladdinApiUtil.a(this.a.a, this.a.b, this.a.h, this.a.i, this.a.j, true);
                        this.b = a2.c;
                        WebAPIResult webAPIResult;
                        WebAPIResult webAPIResult2;
                        if (this.b == HttpResponseCode.OK) {
                            webAPIResult = a2;
                            a = a(a2);
                            webAPIResult2 = webAPIResult;
                        } else if (a2.c == HttpResponseCode.BAD_REQUEST) {
                            webAPIResult = a2;
                            a = b(a2);
                            webAPIResult2 = webAPIResult;
                        } else {
                            a2 = GNSAladdinApiUtil.a(this.a.a, this.a.b, this.a.h, this.a.i, this.a.j, false);
                            this.b = a2.c;
                            if (this.b == HttpResponseCode.OK) {
                                webAPIResult = a2;
                                a = a(a2);
                                webAPIResult2 = webAPIResult;
                            } else if (a2.c == HttpResponseCode.BAD_REQUEST) {
                                webAPIResult = a2;
                                a = b(a2);
                                webAPIResult2 = webAPIResult;
                            } else {
                                webAPIResult2 = a2;
                                a = false;
                            }
                        }
                        this.a.h.d("GetCommand", "StatusCode:" + this.b + ", Message:" + this.c);
                        if (!a) {
                            synchronized (r1) {
                                try {
                                    this.a.h.d("GetCommand", "ZoneInfo does not take.");
                                    wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                            i++;
                        }
                    }
                } catch (Exception e2) {
                    this.d = e2;
                }
            }
            try {
                this.a.f.lock();
                while (this.a.e == null && !this.e) {
                    this.a.g.awaitUninterruptibly();
                }
                if (!this.e) {
                    if (this.a.c == null || this.a.c.a()) {
                        if (this.a.e != null) {
                            this.a.e.a(this.b, this.c, this.d);
                        }
                    } else if (this.a.e != null) {
                        this.a.e.a(this.a.c);
                    }
                }
                this.a.f.unlock();
            } catch (Throwable th) {
                this.a.f.unlock();
            }
        }

        private boolean a(WebAPIResult webAPIResult) {
            if (a(webAPIResult.a)) {
                GNSZoneInfo a = GNSZoneInfo.a(this.a.a, this.a.b, webAPIResult.a, false);
                if (a != null) {
                    long time = new Date().getTime();
                    if (!a(webAPIResult.a, time)) {
                        return false;
                    }
                    this.c = "ZoneInfo is saved";
                    this.a.c = a;
                    this.a.c.a(time + TapjoyConstants.PAID_APP_TIME);
                    return true;
                }
                this.c = "ZoneInfo is null";
                return false;
            }
            this.c = "ZoneInfo format valid error";
            return false;
        }

        private boolean b(WebAPIResult webAPIResult) {
            this.c = "ZoneInfo BAD_REQUEST";
            return false;
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String str2 = BuildConfig.FLAVOR;
                if (!jSONObject.has("zones")) {
                    return false;
                }
                if (jSONObject.getJSONArray("zones") != null) {
                    return true;
                }
                if (!String.VIDEO_ERROR.equals(str2) || !jSONObject.has("values")) {
                    return false;
                }
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("values"));
                if (!jSONObject2.has(String.MESSAGE)) {
                    return false;
                }
                this.a.h.f("GetCommand", "error=" + jSONObject2.getString(String.MESSAGE));
                return false;
            } catch (Exception e) {
                this.a.h.f("GetCommand", "JSONException");
                this.a.h.a("GetCommand", e);
                return false;
            }
        }

        private boolean a(String str, long j) {
            String b = GNSPrefUtil.b(this.a.a, this.a.b);
            long a = GNSPrefUtil.a(this.a.a, this.a.b);
            String a2 = GNSPrefUtil.a(b);
            try {
                GNSPrefUtil.c(this.a.a, this.a.b, j);
                GNSPrefUtil.b(b);
                GNSPrefUtil.a(b, str);
                return true;
            } catch (Exception e) {
                GNSPrefUtil.c(this.a.a, this.a.b, a);
                GNSPrefUtil.a(b, a2);
                return false;
            }
        }
    }

    public GNSZoneGetCommand(Activity activity, String str, String str2) {
        this.a = activity.getApplicationContext();
        this.b = str;
        this.i = str2;
        this.j = new d(this.a);
        this.g = this.f.newCondition();
        this.h = GNSLogger.a();
    }

    public void a() {
        if (this.d != null && this.d.isAlive()) {
            this.d.a();
            this.d = null;
        }
    }

    public void a(GNSZoneGetCommandListener gNSZoneGetCommandListener) {
        try {
            this.f.lock();
            this.e = gNSZoneGetCommandListener;
            this.g.signal();
        } finally {
            this.f.unlock();
        }
    }

    public void b() {
        this.h.d("GetCommand", "ZoneInfo\u53d6\u5f97\u51e6\u7406\u958b\u59cb");
        a(false);
    }

    public void c() {
        a(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        if (r2 != 0) goto L_0x000f;
    L_0x0003:
        r0 = r1.d;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r0 = r1.d;	 Catch:{ all -> 0x001d }
        r0 = r0.isAlive();	 Catch:{ all -> 0x001d }
        if (r0 != 0) goto L_0x001b;
    L_0x000f:
        r0 = new jp.co.geniee.gnadsdk.rewardvideo.GNSZoneGetCommand$GNSZoneGetCommandUpdateTask;	 Catch:{ all -> 0x001d }
        r0.<init>(r1);	 Catch:{ all -> 0x001d }
        r1.d = r0;	 Catch:{ all -> 0x001d }
        r0 = r1.d;	 Catch:{ all -> 0x001d }
        r0.start();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);
        return;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.co.geniee.gnadsdk.rewardvideo.GNSZoneGetCommand.a(boolean):void");
    }
}
