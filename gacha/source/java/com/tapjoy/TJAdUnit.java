package com.tapjoy;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.cr;
import com.tapjoy.internal.ga;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.view.BasicWebView;
import com.tapjoy.mraid.view.MraidView;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import jp.co.vaz.creator.hikaru.InAppPurchase.util.IabHelper;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.MediaEntity.Size;
import twitter4j.TwitterResponse;

public class TJAdUnit implements OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener {
    public static TJVideoListener publisherVideoListener;
    private TJAdUnitWebViewListener a;
    private TJAdUnitVideoListener b;
    private Context c;
    private TJAdUnitActivity d;
    private TJAdUnitJSBridge e;
    private BasicWebView f;
    private MraidView g;
    private VideoView h;
    private int i;
    private boolean j;
    private boolean k;
    private Timer l = new Timer();
    private ScheduledFuture m;
    private AudioManager n;
    private int o = 0;
    private int p;
    private Runnable q;
    private boolean r;
    private boolean s;
    private volatile boolean t;
    private boolean u;
    private int v = -1;
    private final Runnable w = new Runnable(this) {
        final /* synthetic */ TJAdUnit a;

        {
            this.a = r1;
        }

        public final void run() {
            int streamVolume = this.a.n.getStreamVolume(3);
            if (this.a.o != streamVolume) {
                this.a.o = streamVolume;
                this.a.e.onVolumeChanged();
            }
        }
    };
    private Handler x;

    public interface TJAdUnitVideoListener {
        void onVideoCompleted();

        void onVideoError(String str);

        void onVideoStart();
    }

    public interface TJAdUnitWebViewListener {
        void onClosed();

        void onContentReady();
    }

    class a implements MraidViewListener {
        final /* synthetic */ TJAdUnit a;

        private a(TJAdUnit tJAdUnit) {
            this.a = tJAdUnit;
        }

        public final boolean onResizeClose() {
            return false;
        }

        public final boolean onResize() {
            return false;
        }

        public final boolean onReady() {
            return false;
        }

        public final boolean onExpandClose() {
            return false;
        }

        public final boolean onExpand() {
            return false;
        }

        public final boolean onEventFired() {
            return false;
        }

        public final boolean onClose() {
            if (this.a.d != null) {
                this.a.d.handleClose();
            }
            return false;
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            int i = 0;
            if (this.a.e.closeRequested) {
                String[] strArr = new String[]{"Uncaught", "uncaught", "Error", String.VIDEO_ERROR, "not defined"};
                TapjoyLog.d("TJAdUnit", "shouldClose...");
                if (this.a.d != null) {
                    while (i < 5) {
                        if (consoleMessage.message().contains(strArr[i])) {
                            this.a.d.handleClose();
                            break;
                        }
                        i++;
                    }
                }
            }
            return true;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.a.d != null) {
                this.a.d.showErrorDialog();
            }
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            TapjoyLog.d("TJAdUnit", "onPageStarted: " + str);
            if (this.a.e != null) {
                this.a.e.allowRedirect = true;
                this.a.e.customClose = false;
                this.a.e.closeRequested = false;
                this.a.a();
            }
        }

        public final void onPageFinished(WebView webView, String str) {
            TapjoyLog.d("TJAdUnit", "onPageFinished: " + str);
            if (!(this.a == null || this.a.g == null || !this.a.g.isMraid())) {
                this.a.e.allowRedirect = false;
            }
            if (this.a.d != null) {
                this.a.d.setProgressSpinnerVisibility(false);
            }
            this.a.u = true;
            if (this.a.s) {
                this.a.e.display();
            }
            this.a.e.flushMessageQueue();
        }

