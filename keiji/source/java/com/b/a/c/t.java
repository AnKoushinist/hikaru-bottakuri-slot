package com.b.a.c;

import a.a.a.a.a.b.i;
import a.a.a.a.c;
import android.content.Context;
import java.io.File;
import java.util.Set;

/* compiled from: LogFileManager */
class t {
    private static final a a = new a();
    private final Context b;
    private final a.a.a.a.a.f.a c;
    private s d;

    /* compiled from: LogFileManager */
    private static final class a implements s {
        private a() {
        }

        public b a() {
            return null;
        }

        public void b() {
        }

        public void c() {
        }
    }

    public t(Context context, a.a.a.a.a.f.a aVar) {
        this(context, aVar, null);
    }

    public t(Context context, a.a.a.a.a.f.a aVar, String str) {
        this.b = context;
        this.c = aVar;
        this.d = a;
        a(str);
    }

    public final void a(String str) {
        this.d.b();
        this.d = a;
        if (str != null) {
            if (c()) {
                a(b(str), 65536);
            } else {
                c.h().a("CrashlyticsCore", "Preferences requested no custom logs. Aborting log file creation.");
            }
        }
    }

    public b a() {
        return this.d.a();
    }

    public void b() {
        this.d.c();
    }

    public void a(Set<String> set) {
        File[] listFiles = d().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!set.contains(a(file))) {
                    file.delete();
                }
            }
        }
    }

    void a(File file, int i) {
        this.d = new y(file, i);
    }

    private File b(String str) {
        return new File(d(), "crashlytics-userlog-" + str + ".temp");
    }

    private String a(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".temp");
        return lastIndexOf == -1 ? name : name.substring("crashlytics-userlog-".length(), lastIndexOf);
    }

    private boolean c() {
        return i.a(this.b, "com.crashlytics.CollectCustomLogs", true);
    }

    private File d() {
        File file = new File(this.c.a(), "log-files");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
