package com.b.a.c;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import b.a.a.a.a.b.i;
import b.a.a.a.a.b.o;
import b.a.a.a.a.g.p;
import b.a.a.a.a.g.q;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: CrashlyticsUncaughtExceptionHandler */
class l implements UncaughtExceptionHandler {
    static final FilenameFilter a = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.length() == ".cls".length() + 35 && str.endsWith(".cls");
        }
    };
    static final Comparator<File> b = new Comparator<File>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((File) obj, (File) obj2);
        }

        public int a(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    static final Comparator<File> c = new Comparator<File>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((File) obj, (File) obj2);
        }

        public int a(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    static final FilenameFilter d = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return l.e.matcher(str).matches();
        }
    };
    private static final Pattern e = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final Map<String, String> f = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    private static final String[] g = new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"};
    private final AtomicInteger h = new AtomicInteger(0);
    private final UncaughtExceptionHandler i;
    private final b.a.a.a.a.f.a j;
    private final AtomicBoolean k;
    private final g l;
    private final o m;
    private final f n;
    private final t o;
    private final p p;
    private final String q;

    /* compiled from: CrashlyticsUncaughtExceptionHandler */
    private static class a implements FilenameFilter {
        private a() {
        }

        public boolean accept(File file, String str) {
            return !l.a.accept(file, str) && l.e.matcher(str).matches();
        }
    }

    /* compiled from: CrashlyticsUncaughtExceptionHandler */
    static class b implements FilenameFilter {
        private final String a;

        public b(String str) {
            this.a = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.a) && !str.endsWith(".cls_temp");
        }
    }

    /* compiled from: CrashlyticsUncaughtExceptionHandler */
    private static final class c implements Runnable {
        private final f a;
        private final File b;

        public c(f fVar, File file) {
            this.a = fVar;
            this.b = file;
        }

        public void run() {
            if (i.n(this.a.E())) {
                b.a.a.a.c.h().a("CrashlyticsCore", "Attempting to send crash report at time of crash...");
                n a = this.a.a(q.a().b());
                if (a != null) {
                    new aa(a).a(new ac(this.b, l.f));
                }
            }
        }
    }

    /* compiled from: CrashlyticsUncaughtExceptionHandler */
    static class d implements FilenameFilter {
        private final String a;

        public d(String str) {
            this.a = str;
        }

        public boolean accept(File file, String str) {
            if (str.equals(this.a + ".cls") || !str.contains(this.a) || str.endsWith(".cls_temp")) {
                return false;
            }
            return true;
        }
    }

    l(UncaughtExceptionHandler uncaughtExceptionHandler, g gVar, o oVar, ad adVar, b.a.a.a.a.f.a aVar, f fVar) {
        this.i = uncaughtExceptionHandler;
        this.l = gVar;
        this.m = oVar;
        this.n = fVar;
        this.q = adVar.a();
        this.j = aVar;
        this.k = new AtomicBoolean(false);
        Context E = fVar.E();
        this.o = new t(E, aVar);
        this.p = new p(E);
    }

    public synchronized void uncaughtException(final Thread thread, final Throwable th) {
        this.k.set(true);
        try {
            b.a.a.a.c.h().a("CrashlyticsCore", "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
            this.p.b();
            final Date date = new Date();
            this.l.a(new Callable<Void>(this) {
                final /* synthetic */ l d;

                public /* synthetic */ Object call() {
                    return a();
                }

                public Void a() {
                    this.d.a(date, thread, th);
                    return null;
                }
            });
        } catch (Throwable e) {
            b.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the uncaught exception handler", e);
        } finally {
            b.a.a.a.c.h().a("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.i.uncaughtException(thread, th);
            this.k.set(false);
        }
    }

    private void a(Date date, Thread thread, Throwable th) {
        this.n.A();
        b(date, thread, th);
        d();
        l();
        f();
        if (!this.n.x()) {
            p();
        }
    }

    boolean a() {
        return this.k.get();
    }

    void b() {
        this.l.b(new Callable<Void>(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Void a() {
                this.a.l();
                return null;
            }
        });
    }

    private String j() {
        File[] n = n();
        return n.length > 0 ? a(n[0]) : null;
    }

    private String k() {
        File[] n = n();
        return n.length > 1 ? a(n[1]) : null;
    }

    private String a(File file) {
        return file.getName().substring(0, 35);
    }

    boolean c() {
        return ((Boolean) this.l.a(new Callable<Boolean>(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call() {
                return a();
            }

            public Boolean a() {
                if (this.a.k.get()) {
                    b.a.a.a.c.h().a("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                b.a.a.a.c.h().a("CrashlyticsCore", "Finalizing previously open sessions.");
                com.b.a.c.a.a.d v = this.a.n.v();
                if (v != null) {
                    this.a.a(v);
                }
                this.a.a(true);
                b.a.a.a.c.h().a("CrashlyticsCore", "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void l() {
        Date date = new Date();
        String cVar = new c(this.m).toString();
        b.a.a.a.c.h().a("CrashlyticsCore", "Opening an new session with ID " + cVar);
        a(cVar, date);
        c(cVar);
        d(cVar);
        e(cVar);
        this.o.a(cVar);
    }

    void d() {
        a(false);
    }

    private void a(boolean z) {
        int i = z ? 1 : 0;
        a(i + 8);
        File[] n = n();
        if (n.length <= i) {
            b.a.a.a.c.h().a("CrashlyticsCore", "No open sessions to be closed.");
            return;
        }
        f(a(n[i]));
        f fVar = this.n;
        p B = f.B();
        if (B == null) {
            b.a.a.a.c.h().a("CrashlyticsCore", "Unable to close session. Settings are not loaded.");
        } else {
            a(n, i, B.c);
        }
    }

    private void a(File[] fileArr, int i, int i2) {
        b.a.a.a.c.h().a("CrashlyticsCore", "Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String a = a(file);
            b.a.a.a.c.h().a("CrashlyticsCore", "Closing session: " + a);
            a(file, a, i2);
            i++;
        }
    }

    private void a(d dVar) {
        if (dVar != null) {
            try {
                dVar.a();
            } catch (Throwable e) {
                b.a.a.a.c.h().e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void a(String str) {
        for (File delete : b(str)) {
            delete.delete();
        }
    }

    private File[] b(String str) {
        return a(new d(str));
    }

    private File[] m() {
        return a(a);
    }

    File[] e() {
        return a(new b("BeginSession"));
    }

    private File[] n() {
        File[] e = e();
        Arrays.sort(e, b);
        return e;
    }

    private File[] a(FilenameFilter filenameFilter) {
        return b(q().listFiles(filenameFilter));
    }

    private File[] b(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void a(String str, int i) {
        af.a(q(), new b(str + "SessionEvent"), i, c);
    }

    void f() {
        af.a(q(), a, 4, c);
    }

    private void a(int i) {
        int i2 = 0;
        Set hashSet = new HashSet();
        File[] n = n();
        int min = Math.min(i, n.length);
        for (int i3 = 0; i3 < min; i3++) {
            hashSet.add(a(n[i3]));
        }
        this.o.a(hashSet);
        File[] a = a(new a());
        int length = a.length;
        while (i2 < length) {
            File file = a[i2];
            Object name = file.getName();
            Matcher matcher = e.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(1))) {
                b.a.a.a.c.h().a("CrashlyticsCore", "Trimming open session file: " + name);
                file.delete();
            }
            i2++;
        }
    }

    private File[] a(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        b.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
        a(str, i);
        return a(new b(str + "SessionEvent"));
    }

    void g() {
        this.l.a(new Runnable(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(this.a.a(d.a));
            }
        });
    }

    void a(File[] fileArr) {
        o();
        for (File file : fileArr) {
            b.a.a.a.c.h().a("CrashlyticsCore", "Found invalid session part file: " + file);
            final String a = a(file);
            FilenameFilter anonymousClass5 = new FilenameFilter(this) {
                final /* synthetic */ l b;

                public boolean accept(File file, String str) {
                    return str.startsWith(a);
                }
            };
            b.a.a.a.c.h().a("CrashlyticsCore", "Deleting all part files for invalid session: " + a);
            for (File file2 : a(anonymousClass5)) {
                b.a.a.a.c.h().a("CrashlyticsCore", "Deleting session file: " + file2);
                file2.delete();
            }
        }
    }

    private void o() {
        File file = new File(this.n.w(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
            file.delete();
        }
    }

    private void b(Date date, Thread thread, Throwable th) {
        Throwable e;
        Closeable closeable;
        Flushable flushable = null;
        try {
            String j = j();
            if (j == null) {
                b.a.a.a.c.h().e("CrashlyticsCore", "Tried to write a fatal exception while no session was open.", null);
                i.a(null, "Failed to flush to session begin file.");
                i.a(null, "Failed to close fatal exception file output stream.");
                return;
            }
            f.a(j);
            Closeable dVar = new d(q(), j + "SessionCrash");
            try {
                flushable = e.a((OutputStream) dVar);
                a(flushable, date, thread, th, "crash", true);
                i.a(flushable, "Failed to flush to session begin file.");
                i.a(dVar, "Failed to close fatal exception file output stream.");
            } catch (Exception e2) {
                e = e2;
                closeable = dVar;
                try {
                    b.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
                    r.a(e, (OutputStream) closeable);
                    i.a(flushable, "Failed to flush to session begin file.");
                    i.a(closeable, "Failed to close fatal exception file output stream.");
                } catch (Throwable th2) {
                    e = th2;
                    i.a(flushable, "Failed to flush to session begin file.");
                    i.a(closeable, "Failed to close fatal exception file output stream.");
                    throw e;
                }
            } catch (Throwable th3) {
                e = th3;
                closeable = dVar;
                i.a(flushable, "Failed to flush to session begin file.");
                i.a(closeable, "Failed to close fatal exception file output stream.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            closeable = null;
            b.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
            r.a(e, (OutputStream) closeable);
            i.a(flushable, "Failed to flush to session begin file.");
            i.a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Throwable th4) {
            e = th4;
            closeable = null;
            i.a(flushable, "Failed to flush to session begin file.");
            i.a(closeable, "Failed to close fatal exception file output stream.");
            throw e;
        }
    }

    private void a(com.b.a.c.a.a.d dVar) {
        Closeable dVar2;
        Throwable e;
        Flushable flushable = null;
        try {
            String k = k();
            if (k == null) {
                b.a.a.a.c.h().e("CrashlyticsCore", "Tried to write a native crash while no session was open.", null);
                i.a(null, "Failed to flush to session begin file.");
                i.a(null, "Failed to close fatal exception file output stream.");
                return;
            }
            f.a(k);
            dVar2 = new d(q(), k + "SessionCrash");
            try {
                flushable = e.a((OutputStream) dVar2);
                w.a(dVar, new t(this.n.E(), this.j, k), new v(q()).b(k), flushable);
                i.a(flushable, "Failed to flush to session begin file.");
                i.a(dVar2, "Failed to close fatal exception file output stream.");
            } catch (Exception e2) {
                e = e2;
                try {
                    b.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
                    r.a(e, (OutputStream) dVar2);
                    i.a(flushable, "Failed to flush to session begin file.");
                    i.a(dVar2, "Failed to close fatal exception file output stream.");
                } catch (Throwable th) {
                    e = th;
                    i.a(flushable, "Failed to flush to session begin file.");
                    i.a(dVar2, "Failed to close fatal exception file output stream.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            dVar2 = null;
            b.a.a.a.c.h().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
            r.a(e, (OutputStream) dVar2);
            i.a(flushable, "Failed to flush to session begin file.");
            i.a(dVar2, "Failed to close fatal exception file output stream.");
        } catch (Throwable th2) {
            e = th2;
            dVar2 = null;
            i.a(flushable, "Failed to flush to session begin file.");
            i.a(dVar2, "Failed to close fatal exception file output stream.");
            throw e;
        }
    }

    private void a(String str, Date date) {
        Closeable dVar;
        Throwable e;
        Flushable flushable = null;
        try {
            dVar = new d(q(), str + "BeginSession");
            try {
                flushable = e.a((OutputStream) dVar);
                ab.a((e) flushable, str, String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{this.n.a()}), date.getTime() / 1000);
                i.a(flushable, "Failed to flush to session begin file.");
                i.a(dVar, "Failed to close begin session file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    r.a(e, (OutputStream) dVar);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    i.a(flushable, "Failed to flush to session begin file.");
                    i.a(dVar, "Failed to close begin session file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            dVar = null;
            r.a(e, (OutputStream) dVar);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            dVar = null;
            i.a(flushable, "Failed to flush to session begin file.");
            i.a(dVar, "Failed to close begin session file.");
            throw e;
        }
    }

    private void c(String str) {
        Closeable dVar;
        Flushable a;
        Throwable e;
        Closeable closeable;
        OutputStream outputStream;
        Flushable flushable = null;
        try {
            dVar = new d(q(), str + "SessionApp");
            try {
                a = e.a((OutputStream) dVar);
            } catch (Exception e2) {
                e = e2;
                closeable = dVar;
                try {
                    r.a(e, outputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    dVar = outputStream;
                    i.a(flushable, "Failed to flush to session app file.");
                    i.a(dVar, "Failed to close session app file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                i.a(flushable, "Failed to flush to session app file.");
                i.a(dVar, "Failed to close session app file.");
                throw e;
            }
            try {
                ab.a((e) a, this.m.c(), this.n.i(), this.n.l(), this.n.k(), this.m.b(), b.a.a.a.a.b.l.a(this.n.j()).a(), this.q);
                i.a(a, "Failed to flush to session app file.");
                i.a(dVar, "Failed to close session app file.");
            } catch (Throwable e3) {
                closeable = dVar;
                Flushable flushable2 = a;
                e = e3;
                flushable = flushable2;
                r.a(e, outputStream);
                throw e;
            } catch (Throwable e32) {
                Throwable th3 = e32;
                flushable = a;
                e = th3;
                i.a(flushable, "Failed to flush to session app file.");
                i.a(dVar, "Failed to close session app file.");
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            outputStream = null;
            r.a(e, outputStream);
            throw e;
        } catch (Throwable th4) {
            e = th4;
            dVar = null;
            i.a(flushable, "Failed to flush to session app file.");
            i.a(dVar, "Failed to close session app file.");
            throw e;
        }
    }

    private void d(String str) {
        Closeable dVar;
        Throwable e;
        Flushable flushable = null;
        try {
            dVar = new d(q(), str + "SessionOS");
            try {
                flushable = e.a((OutputStream) dVar);
                ab.a((e) flushable, i.g(this.n.E()));
                i.a(flushable, "Failed to flush to session OS file.");
                i.a(dVar, "Failed to close session OS file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    r.a(e, (OutputStream) dVar);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    i.a(flushable, "Failed to flush to session OS file.");
                    i.a(dVar, "Failed to close session OS file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            dVar = null;
            r.a(e, (OutputStream) dVar);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            dVar = null;
            i.a(flushable, "Failed to flush to session OS file.");
            i.a(dVar, "Failed to close session OS file.");
            throw e;
        }
    }

    private void e(String str) {
        Throwable e;
        OutputStream outputStream;
        OutputStream outputStream2 = null;
        Flushable flushable = null;
        try {
            OutputStream dVar = new d(q(), str + "SessionDevice");
            try {
                flushable = e.a(dVar);
                Context E = this.n.E();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                ab.a((e) flushable, this.m.h(), i.a(), Build.MODEL, Runtime.getRuntime().availableProcessors(), i.b(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), i.f(E), this.m.i(), i.h(E), Build.MANUFACTURER, Build.PRODUCT);
                i.a(flushable, "Failed to flush session device info.");
                i.a((Closeable) dVar, "Failed to close session device file.");
            } catch (Exception e2) {
                e = e2;
                outputStream2 = dVar;
                try {
                    r.a(e, outputStream2);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    outputStream = outputStream2;
                    i.a(flushable, "Failed to flush session device info.");
                    i.a((Closeable) outputStream, "Failed to close session device file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                i.a(flushable, "Failed to flush session device info.");
                i.a((Closeable) outputStream, "Failed to close session device file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            r.a(e, outputStream2);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            outputStream = null;
            i.a(flushable, "Failed to flush session device info.");
            i.a((Closeable) outputStream, "Failed to close session device file.");
            throw e;
        }
    }

    private void f(String str) {
        Throwable e;
        Flushable flushable = null;
        Closeable dVar;
        try {
            dVar = new d(q(), str + "SessionUser");
            try {
                flushable = e.a((OutputStream) dVar);
                ae g = g(str);
                if (g.a()) {
                    i.a(flushable, "Failed to flush session user file.");
                    i.a(dVar, "Failed to close session user file.");
                    return;
                }
                ab.a((e) flushable, g.b, g.c, g.d);
                i.a(flushable, "Failed to flush session user file.");
                i.a(dVar, "Failed to close session user file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    r.a(e, (OutputStream) dVar);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    i.a(flushable, "Failed to flush session user file.");
                    i.a(dVar, "Failed to close session user file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            dVar = null;
            r.a(e, (OutputStream) dVar);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            dVar = null;
            i.a(flushable, "Failed to flush session user file.");
            i.a(dVar, "Failed to close session user file.");
            throw e;
        }
    }

    private void a(e eVar, Date date, Thread thread, Throwable th, String str, boolean z) {
        Thread[] threadArr;
        Map treeMap;
        Context E = this.n.E();
        long time = date.getTime() / 1000;
        float c = i.c(E);
        int a = i.a(E, this.p.a());
        boolean d = i.d(E);
        int i = E.getResources().getConfiguration().orientation;
        long b = i.b() - i.b(E);
        long b2 = i.b(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo a2 = i.a(E.getPackageName(), E);
        List linkedList = new LinkedList();
        StackTraceElement[] stackTrace = th.getStackTrace();
        String n = this.n.n();
        String c2 = this.m.c();
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            threadArr = new Thread[allStackTraces.size()];
            int i2 = 0;
            for (Entry entry : allStackTraces.entrySet()) {
                threadArr[i2] = (Thread) entry.getKey();
                linkedList.add(entry.getValue());
                i2++;
            }
        } else {
            threadArr = new Thread[0];
        }
        if (i.a(E, "com.crashlytics.CollectCustomKeys", true)) {
            Map g = this.n.g();
            treeMap = (g == null || g.size() <= 1) ? g : new TreeMap(g);
        } else {
            treeMap = new TreeMap();
        }
        ab.a(eVar, time, str, th, thread, stackTrace, threadArr, linkedList, treeMap, this.o, a2, i, c2, n, c, a, d, b, b2);
    }

    private void a(File file, String str, int i) {
        boolean z;
        b.a.a.a.c.h().a("CrashlyticsCore", "Collecting session parts for ID " + str);
        File[] a = a(new b(str + "SessionCrash"));
        boolean z2 = a != null && a.length > 0;
        b.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z2)}));
        File[] a2 = a(new b(str + "SessionEvent"));
        if (a2 == null || a2.length <= 0) {
            z = false;
        } else {
            z = true;
        }
        b.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z)}));
        if (z2 || z) {
            a(file, str, a(str, a2, i), z2 ? a[0] : null);
        } else {
            b.a.a.a.c.h().a("CrashlyticsCore", "No events present for session ID " + str);
        }
        b.a.a.a.c.h().a("CrashlyticsCore", "Removing session part files for ID " + str);
        a(str);
    }

    private void a(File file, String str, File[] fileArr, File file2) {
        Throwable e;
        boolean z = true;
        if (file2 == null) {
            z = false;
        }
        Closeable dVar;
        try {
            dVar = new d(q(), str);
            try {
                Flushable a = e.a((OutputStream) dVar);
                b.a.a.a.c.h().a("CrashlyticsCore", "Collecting SessionStart data for session ID " + str);
                a((e) a, file);
                a.a(4, new Date().getTime() / 1000);
                a.a(5, z);
                a((e) a, str);
                a((e) a, fileArr, str);
                if (z) {
                    a((e) a, file2);
                }
                a.a(11, 1);
                a.b(12, 3);
                i.a(a, "Error flushing session file stream");
                i.a(dVar, "Failed to close CLS file");
            } catch (Exception e2) {
                e = e2;
                try {
                    b.a.a.a.c.h().e("CrashlyticsCore", "Failed to write session file for session ID: " + str, e);
                    r.a(e, (OutputStream) dVar);
                    i.a(null, "Error flushing session file stream");
                    a((d) dVar);
                } catch (Throwable th) {
                    e = th;
                    i.a(null, "Error flushing session file stream");
                    i.a(dVar, "Failed to close CLS file");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            dVar = null;
            b.a.a.a.c.h().e("CrashlyticsCore", "Failed to write session file for session ID: " + str, e);
            r.a(e, (OutputStream) dVar);
            i.a(null, "Error flushing session file stream");
            a((d) dVar);
        } catch (Throwable th2) {
            e = th2;
            dVar = null;
            i.a(null, "Error flushing session file stream");
            i.a(dVar, "Failed to close CLS file");
            throw e;
        }
    }

    private static void a(e eVar, File[] fileArr, String str) {
        Arrays.sort(fileArr, i.a);
        for (File name : fileArr) {
            try {
                b.a.a.a.c.h().a("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, name.getName()}));
                a(eVar, name);
            } catch (Throwable e) {
                b.a.a.a.c.h().e("CrashlyticsCore", "Error writting non-fatal to session.", e);
            }
        }
    }

    private void a(e eVar, String str) {
        for (String str2 : g) {
            File[] a = a(new b(str + str2));
            if (a.length == 0) {
                b.a.a.a.c.h().e("CrashlyticsCore", "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                b.a.a.a.c.h().a("CrashlyticsCore", "Collecting " + str2 + " data for session ID " + str);
                a(eVar, a[0]);
            }
        }
    }

    private static void a(e eVar, File file) {
        Closeable fileInputStream;
        Throwable th;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    a((InputStream) fileInputStream, eVar, (int) file.length());
                    i.a(fileInputStream, "Failed to close file input stream.");
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    i.a(fileInputStream, "Failed to close file input stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                i.a(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        }
        b.a.a.a.c.h().e("CrashlyticsCore", "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private static void a(InputStream inputStream, e eVar, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = inputStream.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
        }
        eVar.a(bArr);
    }

    private ae g(String str) {
        return a() ? new ae(this.n.p(), this.n.r(), this.n.q()) : new v(q()).a(str);
    }

    private void p() {
        for (File cVar : m()) {
            this.l.a(new c(this.n, cVar));
        }
    }

    private File q() {
        return this.j.a();
    }
}
