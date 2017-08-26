package jp.co.mediasdk.mscore.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import jp.co.mediasdk.android.WindowUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.common.MSWebView;
import jp.co.mediasdk.mscore.ui.hybrid.MSJavaScriptInterface;
import jp.co.mediasdk.mscore.ui.hybrid.MSNavigationBarView;
import jp.co.mediasdk.mscore.ui.hybrid.MSVideoView;
import jp.co.mediasdk.mscore.ui.hybrid.MSVideoView.OnCloseListener;
import jp.co.mediasdk.mscore.ui.hybrid.MSWebViewClient;

public class MSWebViewActivity extends Activity implements OnClickListener {
    private static String a = "VIDEO_PLAYING";
    private static String b = TapjoyConstants.EXTRA_VIDEO_URL;
    private static String c = "VIDEO_CURRENT_POSITION";
    private MSWebView d = null;
    private MSVideoView e = null;
    private ProgressDialog f = null;
    private boolean g = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        setContentView(linearLayout);
        View mSNavigationBarView = new MSNavigationBarView(this);
        mSNavigationBarView.setOnBackButtonClickListner(this);
        linearLayout.addView(mSNavigationBarView);
        this.d = new MSWebView(this);
        this.d.addJavascriptInterface(new MSJavaScriptInterface(this), "mediaNative");
        this.d.setWebViewClient(new MSWebViewClient(this));
        this.d.a(getIntent().getStringExtra(String.URL));
        linearLayout.addView(this.d, -1, -1);
        if (bundle != null && bundle.getBoolean(a)) {
            a(bundle.getString(b));
        }
    }

    public void onClick(View view) {
        finish();
    }

    public void onPause() {
        super.onPause();
    }

    public void onRestart() {
        super.onRestart();
    }

    public void onDestroy() {
        if (this.d != null) {
            this.d.setWebChromeClient(null);
            this.d.setWebViewClient(null);
            if (this.d.getParent() != null) {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
            }
            this.d.removeAllViews();
            this.d.destroy();
            this.d = null;
        }
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.d.saveState(bundle);
        boolean z = false;
        if (this.e != null) {
            z = this.e.getVideoPlaying();
            bundle.putString(b, this.e.getVideoUrlString());
            bundle.putInt(c, this.e.getCurrentPosition());
        }
        bundle.putBoolean(a, z);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.f != null) {
                this.f.dismiss();
            }
            if (this.g) {
                return false;
            }
            if (this.d.canGoBack()) {
                this.d.goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @SuppressLint({"NewApi"})
    public void a(String str) {
        if (!this.g) {
            this.g = true;
            try {
                this.f = new ProgressDialog(this);
                this.f.setProgressStyle(0);
                CharSequence charSequence = "\u8aad\u307f\u8fbc\u307f\u4e2d";
                if (MSParameterSupport.b(MSParameterSupport.n)) {
                    charSequence = MSParameterSupport.a(MSParameterSupport.n);
                }
                this.f.setMessage(charSequence);
                this.f.setCancelable(false);
                this.f.show();
                this.e = new MSVideoView(this);
                this.e.requestFocus();
                WindowUtil.a(this);
                addContentView(this.e, new LayoutParams(-1, -1));
                this.e.setOnPreparedListener(new OnPreparedListener(this) {
                    final /* synthetic */ MSWebViewActivity a;

                    {
                        this.a = r1;
                    }

                    public void onPrepared(MediaPlayer mediaPlayer) {
                        if (this.a.f != null) {
                            this.a.f.dismiss();
                        }
                        this.a.d.loadUrl("javascript:moviePlay()");
                    }
                });
                this.e.setOnCompletionListener(new OnCompletionListener(this) {
                    final /* synthetic */ MSWebViewActivity a;

                    {
                        this.a = r1;
                    }

                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ((ViewGroup) this.a.e.getParent()).removeView(this.a.e);
                        this.a.d.loadUrl("javascript:movieFinish()");
                        this.a.g = false;
                        WindowUtil.b(this.a);
                        this.a.e = null;
                    }
                });
                this.e.setOnCloseListener(new OnCloseListener(this) {
                    final /* synthetic */ MSWebViewActivity a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        if (this.a.f != null) {
                            this.a.f.dismiss();
                        }
                        this.a.g = false;
                        WindowUtil.b(this.a);
                        this.a.e = null;
                    }
                });
                this.e.setOnErrorListener(new OnErrorListener(this) {
                    final /* synthetic */ MSWebViewActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        if (this.a.f != null) {
                            this.a.f.dismiss();
                        }
                        this.a.g = false;
                        return false;
                    }
                });
                this.e.setVideoUrlString(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
