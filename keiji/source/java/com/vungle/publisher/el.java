package com.vungle.publisher;

/* compiled from: vungle */
public interface el<A extends cj> extends gr<Integer> {

    /* compiled from: vungle */
    public enum a {
        aware,
        downloaded,
        ready,
        failed
    }

    /* compiled from: vungle */
    public enum b {
        localVideo,
        postRoll,
        streamingVideo,
        template,
        asset
    }

    void a(a aVar);

    void b(a aVar);

    String d();

    String l();

    a s();

    b t();
}
