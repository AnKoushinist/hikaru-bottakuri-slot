package com.vungle.publisher;

import dagger.a.c;

/* compiled from: vungle */
public enum Demographic_Factory implements c<Demographic> {
    ;

    private Demographic_Factory(String str) {
    }

    public final Demographic get() {
        return new Demographic();
    }

    public static c<Demographic> create() {
        return INSTANCE;
    }
}
