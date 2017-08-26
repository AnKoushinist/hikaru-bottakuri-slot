package com.tapjoy.internal;

import android.graphics.Bitmap;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.at.a;
import java.net.URL;

public final class gk {
    public static final bm c = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            return new gk(brVar);
        }
    };
    private static final ar d;
    public URL a;
    public Bitmap b;

    static {
        ar avVar = new av();
        if (!(avVar instanceof aw)) {
            Object aVar = new a((au) avVar);
        }
        d = avVar;
    }

    public gk(URL url) {
        this.a = url;
    }

    public final void a() {
        this.b = (Bitmap) d.a(this.a);
        if (this.b == null) {
            u uVar = u.a;
            this.b = u.a(eh.a(this.a));
            d.a(this.a, this.b);
        }
    }

    gk(br brVar) {
        Object obj;
        if (brVar.k() == bw.STRING) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.a = brVar.e();
            return;
        }
        brVar.h();
        String l = brVar.l();
        while (brVar.j()) {
            if (String.URL.equals(l)) {
                this.a = brVar.e();
            } else {
                brVar.s();
            }
        }
        brVar.i();
    }
}
