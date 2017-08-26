package com.vungle.publisher;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class og extends LinearLayout implements OnClickListener {
    private ql a;
    private boolean b = false;
    private TextView c;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        agw a;
        @Inject
        ql b;

        @Inject
        a() {
        }

        public final og a(Context context, boolean z) {
            og ogVar = new og(context);
            ogVar.setGravity(16);
            View oaVar = new oa(context);
            this.a.a(oaVar, com.vungle.publisher.rb.a.privacy);
            TextView textView = new TextView(context);
            textView.setText("privacy");
            textView.setTextSize(16.0f);
            textView.setTextColor(-1);
            textView.setVisibility(8);
            textView.setPadding(10, 0, 10, 0);
            if (z) {
                ogVar.addView(oaVar);
                ogVar.addView(textView);
            } else {
                ogVar.addView(textView);
                ogVar.addView(oaVar);
            }
            ogVar.a = this.b;
            ogVar.c = textView;
            ogVar.setVisibility(8);
            return ogVar;
        }
    }

    public og(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public final void onClick(View view) {
        if (this.b) {
            this.a.a(new ak());
            return;
        }
        this.b = true;
        setBackgroundColor(Color.parseColor("#000000"));
        this.c.setVisibility(0);
    }
}
