package com.glossomads.View;

import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.glossomads.b.b;
import com.glossomads.d.d;
import com.glossomads.e;
import com.glossomads.j;
import com.glossomads.s;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class l extends FrameLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnSeekCompleteListener, Callback {
    private t a;
    private u b;
    private g c;
    private k d;
    private SurfaceView e;
    private MediaPlayer f;
    private boolean g = e.a().b(this.a.b().l());
    private r h;
    private a i = e.a().f(this.a.b().l());
    private boolean j;

    public enum a {
        PREPARING,
        PLAYBACK,
        COMPLETE,
        ERROR
    }

    public l(u uVar, t tVar) {
        super(s.e());
        setBackgroundColor(-16777216);
        this.a = tVar;
        this.b = uVar;
        if (e.a().e(this.a.b().l())) {
            this.j = true;
        } else {
            this.j = false;
        }
        a();
    }

    public void a() {
        setLayoutParams(new LayoutParams(this.a.d(), this.a.e(), 17));
        b();
        c();
        this.e = new SurfaceView(s.e());
        this.e.getHolder().addCallback(this);
        this.e.setVisibility(4);
        addView(this.e);
    }

    public void b() {
        this.c = new g(this.a);
        this.c.setVisibility(4);
        this.c.setSugarEndcardViewListener(new m(this));
        addView(this.c);
    }

    public void c() {
        this.d = new k();
        addView(this.d);
        this.h = new r(1);
        this.h.a(new n(this));
    }

    public void d() {
        if (this.i == a.COMPLETE) {
            this.i = a.COMPLETE;
            this.c.c();
            return;
        }
        e();
    }

    public void e() {
        e.a().a(this.a.b());
        n();
        this.c.c();
        this.h.b(e.a().a(this.a.b().l()));
        this.e.setVisibility(0);
    }

    public void f() {
        this.h.b(this.f.getCurrentPosition());
        this.h.c();
        this.d.setTime(this.h.f());
        this.d.b();
    }

    public void g() {
        com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.videoReplay, this.a.b().l());
        this.g = true;
        this.i = a.PREPARING;
        e.a().a(this.i, this.h.g(), this.g);
        e();
    }

    public void h() {
        if (!this.j) {
            this.j = true;
            o();
            if (this.i == a.PLAYBACK && this.f != null) {
                this.f.pause();
            }
            e.a().a(this.i, this.h.g(), this.g);
            this.c.d();
            this.h.d();
            this.d.c();
        }
    }

    public void i() {
        if (this.j) {
            this.j = false;
            p();
            if (this.i == a.PLAYBACK && this.f != null) {
                this.f.start();
                f();
            }
            e.a().a(this.i, this.h.g(), this.g);
            this.c.e();
        }
    }

    public void j() {
        if (this.b != null) {
            this.b = null;
        }
        if (this.a != null) {
            this.a = null;
        }
        if (this.e != null) {
            removeView(this.e);
            this.e.destroyDrawingCache();
            this.e = null;
        }
        u();
        this.c.f();
        removeView(this.c);
        removeView(this.d);
        this.c = null;
        this.h.h();
        this.d = null;
    }

    public void a(boolean z) {
        AudioManager audioManager = (AudioManager) s.e().getSystemService("audio");
        if (z) {
            audioManager.setStreamVolume(3, 0, 0);
        } else {
            audioManager.setStreamVolume(3, audioManager.getStreamVolume(3), 0);
        }
    }

    public File getMovieFile() {
        File file = null;
        try {
            file = j.a().a(this.a.b().b().toString(), this.a.a());
        } catch (b e) {
            a(e);
        }
        return file;
    }

    private void s() {
        InputStream fileInputStream;
        Throwable th;
        this.c.setVisibility(4);
        InputStream inputStream = null;
        try {
            t();
            this.f.reset();
            fileInputStream = new FileInputStream(getMovieFile());
            try {
                this.f.setDataSource(fileInputStream.getFD());
                this.f.setDisplay(this.e.getHolder());
                this.f.setOnPreparedListener(this);
                this.f.setOnCompletionListener(this);
                this.f.setOnSeekCompleteListener(this);
                this.f.setOnErrorListener(this);
                this.f.setScreenOnWhilePlaying(true);
                this.f.setAudioStreamType(3);
                this.f.prepareAsync();
                SugarUtil.close(fileInputStream);
            } catch (Exception e) {
                try {
                    a(new b(b.d));
                    SugarUtil.close(fileInputStream);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    inputStream = fileInputStream;
                    th = th3;
                    SugarUtil.close(inputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            fileInputStream = null;
            a(new b(b.d));
            SugarUtil.close(fileInputStream);
        } catch (Throwable th4) {
            th = th4;
            SugarUtil.close(inputStream);
            throw th;
        }
    }

    private void t() {
        u();
        this.f = new MediaPlayer();
    }

    private void u() {
        if (this.f != null) {
            e.a().a(this.i, this.h.g(), this.g);
            this.f.release();
            this.f = null;
        }
    }

    public void k() {
        if (this.c == null || this.c.g()) {
            m();
        } else {
            this.c.c();
        }
    }

    public void l() {
        this.e.setVisibility(4);
        this.d.setVisibility(4);
        this.c.setVisibility(0);
        this.c.bringToFront();
        this.c.e();
    }

    public void m() {
        a(new b(b.e));
    }

    private void v() {
        setLayoutParams(new LayoutParams(this.a.d(), this.a.e(), 17));
        this.c.setLayoutParams(new LayoutParams(v.a, v.a, 17));
        w();
    }

    private void w() {
        if (this.f != null) {
            Point a = v.a(this.a.d(), this.a.e(), this.f.getVideoWidth(), this.f.getVideoHeight());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(a.x, a.y, 17);
            if (this.e != null) {
                this.e.setLayoutParams(layoutParams);
            }
        }
    }

    public void a(int i, int i2) {
        this.a.a(i);
        this.a.b(i2);
        v();
    }

    public void b(boolean z) {
        if (z && this.c != null && !this.c.g()) {
            this.c.c();
        }
    }

    public void a(com.glossomads.d.b.b bVar) {
        if (bVar.equals(com.glossomads.d.b.b.NORMAL)) {
            a(false);
        } else {
            a(true);
        }
    }

    public void n() {
        com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.videoStart, this.a.a(), this.a.b().l());
        if (a(a.PREPARING) && !e.a().c(this.a.b().l())) {
            a(com.glossomads.d.a.b.AD_START);
        }
        if (this.b != null) {
            this.b.onVideoStart();
        }
    }

    public void o() {
        com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.videoPause, this.a.a(), this.a.b().l());
        if (a(a.PLAYBACK)) {
            a(com.glossomads.d.a.b.AD_PAUSE);
        }
        if (this.b != null) {
            this.b.onVideoPause();
        }
    }

    public void p() {
        com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.videoResume, this.a.a(), this.a.b().l());
        if (a(a.PLAYBACK)) {
            a(com.glossomads.d.a.b.AD_RESUME);
        }
        if (this.b != null) {
            this.b.onVideoResume();
        }
    }

    public void q() {
        com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.videoFinish, this.a.a(), this.a.b().l());
        if (a(a.COMPLETE) && !e.a().d(this.a.b().l())) {
            a(com.glossomads.d.a.b.AD_FINISH);
        }
        if (this.b != null) {
            this.b.onVideoFinish();
        }
    }

    private boolean a(a aVar) {
        if (!this.g && this.i == aVar) {
            return true;
        }
        return false;
    }

    private void a(com.glossomads.d.a.b bVar) {
        e.a(bVar, this.a.b());
    }

    public void r() {
        e.a().b();
        if (this.b != null) {
            this.b.onVideoClose(this.i == a.COMPLETE);
        }
    }

    public void a(b bVar) {
        e.a().b();
        this.i = a.ERROR;
        if (a(a.ERROR)) {
            a(com.glossomads.d.a.b.AD_ERROR);
        }
        if (this.b != null) {
            this.b.onVideoError(bVar);
        }
    }

    private boolean x() {
        return d.m();
    }

    private void y() {
        e.a().a(this.i, this.h.g(), this.g);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        s();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.f != null) {
            u();
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.i = a.PLAYBACK;
        a(d.n());
        v();
        this.h.a(this.f.getDuration());
        this.f.seekTo(this.h.g());
        this.f.start();
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        new Handler().postDelayed(new o(this), 500);
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.i != a.COMPLETE) {
            this.h.e();
            if (x()) {
                this.i = a.COMPLETE;
                q();
                y();
                if (this.c.g()) {
                    l();
                    return;
                } else {
                    k();
                    return;
                }
            }
            this.i = a.ERROR;
            a(new b(b.c));
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        if (i == 1) {
            str = "Broadcast finished";
        } else if (i == 100) {
            str = "Media service died unexpectedly";
        } else {
            str = "Unknown media error : " + i;
        }
        SugarDebugLogger.d(str);
        a(new b(b.d));
        return true;
    }
}
