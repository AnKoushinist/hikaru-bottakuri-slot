package com.glossomads.View;

import com.glossomads.View.p.a;
import com.glossomads.b.b;

class d implements a {
    final /* synthetic */ SugarAdView a;

    d(SugarAdView sugarAdView) {
        this.a = sugarAdView;
    }

    public void a() {
        this.a.show();
    }

    public void a(boolean z) {
        if (z) {
            this.a.makeViews();
            return;
        }
        this.a.onVideoError(new b(b.g));
    }
}
