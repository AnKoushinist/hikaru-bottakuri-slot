package jp.co.geniee.gnadsdk.interstitial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import jp.co.geniee.gnadsdk.a.a;
import twitter4j.TwitterResponse;

public class GNInterstitialActivity extends Activity implements d {
    a a;
    private final a b = new a("GNAdSDK", Integer.MAX_VALUE);
    private b c;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.b.a(intent.getIntExtra("log_priority", Integer.MAX_VALUE));
        }
        this.b.a("GNInterstitialActivity", "onCreate call");
        if (this.a == null) {
            this.a = a.b();
        }
        if (this.a != null) {
            requestWindowFeature(1);
            this.c = a.c();
            this.c.a(this);
            this.c.a((d) this);
            setContentView(this.c);
            return;
        }
        finish();
    }

    public void onBackPressed() {
        if (this.c.a()) {
            this.c.b();
            return;
        }
        if (this.c != null) {
            this.c.c();
        }
        finish();
        if (this.a != null && this.a.e() != null) {
            this.a.e().a();
            this.a.a(false);
        }
    }

    public void a(int i, String str) {
        this.b.a("GNInterstitialActivity", "onShowWebPage : " + str);
        switch (i) {
            case TwitterResponse.READ_WRITE /*2*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return;
            default:
                return;
        }
    }

    protected void onPause() {
        super.onPause();
        this.b.a("GNInterstitialActivity", "onPause call");
        if (this.a != null) {
            this.a.a(false);
        }
        if (this.c != null) {
            this.c.c();
        }
    }

    protected void onStop() {
        super.onStop();
        this.b.a("GNInterstitialActivity", "onStop call");
    }

    protected void onStart() {
        super.onStart();
        this.b.a("GNInterstitialActivity", "onStart call");
    }

    protected void onResume() {
        this.b.a("GNInterstitialActivity", "onResume call");
        super.onResume();
        if (this.a != null) {
            this.a.a(true);
        }
        if (this.c != null) {
            this.c.d();
        }
    }

    protected void onDestroy() {
        this.b.a("GNInterstitialActivity", "onDestroy call");
        if (this.c != null) {
            ViewGroup viewGroup = (ViewGroup) this.c.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.c);
            }
            this.c.e();
        }
        if (this.a != null) {
            this.a.a();
        }
        super.onDestroy();
    }
}
