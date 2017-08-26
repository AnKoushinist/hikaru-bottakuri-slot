package com.e.a.a.a;

import android.view.View;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;

public class ab extends am implements d {
    private Integer j;

    public ab(String str, f fVar, n nVar) {
        super(str, fVar, nVar);
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

    public /* bridge */ /* synthetic */ void a(a aVar) {
        super.a(aVar);
    }

    public boolean a(Map map, Integer num, View view) {
        if (num.intValue() < GameControllerDelegate.THUMBSTICK_LEFT_X) {
            a(String.format("Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        }
        this.j = num;
        return super.a(map, new Object(), view);
    }

    protected JSONObject b(a aVar) {
        if (aVar.e == b.AD_EVT_COMPLETE && !a(aVar.c, this.j)) {
            aVar.e = b.AD_EVT_STOPPED;
        }
        return super.b(aVar);
    }
}
