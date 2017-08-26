package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.fa;
import com.tapjoy.internal.fm;
import java.util.Timer;
import java.util.TimerTask;

public class TJAdUnitActivity extends Activity implements OnClickListener {
    private static TJAdUnitActivity a;
    private TJAdUnit b;
    private TJPlacementData c;
    private a d;
    private TJAdUnitSaveStateData e = new TJAdUnitSaveStateData();
    private RelativeLayout f = null;
    private TJCloseButton g;
    private ProgressBar h;
    private boolean i = false;

    class a extends BroadcastReceiver {
        final /* synthetic */ TJAdUnitActivity a;

        private a(TJAdUnitActivity tJAdUnitActivity) {
            this.a = tJAdUnitActivity;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("noConnectivity", false)) {
                TapjoyLog.i("TJAdUnitActivity", "Network connectivity lost during ad unit activity");
                this.a.showErrorDialog();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        TapjoyLog.d("TJAdUnitActivity", "TJAdUnitActivity onCreate: " + bundle);
        super.onCreate(bundle);
        a = this;
        if (bundle != null) {
            this.e = (TJAdUnitSaveStateData) bundle.getSerializable("ad_unit_bundle");
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) == null) {
            TapjoyLog.e("TJAdUnitActivity", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Failed to launch AdUnit Activity"));
            finish();
            return;
        }
        this.c = (TJPlacementData) extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
        if (TJPlacementManager.get(this.c.getGuid()) != null) {
            this.b = TJPlacementManager.get(this.c.getGuid()).a;
            this.b.setContext(this);
        } else {
            this.b = new TJAdUnit(this);
        }
        if (!this.b.isAdUnitConstructed()) {
            TapjoyLog.d("TJAdUnitActivity", "No content loaded for ad unit -- loading now");
            this.b.load(this.c);
        }
        if (VERSION.SDK_INT < 11) {
            setTheme(16973829);
        } else {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (!TapjoyConnectCore.isUnitTestMode()) {
            this.d = new a();
            registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f = new RelativeLayout(this);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackgroundColor(0);
        View backgroundWebView = this.b.getBackgroundWebView();
        backgroundWebView.setLayoutParams(layoutParams);
        if (backgroundWebView.getParent() != null) {
            ((ViewGroup) backgroundWebView.getParent()).removeView(backgroundWebView);
        }
        View webView = this.b.getWebView();
        webView.setLayoutParams(layoutParams);
        if (webView.getParent() != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        View videoView = this.b.getVideoView();
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        videoView.setLayoutParams(layoutParams2);
        if (videoView.getParent() != null) {
            ((ViewGroup) videoView.getParent()).removeView(videoView);
        }
        this.f.addView(backgroundWebView);
        this.f.addView(videoView);
        this.f.addView(webView);
        this.h = new ProgressBar(this, null, 16842874);
        if (this.c.hasProgressSpinner()) {
            setProgressSpinnerVisibility(true);
        } else {
            setProgressSpinnerVisibility(false);
        }
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.h.setLayoutParams(layoutParams2);
        this.f.addView(this.h);
        if (!this.b.getWebView().isMraid()) {
            this.g = new TJCloseButton(this);
            this.g.setOnClickListener(this);
            this.f.addView(this.g);
        }
        setContentView(this.f);
        this.b.setVisible(true);
        TJPlacement tJPlacement = TJPlacementManager.get(this.c.getGuid());
        if (tJPlacement != null && tJPlacement.c != null) {
            tJPlacement.c.onContentShow(tJPlacement);
        }
    }

    public void setCloseButtonVisibility(boolean z) {
        if (z) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(4);
        }
    }

    public void setProgressSpinnerVisibility(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(4);
        }
    }

    public void onBackPressed() {
        handleClose();
    }

    public void handleClose() {
        handleClose(false);
    }

    public void handleClose(boolean z) {
        if (!this.b.getCloseRequested()) {
            TapjoyLog.d("TJAdUnitActivity", String.CLOSE_REQUESTED);
            this.b.closeRequested(z);
            new Timer().schedule(new TimerTask(this) {
                final /* synthetic */ TJAdUnitActivity a;

                {
                    this.a = r1;
                }

                public final void run() {
                    if (this.a.b.getCloseRequested()) {
                        TapjoyLog.d("TJAdUnitActivity", "Did not receive callback from content. Closing ad.");
                        this.a.finish();
                    }
                }
            }, 1000);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        a = null;
        TapjoyLog.d("TJAdUnitActivity", "onDestroy");
        if (this.b != null) {
            this.b.destroy();
        }
        if (!(this.d == null || TapjoyConnectCore.isUnitTestMode())) {
            unregisterReceiver(this.d);
        }
        if (this.c.isBaseActivity()) {
            TapjoyConnectCore.viewDidClose(this.c.getGuid());
            TJPlacement tJPlacement = TJPlacementManager.get(this.c.getGuid());
            if (tJPlacement != null && tJPlacement.c != null) {
                tJPlacement.c.onContentDismiss(tJPlacement);
            }
        }
    }

    protected void onResume() {
        TapjoyLog.d("TJAdUnitActivity", "onResume");
        super.onResume();
        if (this.b.isLockedOrientation()) {
            setRequestedOrientation(this.b.getOrientation());
        }
        this.b.resume(this.e);
    }

    protected void onStart() {
        super.onStart();
        if (fm.a().n) {
            this.i = true;
            fa.a((Activity) this);
        }
        if (!this.c.isBaseActivity()) {
            setResult(-1, getIntent());
        }
    }

    protected void onPause() {
        super.onPause();
        this.b.pauseVideo();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TapjoyLog.d("TJAdUnitActivity", "onSaveInstanceState");
        this.e.seekTime = this.b.getVideoSeekTime();
        bundle.putSerializable("ad_unit_bundle", this.e);
    }

    protected void onStop() {
        if (this.i) {
            this.i = false;
            fa.b(this);
        }
        super.onStop();
    }

    public void showErrorDialog() {
        if (!isFinishing()) {
            new Builder(this).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new DialogInterface.OnClickListener(this) {
                final /* synthetic */ TJAdUnitActivity a;

                {
                    this.a = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.a.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        }
    }

    public void onClick(View view) {
        handleClose();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == TJAdUnitConstants.MRAID_REQUEST_CODE && intent != null && intent.hasExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) && this.b != null) {
            TJPlacementData tJPlacementData = (TJPlacementData) intent.getSerializableExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
            this.b.invokeBridgeCallback(tJPlacementData.getCallbackID(), Boolean.TRUE);
        }
    }

    static void a() {
        TJAdUnitActivity tJAdUnitActivity = a;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.handleClose(true);
        }
    }
}
