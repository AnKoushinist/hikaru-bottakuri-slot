package com.glossomads.Logger;

import com.glossomads.SugarUtil;
import org.cocos2dx.lib.BuildConfig;

public class a {

    public enum a {
        zoneReady,
        zoneNotReady,
        videoStart,
        videoPause,
        videoResume,
        videoFinish,
        videoReplay,
        endcarodClick,
        interstitial,
        interstitialFailed,
        feed,
        feedFailed,
        reward,
        rewardFailed,
        queueIsNow,
        sugarLibraryVersion,
        configureError,
        configureEmptyZoneIdError,
        configureOptionFormatError,
        configureOverlapError,
        configureInvalidZoneId,
        configureLimitZoneIdError,
        configureDuplicateZoneIdError,
        configureZoneIdLengthError,
        configurePermissionError,
        showInterstitialVideo,
        showInterstitialVideoError,
        showFeedVideo,
        showFeedVideoError,
        showRewardVideo,
        showRewardVideoError,
        loadAdStart,
        loadAdFinish,
        loadAdFailed,
        loadAdRequestParams,
        loadAdPaused,
        loadAdResumed,
        assetAddQueue,
        assetAddQueueFailed,
        assetDownloadStart,
        assetDownloaded,
        assetDownloadFailed,
        eventSend,
        eventSendFailed,
        connectionType,
        getIfa,
        getIfaTracking,
        getIfaFailed,
        setCustomIdFailed,
        loadStoreFailed,
        minSdkVersionError,
        configureActivityIsNull,
        setTestDeviceInfo,
        setTestDeviceWarning,
        advertisingIdIsNull,
        configureKindleFire
    }

    public enum b {
        debug,
        error,
        warning,
        info
    }

    public static void a(a aVar, String... strArr) {
        a(b.warning, aVar, strArr);
        b(b.warning, aVar, strArr);
    }

    public static void b(a aVar, String... strArr) {
        a(b.error, aVar, strArr);
        b(b.error, aVar, strArr);
    }

    public static void c(a aVar, String... strArr) {
        a(b.info, aVar, strArr);
        b(b.info, aVar, strArr);
    }

    public static void d(a aVar, String... strArr) {
        a(b.warning, aVar, strArr, true);
    }

    public static void e(a aVar, String... strArr) {
        a(b.error, aVar, strArr, true);
    }

    public static void f(a aVar, String... strArr) {
        a(b.info, aVar, strArr, true);
    }

    public static void a(b bVar, a aVar, String... strArr) {
        a(bVar, aVar, strArr, true);
    }

    public static void g(a aVar, String... strArr) {
        a(b.debug, aVar, strArr, false);
    }

    public static void b(b bVar, a aVar, String... strArr) {
        a(bVar, aVar, strArr, false);
    }

