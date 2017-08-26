package com.adcolony.sdk;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyInterstitialActivity extends ah {
    g a;

    public AdColonyInterstitialActivity() {
        this.a = !n.b() ? null : n.a().o();
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void onCreate(Bundle bundle) {
        this.f = this.a == null ? 0 : this.a.f();
        super.onCreate(bundle);
        if (n.b() && this.a != null && this.a.b() != null) {
            this.a.b().onOpened(this.a);
        }
    }

    void a(o oVar) {
        super.a(oVar);
        am h = n.a().h();
        ao aoVar = (ao) h.e().remove(this.g);
        if (aoVar != null) {
            for (MediaPlayer mediaPlayer : aoVar.c().c().values()) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
            aoVar.d().a().autoPause();
            aoVar.d().a().release();
        }
        JSONObject e = bb.e(oVar.b(), "v4iap");
        JSONArray f = bb.f(e, "product_ids");
        if (!(e == null || this.a == null || this.a.b() == null || f.length() <= 0)) {
            this.a.b().onIAPEvent(this.a, bb.b(f, 0), bb.b(e, "engagement_type"));
        }
        h.a(this.e);
        if (this.a != null) {
            h.c().remove(this.a.g());
        }
        if (!(this.a == null || this.a.b() == null)) {
            this.a.b().onClosed(this.a);
            this.a.a(null);
            this.a.a(null);
            this.a = null;
        }
        bd.d.b((Object) "finish_ad call finished");
        System.gc();
    }
}
