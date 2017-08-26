package com.unity3d.ads.log;

import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public class DeviceLog {
    private static boolean FORCE_DEBUG_LOG = false;
    public static final int LOGLEVEL_DEBUG = 8;
    private static final int LOGLEVEL_ERROR = 1;
    public static final int LOGLEVEL_INFO = 4;
    private static final int LOGLEVEL_WARNING = 2;
    private static boolean LOG_DEBUG = true;
    private static boolean LOG_ERROR = true;
    private static boolean LOG_INFO = true;
    private static boolean LOG_WARNING = true;
    private static final HashMap<UnityAdsLogLevel, DeviceLogLevel> _deviceLogLevel = new HashMap();

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel = new int[UnityAdsLogLevel.values().length];

        static {
            try {
                $SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.INFO.ordinal()] = DeviceLog.LOGLEVEL_ERROR;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.DEBUG.ordinal()] = DeviceLog.LOGLEVEL_WARNING;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel[UnityAdsLogLevel.ERROR.ordinal()] = DeviceLog.LOGLEVEL_INFO;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum UnityAdsLogLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    static {
        FORCE_DEBUG_LOG = false;
        if (_deviceLogLevel.size() == 0) {
            _deviceLogLevel.put(UnityAdsLogLevel.INFO, new DeviceLogLevel("i"));
            _deviceLogLevel.put(UnityAdsLogLevel.DEBUG, new DeviceLogLevel("d"));
            _deviceLogLevel.put(UnityAdsLogLevel.WARNING, new DeviceLogLevel("w"));
            _deviceLogLevel.put(UnityAdsLogLevel.ERROR, new DeviceLogLevel("e"));
        }
        if (new File("/data/local/tmp/UnityAdsForceDebugMode").exists()) {
            FORCE_DEBUG_LOG = true;
        }
    }

    public static void setLogLevel(int i) {
        if (i >= LOGLEVEL_DEBUG) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = true;
        } else if (i >= LOGLEVEL_INFO) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = false;
        } else if (i >= LOGLEVEL_WARNING) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else if (i >= LOGLEVEL_ERROR) {
            LOG_ERROR = true;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else {
            LOG_ERROR = false;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        }
    }

    public static void entered() {
        debug("ENTERED METHOD");
    }

    public static void info(String str) {
        write(UnityAdsLogLevel.INFO, checkMessage(str));
    }

    public static void info(String str, Object... objArr) {
        info(String.format(str, objArr));
    }

    public static void debug(String str) {
        if (str.length() > 3072) {
            debug(str.substring(0, 3072));
            if (str.length() < 30720) {
                debug(str.substring(3072));
                return;
            }
            return;
        }
        write(UnityAdsLogLevel.DEBUG, checkMessage(str));
    }

    public static void debug(String str, Object... objArr) {
        debug(String.format(str, objArr));
    }

    public static void warning(String str) {
        write(UnityAdsLogLevel.WARNING, checkMessage(str));
    }

    public static void warning(String str, Object... objArr) {
        warning(String.format(str, objArr));
    }

    public static void error(String str) {
        write(UnityAdsLogLevel.ERROR, checkMessage(str));
    }

    public static void exception(String str, Exception exception) {
        String str2 = BuildConfig.FLAVOR;
        if (str != null) {
            str2 = str2 + str;
        }
        if (exception != null) {
            str2 = str2 + ": " + exception.getMessage();
        }
        if (!(exception == null || exception.getCause() == null)) {
            str2 = str2 + ": " + exception.getCause().getMessage();
        }
        write(UnityAdsLogLevel.ERROR, str2);
    }

    public static void error(String str, Object... objArr) {
        error(String.format(str, objArr));
    }

    private static void write(UnityAdsLogLevel unityAdsLogLevel, String str) {
        boolean z;
        boolean z2 = true;
        switch (AnonymousClass1.$SwitchMap$com$unity3d$ads$log$DeviceLog$UnityAdsLogLevel[unityAdsLogLevel.ordinal()]) {
            case LOGLEVEL_ERROR /*1*/:
                z = LOG_INFO;
                break;
            case LOGLEVEL_WARNING /*2*/:
                z = LOG_DEBUG;
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                z = LOG_WARNING;
                break;
            case LOGLEVEL_INFO /*4*/:
                z = LOG_ERROR;
                break;
            default:
                z = true;
                break;
        }
        if (!FORCE_DEBUG_LOG) {
            z2 = z;
        }
        if (z2) {
            writeToLog(createLogEntry(unityAdsLogLevel, str));
        }
    }

    private static String checkMessage(String str) {
        if (str == null || str.length() == 0) {
            return "DO NOT USE EMPTY MESSAGES, use DeviceLog.entered() instead";
        }
        return str;
    }

    private static DeviceLogLevel getLogLevel(UnityAdsLogLevel unityAdsLogLevel) {
        return (DeviceLogLevel) _deviceLogLevel.get(unityAdsLogLevel);
    }

    private static DeviceLogEntry createLogEntry(UnityAdsLogLevel unityAdsLogLevel, String str) {
        Object obj = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        DeviceLogLevel logLevel = getLogLevel(unityAdsLogLevel);
        if (logLevel != null) {
            StackTraceElement stackTraceElement;
            int i = 0;
            while (i < stackTrace.length) {
                StackTraceElement stackTraceElement2 = stackTrace[i];
                if (stackTraceElement2.getClassName().equals(DeviceLog.class.getName())) {
                    obj = LOGLEVEL_ERROR;
                }
                if (!stackTraceElement2.getClassName().equals(DeviceLog.class.getName()) && r0 != null) {
                    break;
                }
                i += LOGLEVEL_ERROR;
            }
            if (i < stackTrace.length) {
                stackTraceElement = stackTrace[i];
            } else {
                stackTraceElement = null;
            }
            if (stackTraceElement != null) {
                return new DeviceLogEntry(logLevel, str, stackTraceElement);
            }
        }
        return null;
    }

    private static void writeToLog(DeviceLogEntry deviceLogEntry) {
        if (deviceLogEntry != null && deviceLogEntry.getLogLevel() != null) {
            Method method;
            try {
                String receivingMethodName = deviceLogEntry.getLogLevel().getReceivingMethodName();
                Class[] clsArr = new Class[LOGLEVEL_WARNING];
                clsArr[0] = String.class;
                clsArr[LOGLEVEL_ERROR] = String.class;
                method = Log.class.getMethod(receivingMethodName, clsArr);
            } catch (Throwable e) {
                Log.e(MovieReward_6001.ADNETWORK_NAME, "Writing to log failed!", e);
                method = null;
            }
            if (method != null) {
                try {
                    Object[] objArr = new Object[LOGLEVEL_WARNING];
                    objArr[0] = deviceLogEntry.getLogLevel().getLogTag();
                    objArr[LOGLEVEL_ERROR] = deviceLogEntry.getParsedMessage();
                    method.invoke(null, objArr);
                } catch (Throwable e2) {
                    Log.e(MovieReward_6001.ADNETWORK_NAME, "Writing to log failed!", e2);
                }
            }
        }
    }
}
