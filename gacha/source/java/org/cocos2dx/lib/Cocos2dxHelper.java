package org.cocos2dx.lib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.os.Vibrator;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.c.a.a.a;
import com.tapjoy.TapjoyConnectCore;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class Cocos2dxHelper {
    private static final int BOOST_TIME = 7;
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";
    private static final int RUNNABLES_PER_FRAME = 5;
    private static ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Cocos2dxHelper.mGameServiceBinder = a.a(iBinder);
            Cocos2dxHelper.fastLoading(Cocos2dxHelper.BOOST_TIME);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Cocos2dxHelper.sActivity.getApplicationContext().unbindService(Cocos2dxHelper.connection);
        }
    };
    private static com.c.a.a mGameServiceBinder = null;
    private static Set<OnActivityResultListener> onActivityResultListeners = new LinkedHashSet();
    private static boolean sAccelerometerEnabled;
    private static Activity sActivity = null;
    private static boolean sActivityVisible;
    private static AssetManager sAssetManager;
    private static Cocos2dxMusic sCocos2dMusic;
    private static Cocos2dxSound sCocos2dSound;
    private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
    private static Cocos2dxHelperListener sCocos2dxHelperListener;
    private static String sFileDirectory;
    private static boolean sInited = false;
    private static String sPackageName;
    private static Vibrator sVibrateService = null;

    public interface Cocos2dxHelperListener {
        void runOnGLThread(Runnable runnable);

        void showDialog(String str, String str2);
    }

    private static native void nativeSetApkPath(String str);

    private static native void nativeSetContext(Context context, AssetManager assetManager);

    private static native void nativeSetEditTextDialogResult(byte[] bArr);

    public static void runOnGLThread(Runnable runnable) {
        ((Cocos2dxActivity) sActivity).runOnGLThread(runnable);
    }

    public static void init(Activity activity) {
        sActivity = activity;
        sCocos2dxHelperListener = (Cocos2dxHelperListener) activity;
        if (!sInited) {
            ApplicationInfo applicationInfo = activity.getApplicationInfo();
            sPackageName = applicationInfo.packageName;
            sFileDirectory = activity.getFilesDir().getAbsolutePath();
            nativeSetApkPath(applicationInfo.sourceDir);
            sCocos2dxAccelerometer = new Cocos2dxAccelerometer(activity);
            sCocos2dMusic = new Cocos2dxMusic(activity);
            sCocos2dSound = new Cocos2dxSound(activity);
            sAssetManager = activity.getAssets();
            nativeSetContext(activity, sAssetManager);
            Cocos2dxBitmap.setContext(activity);
            sVibrateService = (Vibrator) activity.getSystemService("vibrator");
            sInited = true;
            Intent intent = new Intent(com.c.a.a.class.getName());
            intent.setPackage("com.enhance.gameservice");
            activity.getApplicationContext().bindService(intent, connection, 1);
        }
    }

    public static Activity getActivity() {
        return sActivity;
    }

    public static void addOnActivityResultListener(OnActivityResultListener onActivityResultListener) {
        onActivityResultListeners.add(onActivityResultListener);
    }

    public static Set<OnActivityResultListener> getOnActivityResultListeners() {
        return onActivityResultListeners;
    }

    public static boolean isActivityVisible() {
        return sActivityVisible;
    }

    public static String getCocos2dxPackageName() {
        return sPackageName;
    }

    public static String getCocos2dxWritablePath() {
        return sFileDirectory;
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static AssetManager getAssetManager() {
        return sAssetManager;
    }

    public static void enableAccelerometer() {
        sAccelerometerEnabled = true;
        sCocos2dxAccelerometer.enable();
    }

    public static void setAccelerometerInterval(float f) {
        sCocos2dxAccelerometer.setInterval(f);
    }

    public static void disableAccelerometer() {
        sAccelerometerEnabled = false;
        sCocos2dxAccelerometer.disable();
    }

    public static void setKeepScreenOn(boolean z) {
        ((Cocos2dxActivity) sActivity).setKeepScreenOn(z);
    }

    public static void vibrate(float f) {
        sVibrateService.vibrate((long) (1000.0f * f));
    }

    public static String getVersion() {
        try {
            return Cocos2dxActivity.getContext().getPackageManager().getPackageInfo(Cocos2dxActivity.getContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            return BuildConfig.FLAVOR;
        }
    }

    public static boolean openURL(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            sActivity.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void preloadBackgroundMusic(String str) {
        sCocos2dMusic.preloadBackgroundMusic(str);
    }

    public static void playBackgroundMusic(String str, boolean z) {
        sCocos2dMusic.playBackgroundMusic(str, z);
    }

    public static void resumeBackgroundMusic() {
        sCocos2dMusic.resumeBackgroundMusic();
    }

    public static void pauseBackgroundMusic() {
        sCocos2dMusic.pauseBackgroundMusic();
    }

    public static void stopBackgroundMusic() {
        sCocos2dMusic.stopBackgroundMusic();
    }

    public static void rewindBackgroundMusic() {
        sCocos2dMusic.rewindBackgroundMusic();
    }

    public static boolean isBackgroundMusicPlaying() {
        return sCocos2dMusic.isBackgroundMusicPlaying();
    }

    public static float getBackgroundMusicVolume() {
        return sCocos2dMusic.getBackgroundVolume();
    }

    public static void setBackgroundMusicVolume(float f) {
        sCocos2dMusic.setBackgroundVolume(f);
    }

    public static void preloadEffect(String str) {
        sCocos2dSound.preloadEffect(str);
    }

    public static int playEffect(String str, boolean z, float f, float f2, float f3) {
        return sCocos2dSound.playEffect(str, z, f, f2, f3);
    }

    public static void resumeEffect(int i) {
        sCocos2dSound.resumeEffect(i);
    }

    public static void pauseEffect(int i) {
        sCocos2dSound.pauseEffect(i);
    }

    public static void stopEffect(int i) {
        sCocos2dSound.stopEffect(i);
    }

    public static float getEffectsVolume() {
        return sCocos2dSound.getEffectsVolume();
    }

    public static void setEffectsVolume(float f) {
        sCocos2dSound.setEffectsVolume(f);
    }

    public static void unloadEffect(String str) {
        sCocos2dSound.unloadEffect(str);
    }

    public static void pauseAllEffects() {
        sCocos2dSound.pauseAllEffects();
    }

    public static void resumeAllEffects() {
        sCocos2dSound.resumeAllEffects();
    }

    public static void stopAllEffects() {
        sCocos2dSound.stopAllEffects();
    }

    public static void end() {
        sCocos2dMusic.end();
        sCocos2dSound.end();
    }

    public static void onResume() {
        sActivityVisible = true;
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.enable();
        }
    }

    public static void onPause() {
        sActivityVisible = false;
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.disable();
        }
    }

    public static void onEnterBackground() {
        sCocos2dSound.onEnterBackground();
        sCocos2dMusic.onEnterBackground();
    }

    public static void onEnterForeground() {
        sCocos2dSound.onEnterForeground();
        sCocos2dMusic.onEnterForeground();
    }

    public static void terminateProcess() {
        Process.killProcess(Process.myPid());
    }

    private static void showDialog(String str, String str2) {
        sCocos2dxHelperListener.showDialog(str, str2);
    }

    public static void setEditTextDialogResult(String str) {
        try {
            final byte[] bytes = str.getBytes("UTF8");
            sCocos2dxHelperListener.runOnGLThread(new Runnable() {
                public void run() {
                    Cocos2dxHelper.nativeSetEditTextDialogResult(bytes);
                }
            });
        } catch (UnsupportedEncodingException e) {
        }
    }

    public static int getDPI() {
        if (sActivity != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = sActivity.getWindowManager();
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                if (defaultDisplay != null) {
                    defaultDisplay.getMetrics(displayMetrics);
                    return (int) (displayMetrics.density * 160.0f);
                }
            }
        }
        return -1;
    }

    public static boolean getBoolForKey(String str, boolean z) {
        boolean z2 = true;
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getBoolean(str, z);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Boolean.parseBoolean(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue() != 0;
            } else {
                if (!(obj instanceof Float)) {
                    return false;
                }
                if (((Float) obj).floatValue() == 0.0f) {
                    z2 = false;
                }
                return z2;
            }
        }
    }

    public static int getIntegerForKey(String str, int i) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getInt(str, i);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Integer.parseInt(obj.toString());
            }
            if (obj instanceof Float) {
                return ((Float) obj).intValue();
            }
            return ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) ? 1 : 0;
        }
    }

    public static float getFloatForKey(String str, float f) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getFloat(str, f);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Float.parseFloat(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).floatValue();
            }
            if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                return TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
            }
            return 0.0f;
        }
    }

    public static double getDoubleForKey(String str, double d) {
        return (double) getFloatForKey(str, (float) d);
    }

    public static String getStringForKey(String str, String str2) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getString(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return sharedPreferences.getAll().get(str).toString();
        }
    }

    public static void setBoolForKey(String str, boolean z) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static void setIntegerForKey(String str, int i) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void setFloatForKey(String str, float f) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    public static void setDoubleForKey(String str, double d) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putFloat(str, (float) d);
        edit.commit();
    }

    public static void setStringForKey(String str, String str2) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void deleteValueForKey(String str) {
        Editor edit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public static byte[] conversionEncoding(byte[] bArr, String str, String str2) {
        try {
            return new String(bArr, str).getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int setResolutionPercent(int i) {
        int i2 = -1;
        try {
            if (mGameServiceBinder != null) {
                i2 = mGameServiceBinder.a(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }

    public static int setFPS(int i) {
        int i2 = -1;
        try {
            if (mGameServiceBinder != null) {
                i2 = mGameServiceBinder.b(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }

    public static int fastLoading(int i) {
        int i2 = -1;
        try {
            if (mGameServiceBinder != null) {
                i2 = mGameServiceBinder.c(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }

    public static int getTemperature() {
        int i = -1;
        try {
            if (mGameServiceBinder != null) {
                i = mGameServiceBinder.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int setLowPowerMode(boolean z) {
        int i = -1;
        try {
            if (mGameServiceBinder != null) {
                i = mGameServiceBinder.a(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
