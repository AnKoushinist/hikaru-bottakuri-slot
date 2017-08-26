package com.adcolony.sdk;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.tapjoy.TJAdUnitConstants.String;
import jp.co.vaz.creator.hikaru2.R;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class ap extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private al h;
    private o i;

    ap(Context context, o oVar, int i, al alVar) {
        super(context);
        this.h = alVar;
        this.i = oVar;
        this.a = i;
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "id") == this.a && bb.b(b, "container_id") == this.h.c() && bb.a(b, "ad_session_id").equals(this.h.a())) {
            return true;
        }
        return false;
    }

    void a() {
        JSONObject b = this.i.b();
        this.g = bb.a(b, "ad_session_id");
        this.b = bb.b(b, "x");
        this.c = bb.b(b, "y");
        this.d = bb.b(b, "width");
        this.e = bb.b(b, "height");
        this.f = bb.a(b, "color");
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.h.addView(this, layoutParams);
        setBackgroundColor(ab.f(this.f));
        this.h.l().add(n.a("ColorView.set_bounds", new q(this) {
            final /* synthetic */ ap a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.h.l().add(n.a("ColorView.set_visible", new q(this) {
            final /* synthetic */ ap a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.d(oVar);
                }
            }
        }, true));
        this.h.l().add(n.a("ColorView.set_color", new q(this) {
            final /* synthetic */ ap a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.h.m().add("ColorView.set_bounds");
        this.h.m().add("ColorView.set_visible");
        this.h.m().add("ColorView.set_color");
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
        bb.b(a2, "view_id", this.a);
        bb.a(a2, "ad_session_id", this.g);
        bb.b(a2, "container_x", this.b + x);
        bb.b(a2, "container_y", this.c + y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.h.c());
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.h.b(), a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.h.p()) {
                    a.a((ae) h.d().get(this.g));
                }
                new o("AdContainer.on_touch_ended", this.h.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.h.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.h.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action2)) + this.b);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action2)) + this.c);
                bb.b(a2, "view_x", (int) motionEvent.getX(action2));
                bb.b(a2, "view_y", (int) motionEvent.getY(action2));
                new o("AdContainer.on_touch_began", this.h.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action)) + this.b);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action)) + this.c);
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                if (!this.h.p()) {
                    a.a((ae) h.d().get(this.g));
                }
                new o("AdContainer.on_touch_ended", this.h.b(), a2).a();
                break;
        }
        return true;
    }

    void b(o oVar) {
        JSONObject b = oVar.b();
        this.b = bb.b(b, "x");
        this.c = bb.b(b, "y");
        this.d = bb.b(b, "width");
        this.e = bb.b(b, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    void c(o oVar) {
        setBackgroundColor(ab.f(bb.a(oVar.b(), "color")));
    }

    void d(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
