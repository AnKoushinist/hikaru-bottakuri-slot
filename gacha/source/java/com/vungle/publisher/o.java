package com.vungle.publisher;

import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class o<R> {

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[j.values().length];

        static {
            try {
                a[j.vungle_local.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[j.vungle_streaming.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[j.vungle_mraid.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[j.third_party_mraid.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public abstract R a();

    public abstract R b();

    public abstract R c();

    public abstract R d();

    public final R a(j jVar) {
        switch (AnonymousClass1.a[jVar.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return a();
            case TwitterResponse.READ_WRITE /*2*/:
                return b();
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return c();
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return d();
            default:
                throw new IllegalArgumentException("unknown ad type " + jVar);
        }
    }

    public final R a(cj cjVar) {
        return a(cjVar.f());
    }
}
