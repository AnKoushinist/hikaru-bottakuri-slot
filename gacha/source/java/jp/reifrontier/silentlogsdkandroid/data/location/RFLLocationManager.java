package jp.reifrontier.silentlogsdkandroid.data.location;

import android.content.Context;
import android.location.LocationListener;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class RFLLocationManager {
    public static final String RFL_KALMAN = "rfl_kalman";
    private static final String TAG = RFLLocationManager.class.getSimpleName();
    private final Context mContext;
    private final Map<LocationListener, LooperThread> mListener2Thread = new HashMap();

    public enum UseProvider {
        GPS,
        NET,
        GPS_AND_NET
    }

    public RFLLocationManager(Context context) {
        this.mContext = context;
    }

    public void requestLocationUpdates(UseProvider useProvider, long j, long j2, long j3, LocationListener locationListener, boolean z) {
        if (useProvider == null) {
            throw new IllegalArgumentException("useProvider can't be null");
        } else if (locationListener == null) {
            throw new IllegalArgumentException("listener can't be null");
        } else {
            long j4;
            long j5;
            long j6;
            if (j < 0) {
                Log.w(TAG, "minTimeFilter < 0. Setting to 0");
                j4 = 0;
            } else {
                j4 = j;
            }
            if (j2 < 0) {
                Log.w(TAG, "minTimeGpsProvider < 0. Setting to 0");
                j5 = 0;
            } else {
                j5 = j2;
            }
            if (j3 < 0) {
                Log.w(TAG, "minTimeNetProvider < 0. Setting to 0");
                j6 = 0;
            } else {
                j6 = j3;
            }
            if (this.mListener2Thread.containsKey(locationListener)) {
                Log.d(TAG, "Requested location updates with a listener that is already in use. Removing.");
                removeUpdates(locationListener);
            }
            this.mListener2Thread.put(locationListener, new LooperThread(this.mContext, useProvider, j4, j5, j6, locationListener, z));
        }
    }

    public void removeUpdates(LocationListener locationListener) {
        LooperThread looperThread = (LooperThread) this.mListener2Thread.remove(locationListener);
        if (looperThread == null) {
            Log.d(TAG, "Did not remove updates for given LocationListener. Wasn't registered in this instance.");
        } else {
            looperThread.close();
        }
    }
}
