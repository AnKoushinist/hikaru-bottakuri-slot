package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class Cocos2dxGLSurfaceView extends GLSurfaceView {
    private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
    private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
    private static final String TAG = Cocos2dxGLSurfaceView.class.getSimpleName();
    private static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private static Cocos2dxTextInputWraper sCocos2dxTextInputWraper;
    private static Handler sHandler;
    private Cocos2dxEditBox mCocos2dxEditText;
    private Cocos2dxRenderer mCocos2dxRenderer;
    private boolean mSoftKeyboardShown = false;

    public boolean isSoftKeyboardShown() {
        return this.mSoftKeyboardShown;
    }

    public void setSoftKeyboardShown(boolean z) {
        this.mSoftKeyboardShown = z;
    }

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    protected void initView() {
        setEGLContextClientVersion(HANDLER_OPEN_IME_KEYBOARD);
        setFocusableInTouchMode(true);
        mCocos2dxGLSurfaceView = this;
        sCocos2dxTextInputWraper = new Cocos2dxTextInputWraper(this);
        sHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case Cocos2dxGLSurfaceView.HANDLER_OPEN_IME_KEYBOARD /*2*/:
                        if (Cocos2dxGLSurfaceView.this.mCocos2dxEditText != null && Cocos2dxGLSurfaceView.this.mCocos2dxEditText.requestFocus()) {
                            Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                            Cocos2dxGLSurfaceView.this.mCocos2dxEditText.setText(BuildConfig.FLAVOR);
                            String str = (String) message.obj;
                            Cocos2dxGLSurfaceView.this.mCocos2dxEditText.append(str);
                            Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText(str);
                            Cocos2dxGLSurfaceView.this.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                            ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(Cocos2dxGLSurfaceView.this.mCocos2dxEditText, 0);
                            Log.d("GLSurfaceView", "showSoftInput");
                            return;
                        }
                        return;
                    case Cocos2dxGLSurfaceView.HANDLER_CLOSE_IME_KEYBOARD /*3*/:
                        if (Cocos2dxGLSurfaceView.this.mCocos2dxEditText != null) {
                            Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
                            ((InputMethodManager) Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.this.mCocos2dxEditText.getWindowToken(), 0);
                            Cocos2dxGLSurfaceView.this.requestFocus();
                            Log.d("GLSurfaceView", "HideSoftInput");
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public static Cocos2dxGLSurfaceView getInstance() {
        return mCocos2dxGLSurfaceView;
    }

    public static void queueAccelerometer(float f, float f2, float f3, long j) {
        final float f4 = f;
        final float f5 = f2;
        final float f6 = f3;
        final long j2 = j;
        mCocos2dxGLSurfaceView.queueEvent(new Runnable() {
            public void run() {
                Cocos2dxAccelerometer.onSensorChanged(f4, f5, f6, j2);
            }
        });
    }

    public void setCocos2dxRenderer(Cocos2dxRenderer cocos2dxRenderer) {
        this.mCocos2dxRenderer = cocos2dxRenderer;
        setRenderer(this.mCocos2dxRenderer);
    }

    private String getContentText() {
        return this.mCocos2dxRenderer.getContentText();
    }

    public Cocos2dxEditBox getCocos2dxEditText() {
        return this.mCocos2dxEditText;
    }

    public void setCocos2dxEditText(Cocos2dxEditBox cocos2dxEditBox) {
        this.mCocos2dxEditText = cocos2dxEditBox;
        if (this.mCocos2dxEditText != null && sCocos2dxTextInputWraper != null) {
            this.mCocos2dxEditText.setOnEditorActionListener(sCocos2dxTextInputWraper);
            requestFocus();
        }
    }

    public void onResume() {
        super.onResume();
        setRenderMode(1);
        queueEvent(new Runnable() {
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnResume();
            }
        });
    }

    public void onPause() {
        queueEvent(new Runnable() {
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnPause();
            }
        });
        setRenderMode(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int pointerCount = motionEvent.getPointerCount();
        final int[] iArr = new int[pointerCount];
        final float[] fArr = new float[pointerCount];
        final float[] fArr2 = new float[pointerCount];
        if (this.mSoftKeyboardShown) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
            requestFocus();
            this.mSoftKeyboardShown = false;
        }
        for (i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
            fArr[i] = motionEvent.getX(i);
            fArr2[i] = motionEvent.getY(i);
        }
        final float f;
        final float f2;
        final int pointerId;
        final float y;
        switch (motionEvent.getAction() & 255) {
            case TwitterResponse.NONE /*0*/:
                i = motionEvent.getPointerId(0);
                f = fArr[0];
                f2 = fArr2[0];
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(i, f, f2);
                    }
                });
                break;
            case TwitterResponse.READ /*1*/:
                i = motionEvent.getPointerId(0);
                f = fArr[0];
                f2 = fArr2[0];
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(i, f, f2);
                    }
                });
                break;
            case HANDLER_OPEN_IME_KEYBOARD /*2*/:
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionMove(iArr, fArr, fArr2);
                    }
                });
                break;
            case HANDLER_CLOSE_IME_KEYBOARD /*3*/:
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionCancel(iArr, fArr, fArr2);
                    }
                });
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                i = motionEvent.getAction() >> 8;
                pointerId = motionEvent.getPointerId(i);
                f2 = motionEvent.getX(i);
                y = motionEvent.getY(i);
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(pointerId, f2, y);
                    }
                });
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                i = motionEvent.getAction() >> 8;
                pointerId = motionEvent.getPointerId(i);
                f2 = motionEvent.getX(i);
                y = motionEvent.getY(i);
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(pointerId, f2, y);
                    }
                });
                break;
        }
        return true;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!isInEditMode()) {
            this.mCocos2dxRenderer.setScreenWidthAndHeight(i, i2);
        }
    }

    public boolean onKeyDown(final int i, KeyEvent keyEvent) {
        switch (i) {
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                Cocos2dxVideoHelper.mVideoHandler.sendEmptyMessage(GameControllerDelegate.THUMBSTICK_LEFT_X);
                break;
            case R.styleable.Toolbar_titleMargins /*19*/:
            case R.styleable.Toolbar_maxButtonHeight /*20*/:
            case R.styleable.Toolbar_buttonGravity /*21*/:
            case R.styleable.Toolbar_collapseIcon /*22*/:
            case R.styleable.Toolbar_collapseContentDescription /*23*/:
            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
            case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator /*82*/:
            case R.styleable.AppCompatTheme_colorAccent /*85*/:
                break;
            default:
                return super.onKeyDown(i, keyEvent);
        }
        queueEvent(new Runnable() {
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyDown(i);
            }
        });
        return true;
    }

    public boolean onKeyUp(final int i, KeyEvent keyEvent) {
        switch (i) {
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
            case R.styleable.Toolbar_titleMargins /*19*/:
            case R.styleable.Toolbar_maxButtonHeight /*20*/:
            case R.styleable.Toolbar_buttonGravity /*21*/:
            case R.styleable.Toolbar_collapseIcon /*22*/:
            case R.styleable.Toolbar_collapseContentDescription /*23*/:
            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle /*66*/:
            case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator /*82*/:
            case R.styleable.AppCompatTheme_colorAccent /*85*/:
                queueEvent(new Runnable() {
                    public void run() {
                        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyUp(i);
                    }
                });
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public static void openIMEKeyboard() {
        Message message = new Message();
        message.what = HANDLER_OPEN_IME_KEYBOARD;
        message.obj = mCocos2dxGLSurfaceView.getContentText();
        sHandler.sendMessage(message);
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = HANDLER_CLOSE_IME_KEYBOARD;
        sHandler.sendMessage(message);
    }

    public void insertText(final String str) {
        queueEvent(new Runnable() {
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleInsertText(str);
            }
        });
    }

    public void deleteBackward() {
        queueEvent(new Runnable() {
            public void run() {
                Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleDeleteBackward();
            }
        });
    }

    private static void dumpMotionEvent(MotionEvent motionEvent) {
        int i = 0;
        String[] strArr = new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder stringBuilder = new StringBuilder();
        int action = motionEvent.getAction();
        int i2 = action & 255;
        stringBuilder.append("event ACTION_").append(strArr[i2]);
        if (i2 == 5 || i2 == 6) {
            stringBuilder.append("(pid ").append(action >> 8);
            stringBuilder.append(")");
        }
        stringBuilder.append("[");
        while (i < motionEvent.getPointerCount()) {
            stringBuilder.append("#").append(i);
            stringBuilder.append("(pid ").append(motionEvent.getPointerId(i));
            stringBuilder.append(")=").append((int) motionEvent.getX(i));
            stringBuilder.append(",").append((int) motionEvent.getY(i));
            if (i + 1 < motionEvent.getPointerCount()) {
                stringBuilder.append(";");
            }
            i++;
        }
        stringBuilder.append("]");
        Log.d(TAG, stringBuilder.toString());
    }
}
