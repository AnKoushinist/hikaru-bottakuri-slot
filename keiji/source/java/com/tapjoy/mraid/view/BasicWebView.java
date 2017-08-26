package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.webkit.WebView;

public class BasicWebView extends WebView {

    class a extends SimpleOnGestureListener {
        final /* synthetic */ BasicWebView a;

        a(BasicWebView basicWebView) {
            this.a = basicWebView;
        }

        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }
    }

    @SuppressLint({"NewApi"})
    public BasicWebView(Context context) {
        super(context);
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        GestureDetector gestureDetector = new GestureDetector(new a(this));
        if (getSettings() != null) {
            getSettings().setJavaScriptEnabled(true);
            if (VERSION.SDK_INT >= 17) {
                getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
        }
        setBackgroundColor(0);
    }

    public BasicWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