    private static void a(b bVar, a aVar, String[] strArr, boolean z) {
        String str = BuildConfig.FLAVOR;
        if (aVar.equals(a.zoneReady)) {
            if (z) {
                str = "zone " + a(strArr, 0) + " is ready";
            } else {
                str = a(strArr, 1);
                if (SugarUtil.isEmptyValue(str)) {
                    str = "empty adIds";
                }
                str = "zone " + a(strArr, 0) + " is ready (impid = " + str + ")";
            }
        } else if (aVar.equals(a.zoneNotReady)) {
            if (z) {
                str = "zone " + a(strArr, 0) + " is not ready";
            } else {
                str = a(strArr, 1);
                if (SugarUtil.isEmptyValue(str)) {
                    str = "empty adIds";
                }
                str = "zone " + a(strArr, 0) + " is not ready (impid = " + str + ")";
            }
        } else if (aVar.equals(a.videoStart)) {
            str = z ? "video start " + a(strArr) : "video start " + b(strArr);
        } else if (aVar.equals(a.videoPause)) {
            str = z ? "video pause " + a(strArr) : "video pause " + b(strArr);
        } else if (aVar.equals(a.videoResume)) {
            str = z ? "video resume " + a(strArr) : "video resume " + b(strArr);
        } else if (aVar.equals(a.videoFinish)) {
            str = z ? "video finish " + a(strArr) : "video finish " + b(strArr);
        } else if (aVar.equals(a.videoReplay)) {
            str = z ? "video replay " + a(strArr) : "video replay " + b(strArr);
        } else if (aVar.equals(a.endcarodClick)) {
            if (!z) {
                str = "click " + e(strArr);
            }
        } else if (aVar.equals(a.interstitial)) {
            str = z ? "interstitial " + a(strArr) : "interstitial " + b(strArr);
        } else if (aVar.equals(a.interstitialFailed)) {
            str = z ? "interstitial failed " + c(a(strArr, 0), a(strArr, 2)) : "interstitial failed " + d(strArr);
        } else if (aVar.equals(a.feed)) {
            str = z ? "feed " + a(strArr) : "feed " + b(strArr);
        } else if (aVar.equals(a.feedFailed)) {
            str = z ? "feed failed " + c(a(strArr, 0), a(strArr, 2)) : "feed failed " + d(strArr);
        } else if (aVar.equals(a.reward)) {
            str = z ? "reward " + a(strArr) : "reward " + b(strArr);
        } else if (aVar.equals(a.rewardFailed)) {
            str = z ? "reward failed " + c(a(strArr, 0), a(strArr, 2)) : "reward failed " + d(strArr);
        } else if (aVar.equals(a.queueIsNow)) {
            if (!z) {
                str = "queue is now(zoneid = " + a(strArr, 0) + ", impids = [" + a(strArr, 1) + "] -> [" + a(strArr, 2) + "])";
            }
        } else if (aVar.equals(a.sugarLibraryVersion)) {
            if (z) {
                str = "GlossomAds library version: " + a(strArr, 0) + " configure (zoneids = " + a(strArr, 1) + ")";
            }
        } else if (aVar.equals(a.configureError)) {
            if (z) {
                str = "configure error " + c(a(strArr, 0), a(strArr, 1));
            }
        } else if (aVar.equals(a.configureEmptyZoneIdError)) {
            if (z) {
                str = "configure error (reason = \"zoneid is null\")";
            }
        } else if (aVar.equals(a.configureOptionFormatError)) {
            if (z) {
                str = "configure error (reason = \"client option format mistakes key = " + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.configureOverlapError)) {
            if (z) {
                str = "configure error (reason = \"configure should be called just once\")";
            }
        } else if (aVar.equals(a.configureInvalidZoneId)) {
            if (z) {
                str = "invalid zoneid " + a(strArr, 0);
            }
        } else if (aVar.equals(a.configureLimitZoneIdError)) {
            if (z) {
                str = "configure error (reason = \"too many zones\")";
            }
        } else if (aVar.equals(a.configureDuplicateZoneIdError)) {
            if (z) {
                str = "configure error (reason = zone id is duplicate, zone id = \"" + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.configureZoneIdLengthError)) {
            if (z) {
                str = "configure error (reason = zone id is too long, zone id = \"" + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.configurePermissionError)) {
            if (z) {
                str = "configure error (reason = \"necessary permission :" + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.showInterstitialVideo)) {
            str = z ? "show interstitial video " + a(strArr) : "show interstitial video " + b(strArr);
        } else if (aVar.equals(a.showInterstitialVideoError)) {
            str = z ? "show interstitial video error " + c(a(strArr, 0), a(strArr, 2)) : "show interstitial video error " + d(strArr);
        } else if (aVar.equals(a.showFeedVideo)) {
            str = z ? "show feed video " + a(strArr) : "show feed video " + b(strArr);
        } else if (aVar.equals(a.showFeedVideoError)) {
            str = z ? "show feed video error " + c(a(strArr, 0), a(strArr, 2)) : "show feed video error " + d(strArr);
        } else if (aVar.equals(a.showRewardVideo)) {
            str = z ? "show reward video " + a(strArr) : "show reward video " + b(strArr);
        } else if (aVar.equals(a.showRewardVideoError)) {
            str = z ? "show reward video error " + c(a(strArr, 0), a(strArr, 2)) : "show reward video error " + d(strArr);
        } else if (aVar.equals(a.loadAdStart)) {
            if (!z) {
                str = "load ad start (zoneid = " + a(strArr, 0) + ", next load interval = " + a(strArr, 1) + ", url = " + a(strArr, 2) + ")";
            }
        } else if (aVar.equals(a.loadAdFinish)) {
            if (!z) {
                str = "load ad finish (zoneid = " + a(strArr, 0) + ", url = " + a(strArr, 1) + ")";
            }
        } else if (aVar.equals(a.loadAdFailed)) {
            if (!z) {
                str = "load ad failed " + c(strArr);
            }
        } else if (aVar.equals(a.loadAdRequestParams)) {
            if (!z) {
                str = "request params " + a(strArr, 0);
            }
        } else if (aVar.equals(a.loadAdPaused)) {
            if (!z) {
                str = "load ad paused " + a(strArr);
            }
        } else if (aVar.equals(a.loadAdResumed)) {
            if (!z) {
                str = "load ad resumed " + a(strArr);
            }
        } else if (aVar.equals(a.assetAddQueue)) {
            if (!z) {
                str = "add asset queue " + e(strArr);
            }
        } else if (aVar.equals(a.assetAddQueueFailed)) {
            if (!z) {
                str = "failed to asset queue " + g(strArr);
            }
        } else if (aVar.equals(a.assetDownloadStart)) {
            if (!z) {
                str = "asset download start " + f(strArr);
            }
        } else if (aVar.equals(a.assetDownloaded)) {
            if (!z) {
                str = "asset was downloaded " + f(strArr);
            }
        } else if (aVar.equals(a.assetDownloadFailed)) {
            if (!z) {
                str = "failed to download asset " + f(strArr);
            }
        } else if (aVar.equals(a.eventSend)) {
            if (!z) {
                str = "send event " + e(strArr);
            }
        } else if (aVar.equals(a.eventSendFailed)) {
            if (!z) {
                str = "failed to send event " + g(strArr);
            }
        } else if (aVar.equals(a.connectionType)) {
            if (!z) {
                str = "connection type = " + a(strArr, 0);
            }
        } else if (aVar.equals(a.getIfa)) {
            if (z) {
                str = "ifa = " + a(strArr, 0);
            }
        } else if (aVar.equals(a.getIfaTracking)) {
            if (z) {
                str = "ifa = " + a(strArr, 0) + " (no tracking)";
            }
        } else if (aVar.equals(a.getIfaFailed)) {
            if (z) {
                str = "ifa failed (reason = \"" + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.setCustomIdFailed)) {
            if (z) {
                str = "setCustomID failed (reason = \"customId is " + a(strArr, 0) + "\")";
            }
        } else if (aVar.equals(a.loadStoreFailed)) {
            str = z ? "load store failed (zoneid = " + a(strArr, 0) + ", id= " + a(strArr, 2) + ")" : "load store failed (zoneid = " + a(strArr, 0) + ", impid = " + a(strArr, 1) + ", id= " + a(strArr, 2) + ", reason = " + a(strArr, 3) + ")";
        } else if (aVar.equals(a.configureKindleFire)) {
            if (z) {
                str = "configure error (reason = \"kindle fire device isn't permitted.\")";
            }
        } else if (aVar.equals(a.minSdkVersionError)) {
            if (z) {
                str = "GlossomAds requires API version 16 or higher";
            }
        } else if (aVar.equals(a.configureActivityIsNull)) {
            str = "configure error (reason = activity is null)";
        } else if (aVar.equals(a.setTestDeviceInfo)) {
            str = "your device id is " + a(strArr, 0) + " . If you use testMode, please call GlossomAds.addTestDevice.";
        } else if (aVar.equals(a.setTestDeviceWarning)) {
            str = "please call GlossomAds.addTestDevice before configure";
        } else if (aVar.equals(a.advertisingIdIsNull)) {
            str = "your device id is null.";
        }
        if (z) {
            if (SugarUtil.isNotEmptyValue(str)) {
                a(bVar, str);
            }
        } else if (SugarUtil.isNotEmptyValue(str)) {
            b(bVar, str);
        }
    }

