package com.b.a.c;

import a.a.a.a.a.b.i;
import a.a.a.a.a.b.o;
import android.os.Process;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: CLSUUID */
class c {
    private static final AtomicLong a = new AtomicLong(0);
    private static String b;

    public c(o oVar) {
        r0 = new byte[10];
        a(r0);
        b(r0);
        c(r0);
        String a = i.a(oVar.b());
        String a2 = i.a(r0);
        b = String.format(Locale.US, "%s-%s-%s-%s", new Object[]{a2.substring(0, 12), a2.substring(12, 16), a2.subSequence(16, 20), a.substring(0, 12)}).toUpperCase(Locale.US);
    }

    private void a(byte[] bArr) {
        long time = new Date().getTime();
        long j = time / 1000;
        time %= 1000;
        byte[] a = a(j);
        bArr[0] = a[0];
        bArr[1] = a[1];
        bArr[2] = a[2];
        bArr[3] = a[3];
        byte[] b = b(time);
        bArr[4] = b[0];
        bArr[5] = b[1];
    }

    private void b(byte[] bArr) {
        byte[] b = b(a.incrementAndGet());
        bArr[6] = b[0];
        bArr[7] = b[1];
    }

    private void c(byte[] bArr) {
        byte[] b = b((long) Integer.valueOf(Process.myPid()).shortValue());
        bArr[8] = b[0];
        bArr[9] = b[1];
    }

    private static byte[] a(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt((int) j);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        return allocate.array();
    }

    private static byte[] b(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort((short) ((int) j));
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        return allocate.array();
    }

    public String toString() {
        return b;
    }
}
