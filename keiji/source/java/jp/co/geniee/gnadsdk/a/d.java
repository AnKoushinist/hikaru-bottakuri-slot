package jp.co.geniee.gnadsdk.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.tapjoy.TapjoyConstants;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: GNSLocation */
public class d implements LocationListener {
    public String a = BuildConfig.FLAVOR;
    public String b = BuildConfig.FLAVOR;

    public d(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        String packageName = context.getApplicationContext().getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0 || packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0) {
            locationManager.requestLocationUpdates("network", 0, 0.0f, this);
            Location lastKnownLocation = locationManager.getLastKnownLocation("network");
            if (lastKnownLocation != null) {
                onLocationChanged(lastKnownLocation);
            }
            locationManager.requestLocationUpdates("network", TapjoyConstants.SESSION_ID_INACTIVITY_TIME, 100.0f, this);
        }
    }

    public void onLocationChanged(Location location) {
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        if (valueOf != null && valueOf2 != null && valueOf.length() > 0 && valueOf2.length() > 0) {
            if (!valueOf.equals(this.a) || !valueOf2.equals(this.b)) {
                this.a = valueOf;
                this.b = valueOf2;
            }
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }
}
