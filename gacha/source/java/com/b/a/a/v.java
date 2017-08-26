package com.b.a.a;

import android.content.Context;
import b.a.a.a.a.b.i;
import b.a.a.a.a.b.o;
import b.a.a.a.a.b.o.a;
import java.util.Map;
import java.util.UUID;

/* compiled from: SessionMetadataCollector */
class v {
    private final Context a;
    private final o b;
    private final String c;
    private final String d;

    public v(Context context, o oVar, String str, String str2) {
        this.a = context;
        this.b = oVar;
        this.c = str;
        this.d = str2;
    }

    public t a() {
        Map i = this.b.i();
        return new t(this.b.c(), UUID.randomUUID().toString(), this.b.b(), (String) i.get(a.ANDROID_ID), (String) i.get(a.ANDROID_ADVERTISING_ID), this.b.l(), (String) i.get(a.FONT_TOKEN), i.m(this.a), this.b.d(), this.b.g(), this.c, this.d);
    }
}
