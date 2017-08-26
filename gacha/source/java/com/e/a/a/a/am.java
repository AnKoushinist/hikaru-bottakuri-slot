package com.e.a.a.a;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.e.a.a.a.a.b.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

abstract class am {
    protected static final b[] b = new b[]{b.AD_EVT_FIRST_QUARTILE, b.AD_EVT_MID_POINT, b.AD_EVT_THIRD_QUARTILE};
    protected boolean a;
    protected final Map c = new HashMap();
    protected final Handler d = new Handler();
    protected Map e;
    protected WeakReference f;
    protected WeakReference g;
    protected final f h;
    protected final n i;
    private boolean j;
    private WeakReference k;
    private j l;

    public am(String str, f fVar, n nVar) {
        this.i = nVar;
        this.h = fVar;
        a("Initializing.");
        this.l = new j(str, nVar, fVar);
        this.k = new WeakReference(fVar.c());
        this.j = false;
        this.a = false;
    }

    private int a(AudioManager audioManager) {
        return audioManager.getStreamVolume(3);
    }

    private void c(a aVar) {
        a(String.format("Received event: %s", new Object[]{b(aVar).toString()}));
        this.l.a(r0);
        b bVar = aVar.e;
        if (bVar == b.AD_EVT_COMPLETE || bVar == b.AD_EVT_STOPPED || bVar == b.AD_EVT_SKIPPED) {
            this.c.put(bVar, Integer.valueOf(1));
            c();
        }
    }

    protected abstract Map a();

    public void a(a aVar) {
        try {
            c(aVar);
        } catch (Exception e) {
            a.a(e);
        }
    }

    protected void a(String str) {
        if (this.i.b() || this.a) {
            Log.d("MoatVideoTracker", str);
        }
    }

    protected boolean a(Integer num, Integer num2) {
        return ((double) (num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public boolean a(Map map, Object obj, View view) {
        boolean z = true;
        boolean z2 = false;
        if (map == null) {
            try {
                a("trackVideoAd received null adIds object. Not tracking.");
                z = false;
            } catch (Exception e) {
                a.a(e);
            }
        }
        if (view == null) {
            a("trackVideoAd received null video view instance");
        }
        if (obj == null) {
            a("trackVideoAd received null ad instance. Not tracking.");
            z = false;
        }
        if (z) {
            String str = "trackVideoAd tracking ids: %s | ad: %s | view: %s";
            Object[] objArr = new Object[3];
            objArr[0] = new JSONObject(map).toString();
            objArr[1] = obj.toString();
            objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
            a(String.format(str, objArr));
            this.e = map;
            this.f = new WeakReference(obj);
            this.g = new WeakReference(view);
            b();
        }
        z2 = z;
        a("Attempt to start tracking ad was " + (z2 ? BuildConfig.FLAVOR : "un") + "successful.");
        return z2;
    }

    protected JSONObject b(a aVar) {
        if (Double.isNaN(aVar.d.doubleValue())) {
            try {
                aVar.d = Double.valueOf(d());
            } catch (Exception e) {
                aVar.d = Double.valueOf(1.0d);
            }
        }
        return new JSONObject(aVar.a());
    }

    protected void b() {
        Map a = a();
        Integer num = (Integer) a.get("height");
        a(String.format("Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) a.get("width"), (Integer) a.get("duration")}));
        this.l.a((View) this.g.get(), this.e, r3, num, r5);
    }

    protected void c() {
        if (!this.j) {
            this.d.postDelayed(new an(this), 500);
            this.j = true;
        }
    }

    protected double d() {
        AudioManager audioManager = (AudioManager) ((Context) this.k.get()).getSystemService("audio");
        return ((double) a(audioManager)) / ((double) audioManager.getStreamMaxVolume(3));
    }
}