    public static void a(b bVar, String str) {
        if (bVar.equals(b.debug)) {
            SugarLogger.d(str);
        } else if (bVar.equals(b.warning)) {
            SugarLogger.w(str);
        } else if (bVar.equals(b.error)) {
            SugarLogger.e(str);
        } else if (bVar.equals(b.info)) {
            SugarLogger.i(str);
        }
    }

    public static void b(b bVar, String str) {
        if (bVar.equals(b.debug)) {
            SugarDebugLogger.d(str);
        } else if (bVar.equals(b.warning)) {
            SugarDebugLogger.w(str);
        } else if (bVar.equals(b.error)) {
            SugarDebugLogger.e(str);
        } else if (bVar.equals(b.info)) {
            SugarDebugLogger.i(str);
        }
    }

    private static String a(String[] strArr, int i) {
        if (strArr == null || strArr.length < i + 1) {
            return BuildConfig.FLAVOR;
        }
        String str = strArr[i];
        if (SugarUtil.isNotEmptyValue(str)) {
            str = str.replaceAll("\\[", BuildConfig.FLAVOR).replaceAll("\\]", BuildConfig.FLAVOR);
        }
        return SugarUtil.isEmptyValue(str) ? BuildConfig.FLAVOR : str;
    }

    private static String a(String[] strArr) {
        return "(zoneid = " + a(strArr, 0) + ")";
    }

