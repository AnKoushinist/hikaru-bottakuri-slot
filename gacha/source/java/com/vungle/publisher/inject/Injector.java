package com.vungle.publisher.inject;

import com.vungle.log.Logger;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.re;
import com.vungle.publisher.rz;
import com.vungle.publisher.sc;

/* compiled from: vungle */
public class Injector {
    private static final Injector e = new Injector();
    public boolean a;
    public EndpointModule b;
    public rz c;
    public sc d;
    private re f;

    public static Injector getInstance() {
        return e;
    }

    private Injector() {
    }

    public void setWrapperFramework(WrapperFramework wrapperFramework) {
        try {
            if (this.a) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework in injector: " + wrapperFramework);
            re a = a();
            if (a.g) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in publisher module NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + wrapperFramework);
            a.e = wrapperFramework;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFrameworkVersion(String str) {
        try {
            if (this.a) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework version in injector: " + str);
            re a = a();
            if (a.g) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in publisher module NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + str);
            a.f = str;
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public final re a() {
        if (this.f == null) {
            this.f = new re();
        }
        return this.f;
    }

    public Injector setEndpointModule(EndpointModule endpointModule) {
        this.b = endpointModule;
        this.a = false;
        return this;
    }

    public static sc b() {
        return getInstance().d;
    }
}
