package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class ad {
    protected final a a;
    protected final ap b;
    protected bh c;
    private final String d;
    private final String e = String.format("_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private WeakReference f;
    private WeakReference g;
    private WebView h;
    private boolean i = false;
    private boolean j;
    private final LinkedList k = new LinkedList();

    ad(String str, ap apVar, a aVar) {
        this.d = str;
        this.b = apVar;
        this.a = aVar;
        this.f = new WeakReference(aVar.c());
    }

    void a() {
        int i;
        int i2;
        if (this.k.size() >= HttpResponseCode.OK) {
            LinkedList linkedList = new LinkedList();
            for (i = 0; i < 10; i++) {
                linkedList.addFirst((String) this.k.removeFirst());
            }
            i = Math.min(Math.min(this.k.size() / HttpResponseCode.OK, 10) + HttpResponseCode.OK, this.k.size());
            for (i2 = 0; i2 < i; i2++) {
                this.k.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.k.addFirst((String) it.next());
            }
        }
        i2 = 0;
        while (!this.k.isEmpty() && i2 < HttpResponseCode.OK) {
            i2++;
            String str = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            i = 1;
            while (!this.k.isEmpty() && i2 < HttpResponseCode.OK) {
                int i3 = i2 + 1;
                String str2 = (String) this.k.getFirst();
                if (stringBuilder.length() + str2.length() > 2000) {
                    i2 = i3;
                    break;
                }
                this.k.removeFirst();
                if (i != 0) {
                    i = 0;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str2);
                i2 = i3;
            }
            a(String.format(str, new Object[]{this.e, stringBuilder.toString()}));
        }
        this.k.clear();
    }

    public void a(View view) {
        this.g = new WeakReference(view);
        if (this.c != null) {
            this.c.a(view);
        }
    }

    public void a(View view, Map map, Integer num, Integer num2, Integer num3) {
        this.g = new WeakReference(view);
        this.h = new WebView((Context) this.f.get());
        WebSettings settings = this.h.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.h.setWebViewClient(new ae(this));
        JSONObject jSONObject = new JSONObject(map);
        this.h.loadData(String.format("<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), this.e, this.d, jSONObject.toString(), num3}), "text/html", null);
    }

    void a(String str) {
        this.h.loadUrl(str);
    }

    public void a(JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (!this.i || this.h == null) {
            this.k.add(jSONObject2);
            return;
        }
        this.h.loadUrl(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{this.e, jSONObject2}));
    }

    public void b() {
        if (!this.j) {
            if (this.c != null) {
                this.c.d();
                this.c = null;
            }
            if (this.h != null) {
                this.h.loadUrl("about:blank");
                this.h.destroy();
                this.h = null;
            }
            this.j = true;
        }
    }
}
