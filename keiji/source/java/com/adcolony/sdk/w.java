package com.adcolony.sdk;

import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import com.tapjoy.TapjoyConnectCore;
import java.util.HashMap;
import org.json.JSONObject;

class w {
    final String a;
    private final int b;
    private HashMap<Integer, Integer> c = new HashMap();
    private HashMap<Integer, Integer> d = new HashMap();
    private HashMap<Integer, Boolean> e = new HashMap();
    private HashMap<Integer, Integer> f = new HashMap();
    private HashMap<Integer, Integer> g = new HashMap();
    private HashMap<String, Integer> h = new HashMap();
    private SoundPool i;

    w(final String str, final int i) {
        this.a = str;
        this.b = i;
        this.i = new SoundPool(50, 3, 0);
        this.i.setOnLoadCompleteListener(new OnLoadCompleteListener(this) {
            final /* synthetic */ w c;

            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                JSONObject a = bb.a();
                bb.b(a, "id", ((Integer) this.c.f.get(Integer.valueOf(i))).intValue());
                bb.a(a, "ad_session_id", str);
                if (i2 == 0) {
                    new o("AudioPlayer.on_ready", i, a).a();
                    this.c.g.put(this.c.f.get(Integer.valueOf(i)), Integer.valueOf(i));
                    return;
                }
                new o("AudioPlayer.on_error", i, a).a();
            }
        });
    }

    void a(o oVar) {
        JSONObject b = oVar.b();
        int load = this.i.load(bb.a(b, "filepath"), 1);
        int i = bb.c(b, "repeats") ? -1 : 0;
        this.f.put(Integer.valueOf(load), Integer.valueOf(bb.b(b, "id")));
        bd.d.a("Load audio with id = ").b(load);
        this.d.put(Integer.valueOf(load), Integer.valueOf(i));
        this.e.put(Integer.valueOf(load), Boolean.valueOf(false));
    }

    void b(o oVar) {
        this.i.unload(((Integer) this.g.get(Integer.valueOf(bb.b(oVar.b(), "id")))).intValue());
    }

    void c(o oVar) {
        int intValue = ((Integer) this.g.get(Integer.valueOf(bb.b(oVar.b(), "id")))).intValue();
        if (((Boolean) this.e.get(Integer.valueOf(intValue))).booleanValue()) {
            this.i.resume(((Integer) this.c.get(Integer.valueOf(intValue))).intValue());
            return;
        }
        int play = this.i.play(intValue, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0, ((Integer) this.d.get(Integer.valueOf(intValue))).intValue(), TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
        if (play != 0) {
            this.c.put(Integer.valueOf(intValue), Integer.valueOf(play));
            return;
        }
        JSONObject a = bb.a();
        bb.b(a, "id", bb.b(oVar.b(), "id"));
        bb.a(a, "ad_session_id", this.a);
        new o("AudioPlayer.on_error", this.b, a).a();
    }

    void d(o oVar) {
        int intValue = ((Integer) this.g.get(Integer.valueOf(bb.b(oVar.b(), "id")))).intValue();
        this.i.pause(((Integer) this.c.get(Integer.valueOf(intValue))).intValue());
        this.e.put(Integer.valueOf(intValue), Boolean.valueOf(true));
    }

    void e(o oVar) {
        this.i.stop(((Integer) this.c.get(this.g.get(Integer.valueOf(bb.b(oVar.b(), "id"))))).intValue());
    }

    SoundPool a() {
        return this.i;
    }
}
