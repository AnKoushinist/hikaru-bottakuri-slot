package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import org.cocos2dx.lib.Cocos2dxHandler.DialogMessage;
import org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelperListener {
    private static final String TAG = Cocos2dxActivity.class.getSimpleName();
    private static Cocos2dxActivity sContext = null;
    private boolean hasFocus = false;
    private Cocos2dxEditBoxHelper mEditBoxHelper = null;
    protected ResizeLayout mFrameLayout = null;
    private int[] mGLContextAttrs = null;
    private Cocos2dxGLSurfaceView mGLSurfaceView = null;
    private Cocos2dxHandler mHandler = null;
    private Cocos2dxVideoHelper mVideoHelper = null;
    private Cocos2dxWebViewHelper mWebViewHelper = null;

    public class Cocos2dxEGLConfigChooser implements EGLConfigChooser {
        protected int[] configAttribs;

        class ConfigValue implements Comparable<ConfigValue> {
            public EGLConfig config = null;
            public int[] configAttribs = null;
            public int value = 0;

            private void calcValue() {
                if (this.configAttribs[4] > 0) {
                    this.value = (this.value + 536870912) + ((this.configAttribs[4] % 64) << 6);
                }
                if (this.configAttribs[5] > 0) {
                    this.value = (this.value + 268435456) + (this.configAttribs[5] % 64);
                }
                if (this.configAttribs[3] > 0) {
                    this.value = (this.value + 1073741824) + ((this.configAttribs[3] % 16) << 24);
                }
                if (this.configAttribs[1] > 0) {
                    this.value += (this.configAttribs[1] % 16) << 20;
                }
                if (this.configAttribs[2] > 0) {
                    this.value += (this.configAttribs[2] % 16) << 16;
                }
                if (this.configAttribs[0] > 0) {
                    this.value += (this.configAttribs[0] % 16) << 12;
                }
            }

            public ConfigValue(int[] iArr) {
                this.configAttribs = iArr;
                calcValue();
            }

            public ConfigValue(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
                this.config = eGLConfig;
                this.configAttribs = new int[6];
                this.configAttribs[0] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12324, 0);
                this.configAttribs[1] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12323, 0);
                this.configAttribs[2] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12322, 0);
                this.configAttribs[3] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12321, 0);
                this.configAttribs[4] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12325, 0);
                this.configAttribs[5] = Cocos2dxEGLConfigChooser.this.findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12326, 0);
                calcValue();
            }

            public int compareTo(ConfigValue configValue) {
                if (this.value < configValue.value) {
                    return -1;
                }
                if (this.value > configValue.value) {
                    return 1;
                }
                return 0;
            }

            public String toString() {
                return "{ color: " + this.configAttribs[3] + this.configAttribs[2] + this.configAttribs[1] + this.configAttribs[0] + "; depth: " + this.configAttribs[4] + "; stencil: " + this.configAttribs[5] + ";}";
            }
        }

        public Cocos2dxEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.configAttribs = new int[]{i, i2, i3, i4, i5, i6};
        }

        public Cocos2dxEGLConfigChooser(int[] iArr) {
            this.configAttribs = iArr;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            int[] iArr = new int[1];
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr)) {
                return iArr[0];
            }
            return i2;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, new int[]{12324, this.configAttribs[0], 12323, this.configAttribs[1], 12322, this.configAttribs[2], 12321, this.configAttribs[3], 12325, this.configAttribs[4], 12326, this.configAttribs[5], 12352, 4, 12344}, eGLConfigArr, 1, iArr) && iArr[0] > 0) {
                return eGLConfigArr[0];
            }
            int[] iArr2 = new int[]{12352, 4, 12344};
            if (!egl10.eglChooseConfig(eGLDisplay, iArr2, null, 0, iArr) || iArr[0] <= 0) {
                Log.e("device_policy", "Can not select an EGLConfig for rendering.");
                return null;
            }
            int i;
            int i2 = iArr[0];
            ConfigValue[] configValueArr = new ConfigValue[i2];
            eGLConfigArr = new EGLConfig[i2];
            egl10.eglChooseConfig(eGLDisplay, iArr2, eGLConfigArr, i2, iArr);
            for (i = 0; i < i2; i++) {
                configValueArr[i] = new ConfigValue(egl10, eGLDisplay, eGLConfigArr[i]);
            }
            ConfigValue configValue = new ConfigValue(this.configAttribs);
            int i3 = i2;
            i = 0;
            while (i < i3 - 1) {
                int i4 = (i + i3) / 2;
                if (configValue.compareTo(configValueArr[i4]) < 0) {
                    i3 = i4;
                } else {
                    i = i4;
                }
            }
            if (i != i2 - 1) {
                i++;
            }
            Log.w("cocos2d", "Can't find EGLConfig match: " + configValue + ", instead of closest one:" + configValueArr[i]);
            return configValueArr[i].config;
        }
    }

    private static native int[] getGLContextAttrs();

    public Cocos2dxGLSurfaceView getGLSurfaceView() {
        return this.mGLSurfaceView;
    }

    public static Context getContext() {
        return sContext;
    }

    public void setKeepScreenOn(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxActivity.this.mGLSurfaceView.setKeepScreenOn(z);
            }
        });
    }

    protected void onLoadNativeLibraries() {
        try {
            System.loadLibrary(getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("android.app.lib_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onLoadNativeLibraries();
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        Cocos2dxHelper.init(this);
        this.mGLContextAttrs = getGLContextAttrs();
        init();
        if (this.mVideoHelper == null) {
            this.mVideoHelper = new Cocos2dxVideoHelper(this, this.mFrameLayout);
        }
        if (this.mWebViewHelper == null) {
            this.mWebViewHelper = new Cocos2dxWebViewHelper(this.mFrameLayout);
        }
        if (this.mEditBoxHelper == null) {
            this.mEditBoxHelper = new Cocos2dxEditBoxHelper(this.mFrameLayout);
        }
        getWindow().setSoftInputMode(32);
    }

    protected void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        resumeIfHasFocus();
    }

    public void onWindowFocusChanged(boolean z) {
        Log.d(TAG, "onWindowFocusChanged() hasFocus=" + z);
        super.onWindowFocusChanged(z);
        this.hasFocus = z;
        resumeIfHasFocus();
    }

    private void resumeIfHasFocus() {
        if (this.hasFocus) {
            Cocos2dxHelper.onResume();
            this.mGLSurfaceView.onResume();
        }
    }

    protected void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new DialogMessage(str, str2);
        this.mHandler.sendMessage(message);
    }

    public void runOnGLThread(Runnable runnable) {
        this.mGLSurfaceView.queueEvent(runnable);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        for (OnActivityResultListener onActivityResult : Cocos2dxHelper.getOnActivityResultListeners()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void init() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.mFrameLayout = new ResizeLayout(this);
        this.mFrameLayout.setLayoutParams(layoutParams);
        layoutParams = new LayoutParams(-1, -2);
        View cocos2dxEditBox = new Cocos2dxEditBox(this);
        cocos2dxEditBox.setLayoutParams(layoutParams);
        this.mFrameLayout.addView(cocos2dxEditBox);
        this.mGLSurfaceView = onCreateView();
        this.mFrameLayout.addView(this.mGLSurfaceView);
        if (isAndroidEmulator()) {
            this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(cocos2dxEditBox);
        setContentView(this.mFrameLayout);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
        if (this.mGLContextAttrs[3] > 0) {
            cocos2dxGLSurfaceView.getHolder().setFormat(-3);
        }
        cocos2dxGLSurfaceView.setEGLConfigChooser(new Cocos2dxEGLConfigChooser(this.mGLContextAttrs));
        return cocos2dxGLSurfaceView;
    }

    private static final boolean isAndroidEmulator() {
        boolean z = false;
        Log.d(TAG, "model=" + Build.MODEL);
        String str = Build.PRODUCT;
        Log.d(TAG, "product=" + str);
        if (str != null && (str.equals("sdk") || str.contains("_sdk") || str.contains("sdk_"))) {
            z = true;
        }
        Log.d(TAG, "isEmulator=" + z);
        return z;
    }
}
