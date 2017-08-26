package com.jirbo.adcolony;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import org.cocos2dx.lib.GameControllerDelegate;

class v extends h {
    static boolean H;
    static v I;
    boolean J;
    boolean K;
    ADCVideo L;

    public v(ADCVideo aDCVideo, AdColonyV4VCAd adColonyV4VCAd) {
        this.L = aDCVideo;
        this.G = adColonyV4VCAd;
        aDCVideo.G.pause();
        I = this;
        if (!a()) {
        }
    }

    public void onDraw(Canvas canvas) {
        int i = 128;
        if (this.L.G != null) {
            H = true;
            d();
            int currentTimeMillis = (((int) (System.currentTimeMillis() - this.w)) * 255) / GameControllerDelegate.THUMBSTICK_LEFT_X;
            if (currentTimeMillis <= 128) {
                i = currentTimeMillis;
            }
            canvas.drawARGB(i, 0, 0, 0);
            this.a.a(canvas, this.x, this.y);
            i = (b() * 3) / 2;
            a("Completion is required to receive", this.z, (int) (((double) this.A) - (((double) i) * 2.75d)), canvas);
            a("your reward.", this.z, this.A - (i * 2), canvas);
            a("Are you sure you want to skip?", this.z, (int) (((double) this.A) - (((double) i) * 1.25d)), canvas);
            this.b.a(canvas, this.z - (this.b.f / 2), this.A - (this.b.g / 2));
            if (this.J) {
                this.d.a(canvas, this.B, this.D);
            } else {
                this.c.a(canvas, this.B, this.D);
            }
            if (this.K) {
                this.f.a(canvas, this.C, this.D);
            } else {
                this.e.a(canvas, this.C, this.D);
            }
            c("Yes", this.B, this.D, canvas);
            c("No", this.C, this.D, canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ADCVideo aDCVideo = this.L;
        if (ADCVideo.d) {
            I = null;
            return this.L.H.onTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 1) {
            if (a(x, y, this.B, this.D) && this.J) {
                I = null;
                H = false;
                a.D = false;
                a.ak = true;
                a.W.b(this.G);
                AdColonyBrowser.A = true;
                this.L.finish();
                a.E = true;
            } else if (a(x, y, this.C, this.D) && this.K) {
                I = null;
                H = false;
                this.L.G.start();
            }
            this.J = false;
            this.K = false;
            invalidate();
        }
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (a(x, y, this.B, this.D)) {
            this.J = true;
            invalidate();
            return true;
        } else if (!a(x, y, this.C, this.D)) {
            return true;
        } else {
            this.K = true;
            invalidate();
            return true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.L.G != null && i == 4) {
            return super.onKeyDown(i, keyEvent);
        }
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        I = null;
        this.L.G.start();
        return true;
    }

    void d() {
        int i = this.L.t;
        int i2 = this.L.u;
        this.x = (i - this.a.f) / 2;
        this.y = (i2 - this.a.g) / 2;
        this.z = this.x + (this.a.f / 2);
        this.A = this.y + (this.a.g / 2);
        this.D = this.y + ((int) (((double) this.a.g) - (((double) this.c.g) + (p * 16.0d))));
        this.B = this.x + ((int) (p * 16.0d));
        this.C = this.x + ((int) (((double) this.a.f) - (((double) this.c.f) + (p * 16.0d))));
    }
}
