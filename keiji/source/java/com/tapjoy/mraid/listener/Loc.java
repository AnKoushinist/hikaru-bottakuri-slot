package com.tapjoy.mraid.listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.tapjoy.mraid.controller.MraidLocation;

public class Loc implements LocationListener {
    MraidLocation a;
    private LocationManager b;
    private String c;

    public Loc(Context context, int i, MraidLocation mraidLocation, String str) {
        this.a = mraidLocation;
        this.b = (LocationManager) context.getSystemService("location");
        this.c = str;
    }

    public void onProviderDisabled(String str) {
        this.a.fail();
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0) {
            this.a.fail();
        }
    }

    public void onLocationChanged(Location location) {
        this.a.success(location);
    }

    public void stop() {
        this.b.removeUpdates(this);
    }

    public void onProviderEnabled(String str) {
    }

    public void start() {
        this.b.requestLocationUpdates(this.c, 0, 0.0f, this);
    }
}
