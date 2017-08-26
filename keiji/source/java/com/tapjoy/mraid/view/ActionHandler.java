package com.tapjoy.mraid.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.controller.Abstract.PlayerProperties;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import com.tapjoy.mraid.view.MraidView.Action;
import java.util.HashMap;
import java.util.Map.Entry;
import twitter4j.TwitterResponse;

public class ActionHandler extends Activity {
    private HashMap a = new HashMap();
    private RelativeLayout b;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[Action.values().length];

        static {
            try {
                a[Action.PLAY_AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Action.PLAY_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Bundle extras = getIntent().getExtras();
        this.b = new RelativeLayout(this);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
        this.b.setBackgroundColor(-16777216);
        setContentView(this.b);
        String string = extras.getString(MraidView.ACTION_KEY);
        if (string != null) {
            Action valueOf = Action.valueOf(string);
            switch (AnonymousClass2.a[valueOf.ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    a(extras, valueOf).playAudio();
                    return;
                case TwitterResponse.READ_WRITE /*2*/:
                    a(extras, valueOf).playVideo();
                    return;
                default:
                    return;
            }
        }
    }

    private MraidPlayer a(Bundle bundle, Action action) {
        LayoutParams layoutParams;
        PlayerProperties playerProperties = (PlayerProperties) bundle.getParcelable(MraidView.PLAYER_PROPERTIES);
        Dimensions dimensions = (Dimensions) bundle.getParcelable(MraidView.DIMENSIONS);
        View mraidPlayer = new MraidPlayer(this);
        mraidPlayer.setPlayData(playerProperties, Utils.getData(MraidView.EXPAND_URL, bundle));
        if (playerProperties.inline || !playerProperties.startStyle.equals(Abstract.FULL_SCREEN)) {
            layoutParams = new RelativeLayout.LayoutParams(dimensions.width, dimensions.height);
            layoutParams.topMargin = dimensions.y;
            layoutParams.leftMargin = dimensions.x;
        } else {
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
        }
        mraidPlayer.setLayoutParams(layoutParams);
        this.b.addView(mraidPlayer);
        this.a.put(action, mraidPlayer);
        mraidPlayer.setListener(new Player(this) {
            final /* synthetic */ ActionHandler a;

            {
                this.a = r1;
            }

            public final void onPrepared() {
            }

            public final void onError() {
                this.a.finish();
            }

            public final void onComplete() {
                this.a.finish();
            }
        });
        return mraidPlayer;
    }

    protected void onStop() {
        for (Entry entry : this.a.entrySet()) {
            switch (AnonymousClass2.a[((Action) entry.getKey()).ordinal()]) {
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                    ((MraidPlayer) entry.getValue()).releasePlayer();
                    break;
                default:
                    break;
            }
        }
        super.onStop();
    }
}
