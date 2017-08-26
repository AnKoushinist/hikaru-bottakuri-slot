package a.a.a.a.a.b;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: QueueFile */
public class q implements Closeable {
    private static final Logger b = Logger.getLogger(q.class.getName());
    int a;
    private final RandomAccessFile c;
    private int d;
    private a e;
    private a f;
    private final byte[] g = new byte[16];

    /* compiled from: QueueFile */
    public interface c {
        void a(InputStream inputStream, int i);
    }

    /* compiled from: QueueFile */
    static class a {
        static final a a = new a(0, 0);
        final int b;
        final int c;

        a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[" + "position = " + this.b + ", length = " + this.c + "]";
        }
    }

    /* compiled from: QueueFile */
    private final class b extends InputStream {
        final /* synthetic */ q a;
        private int b;
        private int c;

        private b(q qVar, a aVar) {
            this.a = qVar;
            this.b = qVar.b(aVar.b + 4);
            this.c = aVar.c;
        }

        public int read(byte[] bArr, int i, int i2) {
            q.b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.c <= 0) {
                return -1;
            } else {
                if (i2 > this.c) {
                    i2 = this.c;
                }
                this.a.b(this.b, bArr, i, i2);
                this.b = this.a.b(this.b + i2);
                this.c -= i2;
                return i2;
            }
        }

        public int read() {
            if (this.c == 0) {
                return -1;
            }
            this.a.c.seek((long) this.b);
            int read = this.a.c.read();
            this.b = this.a.b(this.b + 1);
            this.c--;
            return read;
        }
    }

    public q(File file) {
        if (!file.exists()) {
            a(file);
        }
        this.c = b(file);
        c();
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            b(bArr, i2, iArr[i]);
            i2 += 4;
            i++;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    private void c() {
        this.c.seek(0);
        this.c.readFully(this.g);
        this.a = a(this.g, 0);
        if (((long) this.a) > this.c.length()) {
            throw new IOException("File is truncated. Expected length: " + this.a + ", Actual length: " + this.c.length());
        }
        this.d = a(this.g, 4);
        int a = a(this.g, 8);
        int a2 = a(this.g, 12);
        this.e = a(a);
        this.f = a(a2);
    }

    private void a(int i, int i2, int i3, int i4) {
        a(this.g, i, i2, i3, i4);
        this.c.seek(0);
        this.c.write(this.g);
    }

    private a a(int i) {
        if (i == 0) {
            return a.a;
        }
        this.c.seek((long) i);
        return new a(i, this.c.readInt());
    }

    private static void a(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b = b(file2);
        try {
            b.setLength(4096);
            b.seek(0);
            byte[] bArr = new byte[16];
            a(bArr, 4096, 0, 0, 0);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    private static RandomAccessFile b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    private int b(int i) {
        return i < this.a ? i : (i + 16) - this.a;
    }

    private void a(int i, byte[] bArr, int i2, int i3) {
        int b = b(i);
        if (b + i3 <= this.a) {
            this.c.seek((long) b);
            this.c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.a - b;
        this.c.seek((long) b);
        this.c.write(bArr, i2, i4);
        this.c.seek(16);
        this.c.write(bArr, i2 + i4, i3 - i4);
    }

    private void b(int i, byte[] bArr, int i2, int i3) {
        int b = b(i);
        if (b + i3 <= this.a) {
            this.c.seek((long) b);
            this.c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.a - b;
        this.c.seek((long) b);
        this.c.readFully(bArr, i2, i4);
        this.c.seek(16);
        this.c.readFully(bArr, i2 + i4, i3 - i4);
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public synchronized void a(byte[] bArr, int i, int i2) {
        b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        c(i2);
        boolean b = b();
        if (b) {
            i3 = 16;
        } else {
            i3 = b((this.f.b + 4) + this.f.c);
        }
        a aVar = new a(i3, i2);
        b(this.g, 0, i2);
        a(aVar.b, this.g, 0, 4);
        a(aVar.b + 4, bArr, i, i2);
        a(this.a, this.d + 1, b ? aVar.b : this.e.b, aVar.b);
        this.f = aVar;
        this.d++;
        if (b) {
            this.e = this.f;
        }
    }

    public int a() {
        if (this.d == 0) {
            return 16;
        }
        if (this.f.b >= this.e.b) {
            return (((this.f.b - this.e.b) + 4) + this.f.c) + 16;
        }
        return (((this.f.b + 4) + this.f.c) + this.a) - this.e.b;
    }

    private int d() {
        return this.a - a();
    }

    public synchronized boolean b() {
        return this.d == 0;
    }

    private void c(int i) {
        int i2 = i + 4;
        int d = d();
        if (d < i2) {
            int i3 = this.a;
            do {
                d += i3;
                i3 <<= 1;
            } while (d < i2);
            d(i3);
            i2 = b((this.f.b + 4) + this.f.c);
            if (i2 < this.e.b) {
                FileChannel channel = this.c.getChannel();
                channel.position((long) this.a);
                int i4 = i2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.b < this.e.b) {
                d = (this.a + this.f.b) - 16;
                a(i3, this.d, this.e.b, d);
                this.f = new a(d, this.f.c);
            } else {
                a(i3, this.d, this.e.b, this.f.b);
            }
            this.a = i3;
        }
    }

    private void d(int i) {
        this.c.setLength((long) i);
        this.c.getChannel().force(true);
    }

    public synchronized void a(c cVar) {
        int i = this.e.b;
        for (int i2 = 0; i2 < this.d; i2++) {
            a a = a(i);
            cVar.a(new b(a), a.c);
            i = b(a.c + (a.b + 4));
        }
    }

    private static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public synchronized void close() {
        this.c.close();
    }

    public boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.a);
        stringBuilder.append(", size=").append(this.d);
        stringBuilder.append(", first=").append(this.e);
        stringBuilder.append(", last=").append(this.f);
        stringBuilder.append(", element lengths=[");
        try {
            a(new c(this) {
                boolean a = true;
                final /* synthetic */ q c;

                public void a(InputStream inputStream, int i) {
                    if (this.a) {
                        this.a = false;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(i);
                }
            });
        } catch (Throwable e) {
            b.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
