package jp.co.mediasdk.mscore.ui.hybrid;

import android.content.Context;
import android.graphics.Color;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import org.cocos2dx.lib.BuildConfig;

public class MSNavigationBarView extends LinearLayout {
    public static String a = "title_background_color_key";
    public static String b = "title_text_color_key";
    public static String c = "title_text_key";
    public static String d = "left_button_text_key";
    public static String e = "left_button_text_color_key";
    private Button f;
    private TextView g;
    private FrameLayout h;

    public MSNavigationBarView(Context context) {
        super(context);
        a(context);
    }

    public void setOnBackButtonClickListner(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    private void a(Context context) {
        setOrientation(0);
        this.h = new FrameLayout(context);
        setBackButton(context);
        setTitleView(context);
        addView(this.h);
    }

    private void setBackButton(Context context) {
        this.f = new Button(context);
        CharSequence charSequence = "\u623b\u308b";
        if (MSParameterSupport.b(d)) {
            charSequence = MSParameterSupport.a(d);
        }
        this.f.setText(charSequence);
        int i = -1;
        if (MSParameterSupport.b(e)) {
            i = Color.parseColor(MSParameterSupport.a(e));
        }
        this.f.setTextColor(i);
        this.f.setBackgroundColor(0);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 3;
        this.h.addView(this.f, layoutParams);
    }

    private void setTitleView(Context context) {
        int i = -16777216;
        if (MSParameterSupport.b(a)) {
            i = Color.parseColor(MSParameterSupport.a(a));
        }
        this.h.setBackgroundColor(i);
        this.g = new TextView(context);
        CharSequence charSequence = BuildConfig.FLAVOR;
        if (MSParameterSupport.b(c)) {
            charSequence = MSParameterSupport.a(c);
        }
        this.g.setText(charSequence);
        if (MSParameterSupport.b(b)) {
            i = Color.parseColor(MSParameterSupport.a(b));
        } else {
            i = -1;
        }
        this.g.setTextColor(i);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.h.addView(this.g, layoutParams);
        this.h.setLayoutParams(new LayoutParams(-1, -2));
    }
}
