package org.cocos2dx.lib;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.Cocos2dxVideoView.OnVideoEventListener;

public class Cocos2dxVideoHelper {
    static final int KeyEventBack = 1000;
    private static final int VideoTaskCreate = 0;
    private static final int VideoTaskFullScreen = 12;
    private static final int VideoTaskKeepRatio = 11;
    private static final int VideoTaskPause = 5;
    private static final int VideoTaskRemove = 1;
    private static final int VideoTaskRestart = 10;
    private static final int VideoTaskResume = 6;
    private static final int VideoTaskSeek = 8;
    private static final int VideoTaskSetRect = 3;
    private static final int VideoTaskSetSource = 2;
    private static final int VideoTaskSetVisible = 9;
    private static final int VideoTaskStart = 4;
    private static final int VideoTaskStop = 7;
    static VideoHandler mVideoHandler = null;
    private static int videoTag = VideoTaskCreate;
    private Cocos2dxActivity mActivity = null;
    private FrameLayout mLayout = null;
    private SparseArray<Cocos2dxVideoView> sVideoViews = null;
    OnVideoEventListener videoEventListener = new OnVideoEventListener() {
        public void onVideoEvent(int i, int i2) {
            Cocos2dxVideoHelper.this.mActivity.runOnGLThread(new VideoEventRunnable(i, i2));
        }
    };

    private class VideoEventRunnable implements Runnable {
        private int mVideoEvent;
        private int mVideoTag;

        public VideoEventRunnable(int i, int i2) {
            this.mVideoTag = i;
            this.mVideoEvent = i2;
        }

        public void run() {
            Cocos2dxVideoHelper.nativeExecuteVideoCallback(this.mVideoTag, this.mVideoEvent);
        }
    }

    static class VideoHandler extends Handler {
        WeakReference<Cocos2dxVideoHelper> mReference;

        VideoHandler(Cocos2dxVideoHelper cocos2dxVideoHelper) {
            this.mReference = new WeakReference(cocos2dxVideoHelper);
        }

        public void handleMessage(Message message) {
            Cocos2dxVideoHelper cocos2dxVideoHelper;
            switch (message.what) {
                case Cocos2dxVideoHelper.VideoTaskCreate /*0*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._createVideoView(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskRemove /*1*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._removeVideoView(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskSetSource /*2*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._setVideoURL(message.arg1, message.arg2, (String) message.obj);
                    break;
                case Cocos2dxVideoHelper.VideoTaskSetRect /*3*/:
                    Rect rect = (Rect) message.obj;
                    ((Cocos2dxVideoHelper) this.mReference.get())._setVideoRect(message.arg1, rect.left, rect.top, rect.right, rect.bottom);
                    break;
                case Cocos2dxVideoHelper.VideoTaskStart /*4*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._startVideo(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskPause /*5*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._pauseVideo(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskResume /*6*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._resumeVideo(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskStop /*7*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._stopVideo(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskSeek /*8*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._seekVideoTo(message.arg1, message.arg2);
                    break;
                case Cocos2dxVideoHelper.VideoTaskSetVisible /*9*/:
                    cocos2dxVideoHelper = (Cocos2dxVideoHelper) this.mReference.get();
                    if (message.arg2 != Cocos2dxVideoHelper.VideoTaskRemove) {
                        cocos2dxVideoHelper._setVideoVisible(message.arg1, false);
                        break;
                    } else {
                        cocos2dxVideoHelper._setVideoVisible(message.arg1, true);
                        break;
                    }
                case Cocos2dxVideoHelper.VideoTaskRestart /*10*/:
                    ((Cocos2dxVideoHelper) this.mReference.get())._restartVideo(message.arg1);
                    break;
                case Cocos2dxVideoHelper.VideoTaskKeepRatio /*11*/:
                    cocos2dxVideoHelper = (Cocos2dxVideoHelper) this.mReference.get();
                    if (message.arg2 != Cocos2dxVideoHelper.VideoTaskRemove) {
                        cocos2dxVideoHelper._setVideoKeepRatio(message.arg1, false);
                        break;
                    } else {
                        cocos2dxVideoHelper._setVideoKeepRatio(message.arg1, true);
                        break;
                    }
                case Cocos2dxVideoHelper.VideoTaskFullScreen /*12*/:
                    cocos2dxVideoHelper = (Cocos2dxVideoHelper) this.mReference.get();
                    Rect rect2 = (Rect) message.obj;
                    if (message.arg2 != Cocos2dxVideoHelper.VideoTaskRemove) {
                        cocos2dxVideoHelper._setFullScreenEnabled(message.arg1, false, rect2.right, rect2.bottom);
                        break;
                    } else {
                        cocos2dxVideoHelper._setFullScreenEnabled(message.arg1, true, rect2.right, rect2.bottom);
                        break;
                    }
                case Cocos2dxVideoHelper.KeyEventBack /*1000*/:
                    ((Cocos2dxVideoHelper) this.mReference.get()).onBackKeyEvent();
                    break;
            }
            super.handleMessage(message);
        }
    }

    public static native void nativeExecuteVideoCallback(int i, int i2);

    Cocos2dxVideoHelper(Cocos2dxActivity cocos2dxActivity, FrameLayout frameLayout) {
        this.mActivity = cocos2dxActivity;
        this.mLayout = frameLayout;
        mVideoHandler = new VideoHandler(this);
        this.sVideoViews = new SparseArray();
    }

