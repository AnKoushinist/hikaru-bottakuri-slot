package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import jp.co.mediasdk.android.ImageUtil;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.Util;

public class MSPVAInstallButton {
    private TextView a;
    private WeakReference<Activity> b = null;
    private Drawable c = null;

    public MSPVAInstallButton(Activity activity) {
        this.b = new WeakReference(activity);
        this.a = new TextView((Context) this.b.get());
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MSPVAInstallButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String j = MSPVAVast.a().j("ClickThrough");
                if (!j.isEmpty()) {
                    ((Activity) this.a.b.get()).startActivity(new Intent("android.intent.action.VIEW", Uri.parse(j)));
                }
                j = MSPVAVast.a().j("ClickTracking");
                if (!j.isEmpty()) {
                    MSPVATrackingCheck.b(j);
                }
            }
        });
        if (MSPVAOrientation.b()) {
            this.a.setTextSize(1, 40.0f);
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
        }
        CharSequence j = MSPVAVast.a().j("ClickButtonText");
        if (StringUtilEmptySupport.c(j)) {
            this.a.setText("\u30a4\u30f3\u30b9\u30c8\u30fc\u30eb\u3059\u308b");
        } else {
            this.a.setText(j);
        }
        this.a.setTextColor(-1);
        new LayoutParams(-2, -2).gravity = 17;
        int a = Util.a((Context) this.b.get(), 10);
        this.a.setPadding(a, a, a, a);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 7.0f, activity.getResources().getDisplayMetrics()));
        gradientDrawable.setColor(Color.parseColor("#00CC00"));
        if (VERSION.SDK_INT < 16) {
            this.a.setBackgroundDrawable(gradientDrawable);
        } else {
            this.a.setBackground(gradientDrawable);
        }
        String j2 = MSPVAVast.a().j("ClickButtonImage");
        if (!StringUtilEmptySupport.c(j2)) {
            a(j2);
        }
    }

    public void a(int i) {
        this.a.setTextSize(1, (float) i);
    }

    public void b(int i) {
        this.a.setVisibility(i);
    }

    public TextView a() {
        return this.a;
    }

    public void b() {
        this.a = null;
        this.b = null;
    }

    private void a(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ MSPVAInstallButton b;

            public void run() {
                try {
                    this.b.c = ImageUtil.a((Context) this.b.b.get(), str);
                    this.b.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.b.c != null) {
                                ImageUtil.a(this.a.b.a, this.a.b.c);
                                this.a.b.a.setTextColor(0);
                            }
                        }
                    });
                } catch (OutOfMemoryError e) {
                }
            }
        }).start();
    }
}
