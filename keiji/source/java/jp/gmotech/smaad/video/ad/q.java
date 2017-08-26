package jp.gmotech.smaad.video.ad;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.tapjoy.TapjoyConstants;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import jp.gmotech.smaad.video.ad.a.d;
import jp.gmotech.smaad.video.ad.b.a;
import jp.gmotech.smaad.video.ad.b.b;
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
    private v i = null;
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
    private u x = new u(this);

    q() {
    }

    private void a(p pVar) {
        a.a("SmaAdVideoManager", "[checkPing]");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("object", jp.gmotech.smaad.video.ad.c.a.SMAAD_VIDEO_PING);
            d.a().a(jSONObject, new t(this, pVar));
        } catch (Exception e) {
            a.a("SmaAdVideoManager", "[checkPing]  " + e.getMessage());
        }
    }

    private void b(p pVar) {
        BufferedInputStream bufferedInputStream;
        Exception e;
        Throwable th;
        BufferedInputStream bufferedInputStream2 = null;
        int i = 0;
        if (pVar != null) {
            String b = pVar.b();
            String a = pVar.a(this.p);
            if (!b.d(b) && !b.d(a)) {
                a.a("SmaAdVideoManager", "[downloadFileFromServer] filename : " + b);
                a.a("SmaAdVideoManager", "[downloadFileFromServer] urlString : " + a);
                FileOutputStream fileOutputStream;
                try {
                    a.a("SmaAdVideoManager", "[downloadFileFromServer] download has just started for ZoneId: " + this.c);
                    bufferedInputStream = new BufferedInputStream(new URL(a).openStream());
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                        bufferedInputStream2 = bufferedInputStream;
                        try {
                            e.printStackTrace();
                            a.a("SmaAdVideoManager", "[downloadFileFromServer] Error" + e.getMessage());
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (fileOutputStream == null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream = bufferedInputStream2;
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            i += read;
                        }
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] videofile size: " + i + ", zone: " + this.c);
                        if (b.b(b)) {
                            String valueOf = String.valueOf(System.currentTimeMillis());
                            this.k.add(valueOf);
                            this.j.put(valueOf, pVar);
                            this.l.put(pVar.b(), valueOf);
                        } else {
                            a.c("SmaAdVideoManager", "[downloadFileFromServer] loop ended but videofile is empty: " + this.c);
                        }
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] download has done for ZoneId: " + this.c);
                        k();
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        bufferedInputStream2 = bufferedInputStream;
                        e.printStackTrace();
                        a.a("SmaAdVideoManager", "[downloadFileFromServer] Error" + e.getMessage());
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
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
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th5) {
                    th = th5;
                    fileOutputStream = null;
                    bufferedInputStream = null;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
        }
    }

    private void i() {
        try {
            this.o = AdvertisingIdClient.getAdvertisingIdInfo(this.b).getId();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean j() {
        return (this.j == null || this.j.isEmpty()) ? false : true;
    }

    private void k() {
        if (!this.v && this.t != null && w.a().b() == null) {
            a.a("SmaAdVideoManager", "[callOnReady] ZoneId:" + this.c);
            this.t.a();
        }
    }

    String a() {
        return this.o;
    }

    p a(String str) {
        return (p) this.j.get(str);
    }

    void a(boolean z) {
        a.a("SmaAdVideoManager", "[closeActivity]");
        this.g = false;
        this.f = null;
        if (this.m != null) {
            b(this.m);
            this.m = null;
        }
        new Thread(new r(this)).start();
        if (this.t == null) {
            return;
        }
        if (z) {
            this.t.d();
        } else {
            this.t.c();
        }
    }

    String b() {
        return this.d;
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
        b.c(pVar.b());
    }

    boolean c() {
        return this.n;
    }

    v d() {
        return this.i;
    }

    void e() {
        if (this.o == null) {
            i();
        }
        if (this.c == null || this.c.isEmpty() || this.d == null || this.d.isEmpty() || this.b == null) {
            a.a("SmaAdVideoManager", "[getAdInfo] zoneId : " + this.c + ", userId : " + this.d + ", context : " + this.b);
            if (this.t != null) {
                this.t.a(6);
            }
        } else if (j() && System.currentTimeMillis() - this.h <= 1200000) {
            a.a("SmaAdVideoManager", "[getAdInfo] already has video ad that is not expired");
            k();
        } else if (System.currentTimeMillis() - this.h <= 5000) {
            a.a("SmaAdVideoManager", "[getAdInfo] Limit Request Interval");
        } else {
            this.h = System.currentTimeMillis();
            a.a("SmaAdVideoManager", "[getAdInfo] start to receive video ad");
            long currentTimeMillis = System.currentTimeMillis();
            String a = b.a(b.a(this.c + ":" + currentTimeMillis));
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME, this.c);
                jSONObject.put("t", currentTimeMillis);
                jSONObject.put("u", this.d);
                jSONObject.put("d", a);
                jSONObject.put("device_id", this.o);
                jSONObject.put("os_type", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                jSONObject.put(TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, this.a);
                jSONObject.put("is_tablet", this.q);
                jSONObject.put("sdk_version", "1.1.2");
                jSONObject2.put("object", jp.gmotech.smaad.video.ad.c.a.SMAAD_VIDEO_AD);
                jSONObject2.put("argument", jSONObject);
                d.a().a(jSONObject2, new s(this));
            } catch (Exception e) {
                a.a("SmaAdVideoManager", "[getAdInfo]" + e.getMessage());
            }
        }
    }

    void f() {
        a.a("SmaAdVideoManager", "[onVideoActivityResume] mIsShowActivity : " + this.g);
        this.f = Boolean.FALSE;
    }

    void g() {
        a.a("SmaAdVideoManager", "[onVideoActivityPause] mIsShowActivity : " + this.g);
        if (this.g) {
            this.f = Boolean.TRUE;
        }
    }
}
