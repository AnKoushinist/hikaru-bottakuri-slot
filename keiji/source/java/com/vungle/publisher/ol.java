package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.oj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ol implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ol.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<nb> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.b.get();
        aVar.b = (nb) this.c.get();
    }

    private ol(Provider<Context> provider, Provider<nb> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<Context> provider, Provider<nb> provider2) {
        return new ol(provider, provider2);
    }
}
