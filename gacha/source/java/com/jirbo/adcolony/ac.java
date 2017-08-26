package com.jirbo.adcolony;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import org.cocos2dx.lib.GameControllerDelegate;

class ac extends h {
    boolean H;

    public ac(String str, AdColonyV4VCAd adColonyV4VCAd) {
        this.F = str;
        this.G = adColonyV4VCAd;
        if (a()) {
            AdColony.activity().addContentView(this, new LayoutParams(-1, -1, 17));
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
        if (remainingViewsUntilReward == this.G.getViewsPerReward() || remainingViewsUntilReward == 0) {
            a(this.F, "video. You earned");
            if (s) {
                a("Thanks for watching the sponsored", this.z, (int) (((double) this.A) - (((double) b) * 2.5d)), canvas);
                a("video. You earned " + q + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.5d)), canvas);
            } else {
                a("Thanks for watching the sponsored", this.z, (int) (((double) this.A) - (((double) b) * 2.8d)), canvas);
                a("video. You earned " + q, this.z, (int) (((double) this.A) - (((double) b) * 2.05d)), canvas);
                a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.3d)), canvas);
            }
        } else {
            a(this.F, "to earn ");
            String str = remainingViewsUntilReward == 1 ? "video" : "videos";
            if (s) {
                a("Thank you. Watch " + remainingViewsUntilReward + " more " + str, this.z, (int) (((double) this.A) - (((double) b) * 2.5d)), canvas);
                a("to earn " + q + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.5d)), canvas);
            } else {
                a("Thank you. Watch " + remainingViewsUntilReward + " more " + str, this.z, (int) (((double) this.A) - (((double) b) * 2.8d)), canvas);
                a("to earn " + q, this.z, (int) (((double) this.A) - (((double) b) * 2.05d)), canvas);
                a(r + ".", this.z, (int) (((double) this.A) - (((double) b) * 1.3d)), canvas);
            }
        }
        this.b.a(canvas, this.z - (this.b.f / 2), this.A - (this.b.g / 2));
        if (this.H) {
            this.g.a(canvas, this.B, this.D);
        } else {
            this.h.a(canvas, this.B, this.D);
        }
        c("Ok", this.B, this.D, canvas);
        if (currentTimeMillis != 128) {
            invalidate();
        }
    }

    void d() {
        Display defaultDisplay = a.b().getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        double d = this.n ? 12.0d : 16.0d;
        this.x = (width - this.a.f) / 2;
        this.y = ((height - this.a.g) / 2) - 80;
        this.z = this.x + (this.a.f / 2);
        this.A = this.y + (this.a.g / 2);
        this.D = ((int) (((double) this.a.g) - ((d * p) + ((double) this.h.g)))) + this.y;
        this.B = this.z - (this.h.f / 2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == 1) {
            if (a(x, y, this.B, this.D) && this.H) {
                a.S = null;
                ((ViewGroup) getParent()).removeView(this);
                for (int i = 0; i < a.an.size(); i++) {
                    ((Bitmap) a.an.get(i)).recycle();
                }
                a.an.clear();
                a.E = true;
            }
            this.H = false;
            invalidate();
        }
        if (motionEvent.getAction() == 0 && a(x, y, this.B, this.D)) {
            this.H = true;
            invalidate();
        }
        return true;
    }
}
