package com.glossomads.Listener;

public interface GlossomAdsAdListener {
    void onGlossomAdsVideoClose(String str);

    void onGlossomAdsVideoFinish(String str, boolean z);

    void onGlossomAdsVideoPause(String str);

    void onGlossomAdsVideoResume(String str);

    void onGlossomAdsVideoStart(String str);
}
