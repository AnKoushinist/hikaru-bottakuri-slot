package com.glossomads.View;

import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.glossomads.s;

public class k extends FrameLayout {
    private TextView a;

    public k() {
        super(s.e());
        a();
    }

    public void a() {
        int i = (int) (s.e().getResources().getDisplayMetrics().density * 50.0f);
        setLayoutParams(new LayoutParams(i, i, 80));
        this.a = new TextView(s.e());
        this.a.setTextColor(-1);
        this.a.setTextSize(16.0f);
        this.a.setLayoutParams(new LayoutParams(v.b, v.b, 17));
        addView(this.a);
    }

    public void setTime(int i) {
        if (!String.valueOf(i).equals(this.a.getText().toString())) {
            this.a.setText(String.valueOf(i));
        }
    }

    public void b() {
        bringToFront();
        setVisibility(0);
    }

    public void c() {
        setVisibility(4);
    }
}
