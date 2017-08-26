package com.b.a.c;

import a.a.a.a.a.b.i;
import a.a.a.a.a.b.q;
import a.a.a.a.a.b.q.c;
import java.io.File;
import java.io.InputStream;

/* compiled from: QueueFileLogStore */
class y implements s {
    private final File a;
    private final int b;
    private q c;

    public y(File file, int i) {
        this.a = file;
        this.b = i;
    }

    public b a() {
        if (!this.a.exists()) {
            return null;
        }
        d();
        if (this.c == null) {
            return null;
        }
        final int[] iArr = new int[]{0};
        final byte[] bArr = new byte[this.c.a()];
        try {
            this.c.a(new c(this) {
                final /* synthetic */ y c;

                public void a(InputStream inputStream, int i) {
                    try {
                        inputStream.read(bArr, iArr[0], i);
                        int[] iArr = iArr;
                        iArr[0] = iArr[0] + i;
                    } finally {
                        inputStream.close();
                    }
                }
            });
        } catch (Throwable e) {
            a.a.a.a.c.h().e("CrashlyticsCore", "A problem occurred while reading the Crashlytics log file.", e);
        }
        return b.a(bArr, 0, iArr[0]);
    }

    public void b() {
        i.a(this.c, "There was a problem closing the Crashlytics log file.");
        this.c = null;
    }

    public void c() {
        b();
        this.a.delete();
    }

    private void d() {
        if (this.c == null) {
            try {
                this.c = new q(this.a);
            } catch (Throwable e) {
                a.a.a.a.c.h().e("CrashlyticsCore", "Could not open log file: " + this.a, e);
            }
        }
    }
}
