package jp.co.mediasdk.mscore.ui.pva;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.adunit.AdUnitActivity;
import jp.co.mediasdk.a;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListenerManager;

public class MSPVAActivity extends Activity implements MSPVAVideoListener {
    private static String a = "VIDEO_PLAYING";
    private static String b = TapjoyConstants.EXTRA_VIDEO_URL;
    private static String c = "VIDEO_CURRENT_POSITION";
    private FrameLayout d;
    private MSPVAActivityVideo e;
    private MSPVAActivityDetail f;
    private MSPVAActivityWeb g;
    private MSPVAActivityImage h;
    private MSPVAActivityGoToDetailButton i;
    private MSPVAActivityGoToVideoButton j;
    private int k = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String j = MSPVAVast.a().j("Orientation");
        if (StringUtilEqualsSupport.a(j, String.LANDSCAPE)) {
            setRequestedOrientation(0);
        } else if (StringUtilEqualsSupport.a(j, "portrait")) {
            setRequestedOrientation(1);
        }
        if (StringUtilEmptySupport.c(MSPVAVast.a().j("MediaFile"))) {
            finish();
        }
        requestWindowFeature(1);
        this.d = new FrameLayout(this);
        this.d.setBackgroundColor(-16777216);
        setContentView(this.d);
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == 2) {
            a.a(AdUnitActivity.EXTRA_ORIENTATION, String.LANDSCAPE);
        } else if (configuration.orientation == 1) {
            a.a(AdUnitActivity.EXTRA_ORIENTATION, "portrait");
        }
        this.e = new MSPVAActivityVideo(this, this.d, this);
        if (MSPVAType.a()) {
            this.f = new MSPVAActivityDetail(this, this.d);
            if (MSPVAOrientation.b()) {
                this.j = new MSPVAActivityGoToVideoButton(this, this.d, this.f);
                this.i = new MSPVAActivityGoToDetailButton(this, this.d, this.f, this.j);
            }
        }
        this.g = new MSPVAActivityWeb(this, this.d);
        if (MSPVAType.c()) {
            this.j = new MSPVAActivityGoToVideoButton(this, this.d, this.g);
            this.i = new MSPVAActivityGoToDetailButton(this, this.d, this.g, this.j);
            if (MSPVAOrientation.a()) {
                this.j.a(4);
                this.i.a(4);
            }
        } else if (MSPVAType.b()) {
            this.g.a(4);
        }
        if (this.g != null) {
            if (MSPVAOrientation.b()) {
                this.g.a(4);
            }
            this.g.b(0);
        }
        if (bundle != null && bundle.getBoolean(a)) {
            this.e.a(MSPVAVast.a().j("MediaFile"));
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.e == null) {
            return super.onKeyDown(i, keyEvent);
        }
        this.e.a();
        return false;
    }

    public void a() {
        if (this.g != null) {
            this.g.d();
            this.g.e();
            this.g.b(1);
            if (MSPVAType.b()) {
                this.g.a(0);
            } else if (MSPVAType.d()) {
                this.g.a(0);
            }
        }
        if (this.i != null) {
            this.i.b();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation == 2) {
            a.a(AdUnitActivity.EXTRA_ORIENTATION, String.LANDSCAPE);
            if (this.i != null) {
                this.i.a(0);
            }
        } else if (configuration.orientation == 1) {
            a.a(AdUnitActivity.EXTRA_ORIENTATION, "portrait");
            if (this.j != null) {
                this.j.a(4);
            }
            if (this.i != null) {
                this.i.a(4);
            }
        }
        if (this.e != null) {
            this.e.j();
        }
        if (this.g != null) {
            if (this.e.e() == 0) {
                if (MSPVAType.b()) {
                    this.g.a(4);
                } else if (MSPVAType.c()) {
                    if (MSPVAOrientation.a()) {
                        this.g.a(0);
                    } else {
                        this.g.a(4);
                    }
                } else if (MSPVAType.d()) {
                    if (MSPVAOrientation.a()) {
                        this.g.a(0);
                    } else {
                        this.g.a(4);
                    }
                }
                this.g.b(0);
            } else {
                if (MSPVAType.b()) {
                    this.g.a(0);
                } else if (MSPVAType.c()) {
                    if (MSPVAOrientation.a()) {
                        this.g.a(0);
                    } else {
                        this.g.a(4);
                    }
                } else if (MSPVAType.d()) {
                    if (MSPVAOrientation.a()) {
                        this.g.a(0);
                    } else {
                        this.g.a(0);
                    }
                }
                this.g.b(1);
            }
        }
        super.onConfigurationChanged(configuration);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        boolean z = false;
        if (this.e != null) {
            z = this.e.d();
            bundle.putString(b, this.e.c());
            bundle.putInt(c, this.e.f());
            this.k = this.e.f();
        }
        bundle.putBoolean(a, z);
    }

    public void onPause() {
        finish();
        super.onPause();
    }

    public void onResume() {
        if (this.e != null && this.e.d()) {
            this.e.a(this.k);
            this.e.g();
        }
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        MSPVAListenerManager.a("type=close&status=ok");
        MSPVAListenerManager.a();
        b();
    }

    public void b() {
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        this.i = null;
        this.j = null;
        this.d = null;
        MSPVAVast.h();
    }
}
