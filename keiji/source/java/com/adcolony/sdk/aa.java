package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.tapjoy.TJAdUnitConstants.String;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class aa extends Button {
    private al A;
    private o B;
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private final int e = 1;
    private final int f = 2;
    private final int g = 3;
    private final int h = 0;
    private final int i = 1;
    private final int j = 2;
    private final int k = 1;
    private final int l = 2;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private String w;
    private String x;
    private String y;
    private String z;

    aa(Context context, o oVar, int i, al alVar) {
        super(context);
        this.m = i;
        this.B = oVar;
        this.A = alVar;
    }

    aa(Context context, int i, o oVar, int i2, al alVar) {
        super(context, null, i);
        this.m = i2;
        this.B = oVar;
        this.A = alVar;
    }

    void a(o oVar) {
        JSONObject b = oVar.b();
        this.u = bb.b(b, "x");
        this.v = bb.b(b, "y");
        setGravity(a(true, this.u) | a(false, this.v));
    }

    int a(boolean z, int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                if (z) {
                    return 1;
                }
                return 16;
            case TwitterResponse.READ /*1*/:
                if (z) {
                    return 3;
                }
                return 48;
            case TwitterResponse.READ_WRITE /*2*/:
                if (z) {
                    return 5;
                }
                return 80;
            default:
                return 17;
        }
    }

    void b(o oVar) {
        JSONObject a = bb.a();
        bb.a(a, "text", getText().toString());
        oVar.a(a).a();
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
        bb.b(a2, "view_id", this.m);
        bb.a(a2, "ad_session_id", this.z);
        bb.b(a2, "container_x", this.n + x);
        bb.b(a2, "container_y", this.o + y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.A.getId());
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.A.b(), a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.A.p()) {
                    a.a((ae) h.d().get(this.z));
                }
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    new o("AdContainer.on_touch_ended", this.A.b(), a2).a();
                    break;
                }
                new o("AdContainer.on_touch_cancelled", this.A.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.A.b(), a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.A.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", ((int) motionEvent.getX(action2)) + this.n);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action2)) + this.o);
                bb.b(a2, "view_x", (int) motionEvent.getX(action2));
                bb.b(a2, "view_y", (int) motionEvent.getY(action2));
                new o("AdContainer.on_touch_began", this.A.b(), a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                x = (int) motionEvent.getX(action);
                y = (int) motionEvent.getY(action);
                bb.b(a2, "container_x", ((int) motionEvent.getX(action)) + this.n);
                bb.b(a2, "container_y", ((int) motionEvent.getY(action)) + this.o);
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                if (!this.A.p()) {
                    a.a((ae) h.d().get(this.z));
                }
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    new o("AdContainer.on_touch_ended", this.A.b(), a2).a();
                    break;
                }
                new o("AdContainer.on_touch_cancelled", this.A.b(), a2).a();
                break;
        }
        return true;
    }

    boolean c(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "id") == this.m && bb.b(b, "container_id") == this.A.c() && bb.a(b, "ad_session_id").equals(this.A.a())) {
            return true;
        }
        return false;
    }

    void a() {
        LayoutParams layoutParams;
        int j;
        int j2;
        JSONObject b = this.B.b();
        this.z = bb.a(b, "ad_session_id");
        this.n = bb.b(b, "x");
        this.o = bb.b(b, "y");
        this.p = bb.b(b, "width");
        this.q = bb.b(b, "height");
        this.s = bb.b(b, "font_family");
        this.r = bb.b(b, "font_style");
        this.t = bb.b(b, "font_size");
        this.w = bb.a(b, "background_color");
        this.x = bb.a(b, "font_color");
        this.y = bb.a(b, "text");
        this.u = bb.b(b, "align_x");
        this.v = bb.b(b, "align_y");
        aq a = n.a();
        if (this.y.equals(BuildConfig.FLAVOR)) {
            this.y = "Learn More";
        }
        setVisibility(4);
        if (bb.c(b, "wrap_content")) {
            layoutParams = new FrameLayout.LayoutParams(-2, -2);
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.p, this.q);
        }
        layoutParams.gravity = 0;
        setText(this.y);
        setTextSize((float) this.t);
        if (bb.c(b, "overlay")) {
            this.n = 0;
            this.o = 0;
            j = (int) (a.a.j() * 6.0f);
            j2 = (int) (a.a.j() * 6.0f);
            int j3 = (int) (a.a.j() * 4.0f);
            setPadding(j3, j3, j3, j3);
            layoutParams.gravity = 85;
        } else {
            j2 = 0;
            j = 0;
        }
        layoutParams.setMargins(this.n, this.o, j, j2);
        this.A.addView(this, layoutParams);
        switch (this.s) {
            case TwitterResponse.NONE /*0*/:
                setTypeface(Typeface.DEFAULT);
                break;
            case TwitterResponse.READ /*1*/:
                setTypeface(Typeface.SERIF);
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                setTypeface(Typeface.SANS_SERIF);
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setTypeface(Typeface.MONOSPACE);
                break;
        }
        switch (this.r) {
            case TwitterResponse.NONE /*0*/:
                setTypeface(getTypeface(), 0);
                break;
            case TwitterResponse.READ /*1*/:
                setTypeface(getTypeface(), 1);
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                setTypeface(getTypeface(), 2);
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setTypeface(getTypeface(), 3);
                break;
        }
        setGravity(a(true, this.u) | a(false, this.v));
        if (!this.w.equals(BuildConfig.FLAVOR)) {
            setBackgroundColor(ab.f(this.w));
        }
        if (!this.x.equals(BuildConfig.FLAVOR)) {
            setTextColor(ab.f(this.x));
        }
        this.A.l().add(n.a("TextView.set_visible", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.k(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_bounds", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.d(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_font_color", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.f(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_background_color", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.e(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_typeface", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.j(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_font_size", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.g(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_font_style", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.h(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.get_text", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.b(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.set_text", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.i(oVar);
                }
            }
        }, true));
        this.A.l().add(n.a("TextView.align", new q(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.a(oVar);
                }
            }
        }, true));
        this.A.m().add("TextView.set_visible");
        this.A.m().add("TextView.set_bounds");
        this.A.m().add("TextView.set_font_color");
        this.A.m().add("TextView.set_background_color");
        this.A.m().add("TextView.set_typeface");
        this.A.m().add("TextView.set_font_size");
        this.A.m().add("TextView.set_font_style");
        this.A.m().add("TextView.get_text");
        this.A.m().add("TextView.set_text");
        this.A.m().add("TextView.align");
        bd.d.b((Object) "TextView added to layout");
    }

    void d(o oVar) {
        JSONObject b = oVar.b();
        this.n = bb.b(b, "x");
        this.o = bb.b(b, "y");
        this.p = bb.b(b, "width");
        this.q = bb.b(b, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.n, this.o, 0, 0);
        layoutParams.width = this.p;
        layoutParams.height = this.q;
        setLayoutParams(layoutParams);
    }

    void e(o oVar) {
        this.w = bb.a(oVar.b(), "background_color");
        setBackgroundColor(ab.f(this.w));
    }

    void f(o oVar) {
        this.x = bb.a(oVar.b(), "font_color");
        setTextColor(ab.f(this.x));
    }

    void g(o oVar) {
        this.t = bb.b(oVar.b(), "font_size");
        setTextSize((float) this.t);
    }

    void h(o oVar) {
        int b = bb.b(oVar.b(), "font_style");
        this.r = b;
        switch (b) {
            case TwitterResponse.NONE /*0*/:
                setTypeface(getTypeface(), 0);
                return;
            case TwitterResponse.READ /*1*/:
                setTypeface(getTypeface(), 1);
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                setTypeface(getTypeface(), 2);
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setTypeface(getTypeface(), 3);
                return;
            default:
                return;
        }
    }

    void i(o oVar) {
        this.y = bb.a(oVar.b(), "text");
        setText(this.y);
    }

    void j(o oVar) {
        int b = bb.b(oVar.b(), "font_family");
        this.s = b;
        switch (b) {
            case TwitterResponse.NONE /*0*/:
                setTypeface(Typeface.DEFAULT);
                return;
            case TwitterResponse.READ /*1*/:
                setTypeface(Typeface.SERIF);
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                setTypeface(Typeface.SANS_SERIF);
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setTypeface(Typeface.MONOSPACE);
                return;
            default:
                return;
        }
    }

    void k(o oVar) {
        if (bb.c(oVar.b(), String.VISIBLE)) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
