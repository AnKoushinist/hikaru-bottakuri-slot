package com.b.a.c;

import b.a.a.a.a.b.g;
import b.a.a.a.a.b.h;
import b.a.a.a.c;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: ReportUploader */
class aa {
    static final Map<String, String> a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    private static final FilenameFilter b = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".cls") && !str.contains("Session");
        }
    };
    private static final short[] c = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    private final Object d = new Object();
    private final n e;
    private Thread f;

    /* compiled from: ReportUploader */
    private class a extends h {
        final /* synthetic */ aa a;
        private final float b;

        a(aa aaVar, float f) {
            this.a = aaVar;
            this.b = f;
        }

        public void a() {
            try {
                b();
            } catch (Throwable e) {
                c.h().e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            this.a.f = null;
        }

        private void b() {
            c.h().a("CrashlyticsCore", "Starting report processing in " + this.b + " second(s)...");
            if (this.b > 0.0f) {
                try {
                    Thread.sleep((long) (this.b * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            f f = f.f();
            l o = f.o();
            List<z> a = this.a.a();
            if (!o.a()) {
                if (a.isEmpty() || f.z()) {
                    List list = a;
                    int i = 0;
                    while (!r0.isEmpty() && !f.f().o().a()) {
                        c.h().a("CrashlyticsCore", "Attempting to send " + r0.size() + " report(s)");
                        for (z a2 : r0) {
                            this.a.a(a2);
                        }
                        List a3 = this.a.a();
                        if (a3.isEmpty()) {
                            list = a3;
                        } else {
                            int i2 = i + 1;
                            long j = (long) aa.c[Math.min(i, aa.c.length - 1)];
                            c.h().a("CrashlyticsCore", "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                                list = a3;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                c.h().a("CrashlyticsCore", "User declined to send. Removing " + a.size() + " Report(s).");
                for (z a22 : a) {
                    a22.a();
                }
            }
        }
    }

    public aa(n nVar) {
        if (nVar == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.e = nVar;
    }

    public synchronized void a(float f) {
        if (this.f == null) {
            this.f = new Thread(new a(this, f), "Crashlytics Report Uploader");
            this.f.start();
        }
    }

    boolean a(z zVar) {
        boolean z = false;
        synchronized (this.d) {
            try {
                boolean a = this.e.a(new m(new g().a(f.f().E()), zVar));
                c.h().c("CrashlyticsCore", "Crashlytics report upload " + (a ? "complete: " : "FAILED: ") + zVar.b());
                if (a) {
                    zVar.a();
                    z = true;
                }
            } catch (Throwable e) {
                c.h().e("CrashlyticsCore", "Error occurred sending report " + zVar, e);
            }
        }
        return z;
    }

    List<z> a() {
        c.h().a("CrashlyticsCore", "Checking for crash reports...");
        synchronized (this.d) {
            File[] listFiles = f.f().w().listFiles(b);
        }
        List<z> linkedList = new LinkedList();
        for (File file : listFiles) {
            c.h().a("CrashlyticsCore", "Found crash report " + file.getPath());
            linkedList.add(new ac(file));
        }
        if (linkedList.isEmpty()) {
            c.h().a("CrashlyticsCore", "No reports found.");
        }
        return linkedList;
    }
}
