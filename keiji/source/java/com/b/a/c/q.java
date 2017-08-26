package com.b.a.c;

import a.a.a.a.a.b.i;
import a.a.a.a.a.g.o;
import android.content.Context;

/* compiled from: DialogStringResolver */
class q {
    private final Context a;
    private final o b;

    public q(Context context, o oVar) {
        this.a = context;
        this.b = oVar;
    }

    public String a() {
        return a("com.crashlytics.CrashSubmissionPromptTitle", this.b.a);
    }

    public String b() {
        return a("com.crashlytics.CrashSubmissionPromptMessage", this.b.b);
    }

    public String c() {
        return a("com.crashlytics.CrashSubmissionSendTitle", this.b.c);
    }

    public String d() {
        return a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.b.g);
    }

    public String e() {
        return a("com.crashlytics.CrashSubmissionCancelTitle", this.b.e);
    }

    private String a(String str, String str2) {
        return b(i.b(this.a, str), str2);
    }

    private String b(String str, String str2) {
        return a(str) ? str2 : str;
    }

    private boolean a(String str) {
        return str == null || str.length() == 0;
    }
}
