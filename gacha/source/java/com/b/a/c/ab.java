package com.b.a.c;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Build.VERSION;
import b.a.a.a.a.b.o.a;
import b.a.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: SessionProtobufHelper */
class ab {
    private static final b a = b.a("0");
    private static final b b = b.a("Unity");

    public static void a(e eVar, String str, String str2, long j) {
        eVar.a(1, b.a(str2));
        eVar.a(2, b.a(str));
        eVar.a(3, j);
    }

    public static void a(e eVar, String str, String str2, String str3, String str4, String str5, int i, String str6) {
        b a = b.a(str);
        b a2 = b.a(str2);
        b a3 = b.a(str3);
        b a4 = b.a(str4);
        b a5 = b.a(str5);
        b a6 = str6 != null ? b.a(str6) : null;
        eVar.g(7, 2);
        eVar.k(a(a, a2, a3, a4, a5, i, a6));
        eVar.a(1, a);
        eVar.a(2, a3);
        eVar.a(3, a4);
        eVar.g(5, 2);
        eVar.k(a(a2));
        eVar.a(1, a2);
        eVar.a(6, a5);
        if (a6 != null) {
            eVar.a(8, b);
            eVar.a(9, a6);
        }
        eVar.b(10, i);
    }

    public static void a(e eVar, boolean z) {
        b a = b.a(VERSION.RELEASE);
        b a2 = b.a(VERSION.CODENAME);
        eVar.g(8, 2);
        eVar.k(a(a, a2, z));
        eVar.b(1, 3);
        eVar.a(2, a);
        eVar.a(3, a2);
        eVar.a(4, z);
    }

    public static void a(e eVar, String str, int i, String str2, int i2, long j, long j2, boolean z, Map<a, String> map, int i3, String str3, String str4) {
        b a = b.a(str);
        b a2 = a(str2);
        b a3 = a(str4);
        b a4 = a(str3);
        eVar.g(9, 2);
        eVar.k(a(i, a, a2, i2, j, j2, z, (Map) map, i3, a4, a3));
        eVar.a(1, a);
        eVar.b(3, i);
        eVar.a(4, a2);
        eVar.a(5, i2);
        eVar.a(6, j);
        eVar.a(7, j2);
        eVar.a(10, z);
        for (Entry entry : map.entrySet()) {
            eVar.g(11, 2);
            eVar.k(a((a) entry.getKey(), (String) entry.getValue()));
            eVar.b(1, ((a) entry.getKey()).h);
            eVar.a(2, b.a((String) entry.getValue()));
        }
        eVar.a(12, i3);
        if (a4 != null) {
            eVar.a(13, a4);
        }
        if (a3 != null) {
            eVar.a(14, a3);
        }
    }

