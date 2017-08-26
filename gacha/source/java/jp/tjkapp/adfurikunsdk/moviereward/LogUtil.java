package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

class LogUtil {
    private static LogUtil a = null;
    private boolean b = true;

    public static synchronized LogUtil getInstance(Context context) {
        LogUtil logUtil;
        synchronized (LogUtil.class) {
            if (a == null) {
                a = new LogUtil(context.getApplicationContext());
            }
            logUtil = a;
        }
        return logUtil;
    }

    private LogUtil(Context context) {
        int testMode = FileUtil.getTestMode(context);
        if (testMode == 0) {
            this.b = true;
        } else if (testMode == 1) {
            this.b = false;
        } else {
            this.b = false;
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo.metaData != null) {
                    this.b = applicationInfo.metaData.getBoolean("adfurikun_test", false);
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    public boolean isDebugMode() {
        return this.b;
    }

    public void detail(String str, String str2) {
        if (a()) {
            Log.v(str, str2);
        }
    }

    public void detail_i(String str, String str2) {
        if (a()) {
            Log.i(str, str2);
        }
    }

    public void detail_w(String str, String str2) {
        if (a()) {
            Log.w(str, str2);
        }
    }

    public void detail_e(String str, String str2) {
        if (a()) {
            Log.e(str, str2);
        }
    }

    private boolean a() {
        if (this.b) {
        }
        return false;
    }

    public void debug(String str, String str2) {
        if (this.b) {
            Log.d(str, str2);
        }
    }

    public void debug_i(String str, String str2) {
        if (this.b) {
            Log.i(str, str2);
        }
    }

    public void debug_w(String str, String str2) {
        if (this.b) {
            Log.w(str, str2);
        }
    }

    public void debug_e(String str, String str2) {
        if (this.b) {
            Log.e(str, str2);
        }
    }

    public void debug_e(String str, Exception exception) {
        if (this.b) {
            String message = exception.getMessage();
            if (message == null) {
                message = exception.toString();
            }
            if (message != null) {
                Log.e(str, message);
            } else {
                Log.e(str, "Exception is no message!");
            }
        }
    }

    public void debug_e(String str, String str2, Exception exception) {
        if (this.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            String message = exception.getMessage();
            if (message == null) {
                message = exception.toString();
            }
            if (message != null) {
                stringBuilder.append(message);
            } else {
                stringBuilder.append("Exception is no message!");
            }
            Log.e(str, stringBuilder.toString());
        }
    }
}
