package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pl implements MembersInjector<AndroidDevice> {
    static final /* synthetic */ boolean a = (!pl.class.desiredAssertionStatus());
    private final Provider<lr> b;
    private final Provider<WindowManager> c;
    private final Provider<Context> d;
    private final Provider<ql> e;
    private final Provider<SharedPreferences> f;
    private final Provider<DeviceIdStrategy> g;
    private final Provider<String> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        AndroidDevice androidDevice = (AndroidDevice) obj;
        if (androidDevice == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        androidDevice.g = (lr) this.b.get();
        androidDevice.h = (WindowManager) this.c.get();
        androidDevice.i = (Context) this.d.get();
        androidDevice.j = (ql) this.e.get();
        androidDevice.k = (SharedPreferences) this.f.get();
        androidDevice.l = (DeviceIdStrategy) this.g.get();
        androidDevice.m = (String) this.h.get();
    }

    private pl(Provider<lr> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<ql> provider4, Provider<SharedPreferences> provider5, Provider<DeviceIdStrategy> provider6, Provider<String> provider7) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    return;
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AndroidDevice> a(Provider<lr> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<ql> provider4, Provider<SharedPreferences> provider5, Provider<DeviceIdStrategy> provider6, Provider<String> provider7) {
        return new pl(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
