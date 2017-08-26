package com.vungle.publisher;

import android.content.Context;

/* compiled from: vungle */
public class VunglePub extends VunglePubBase {
    public static final String VERSION = "VungleDroid/4.0.3";
    private static final VunglePub o = new VunglePub();

    public static VunglePub getInstance() {
        return o;
    }

    VunglePub() {
    }

    public boolean init(Context context, String str) {
        return super.init(context, str);
    }

    public Demographic getDemographic() {
        return super.getDemographic();
    }

    public void addEventListeners(EventListener... eventListenerArr) {
        super.addEventListeners(eventListenerArr);
    }

    public void clearEventListeners() {
        super.clearEventListeners();
    }

    public void setEventListeners(EventListener... eventListenerArr) {
        super.setEventListeners(eventListenerArr);
    }

    public void removeEventListeners(EventListener... eventListenerArr) {
        super.removeEventListeners(eventListenerArr);
    }
}
