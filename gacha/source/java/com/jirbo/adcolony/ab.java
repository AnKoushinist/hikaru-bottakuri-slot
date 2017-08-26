package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

class ab extends h {
    boolean H;
    boolean I;
    boolean J = true;

    public ab(String str, AdColonyV4VCAd adColonyV4VCAd) {
        this.F = str;
        this.G = adColonyV4VCAd;
        if (a()) {
            AdColony.activity().addContentView(this, new LayoutParams(-1, -1, 17));
            if (this.n) {
                this.D += 20;
            }
        }
    }

    protected void onDetachedFromWindow() {
        if (this.J) {
            a.S = null;
            this.J = false;
            this.G.c(false);
            a.E = true;
            for (int i = 0; i < a.an.size(); i++) {
                ((Bitmap) a.an.get(i)).recycle();
            }
            a.an.clear();
        }
    }

    public void onDraw(Canvas canvas) {
        d();
        int currentTimeMillis = (((int) (System.currentTimeMillis() - this.w)) * 255) / GameControllerDelegate.THUMBSTICK_LEFT_X;
        if (currentTimeMillis > 128) {
            currentTimeMillis = 128;
        }
        canvas.drawARGB(currentTimeMillis, 0, 0, 0);
        this.a.a(canvas, this.x, this.y);
        int b = (b() * 3) / 2;
        int remainingViewsUntilReward = this.G.getRemainingViewsUntilReward();
        if (this.G.getViewsPerReward() == 1 || this.G.getViewsPerReward() == 0) {
            a(this.F, BuildConfig.FLAVOR);
            if (s) {
                a("Watch a video to earn", this.z, (int) (((double) this.A) - (((double) b) * this.l)), canvas);
                a(q + ".", this.z, (int) (((double) this.A) - (((double) b) * this.m)), canvas);
            } else {
                a("Watch a video to earn", this.z, (int) (((double) this.A) - (((double) b) * this.i)), canvas);
                a(q, this.z, (int) (((double) this.A) - (((double) b) * this.j)), canvas);
                a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * this.k)), canvas);
            }
        } else {
            String str = remainingViewsUntilReward == 1 ? "video" : "videos";
            a(this.F, BuildConfig.FLAVOR + remainingViewsUntilReward + " more " + str + " to earn )?");
            if (s) {
                a("Watch a sponsored video now (Only", this.z, (int) (((double) this.A) - (((double) b) * this.l)), canvas);
                a(BuildConfig.FLAVOR + remainingViewsUntilReward + " more " + str + " to earn " + q + ")?", this.z, (int) (((double) this.A) - (((double) b) * this.m)), canvas);
            } else {
                a("Watch a sponsored video now (Only", this.z, (int) (((double) this.A) - (((double) b) * this.i)), canvas);
                a(BuildConfig.FLAVOR + remainingViewsUntilReward + " more " + str + " to earn " + q, this.z, (int) (((double) this.A) - (((double) b) * this.j)), canvas);
                a(r + ")?", this.z, (int) (((double) this.A) - (((double) b) * this.k)), canvas);
            }
        }
        this.b.a(canvas, this.z - (this.b.f / 2), this.A - (this.b.g / 2));
        if (this.I) {
            this.d.a(canvas, this.B, this.D);
        } else {
            this.c.a(canvas, this.B, this.D);
        }
        if (this.H) {
            this.f.a(canvas, this.C, this.D);
        } else {
            this.e.a(canvas, this.C, this.D);
        }
        c("Yes", this.B, this.D, canvas);
        c("No", this.C, this.D, canvas);
        if (currentTimeMillis != 128) {
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 1) {
            if (a(x, y, this.B, this.D) && this.I) {
                a.S = null;
                this.J = false;
                ((ViewGroup) getParent()).removeView(this);
                this.G.c(true);
            } else if (a(x, y, this.C, this.D) && this.H) {
                a.S = null;
                this.J = false;
                ((ViewGroup) getParent()).removeView(this);
                this.G.c(false);
                a.E = true;
                for (int i = 0; i < a.an.size(); i++) {
                    ((Bitmap) a.an.get(i)).recycle();
                }
                a.an.clear();
            }
            this.H = false;
            this.I = false;
            invalidate();
        }
        if (motionEvent.getAction() == 0) {
            if (a(x, y, this.B, this.D)) {
                this.I = true;
                invalidate();
            } else if (a(x, y, this.C, this.D)) {
                this.H = true;
                invalidate();
            }
        }
        return true;
    }
}
