package com.onesignal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;

class AndroidSupportV4Compat {

    static class ActivityCompat {
        static void requestPermissions(Activity activity, String[] strArr, int i) {
            ActivityCompatApi23.requestPermissions(activity, strArr, i);
        }
    }

    @TargetApi(23)
    static class ActivityCompatApi23 {
        static void requestPermissions(Activity activity, String[] strArr, int i) {
            if (activity instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i);
            }
            activity.requestPermissions(strArr, i);
        }
    }

    static class ContextCompat {
        static int checkSelfPermission(Context context, String str) {
            try {
                return context.checkPermission(str, Process.myPid(), Process.myUid());
            } catch (Throwable th) {
                Log.e("OneSignal", "checkSelfPermission failed, returning PERMISSION_DENIED");
                return -1;
            }
        }

        static int getColor(Context context, int i) {
            if (VERSION.SDK_INT > 22) {
                return context.getColor(i);
            }
            return context.getResources().getColor(i);
        }
    }

    interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }
}
