package com.vungle.publisher;

import android.content.Context;
import android.view.WindowManager;
import com.vungle.log.Logger;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rt implements c<WindowManager> {
    static final /* synthetic */ boolean a = (!rt.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        WindowManager windowManager = (WindowManager) ((Context) this.c.get()).getSystemService("window");
        if (windowManager == null) {
            Logger.d(Logger.DEVICE_TAG, "WindowManager not available");
        }
        return (WindowManager) e.a(windowManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rt(re reVar, Provider<Context> provider) {
        if (a || reVar != null) {
            this.b = reVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<WindowManager> a(re reVar, Provider<Context> provider) {
        return new rt(reVar, provider);
    }
}
