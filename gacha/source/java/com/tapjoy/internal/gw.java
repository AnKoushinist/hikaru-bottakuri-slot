package com.tapjoy.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.tapjoy.TapjoyConnectCore;
import java.util.ArrayList;
import java.util.Iterator;

public final class gw extends RelativeLayout {
    private gf a;
    private a b;
    private af c = af.UNSPECIFIED;
    private int d = 0;
    private int e = 0;
    private ArrayList f = new ArrayList();
    private gn g = null;

    public interface a {
        void a();

        void a(gm gmVar);
    }

    public gw(Context context, gf gfVar, a aVar) {
        super(context);
        this.a = gfVar;
        this.b = aVar;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a();
    }

    protected final void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (size >= size2) {
            if (this.c != af.LANDSCAPE) {
                this.c = af.LANDSCAPE;
                a();
            }
        } else if (this.c != af.PORTRAIT) {
            this.c = af.PORTRAIT;
            a();
        }
        if (!(this.d == size && this.e == size2)) {
            float f;
            float f2;
            float f3;
            LayoutParams layoutParams;
            gm gmVar;
            float a;
            float a2;
            float a3;
            float a4;
            int i3;
            this.d = size;
            this.e = size2;
            float f4 = (float) size;
            float f5 = (float) size2;
            if (!(this.g == null || this.g.b == null)) {
                float f6 = ((this.g.b.y * f4) / this.g.b.x) / f5;
                if (f6 < TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                    f6 = (this.g.b.y * f4) / this.g.b.x;
                    f = f4;
                    f2 = 0.0f;
                    f3 = (f5 - f6) / 2.0f;
                    f4 = f6;
                } else if (f6 > TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                    f6 = (this.g.b.x * f5) / this.g.b.y;
                    f3 = 0.0f;
                    f2 = (f4 - f6) / 2.0f;
                    f4 = f5;
                    f = f6;
                }
                for (View view : ah.a(this)) {
                    layoutParams = (LayoutParams) view.getLayoutParams();
                    gmVar = (gm) view.getTag();
                    a = gmVar.a.a(f, f4);
                    a2 = gmVar.b.a(f, f4);
                    a3 = gmVar.c.a(f, f4);
                    a4 = gmVar.d.a(f, f4);
                    i3 = gmVar.e;
                    size = gmVar.f;
                    if (i3 == 14) {
                        i3 = 9;
                        a += (f - a3) / 2.0f;
                    }
                    if (size == 15) {
                        size = 10;
                        a2 += (f4 - a4) / 2.0f;
                    }
                    layoutParams.addRule(i3, -1);
                    layoutParams.addRule(size, -1);
                    layoutParams.width = Math.round(a3);
                    layoutParams.height = Math.round(a4);
                    if (i3 == 9) {
                        layoutParams.leftMargin = Math.round(f2 + a);
                    } else if (i3 == 11) {
                        layoutParams.rightMargin = Math.round(f2 + a);
                    }
                    if (size == 10) {
                        layoutParams.topMargin = Math.round(f3 + a2);
                    } else if (size == 12) {
                        layoutParams.bottomMargin = Math.round(f3 + a2);
                    }
                }
            }
            f2 = 0.0f;
            f3 = 0.0f;
            f = f4;
            f4 = f5;
            for (View view2 : ah.a(this)) {
                layoutParams = (LayoutParams) view2.getLayoutParams();
                gmVar = (gm) view2.getTag();
                a = gmVar.a.a(f, f4);
                a2 = gmVar.b.a(f, f4);
                a3 = gmVar.c.a(f, f4);
                a4 = gmVar.d.a(f, f4);
                i3 = gmVar.e;
                size = gmVar.f;
                if (i3 == 14) {
                    i3 = 9;
                    a += (f - a3) / 2.0f;
                }
                if (size == 15) {
                    size = 10;
                    a2 += (f4 - a4) / 2.0f;
                }
                layoutParams.addRule(i3, -1);
                layoutParams.addRule(size, -1);
                layoutParams.width = Math.round(a3);
                layoutParams.height = Math.round(a4);
                if (i3 == 9) {
                    layoutParams.leftMargin = Math.round(f2 + a);
                } else if (i3 == 11) {
                    layoutParams.rightMargin = Math.round(f2 + a);
                }
                if (size == 10) {
                    layoutParams.topMargin = Math.round(f3 + a2);
                } else if (size == 12) {
                    layoutParams.bottomMargin = Math.round(f3 + a2);
                }
            }
        }
        super.onMeasure(i, i2);
    }

    private void a() {
        Object obj;
        Iterator it = this.a.a.iterator();
        Resources resources = null;
        while (it.hasNext()) {
            gn gnVar = (gn) it.next();
            if (gnVar.a == this.c) {
                break;
            }
            if (gnVar.a != af.UNSPECIFIED) {
                obj = resources;
            }
            Object obj2 = gnVar;
        }
        obj = resources;
        removeAllViews();
        if (gnVar != null) {
            this.g = gnVar;
            Context context = getContext();
            Iterator it2 = gnVar.c.iterator();
            while (it2.hasNext()) {
                Bitmap bitmap;
                BitmapDrawable bitmapDrawable;
                final gm gmVar = (gm) it2.next();
                View view = new View(context);
                ViewGroup.LayoutParams layoutParams = new LayoutParams(0, 0);
                Bitmap bitmap2 = gmVar.l.b;
                if (gmVar.m != null) {
                    bitmap = gmVar.m.b;
                } else {
                    bitmap = null;
                }
                final Drawable bitmapDrawable2 = new BitmapDrawable(null, bitmap2);
                if (bitmap != null) {
                    bitmapDrawable = new BitmapDrawable(null, bitmap);
                } else {
                    bitmapDrawable = null;
                }
                ag.a(view, bitmapDrawable2);
                view.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ gw c;

                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            if (bitmapDrawable != null) {
                                ag.a(view, bitmapDrawable);
                            }
                        } else if (motionEvent.getAction() == 1) {
                            ag.a(view, bitmapDrawable2);
                        }
                        return false;
                    }
                });
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ gw b;

                    public final void onClick(View view) {
                        this.b.b.a(gmVar);
                    }
                });
                view.setTag(gmVar);
                addView(view, layoutParams);
            }
        }
    }
}
