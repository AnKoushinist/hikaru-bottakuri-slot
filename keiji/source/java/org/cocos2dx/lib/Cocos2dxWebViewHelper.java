package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Cocos2dxWebViewHelper {
    private static final String TAG = Cocos2dxWebViewHelper.class.getSimpleName();
    private static Cocos2dxActivity sCocos2dxActivity;
    private static Handler sHandler;
    private static FrameLayout sLayout;
    private static int viewTag = 0;
    private static SparseArray<Cocos2dxWebView> webViews;

    private static native void didFailLoading(int i, String str);

    private static native void didFinishLoading(int i, String str);

    private static native void onJsCallback(int i, String str);

    private static native boolean shouldStartLoading(int i, String str);

    public Cocos2dxWebViewHelper(FrameLayout frameLayout) {
        sLayout = frameLayout;
        sHandler = new Handler(Looper.myLooper());
        sCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        webViews = new SparseArray();
    }

    public static boolean _shouldStartLoading(int i, String str) {
        return !shouldStartLoading(i, str);
    }

    public static void _didFinishLoading(int i, String str) {
        didFinishLoading(i, str);
    }

    public static void _didFailLoading(int i, String str) {
        didFailLoading(i, str);
    }

    public static void _onJsCallback(int i, String str) {
        onJsCallback(i, str);
    }

    public static int createWebView() {
        final int i = viewTag;
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                View cocos2dxWebView = new Cocos2dxWebView(Cocos2dxWebViewHelper.sCocos2dxActivity, i);
                Cocos2dxWebViewHelper.sLayout.addView(cocos2dxWebView, new LayoutParams(-2, -2));
                Cocos2dxWebViewHelper.webViews.put(i, cocos2dxWebView);
            }
        });
        i = viewTag;
        viewTag = i + 1;
        return i;
    }

    public static void removeWebView(final int i) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    Cocos2dxWebViewHelper.webViews.remove(i);
                    Cocos2dxWebViewHelper.sLayout.removeView(cocos2dxWebView);
                }
            }
        });
    }

    public static void setVisible(final int i, final boolean z) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.setVisibility(z ? 0 : 8);
                }
            }
        });
    }

    public static void setWebViewRect(int i, int i2, int i3, int i4, int i5) {
        final int i6 = i;
        final int i7 = i2;
        final int i8 = i3;
        final int i9 = i4;
        final int i10 = i5;
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i6);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.setWebViewRect(i7, i8, i9, i10);
                }
            }
        });
    }

    public static void setJavascriptInterfaceScheme(final int i, final String str) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.setJavascriptInterfaceScheme(str);
                }
            }
        });
    }

    public static void loadData(int i, String str, String str2, String str3, String str4) {
        final int i2 = i;
        final String str5 = str4;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i2);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.loadDataWithBaseURL(str5, str6, str7, str8, null);
                }
            }
        });
    }

    public static void loadHTMLString(final int i, final String str, final String str2) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.loadDataWithBaseURL(str2, str, null, null, null);
                }
            }
        });
    }

    public static void loadUrl(final int i, final String str) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.loadUrl(str);
                }
            }
        });
    }

    public static void loadFile(final int i, final String str) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.loadUrl(str);
                }
            }
        });
    }

    public static void stopLoading(final int i) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.stopLoading();
                }
            }
        });
    }

    public static void reload(final int i) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.reload();
                }
            }
        });
    }

    public static <T> T callInMainThread(Callable<T> callable) {
        Object futureTask = new FutureTask(callable);
        sHandler.post(futureTask);
        return futureTask.get();
    }

    public static boolean canGoBack(final int i) {
        try {
            return ((Boolean) callInMainThread(new Callable<Boolean>() {
                public Boolean call() {
                    Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                    boolean z = cocos2dxWebView != null && cocos2dxWebView.canGoBack();
                    return Boolean.valueOf(z);
                }
            })).booleanValue();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e2) {
            return false;
        }
    }

    public static boolean canGoForward(final int i) {
        try {
            return ((Boolean) callInMainThread(new Callable<Boolean>() {
                public Boolean call() {
                    Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                    boolean z = cocos2dxWebView != null && cocos2dxWebView.canGoForward();
                    return Boolean.valueOf(z);
                }
            })).booleanValue();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e2) {
            return false;
        }
    }

    public static void goBack(final int i) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.goBack();
                }
            }
        });
    }

    public static void goForward(final int i) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.goForward();
                }
            }
        });
    }

    public static void evaluateJS(final int i, final String str) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.loadUrl("javascript:" + str);
                }
            }
        });
    }

    public static void setScalesPageToFit(final int i, final boolean z) {
        sCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i);
                if (cocos2dxWebView != null) {
                    cocos2dxWebView.setScalesPageToFit(z);
                }
            }
        });
    }
}
