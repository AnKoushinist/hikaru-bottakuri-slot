package com.adcolony.sdk;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

class bg {
    final String a;
    private final int b;
    private HashMap<Integer, MediaPlayer> c = new HashMap();
    private HashMap<Integer, a> d = new HashMap();
    private HashMap<Integer, Boolean> e = new HashMap();
    private HashMap<Integer, Boolean> f = new HashMap();
    private ArrayList<MediaPlayer> g = new ArrayList();

    private class a implements OnErrorListener, OnPreparedListener {
        int a;
        boolean b;
        final /* synthetic */ bg c;

        a(bg bgVar, int i, boolean z) {
            this.c = bgVar;
            this.a = i;
            this.b = z;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            JSONObject a = bb.a();
            bb.b(a, "id", this.a);
            bb.a(a, "ad_session_id", this.c.a);
            new o("AudioPlayer.on_error", this.c.b, a).a();
            return true;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setLooping(this.b);
            this.c.e.put(Integer.valueOf(this.a), Boolean.valueOf(true));
            JSONObject a = bb.a();
            bb.b(a, "id", this.a);
            bb.a(a, "ad_session_id", this.c.a);
            new o("AudioPlayer.on_ready", this.c.b, a).a();
        }
    }

    bg(String str, int i) {
        this.a = str;
        this.b = i;
    }

    void a(o oVar) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        JSONObject b = oVar.b();
        int b2 = bb.b(b, "id");
        Object aVar = new a(this, b2, bb.c(b, "repeats"));
        this.c.put(Integer.valueOf(b2), mediaPlayer);
        this.d.put(Integer.valueOf(b2), aVar);
        this.e.put(Integer.valueOf(b2), Boolean.valueOf(false));
        this.f.put(Integer.valueOf(b2), Boolean.valueOf(false));
        mediaPlayer.setOnErrorListener(aVar);
        mediaPlayer.setOnPreparedListener(aVar);
        try {
            mediaPlayer.setDataSource(bb.a(b, "filepath"));
        } catch (Exception e) {
            b = bb.a();
            bb.b(b, "id", b2);
            bb.a(b, "ad_session_id", this.a);
            new o("AudioPlayer.on_error", this.b, b).a();
        }
        mediaPlayer.prepareAsync();
    }

    void a() {
        this.g.clear();
        for (MediaPlayer mediaPlayer : this.c.values()) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                this.g.add(mediaPlayer);
            }
        }
    }

    void b() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ((MediaPlayer) it.next()).start();
        }
        this.g.clear();
    }

    void b(o oVar) {
        int b = bb.b(oVar.b(), "id");
        if (((Boolean) this.f.get(Integer.valueOf(b))).booleanValue()) {
            ((MediaPlayer) this.c.get(Integer.valueOf(b))).pause();
        }
    }

    void c(o oVar) {
        int b = bb.b(oVar.b(), "id");
        if (((Boolean) this.e.get(Integer.valueOf(b))).booleanValue()) {
            ((MediaPlayer) this.c.get(Integer.valueOf(b))).start();
            this.f.put(Integer.valueOf(b), Boolean.valueOf(true));
        }
    }

    void d(o oVar) {
        ((MediaPlayer) this.c.remove(Integer.valueOf(bb.b(oVar.b(), "id")))).release();
    }

    void e(o oVar) {
        int b = bb.b(oVar.b(), "id");
        if (((Boolean) this.f.get(Integer.valueOf(b))).booleanValue()) {
            MediaPlayer mediaPlayer = (MediaPlayer) this.c.get(Integer.valueOf(b));
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    HashMap<Integer, MediaPlayer> c() {
        return this.c;
    }
}
