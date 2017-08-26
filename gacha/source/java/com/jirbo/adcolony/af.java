package com.jirbo.adcolony;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.Serializable;
import org.cocos2dx.lib.BuildConfig;

class af implements Serializable {
    String a = BuildConfig.FLAVOR;
    int b;
    int c;
    int d;

    af() {
    }

    af(String str) {
        this.a = str;
    }

    boolean a(g gVar) {
        if (gVar == null) {
            return false;
        }
        this.a = gVar.a("uuid", String.VIDEO_ERROR);
        this.b = gVar.g("skipped_plays");
        this.c = gVar.g("play_order_index");
        return true;
    }

    g a() {
        g gVar = new g();
        gVar.b("uuid", this.a);
        gVar.b("skipped_plays", this.b);
        gVar.b("play_order_index", this.c);
        return gVar;
    }
}
