package com.b.a.c;

import b.a.a.a.a.b.i;
import b.a.a.a.c;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: ExceptionUtils */
final class r {
    public static void a(Throwable th, OutputStream outputStream) {
        if (outputStream != null) {
            b(th, outputStream);
        }
    }

    private static void b(Throwable th, OutputStream outputStream) {
        Closeable printWriter;
        Throwable e;
        try {
            printWriter = new PrintWriter(outputStream);
            try {
                a(th, (Writer) printWriter);
                i.a(printWriter, "Failed to close stack trace writer.");
            } catch (Exception e2) {
                e = e2;
                try {
                    c.h().e("CrashlyticsCore", "Failed to create PrintWriter", e);
                    i.a(printWriter, "Failed to close stack trace writer.");
                } catch (Throwable th2) {
                    e = th2;
                    i.a(printWriter, "Failed to close stack trace writer.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            printWriter = null;
            c.h().e("CrashlyticsCore", "Failed to create PrintWriter", e);
            i.a(printWriter, "Failed to close stack trace writer.");
        } catch (Throwable th3) {
            e = th3;
            printWriter = null;
            i.a(printWriter, "Failed to close stack trace writer.");
            throw e;
        }
    }

    private static void a(Throwable th, Writer writer) {
        Object obj = 1;
        while (th != null) {
            try {
                String a = a(th);
                writer.write((obj != null ? BuildConfig.FLAVOR : "Caused by: ") + th.getClass().getName() + ": " + (a != null ? a : BuildConfig.FLAVOR) + "\n");
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    writer.write("\tat " + stackTraceElement.toString() + "\n");
                }
                th = th.getCause();
                obj = null;
            } catch (Throwable e) {
                c.h().e("CrashlyticsCore", "Could not write stack trace", e);
                return;
            }
        }
    }

    private static String a(Throwable th) {
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage == null) {
            return null;
        }
        return localizedMessage.replaceAll("(\r\n|\n|\f)", " ");
    }
}
