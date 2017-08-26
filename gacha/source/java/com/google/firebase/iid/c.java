package com.google.firebase.iid;

@Deprecated
public class c {
    private final FirebaseInstanceId a;

    private c(FirebaseInstanceId firebaseInstanceId) {
        this.a = firebaseInstanceId;
    }

    public static c a() {
        return new c(FirebaseInstanceId.a());
    }

    public String b() {
        return this.a.c();
    }
}
