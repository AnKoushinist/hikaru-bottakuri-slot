package com.vungle.publisher;

import java.net.HttpURLConnection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class vy {
    public HttpURLConnection a;
    int b;
    String c;
    List<vt> d;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<vy> a;

        @Inject
        a() {
        }
    }

    @Inject
    vy() {
    }
}
