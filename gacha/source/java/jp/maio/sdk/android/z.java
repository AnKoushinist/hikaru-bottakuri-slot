package jp.maio.sdk.android;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

final class z implements LocationListener {
    z() {
    }

    public void onLocationChanged(Location location) {
        x.j = location.getLatitude();
        x.k = location.getLongitude();
        ax.a("SDK API Message", "Location get lat:" + x.j + " lng:" + x.k, null);
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