    public static int createVideoWidget() {
        Message message = new Message();
        message.what = VideoTaskCreate;
        message.arg1 = videoTag;
        mVideoHandler.sendMessage(message);
        int i = videoTag;
        videoTag = i + VideoTaskRemove;
        return i;
    }

    private void _createVideoView(int i) {
        View cocos2dxVideoView = new Cocos2dxVideoView(this.mActivity, i);
        this.sVideoViews.put(i, cocos2dxVideoView);
        this.mLayout.addView(cocos2dxVideoView, new LayoutParams(-2, -2));
        cocos2dxVideoView.setZOrderOnTop(true);
        cocos2dxVideoView.setOnCompletionListener(this.videoEventListener);
    }

    public static void removeVideoWidget(int i) {
        Message message = new Message();
        message.what = VideoTaskRemove;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _removeVideoView(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.stopPlayback();
            this.sVideoViews.remove(i);
            this.mLayout.removeView(cocos2dxVideoView);
        }
    }

    public static void setVideoUrl(int i, int i2, String str) {
        Message message = new Message();
        message.what = VideoTaskSetSource;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = str;
        mVideoHandler.sendMessage(message);
    }

    private void _setVideoURL(int i, int i2, String str) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            switch (i2) {
                case VideoTaskCreate /*0*/:
                    cocos2dxVideoView.setVideoFileName(str);
                    return;
                case VideoTaskRemove /*1*/:
                    cocos2dxVideoView.setVideoURL(str);
                    return;
                default:
                    return;
            }
        }
    }

    public static void setVideoRect(int i, int i2, int i3, int i4, int i5) {
        Message message = new Message();
        message.what = VideoTaskSetRect;
        message.arg1 = i;
        message.obj = new Rect(i2, i3, i4, i5);
        mVideoHandler.sendMessage(message);
    }

    private void _setVideoRect(int i, int i2, int i3, int i4, int i5) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.setVideoRect(i2, i3, i4, i5);
        }
    }

    public static void setFullScreenEnabled(int i, boolean z, int i2, int i3) {
        Message message = new Message();
        message.what = VideoTaskFullScreen;
        message.arg1 = i;
        if (z) {
            message.arg2 = VideoTaskRemove;
        } else {
            message.arg2 = VideoTaskCreate;
        }
        message.obj = new Rect(VideoTaskCreate, VideoTaskCreate, i2, i3);
        mVideoHandler.sendMessage(message);
    }

    private void _setFullScreenEnabled(int i, boolean z, int i2, int i3) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.setFullScreenEnabled(z, i2, i3);
        }
    }

    private void onBackKeyEvent() {
        int size = this.sVideoViews.size();
        for (int i = VideoTaskCreate; i < size; i += VideoTaskRemove) {
            int keyAt = this.sVideoViews.keyAt(i);
            Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(keyAt);
            if (cocos2dxVideoView != null) {
                cocos2dxVideoView.setFullScreenEnabled(false, VideoTaskCreate, VideoTaskCreate);
                this.mActivity.runOnGLThread(new VideoEventRunnable(keyAt, KeyEventBack));
            }
        }
    }

    public static void startVideo(int i) {
        Message message = new Message();
        message.what = VideoTaskStart;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _startVideo(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.start();
        }
    }

    public static void pauseVideo(int i) {
        Message message = new Message();
        message.what = VideoTaskPause;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _pauseVideo(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.pause();
        }
    }

    public static void resumeVideo(int i) {
        Message message = new Message();
        message.what = VideoTaskResume;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _resumeVideo(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.resume();
        }
    }

    public static void stopVideo(int i) {
        Message message = new Message();
        message.what = VideoTaskStop;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _stopVideo(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.stop();
        }
    }

    public static void restartVideo(int i) {
        Message message = new Message();
        message.what = VideoTaskRestart;
        message.arg1 = i;
        mVideoHandler.sendMessage(message);
    }

    private void _restartVideo(int i) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.restart();
        }
    }

    public static void seekVideoTo(int i, int i2) {
        Message message = new Message();
        message.what = VideoTaskSeek;
        message.arg1 = i;
        message.arg2 = i2;
        mVideoHandler.sendMessage(message);
    }

    private void _seekVideoTo(int i, int i2) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.seekTo(i2);
        }
    }

    public static void setVideoVisible(int i, boolean z) {
        Message message = new Message();
        message.what = VideoTaskSetVisible;
        message.arg1 = i;
        if (z) {
            message.arg2 = VideoTaskRemove;
        } else {
            message.arg2 = VideoTaskCreate;
        }
        mVideoHandler.sendMessage(message);
    }

    private void _setVideoVisible(int i, boolean z) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView == null) {
            return;
        }
        if (z) {
            cocos2dxVideoView.fixSize();
            cocos2dxVideoView.setVisibility(VideoTaskCreate);
            return;
        }
        cocos2dxVideoView.setVisibility(VideoTaskStart);
    }

    public static void setVideoKeepRatioEnabled(int i, boolean z) {
        Message message = new Message();
        message.what = VideoTaskKeepRatio;
        message.arg1 = i;
        if (z) {
            message.arg2 = VideoTaskRemove;
        } else {
            message.arg2 = VideoTaskCreate;
        }
        mVideoHandler.sendMessage(message);
    }

    private void _setVideoKeepRatio(int i, boolean z) {
        Cocos2dxVideoView cocos2dxVideoView = (Cocos2dxVideoView) this.sVideoViews.get(i);
        if (cocos2dxVideoView != null) {
            cocos2dxVideoView.setKeepRatio(z);
        }
    }
}
