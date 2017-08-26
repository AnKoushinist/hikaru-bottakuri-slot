package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Cocos2dxTextInputWraper implements TextWatcher, OnEditorActionListener {
    private static final String TAG = Cocos2dxTextInputWraper.class.getSimpleName();
    private final Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
    private String mOriginText;
    private String mText;

    public Cocos2dxTextInputWraper(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mCocos2dxGLSurfaceView = cocos2dxGLSurfaceView;
    }

    private boolean isFullScreenEdit() {
        return ((InputMethodManager) this.mCocos2dxGLSurfaceView.getCocos2dxEditText().getContext().getSystemService("input_method")).isFullscreenMode();
    }

    public void setOriginText(String str) {
        this.mOriginText = str;
    }

    public void afterTextChanged(Editable editable) {
        int i = 0;
        if (!isFullScreenEdit()) {
            int i2 = 0;
            while (i2 < this.mText.length() && i < editable.length() && this.mText.charAt(i2) == editable.charAt(i)) {
                i2++;
                i++;
            }
            while (i2 < this.mText.length()) {
                this.mCocos2dxGLSurfaceView.deleteBackward();
                i2++;
            }
            if (editable.length() - i > 0) {
                this.mCocos2dxGLSurfaceView.insertText(editable.subSequence(i, editable.length()).toString());
            }
            this.mText = editable.toString();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mText = charSequence.toString();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.mCocos2dxGLSurfaceView.getCocos2dxEditText() == textView && isFullScreenEdit()) {
            if (this.mOriginText != null) {
                for (int length = this.mOriginText.length(); length > 0; length--) {
                    this.mCocos2dxGLSurfaceView.deleteBackward();
                }
            }
            String charSequence = textView.getText().toString();
            if (charSequence != null) {
                if (charSequence.compareTo(BuildConfig.FLAVOR) == 0) {
                    charSequence = "\n";
                }
                if ('\n' != charSequence.charAt(charSequence.length() - 1)) {
                    charSequence = charSequence + '\n';
                }
            }
            this.mCocos2dxGLSurfaceView.insertText(charSequence);
        }
        if (i == 6) {
            this.mCocos2dxGLSurfaceView.requestFocus();
        }
        return false;
    }
}
