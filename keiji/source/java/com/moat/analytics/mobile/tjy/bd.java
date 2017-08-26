package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;

public class bd extends f implements ReactiveVideoTracker {
    private Integer j;

    public bd(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    protected Map a() {
        Object valueOf;
        Object valueOf2;
        Map hashMap = new HashMap();
        View view = (View) this.g.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.j);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    protected JSONObject a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE && !a(moatAdEvent.adPlayhead, this.j)) {
            moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.a(moatAdEvent);
    }

    public /* bridge */ /* synthetic */ boolean a(Map map, Object obj, View view) {
        return super.a(map, obj, view);
    }

    public /* bridge */ /* synthetic */ void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    public /* bridge */ /* synthetic */ void setDebug(boolean z) {
        super.setDebug(z);
    }

    public boolean trackVideoAd(Map map, Integer num, View view) {
        if (num.intValue() < GameControllerDelegate.THUMBSTICK_LEFT_X) {
            a(String.format("Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        }
        this.j = num;
        return super.a(map, new Object(), view);
    }
}
