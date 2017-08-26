package org.cocos2dx.lib;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Cocos2dxEditBoxHelper {
    private static final String TAG = Cocos2dxEditBoxHelper.class.getSimpleName();
    private static Cocos2dxActivity mCocos2dxActivity;
    private static SparseArray<Cocos2dxEditBox> mEditBoxArray;
    private static ResizeLayout mFrameLayout;
    private static int mViewTag = 0;

    private static native void editBoxEditingChanged(int i, String str);

    private static native void editBoxEditingDidBegin(int i);

    private static native void editBoxEditingDidEnd(int i, String str);

    public static void __editBoxEditingDidBegin(int i) {
        editBoxEditingDidBegin(i);
    }

    public static void __editBoxEditingChanged(int i, String str) {
        editBoxEditingChanged(i, str);
    }

    public static void __editBoxEditingDidEnd(int i, String str) {
        editBoxEditingDidEnd(i, str);
    }

    public Cocos2dxEditBoxHelper(ResizeLayout resizeLayout) {
        mFrameLayout = resizeLayout;
        mCocos2dxActivity = (Cocos2dxActivity) Cocos2dxActivity.getContext();
        mEditBoxArray = new SparseArray();
    }

    public static int convertToSP(float f) {
        return (int) TypedValue.applyDimension(2, f, mCocos2dxActivity.getResources().getDisplayMetrics());
    }

    public static int createEditBox(int i, int i2, int i3, int i4, float f) {
        final int i5 = mViewTag;
        final float f2 = f;
        final int i6 = i4;
        final int i7 = i;
        final int i8 = i2;
        final int i9 = i3;
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                final View cocos2dxEditBox = new Cocos2dxEditBox(Cocos2dxEditBoxHelper.mCocos2dxActivity);
                cocos2dxEditBox.setFocusable(true);
                cocos2dxEditBox.setFocusableInTouchMode(true);
                cocos2dxEditBox.setInputFlag(4);
                cocos2dxEditBox.setInputMode(6);
                cocos2dxEditBox.setReturnType(0);
                cocos2dxEditBox.setHintTextColor(-7829368);
                cocos2dxEditBox.setVisibility(4);
                cocos2dxEditBox.setBackgroundColor(0);
                cocos2dxEditBox.setTextColor(-1);
                cocos2dxEditBox.setSingleLine();
                cocos2dxEditBox.setOpenGLViewScaleX(f2);
                float f = Cocos2dxEditBoxHelper.mCocos2dxActivity.getResources().getDisplayMetrics().density;
                int convertToSP = Cocos2dxEditBoxHelper.convertToSP(((float) ((int) ((((float) i6) * 0.33f) / f))) - ((f2 * 5.0f) / f)) / 2;
                cocos2dxEditBox.setPadding(Cocos2dxEditBoxHelper.convertToSP((float) ((int) ((f2 * 5.0f) / f))), convertToSP, 0, convertToSP);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = i7;
                layoutParams.topMargin = i8;
                layoutParams.width = i9;
                layoutParams.height = i6;
                layoutParams.gravity = 51;
                Cocos2dxEditBoxHelper.mFrameLayout.addView(cocos2dxEditBox, layoutParams);
                cocos2dxEditBox.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(final CharSequence charSequence, int i, int i2, int i3) {
                        Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new Runnable() {
                            public void run() {
                                Cocos2dxEditBoxHelper.__editBoxEditingChanged(i5, charSequence.toString());
                            }
                        });
                    }

                    public void afterTextChanged(Editable editable) {
                    }
                });
                cocos2dxEditBox.setOnFocusChangeListener(new OnFocusChangeListener() {
                    public void onFocusChange(View view, boolean z) {
                        if (z) {
                            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new Runnable() {
                                public void run() {
                                    Cocos2dxEditBoxHelper.__editBoxEditingDidBegin(i5);
                                }
                            });
                            cocos2dxEditBox.setSelection(cocos2dxEditBox.getText().length());
                            Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(true);
                            Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
                            Log.d(Cocos2dxEditBoxHelper.TAG, "edit box get focus");
                            return;
                        }
                        cocos2dxEditBox.setVisibility(8);
                        Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new Runnable() {
                            public void run() {
                                Cocos2dxEditBoxHelper.__editBoxEditingDidEnd(i5, cocos2dxEditBox.getText().toString());
                            }
                        });
                        Cocos2dxEditBoxHelper.mFrameLayout.setEnableForceDoLayout(false);
                        Log.d(Cocos2dxEditBoxHelper.TAG, "edit box lose focus");
                    }
                });
                cocos2dxEditBox.setOnKeyListener(new OnKeyListener() {
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (keyEvent.getAction() != 0 || i != 66 || (cocos2dxEditBox.getInputType() & 131072) == 131072) {
                            return false;
                        }
                        Cocos2dxEditBoxHelper.closeKeyboard(i5);
                        Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().requestFocus();
                        return true;
                    }
                });
                cocos2dxEditBox.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == 6) {
                            Cocos2dxEditBoxHelper.closeKeyboard(i5);
                            Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().requestFocus();
                        }
                        return false;
                    }
                });
                Cocos2dxEditBoxHelper.mEditBoxArray.put(i5, cocos2dxEditBox);
            }
        });
        int i10 = mViewTag;
        mViewTag = i10 + 1;
        return i10;
    }

    public static void removeEditBox(final int i) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    Cocos2dxEditBoxHelper.mEditBoxArray.remove(i);
                    Cocos2dxEditBoxHelper.mFrameLayout.removeView(cocos2dxEditBox);
                    Log.e(Cocos2dxEditBoxHelper.TAG, "remove EditBox");
                }
            }
        });
    }

    public static void setFont(final int i, final String str, final float f) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    Typeface typeface;
                    if (str.isEmpty()) {
                        typeface = Typeface.DEFAULT;
                    } else {
                        typeface = Typeface.create(str, 0);
                    }
                    if (f >= 0.0f) {
                        cocos2dxEditBox.setTextSize(2, f / Cocos2dxEditBoxHelper.mCocos2dxActivity.getResources().getDisplayMetrics().density);
                    }
                    cocos2dxEditBox.setTypeface(typeface);
                }
            }
        });
    }

    public static void setFontColor(int i, int i2, int i3, int i4, int i5) {
        final int i6 = i;
        final int i7 = i5;
        final int i8 = i2;
        final int i9 = i3;
        final int i10 = i4;
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i6);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setTextColor(Color.argb(i7, i8, i9, i10));
                }
            }
        });
    }

    public static void setPlaceHolderText(final int i, final String str) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setHint(str);
                }
            }
        });
    }

    public static void setPlaceHolderTextColor(int i, int i2, int i3, int i4, int i5) {
        final int i6 = i;
        final int i7 = i5;
        final int i8 = i2;
        final int i9 = i3;
        final int i10 = i4;
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i6);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setHintTextColor(Color.argb(i7, i8, i9, i10));
                }
            }
        });
    }

    public static void setMaxLength(final int i, final int i2) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setMaxLength(i2);
                }
            }
        });
    }

    public static void setVisible(final int i, final boolean z) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setVisibility(z ? 0 : 8);
                    if (z) {
                        cocos2dxEditBox.requestFocus();
                        Cocos2dxEditBoxHelper.openKeyboard(i);
                        return;
                    }
                    Cocos2dxEditBoxHelper.mCocos2dxActivity.getGLSurfaceView().requestFocus();
                    Cocos2dxEditBoxHelper.closeKeyboard(i);
                }
            }
        });
    }

    public static void setText(final int i, final String str) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setText(str);
                }
            }
        });
    }

    public static void setReturnType(final int i, final int i2) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setReturnType(i2);
                }
            }
        });
    }

    public static void setInputMode(final int i, final int i2) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setInputMode(i2);
                }
            }
        });
    }

    public static void setInputFlag(final int i, final int i2) {
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setInputFlag(i2);
                }
            }
        });
    }

    public static void setEditBoxViewRect(int i, int i2, int i3, int i4, int i5) {
        final int i6 = i;
        final int i7 = i2;
        final int i8 = i3;
        final int i9 = i4;
        final int i10 = i5;
        mCocos2dxActivity.runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(i6);
                if (cocos2dxEditBox != null) {
                    cocos2dxEditBox.setEditBoxViewRect(i7, i8, i9, i10);
                }
            }
        });
    }

    public static void openKeyboard(int i) {
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) mEditBoxArray.get(i);
        if (cocos2dxEditBox != null) {
            inputMethodManager.showSoftInput(cocos2dxEditBox, 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(true);
        }
    }

    public static void closeKeyboard(int i) {
        Cocos2dxActivity cocos2dxActivity = mCocos2dxActivity;
        InputMethodManager inputMethodManager = (InputMethodManager) Cocos2dxActivity.getContext().getSystemService("input_method");
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) mEditBoxArray.get(i);
        if (cocos2dxEditBox != null) {
            inputMethodManager.hideSoftInputFromWindow(cocos2dxEditBox.getWindowToken(), 0);
            mCocos2dxActivity.getGLSurfaceView().setSoftKeyboardShown(false);
        }
    }
}
