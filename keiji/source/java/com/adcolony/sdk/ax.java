package com.adcolony.sdk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.tapjoy.TJAdUnitConstants.String;
import jp.co.vaz.creator.hikaru2.R;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class ax extends GLSurfaceView {
    ay a;
    int b;
    int c;
    int d;
    int e;
    int f;
    String g;
    boolean h;
    al i;
    o j;
    boolean k;

    ax(Context context, o oVar, int i, al alVar) {
        super(context);
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        this.b = i;
        this.j = oVar;
        this.i = alVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        aq a = n.a();
        am h = a.h();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject a2 = bb.a();
        bb.b(a2, "view_id", this.b);
        bb.a(a2, "ad_session_id", this.g);
        bb.b(a2, "container_x", this.c + x);
        bb.b(a2, "container_y", this.d + y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.i.c());
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.i.b(), a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.i.p()) {
                    a.a((ae) h.d().get(this.g));
                }
                new o("AdContainer.on_touch_ended", this.i.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.i.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.i.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action2)) + this.c);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action2)) + this.d);
                bb.b(a2, "view_x", (int) motionEvent.getX(action2));
                bb.b(a2, "view_y", (int) motionEvent.getY(action2));
                new o("AdContainer.on_touch_began", this.i.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action)) + this.c);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action)) + this.d);
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                if (!this.i.p()) {
                    a.a((ae) h.d().get(this.g));
                }
                new o("AdContainer.on_touch_ended", this.i.b(), a2).a();
                break;
        }
        return true;
    }

    void a() {
        JSONObject b = this.j.b();
        this.g = bb.a(b, "ad_session_id");
        this.c = bb.b(b, "x");
        this.d = bb.b(b, "y");
        this.e = bb.b(b, "width");
        this.f = bb.b(b, "height");
        this.h = bb.c(b, String.TRANSPARENT);
        setEGLConfigChooser(8, 8, 8, 8, 16, 8);
        if (this.h) {
            getHolder().setFormat(-3);
            setZOrderOnTop(true);
        } else {
            getHolder().setFormat(1);
        }
        this.a = new ay(this, true, this.g);
        setRenderer(this.a);
        this.i.l().add(n.a("RenderView.set_visible", new q(this) {
            final /* synthetic */ ax a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.i.l().add(n.a("RenderView.set_bounds", new q(this) {
            final /* synthetic */ ax a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.i.m().add("RenderView.set_visible");
        this.i.m().add("RenderView.set_bounds");
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.e, this.f);
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.gravity = 0;
        this.i.addView(this, layoutParams);
    }

    boolean b() {
        if (this.k) {
            return false;
        }
        this.k = true;
        this.a.a();
        return true;
    }

    protected void finalize() {
        b();
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "id") == this.b && bb.b(b, "container_id") == this.i.c() && bb.a(b, "ad_session_id").equals(this.i.a())) {
            return true;
        }
        return false;
    }

    void b(o oVar) {
        JSONObject b = oVar.b();
        this.c = bb.b(b, "x");
        this.d = bb.b(b, "y");
        this.e = bb.b(b, "width");
        this.f = bb.b(b, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.width = this.e;
        layoutParams.height = this.f;
        setLayoutParams(layoutParams);
        getHolder().setFixedSize(this.e, this.f);
    }

    void c(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
