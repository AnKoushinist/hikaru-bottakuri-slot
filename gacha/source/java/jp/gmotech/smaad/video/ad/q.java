package jp.gmotech.smaad.video.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConstants;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import jp.gmotech.smaad.video.ad.a.d;
import jp.gmotech.smaad.video.ad.b.a;
import jp.gmotech.smaad.video.ad.c.b;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.json.JSONObject;

class q {
    private static final Object u = new Object();
    private final String a = VERSION.RELEASE;
    private Context b = null;
    private String c = null;
    private String d = null;
    private boolean e = false;
    private Boolean f = null;
    private boolean g = false;
    private long h;
    private y i = null;
    private HashMap j = null;
    private ArrayList k = null;
    private HashMap l = null;
    private String m = null;
    private boolean n = false;
    private String o = null;
    private int p = 1;
    private int q = 0;
    private boolean r = false;
    private boolean s = false;
    private b t = null;
    private boolean v = false;
    private boolean w = false;
    private v x = new v(this);

    q() {
    }

    private void a(p pVar) {
        a.a("SmaAdVideoManager", "[checkPing]");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("object", b.SMAAD_VIDEO_PING);
            d.a().a(jSONObject, new u(this, pVar));
        } catch (Exception e) {
            a.a("SmaAdVideoManager", "[checkPing]  " + e.getMessage());
        }
    }

    private void b(Activity activity) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        if (i3 < i4) {
            i = i4;
            i2 = i3;
        } else {
            i = i3;
            i2 = i4;
        }
        while (i2 % i != 0) {
            int i5 = i2 % i;
            i2 = i;
            i = i5;
        }
        i3 /= i;
        if (i4 / i == 4 && i3 == 3) {
            this.n = true;
        }
    }

    private void b(p pVar) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        int i = 0;
        if (pVar != null) {
            String b = pVar.b();
            String a = pVar.a(this.p);
            if (!jp.gmotech.smaad.video.ad.b.b.d(b) && !jp.gmotech.smaad.video.ad.b.b.d(a)) {
                a.a("SmaAdVideoManager", "[downloadFileFromServer] filename : " + b);
                a.a("SmaAdVideoManager", "[downloadFileFromServer] urlString : " + a);
                BufferedInputStream bufferedInputStream2;
                try {
                    a.a("SmaAdVideoManager", "[downloadFileFromServer] download has just started for ZoneId: " + this.c);
                    bufferedInputStream2 = new BufferedInputStream(new URL(a).openStream());
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            e.printStackTrace();
                            a.a("SmaAdVideoManager", "[downloadFileFromServer] Error" + e.getMessage());
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileOutputStream == null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream2 = bufferedInputStream;
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream2.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            i += read;
                        }
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] videofile size: " + i + ", zone: " + this.c);
                        if (jp.gmotech.smaad.video.ad.b.b.b(b)) {
                            String valueOf = String.valueOf(System.currentTimeMillis());
                            this.k.add(valueOf);
                            this.j.put(valueOf, pVar);
                            this.l.put(pVar.b(), valueOf);
                        } else {
                            a.c("SmaAdVideoManager", "[downloadFileFromServer] loop ended but videofile is empty: " + this.c);
                        }
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] download has done for ZoneId: " + this.c);
                        o();
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        bufferedInputStream = bufferedInputStream2;
                        e.printStackTrace();
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] Error" + e.getMessage());
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = null;
                    e.printStackTrace();
                    a.a("SmaAdVideoManager", "[downloadFileFromServer] Error" + e.getMessage());
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    bufferedInputStream2 = null;
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
        }
    }

    private void m() {
        try {
            this.o = AdvertisingIdClient.getAdvertisingIdInfo(this.b).getId();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean n() {
        return (this.j == null || this.j.isEmpty()) ? false : true;
    }

    private void o() {
        if (!this.v && this.t != null && z.a().b() == null) {
            a.a("SmaAdVideoManager", "[callOnReady] ZoneId:" + this.c);
            this.t.onSmaAdVideoReady();
        }
    }

    private void p() {
        for (File file : new File(jp.gmotech.smaad.video.ad.b.b.a(this.b)).listFiles()) {
            String absolutePath = file.getAbsolutePath();
            String substring = absolutePath.substring(absolutePath.lastIndexOf(Operation.DIVISION) + 1);
            a.a("SmaAdVideoManager", "[deleteVideoFiles] nodirFilename : " + substring);
            if (substring.startsWith(this.c)) {
                a.a("SmaAdVideoManager", "[deleteVideoFiles] delete file : " + absolutePath);
                file.delete();
            }
        }
    }

    public b a() {
        return this.t;
    }

    p a(String str) {
        return (p) this.j.get(str);
    }

    synchronized void a(Activity activity) {
        a.a("SmaAdVideoManager", "[showVideoAd]");
        if (n()) {
            this.m = (String) this.k.get(0);
            p pVar = (p) this.j.get(this.m);
            if (jp.gmotech.smaad.video.ad.b.b.b(pVar.b())) {
                pVar.a(this.t);
                Intent intent = new Intent(activity, SmaAdVideoActivity.class);
                intent.putExtra("id", this.m);
                intent.putExtra("zoneId", this.c);
                intent.putExtra("disableStopBtn", this.r);
                activity.startActivity(intent);
                this.f = Boolean.FALSE;
                this.g = true;
            } else {
                b(this.m);
                this.m = null;
                if (!n()) {
                    new Thread(new r(this)).start();
                }
                if (this.t != null) {
                    this.t.onSmaAdVideoError(5);
                }
            }
        } else {
            this.t.onSmaAdVideoError(5);
        }
    }

    void a(boolean z) {
        a.a("SmaAdVideoManager", "[closeActivity]");
        this.g = false;
        this.f = null;
        if (this.m != null) {
            b(this.m);
            this.m = null;
        }
        new Thread(new s(this)).start();
        if (this.t == null) {
            return;
        }
        if (z) {
            this.t.onSmaAdEndcardClosed();
        } else {
            this.t.onSmaAdVideoStop();
        }
    }

    boolean a(Activity activity, String str, String str2, boolean z, b bVar, jp.gmotech.smaad.video.ad.c.a aVar) {
        if (this.w) {
            a.a("SmaAdVideoManager", "[initialize] Already init for ZoneId: " + this.c);
            this.t = bVar;
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) activity.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            a.a("SmaAdVideoManager", "[initialize] has no active network");
            if (aVar != null) {
                aVar.a(2);
            }
            return false;
        } else if (!activeNetworkInfo.isConnectedOrConnecting()) {
            a.a("SmaAdVideoManager", "[initialize] has no connected network");
            if (aVar != null) {
                aVar.a(2);
            }
            return false;
        } else if (VERSION.SDK_INT >= 19 || -1 != activity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            this.t = bVar;
            this.s = activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
            a.a("SmaAdVideoManager", "[initialize]");
            this.b = activity;
            this.c = str;
            this.d = str2;
            this.j = new HashMap();
            this.k = new ArrayList();
            this.l = new HashMap();
            this.o = null;
            this.q = jp.gmotech.smaad.video.ad.b.b.b(this.b);
            this.r = z;
            b(activity);
            p();
            if (activity.getResources().getConfiguration().orientation == 1) {
                a.a("SmaAdVideoManager", "[orientation] portrait");
                this.i = y.PORT;
            } else {
                a.a("SmaAdVideoManager", "[orientation] landscape");
                this.i = y.LAND;
            }
            this.x.a();
            this.w = true;
            if (this.t != null) {
                this.t.onSmaAdVideoInitEnd();
            }
            return true;
        } else {
            a.a("SmaAdVideoManager", "[initialize] has no permission WRITE_EXTERNAL_STORAGE");
            if (aVar != null) {
                aVar.a(0);
            }
            return false;
        }
    }

    String b() {
        return this.o;
    }

    void b(String str) {
        a.a("SmaAdVideoManager", "[deleteSmaAdInfo] id : " + str);
        this.k.remove(str);
        p pVar = (p) this.j.get(str);
        this.j.remove(str);
        if (pVar == null) {
            a.a("SmaAdVideoManager", "[deleteSmaAdInfo] already has been deleted ");
            return;
        }
        this.l.remove(pVar.b());
        jp.gmotech.smaad.video.ad.b.b.c(pVar.b());
    }

    String c() {
        return this.d;
    }

    boolean d() {
        return n();
    }

    boolean e() {
        return this.n;
    }

    y f() {
        return this.i;
    }

    void g() {
        if (this.o == null) {
            m();
        }
        if (this.c == null || this.c.isEmpty() || this.d == null || this.d.isEmpty() || this.b == null) {
            a.a("SmaAdVideoManager", "[getAdInfo] zoneId : " + this.c + ", userId : " + this.d + ", context : " + this.b);
            if (this.t != null) {
                this.t.onSmaAdVideoError(6);
            }
        } else if (n() && System.currentTimeMillis() - this.h <= 1200000) {
            a.a("SmaAdVideoManager", "[getAdInfo] already has video ad that is not expired");
            o();
        } else if (System.currentTimeMillis() - this.h <= RFLConstants.GPS_TIME) {
            a.a("SmaAdVideoManager", "[getAdInfo] Limit Request Interval");
        } else {
            this.h = System.currentTimeMillis();
            a.a("SmaAdVideoManager", "[getAdInfo] start to receive video ad");
            long currentTimeMillis = System.currentTimeMillis();
            String a = jp.gmotech.smaad.video.ad.b.b.a(jp.gmotech.smaad.video.ad.b.b.a(this.c + ":" + currentTimeMillis));
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put("zone_id", this.c);
                jSONObject.put("t", currentTimeMillis);
                jSONObject.put("u", this.d);
                jSONObject.put("d", a);
                jSONObject.put("device_id", this.o);
                jSONObject.put("os_type", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
                jSONObject.put(TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, this.a);
                jSONObject.put("is_tablet", this.q);
                jSONObject.put("sdk_version", "1.1.2");
                jSONObject2.put("object", b.SMAAD_VIDEO_AD);
                jSONObject2.put("argument", jSONObject);
                d.a().a(jSONObject2, new t(this));
            } catch (Exception e) {
                a.a("SmaAdVideoManager", "[getAdInfo]" + e.getMessage());
            }
        }
    }

    void h() {
        if (this.v) {
            this.v = false;
        }
    }

    void i() {
        this.v = true;
    }

    void j() {
        a.a("SmaAdVideoManager", "[onVideoActivityResume] mIsShowActivity : " + this.g);
        this.f = Boolean.FALSE;
    }

    void k() {
        a.a("SmaAdVideoManager", "[onVideoActivityPause] mIsShowActivity : " + this.g);
        if (this.g) {
            this.f = Boolean.TRUE;
        }
    }
}
