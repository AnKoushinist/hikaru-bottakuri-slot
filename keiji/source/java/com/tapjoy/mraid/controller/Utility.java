package com.tapjoy.mraid.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Defines.Events;
import com.tapjoy.mraid.view.MraidView;

@TargetApi(14)
public class Utility extends Abstract {
    private Assets c;
    private Display d;
    private MraidLocation e;
    private Network f;
    private MraidSensor g;

    public Utility(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.c = new Assets(mraidView, context);
        this.d = new Display(mraidView, context);
        this.e = new MraidLocation(mraidView, context);
        this.f = new Network(mraidView, context);
        this.g = new MraidSensor(mraidView, context);
        mraidView.addJavascriptInterface(this.c, "MRAIDAssetsControllerBridge");
        mraidView.addJavascriptInterface(this.d, "MRAIDDisplayControllerBridge");
        mraidView.addJavascriptInterface(this.e, "MRAIDLocationControllerBridge");
        mraidView.addJavascriptInterface(this.f, "MRAIDNetworkControllerBridge");
        mraidView.addJavascriptInterface(this.g, "MRAIDSensorControllerBridge");
    }

    public void init(float f) {
        StringBuilder append = new StringBuilder("window.mraidview.fireChangeEvent({ state: 'default', network: '").append(this.f.getNetwork()).append("', size: ").append(this.d.getSize()).append(", placement: '").append(this.a.getPlacementType()).append("', maxSize: ").append(this.d.getMaxSize()).append(",expandProperties: ").append(this.d.getMaxSize()).append(", screenSize: ").append(this.d.getScreenSize()).append(", defaultPosition: { x:").append((int) (((float) this.a.getLeft()) / f)).append(", y: ").append((int) (((float) this.a.getTop()) / f)).append(", width: ").append((int) (((float) this.a.getWidth()) / f)).append(", height: ").append((int) (((float) this.a.getHeight()) / f)).append(" }, orientation:").append(this.d.getOrientation()).append(",");
        String str = "supports: [ 'level-1', 'level-2', 'screen', 'orientation', 'network'";
        boolean z = this.e.allowLocationServices() && (this.b.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || this.b.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0);
        if (z) {
            str = str + ", 'location'";
        }
        str = ((str + ", 'video'") + ", 'audio'") + ", 'map' ]";
        TapjoyLog.d("MRAID Utility", "getSupports: " + str);
        str = append.append(str).append(",viewable:true });").toString();
        TapjoyLog.d("MRAID Utility", "init: injection: " + str);
        this.a.injectMraidJavaScript(str);
        fireReadyEvent();
        fireViewableChange(true);
    }

    public void fireReadyEvent() {
        this.a.injectMraidJavaScript("mraid.signalReady();");
    }

    public void fireViewableChange(boolean z) {
        this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({viewable:" + z + "});");
    }

    public String copyTextFromJarIntoAssetDir(String str, String str2) {
        return this.c.copyTextFromJarIntoAssetDir(str, str2);
    }

    public void setMaxSize(int i, int i2) {
        this.d.setMaxSize(i, i2);
    }

    @JavascriptInterface
    public void activate(String str) {
        TapjoyLog.d("MRAID Utility", "activate: " + str);
        if (str.equalsIgnoreCase(Events.NETWORK_CHANGE)) {
            this.f.startNetworkListener();
        } else if (this.e.allowLocationServices() && str.equalsIgnoreCase(Events.LOCATION_CHANGE)) {
            this.e.startLocationListener();
        } else if (str.equalsIgnoreCase(Events.SHAKE)) {
            this.g.startShakeListener();
        } else if (str.equalsIgnoreCase(Events.TILT_CHANGE)) {
            this.g.startTiltListener();
        } else if (str.equalsIgnoreCase(Events.HEADING_CHANGE)) {
            this.g.startHeadingListener();
        } else if (str.equalsIgnoreCase(Events.ORIENTATION_CHANGE)) {
            this.d.startConfigurationListener();
        }
    }

    @JavascriptInterface
    public void deactivate(String str) {
        TapjoyLog.d("MRAID Utility", "deactivate: " + str);
        if (str.equalsIgnoreCase(Events.NETWORK_CHANGE)) {
            this.f.stopNetworkListener();
        } else if (str.equalsIgnoreCase(Events.LOCATION_CHANGE)) {
            this.e.stopAllListeners();
        } else if (str.equalsIgnoreCase(Events.SHAKE)) {
            this.g.stopShakeListener();
        } else if (str.equalsIgnoreCase(Events.TILT_CHANGE)) {
            this.g.stopTiltListener();
        } else if (str.equalsIgnoreCase(Events.HEADING_CHANGE)) {
            this.g.stopHeadingListener();
        } else if (str.equalsIgnoreCase(Events.ORIENTATION_CHANGE)) {
            this.d.stopConfigurationListener();
        }
    }

    public void deleteOldAds() {
        this.c.deleteOldAds();
    }

    public void stopAllListeners() {
        try {
            this.c.stopAllListeners();
            this.d.stopAllListeners();
            this.e.stopAllListeners();
            this.f.stopAllListeners();
            this.g.stopAllListeners();
        } catch (Exception e) {
        }
    }

    @JavascriptInterface
    public void showAlert(String str) {
        TapjoyLog.e("MRAID Utility", str);
    }
}
