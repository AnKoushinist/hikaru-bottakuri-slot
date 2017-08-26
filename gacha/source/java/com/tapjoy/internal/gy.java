package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tapjoy.TapjoyConnectCore;
import java.util.ArrayList;
import java.util.Iterator;

public final class gy extends RelativeLayout implements OnClickListener {
    private boolean a;
    private float b = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
    private View c;
    private View d;
    private FrameLayout e;
    private ImageView f;
    private gv g;
    private gh h;
    private a i;

    public interface a {
        void a();

        void a(gg ggVar);

        void b();
    }

    public gy(Context context, gh ghVar, a aVar) {
        int i = 1;
        super(context);
        this.h = ghVar;
        this.i = aVar;
        Context context2 = getContext();
        this.c = new View(context2);
        this.c.setId(1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.c, layoutParams);
        this.d = new View(context2);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.d, layoutParams);
        this.e = new FrameLayout(context2);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(13);
        addView(this.e, layoutParams);
        this.f = new ImageView(context2);
        this.f.setOnClickListener(this);
        layoutParams = new RelativeLayout.LayoutParams(0, 0);
        layoutParams.addRule(7, this.c.getId());
        layoutParams.addRule(6, this.c.getId());
        addView(this.f, layoutParams);
        if (this.h.m != null) {
            gi giVar = this.h.m;
            if (giVar.a == null || (giVar.b == null && giVar.c == null)) {
                i = 0;
            }
            if (i != 0) {
                this.g = new gv(context2);
                this.g.setOnClickListener(this);
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, 0);
                layoutParams2.addRule(5, this.d.getId());
                layoutParams2.addRule(8, this.d.getId());
                addView(this.g, layoutParams2);
            }
        }
        this.f.setImageBitmap(ghVar.c.b);
        if (this.g != null && ghVar.m != null && ghVar.m.a != null) {
            this.g.setImageBitmap(ghVar.m.a.b);
        }
    }

    public final void setLandscape(boolean z) {
        Bitmap bitmap;
        Bitmap bitmap2;
        ArrayList arrayList;
        this.a = z;
        if (z) {
            bitmap = this.h.b.b;
            bitmap2 = this.h.f.b;
            arrayList = this.h.j;
        } else {
            bitmap = this.h.a.b;
            bitmap2 = this.h.e.b;
            arrayList = this.h.i;
        }
        ag.a(this.c, new BitmapDrawable(null, bitmap));
        ag.a(this.d, new BitmapDrawable(null, bitmap2));
        if (this.e.getChildCount() > 0) {
            this.e.removeAllViews();
        }
        Context context = getContext();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            gg ggVar = (gg) it.next();
            View view = new View(context);
            view.setTag(ggVar);
            view.setOnClickListener(this);
            this.e.addView(view, new FrameLayout.LayoutParams(0, 0, 51));
        }
    }

    protected final void onMeasure(int i, int i2) {
        int i3 = 15;
        int i4 = 0;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.a) {
            this.b = Math.min(((float) size) / 480.0f, ((float) size2) / 320.0f);
        } else {
            this.b = Math.min(((float) size) / 320.0f, ((float) size2) / 480.0f);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
        layoutParams.width = a(this.a ? 480 : 320);
        layoutParams.height = a(this.a ? 320 : 480);
        layoutParams = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        layoutParams.width = a(this.a ? 448 : 290);
        layoutParams.height = a(this.a ? 290 : 448);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams2.width = layoutParams.width;
        layoutParams2.height = layoutParams.height;
        for (View view : ah.a(this.e)) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) view.getLayoutParams();
            Rect rect = ((gg) view.getTag()).a;
            layoutParams3.width = a(rect.width());
            layoutParams3.height = a(rect.height());
            layoutParams3.leftMargin = a(rect.left);
            layoutParams3.topMargin = a(rect.top);
        }
        size2 = a(0);
        this.f.setPadding(size2, size2, size2, size2);
        layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
        layoutParams.width = a(30);
        layoutParams.height = layoutParams.width;
        layoutParams.rightMargin = (-size2) + a(this.h.d.x);
        layoutParams.topMargin = (-size2) + a(this.h.d.y);
        if (this.g != null) {
            int a = a(this.a ? 16 : 15);
            if (!this.a) {
                i3 = 16;
            }
            int a2 = a(i3);
            this.g.setPadding(size2, size2, size2, size2);
            layoutParams = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams.width = a(26);
            layoutParams.height = layoutParams.width;
            if (this.h.m != null) {
                Point point;
                gi giVar;
                if (this.a) {
                    giVar = this.h.m;
                    if (giVar.b == null) {
                        point = giVar.c;
                    } else {
                        point = giVar.b;
                    }
                } else {
                    giVar = this.h.m;
                    if (giVar.c == null) {
                        point = giVar.b;
                    } else {
                        point = giVar.c;
                    }
                }
                if (point != null) {
                    i4 = point.x;
                    size2 = point.y;
                    layoutParams.leftMargin = a(i4) + a;
                    layoutParams.topMargin = a(size2) + a2;
                }
            }
            size2 = 0;
            layoutParams.leftMargin = a(i4) + a;
            layoutParams.topMargin = a(size2) + a2;
        }
        super.onMeasure(i, i2);
    }

    private int a(int i) {
        return (int) (((float) i) * this.b);
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public final void onClick(View view) {
        if (view == this.f) {
            this.i.a();
        } else if (view != null && view == this.g) {
            gv gvVar = this.g;
            gvVar.a = !gvVar.a;
            gvVar.a();
            gvVar.invalidate();
            this.i.b();
        } else if (view.getTag() instanceof gg) {
            this.i.a((gg) view.getTag());
        }
    }
}
