package com.adcolony.sdk;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import jp.co.vaz.creator.hikaru2.R;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class az extends ImageView {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private o k;
    private al l;

    az(Context context, o oVar, int i, al alVar) {
        super(context);
        this.a = i;
        this.k = oVar;
        this.l = alVar;
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
        bb.a(a2, "ad_session_id", this.j);
        bb.b(a2, "container_x", this.b + x);
        bb.b(a2, "container_y", this.c + y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.l.getId());
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.l.b(), a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.l.p()) {
                    a.a((ae) h.d().get(this.j));
                }
                if (x > 0 && x < this.d && y > 0 && y < this.e) {
                    new o("AdContainer.on_touch_ended", this.l.b(), a2).a();
                    break;
                }
                new o("AdContainer.on_touch_cancelled", this.l.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.l.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.l.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action2)) + this.b);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action2)) + this.c);
                bb.b(a2, "view_x", (int) motionEvent.getX(action2));
                bb.b(a2, "view_y", (int) motionEvent.getY(action2));
                new o("AdContainer.on_touch_began", this.l.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                x = (int) motionEvent.getX(action);
                y = (int) motionEvent.getY(action);
                bb.b(a2, "container_x", ((int) motionEvent.getX(action)) + this.b);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action)) + this.c);
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                if (!this.l.p()) {
                    a.a((ae) h.d().get(this.j));
                }
                if (x > 0 && x < this.d && y > 0 && y < this.e) {
                    new o("AdContainer.on_touch_ended", this.l.b(), a2).a();
                    break;
                }
                new o("AdContainer.on_touch_cancelled", this.l.b(), a2).a();
                break;
        }
        return true;
    }

    private boolean a(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "id") == this.a && bb.b(b, "container_id") == this.l.c() && bb.a(b, "ad_session_id").equals(this.l.a())) {
            return true;
        }
        return false;
    }

    void a() {
        JSONObject b = this.k.b();
        this.j = bb.a(b, "ad_session_id");
        this.b = bb.b(b, "x");
        this.c = bb.b(b, "y");
        this.d = bb.b(b, "width");
        this.e = bb.b(b, "height");
        this.i = bb.a(b, "filepath");
        this.f = bb.c(b, "dpi");
        this.g = bb.c(b, "invert_y");
        this.h = bb.c(b, "wrap_content");
        setImageURI(Uri.fromFile(new File(this.i)));
        if (this.f) {
            float j = (n.a().i().j() * ((float) this.e)) / ((float) getDrawable().getIntrinsicHeight());
            this.e = (int) (((float) getDrawable().getIntrinsicHeight()) * j);
            this.d = (int) (j * ((float) getDrawable().getIntrinsicWidth()));
            this.b -= this.d;
            this.c = this.g ? this.c + this.e : this.c - this.e;
        }
        setVisibility(4);
        LayoutParams layoutParams = this.h ? new FrameLayout.LayoutParams(-2, -2) : new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.l.addView(this, layoutParams);
        this.l.l().add(n.a("ImageView.set_visible", new q(this) {
            final /* synthetic */ az a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.d(oVar);
                }
            }
        }, true));
        this.l.l().add(n.a("ImageView.set_bounds", new q(this) {
            final /* synthetic */ az a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.l.l().add(n.a("ImageView.set_image", new q(this) {
            final /* synthetic */ az a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.l.m().add("ImageView.set_visible");
        this.l.m().add("ImageView.set_bounds");
        this.l.m().add("ImageView.set_image");
    }

    private void b(o oVar) {
        JSONObject b = oVar.b();
        this.b = bb.b(b, "x");
        this.c = bb.b(b, "y");
        this.d = bb.b(b, "width");
        this.e = bb.b(b, "height");
        if (this.f) {
            float j = (n.a().i().j() * ((float) this.e)) / ((float) getDrawable().getIntrinsicHeight());
            this.e = (int) (((float) getDrawable().getIntrinsicHeight()) * j);
            this.d = (int) (j * ((float) getDrawable().getIntrinsicWidth()));
            this.b -= this.d;
            this.c -= this.e;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    private void c(o oVar) {
        this.i = bb.a(oVar.b(), "filepath");
        setImageURI(Uri.fromFile(new File(this.i)));
    }

    private void d(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
