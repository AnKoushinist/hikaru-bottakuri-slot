package jp.reifrontier.silentlogsdkandroid.data.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.a;
import jp.reifrontier.silentlogsdkandroid.data.location.RFLLocationManager.UseProvider;

public class LooperThread extends Thread {
    private static final double ALTITUDE_NOISE = 10.0d;
    private static final double COORDINATE_NOISE = 3.596313778377164E-5d;
    private static final double DEG_TO_METER = 111225.0d;
    private static final double METER_TO_DEG = 8.99078444594291E-6d;
    private static final int THREAD_PRIORITY = 5;
    private static final double TIME_STEP = 1.0d;
    private Tracker1D mAltitudeTracker;
    private final Handler mClientHandler;
    private final LocationListener mClientLocationListener;
    private final Context mContext;
    private final boolean mForwardProviderUpdates;
    private Location mLastLocation;
    private Tracker1D mLatitudeTracker;
    private final LocationManager mLocationManager;
    private Tracker1D mLongitudeTracker;
    private Looper mLooper;
    private final long mMinTimeFilter;
    private final long mMinTimeGpsProvider;
    private final long mMinTimeNetProvider;
    private Handler mOwnHandler;
    private Callback mOwnHandlerCallback = new Callback() {
        public boolean handleMessage(Message message) {
            final Location location = new Location(RFLLocationManager.RFL_KALMAN);
            LooperThread.this.mLatitudeTracker.predict(0.0d);
            location.setLatitude(LooperThread.this.mLatitudeTracker.getPosition());
            LooperThread.this.mLongitudeTracker.predict(0.0d);
            location.setLongitude(LooperThread.this.mLongitudeTracker.getPosition());
            if (LooperThread.this.mLastLocation.hasAltitude()) {
                LooperThread.this.mAltitudeTracker.predict(0.0d);
                location.setAltitude(LooperThread.this.mAltitudeTracker.getPosition());
            }
            if (LooperThread.this.mLastLocation.hasSpeed()) {
                location.setSpeed(LooperThread.this.mLastLocation.getSpeed());
            }
            if (LooperThread.this.mLastLocation.hasBearing()) {
                location.setBearing(LooperThread.this.mLastLocation.getBearing());
            }
            location.setAccuracy((float) (LooperThread.this.mLatitudeTracker.getAccuracy() * LooperThread.DEG_TO_METER));
            location.setTime(System.currentTimeMillis());
            if (VERSION.SDK_INT >= 17) {
                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            }
            LooperThread.this.mClientHandler.post(new Runnable() {
                public void run() {
                    LooperThread.this.mClientLocationListener.onLocationChanged(location);
                }
            });
            LooperThread.this.mOwnHandler.removeMessages(0);
            LooperThread.this.mOwnHandler.sendEmptyMessageDelayed(0, LooperThread.this.mMinTimeFilter);
            LooperThread.this.mPredicted = true;
            return true;
        }
    };
    private LocationListener mOwnLocationListener = new LocationListener() {
        public void onLocationChanged(final Location location) {
            double accuracy = (double) location.getAccuracy();
            double latitude = location.getLatitude();
            double d = accuracy * LooperThread.METER_TO_DEG;
            if (LooperThread.this.mLatitudeTracker == null) {
                LooperThread.this.mLatitudeTracker = new Tracker1D(LooperThread.TIME_STEP, LooperThread.COORDINATE_NOISE);
                LooperThread.this.mLatitudeTracker.setState(latitude, 0.0d, d);
            }
            if (!LooperThread.this.mPredicted) {
                LooperThread.this.mLatitudeTracker.predict(0.0d);
            }
            LooperThread.this.mLatitudeTracker.update(latitude, d);
            latitude = location.getLongitude();
            d = (Math.cos(Math.toRadians(location.getLatitude())) * accuracy) * LooperThread.METER_TO_DEG;
            if (LooperThread.this.mLongitudeTracker == null) {
                LooperThread.this.mLongitudeTracker = new Tracker1D(LooperThread.TIME_STEP, LooperThread.COORDINATE_NOISE);
                LooperThread.this.mLongitudeTracker.setState(latitude, 0.0d, d);
            }
            if (!LooperThread.this.mPredicted) {
                LooperThread.this.mLongitudeTracker.predict(0.0d);
            }
            LooperThread.this.mLongitudeTracker.update(latitude, d);
            if (location.hasAltitude()) {
                latitude = location.getAltitude();
                if (LooperThread.this.mAltitudeTracker == null) {
                    LooperThread.this.mAltitudeTracker = new Tracker1D(LooperThread.TIME_STEP, LooperThread.ALTITUDE_NOISE);
                    LooperThread.this.mAltitudeTracker.setState(latitude, 0.0d, accuracy);
                }
                if (!LooperThread.this.mPredicted) {
                    LooperThread.this.mAltitudeTracker.predict(0.0d);
                }
                LooperThread.this.mAltitudeTracker.update(latitude, accuracy);
            }
            LooperThread.this.mPredicted = false;
            if (LooperThread.this.mForwardProviderUpdates) {
                LooperThread.this.mClientHandler.post(new Runnable() {
                    public void run() {
                        LooperThread.this.mClientLocationListener.onLocationChanged(new Location(location));
                    }
                });
            }
            if (location.getProvider().equals("gps") || LooperThread.this.mLastLocation == null || LooperThread.this.mLastLocation.getProvider().equals("network")) {
                LooperThread.this.mLastLocation = new Location(location);
            }
            if (LooperThread.this.mOwnHandler == null) {
                LooperThread.this.mOwnHandler = new Handler(LooperThread.this.mLooper, LooperThread.this.mOwnHandlerCallback);
                LooperThread.this.mOwnHandler.sendEmptyMessageDelayed(0, LooperThread.this.mMinTimeFilter);
            }
        }

        public void onStatusChanged(final String str, final int i, final Bundle bundle) {
            LooperThread.this.mClientHandler.post(new Runnable() {
                public void run() {
                    LooperThread.this.mClientLocationListener.onStatusChanged(str, i, bundle);
                }
            });
        }

        public void onProviderEnabled(final String str) {
            LooperThread.this.mClientHandler.post(new Runnable() {
                public void run() {
                    LooperThread.this.mClientLocationListener.onProviderEnabled(str);
                }
            });
        }

        public void onProviderDisabled(final String str) {
            LooperThread.this.mClientHandler.post(new Runnable() {
                public void run() {
                    LooperThread.this.mClientLocationListener.onProviderDisabled(str);
                }
            });
        }
    };
    private boolean mPredicted;
    private final UseProvider mUseProvider;

