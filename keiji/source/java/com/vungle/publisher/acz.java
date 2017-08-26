package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class acz extends ace {
    protected String t;

    /* compiled from: vungle */
    public static abstract class a<O extends acz, T extends cq<T, P, E, A>, P extends cp<T, P, E>, E extends cr<P>, A extends jk<A, ?, ?>> extends com.vungle.publisher.ace.a<P, O, T, A> {
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    protected acz() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt(String.URL, this.t);
        return a;
    }
}
