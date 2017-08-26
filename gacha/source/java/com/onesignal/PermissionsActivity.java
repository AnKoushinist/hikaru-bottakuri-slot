package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;

public class PermissionsActivity extends Activity {
    private static ActivityAvailableListener activityAvailableListener;
    static boolean answered;
    static boolean waiting;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null || !bundle.getBoolean("android:hasCurrentPermissionsRequest", false)) {
            requestPermission();
        } else {
            waiting = true;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (OneSignal.initDone) {
            requestPermission();
        }
    }

    private void requestPermission() {
        if (VERSION.SDK_INT < 23) {
            finish();
        } else if (!waiting) {
            waiting = true;
            ActivityCompat.requestPermissions(this, new String[]{LocationGMS.requestPermission}, 2);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        answered = true;
        waiting = false;
        if (i == 2) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                LocationGMS.fireFailedComplete();
            } else {
                LocationGMS.startGetLocation();
            }
        }
        ActivityLifecycleHandler.removeActivityAvailableListener(activityAvailableListener);
        finish();
    }

    static void startPrompt() {
        if (!waiting && !answered) {
            activityAvailableListener = new ActivityAvailableListener() {
                public void available(Activity activity) {
                    if (!activity.getClass().equals(PermissionsActivity.class)) {
                        Intent intent = new Intent(activity, PermissionsActivity.class);
                        intent.setFlags(131072);
                        activity.startActivity(intent);
                    }
                }
            };
            ActivityLifecycleHandler.setActivityAvailableListener(activityAvailableListener);
        }
    }
}
