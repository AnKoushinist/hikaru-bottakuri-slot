package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import java.lang.ref.WeakReference;
import jp.co.mediasdk.android.LayoutUtil;
import jp.co.mediasdk.android.LayoutUtilMarginSupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.WindowUtil;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListenerManager;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.pva.MSPVACloseButton.OnCloseListener;
import jp.co.mediasdk.mscore.ui.pva.MSPVAVideoView.OnCurrentVideoTimeListener;

public class MSPVAActivityVideo {
    private WeakReference<Activity> a;
    private FrameLayout b = null;
    private MSPVAVideoView c = null;
    private MSPVAEndCard d;
    private ProgressBar e;
    private boolean f = false;
    private boolean g = true;
    private MSPVACloseButton h;
    private MSPVACloseButton i;
    private MSPVAVideoTimeDisplay j;
    private int k = 0;
    private int l = 0;
    private MSPVAVideoListener m = null;

    public MSPVAActivityVideo(Activity activity, FrameLayout frameLayout, MSPVAVideoListener mSPVAVideoListener) {
        this.m = mSPVAVideoListener;
        this.a = new WeakReference(activity);
        this.b = frameLayout;
        a(MSPVAVast.a().j("MediaFile"));
    }

    public void a(String str) {
        this.g = true;
        if (!this.f) {
            this.f = true;
            try {
                if (MSPVAType.b()) {
                    this.c = new MSPVAVideoView((Context) this.a.get(), this.b);
                } else {
                    this.c = new MSPVAVideoView((Context) this.a.get(), null);
                }
                this.c.requestFocus();
                WindowUtil.a((Activity) this.a.get());
                this.b.addView(this.c, 0, m());
                this.c.setOnPreparedListener(new OnPreparedListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public void onPrepared(MediaPlayer mediaPlayer) {
                        this.a.i();
                        WindowUtil.a((Activity) this.a.a.get());
                        if (this.a.g && !this.a.c.getError()) {
                            MSPVAListenerManager.a("type=video_start&status=ok");
                            this.a.g = false;
                        }
                        this.a.d.a(8);
                    }
                });
                this.c.setOnCompletionListener(new OnCompletionListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (this.a.c.getPlayCount() == 0 && !this.a.c.getError()) {
                            MSPVAListenerManager.a("type=video_end&status=ok");
                            String j = MSPVAVast.a().j("IncentiveId");
                            j = "type=incentive&id=" + j + "&amount=" + MSPVAVast.a().j("IncentiveAmount") + "&status=ok";
                            MSParameterSupport.a("incentive", j);
                            MSPVAListenerManager.a(j);
                        }
                        this.a.d.a(0);
                        this.a.f = false;
                        this.a.c.invalidate();
                        this.a.b.invalidate();
                        this.a.h.a(4);
                        this.a.p();
                        if (this.a.m != null) {
                            this.a.m.a();
                        }
                        this.a.j.c(this.a.c.getPlayCount() + 1);
                    }
                });
                this.c.setOnErrorListener(new OnErrorListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        this.a.i();
                        MSPVAListenerManager.a("type=video_play&status=ng");
                        this.a.f = false;
                        return false;
                    }
                });
                this.c.setVideoUrlString(str);
                if (this.d == null) {
                    this.d = new MSPVAEndCard((Activity) this.a.get(), this.c);
                }
                this.d.a(8);
                String a = MSPVACloseButtonPosition.a();
                LayoutParams a2 = MSPVAVideoCloseButtonPosition.a(a, (Context) this.a.get());
                if (MSPVAType.b()) {
                    this.h = new MSPVACloseButton((Context) this.a.get(), this.b, a, a2);
                } else {
                    this.h = new MSPVACloseButton((Context) this.a.get(), this.c, a, a2);
                }
                this.h.a(new OnCloseListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a();
                    }
                });
                a = MSPVACloseButtonPosition.b();
                a2 = MSPVAVideoCloseButtonPosition.a(a, (Context) this.a.get());
                if (MSPVAType.b()) {
                    this.i = new MSPVACloseButton((Context) this.a.get(), this.b, a, a2);
                } else {
                    this.i = new MSPVACloseButton((Context) this.a.get(), this.c, a, a2);
                }
                this.i.a(new OnCloseListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.l();
                    }
                });
                this.i.a(4);
                o();
                if (MSPVAType.a() || MSPVAType.c() || MSPVAType.d()) {
                    this.j = new MSPVAVideoTimeDisplay((Context) this.a.get(), this.c);
                } else if (MSPVAType.b()) {
                    this.j = new MSPVAVideoTimeDisplay((Context) this.a.get(), this.b);
                }
                this.c.setCurrentVideoTimeListener(new OnCurrentVideoTimeListener(this) {
                    final /* synthetic */ MSPVAActivityVideo a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        WindowUtil.a((Activity) this.a.a.get());
                        this.a.j.a(i);
                        this.a.i();
                    }
                });
                h();
            } catch (Exception e) {
            }
        }
    }

    public void a() {
        if (!StringUtilEqualsSupport.a("1", MSPVAVast.a().j("CloseButtonConfirm"))) {
            l();
        } else if (this.c.getPlayCount() == 0) {
            k();
        } else {
            l();
        }
    }

    private void k() {
        new Builder((Context) this.a.get()).setMessage("\u8996\u8074\u5b8c\u4e86\u524d\u306b\u9589\u3058\u308b\u3068\n\u5831\u916c\u304c\u7372\u5f97\u3067\u304d\u307e\u305b\u3093\u3002").setPositiveButton("OK", new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityVideo a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.l();
            }
        }).setNegativeButton("Cancel", new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityVideo a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }

    private void l() {
        this.c.c();
        i();
        this.f = false;
        ((Activity) this.a.get()).finish();
    }

    private LayoutParams m() {
        Point a = Util.a((Context) this.a.get());
        if (MSPVAOrientation.b()) {
            return new LayoutParams(-1, -1);
        }
        int i;
        if (MSPVAType.c() || MSPVAType.d()) {
            this.k = Util.b(a.x);
            this.l = a.x;
            i = 0;
        } else if (!MSPVAType.b()) {
            i = 0;
        } else if (StringUtilEqualsSupport.a(MSPVAVast.a().j("TateFull"), "1")) {
            return new LayoutParams(-1, -1);
        } else {
            this.k = Util.b(a.x);
            this.l = a.x;
            i = (int) (((float) (a.y - Util.b(a.x))) / 2.0f);
        }
        this.l--;
        LayoutParams layoutParams = new LayoutParams(this.l, this.k);
        layoutParams.setMargins(0, i, 0, i);
        return layoutParams;
    }

    private void n() {
        this.c.setLayoutParams(m());
        this.c.setVisibility(4);
        this.c.setVisibility(0);
        this.c.requestLayout();
        this.c.invalidate();
    }

    public void b() {
        if (this.c != null) {
            this.c.setCurrentVideoTimeListener(null);
            this.c.d();
            ((ViewGroup) this.c.getParent()).removeView(this.c);
            this.c = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        if (this.e != null) {
            this.e.setVisibility(8);
            this.e = null;
        }
        this.b = null;
        this.a = null;
    }

    public String c() {
        return this.c.getVideoUrlString();
    }

    public boolean d() {
        if (this.c == null) {
            return false;
        }
        return this.c.getVideoPlaying();
    }

    public int e() {
        if (this.c != null) {
            return this.c.getPlayCount();
        }
        return 0;
    }

    public int f() {
        return this.c.getCurrentPosition();
    }

    public void a(int i) {
        this.c.a(i);
    }

    public void g() {
        h();
        this.c.a();
    }

    public void h() {
        if (this.e == null) {
            this.e = new ProgressBar((Context) this.a.get());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            if ((MSPVAType.a() || MSPVAType.c() || MSPVAType.d()) && MSPVAOrientation.a()) {
                layoutParams.gravity = 49;
            } else {
                layoutParams.gravity = 17;
            }
            this.e.setLayoutParams(layoutParams);
            this.e.setVisibility(0);
            LayoutUtil.a(this.e, layoutParams);
            if ((MSPVAType.a() || MSPVAType.c() || MSPVAType.d()) && MSPVAOrientation.a()) {
                LayoutUtilMarginSupport.c(this.e, (this.k / 2) - (this.e.getMax() / 2));
            }
            LayoutUtil.a(this.b, this.e);
        }
    }

    public void i() {
        if (this.e != null) {
            this.e.setVisibility(8);
            if ((MSPVAType.a() || MSPVAType.c() || MSPVAType.d()) && MSPVAOrientation.a()) {
                this.c.removeView(this.e);
            } else {
                LayoutUtil.a(this.e);
            }
            this.e = null;
        }
    }

    public void j() {
        n();
        if (this.d != null) {
            if (MSPVAOrientation.a()) {
                this.d.c(8);
            } else {
                this.d.b(40);
                this.d.c(0);
            }
        }
        if (this.c.getPlayCount() == 0) {
            o();
        } else {
            p();
        }
        if (this.c != null) {
            this.c.e();
            this.j.b(this.c.getPlayCount());
        }
    }

    private void o() {
        if (StringUtilEqualsSupport.a("0", MSPVAVast.a().j("CloseButtonShowPlaying"))) {
            this.h.a(4);
            return;
        }
        String a = MSPVACloseButtonPosition.a();
        if (!StringUtilEqualsSupport.a("lower_right", a) && !StringUtilEqualsSupport.a("lower_left", a)) {
            this.h.a(0);
        } else if (MSPVAType.b()) {
            this.h.a(0);
        } else if (MSPVAOrientation.a()) {
            this.h.a(4);
        } else {
            this.h.a(0);
        }
    }

    private void p() {
        String b = MSPVACloseButtonPosition.b();
        if (!MSPVAType.c()) {
            this.i.a(4);
        } else if (!MSPVAOrientation.a()) {
            this.i.a(0);
        } else if (StringUtilEqualsSupport.a("lower_right", b) || StringUtilEqualsSupport.a("lower_left", b)) {
            this.i.a(4);
        } else {
            this.i.a(0);
        }
        this.i.b();
    }
}
