package com.adcolony.sdk;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

class ar implements q {
    ar() {
        n.a("CustomMessage.controller_send", (q) this);
    }

    public void a(o oVar) {
        JSONObject b = oVar.b();
        final String a = bb.a(b, MoatAdEvent.EVENT_TYPE);
        final String a2 = bb.a(b, String.MESSAGE);
        ab.a(new Runnable(this) {
            final /* synthetic */ ar c;

            public void run() {
                bd.d.b("Received custom message " + a2 + " of type " + a);
                try {
                    f fVar = (f) n.a().s().get(a);
                    if (fVar != null) {
                        fVar.a(new e(a, a2));
                    }
                } catch (RuntimeException e) {
                }
            }
        });
    }
}
