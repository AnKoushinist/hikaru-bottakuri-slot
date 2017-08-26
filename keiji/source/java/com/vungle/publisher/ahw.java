package com.vungle.publisher;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class ahw extends RuntimeException {
    private final List<Throwable> a;
    private final String b;
    private Throwable c;

    /* compiled from: vungle */
    static final class a extends RuntimeException {
        a() {
        }

        public final String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    /* compiled from: vungle */
    static abstract class b {
        abstract Object a();

        abstract void a(Object obj);

        b() {
        }
    }

    /* compiled from: vungle */
    static final class c extends b {
        private final PrintStream a;

        c(PrintStream printStream) {
            this.a = printStream;
        }

        final Object a() {
            return this.a;
        }

        final void a(Object obj) {
            this.a.println(obj);
        }
    }

    /* compiled from: vungle */
    static final class d extends b {
        private final PrintWriter a;

        d(PrintWriter printWriter) {
            this.a = printWriter;
        }

        final Object a() {
            return this.a;
        }

        final void a(Object obj) {
            this.a.println(obj);
        }
    }

    @Deprecated
    private ahw(Collection<? extends Throwable> collection) {
        Collection linkedHashSet = new LinkedHashSet();
        List arrayList = new ArrayList();
        if (collection != null) {
            for (Throwable th : collection) {
                if (th instanceof ahw) {
                    linkedHashSet.addAll(((ahw) th).a);
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.a = Collections.unmodifiableList(arrayList);
        this.b = this.a.size() + " exceptions occurred. ";
    }

    public ahw(Collection<? extends Throwable> collection, byte b) {
        this(collection);
    }

    public final String getMessage() {
        return this.b;
    }

    public final synchronized Throwable getCause() {
        if (this.c == null) {
            Throwable aVar = new a();
            Set hashSet = new HashSet();
            Throwable th = aVar;
            for (Throwable th2 : this.a) {
                if (!hashSet.contains(th2)) {
                    hashSet.add(th2);
                    Throwable th3 = th2;
                    for (Throwable th22 : a(th22)) {
                        if (hashSet.contains(th22)) {
                            th3 = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th22);
                        }
                    }
                    try {
                        th.initCause(th3);
                    } catch (Throwable th4) {
                    }
                    th = b(th);
                }
            }
            this.c = aVar;
        }
        return this.c;
    }

    public final void printStackTrace() {
        printStackTrace(System.err);
    }

    public final void printStackTrace(PrintStream printStream) {
        a(new c(printStream));
    }

    public final void printStackTrace(PrintWriter printWriter) {
        a(new d(printWriter));
    }

    private void a(b bVar) {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(this).append('\n');
        for (Object append : getStackTrace()) {
            stringBuilder.append("\tat ").append(append).append('\n');
        }
        int i = 1;
        for (Throwable th : this.a) {
            stringBuilder.append("  ComposedException ").append(i).append(" :\n");
            a(stringBuilder, th, "\t");
            i++;
        }
        synchronized (bVar.a()) {
            bVar.a(stringBuilder.toString());
        }
    }

    private static void a(StringBuilder stringBuilder, Throwable th, String str) {
        while (true) {
            stringBuilder.append(str).append(th).append('\n');
            for (Object append : th.getStackTrace()) {
                stringBuilder.append("\t\tat ").append(append).append('\n');
            }
            if (th.getCause() != null) {
                stringBuilder.append("\tCaused by: ");
                th = th.getCause();
                str = BuildConfig.FLAVOR;
            } else {
                return;
            }
        }
    }

    private static List<Throwable> a(Throwable th) {
        List<Throwable> arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            Throwable cause2 = cause.getCause();
            if (cause2 != null && cause2 != cause) {
                cause = cause.getCause();
            }
        }
        return arrayList;
    }

    private static Throwable b(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return th;
        }
        while (true) {
            Throwable cause2 = cause.getCause();
            if (cause2 == null) {
                return cause;
            }
            if (cause2 == cause) {
                return cause;
            }
            cause = cause.getCause();
        }
    }
}
