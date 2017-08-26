package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.log.Logger;
import com.vungle.publisher.qf.a;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class pv {
    public final Map<EventListener, qn> a = new HashMap();
    public boolean b;
    public final Set<un> c = EnumSet.noneOf(un.class);
    public int d;
    public String e = "isExceptionReportingEnabled";
    public long f;
    public long g;
    @Inject
    a h;
    @Inject
    Context i;
    @Inject
    public SharedPreferences j;

    @Inject
    pv() {
    }

    public final void a(EventListener... eventListenerArr) {
        if (eventListenerArr != null) {
            for (EventListener eventListener : eventListenerArr) {
                if (eventListener == null) {
                    Logger.d(Logger.EVENT_TAG, "ignoring add null event listener");
                } else {
                    if ((!this.a.containsKey(eventListener) ? 1 : null) != null) {
                        Logger.d(Logger.EVENT_TAG, "adding event listener " + eventListener);
                        qf qfVar = (qf) this.h.a.get();
                        qfVar.a = eventListener;
                        this.a.put(eventListener, qfVar);
                        qfVar.registerSticky();
                    } else {
                        Logger.d(Logger.EVENT_TAG, "already added event listener " + eventListener);
                    }
                }
            }
        }
    }

    public final void a() {
        for (qn unregister : this.a.values()) {
            unregister.unregister();
        }
        this.a.clear();
    }

    public final void a(un... unVarArr) {
        Logger.d(Logger.CONFIG_TAG, "setting ad streaming connectivity types " + ags.b((Object[]) unVarArr));
        this.c.clear();
        if (unVarArr != null) {
            for (Object obj : unVarArr) {
                if (obj != null) {
                    this.c.add(obj);
                }
            }
        }
    }

    public final boolean b() {
        Logger.d(Logger.CONFIG_TAG, "isExceptionReportingEnabled: " + this.j.getBoolean(this.e, false));
        return this.j.getBoolean(this.e, false);
    }

    public final void a(long j) {
        Logger.d(Logger.CONFIG_TAG, "setting last app fingerprint timestamp to " + j);
        this.f = j;
    }
}
