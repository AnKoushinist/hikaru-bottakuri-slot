package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public class Cocos2dxEditBox extends EditText {
    private final int kEditBoxInputFlagInitialCapsAllCharacters = 4;
    private final int kEditBoxInputFlagInitialCapsSentence = 3;
    private final int kEditBoxInputFlagInitialCapsWord = 2;
    private final int kEditBoxInputFlagPassword = 0;
    private final int kEditBoxInputFlagSensitive = 1;
    private final int kEditBoxInputModeAny = 0;
    private final int kEditBoxInputModeDecimal = 5;
    private final int kEditBoxInputModeEmailAddr = 1;
    private final int kEditBoxInputModeNumeric = 2;
    private final int kEditBoxInputModePhoneNumber = 3;
    private final int kEditBoxInputModeSingleLine = 6;
    private final int kEditBoxInputModeUrl = 4;
    private final int kKeyboardReturnTypeDefault = 0;
    private final int kKeyboardReturnTypeDone = 1;
    private final int kKeyboardReturnTypeGo = 4;
    private final int kKeyboardReturnTypeSearch = 3;
    private final int kKeyboardReturnTypeSend = 2;
    private int mInputFlagConstraints;
    private int mInputModeConstraints;
    private int mMaxLength;
    private float mScaleX;

    public Cocos2dxEditBox(Context context) {
        super(context);
    }

    public void setEditBoxViewRect(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        layoutParams.width = i3;
        layoutParams.height = i4;
        layoutParams.gravity = 51;
        setLayoutParams(layoutParams);
    }

    public float getOpenGLViewScaleX() {
        return this.mScaleX;
    }

    public void setOpenGLViewScaleX(float f) {
        this.mScaleX = f;
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
        setFilters(new InputFilter[]{new LengthFilter(this.mMaxLength)});
    }

    public void setMultilineEnabled(boolean z) {
        this.mInputModeConstraints |= 131072;
    }

    public void setReturnType(int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                setImeOptions(268435457);
                return;
            case TwitterResponse.READ /*1*/:
                setImeOptions(268435462);
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                setImeOptions(268435460);
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                setImeOptions(268435459);
                return;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                setImeOptions(268435458);
                return;
            default:
                setImeOptions(268435457);
                return;
        }
    }

    public void setInputMode(int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                this.mInputModeConstraints = 131073;
                break;
            case TwitterResponse.READ /*1*/:
                this.mInputModeConstraints = 33;
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                this.mInputModeConstraints = 4098;
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                this.mInputModeConstraints = 3;
                break;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                this.mInputModeConstraints = 17;
                break;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                this.mInputModeConstraints = 12290;
                break;
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                this.mInputModeConstraints = 1;
                break;
        }
        setInputType(this.mInputModeConstraints | this.mInputFlagConstraints);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                ((Cocos2dxActivity) getContext()).getGLSurfaceView().requestFocus();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return super.onKeyPreIme(i, keyEvent);
    }

    public void setInputFlag(int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                this.mInputFlagConstraints = 129;
                setTypeface(Typeface.DEFAULT);
                setTransformationMethod(new PasswordTransformationMethod());
                break;
            case TwitterResponse.READ /*1*/:
                this.mInputFlagConstraints = 524288;
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                this.mInputFlagConstraints = 8192;
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                this.mInputFlagConstraints = 16384;
                break;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                this.mInputFlagConstraints = 4096;
                break;
        }
        setInputType(this.mInputFlagConstraints | this.mInputModeConstraints);
    }
}
