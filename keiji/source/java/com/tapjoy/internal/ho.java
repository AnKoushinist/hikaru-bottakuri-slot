package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.tapjoy.TapjoyConnectCore;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public final class ho extends RelativeLayout {
    private gr a;
    private a b;
    private af c = af.UNSPECIFIED;
    private int d = 0;
    private int e = 0;
    private ha f = null;
    private ArrayList g = null;
    private ArrayList h = null;

    public interface a {
        void a();

        void a(gz gzVar);
    }

    public ho(Context context, gr grVar, a aVar) {
        super(context);
        this.a = grVar;
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
            gz gzVar;
            float a;
            float a2;
            float a3;
            float a4;
            int i3;
            this.d = size;
            this.e = size2;
            float f4 = (float) size;
            float f5 = (float) size2;
            if (!(this.f == null || this.f.b == null)) {
                float f6 = ((this.f.b.y * f4) / this.f.b.x) / f5;
                if (f6 < TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                    f6 = (this.f.b.y * f4) / this.f.b.x;
                    f = f4;
                    f2 = 0.0f;
                    f3 = (f5 - f6) / 2.0f;
                    f4 = f6;
                } else if (f6 > TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                    f6 = (this.f.b.x * f5) / this.f.b.y;
                    f3 = 0.0f;
                    f2 = (f4 - f6) / 2.0f;
                    f4 = f5;
                    f = f6;
                }
                for (View view : ah.a(this)) {
                    layoutParams = (LayoutParams) view.getLayoutParams();
                    gzVar = (gz) view.getTag();
                    a = gzVar.a.a(f, f4);
                    a2 = gzVar.b.a(f, f4);
                    a3 = gzVar.c.a(f, f4);
                    a4 = gzVar.d.a(f, f4);
                    i3 = gzVar.e;
                    size = gzVar.f;
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
                gzVar = (gz) view2.getTag();
                a = gzVar.a.a(f, f4);
                a2 = gzVar.b.a(f, f4);
                a3 = gzVar.c.a(f, f4);
                a4 = gzVar.d.a(f, f4);
                i3 = gzVar.e;
                size = gzVar.f;
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

    protected final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Iterator it;
        hg hgVar;
        if (i == 0) {
            if (this.h != null) {
                it = this.h.iterator();
                while (it.hasNext()) {
                    hgVar = (hg) ((WeakReference) it.next()).get();
                    if (hgVar != null) {
                        hgVar.setVisibility(4);
                        hgVar.b();
                    }
                }
            }
            if (this.g != null) {
                it = this.g.iterator();
                while (it.hasNext()) {
                    hgVar = (hg) ((WeakReference) it.next()).get();
                    if (hgVar != null) {
                        hgVar.setVisibility(0);
                        hgVar.a();
                    }
                }
                return;
            }
            return;
        }
        if (this.g != null) {
            it = this.g.iterator();
            while (it.hasNext()) {
                hgVar = (hg) ((WeakReference) it.next()).get();
                if (hgVar != null) {
                    hgVar.b();
                }
            }
        }
        if (this.h != null) {
            it = this.h.iterator();
            while (it.hasNext()) {
                hgVar = (hg) ((WeakReference) it.next()).get();
                if (hgVar != null) {
                    hgVar.b();
                }
            }
        }
    }

    private void a() {
        hg hgVar;
        Iterator it = this.a.a.iterator();
        ha haVar = null;
        while (it.hasNext()) {
            ha haVar2 = (ha) it.next();
            if (haVar2.a == this.c) {
                haVar = haVar2;
                break;
            }
            if (haVar2.a != af.UNSPECIFIED) {
                haVar2 = haVar;
            }
            haVar = haVar2;
        }
        removeAllViews();
        if (this.g != null) {
            it = this.g.iterator();
            while (it.hasNext()) {
                hgVar = (hg) ((WeakReference) it.next()).get();
                if (hgVar != null) {
                    hgVar.c();
                }
            }
            this.g.clear();
        }
        if (this.h != null) {
            it = this.h.iterator();
            while (it.hasNext()) {
                hgVar = (hg) ((WeakReference) it.next()).get();
                if (hgVar != null) {
                    hgVar.c();
                }
            }
            this.h.clear();
        }
        if (haVar != null) {
            this.f = haVar;
            Context context = getContext();
            Iterator it2 = haVar.c.iterator();
            while (it2.hasNext()) {
                View hgVar2;
                View view;
                Bitmap bitmap;
                Drawable bitmapDrawable;
                BitmapDrawable bitmapDrawable2;
                gz gzVar = (gz) it2.next();
                View relativeLayout = new RelativeLayout(context);
                if (gzVar.l.c != null) {
                    hgVar2 = new hg(context);
                    hgVar2.setScaleType(ScaleType.FIT_XY);
                    hgVar2.a(gzVar.l.d, gzVar.l.c);
                    if (this.g == null) {
                        this.g = new ArrayList();
                    }
                    this.g.add(new WeakReference(hgVar2));
                } else {
                    hgVar2 = null;
                }
                if (gzVar.m == null || gzVar.m.c == null) {
                    view = null;
                } else {
                    view = new hg(context);
                    view.setScaleType(ScaleType.FIT_XY);
                    view.a(gzVar.m.d, gzVar.m.c);
                    if (this.h == null) {
                        this.h = new ArrayList();
                    }
                    this.h.add(new WeakReference(view));
                }
                ViewGroup.LayoutParams layoutParams = new LayoutParams(0, 0);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                Bitmap bitmap2 = gzVar.l.b;
                if (gzVar.m != null) {
                    bitmap = gzVar.m.b;
                } else {
                    bitmap = null;
                }
                if (bitmap2 != null) {
                    bitmapDrawable = new BitmapDrawable(null, bitmap2);
                } else {
                    bitmapDrawable = null;
                }
                if (bitmap != null) {
                    bitmapDrawable2 = new BitmapDrawable(null, bitmap);
                } else {
                    bitmapDrawable2 = null;
                }
                if (bitmapDrawable != null) {
                    ag.a(relativeLayout, bitmapDrawable);
                }
                if (hgVar2 != null) {
                    relativeLayout.addView(hgVar2, layoutParams2);
                    hgVar2.a();
                }
                if (view != null) {
                    relativeLayout.addView(view, layoutParams2);
                    view.setVisibility(4);
                }
                relativeLayout.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ ho e;

                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        boolean z = true;
                        if (motionEvent.getAction() == 0) {
                            if (!(view == null && bitmapDrawable2 == null)) {
                                if (hgVar2 != null) {
                                    hgVar2.b();
                                    hgVar2.setVisibility(4);
                                }
                                ag.a(view, null);
                            }
                            if (bitmapDrawable2 != null) {
                                ag.a(view, bitmapDrawable2);
                            } else if (view != null) {
                                view.setVisibility(0);
                                view.a();
                            }
                        } else if (motionEvent.getAction() == 1) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            if (x >= 0.0f && x < ((float) view.getWidth()) && y >= 0.0f && y < ((float) view.getHeight())) {
                                z = false;
                            }
                            if (z) {
                                if (bitmapDrawable != null) {
                                    ag.a(view, bitmapDrawable);
                                } else if (bitmapDrawable2 != null) {
                                    ag.a(view, null);
                                }
                            }
                            if (view != null) {
                                view.b();
                                view.setVisibility(4);
                            }
                            if (!((view == null && bitmapDrawable2 == null) || hgVar2 == null || !z)) {
                                hgVar2.setVisibility(0);
                                hgVar2.a();
                            }
                        }
                        return false;
                    }
                });
                final View view2 = relativeLayout;
                final gz gzVar2 = gzVar;
                relativeLayout.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ho e;

                    public final void onClick(View view) {
                        if (view != null) {
                            view.b();
                            view2.removeView(view);
                        }
                        if (hgVar2 != null) {
                            hgVar2.b();
                            view2.removeView(hgVar2);
                        }
                        this.e.b.a(gzVar2);
                    }
                });
                relativeLayout.setTag(gzVar);
                addView(relativeLayout, layoutParams);
            }
        }
    }
}