    private static String b(String[] strArr) {
        return "(zoneid = " + a(strArr, 0) + ", impid = " + a(strArr, 1) + ")";
    }

    private static String c(String[] strArr) {
        return c(a(strArr, 0), a(strArr, 1));
    }

    private static String c(String str, String str2) {
        return "(zoneid = " + str + ", reason = " + str2 + ")";
    }

    private static String d(String[] strArr) {
        return "(zoneid = " + a(strArr, 0) + ", impid = " + a(strArr, 1) + ", reason = " + a(strArr, 2) + ")";
    }

    private static String e(String[] strArr) {
        return "(url = " + a(strArr, 0) + ")";
    }

    private static String f(String[] strArr) {
        return "(url = " + a(strArr, 0) + ", zoneid = " + a(strArr, 1) + ")";
    }

    private static String g(String[] strArr) {
        return "(url = " + a(strArr, 0) + ", reason = " + a(strArr, 1) + ")";
    }

    public static void a(String str, String str2) {
        a(b(str2), str, BuildConfig.FLAVOR, "Ads are not ready for zone");
    }

    public static void b(String str, String str2) {
        a(b(str2), str, BuildConfig.FLAVOR, "your other ad is already showing.");
    }

    public static void a(String str, String str2, String str3) {
        a(b(str3), str, str2, "our other ad is already showing.");
    }

    public static a a(String str) {
        if ("interstitial".equals(str)) {
            return a.showInterstitialVideo;
        }
        if ("v4vc".equals(str)) {
            return a.showRewardVideo;
        }
        return a.showFeedVideo;
    }

    public static a b(String str) {
        if ("interstitial".equals(str)) {
            return a.showInterstitialVideoError;
        }
        if ("v4vc".equals(str)) {
            return a.showRewardVideoError;
        }
        return a.showFeedVideoError;
    }
}
