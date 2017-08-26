package com.unity3d.ads.api;

import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.device.DeviceError;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import jp.co.geniee.gnsrewardadapter.BuildConfig;
import org.json.JSONArray;
import twitter4j.TwitterResponse;

public class DeviceInfo {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType = new int[StorageType.values().length];

        static {
            try {
                $SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType[StorageType.INTERNAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType[StorageType.EXTERNAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum StorageType {
        EXTERNAL,
        INTERNAL
    }

    @WebViewExposed
    public static void getAndroidId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getAndroidId());
    }

    @WebViewExposed
    public static void getAdvertisingTrackingId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getAdvertisingTrackingId());
    }

    @WebViewExposed
    public static void getLimitAdTrackingFlag(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Boolean.valueOf(Device.isLimitAdTrackingEnabled()));
    }

    @WebViewExposed
    public static void getApiLevel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getApiLevel()));
    }

    @WebViewExposed
    public static void getOsVersion(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getOsVersion());
    }

    @WebViewExposed
    public static void getManufacturer(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getManufacturer());
    }

    @WebViewExposed
    public static void getModel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getModel());
    }

    @WebViewExposed
    public static void getScreenLayout(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getScreenLayout()));
    }

    @WebViewExposed
    public static void getScreenDensity(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getScreenDensity()));
    }

    @WebViewExposed
    public static void getScreenWidth(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getScreenWidth()));
    }

    @WebViewExposed
    public static void getScreenHeight(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getScreenHeight()));
    }

    @WebViewExposed
    public static void getTimeZone(Boolean bool, WebViewCallback webViewCallback) {
        webViewCallback.invoke(TimeZone.getDefault().getDisplayName(bool.booleanValue(), 0, Locale.US));
    }

    @WebViewExposed
    public static void getConnectionType(WebViewCallback webViewCallback) {
        String str;
        if (Device.isUsingWifi()) {
            str = TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
        } else if (Device.isActiveNetworkConnected()) {
            str = "cellular";
        } else {
            str = "none";
        }
        webViewCallback.invoke(str);
    }

    @WebViewExposed
    public static void getNetworkType(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getNetworkType()));
    }

    @WebViewExposed
    public static void getNetworkOperator(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkOperator());
    }

    @WebViewExposed
    public static void getNetworkOperatorName(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getNetworkOperatorName());
    }

    @WebViewExposed
    public static void isAppInstalled(String str, WebViewCallback webViewCallback) {
        webViewCallback.invoke(Boolean.valueOf(Device.isAppInstalled(str)));
    }

    @WebViewExposed
    public static void isRooted(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Boolean.valueOf(Device.isRooted()));
    }

    @WebViewExposed
    public static void getInstalledPackages(boolean z, WebViewCallback webViewCallback) {
        JSONArray jSONArray = new JSONArray(Device.getInstalledPackages(z));
        webViewCallback.invoke(jSONArray);
    }

    @WebViewExposed
    public static void getUniqueEventId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getUniqueEventId());
    }

    @WebViewExposed
    public static void getHeadset(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Boolean.valueOf(Device.isWiredHeadsetOn()));
    }

    @WebViewExposed
    public static void getSystemProperty(String str, String str2, WebViewCallback webViewCallback) {
        webViewCallback.invoke(Device.getSystemProperty(str, str2));
    }

    @WebViewExposed
    public static void getRingerMode(WebViewCallback webViewCallback) {
        int ringerMode = Device.getRingerMode();
        if (ringerMode > -1) {
            webViewCallback.invoke(Integer.valueOf(ringerMode));
            return;
        }
        switch (ringerMode) {
            case -2:
                webViewCallback.error(DeviceError.AUDIOMANAGER_NULL, Integer.valueOf(ringerMode));
                return;
            case BuildConfig.VERSION_CODE /*-1*/:
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, Integer.valueOf(ringerMode));
                return;
            default:
                DeviceLog.error("Unhandled ringerMode error: " + ringerMode);
                return;
        }
    }

    @WebViewExposed
    public static void getSystemLanguage(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Locale.getDefault().toString());
    }

    @WebViewExposed
    public static void getDeviceVolume(Integer num, WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getStreamVolume(num.intValue())));
    }

    @WebViewExposed
    public static void getScreenBrightness(WebViewCallback webViewCallback) {
        int screenBrightness = Device.getScreenBrightness();
        if (screenBrightness > -1) {
            webViewCallback.invoke(Integer.valueOf(screenBrightness));
            return;
        }
        switch (screenBrightness) {
            case BuildConfig.VERSION_CODE /*-1*/:
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, Integer.valueOf(screenBrightness));
                return;
            default:
                DeviceLog.error("Unhandled screenBrightness error: " + screenBrightness);
                return;
        }
    }

    private static StorageType getStorageTypeFromString(String str) {
        try {
            return StorageType.valueOf(str);
        } catch (Exception e) {
            DeviceLog.exception("Illegal argument: " + str, e);
            return null;
        }
    }

    private static File getFileForStorageType(StorageType storageType) {
        switch (AnonymousClass1.$SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType[storageType.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return ClientProperties.getApplicationContext().getCacheDir();
            case TwitterResponse.READ_WRITE /*2*/:
                return ClientProperties.getApplicationContext().getExternalCacheDir();
            default:
                DeviceLog.error("Unhandled storagetype: " + storageType);
                return null;
        }
    }

    @WebViewExposed
    public static void getFreeSpace(String str, WebViewCallback webViewCallback) {
        StorageType storageTypeFromString = getStorageTypeFromString(str);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, str);
            return;
        }
        if (Device.getFreeSpace(getFileForStorageType(storageTypeFromString)) > -1) {
            webViewCallback.invoke(Long.valueOf(Device.getFreeSpace(getFileForStorageType(storageTypeFromString))));
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, Long.valueOf(r0));
    }

    @WebViewExposed
    public static void getTotalSpace(String str, WebViewCallback webViewCallback) {
        StorageType storageTypeFromString = getStorageTypeFromString(str);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, str);
            return;
        }
        if (Device.getTotalSpace(getFileForStorageType(storageTypeFromString)) > -1) {
            webViewCallback.invoke(Long.valueOf(Device.getTotalSpace(getFileForStorageType(storageTypeFromString))));
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, Long.valueOf(r0));
    }

    @WebViewExposed
    public static void getBatteryLevel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Float.valueOf(Device.getBatteryLevel()));
    }

    @WebViewExposed
    public static void getBatteryStatus(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Integer.valueOf(Device.getBatteryStatus()));
    }

    @WebViewExposed
    public static void getFreeMemory(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Long.valueOf(Device.getFreeMemory()));
    }

    @WebViewExposed
    public static void getTotalMemory(WebViewCallback webViewCallback) {
        webViewCallback.invoke(Long.valueOf(Device.getTotalMemory()));
    }
}
