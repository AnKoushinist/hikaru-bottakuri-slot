package jp.maio.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.VideoView;
import jp.maio.sdk.android.view.util.a;

public class ad extends Activity {
    OnTouchListener a = new ag(this);
    Handler b = new Handler();
    Runnable c = new ah(this);
    private a d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View frameLayout = new FrameLayout(this);
        frameLayout.setId(1);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1, 17));
        View frameLayout2 = new FrameLayout(this);
        frameLayout2.setId(2);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        frameLayout2.setLayoutParams(layoutParams);
        View videoView = new VideoView(this);
        videoView.setId(3);
        videoView.setLayoutParams(layoutParams);
        videoView.setKeepScreenOn(true);
        frameLayout.addView(videoView);
        frameLayout.addView(frameLayout2);
        setContentView(frameLayout);
        frameLayout = findViewById(2);
        frameLayout2 = findViewById(3);
        this.d = a.a(this, frameLayout2, 6);
        this.d.a();
        this.d.a(new ae(this, frameLayout));
        frameLayout2.setOnClickListener(new af(this));
    }
}
