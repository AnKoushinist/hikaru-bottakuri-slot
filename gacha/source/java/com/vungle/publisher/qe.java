package com.vungle.publisher;

import com.vungle.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public class qe implements qn {
    private boolean a;
    @Inject
    public ql eventBus;

    public void register() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.eventBus.b(this);
        this.a = true;
    }

    public void registerSticky() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening sticky");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening sticky");
        this.eventBus.a.a((Object) this, "onEvent", true);
        this.a = true;
    }

    public void unregister() {
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " unregistered");
        this.eventBus.a.a((Object) this);
        this.a = false;
    }

    public void registerOnce() {
        if (this.a) {
            Logger.v(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.eventBus.b(this);
        this.a = true;
    }
}
