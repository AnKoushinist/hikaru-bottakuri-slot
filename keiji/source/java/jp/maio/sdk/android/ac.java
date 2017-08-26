package jp.maio.sdk.android;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

final class ac implements LocationListener {
    ac() {
    }

    public void onLocationChanged(Location location) {
        aa.j = location.getLatitude();
        aa.k = location.getLongitude();
        bc.a("SDK API Message", "Location get lat:" + aa.j + " lng:" + aa.k, null);
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
