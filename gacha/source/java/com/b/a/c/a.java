package com.b.a.c;

import android.util.Log;
import b.a.a.a.a.b.i;
import b.a.a.a.c;

/* compiled from: BuildIdValidator */
class a {
    private final String a;
    private final boolean b;

    public a(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void a(String str, String str2) {
        if (i.c(this.a) && this.b) {
            String b = b(str, str2);
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".     |  | ");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".   \\ |  | /");
            Log.e("CrashlyticsCore", ".    \\    /");
            Log.e("CrashlyticsCore", ".     \\  /");
            Log.e("CrashlyticsCore", ".      \\/");
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", b);
            Log.e("CrashlyticsCore", ".");
            Log.e("CrashlyticsCore", ".      /\\");
            Log.e("CrashlyticsCore", ".     /  \\");
            Log.e("CrashlyticsCore", ".    /    \\");
            Log.e("CrashlyticsCore", ".   / |  | \\");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".     |  |");
            Log.e("CrashlyticsCore", ".");
            throw new j(b);
        } else if (!this.b) {
            c.h().a("CrashlyticsCore", "Configured not to require a build ID.");
        }
    }

    protected String b(String str, String str2) {
        return "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    }
}
