package com.glossomads.View;

class e implements Runnable {
    final /* synthetic */ SugarAdView a;

    e(SugarAdView sugarAdView) {
        this.a = sugarAdView;
    }

    public void run() {
        this.a.thumbnailView.setVisibility(4);
    }
}
