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
import com.tapjoy.internal.hq.a;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public final class ge extends gg {
    public static ge a;
    final fz b;
    final String c;
    final gu d;
    e e;
    long f;
    boolean g = false;
    private boolean l;
    private Context m;

    public ge(fz fzVar, String str, gu guVar, Context context) {
        this.b = fzVar;
        this.c = str;
        this.d = guVar;
        this.m = context;
    }

    public final void a() {
        gu guVar = this.d;
        if (guVar.a != null) {
            guVar.a.b();
        }
        if (guVar.b != null) {
            guVar.b.b();
        }
        guVar.c.b();
        if (guVar.e != null) {
            guVar.e.b();
        }
        if (guVar.f != null) {
            guVar.f.b();
        }
        if (guVar.m != null && guVar.m.a != null) {
            guVar.m.a.b();
        }
    }

    public final boolean b() {
        gu guVar = this.d;
        return (guVar.c == null || guVar.c.b == null || ((guVar.m != null && guVar.m.a != null && guVar.m.a.b == null) || ((guVar.b == null || guVar.f == null || guVar.b.b == null || guVar.f.b == null) && (guVar.a == null || guVar.e == null || guVar.a.b == null || guVar.e.b == null)))) ? false : true;
    }

    public final void a(final ga gaVar, final ev evVar) {
        Activity a = c.a(this.m);
        if (!(a == null || a.isFinishing())) {
            try {
                a(a, gaVar, evVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e) {
            }
        }
        Activity a2 = fr.a();
        boolean z = a2 != null ? (a2.getWindow().getAttributes().flags & 1024) != 0 : false;
        try {
            TJContentActivity.start(fz.a().e, new AbstractContentProducer(this) {
                final /* synthetic */ ge c;

                public final void show(Activity activity) {
                    try {
                        this.c.a(activity, gaVar, evVar);
                    } catch (BadTokenException e) {
                        fw.b("Failed to show the content for \"{}\" caused by invalid activity", this.c.c);
                        gaVar.a(this.c.c, this.c.k, null);
                    }
                }

                public final void dismiss(Activity activity) {
                    this.c.c();
                }
            }, z);
            new Object[1][0] = this.c;
        } catch (ActivityNotFoundException e2) {
            if (!(a2 == null || a2.isFinishing())) {
                try {
                    a(a2, gaVar, evVar);
                    new Object[1][0] = this.c;
                    return;
                } catch (BadTokenException e3) {
                    fw.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.c);
                    gaVar.a(this.c, this.k, null);
                }
            }
            fw.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.c);
            gaVar.a(this.c, this.k, null);
        }
    }

    final void a(final Activity activity, final ga gaVar, ev evVar) {
        cs.a(!this.l);
        this.l = true;
        a = this;
        this.e = new e(activity);
        this.e.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ ge b;

            public final void onCancel(DialogInterface dialogInterface) {
                gaVar.d(this.b.c);
            }
        });
        this.e.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ ge c;

            public final void onDismiss(DialogInterface dialogInterface) {
                ge.a = null;
                gg.a(activity, this.c.d.g);
                this.c.b.a(this.c.d.k, SystemClock.elapsedRealtime() - this.c.f);
                if (!this.c.i) {
                    gaVar.a(this.c.c, this.c.k, this.c.d.h);
                }
                if (this.c.g && this.c.d.k != null && this.c.d.k.containsKey("action_id")) {
                    String obj = this.c.d.k.get("action_id").toString();
                    if (obj != null && obj.length() > 0) {
                        fz fzVar = this.c.b;
                        if (fzVar.b != null) {
                            Object obj2;
                            gi giVar = fzVar.b;
                            String a = gi.a();
                            String a2 = giVar.b.a();
                            String a3 = giVar.a.a();
                            if (a3 == null || !a.equals(a3)) {
                                giVar.a.a(a);
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
                            giVar.b.a(a2);
                        }
                    }
                }
                if (activity instanceof TJContentActivity) {
                    activity.finish();
                }
            }
        });
        this.e.setCanceledOnTouchOutside(false);
        View hpVar = new hp(activity, this.d, new hq(activity, this.d, new a(this) {
            final /* synthetic */ ge c;

            public final void a() {
                this.c.e.cancel();
            }

            public final void a(gs gsVar) {
                this.c.b.a(this.c.d.k, gsVar.b);
                gg.a(activity, gsVar.d);
                if (!ct.c(gsVar.e)) {
                    this.c.j.a(activity, gsVar.e, ct.b(gsVar.f));
                    this.c.i = true;
                }
                gaVar.a(this.c.c, gsVar.g);
                if (gsVar.c) {
                    this.c.e.dismiss();
                }
            }

            public final void b() {
                boolean z;
                ge geVar = this.c;
                if (this.c.g) {
                    z = false;
                } else {
                    z = true;
                }
                geVar.g = z;
            }
        }));
        View frameLayout = new FrameLayout(activity);
        frameLayout.addView(hpVar, new LayoutParams(-2, -2, 17));
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                                alVar = new al();
                                alVar.a = true;
                                alVar.b = 60.0f;
                                ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(-0.4f).a());
                                break;
                        }
                        hpVar.startAnimation(ajVar.b().a());
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
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                        alVar = new al();
                        alVar.a = true;
                        alVar.b = 60.0f;
                        ajVar.a(alVar.a()).a(new ScaleAnimation(0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.4f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)).a(new am().a(0.3f).b(-0.4f).a());
                        break;
                }
                hpVar.startAnimation(ajVar.b().a());
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
            evVar.a();
            gaVar.c(this.c);
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
