package com.e.a.a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.cocos2dx.lib.BuildConfig;

class aq implements ao {
    private final ScheduledExecutorService a;
    private ScheduledFuture b;
    private ScheduledFuture c;
    private final n d;
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private WebView h;
    private ap i;

    aq(Context context, n nVar) {
        this.d = nVar;
        this.a = Executors.newScheduledThreadPool(1);
    }

    private void b() {
        try {
            if (this.d.a() != p.OFF) {
                if (this.d.b() && !this.g) {
                    Log.d("MoatJavaScriptBridge", "Ready for communication (setting environment variables).");
                    this.g = true;
                }
                this.h.loadUrl(String.format("javascript:(function(b,f){function g(){function b(a,e){for(k in a)if(a.hasOwnProperty(k)){var c=a[k].fn;if('function'===typeof c)try{e?c(e):c()}catch(d){}}}function d(a,b,c){'function'===typeof a&&(c[b]={ts:+new Date,fn:a})}bjmk={};uqaj={};yhgt={};ryup=dptk=!1;this.a=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash};this.bpsy=function(a){dptk||ryup||d(a,+new Date,bjmk)};this.qmrv=function(a){ryup||d(a,+new Date,uqaj)};this.lgpr=function(a,b){d(a,b,yhgt)};this.xrnk=function(a){yhgt.hasOwnProperty(a)&&delete yhgt[a]};this.vgft=function(){return dptk};this.lkpu=function(){return ryup};this.mqjh=function(){dptk||ryup||(dptk=!0,b(bjmk))};this.egpw=function(){ryup||(ryup=!0,b(uqaj))};this.sglu=function(a){b(yhgt,a);return 0<Object.keys(yhgt).length}}'undefined'===typeof b.MoatMAK&&(b.MoatMAK=new g,b.MoatMAK.a(f),b.__zMoatInit__=!0)})(window,%s);", new Object[]{this.i.e()}));
            }
        } catch (Throwable e) {
            if (this.d.b()) {
                Log.e("MoatJavaScriptBridge", "Failed to initialize communication (did not set environment variables).", e);
            }
        }
    }

    @TargetApi(19)
    private void c() {
        if (this.d.a() != p.OFF) {
            if (this.h == null || (this.f && this.h.getUrl() == null)) {
                if (this.d.b()) {
                    Log.d("MoatJavaScriptBridge", "WebView became null" + (this.h == null ? BuildConfig.FLAVOR : "based on null url") + ", stopping tracking loop");
                }
                g();
                return;
            }
            if (this.h.getUrl() != null) {
                this.f = true;
            }
            String format = String.format("MoatMAK.sglu(%s)", new Object[]{this.i.c()});
            if (VERSION.SDK_INT >= 19) {
                this.h.evaluateJavascript(format, new as(this));
            } else {
                this.h.loadUrl("javascript:" + format);
            }
        }
    }

    private void d() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting metadata reporting loop");
        }
        this.c = this.a.scheduleWithFixedDelay(new at(this), 0, 50, TimeUnit.MILLISECONDS);
    }

    private void e() {
        if (this.c != null) {
            if (!this.c.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping metadata reporting loop");
            }
            this.c.cancel(true);
        }
    }

    private void f() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting view update loop");
        }
        this.b = this.a.scheduleWithFixedDelay(new av(this), 0, (long) this.d.c(), TimeUnit.MILLISECONDS);
    }

    private void g() {
        if (this.b != null) {
            if (this.b.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping view update loop");
            }
            this.b.cancel(true);
        }
    }

    public void a() {
        if (this.d.a() != p.OFF) {
            e();
            g();
        }
    }

    public boolean a(WebView webView, ap apVar) {
        boolean b = this.d.b();
        if (webView.getSettings().getJavaScriptEnabled()) {
            this.h = webView;
            this.i = apVar;
            d();
            f();
            this.a.schedule(new ar(this), 10, TimeUnit.SECONDS);
            return true;
        }
        if (b) {
            Log.e("MoatJavaScriptBridge", "JavaScript is not enabled in the given WebView. Can't track.");
        }
        return false;
    }
}
