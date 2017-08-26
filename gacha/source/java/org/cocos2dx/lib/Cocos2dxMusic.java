package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConnectCore;
import java.io.FileInputStream;

public class Cocos2dxMusic {
    private static final String TAG = Cocos2dxMusic.class.getSimpleName();
    private MediaPlayer mBackgroundMediaPlayer;
    private final Context mContext;
    private String mCurrentPath;
    private boolean mIsLoop = false;
    private float mLeftVolume;
    private boolean mManualPaused = false;
    private boolean mPaused;
    private float mRightVolume;

    public Cocos2dxMusic(Context context) {
        this.mContext = context;
        initData();
    }

    public void preloadBackgroundMusic(String str) {
        if (this.mCurrentPath == null || !this.mCurrentPath.equals(str)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
    }

    public void playBackgroundMusic(String str, boolean z) {
        if (this.mCurrentPath == null) {
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        } else if (!this.mCurrentPath.equals(str)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
        if (this.mBackgroundMediaPlayer == null) {
            Log.e(TAG, "playBackgroundMusic: background media player is null");
            return;
        }
        try {
            if (this.mPaused) {
                this.mBackgroundMediaPlayer.seekTo(0);
                this.mBackgroundMediaPlayer.start();
            } else if (this.mBackgroundMediaPlayer.isPlaying()) {
                this.mBackgroundMediaPlayer.seekTo(0);
            } else {
                this.mBackgroundMediaPlayer.start();
            }
            this.mBackgroundMediaPlayer.setLooping(z);
            this.mPaused = false;
            this.mIsLoop = z;
        } catch (Exception e) {
            Log.e(TAG, "playBackgroundMusic: error state");
        }
    }

    public void stopBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
            this.mBackgroundMediaPlayer = createMediaplayer(this.mCurrentPath);
            this.mPaused = false;
        }
    }

    public void pauseBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
            this.mBackgroundMediaPlayer.pause();
            this.mPaused = true;
            this.mManualPaused = true;
        }
    }

    public void resumeBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null && this.mPaused) {
            this.mBackgroundMediaPlayer.start();
            this.mPaused = false;
            this.mManualPaused = false;
        }
    }

    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            playBackgroundMusic(this.mCurrentPath, this.mIsLoop);
        }
    }

    public boolean isBackgroundMusicPlaying() {
        if (this.mBackgroundMediaPlayer == null) {
            return false;
        }
        return this.mBackgroundMediaPlayer.isPlaying();
    }

    public void end() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
        }
        initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public void setBackgroundVolume(float f) {
        float f2 = TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
            f2 = f3;
        }
        this.mRightVolume = f2;
        this.mLeftVolume = f2;
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void onEnterBackground() {
        if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
            this.mBackgroundMediaPlayer.pause();
            this.mPaused = true;
        }
    }

    public void onEnterForeground() {
        if (!this.mManualPaused && this.mBackgroundMediaPlayer != null && this.mPaused) {
            this.mBackgroundMediaPlayer.start();
            this.mPaused = false;
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mPaused = false;
        this.mCurrentPath = null;
    }

    private MediaPlayer createMediaplayer(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (str.startsWith(Operation.DIVISION)) {
                FileInputStream fileInputStream = new FileInputStream(str);
                mediaPlayer.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            } else {
                AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str);
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Throwable e) {
            Throwable th = e;
            Log.e(TAG, "error: " + th.getMessage(), th);
            return null;
        }
    }
}
