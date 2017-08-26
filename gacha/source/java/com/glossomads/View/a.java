package com.glossomads.View;

import android.view.View;
import android.view.View.OnClickListener;

public class a implements OnClickListener {
    private long a = 0;

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.a < 300) {
            b(view);
        } else {
            a(view);
        }
        this.a = currentTimeMillis;
    }

    public void a(View view) {
    }

    public void b(View view) {
    }
}
