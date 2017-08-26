package com.b.a.c;

/* compiled from: CrashlyticsMissingDependencyException */
public class j extends RuntimeException {
    j(String str) {
        super(a(str));
    }

    private static String a(String str) {
        return "\n" + str + "\n";
    }
}
