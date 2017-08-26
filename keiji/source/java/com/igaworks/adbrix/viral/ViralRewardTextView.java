package com.igaworks.adbrix.viral;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.widget.TextView;
import com.igaworks.adbrix.util.DialogUtil;

public class ViralRewardTextView extends TextView {
    private int strokeColor;
    private float strokeWidth = 0.0f;

    public ViralRewardTextView(Context context) {
        super(context);
        this.strokeWidth = (float) DialogUtil.convertPixelToDP(context, 4, false);
        this.strokeColor = -16777216;
    }

    protected void onDraw(Canvas canvas) {
        ColorStateList textColors = getTextColors();
        getPaint().setStyle(Style.STROKE);
        getPaint().setStrokeWidth(this.strokeWidth);
        setTextColor(this.strokeColor);
        super.onDraw(canvas);
        getPaint().setStyle(Style.FILL);
        setTextColor(textColors);
        super.onDraw(canvas);
    }
}