    public static void a(e eVar, String str, String str2, String str3) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        b a = b.a(str);
        b a2 = a(str2);
        b a3 = a(str3);
        int b = 0 + e.b(1, a);
        if (str2 != null) {
            b += e.b(2, a2);
        }
        if (str3 != null) {
            b += e.b(3, a3);
        }
        eVar.g(6, 2);
        eVar.k(b);
        eVar.a(1, a);
        if (str2 != null) {
            eVar.a(2, a2);
        }
        if (str3 != null) {
            eVar.a(3, a3);
        }
    }

    public static void a(e eVar, long j, String str, Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, Map<String, String> map, t tVar, RunningAppProcessInfo runningAppProcessInfo, int i, String str2, String str3, float f, int i2, boolean z, long j2, long j3) {
        b bVar;
        b a = b.a(str2);
        if (str3 == null) {
            bVar = null;
        } else {
            bVar = b.a(str3.replace(Operation.MINUS, BuildConfig.FLAVOR));
        }
        b a2 = tVar.a();
        if (a2 == null) {
            c.h().a("CrashlyticsCore", "No log data to include with this event.");
        }
        tVar.b();
        eVar.g(10, 2);
        eVar.k(a(j, str, th, thread, stackTraceElementArr, threadArr, (List) list, 8, (Map) map, runningAppProcessInfo, i, a, bVar, f, i2, z, j2, j3, a2));
        eVar.a(1, j);
        eVar.a(2, b.a(str));
        a(eVar, th, thread, stackTraceElementArr, threadArr, (List) list, 8, a, bVar, (Map) map, runningAppProcessInfo, i);
        a(eVar, f, i2, z, i, j2, j3);
        a(eVar, a2);
    }

    private static void a(e eVar, Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, b bVar, b bVar2, Map<String, String> map, RunningAppProcessInfo runningAppProcessInfo, int i2) {
        eVar.g(3, 2);
        eVar.k(a(th, thread, stackTraceElementArr, threadArr, (List) list, i, bVar, bVar2, (Map) map, runningAppProcessInfo, i2));
        a(eVar, th, thread, stackTraceElementArr, threadArr, list, i, bVar, bVar2);
        if (!(map == null || map.isEmpty())) {
            a(eVar, (Map) map);
        }
        if (runningAppProcessInfo != null) {
            eVar.a(3, runningAppProcessInfo.importance != 100);
        }
        eVar.a(4, i2);
    }

    private static void a(e eVar, Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, b bVar, b bVar2) {
        eVar.g(1, 2);
        eVar.k(a(th, thread, stackTraceElementArr, threadArr, (List) list, i, bVar, bVar2));
        a(eVar, thread, stackTraceElementArr, 4, true);
        int length = threadArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            a(eVar, threadArr[i2], (StackTraceElement[]) list.get(i2), 0, false);
        }
        a(eVar, th, 1, i, 2);
        eVar.g(3, 2);
        eVar.k(a());
        eVar.a(1, a);
        eVar.a(2, a);
        eVar.a(3, 0);
        eVar.g(4, 2);
        eVar.k(a(bVar, bVar2));
        eVar.a(1, 0);
        eVar.a(2, 0);
        eVar.a(3, bVar);
        if (bVar2 != null) {
            eVar.a(4, bVar2);
        }
    }

    private static void a(e eVar, Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            eVar.g(2, 2);
            eVar.k(a((String) entry.getKey(), (String) entry.getValue()));
            eVar.a(1, b.a((String) entry.getKey()));
            String str = (String) entry.getValue();
            if (str == null) {
                str = BuildConfig.FLAVOR;
            }
            eVar.a(2, b.a(str));
        }
    }

    private static void a(e eVar, Throwable th, int i, int i2, int i3) {
        int i4 = 0;
        eVar.g(i3, 2);
        eVar.k(a(th, 1, i2));
        eVar.a(1, b.a(th.getClass().getName()));
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            eVar.a(3, b.a(localizedMessage));
        }
        for (StackTraceElement a : th.getStackTrace()) {
            a(eVar, 4, a, true);
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return;
        }
        if (i < i2) {
            a(eVar, cause, i + 1, i2, 6);
            return;
        }
        while (cause != null) {
            cause = cause.getCause();
            i4++;
        }
        eVar.a(7, i4);
    }

    private static void a(e eVar, Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        eVar.g(1, 2);
        eVar.k(a(thread, stackTraceElementArr, i, z));
        eVar.a(1, b.a(thread.getName()));
        eVar.a(2, i);
        for (StackTraceElement a : stackTraceElementArr) {
            a(eVar, 3, a, z);
        }
    }

    private static void a(e eVar, int i, StackTraceElement stackTraceElement, boolean z) {
        int i2 = 4;
        eVar.g(i, 2);
        eVar.k(a(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            eVar.a(1, (long) Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            eVar.a(1, 0);
        }
        eVar.a(2, b.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            eVar.a(3, b.a(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            eVar.a(4, (long) stackTraceElement.getLineNumber());
        }
        if (!z) {
            i2 = 0;
        }
        eVar.a(5, i2);
    }

    private static void a(e eVar, float f, int i, boolean z, int i2, long j, long j2) {
        eVar.g(5, 2);
        eVar.k(a(f, i, z, i2, j, j2));
        eVar.a(1, f);
        eVar.c(2, i);
        eVar.a(3, z);
        eVar.a(4, i2);
        eVar.a(5, j);
        eVar.a(6, j2);
    }

    private static void a(e eVar, b bVar) {
        if (bVar != null) {
            eVar.g(6, 2);
            eVar.k(b(bVar));
            eVar.a(1, bVar);
        }
    }

    private static int a(b bVar, b bVar2, b bVar3, b bVar4, b bVar5, int i, b bVar6) {
        int b = ((0 + e.b(1, bVar)) + e.b(2, bVar3)) + e.b(3, bVar4);
        int a = a(bVar2);
        b = (b + (a + (e.j(5) + e.l(a)))) + e.b(6, bVar5);
        if (bVar6 != null) {
            b = (b + e.b(8, b)) + e.b(9, bVar6);
        }
        return b + e.e(10, i);
    }

    private static int a(b bVar) {
        return 0 + e.b(1, bVar);
    }

    private static int a(b bVar, b bVar2, boolean z) {
        return (((0 + e.e(1, 3)) + e.b(2, bVar)) + e.b(3, bVar2)) + e.b(4, z);
    }

    private static int a(a aVar, String str) {
        return e.e(1, aVar.h) + e.b(2, b.a(str));
    }

    private static int a(int i, b bVar, b bVar2, int i2, long j, long j2, boolean z, Map<a, String> map, int i3, b bVar3, b bVar4) {
        int i4;
        int i5;
        int e = e.e(3, i) + (0 + e.b(1, bVar));
        if (bVar2 == null) {
            i4 = 0;
        } else {
            i4 = e.b(4, bVar2);
        }
        i4 = ((((i4 + e) + e.d(5, i2)) + e.b(6, j)) + e.b(7, j2)) + e.b(10, z);
        if (map != null) {
            i5 = i4;
            for (Entry entry : map.entrySet()) {
                i4 = a((a) entry.getKey(), (String) entry.getValue());
                i5 = (i4 + (e.j(11) + e.l(i4))) + i5;
            }
        } else {
            i5 = i4;
        }
        return (bVar4 == null ? 0 : e.b(14, bVar4)) + ((i5 + e.d(12, i3)) + (bVar3 == null ? 0 : e.b(13, bVar3)));
    }

    private static int a(b bVar, b bVar2) {
        int b = ((0 + e.b(1, 0)) + e.b(2, 0)) + e.b(3, bVar);
        if (bVar2 != null) {
            return b + e.b(4, bVar2);
        }
        return b;
    }

    private static int a(long j, String str, Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, Map<String, String> map, RunningAppProcessInfo runningAppProcessInfo, int i2, b bVar, b bVar2, float f, int i3, boolean z, long j2, long j3, b bVar3) {
        int b = (0 + e.b(1, j)) + e.b(2, b.a(str));
        int a = a(th, thread, stackTraceElementArr, threadArr, (List) list, i, bVar, bVar2, (Map) map, runningAppProcessInfo, i2);
        int j4 = b + (a + (e.j(3) + e.l(a)));
        a = a(f, i3, z, i2, j2, j3);
        a = (a + (e.j(5) + e.l(a))) + j4;
        if (bVar3 == null) {
            return a;
        }
        int b2 = b(bVar3);
        return a + (b2 + (e.j(6) + e.l(b2)));
    }

    private static int a(Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, b bVar, b bVar2, Map<String, String> map, RunningAppProcessInfo runningAppProcessInfo, int i2) {
        int a = a(th, thread, stackTraceElementArr, threadArr, (List) list, i, bVar, bVar2);
        int j = 0 + (a + (e.j(1) + e.l(a)));
        if (map != null) {
            int i3 = j;
            for (Entry entry : map.entrySet()) {
                j = a((String) entry.getKey(), (String) entry.getValue());
                i3 = (j + (e.j(2) + e.l(j))) + i3;
            }
            a = i3;
        } else {
            a = j;
        }
        if (runningAppProcessInfo != null) {
            j = e.b(3, runningAppProcessInfo.importance != 100) + a;
        } else {
            j = a;
        }
        return j + e.d(4, i2);
    }

    private static int a(Throwable th, Thread thread, StackTraceElement[] stackTraceElementArr, Thread[] threadArr, List<StackTraceElement[]> list, int i, b bVar, b bVar2) {
        int a;
        int a2 = a(thread, stackTraceElementArr, 4, true);
        a2 = (a2 + (e.j(1) + e.l(a2))) + 0;
        int length = threadArr.length;
        int i2 = a2;
        for (a2 = 0; a2 < length; a2++) {
            a = a(threadArr[a2], (StackTraceElement[]) list.get(a2), 0, false);
            i2 += a + (e.j(1) + e.l(a));
        }
        a = a(th, 1, i);
        a = (a + (e.j(2) + e.l(a))) + i2;
        a2 = a();
        a += a2 + (e.j(3) + e.l(a2));
        a2 = a(bVar, bVar2);
        return a + (a2 + (e.j(3) + e.l(a2)));
    }

    private static int a(String str, String str2) {
        int b = e.b(1, b.a(str));
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        return b + e.b(2, b.a(str2));
    }

    private static int a(float f, int i, boolean z, int i2, long j, long j2) {
        return (((((0 + e.b(1, f)) + e.f(2, i)) + e.b(3, z)) + e.d(4, i2)) + e.b(5, j)) + e.b(6, j2);
    }

    private static int b(b bVar) {
        return e.b(1, bVar);
    }

    private static int a(Throwable th, int i, int i2) {
        int i3 = 0;
        int b = e.b(1, b.a(th.getClass().getName())) + 0;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            b += e.b(3, b.a(localizedMessage));
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i4 = 0;
        while (i4 < length) {
            int a = a(stackTrace[i4], true);
            i4++;
            b = (a + (e.j(4) + e.l(a))) + b;
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return b;
        }
        if (i < i2) {
            i3 = a(cause, i + 1, i2);
            return b + (i3 + (e.j(6) + e.l(i3)));
        }
        while (cause != null) {
            cause = cause.getCause();
            i3++;
        }
        return b + e.d(7, i3);
    }

    private static int a() {
        return ((0 + e.b(1, a)) + e.b(2, a)) + e.b(3, 0);
    }

    private static int a(StackTraceElement stackTraceElement, boolean z) {
        int b;
        int i;
        if (stackTraceElement.isNativeMethod()) {
            b = e.b(1, (long) Math.max(stackTraceElement.getLineNumber(), 0)) + 0;
        } else {
            b = e.b(1, 0) + 0;
        }
        b += e.b(2, b.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            b += e.b(3, b.a(stackTraceElement.getFileName()));
        }
        if (stackTraceElement.isNativeMethod() || stackTraceElement.getLineNumber() <= 0) {
            i = b;
        } else {
            i = b + e.b(4, (long) stackTraceElement.getLineNumber());
        }
        return e.d(5, z ? 2 : 0) + i;
    }

    private static int a(Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        int d = e.d(2, i) + e.b(1, b.a(thread.getName()));
        for (StackTraceElement a : stackTraceElementArr) {
            int a2 = a(a, z);
            d += a2 + (e.j(3) + e.l(a2));
        }
        return d;
    }

    private static b a(String str) {
        return str == null ? null : b.a(str);
    }
}
