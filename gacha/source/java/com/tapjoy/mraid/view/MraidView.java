package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.controller.Abstract.PlayerProperties;
import com.tapjoy.mraid.controller.Abstract.Properties;
import com.tapjoy.mraid.controller.Utility;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.HttpResponseCode;
import twitter4j.MediaEntity.Size;
import twitter4j.TwitterResponse;

public class MraidView extends BasicWebView implements OnGlobalLayoutListener {
    public static final String ACTION_KEY = "action";
    public static final String DIMENSIONS = "expand_dimensions";
    public static final String EXPAND_URL = "expand_url";
    public static final int MRAID_ID = 102;
    public static final String PLAYER_PROPERTIES = "player_properties";
    private static int[] c = new int[]{16843039, 16843040};
    private static final String[] d = new String[]{".mp4", ".3gp", ".mpg"};
    private static MraidPlayer t;
    private Context A;
    private RelativeLayout B;
    private VideoView C;
    private CustomViewCallback D;
    private ProgressBar E;
    private Handler F = new Handler(this) {
        final /* synthetic */ MraidView a;

        {
            this.a = r1;
        }

        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            MarginLayoutParams marginLayoutParams;
            String string;
            switch (message.what) {
                case GameControllerDelegate.THUMBSTICK_LEFT_X /*1000*/:
                    marginLayoutParams = (MarginLayoutParams) this.a.getLayoutParams();
                    if (marginLayoutParams != null) {
                        this.a.removeCloseImageButton();
                        this.a.r = VIEW_STATE.RESIZED;
                        marginLayoutParams.height = data.getInt("resize_height", marginLayoutParams.height);
                        marginLayoutParams.width = data.getInt("resize_width", marginLayoutParams.width);
                        marginLayoutParams.leftMargin = data.getInt("resize_x", marginLayoutParams.leftMargin);
                        marginLayoutParams.topMargin = data.getInt("resize_y", marginLayoutParams.topMargin);
                        this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'resized', size: { width: " + marginLayoutParams.width + ", height: " + marginLayoutParams.height + ", x: " + marginLayoutParams.leftMargin + ", y: " + marginLayoutParams.topMargin + "}});");
                        this.a.requestLayout();
                        this.a.a(data.getString("resize_customClosePostition"));
                        this.a.showCloseImageButton();
                    }
                    if (this.a.s != null) {
                        this.a.s.onResize();
                        break;
                    }
                    break;
                case GameControllerDelegate.THUMBSTICK_LEFT_Y /*1001*/:
                    switch (AnonymousClass7.a[this.a.r.ordinal()]) {
                        case TwitterResponse.READ /*1*/:
                            this.a.b();
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            this.a.a();
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            if (this.a.q != PLACEMENT_TYPE.INLINE) {
                                MraidView.g(this.a);
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                case GameControllerDelegate.THUMBSTICK_RIGHT_X /*1002*/:
                    this.a.setVisibility(4);
                    this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'hidden' });");
                    break;
                case GameControllerDelegate.THUMBSTICK_RIGHT_Y /*1003*/:
                    this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                    this.a.setVisibility(0);
                    break;
                case GameControllerDelegate.BUTTON_A /*1004*/:
                    MraidView.a(this.a, data);
                    break;
                case GameControllerDelegate.BUTTON_B /*1005*/:
                    if (this.a.s != null) {
                        this.a.s.onExpandClose();
                        break;
                    }
                    break;
                case GameControllerDelegate.BUTTON_C /*1006*/:
                    this.a.r = VIEW_STATE.LEFT_BEHIND;
                    break;
                case GameControllerDelegate.BUTTON_X /*1007*/:
                    this.a.playVideoImpl(data);
                    break;
                case GameControllerDelegate.BUTTON_Y /*1008*/:
                    this.a.playAudioImpl(data);
                    break;
                case GameControllerDelegate.BUTTON_Z /*1009*/:
                    string = data.getString(String.MESSAGE);
                    this.a.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + string + "\", \"" + data.getString(MraidView.ACTION_KEY) + "\")");
                    break;
                case GameControllerDelegate.BUTTON_DPAD_UP /*1010*/:
                    marginLayoutParams = (MarginLayoutParams) this.a.getLayoutParams();
                    if (marginLayoutParams != null) {
                        this.a.removeCloseImageButton();
                        marginLayoutParams.height = data.getInt("resize_height", marginLayoutParams.height);
                        marginLayoutParams.width = data.getInt("resize_width", marginLayoutParams.width);
                        string = "window.mraidview.fireChangeEvent({ state: '" + this.a.getState() + "', size: { width: " + ((int) (((float) marginLayoutParams.width) / this.a.i)) + ", height: " + ((int) (((float) marginLayoutParams.height) / this.a.i)) + "}});";
                        TapjoyLog.d("MRAIDView", "resize: injection: " + string);
                        this.a.injectMraidJavaScript(string);
                        this.a.requestLayout();
                        this.a.a(data.getString("resize_customClosePostition"));
                        if (this.a.q != PLACEMENT_TYPE.INLINE && this.a.e == customCloseState.OPEN) {
                            this.a.showCloseImageButton();
                        }
                    }
                    if (this.a.s != null) {
                        this.a.s.onResize();
                        break;
                    }
                    break;
            }
            super.handleMessage(message);
        }
    };
    private boolean G;
    WebViewClient a = new WebViewClient(this) {
        final /* synthetic */ MraidView a;

        {
            this.a = r1;
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (this.a.s != null) {
                this.a.s.onPageStarted(webView, str, bitmap);
            }
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.a.s != null) {
                this.a.s.onReceivedError(webView, i, str, str2);
            }
            TapjoyLog.d("MRAIDView", "error:" + str);
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onPageFinished(WebView webView, String str) {
            if (this.a.s != null) {
                this.a.s.onPageFinished(webView, str);
            }
            this.a.l = (int) (((float) this.a.getHeight()) / this.a.i);
            this.a.m = (int) (((float) this.a.getWidth()) / this.a.i);
            this.a.h.init(this.a.i);
            this.a.createCloseImageButton();
            if (this.a.q == PLACEMENT_TYPE.INLINE) {
                this.a.removeCloseImageButton();
            }
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            TapjoyLog.d("MRAIDView", "shouldOverrideUrlLoading: " + str);
            if (this.a.s != null && this.a.s.shouldOverrideUrlLoading(webView, str)) {
                return true;
            }
            Uri parse = Uri.parse(str);
            Intent intent;
            try {
                if (str.startsWith("mraid")) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                if (str.startsWith("tel:")) {
                    intent = new Intent("android.intent.action.DIAL", Uri.parse(str));
                    intent.addFlags(268435456);
                    this.a.getContext().startActivity(intent);
                    return true;
                } else if (str.startsWith("mailto:")) {
                    intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.addFlags(268435456);
                    this.a.getContext().startActivity(intent);
                    return true;
                } else {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(parse);
                    intent.addFlags(268435456);
                    this.a.getContext().startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                try {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(parse);
                    intent.addFlags(268435456);
                    this.a.getContext().startActivity(intent);
                    return true;
                } catch (Exception e2) {
                    return false;
                }
            }
        }

        public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (TapjoyCache.getInstance() != null) {
                TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(str);
                if (cachedDataForURL == null) {
                    TapjoyLog.d("MRAIDView", "No cached data for " + str);
                } else {
                    WebResourceResponse a = MraidView.b(cachedDataForURL);
                    if (a != null) {
                        TapjoyLog.d("MRAIDView", "Reading request for " + str + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                        return a;
                    }
                }
            }
            return super.shouldInterceptRequest(webView, str);
        }

        public final void onLoadResource(WebView webView, String str) {
        }
    };
    WebChromeClient b = new WebChromeClient(this) {
        final /* synthetic */ MraidView a;

        {
            this.a = r1;
        }

        public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            TapjoyLog.d("MRAIDView", str2);
            return false;
        }

        public final void onCloseWindow(WebView webView) {
            super.onCloseWindow(webView);
            MraidView.g(this.a);
        }

        public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            TapjoyLog.d("MRAIDView", "-- onShowCustomView --");
            super.onShowCustomView(view, customViewCallback);
            this.a.D = customViewCallback;
            if (view instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) view;
                if ((frameLayout.getFocusedChild() instanceof VideoView) && (this.a.A instanceof Activity)) {
                    Activity activity = (Activity) this.a.A;
                    this.a.C = (VideoView) frameLayout.getFocusedChild();
                    frameLayout.removeView(this.a.C);
                    if (this.a.B == null) {
                        this.a.B = new RelativeLayout(this.a.A);
                        this.a.B.setLayoutParams(new LayoutParams(-1, -1));
                        this.a.B.setBackgroundColor(-16777216);
                    }
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(13);
                    this.a.C.setLayoutParams(layoutParams);
                    this.a.E = new ProgressBar(this.a.A, null, 16842874);
                    this.a.E.setVisibility(0);
                    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(13);
                    this.a.E.setLayoutParams(layoutParams);
                    this.a.B.addView(this.a.C);
                    this.a.B.addView(this.a.E);
                    activity.getWindow().addContentView(this.a.B, new LayoutParams(-1, -1));
                    new Thread(new c(this.a)).start();
                    this.a.setVisibility(8);
                    this.a.C.setOnPreparedListener(new OnPreparedListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public final void onPrepared(MediaPlayer mediaPlayer) {
                            TapjoyLog.i("MRAIDView", "** ON PREPARED **");
                            TapjoyLog.i("MRAIDView", "isPlaying: " + mediaPlayer.isPlaying());
                            if (!mediaPlayer.isPlaying()) {
                                mediaPlayer.start();
                            }
                        }
                    });
                    this.a.C.setOnCompletionListener(new OnCompletionListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public final void onCompletion(MediaPlayer mediaPlayer) {
                            TapjoyLog.i("MRAIDView", "** ON COMPLETION **");
                            this.a.a.videoViewCleanup();
                        }
                    });
                    this.a.C.setOnErrorListener(new OnErrorListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                            TapjoyLog.i("MRAIDView", "** ON ERROR **");
                            this.a.a.videoViewCleanup();
                            return false;
                        }
                    });
                    this.a.C.start();
                }
            }
        }

        public final void onHideCustomView() {
            super.onHideCustomView();
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (this.a.s != null) {
                return this.a.s.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    };
    private customCloseState e = customCloseState.UNKNOWN;
    private boolean f = false;
    private boolean g;
    private Utility h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private PLACEMENT_TYPE q;
    private VIEW_STATE r = VIEW_STATE.DEFAULT;
    private MraidViewListener s;
    private final HashSet u = new HashSet();
    private int v = 0;
    private int w = 0;
    private Thread x = null;
    private boolean y = false;
    private int z;

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[VIEW_STATE.values().length];

        static {
            try {
                a[VIEW_STATE.RESIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[VIEW_STATE.EXPANDED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[VIEW_STATE.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum Action {
        PLAY_AUDIO,
        PLAY_VIDEO
    }

    public static abstract class NewLocationReciever {
        public abstract void OnNewLocation(VIEW_STATE view_state);
    }

    public enum PLACEMENT_TYPE {
        INLINE,
        INTERSTITIAL
    }

    public enum VIEW_STATE {
        DEFAULT,
        RESIZED,
        EXPANDED,
        HIDDEN,
        LEFT_BEHIND,
        OPENED
    }

    class a extends AsyncTask {
        TapjoyHttpURLResponse a;
        TapjoyURLConnection b;
        String c;
        final /* synthetic */ MraidView d;

        private a(MraidView mraidView) {
            this.d = mraidView;
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        private Void a(String... strArr) {
            this.c = strArr[0];
            try {
                this.b = new TapjoyURLConnection();
                this.a = this.b.getResponseFromURL(this.c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            try {
                if (this.a.statusCode == 0 || this.a.response == null) {
                    TapjoyLog.e("MRAIDView", new TapjoyErrorMessage(ErrorType.NETWORK_ERROR, "Connection not properly established"));
                    if (this.d.s != null) {
                        this.d.s.onReceivedError(this.d, 0, "Connection not properly established", this.c);
                    }
                } else if (this.a.statusCode != HttpResponseCode.FOUND || this.a.redirectURL == null || this.a.redirectURL.length() <= 0) {
                    this.d.loadDataWithBaseURL(this.c, this.a.response, "text/html", "utf-8", this.c);
                } else {
                    TapjoyLog.i("MRAIDView", "302 redirectURL detected: " + this.a.redirectURL);
                    this.d.loadUrlStandard(this.a.redirectURL);
                }
            } catch (Exception e) {
                TapjoyLog.w("MRAIDView", "error in loadURL " + e);
                e.printStackTrace();
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ MraidView a;

        public b(MraidView mraidView) {
            this.a = mraidView;
        }

        public final void run() {
            while (!this.a.y) {
                try {
                    Thread.sleep(250);
                    this.a.c();
                } catch (Exception e) {
                }
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ MraidView a;

        class a implements Runnable {
            final /* synthetic */ c a;
            private boolean b = false;

            public a(c cVar) {
                this.a = cVar;
            }

            public final void run() {
                while (this.a.a.C != null) {
                    try {
                        Thread.sleep(100);
                        if (this.b != this.a.a.C.isPlaying()) {
                            this.b = this.a.a.C.isPlaying();
                            this.a.a.loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('" + (this.b ? "videoplay" : "videopause") + "')}catch(e){}");
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }

        public c(MraidView mraidView) {
            this.a = mraidView;
        }

        public final void run() {
            int i = 0;
            while (this.a.C != null && !this.a.C.isPlaying()) {
                try {
                    Thread.sleep(50);
                    i += 50;
                    if (i >= 10000) {
                        break;
                    }
                } catch (Exception e) {
                }
            }
            ((Activity) this.a.A).runOnUiThread(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public final void run() {
                    if (this.a.a.E != null) {
                        this.a.a.E.setVisibility(8);
                    }
                    new Thread(new a(this.a)).start();
                }
            });
        }
    }

    public enum customCloseState {
        HIDDEN,
        OPEN,
        UNKNOWN
    }

    static /* synthetic */ void a(MraidView mraidView, Bundle bundle) {
        if (mraidView.r != VIEW_STATE.EXPANDED) {
            Dimensions dimensions = (Dimensions) bundle.getParcelable(DIMENSIONS);
            String string = bundle.getString(EXPAND_URL);
            Properties properties = (Properties) bundle.getParcelable("expand_properties");
            if (URLUtil.isValidUrl(string)) {
                mraidView.loadUrl(string);
            }
            FrameLayout frameLayout = (FrameLayout) mraidView.getRootView().findViewById(16908290);
            ViewGroup viewGroup = (ViewGroup) mraidView.getParent();
            LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensions.width, dimensions.height);
            layoutParams.topMargin = dimensions.x;
            layoutParams.leftMargin = dimensions.y;
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (i < childCount && viewGroup.getChildAt(i) != mraidView) {
                i++;
            }
            mraidView.p = i;
            View frameLayout2 = new FrameLayout(mraidView.getContext());
            frameLayout2.setId(100);
            viewGroup.addView(frameLayout2, i, new LayoutParams(mraidView.getWidth(), mraidView.getHeight()));
            viewGroup.removeView(mraidView);
            View frameLayout3 = new FrameLayout(mraidView.getContext());
            frameLayout3.setOnTouchListener(new OnTouchListener(mraidView) {
                final /* synthetic */ MraidView a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    TapjoyLog.d("MRAIDView", "background touch called");
                    return true;
                }
            });
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
            frameLayout3.setId(Size.CROP);
            frameLayout3.setPadding(dimensions.x, dimensions.y, 0, 0);
            frameLayout3.addView(mraidView, layoutParams);
            frameLayout.addView(frameLayout3, layoutParams2);
            if (properties.useBackground) {
                frameLayout3.setBackgroundColor(properties.backgroundColor | (((int) (properties.backgroundOpacity * 255.0f)) * 268435456));
            }
            if (!properties.useCustomClose) {
                mraidView.showCloseImageButton();
            }
            String str = "window.mraidview.fireChangeEvent({ state: 'expanded', size: { width: " + ((int) (((float) dimensions.width) / mraidView.i)) + ", height: " + ((int) (((float) dimensions.height) / mraidView.i)) + ",x:0,y:0} });";
            TapjoyLog.d("MRAIDView", "doExpand: injection: " + str);
            mraidView.injectMraidJavaScript(str);
            mraidView.r = VIEW_STATE.EXPANDED;
            mraidView.c();
            if (mraidView.s != null) {
                mraidView.s.onExpand();
            }
        }
    }

    public MraidView(Context context, MraidViewListener mraidViewListener) {
        super(context);
        setListener(mraidViewListener);
        this.A = context;
        initialize();
    }

    public void setListener(MraidViewListener mraidViewListener) {
        this.s = mraidViewListener;
    }

    public void removeListener() {
        this.s = null;
    }

    public MraidView(Context context) {
        super(context);
        this.A = context;
        initialize();
    }

    public void setPlacementType(PLACEMENT_TYPE placement_type) {
        if (placement_type.equals(PLACEMENT_TYPE.INLINE) || placement_type.equals(PLACEMENT_TYPE.INTERSTITIAL)) {
            this.q = placement_type;
        } else {
            TapjoyLog.d("MRAIDView", "Incorrect placement type.");
        }
        if (!placement_type.equals(PLACEMENT_TYPE.INLINE)) {
            return;
        }
        if (this.x == null || !this.x.isAlive()) {
            this.x = new Thread(new b(this));
            this.x.start();
        }
    }

    public PLACEMENT_TYPE getPlacementType() {
        return this.q;
    }

    public void createCloseImageButton() {
        injectMraidJavaScript("window.mraidview.createCss();");
        TapjoyLog.d("MRAIDView", "Creating close button.");
    }

    public void removeCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"hidden\";");
        TapjoyLog.d("MRAIDView", "Removing close button.");
        this.e = customCloseState.HIDDEN;
    }

    public void showCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"visible\";");
        TapjoyLog.d("MRAIDView", "Showing close button.");
        this.e = customCloseState.OPEN;
    }

    public customCloseState getCloseButtonState() {
        return this.e;
    }

    public boolean isMraid() {
        return this.f;
    }

    public void setMaxSize(int i, int i2) {
        this.h.setMaxSize(i, i2);
    }

    public void injectMraidJavaScript(String str) {
        if (str != null && this.f) {
            loadUrl("javascript:" + str);
        }
    }

    public void loadUrl(final String str) {
        ((Activity) this.A).runOnUiThread(new Runnable(this) {
            final /* synthetic */ MraidView b;

            public final void run() {
                if (!URLUtil.isValidUrl(str)) {
                    this.b.loadDataWithBaseURL(null, "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Connection not Established</title></head><h2>Connection Not Properly Established</h2><body></body></html>", "text/html", "utf-8", null);
                } else if (!str.startsWith("javascript:")) {
                    new a().execute(new String[]{str});
                } else if (VERSION.SDK_INT >= 19) {
                    try {
                        super.evaluateJavascript(str.replaceFirst("javascript:", BuildConfig.FLAVOR), null);
                    } catch (Exception e) {
                        TapjoyLog.e("MRAIDView", "Exception in evaluateJavascript. Device not supported. " + e.toString());
                    }
                } else {
                    super.loadUrl(str);
                }
            }
        });
    }

    public void loadUrlStandard(String str) {
        super.loadUrl(str);
    }

    public boolean hasMraidTag(String str) {
        return Pattern.compile("<\\s*script[^>]+mraid\\.js").matcher(str).find();
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (str2 != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int indexOf = str2.indexOf("<html>");
            this.f = false;
            int indexOf2 = str2.indexOf("mraid.js");
            if (indexOf2 <= 0 || !hasMraidTag(str2)) {
                stringBuffer.append(str2);
            } else {
                this.f = true;
                int i = indexOf2;
                while (i >= 0) {
                    if (str2.substring(i, i + 7).equals("<script")) {
                        break;
                    }
                    i--;
                }
                i = indexOf2;
                int i2 = 0;
                while (i2 < str2.length()) {
                    if (str2.substring(indexOf2 + i2, (indexOf2 + i2) + 2).equalsIgnoreCase("/>")) {
                        indexOf2 = (indexOf2 + i2) + 2;
                        break;
                    } else if (str2.substring(indexOf2 + i2, (indexOf2 + i2) + 9).equalsIgnoreCase("</script>")) {
                        indexOf2 = (indexOf2 + i2) + 9;
                        break;
                    } else {
                        i2++;
                    }
                }
                String str6;
                if (indexOf < 0) {
                    TapjoyLog.d("MRAIDView", "wrapping fragment");
                    stringBuffer.append("<html>");
                    stringBuffer.append("<head>");
                    stringBuffer.append("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />");
                    stringBuffer.append("<title>Advertisement</title>");
                    stringBuffer.append("</head>");
                    stringBuffer.append("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">");
                    stringBuffer.append("<div align=\"center\"> ");
                    stringBuffer.append(str2.substring(0, i));
                    stringBuffer.append("<script type=text/javascript>");
                    str6 = (String) TapjoyUtil.getResource("mraid.js");
                    if (str6 == null) {
                        str6 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                    }
                    stringBuffer.append(str6);
                    stringBuffer.append("</script>");
                    stringBuffer.append(str2.substring(indexOf2));
                } else {
                    indexOf2 = str2.indexOf("<head>");
                    if (indexOf2 != -1) {
                        str6 = (String) TapjoyUtil.getResource("mraid.js");
                        if (str6 == null) {
                            str6 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                        }
                        stringBuffer.append(str2.substring(0, indexOf2 + 6));
                        stringBuffer.append("<script type='text/javascript'>");
                        stringBuffer.append(str6);
                        stringBuffer.append("</script>");
                        stringBuffer.append(str2.substring(indexOf2 + 6));
                    }
                }
                TapjoyLog.d("MRAIDView", "injected js/mraid.js");
            }
            super.loadDataWithBaseURL(str, stringBuffer.toString(), str3, str4, str5);
        }
    }

    public void clearView() {
        reset();
        super.clearView();
    }

    public void reset() {
        if (this.r == VIEW_STATE.EXPANDED) {
            a();
        } else if (this.r == VIEW_STATE.RESIZED) {
            b();
        }
        invalidate();
        this.h.deleteOldAds();
        this.h.stopAllListeners();
        d();
    }

    public MraidView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, c);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize > 0 && dimensionPixelSize2 > 0) {
            this.h.setMaxSize(dimensionPixelSize, dimensionPixelSize2);
        }
        obtainStyledAttributes.recycle();
    }

    private void a(String str) {
        if (str != null) {
            String str2 = null;
            if (str.equals("top-right")) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals("top-center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals("top-left")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = 1";
            } else if (str.equals("center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals("bottom-right")) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals("bottom-left")) {
                str2 = "document.getElementById(\"closeButton\").style.left = 1;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            } else if (str.equals("bottom-center")) {
                str2 = "document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 -18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            }
            if (str2 != null) {
                injectMraidJavaScript(str2);
            } else {
                TapjoyLog.d("MRAIDView", "Reposition of close button failed.");
            }
        }
    }

    private void b() {
        this.r = VIEW_STATE.DEFAULT;
        if (this.s != null) {
            this.s.onResizeClose();
        }
        String str = "window.mraidview.fireChangeEvent({ state: 'default', size: { width: " + this.m + ", height: " + this.l + ", x:0,y:0}});";
        TapjoyLog.d("MRAIDView", "closeResized: injection: " + str);
        injectMraidJavaScript(str);
        a("top-right");
        d();
    }

    private static WebResourceResponse b(TapjoyCachedAssetData tapjoyCachedAssetData) {
        if (tapjoyCachedAssetData == null) {
            return null;
        }
        try {
            return new WebResourceResponse(tapjoyCachedAssetData.getMimeType(), com.d.a.a.c.DEFAULT_CHARSET, new FileInputStream(tapjoyCachedAssetData.getLocalFilePath()));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public boolean videoPlaying() {
        return this.C != null;
    }

    public void videoViewCleanup() {
        if (this.B != null) {
            ((ViewGroup) this.B.getParent()).removeView(this.B);
            this.B.setVisibility(8);
            this.B = null;
        }
        try {
            if (this.C != null) {
                this.C.stopPlayback();
            }
            if (this.D != null) {
                this.D.onCustomViewHidden();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.C = null;
        this.D = null;
        if (this != null) {
            setVisibility(0);
        }
        loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('videoend')}catch(e){}");
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void initialize() {
        setPlacementType(PLACEMENT_TYPE.INTERSTITIAL);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.i = displayMetrics.density;
        this.g = false;
        this.h = new Utility(this, getContext());
        addJavascriptInterface(this.h, "MRAIDUtilityControllerBridge");
        setWebViewClient(this.a);
        setWebChromeClient(this.b);
        this.j = getContentViewHeight();
        if (getViewTreeObserver() != null) {
            getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.v = windowManager.getDefaultDisplay().getWidth();
        this.w = windowManager.getDefaultDisplay().getHeight();
        if (getContext() instanceof Activity) {
            this.z = ((Activity) getContext()).getRequestedOrientation();
        }
    }

    private void c() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        if (width != this.v || height != this.w) {
            if ((getPlacementType() == PLACEMENT_TYPE.INLINE && getViewState() == VIEW_STATE.EXPANDED) || getPlacementType() == PLACEMENT_TYPE.INTERSTITIAL) {
                resizeOrientation(width, height, "top-right", true);
            }
        }
    }

    public void addJavascriptObject(Object obj, String str) {
        addJavascriptInterface(obj, str);
    }

    private int getContentViewHeight() {
        View findViewById = getRootView().findViewById(16908290);
        if (findViewById != null) {
            return findViewById.getHeight();
        }
        return -1;
    }

    protected final synchronized void a() {
        resetContents();
        String str = "window.mraidview.fireChangeEvent({ state: 'default', size: { width: " + this.m + ", height: " + this.l + "}});";
        TapjoyLog.d("MRAIDView", "closeExpanded: injection: " + str);
        injectMraidJavaScript(str);
        this.r = VIEW_STATE.DEFAULT;
        this.F.sendEmptyMessage(GameControllerDelegate.BUTTON_B);
        setVisibility(0);
        removeCloseImageButton();
        ((Activity) getContext()).setRequestedOrientation(this.z);
    }

    public VIEW_STATE getViewState() {
        return this.r;
    }

    public String getState() {
        return this.r.toString().toLowerCase();
    }

    public void resize(int i, int i2, int i3, int i4, String str, boolean z) {
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.THUMBSTICK_LEFT_X);
        Bundle bundle = new Bundle();
        bundle.putInt("resize_width", i);
        bundle.putInt("resize_height", i2);
        bundle.putInt("resize_x", i3);
        bundle.putInt("resize_y", i4);
        bundle.putBoolean("resize_allowOffScreen", z);
        bundle.putString("resize_customClosePostition", str);
        obtainMessage.setData(bundle);
        this.F.sendMessage(obtainMessage);
    }

    public void resizeOrientation(int i, int i2, String str, boolean z) {
        this.v = i;
        this.w = i2;
        TapjoyLog.d("MRAIDView", "resizeOrientation to dimensions: " + i + "x" + i2);
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.BUTTON_DPAD_UP);
        Bundle bundle = new Bundle();
        bundle.putInt("resize_width", i);
        bundle.putInt("resize_height", i2);
        bundle.putBoolean("resize_allowOffScreen", z);
        bundle.putString("resize_customClosePostition", str);
        obtainMessage.setData(bundle);
        this.F.sendMessage(obtainMessage);
    }

    public void close() {
        this.F.sendEmptyMessage(GameControllerDelegate.THUMBSTICK_LEFT_Y);
    }

    public void hide() {
        this.F.sendEmptyMessage(GameControllerDelegate.THUMBSTICK_RIGHT_X);
    }

    public void show() {
        this.F.sendEmptyMessage(GameControllerDelegate.THUMBSTICK_RIGHT_Y);
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService("connectivity");
    }

    public void expand(Dimensions dimensions, String str, Properties properties) {
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.BUTTON_A);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DIMENSIONS, dimensions);
        bundle.putString(EXPAND_URL, str);
        bundle.putParcelable("expand_properties", properties);
        obtainMessage.setData(bundle);
        this.F.sendMessage(obtainMessage);
    }

    public void open(String str, boolean z, boolean z2, boolean z3) {
        boolean z4;
        String str2 = null;
        if (b(str)) {
            str2 = str;
            z4 = true;
        } else {
            TapjoyHttpURLResponse redirectFromURL = new TapjoyURLConnection().getRedirectFromURL(str);
            TapjoyLog.i("MRAIDView", "redirect: " + redirectFromURL.redirectURL + ", " + redirectFromURL.statusCode);
            if (redirectFromURL == null || redirectFromURL.redirectURL == null || redirectFromURL.redirectURL.length() <= 0 || !b(redirectFromURL.redirectURL)) {
                z4 = false;
            } else {
                str2 = redirectFromURL.redirectURL;
                z4 = true;
            }
        }
        if (z4) {
            Dimensions dimensions = new Dimensions();
            dimensions.x = 0;
            dimensions.y = 0;
            dimensions.width = getWidth();
            dimensions.height = getHeight();
            playVideo(str2, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
            return;
        }
        TapjoyLog.d("MRAIDView", "Mraid Browser open:" + str);
        Intent intent = new Intent(getContext(), Browser.class);
        intent.putExtra(Browser.URL_EXTRA, str);
        intent.putExtra(Browser.SHOW_BACK_EXTRA, z);
        intent.putExtra(Browser.SHOW_FORWARD_EXTRA, z2);
        intent.putExtra(Browser.SHOW_REFRESH_EXTRA, z3);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    private static boolean b(String str) {
        for (String endsWith : d) {
            if (str.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public void setOrientationProperties(boolean z, String str) {
        int i;
        if (z) {
            i = -1;
        } else {
            i = str.equals(String.LANDSCAPE) ? 0 : 1;
        }
        ((Activity) getContext()).setRequestedOrientation(i);
    }

    public void openMap(String str, boolean z) {
        TapjoyLog.d("MRAIDView", "Opening Map Url " + str);
        String convert = Utils.convert(str.trim());
        if (z) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(convert));
                intent.setFlags(268435456);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void playAudioImpl(Bundle bundle) {
        PlayerProperties playerProperties = (PlayerProperties) bundle.getParcelable(PLAYER_PROPERTIES);
        String string = bundle.getString(EXPAND_URL);
        View player = getPlayer();
        player.setPlayData(playerProperties, string);
        player.setLayoutParams(new LayoutParams(1, 1));
        ((ViewGroup) getParent()).addView(player);
        player.playAudio();
    }

    public void playAudio(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3) {
        Object playerProperties = new PlayerProperties();
        playerProperties.setProperties(false, z, z2, z4, z3, str2, str3);
        Bundle bundle = new Bundle();
        bundle.putString(ACTION_KEY, Action.PLAY_AUDIO.toString());
        bundle.putString(EXPAND_URL, str);
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.BUTTON_Y);
        obtainMessage.setData(bundle);
        this.F.sendMessage(obtainMessage);
    }

    public void playVideoImpl(Bundle bundle) {
        PlayerProperties playerProperties = (PlayerProperties) bundle.getParcelable(PLAYER_PROPERTIES);
        Dimensions dimensions = (Dimensions) bundle.getParcelable(DIMENSIONS);
        String string = bundle.getString(EXPAND_URL);
        View player = getPlayer();
        player.setPlayData(playerProperties, string);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensions.width, dimensions.height);
        layoutParams.topMargin = dimensions.x;
        layoutParams.leftMargin = dimensions.y;
        player.setLayoutParams(layoutParams);
        View frameLayout = new FrameLayout(getContext());
        frameLayout.setId(Size.CROP);
        frameLayout.setPadding(dimensions.x, dimensions.y, 0, 0);
        ((FrameLayout) getRootView().findViewById(16908290)).addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(player);
        setVisibility(4);
        player.setListener(new Player(this) {
            final /* synthetic */ MraidView a;

            {
                this.a = r1;
            }

            public final void onPrepared() {
            }

            public final void onError() {
                onComplete();
            }

            public final void onComplete() {
                FrameLayout frameLayout = (FrameLayout) this.a.getRootView().findViewById(Size.CROP);
                ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
                this.a.setVisibility(0);
            }
        });
        player.playVideo();
    }

    public void playVideo(String str, boolean z, boolean z2, boolean z3, boolean z4, Dimensions dimensions, String str2, String str3) {
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.BUTTON_X);
        Object playerProperties = new PlayerProperties();
        playerProperties.setProperties(z, z2, z3, false, z4, str2, str3);
        Bundle bundle = new Bundle();
        bundle.putString(EXPAND_URL, str);
        bundle.putString(ACTION_KEY, Action.PLAY_VIDEO.toString());
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (dimensions != null) {
            bundle.putParcelable(DIMENSIONS, dimensions);
        }
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                intent.setFlags(268435456);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else if (dimensions != null) {
            obtainMessage.setData(bundle);
            this.F.sendMessage(obtainMessage);
        }
    }

    public void resetContents() {
        FrameLayout frameLayout = (FrameLayout) getRootView().findViewById(16908290);
        FrameLayout frameLayout2 = (FrameLayout) getRootView().findViewById(100);
        FrameLayout frameLayout3 = (FrameLayout) getRootView().findViewById(Size.CROP);
        frameLayout3.removeView(this);
        frameLayout.removeView(frameLayout3);
        d();
        if (frameLayout2 != null) {
            ViewGroup viewGroup = (ViewGroup) frameLayout2.getParent();
            if (viewGroup != null) {
                viewGroup.addView(this, this.p);
                viewGroup.removeView(frameLayout2);
                viewGroup.invalidate();
            }
        }
    }

    private void d() {
        LayoutParams layoutParams = getLayoutParams();
        if (this.G) {
            layoutParams.height = this.n;
            layoutParams.width = this.o;
        }
        setVisibility(0);
        requestLayout();
    }

    public boolean isPageFinished() {
        return this.g;
    }

    public void onGlobalLayout() {
        boolean z = this.k;
        if (!this.k && this.j >= 0 && getContentViewHeight() >= 0 && this.j != getContentViewHeight()) {
            z = true;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: true});");
        }
        if (this.k && this.j >= 0 && getContentViewHeight() >= 0 && this.j == getContentViewHeight()) {
            z = false;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: false});");
        }
        if (this.j < 0) {
            this.j = getContentViewHeight();
        }
        this.k = z;
    }

    public String getSize() {
        return "{ width: " + ((int) Math.ceil((double) (((float) getWidth()) / this.i))) + ", height: " + ((int) Math.ceil((double) (((float) getHeight()) / this.i))) + "}";
    }

    protected void onAttachedToWindow() {
        if (!this.G) {
            LayoutParams layoutParams = getLayoutParams();
            this.n = layoutParams.height;
            this.o = layoutParams.width;
            this.G = true;
        }
        this.y = false;
        if (this.x == null || !this.x.isAlive()) {
            this.x = new Thread(new b(this));
            this.x.start();
        }
        super.onAttachedToWindow();
    }

    public WebBackForwardList saveState(Bundle bundle) {
        return super.saveState(bundle);
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        return super.restoreState(bundle);
    }

    public void raiseError(String str, String str2) {
        Message obtainMessage = this.F.obtainMessage(GameControllerDelegate.BUTTON_Z);
        Bundle bundle = new Bundle();
        bundle.putString(String.MESSAGE, str);
        bundle.putString(ACTION_KEY, str2);
        obtainMessage.setData(bundle);
        this.F.sendMessage(obtainMessage);
    }

    public boolean isExpanded() {
        return this.r == VIEW_STATE.EXPANDED;
    }

    protected void onDetachedFromWindow() {
        this.y = true;
        this.h.stopAllListeners();
        try {
            if (this.C != null) {
                this.C.stopPlayback();
            }
            if (this.D != null) {
                this.D.onCustomViewHidden();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    MraidPlayer getPlayer() {
        if (t != null) {
            t.releasePlayer();
        }
        MraidPlayer mraidPlayer = new MraidPlayer(getContext());
        t = mraidPlayer;
        return mraidPlayer;
    }

    public void setContext(Context context) {
        this.A = context;
    }

    static /* synthetic */ void g(MraidView mraidView) {
        try {
            if (mraidView.s != null) {
                mraidView.s.onClose();
            }
            ((ViewGroup) mraidView.getParent()).removeView(mraidView);
        } catch (Exception e) {
        }
    }
}
