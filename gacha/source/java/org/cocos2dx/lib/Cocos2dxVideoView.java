package org.cocos2dx.lib;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.MediaController.MediaPlayerControl;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.Map;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import twitter4j.HttpResponseCode;

public class Cocos2dxVideoView extends SurfaceView implements MediaPlayerControl {
    private static final String AssetResourceRoot = "assets/";
    private static final int EVENT_COMPLETED = 3;
    private static final int EVENT_PAUSED = 1;
    private static final int EVENT_PLAYING = 0;
    private static final int EVENT_STOPPED = 2;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private String TAG = "Cocos2dxVideoView";
    private OnBufferingUpdateListener mBufferingUpdateListener = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            Cocos2dxVideoView.this.mCurrentBufferPercentage = i;
        }
    };
    protected Cocos2dxActivity mCocos2dxActivity = null;
    private OnCompletionListener mCompletionListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            Cocos2dxVideoView.this.mCurrentState = Cocos2dxVideoView.STATE_PLAYBACK_COMPLETED;
            Cocos2dxVideoView.this.mTargetState = Cocos2dxVideoView.STATE_PLAYBACK_COMPLETED;
            Cocos2dxVideoView.this.release(true);
            if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, Cocos2dxVideoView.STATE_PLAYING);
            }
        }
    };
    private int mCurrentBufferPercentage;
    private int mCurrentState = STATE_IDLE;
    private int mDuration;
    private OnErrorListener mErrorListener = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Log.d(Cocos2dxVideoView.this.TAG, "Error: " + i + "," + i2);
            Cocos2dxVideoView.this.mCurrentState = Cocos2dxVideoView.STATE_ERROR;
            Cocos2dxVideoView.this.mTargetState = Cocos2dxVideoView.STATE_ERROR;
            if ((Cocos2dxVideoView.this.mOnErrorListener == null || !Cocos2dxVideoView.this.mOnErrorListener.onError(Cocos2dxVideoView.this.mMediaPlayer, i, i2)) && Cocos2dxVideoView.this.getWindowToken() != null) {
                int identifier;
                Resources resources = Cocos2dxVideoView.this.mCocos2dxActivity.getResources();
                if (i == HttpResponseCode.OK) {
                    identifier = resources.getIdentifier("VideoView_error_text_invalid_progressive_playback", "string", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
                } else {
                    identifier = resources.getIdentifier("VideoView_error_text_unknown", "string", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
                }
                new Builder(Cocos2dxVideoView.this.mCocos2dxActivity).setTitle(resources.getString(resources.getIdentifier("VideoView_error_title", "string", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND))).setMessage(identifier).setPositiveButton(resources.getString(resources.getIdentifier("VideoView_error_button", "string", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND)), new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                            Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, Cocos2dxVideoView.STATE_PLAYING);
                        }
                    }
                }).setCancelable(false).show();
            }
            return true;
        }
    };
    protected boolean mFullScreenEnabled = false;
    protected int mFullScreenHeight = STATE_IDLE;
    protected int mFullScreenWidth = STATE_IDLE;
    private boolean mIsAssetRouse = false;
    private boolean mKeepRatio = false;
    private MediaPlayer mMediaPlayer = null;
    private boolean mNeedResume = false;
    private OnErrorListener mOnErrorListener;
    private OnPreparedListener mOnPreparedListener;
    private OnVideoEventListener mOnVideoEventListener;
    OnPreparedListener mPreparedListener = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            Cocos2dxVideoView.this.mCurrentState = Cocos2dxVideoView.STATE_PREPARED;
            if (Cocos2dxVideoView.this.mOnPreparedListener != null) {
                Cocos2dxVideoView.this.mOnPreparedListener.onPrepared(Cocos2dxVideoView.this.mMediaPlayer);
            }
            Cocos2dxVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
            Cocos2dxVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
            int access$500 = Cocos2dxVideoView.this.mSeekWhenPrepared;
            if (access$500 != 0) {
                Cocos2dxVideoView.this.seekTo(access$500);
            }
            if (!(Cocos2dxVideoView.this.mVideoWidth == 0 || Cocos2dxVideoView.this.mVideoHeight == 0)) {
                Cocos2dxVideoView.this.fixSize();
            }
            if (Cocos2dxVideoView.this.mTargetState == Cocos2dxVideoView.STATE_PLAYING) {
                Cocos2dxVideoView.this.start();
            }
        }
    };
    Callback mSHCallback = new Callback() {
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Object obj = Cocos2dxVideoView.STATE_PREPARING;
            Object obj2 = Cocos2dxVideoView.this.mTargetState == Cocos2dxVideoView.STATE_PLAYING ? Cocos2dxVideoView.STATE_PREPARING : Cocos2dxVideoView.STATE_IDLE;
            if (!(Cocos2dxVideoView.this.mVideoWidth == i2 && Cocos2dxVideoView.this.mVideoHeight == i3)) {
                obj = Cocos2dxVideoView.STATE_IDLE;
            }
            if (Cocos2dxVideoView.this.mMediaPlayer != null && obj2 != null && r1 != null) {
                if (Cocos2dxVideoView.this.mSeekWhenPrepared != 0) {
                    Cocos2dxVideoView.this.seekTo(Cocos2dxVideoView.this.mSeekWhenPrepared);
                }
                Cocos2dxVideoView.this.start();
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Cocos2dxVideoView.this.mSurfaceHolder = surfaceHolder;
            Cocos2dxVideoView.this.openVideo();
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Cocos2dxVideoView.this.mSurfaceHolder = null;
            Cocos2dxVideoView.this.release(true);
        }
    };
    private int mSeekWhenPrepared;
    protected OnVideoSizeChangedListener mSizeChangedListener = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            Cocos2dxVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
            Cocos2dxVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
            if (Cocos2dxVideoView.this.mVideoWidth != 0 && Cocos2dxVideoView.this.mVideoHeight != 0) {
                Cocos2dxVideoView.this.getHolder().setFixedSize(Cocos2dxVideoView.this.mVideoWidth, Cocos2dxVideoView.this.mVideoHeight);
            }
        }
    };
    private SurfaceHolder mSurfaceHolder = null;
    private int mTargetState = STATE_IDLE;
    private String mVideoFilePath = null;
    private int mVideoHeight = STATE_IDLE;
    private Uri mVideoUri;
    private int mVideoWidth = STATE_IDLE;
    protected int mViewHeight = STATE_IDLE;
    protected int mViewLeft = STATE_IDLE;
    private int mViewTag = STATE_IDLE;
    protected int mViewTop = STATE_IDLE;
    protected int mViewWidth = STATE_IDLE;
    protected int mVisibleHeight = STATE_IDLE;
    protected int mVisibleLeft = STATE_IDLE;
    protected int mVisibleTop = STATE_IDLE;
    protected int mVisibleWidth = STATE_IDLE;

    public interface OnVideoEventListener {
        void onVideoEvent(int i, int i2);
    }

    public Cocos2dxVideoView(Cocos2dxActivity cocos2dxActivity, int i) {
        super(cocos2dxActivity);
        this.mViewTag = i;
        this.mCocos2dxActivity = cocos2dxActivity;
        initVideoView();
    }

    protected void onMeasure(int i, int i2) {
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            setMeasuredDimension(this.mViewWidth, this.mViewHeight);
            Log.i(this.TAG, BuildConfig.FLAVOR + this.mViewWidth + ":" + this.mViewHeight);
            return;
        }
        setMeasuredDimension(this.mVisibleWidth, this.mVisibleHeight);
        Log.i(this.TAG, BuildConfig.FLAVOR + this.mVisibleWidth + ":" + this.mVisibleHeight);
    }

    public void setVideoRect(int i, int i2, int i3, int i4) {
        this.mViewLeft = i;
        this.mViewTop = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
        fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
    }

    public void setFullScreenEnabled(boolean z, int i, int i2) {
        if (this.mFullScreenEnabled != z) {
            this.mFullScreenEnabled = z;
            if (!(i == 0 || i2 == 0)) {
                this.mFullScreenWidth = i;
                this.mFullScreenHeight = i2;
            }
            fixSize();
        }
    }

    public int resolveAdjustedSize(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                return Math.min(i, size);
            case 1073741824:
                return size;
            default:
                return i;
        }
    }

    public void setVisibility(int i) {
        if (i == STATE_PAUSED) {
            this.mNeedResume = isPlaying();
            if (this.mNeedResume) {
                this.mSeekWhenPrepared = getCurrentPosition();
            }
        } else if (this.mNeedResume) {
            start();
            this.mNeedResume = false;
        }
        super.setVisibility(i);
    }

    private void initVideoView() {
        this.mVideoWidth = STATE_IDLE;
        this.mVideoHeight = STATE_IDLE;
        getHolder().addCallback(this.mSHCallback);
        getHolder().setType(STATE_PLAYING);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == STATE_PREPARING) {
            if (isPlaying()) {
                pause();
            } else if (this.mCurrentState == STATE_PAUSED) {
                resume();
            }
        }
        return true;
    }

    public void setVideoFileName(String str) {
        if (str.startsWith(AssetResourceRoot)) {
            str = str.substring(AssetResourceRoot.length());
        }
        if (str.startsWith(Operation.DIVISION)) {
            this.mIsAssetRouse = false;
            setVideoURI(Uri.parse(str), null);
            return;
        }
        this.mVideoFilePath = str;
        this.mIsAssetRouse = true;
        setVideoURI(Uri.parse(str), null);
    }

    public void setVideoURL(String str) {
        this.mIsAssetRouse = false;
        setVideoURI(Uri.parse(str), null);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mVideoUri = uri;
        this.mSeekWhenPrepared = STATE_IDLE;
        this.mVideoWidth = STATE_IDLE;
        this.mVideoHeight = STATE_IDLE;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = STATE_IDLE;
            this.mTargetState = STATE_IDLE;
        }
    }

    private void openVideo() {
        if (this.mSurfaceHolder != null) {
            if (this.mIsAssetRouse) {
                if (this.mVideoFilePath == null) {
                    return;
                }
            } else if (this.mVideoUri == null) {
                return;
            }
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra(String.COMMAND, "pause");
            this.mCocos2dxActivity.sendBroadcast(intent);
            release(false);
            try {
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(STATE_PLAYING);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mDuration = STATE_ERROR;
                this.mCurrentBufferPercentage = STATE_IDLE;
                if (this.mIsAssetRouse) {
                    AssetFileDescriptor openFd = this.mCocos2dxActivity.getAssets().openFd(this.mVideoFilePath);
                    this.mMediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                } else {
                    this.mMediaPlayer.setDataSource(this.mCocos2dxActivity, this.mVideoUri);
                }
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = STATE_PREPARING;
            } catch (Throwable e) {
                Log.w(this.TAG, "Unable to open content: " + this.mVideoUri, e);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, STATE_PREPARING, STATE_IDLE);
            } catch (Throwable e2) {
                Log.w(this.TAG, "Unable to open content: " + this.mVideoUri, e2);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, STATE_PREPARING, STATE_IDLE);
            }
        }
    }

    public void setKeepRatio(boolean z) {
        this.mKeepRatio = z;
        fixSize();
    }

    public void fixSize() {
        if (this.mFullScreenEnabled) {
            fixSize(STATE_IDLE, STATE_IDLE, this.mFullScreenWidth, this.mFullScreenHeight);
        } else {
            fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
        }
    }

    public void fixSize(int i, int i2, int i3, int i4) {
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            this.mVisibleLeft = i;
            this.mVisibleTop = i2;
            this.mVisibleWidth = i3;
            this.mVisibleHeight = i4;
        } else if (i3 == 0 || i4 == 0) {
            this.mVisibleLeft = i;
            this.mVisibleTop = i2;
            this.mVisibleWidth = this.mVideoWidth;
            this.mVisibleHeight = this.mVideoHeight;
        } else if (this.mKeepRatio) {
            if (this.mVideoWidth * i4 > this.mVideoHeight * i3) {
                this.mVisibleWidth = i3;
                this.mVisibleHeight = (this.mVideoHeight * i3) / this.mVideoWidth;
            } else if (this.mVideoWidth * i4 < this.mVideoHeight * i3) {
                this.mVisibleWidth = (this.mVideoWidth * i4) / this.mVideoHeight;
                this.mVisibleHeight = i4;
            }
            this.mVisibleLeft = ((i3 - this.mVisibleWidth) / STATE_PREPARED) + i;
            this.mVisibleTop = ((i4 - this.mVisibleHeight) / STATE_PREPARED) + i2;
        } else {
            this.mVisibleLeft = i;
            this.mVisibleTop = i2;
            this.mVisibleWidth = i3;
            this.mVisibleHeight = i4;
        }
        getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.mVisibleLeft;
        layoutParams.topMargin = this.mVisibleTop;
        layoutParams.gravity = 51;
        setLayoutParams(layoutParams);
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(OnVideoEventListener onVideoEventListener) {
        this.mOnVideoEventListener = onVideoEventListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    private void release(boolean z) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = STATE_IDLE;
            if (z) {
                this.mTargetState = STATE_IDLE;
            }
        }
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = STATE_PLAYING;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, STATE_IDLE);
            }
        }
        this.mTargetState = STATE_PLAYING;
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = STATE_PAUSED;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, STATE_PREPARING);
            }
        }
        this.mTargetState = STATE_PAUSED;
    }

    public void stop() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            stopPlayback();
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, STATE_PREPARED);
            }
        }
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        if (isInPlaybackState() && this.mCurrentState == STATE_PAUSED) {
            this.mMediaPlayer.start();
            this.mCurrentState = STATE_PLAYING;
            if (this.mOnVideoEventListener != null) {
                this.mOnVideoEventListener.onVideoEvent(this.mViewTag, STATE_IDLE);
            }
        }
    }

    public void restart() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(STATE_IDLE);
            this.mMediaPlayer.start();
            this.mCurrentState = STATE_PLAYING;
            this.mTargetState = STATE_PLAYING;
        }
    }

    public int getDuration() {
        if (!isInPlaybackState()) {
            this.mDuration = STATE_ERROR;
            return this.mDuration;
        } else if (this.mDuration > 0) {
            return this.mDuration;
        } else {
            this.mDuration = this.mMediaPlayer.getDuration();
            return this.mDuration;
        }
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return STATE_IDLE;
    }

    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = STATE_IDLE;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return STATE_IDLE;
    }

    public boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == STATE_ERROR || this.mCurrentState == 0 || this.mCurrentState == STATE_PREPARING) ? false : true;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return this.mMediaPlayer.getAudioSessionId();
    }
}
