package com.onesignal;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.a;
import com.google.android.gms.common.api.GoogleApiClient.b;
import com.google.android.gms.common.api.GoogleApiClient.c;
import com.google.android.gms.location.e;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class LocationGMS {
    private static Thread fallbackFailThread;
    private static boolean locationCoarse;
    private static LocationHandler locationHandler;
    private static GoogleApiClientCompatProxy mGoogleApiClient;
    static String requestPermission;

    private static class GoogleApiClientListener implements b, c {
        private GoogleApiClientListener() {
        }

        public void onConnected(Bundle bundle) {
            int i = 0;
            PermissionsActivity.answered = false;
            Location a = e.b.a(LocationGMS.mGoogleApiClient.realInstance());
            if (a != null) {
                a.getAccuracy();
                Double valueOf = Double.valueOf(new BigDecimal(a.getLatitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
                Double valueOf2 = Double.valueOf(new BigDecimal(a.getLongitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
                Float valueOf3 = Float.valueOf(a.getAccuracy());
                if (!LocationGMS.locationCoarse) {
                    i = 1;
                }
                LocationGMS.fireComplete(valueOf, valueOf2, valueOf3, Integer.valueOf(i));
            } else {
                LocationGMS.fireComplete(null, null, null, null);
            }
            LocationGMS.mGoogleApiClient.disconnect();
        }

        public void onConnectionSuspended(int i) {
            LocationGMS.fireFailedComplete();
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            LocationGMS.fireFailedComplete();
        }
    }

    interface LocationHandler {
        void complete(Double d, Double d2, Float f, Integer num);
    }

    static void getLocation(Context context, boolean z, LocationHandler locationHandler) {
        int i = -1;
        locationHandler = locationHandler;
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        if (checkSelfPermission == -1) {
            i = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
            locationCoarse = true;
        }
        if (VERSION.SDK_INT < 23) {
            if (checkSelfPermission == 0 || i == 0) {
                startGetLocation();
            } else {
                locationHandler.complete(null, null, null, null);
            }
        } else if (checkSelfPermission != 0) {
            try {
                List asList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                if (asList.contains("android.permission.ACCESS_FINE_LOCATION")) {
                    requestPermission = "android.permission.ACCESS_FINE_LOCATION";
                } else if (asList.contains("android.permission.ACCESS_COARSE_LOCATION") && i != 0) {
                    requestPermission = "android.permission.ACCESS_COARSE_LOCATION";
                }
                if (requestPermission != null && z) {
                    PermissionsActivity.startPrompt();
                } else if (i == 0) {
                    startGetLocation();
                } else {
                    fireFailedComplete();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            startGetLocation();
        }
    }

    static void startGetLocation() {
        if (fallbackFailThread == null) {
            try {
                startFallBackThread();
                GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
                mGoogleApiClient = new GoogleApiClientCompatProxy(new a(OneSignal.appContext).a(e.a).a(googleApiClientListener).a(googleApiClientListener).b());
                mGoogleApiClient.connect();
            } catch (Throwable th) {
                OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but there was an error initializing: ", th);
                fireFailedComplete();
            }
        }
    }

    private static void startFallBackThread() {
        fallbackFailThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(30000);
                    OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                    LocationGMS.fireFailedComplete();
                } catch (Throwable th) {
                }
            }
        });
        fallbackFailThread.start();
    }

    static void fireFailedComplete() {
        PermissionsActivity.answered = false;
        fireComplete(null, null, null, null);
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    private static void fireComplete(Double d, Double d2, Float f, Integer num) {
        locationHandler.complete(d, d2, f, num);
        if (!(fallbackFailThread == null || Thread.currentThread().equals(fallbackFailThread))) {
            fallbackFailThread.interrupt();
        }
        fallbackFailThread = null;
    }
}