        @TargetApi(9)
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (a()) {
                boolean z;
                TapjoyLog.d("TJAdUnit", "interceptURL: " + str);
                if (this.a == null || this.a.g == null || !this.a.g.isMraid() || !str.contains("mraid")) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    return false;
                }
                if (a(str)) {
                    TapjoyLog.d("TJAdUnit", "Open redirecting URL:" + str);
                    ((MraidView) webView).loadUrlStandard(str);
                    return true;
                } else if (this.a.e.allowRedirect) {
                    return false;
                } else {
                    webView.loadUrl(str);
                    return true;
                }
            } else if (this.a.d == null) {
                return true;
            } else {
                this.a.d.showErrorDialog();
                return true;
            }
        }

        private static boolean a(String str) {
            try {
                CharSequence host = new URL(TapjoyConfig.TJC_SERVICE_URL).getHost();
                if ((host != null && str.contains(host)) || str.contains(TapjoyConnectCore.getRedirectDomain()) || str.contains(TapjoyUtil.getRedirectDomain(TapjoyConnectCore.getPlacementURL()))) {
                    return true;
                }
                return false;
            } catch (MalformedURLException e) {
                return false;
            }
        }

        private boolean a() {
            try {
                NetworkInfo activeNetworkInfo = this.a.g.getConnectivityManager().getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }

    static /* synthetic */ void m(TJAdUnit tJAdUnit) {
        tJAdUnit.l.cancel();
        tJAdUnit.l = new Timer();
        tJAdUnit.l.schedule(new TimerTask(tJAdUnit) {
            final /* synthetic */ TJAdUnit a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.e.onVideoProgress(this.a.h.getCurrentPosition());
            }
        }, 500, 500);
    }

    public TJAdUnit(Context context) {
        setContext(context);
    }

    public boolean preload(TJPlacementData tJPlacementData) {
        if (this.q == null && tJPlacementData.isPrerenderingRequested() && TJPlacementManager.canPreRenderPlacement()) {
            TapjoyLog.i("TJAdUnit", "Pre-rendering ad unit for placement: " + tJPlacementData.getPlacementName());
            TJPlacementManager.incrementPlacementPreRenderCount();
            load(tJPlacementData);
            return true;
        }
        fireContentReady();
        return false;
    }

    public void load(final TJPlacementData tJPlacementData) {
        if (!this.t) {
            this.q = new Runnable(this) {
                final /* synthetic */ TJAdUnit b;

                public final void run() {
                    if (!this.b.t) {
                        this.b.t = true;
                        this.b.f = new BasicWebView(this.b.c);
                        this.b.f.loadDataWithBaseURL(null, "<!DOCTYPE html><html><head><title>Tapjoy Background Webview</title></head></html>", "text/html", "utf-8", null);
                        this.b.g = new MraidView(this.b.c);
                        this.b.g.setListener(new a());
                        this.b.h = new VideoView(this.b.c);
                        this.b.h.setOnCompletionListener(this.b);
                        this.b.h.setOnErrorListener(this.b);
                        this.b.h.setOnPreparedListener(this.b);
                        this.b.h.setVisibility(4);
                        this.b.e = new TJAdUnitJSBridge(this.b.c, this.b);
                        if (cr.c(tJPlacementData.getRedirectURL())) {
                            if (tJPlacementData.getBaseURL() == null || tJPlacementData.getHttpResponse() == null) {
                                TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error loading ad unit content"));
                            } else {
                                this.b.g.loadDataWithBaseURL(tJPlacementData.getBaseURL(), tJPlacementData.getHttpResponse(), "text/html", "utf-8", null);
                            }
                        } else if (tJPlacementData.isPreloadDisabled()) {
                            this.b.g.postUrl(tJPlacementData.getRedirectURL(), null);
                        } else {
                            this.b.g.loadUrl(tJPlacementData.getRedirectURL());
                        }
                    }
                }
            };
            TapjoyUtil.runOnMainThread(this.q);
        }
    }

    public void resume(TJAdUnitSaveStateData tJAdUnitSaveStateData) {
        if (this.e.didLaunchOtherActivity) {
            TapjoyLog.d("TJAdUnit", "onResume bridge.didLaunchOtherActivity callbackID: " + this.e.otherActivityCallbackID);
            this.e.invokeJSCallback(this.e.otherActivityCallbackID, Boolean.TRUE);
            this.e.didLaunchOtherActivity = false;
        }
        if (tJAdUnitSaveStateData != null) {
            this.i = tJAdUnitSaveStateData.seekTime;
            this.h.seekTo(this.i);
        }
    }

    public void invokeBridgeCallback(String str, Object... objArr) {
        if (this.e != null && str != null) {
            this.e.invokeJSCallback(str, objArr);
        }
    }

    public void destroy() {
        this.e.destroy();
        if (this.f != null) {
            this.f.removeAllViews();
            this.f = null;
        }
        if (this.g != null) {
            this.g.removeAllViews();
            this.g = null;
        }
        this.l.cancel();
        this.u = false;
        this.t = false;
        this.s = false;
        this.c = null;
        this.d = null;
        a();
        if (this.a != null) {
            this.a.onClosed();
        }
    }

    public void setVisible(boolean z) {
        this.s = z;
        if (this.s && this.u) {
            this.e.display();
        }
    }

    public void fireContentReady() {
        if (this.a != null) {
            this.a.onContentReady();
        }
    }

    public void closeRequested(boolean z) {
        if (this.g == null || !this.g.videoPlaying()) {
            this.e.closeRequested(Boolean.valueOf(z));
        } else {
            this.g.videoViewCleanup();
        }
    }

    public void setOrientation(int i) {
        int i2 = 0;
        if (this.d != null) {
            if (this.d != null) {
                int rotation = this.d.getWindowManager().getDefaultDisplay().getRotation();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i3 = displayMetrics.widthPixels;
                int i4 = displayMetrics.heightPixels;
                if (((rotation != 0 && rotation != 2) || i4 <= i3) && ((rotation != 1 && rotation != 3) || i3 <= i4)) {
                    switch (rotation) {
                        case TwitterResponse.NONE /*0*/:
                            break;
                        case TwitterResponse.READ /*1*/:
                            i2 = 1;
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            i2 = 8;
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            i2 = 9;
                            break;
                        default:
                            TapjoyLog.w("TJAdUnit", "Unknown screen orientation. Defaulting to landscape.");
                            break;
                    }
                }
                switch (rotation) {
                    case TwitterResponse.NONE /*0*/:
                        i2 = 1;
                        break;
                    case TwitterResponse.READ /*1*/:
                        break;
                    case TwitterResponse.READ_WRITE /*2*/:
                        i2 = 9;
                        break;
                    case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                        i2 = 8;
                        break;
                    default:
                        i2 = 1;
                        break;
                }
            }
            i2 = -1;
            if (this.v != -1) {
                i2 = this.v;
            }
            if ((a(i2) && a(i)) || (b(i2) && b(i))) {
                i = this.v;
            }
            this.d.setRequestedOrientation(i);
            this.v = i;
            this.r = true;
        }
    }

    private void a() {
        TapjoyLog.d("TJAdUnit", "detachVolumeListener");
        if (this.m != null) {
            this.m.cancel(false);
            this.m = null;
        }
        this.n = null;
    }

    private static boolean a(int i) {
        return i == 0 || i == 8 || i == 6 || i == 11;
    }

    private static boolean b(int i) {
        return i == 1 || i == 9 || i == 7 || i == 12;
    }

    public void setContext(Context context) {
        this.c = context;
        if (this.g != null) {
            this.g.setContext(this.c);
        }
        if (this.e != null) {
            this.e.setContext(this.c);
        }
        if (this.c instanceof TJAdUnitActivity) {
            this.d = (TJAdUnitActivity) this.c;
        }
    }

    public void setBackgroundColor(final String str, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            final /* synthetic */ TJAdUnit c;

            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundColor: " + str);
                    this.c.f.setBackgroundColor(Color.parseColor(str));
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.d("TJAdUnit", "Error setting background color. backgroundWebView: " + this.c.f + ", hexColor: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setBackgroundContent(final String str, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            final /* synthetic */ TJAdUnit c;

            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundContent: " + str);
                    this.c.f.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.d("TJAdUnit", "Error setting background content. backgroundWebView: " + this.c.f + ", content: " + str);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setWebViewListener(TJAdUnitWebViewListener tJAdUnitWebViewListener) {
        this.a = tJAdUnitWebViewListener;
    }

    public void setVideoListener(TJAdUnitVideoListener tJAdUnitVideoListener) {
        this.b = tJAdUnitVideoListener;
    }

    public int getOrientation() {
        return this.v;
    }

    public boolean isAdUnitConstructed() {
        return this.t;
    }

    public boolean isLockedOrientation() {
        return this.r;
    }

    public BasicWebView getBackgroundWebView() {
        return this.f;
    }

    public MraidView getWebView() {
        return this.g;
    }

    public boolean getCloseRequested() {
        return this.e.closeRequested;
    }

    public void loadVideoUrl(final String str, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            final /* synthetic */ TJAdUnit c;

            public final void run() {
                if (this.c.h != null) {
                    TapjoyLog.i("TJAdUnit", "loadVideoUrl: " + str);
                    this.c.h.setVisibility(0);
                    this.c.h.setVideoPath(str);
                    this.c.h.seekTo(0);
                    adUnitAsyncTaskListner.onComplete(true);
                    return;
                }
                adUnitAsyncTaskListner.onComplete(false);
            }
        });
    }

    public boolean playVideo() {
        TapjoyLog.i("TJAdUnit", "playVideo");
        if (this.h == null) {
            return false;
        }
        this.h.start();
        if (this.x == null) {
            this.x = new Handler(Looper.getMainLooper());
        }
        this.x.postDelayed(new Runnable(this) {
            final /* synthetic */ TJAdUnit a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.h.getCurrentPosition() != 0) {
                    if (!this.a.k) {
                        this.a.k = true;
                        this.a.fireOnVideoStart();
                    }
                    this.a.e.onVideoStarted(this.a.i);
                    TJAdUnit.m(this.a);
                    return;
                }
                this.a.x.postDelayed(this, 200);
            }
        }, 200);
        return true;
    }

    public boolean pauseVideo() {
        if (this.h == null || !this.h.isPlaying()) {
            return false;
        }
        this.l.cancel();
        this.h.pause();
        this.i = this.h.getCurrentPosition();
        TapjoyLog.i("TJAdUnit", "Video paused at: " + this.i);
        this.e.onVideoPaused(this.i);
        return true;
    }

    public void clearVideo(final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        if (this.h != null) {
            TapjoyUtil.runOnMainThread(new Runnable(this) {
                final /* synthetic */ TJAdUnit b;

                public final void run() {
                    this.b.l.cancel();
                    this.b.h.setVisibility(4);
                    this.b.h.stopPlayback();
                    this.b.k = false;
                    this.b.i = 0;
                    adUnitAsyncTaskListner.onComplete(true);
                }
            });
        } else {
            adUnitAsyncTaskListner.onComplete(false);
        }
    }

    public void attachVolumeListener(boolean z, int i) {
        TapjoyLog.d("TJAdUnit", "attachVolumeListener: isAttached=" + z + "; interval=" + i);
        a();
        if (z) {
            this.n = (AudioManager) this.c.getSystemService("audio");
            this.o = this.n.getStreamVolume(3);
            this.p = this.n.getStreamMaxVolume(3);
            this.m = ga.a.scheduleWithFixedDelay(this.w, (long) i, (long) i, TimeUnit.MILLISECONDS);
        }
    }

    public VideoView getVideoView() {
        return this.h;
    }

    public int getVideoSeekTime() {
        return this.i;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onPrepared");
        final int duration = this.h.getDuration();
        final int measuredWidth = this.h.getMeasuredWidth();
        final int measuredHeight = this.h.getMeasuredHeight();
        if (this.i <= 0 || this.h.getCurrentPosition() == this.i) {
            this.e.onVideoReady(duration, measuredWidth, measuredHeight);
        } else {
            mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListener(this) {
                final /* synthetic */ TJAdUnit d;

                public final void onSeekComplete(MediaPlayer mediaPlayer) {
                    this.d.e.onVideoReady(duration, measuredWidth, measuredHeight);
                }
            });
        }
        mediaPlayer.setOnInfoListener(this);
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error encountered when instantiating the VideoView: " + i + " - " + i2));
        this.j = true;
        this.l.cancel();
        switch (i) {
            case Size.FIT /*100*/:
                str = "MEDIA_ERROR_SERVER_DIED";
                break;
            default:
                str = "MEDIA_ERROR_UNKNOWN";
                break;
        }
        str = str + " -- ";
        switch (i2) {
            case IabHelper.IABHELPER_INVALID_CONSUMPTION /*-1010*/:
                str = "MEDIA_ERROR_UNSUPPORTED";
                break;
            case IabHelper.IABHELPER_MISSING_TOKEN /*-1007*/:
                str = "MEDIA_ERROR_MALFORMED";
                break;
            case IabHelper.IABHELPER_SEND_INTENT_FAILED /*-1004*/:
                str = str + "MEDIA_ERROR_IO";
                break;
            case -110:
                str = "MEDIA_ERROR_TIMED_OUT";
                break;
            default:
                str = "MEDIA_ERROR_EXTRA_UNKNOWN";
                break;
        }
        this.e.onVideoError(str);
        fireOnVideoError(str);
        if (i == 1 || i2 == IabHelper.IABHELPER_SEND_INTENT_FAILED) {
            return true;
        }
        return false;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onCompletion");
        this.l.cancel();
        if (!this.j) {
            this.e.onVideoCompletion();
            fireOnVideoComplete();
        }
        this.j = false;
    }

    public void fireOnVideoStart() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoStart");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoStart();
        }
        if (this.b != null) {
            this.b.onVideoStart();
        }
    }

    public void fireOnVideoError(String str) {
        TapjoyLog.e("TJAdUnit", "Firing onVideoError with error: " + str);
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoError(3);
        }
        if (this.b != null) {
            this.b.onVideoError(str);
        }
    }

    public void fireOnVideoComplete() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoComplete");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoComplete();
        }
        if (this.b != null) {
            this.b.onVideoCompleted();
        }
    }

    public String getVolume() {
        double d = ((double) this.o) / ((double) this.p);
        return String.format("%.2f", new Object[]{Double.valueOf(d)});
    }

    public boolean isMuted() {
        return this.o == 0;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = BuildConfig.FLAVOR;
        switch (i) {
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                str = "MEDIA_INFO_VIDEO_RENDERING_START";
                break;
            case 700:
                str = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                break;
            case 701:
                str = "MEDIA_INFO_BUFFERING_START";
                break;
            case 702:
                str = "MEDIA_INFO_BUFFERING_END";
                break;
            case 801:
                str = "MEDIA_INFO_NOT_SEEKABLE";
                break;
        }
        this.e.onVideoInfo(str);
        return false;
    }

    public TJVideoListener getPublisherVideoListener() {
        return publisherVideoListener;
    }
}
