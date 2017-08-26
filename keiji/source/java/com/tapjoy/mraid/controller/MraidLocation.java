package com.tapjoy.mraid.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.listener.Loc;
import com.tapjoy.mraid.view.MraidView;
import org.cocos2dx.lib.GameControllerDelegate;

public class MraidLocation extends Abstract {
    final int c = GameControllerDelegate.THUMBSTICK_LEFT_X;
    private LocationManager d;
    private boolean e = false;
    private Loc f;
    private Loc g;
    private int h;
    private boolean i = false;

    public MraidLocation(MraidView mraidView, Context context) {
        super(mraidView, context);
        try {
            this.d = (LocationManager) context.getSystemService("location");
            if (!TapjoyConnectCore.isUnitTestMode()) {
                if (this.d.getProvider("gps") != null) {
                    this.f = new Loc(context, GameControllerDelegate.THUMBSTICK_LEFT_X, this, "gps");
                }
                if (this.d.getProvider("network") != null) {
                    this.g = new Loc(context, GameControllerDelegate.THUMBSTICK_LEFT_X, this, "network");
                }
                this.e = true;
            }
        } catch (SecurityException e) {
        }
    }

    public void allowLocationServices(boolean z) {
        this.i = z;
    }

    public boolean allowLocationServices() {
        return this.i;
    }

    private static String a(Location location) {
        return "{ lat: " + location.getLatitude() + ", lon: " + location.getLongitude() + ", acc: " + location.getAccuracy() + "}";
    }

    public String getLocation() {
        TapjoyLog.d("MRAID Location", "getLocation: hasPermission: " + this.e);
        if (!this.e) {
            return null;
        }
        Location location = null;
        for (String lastKnownLocation : this.d.getProviders(true)) {
            location = this.d.getLastKnownLocation(lastKnownLocation);
            if (location != null) {
                break;
            }
        }
        TapjoyLog.d("MRAID Location", "getLocation: " + location);
        if (location != null) {
            return a(location);
        }
        return null;
    }

    public void startLocationListener() {
        if (this.h == 0) {
            if (this.g != null) {
                this.g.start();
            }
            if (this.f != null) {
                this.f.start();
            }
        }
        this.h++;
    }

    public void stopLocationListener() {
        this.h--;
        if (this.h == 0) {
            if (this.g != null) {
                this.g.stop();
            }
            if (this.f != null) {
                this.f.stop();
            }
        }
    }

    public void success(Location location) {
        String str = "window.mraidview.fireChangeEvent({ location: " + a(location) + "})";
        TapjoyLog.d("MRAID Location", str);
        this.a.injectMraidJavaScript(str);
    }

    public void fail() {
        TapjoyLog.e("MRAID Location", "Location can't be determined");
        this.a.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"Location cannot be identified\", \"OrmmaLocationController\")");
    }

    public void stopAllListeners() {
        this.h = 0;
        try {
            this.f.stop();
        } catch (Exception e) {
        }
        try {
            this.g.stop();
        } catch (Exception e2) {
        }
    }
}