    LooperThread(Context context, UseProvider useProvider, long j, long j2, long j3, LocationListener locationListener, boolean z) {
        this.mContext = context;
        this.mClientHandler = new Handler();
        this.mLocationManager = (LocationManager) this.mContext.getSystemService("location");
        this.mUseProvider = useProvider;
        this.mMinTimeFilter = j;
        this.mMinTimeGpsProvider = j2;
        this.mMinTimeNetProvider = j3;
        this.mClientLocationListener = locationListener;
        this.mForwardProviderUpdates = z;
        start();
    }

    public void run() {
        if (a.a(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || a.a(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            setPriority(THREAD_PRIORITY);
            Looper.prepare();
            this.mLooper = Looper.myLooper();
            if (this.mUseProvider == UseProvider.GPS || this.mUseProvider == UseProvider.GPS_AND_NET) {
                this.mLocationManager.requestLocationUpdates("gps", this.mMinTimeGpsProvider, 10.0f, this.mOwnLocationListener, this.mLooper);
            }
            if (this.mUseProvider == UseProvider.NET || this.mUseProvider == UseProvider.GPS_AND_NET) {
                this.mLocationManager.requestLocationUpdates("network", this.mMinTimeNetProvider, 10.0f, this.mOwnLocationListener, this.mLooper);
            }
            Looper.loop();
        }
    }

    public void close() {
        if (a.a(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || a.a(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mLocationManager.removeUpdates(this.mOwnLocationListener);
            this.mLooper.quit();
        }
    }
}
