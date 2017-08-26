package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.tapjoy.TapjoyConstants;
import java.lang.ref.WeakReference;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.android.DateUtil;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.LayoutUtil;
import jp.co.mediasdk.android.LayoutUtilMarginSupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.common.MSWebView;
import jp.co.mediasdk.mscore.ui.pva.MSPVACloseButton.OnCloseListener;

public class MSPVAActivityWeb implements MSPVALandscapeComponent {
    private WeakReference<Activity> a = null;
    private MSWebView b = null;
    private ProgressBar c = null;
    private FrameLayout d = null;
    private int e = 4;
    private MSPVAWebViewClient f = null;
    private MSPVACloseButton g;
    private MSPVACloseButton h;

    public Activity a() {
        return (Activity) this.a.get();
    }

    public MSPVAActivityWeb(Activity activity, FrameLayout frameLayout) {
        this.a = new WeakReference(activity);
        if (this.b == null) {
            this.b = new MSWebView(activity);
            this.f = new MSPVAWebViewClient(this);
            this.b.setWebViewClient(this.f);
        }
        if (MSPVAOrientation.b()) {
            a(4);
        }
        if (LayoutUtil.c(frameLayout, this.b)) {
            this.b.loadUrl(c());
        } else {
            this.b.a(c());
            LayoutUtil.a(this.b, LayoutUtil.a());
            LayoutUtil.a((ViewGroup) frameLayout, this.b);
            if (MSPVAType.c() || MSPVAType.d()) {
                LayoutUtilMarginSupport.c(this.b, l());
            } else {
                this.b.setVisibility(0);
            }
        }
        String a = MSPVACloseButtonPosition.a();
        if (StringUtilEqualsSupport.a("lower_right", a) || StringUtilEqualsSupport.a("lower_left", a)) {
            this.g = new MSPVACloseButton((Context) this.a.get(), frameLayout, a, MSPVAWebViewCloseButtonPosition.a(a, (Context) this.a.get(), false));
            this.g.a(new OnCloseListener(this) {
                final /* synthetic */ MSPVAActivityWeb a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.b();
                }
            });
            if (StringUtilEqualsSupport.a("0", MSPVAVast.a().j("CloseButtonShowPlaying"))) {
                this.g.a(4);
            } else {
                this.g.a(0);
            }
        }
        a = MSPVACloseButtonPosition.b();
        if (i()) {
            this.h = new MSPVACloseButton((Context) this.a.get(), frameLayout, a, MSPVAWebViewCloseButtonPosition.a(a, (Context) this.a.get(), true));
            this.h.a(new OnCloseListener(this) {
                final /* synthetic */ MSPVAActivityWeb a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.k();
                }
            });
            this.h.a(4);
        }
        this.d = frameLayout;
    }

    private boolean i() {
        String b = MSPVACloseButtonPosition.b();
        if (StringUtilEqualsSupport.a("upper_right", b) || StringUtilEqualsSupport.a("upper_left", b) || StringUtilEqualsSupport.a("lower_right", b) || StringUtilEqualsSupport.a("lower_left", b)) {
            return true;
        }
        if (MSPVAType.b()) {
            if (StringUtilEqualsSupport.a("center_right", b) || StringUtilEqualsSupport.a("center_left", b)) {
                return true;
            }
        } else if (MSPVAType.d()) {
            if (StringUtilEqualsSupport.a("center_right", b)) {
                return true;
            }
            if (StringUtilEqualsSupport.a("center_left", b)) {
                return true;
            }
        }
        return false;
    }

    public void b() {
        if (StringUtilEqualsSupport.a("1", MSPVAVast.a().j("CloseButtonConfirm"))) {
            j();
        } else {
            k();
        }
    }

    private void j() {
        new Builder((Context) this.a.get()).setMessage("\u8996\u8074\u5b8c\u4e86\u524d\u306b\u9589\u3058\u308b\u3068\n\u5831\u916c\u304c\u7372\u5f97\u3067\u304d\u307e\u305b\u3093\u3002").setPositiveButton("OK", new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityWeb a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.k();
            }
        }).setNegativeButton("Cancel", new OnClickListener(this) {
            final /* synthetic */ MSPVAActivityWeb a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }

    private void k() {
        ((Activity) this.a.get()).finish();
    }

    public static String c() {
        String str = "https://player.gratefulvideo.jp/info.html?";
        if (StringUtilEqualsSupport.a("1", MSParameterSupport.a("Debug"))) {
            str = "https://player-pre.gratefulvideo.jp/info.html?";
        }
        HashMapEX hashMapEX = new HashMapEX();
        hashMapEX.a(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME, MSParameterSupport.a(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME));
        hashMapEX.a("c_id", MSPVAVast.a().j("Cid"));
        hashMapEX.a("media_user_id", MSParameterSupport.a("media_user_id"));
        hashMapEX.a("placement", MSParameterSupport.a("placement"));
        MSParameterSupport.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
        hashMapEX.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, MSParameterSupport.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT));
        String valueOf = String.valueOf(DateUtil.a());
        hashMapEX.a(TapjoyConstants.TJC_TIMESTAMP, valueOf);
        hashMapEX.a(TapjoyConstants.TJC_VERIFIER, MSPVAParamater.a(MSPVAParamater.a(), valueOf));
        hashMapEX.a("curation_format_id", MSPVAVast.a().j("CurationFormat"));
        hashMapEX.a(TapjoyConstants.TJC_SESSION_ID, MSPVAVast.a().j("SessionId"));
        return str + hashMapEX.e();
    }

    private int l() {
        Point a = Util.a((Context) this.a.get());
        if (MSPVAOrientation.b()) {
            return a.y;
        }
        if (MSPVAType.c() || MSPVAType.d()) {
            return Util.b(a.x);
        }
        if (MSPVAType.b()) {
            return a.y;
        }
        return 0;
    }

    public void a(int i) {
        this.e = i;
        this.b.setVisibility(i);
        if (this.c != null) {
            this.c.setVisibility(i);
        }
    }

    public void b(int i) {
        if (i == 0) {
            if (MSPVAType.c()) {
                if (MSPVAOrientation.b()) {
                    LayoutUtilMarginSupport.c(this.b, 80);
                    if (this.g != null) {
                        this.g.a(4);
                        return;
                    }
                    return;
                }
                LayoutUtilMarginSupport.c(this.b, l());
                if (StringUtilEqualsSupport.a("0", MSPVAVast.a().j("CloseButtonShowPlaying"))) {
                    if (this.g != null) {
                        this.g.a(4);
                    }
                } else if (this.g != null) {
                    this.g.a(0);
                }
            } else if (MSPVAType.d() && MSPVAOrientation.a()) {
                LayoutUtilMarginSupport.c(this.b, l());
            }
        } else if (MSPVAType.b()) {
            LayoutUtilMarginSupport.c(this.b, 0);
        } else if (MSPVAType.c()) {
            if (MSPVAOrientation.b()) {
                LayoutUtilMarginSupport.c(this.b, 80);
                if (this.h != null) {
                    this.h.a(4);
                    return;
                }
                return;
            }
            LayoutUtilMarginSupport.c(this.b, l());
            if (this.h != null) {
                this.h.a(0);
            }
        } else if (MSPVAType.d()) {
            LayoutUtilMarginSupport.c(this.b, 0);
        }
    }

    public void d() {
        this.b.loadUrl("javascript:completeVideo()");
    }

    public void e() {
        if (this.g != null) {
            this.g.a(4);
        }
        if (this.h != null) {
            this.h.a(0);
        }
    }

    public void f() {
        if (this.b != null) {
            this.b.clearCache(true);
            this.b.setWebChromeClient(null);
            this.b.setWebViewClient(null);
            if (this.b.getParent() != null) {
                ((ViewGroup) this.b.getParent()).removeView(this.b);
            }
            this.b.removeAllViews();
            this.b.destroy();
            this.b = null;
        }
        if (this.f != null) {
            this.f.a();
        }
        if (this.c != null) {
            this.c.setVisibility(8);
            this.c = null;
        }
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        this.a = null;
        this.d = null;
    }

    public void g() {
        if (this.c == null) {
            this.c = new ProgressBar((Context) this.a.get());
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.c.setLayoutParams(layoutParams);
            this.c.setVisibility(0);
            LayoutUtil.a(this.c, layoutParams);
            LayoutUtil.a(this.d, this.c);
            if (MSPVAOrientation.b() && this.e == 4) {
                this.c.setVisibility(4);
            }
        }
    }

    public void h() {
        if (this.c != null) {
            this.c.setVisibility(8);
            LayoutUtil.a(this.c);
            this.c = null;
        }
    }
}
