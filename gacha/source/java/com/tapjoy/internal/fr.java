package com.tapjoy.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.BadTokenException;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TJContentActivity.AbstractContentProducer;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.internal.gy.a;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public final class fr extends ft {
    public static fr a;
    final fm b;
    final String c;
    final gh d;
    e e;
    long f;
    boolean g = false;
    private boolean k;
    private Context l;

    public fr(fm fmVar, String str, gh ghVar, Context context) {
        this.b = fmVar;
        this.c = str;
        this.d = ghVar;
        this.l = context;
    }

    public final void a() {
        gh ghVar = this.d;
        if (ghVar.a != null) {
            ghVar.a.a();
        }
        if (ghVar.b != null) {
            ghVar.b.a();
        }
        ghVar.c.a();
        if (ghVar.e != null) {
            ghVar.e.a();
        }
        if (ghVar.f != null) {
            ghVar.f.a();
        }
        if (ghVar.m != null && ghVar.m.a != null) {
            ghVar.m.a.a();
        }
    }

    public final boolean b() {
        gh ghVar = this.d;
        return (ghVar.c == null || ghVar.c.b == null || ((ghVar.m != null && ghVar.m.a != null && ghVar.m.a.b == null) || ((ghVar.b == null || ghVar.f == null || ghVar.b.b == null || ghVar.f.b == null) && (ghVar.a == null || ghVar.e == null || ghVar.a.b == null || ghVar.e.b == null)))) ? false : true;
    }

    public final void a(final fn fnVar) {
        Activity a = c.a(this.l);
        if (!(a == null || a.isFinishing())) {
            try {
                a(a, fnVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e) {
            }
        }
        Activity a2 = fe.a();
        boolean z = a2 != null ? (a2.getWindow().getAttributes().flags & 1024) != 0 : false;
        try {
            TJContentActivity.start(fm.a().e, new AbstractContentProducer(this) {
                final /* synthetic */ fr b;

                public final void show(Activity activity) {
                    try {
                        this.b.a(activity, fnVar);
                    } catch (BadTokenException e) {
                        fj.b("Failed to show the content for \"{}\" caused by invalid activity", this.b.c);
                        fnVar.b(this.b.c, null);
                    }
                }

                public final void dismiss(Activity activity) {
                    this.b.c();
                }
            }, z);
            new Object[1][0] = this.c;
        } catch (ActivityNotFoundException e2) {
            if (!(a2 == null || a2.isFinishing())) {
                try {
                    a(a2, fnVar);
                    new Object[1][0] = this.c;
                    return;
                } catch (BadTokenException e3) {
                    fj.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.c);
                    fnVar.b(this.c, null);
                }
            }
            fj.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.c);
            fnVar.b(this.c, null);
        }
    }

    final void a(final Activity activity, final fn fnVar) {
        cq.a(!this.k);
        this.k = true;
        a = this;
        this.e = new e(activity);
        this.e.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ fr b;

            public final void onCancel(DialogInterface dialogInterface) {
                fnVar.d(this.b.c);
            }
        });
        this.e.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ fr c;

            public final void onDismiss(DialogInterface dialogInterface) {
                fr.a = null;
                ft.a(activity, this.c.d.g);
                this.c.b.a(this.c.d.k, SystemClock.elapsedRealtime() - this.c.f);
                if (!this.c.i) {
                    fnVar.b(this.c.c, this.c.d.h);
                }
                if (this.c.g && this.c.d.k != null && this.c.d.k.containsKey("action_id")) {
                    String obj = this.c.d.k.get("action_id").toString();
                    if (obj != null && obj.length() > 0) {
                        fm fmVar = this.c.b;
                        if (fmVar.b != null) {
                            Object obj2;
                            fv fvVar = fmVar.b;
                            String a = fv.a();
                            String a2 = fvVar.b.a();
                            String a3 = fvVar.a.a();
                            if (a3 == null || !a.equals(a3)) {
                                fvVar.a.a(a);
                                a2 = BuildConfig.FLAVOR;
                            }
                            if (a2.length() == 0) {
                                obj2 = 1;
                            } else {
                                obj2 = null;
                            }
                            if (obj2 != null) {
                                a2 = obj;
                            } else if (!a2.contains(obj)) {
                                a2 = a2.concat("," + obj);
                            }
                            fvVar.b.a(a2);
                        }
                    }
                }
                if (activity instanceof TJContentActivity) {
                    activity.finish();
                }
            }
        });
        this.e.setCanceledOnTouchOutside(false);
        View gxVar = new gx(activity, this.d, new gy(activity, this.d, new a(this) {
            final /* synthetic */ fr c;

            public final void a() {
                this.c.e.cancel();
            }

            public final void a(gg ggVar) {
                this.c.b.a(this.c.d.k, ggVar.b);
                ft.a(activity, ggVar.d);
                if (!cr.c(ggVar.e)) {
                    this.c.j.a(activity, ggVar.e, cr.b(ggVar.f));
                    this.c.i = true;
                }
                fnVar.a(this.c.c, ggVar.g);
                if (ggVar.c) {
                    this.c.e.dismiss();
                }
            }

            public final void b() {
                boolean z;
                fr frVar = this.c;
                if (this.c.g) {
                    z = false;
                } else {
                    z = true;
                }
                frVar.g = z;
            }
        }));
        View frameLayout = new FrameLayout(activity);
        frameLayout.addView(gxVar, new LayoutParams(-2, -2, 17));
        this.e.setContentView(frameLayout);
        if (Boolean.FALSE.booleanValue()) {
            boolean z;
            int i;
            aj ajVar;
            al alVar;
            Window window = this.e.getWindow();
            if (VERSION.SDK_INT == 16 && "4.1.2".equals(VERSION.RELEASE)) {
                if (Boolean.FALSE.equals(a(window.getContext()))) {
                    z = false;
                    if (z) {
                        i = ai.a.b;
                        ajVar = new aj();
                        switch (com.tapjoy.internal.ai.AnonymousClass1.a[i - 1]) {
                            case TwitterResponse.READ /*1*/:
                                alVar = new al();
                                alVar.a = false;
                                alVar.b = 60.0f;
                                ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER).b(0.3f).a());
                                break;
                            case TwitterResponse.READ_WRITE /*2*/:
                                alVar = new al();
                                alVar.a = false;
                                alVar.b = -60.0f;
                                ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(-0.4f).b(0.3f).a());
                                break;
                            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                                alVar = new al();
                                alVar.a = true;
                                alVar.b = -60.0f;
                                ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER).a());
                                break;
                            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                                alVar = new al();
                                alVar.a = true;
                                alVar.b = 60.0f;
                                ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(-0.4f).a());
                                break;
                        }
                        gxVar.startAnimation(ajVar.b().a());
                    }
                } else {
                    window.setFlags(16777216, 16777216);
                }
            }
            z = true;
            if (z) {
                i = ai.a.b;
                ajVar = new aj();
                switch (com.tapjoy.internal.ai.AnonymousClass1.a[i - 1]) {
                    case TwitterResponse.READ /*1*/:
                        alVar = new al();
                        alVar.a = false;
                        alVar.b = 60.0f;
                        ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER).b(0.3f).a());
                        break;
                    case TwitterResponse.READ_WRITE /*2*/:
                        alVar = new al();
                        alVar.a = false;
                        alVar.b = -60.0f;
                        ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(-0.4f).b(0.3f).a());
                        break;
                    case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                        alVar = new al();
                        alVar.a = true;
                        alVar.b = -60.0f;
                        ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER).a());
                        break;
                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                        alVar = new al();
                        alVar.a = true;
                        alVar.b = 60.0f;
                        ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(-0.4f).a());
                        break;
                }
                gxVar.startAnimation(ajVar.b().a());
            }
        }
        try {
            this.e.show();
            this.e.getWindow().setLayout(-1, -1);
            if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
                this.e.getWindow().setFlags(1024, 1024);
            }
            this.f = SystemClock.elapsedRealtime();
            this.b.a(this.d.k);
            fnVar.c(this.c);
        } catch (BadTokenException e) {
            throw e;
        }
    }

    public final void c() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    private static Boolean a(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                Object obj = bundle.get("tapjoy:hardwareAccelerated");
                if (obj instanceof Boolean) {
                    return (Boolean) obj;
                }
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }
}
