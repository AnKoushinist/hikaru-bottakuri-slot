package com.tapjoy.mraid.controller;

import android.R.raw;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.util.ConfigBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;
import java.util.List;
import twitter4j.TwitterResponse;

public class Display extends Abstract {
    private WindowManager c;
    private boolean d = false;
    private int e = -1;
    private int f = -1;
    private ConfigBroadcastReceiver g;
    private float h;
    private Context i;

    public Display(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.i = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c = (WindowManager) context.getSystemService("window");
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        this.h = displayMetrics.density;
    }

    @JavascriptInterface
    public void open(String str, boolean z, boolean z2, boolean z3) {
        TapjoyLog.i("MRAID Display", "open: url: " + str + " back: " + z + " forward: " + z2 + " refresh: " + z3);
        if (URLUtil.isValidUrl(str)) {
            this.a.open(str, z, z2, z3);
            return;
        }
        TapjoyLog.i("MRAID Display", "invalid URL");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        List queryIntentActivities = this.i.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() == 1) {
            this.i.startActivity(intent);
        } else if (queryIntentActivities.size() > 1) {
            ((Activity) this.i).startActivity(Intent.createChooser(intent, TJAdUnitConstants.SHARE_CHOOSE_TITLE));
        } else {
            this.a.raiseError("Invalid url", "open");
        }
    }

    @JavascriptInterface
    public void useCustomClose(boolean z) {
        if (z) {
            this.a.removeCloseImageButton();
        } else if (!z) {
            this.a.showCloseImageButton();
        }
    }

    @JavascriptInterface
    public void openMap(String str, boolean z) {
        TapjoyLog.d("MRAID Display", "openMap: url: " + str);
        this.a.openMap(str, z);
    }

    @JavascriptInterface
    public void setOrientationProperties(boolean z, String str) {
        TapjoyLog.d("MRAID Display", "setOrientationProperties: allowOrientationChange: " + Boolean.toString(z) + " forceOrientation: " + str);
        this.a.setOrientationProperties(z, str);
    }

    @JavascriptInterface
    public void playAudio(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, String str3) {
        TapjoyLog.d("MRAID Display", "playAudio: url: " + str + " autoPlay: " + z + " controls: " + z2 + " loop: " + z3 + " position: " + z4 + " startStyle: " + str2 + " stopStyle: " + str3);
        if (URLUtil.isValidUrl(str)) {
            this.a.playAudio(str, z, z2, z3, z4, str2, str3);
        } else {
            this.a.raiseError("Invalid url", "playAudio");
        }
    }

    @JavascriptInterface
    public void playVideo(String str, boolean z, boolean z2, boolean z3, boolean z4, int[] iArr, String str2, String str3) {
        String str4;
        TapjoyLog.d("MRAID Display", "playVideo: url: " + str + " audioMuted: " + z + " autoPlay: " + z2 + " controls: " + z3 + " loop: " + z4 + " x: " + iArr[0] + " y: " + iArr[1] + " width: " + iArr[2] + " height: " + iArr[3] + " startStyle: " + str2 + " stopStyle: " + str3);
        Dimensions dimensions = null;
        if (iArr[0] != -1) {
            dimensions = new Dimensions();
            dimensions.x = iArr[0];
            dimensions.y = iArr[1];
            dimensions.width = iArr[2];
            dimensions.height = iArr[3];
            dimensions.width = (int) Math.ceil((double) (this.h * ((float) dimensions.width)));
            dimensions.height = (int) Math.ceil((double) (this.h * ((float) dimensions.height)));
            dimensions.x = (int) (((float) dimensions.x) * this.h);
            dimensions.y = (int) (((float) dimensions.y) * this.h);
            if (dimensions.height < 0) {
                dimensions.height = this.a.getHeight();
            }
            if (dimensions.width < 0) {
                dimensions.width = this.a.getWidth();
            }
            int[] iArr2 = new int[2];
            this.a.getLocationInWindow(iArr2);
            if (dimensions.x < 0) {
                dimensions.x = iArr2[0];
            }
            if (dimensions.y < 0) {
                dimensions.y = iArr2[1] + 0;
            }
        }
        int i = 0;
        if (str.contains("android.resource")) {
            try {
                i = raw.class.getField(str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."))).getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            str4 = "android.resource://" + this.b.getPackageName() + "/" + i;
        } else {
            str4 = str;
        }
        this.a.playVideo(str4, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
    }

    @JavascriptInterface
    public void close() {
        TapjoyLog.d("MRAID Display", String.CLOSE);
        this.a.close();
    }

    @JavascriptInterface
    public void show() {
        TapjoyLog.d("MRAID Display", "show");
        this.a.show();
    }

    public boolean isVisible() {
        return this.a.getVisibility() == 0;
    }

    public String dimensions() {
        return "{ \"top\" :" + ((int) (((float) this.a.getTop()) / this.h)) + ",\"left\" :" + ((int) (((float) this.a.getLeft()) / this.h)) + ",\"bottom\" :" + ((int) (((float) this.a.getBottom()) / this.h)) + ",\"right\" :" + ((int) (((float) this.a.getRight()) / this.h)) + "}";
    }

    public int getOrientation() {
        int i = -1;
        switch (this.c.getDefaultDisplay().getOrientation()) {
            case TwitterResponse.NONE /*0*/:
                i = 0;
                break;
            case TwitterResponse.READ /*1*/:
                i = 90;
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                i = 180;
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                i = 270;
                break;
        }
        TapjoyLog.d("MRAID Display", "getOrientation: " + i);
        return i;
    }

    public String getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        return "{ width: " + ((int) Math.ceil((double) (((float) displayMetrics.widthPixels) / displayMetrics.density))) + ", height: " + ((int) Math.ceil((double) (((float) displayMetrics.heightPixels) / displayMetrics.density))) + "}";
    }

    public String getSize() {
        return this.a.getSize();
    }

    public String getMaxSize() {
        if (this.d) {
            return "{ width: " + this.e + ", height: " + this.f + "}";
        }
        return getScreenSize();
    }

    public void setMaxSize(int i, int i2) {
        TapjoyLog.d("MRAID Display", "setMaxSize: " + i + "x" + i2);
        this.d = true;
        this.e = i;
        this.f = i2;
    }

    public void onOrientationChanged(int i) {
        String str = "window.mraidview.fireChangeEvent({ orientation: " + i + "});";
        TapjoyLog.d("MRAID Display", str);
        this.a.injectMraidJavaScript(str);
    }

    public void logHTML(String str) {
        TapjoyLog.d("MRAID Display", str);
    }

    public void stopAllListeners() {
        stopConfigurationListener();
        this.g = null;
    }

    public void stopConfigurationListener() {
        try {
            this.b.unregisterReceiver(this.g);
        } catch (Exception e) {
        }
    }

    public void startConfigurationListener() {
        try {
            if (this.g == null) {
                this.g = new ConfigBroadcastReceiver(this);
            }
            this.b.registerReceiver(this.g, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        } catch (Exception e) {
        }
    }
}
