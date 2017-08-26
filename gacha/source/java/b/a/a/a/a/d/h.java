package b.a.a.a.a.d;

import android.content.Context;
import b.a.a.a.a.b.i;
import b.a.a.a.a.b.q;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: QueueFileEventStorage */
public class h implements c {
    private final Context a;
    private final File b;
    private final String c;
    private final File d;
    private q e = new q(this.d);
    private File f;

    public h(Context context, File file, String str, String str2) {
        this.a = context;
        this.b = file;
        this.c = str2;
        this.d = new File(this.b, str);
        e();
    }

    private void e() {
        this.f = new File(this.b, this.c);
        if (!this.f.exists()) {
            this.f.mkdirs();
        }
    }

    public void a(byte[] bArr) {
        this.e.a(bArr);
    }

    public int a() {
        return this.e.a();
    }

    public void a(String str) {
        this.e.close();
        a(this.d, new File(this.f, str));
        this.e = new q(this.d);
    }

    private void a(File file, File file2) {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                closeable = a(file2);
                i.a((InputStream) fileInputStream, (OutputStream) closeable, new byte[1024]);
                i.a(fileInputStream, "Failed to close file input stream");
                i.a(closeable, "Failed to close output stream");
                file.delete();
            } catch (Throwable th2) {
                th = th2;
                i.a(fileInputStream, "Failed to close file input stream");
                i.a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            i.a(fileInputStream, "Failed to close file input stream");
            i.a(closeable, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    public OutputStream a(File file) {
        return new FileOutputStream(file);
    }

    public List<File> a(int i) {
        List<File> arrayList = new ArrayList();
        for (Object add : this.f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    public void a(List<File> list) {
        for (File file : list) {
            i.a(this.a, String.format("deleting sent analytics file %s", new Object[]{file.getName()}));
            file.delete();
        }
    }

    public List<File> c() {
        return Arrays.asList(this.f.listFiles());
    }

    public void d() {
        try {
            this.e.close();
        } catch (IOException e) {
        }
        this.d.delete();
    }

    public boolean b() {
        return this.e.b();
    }

    public boolean a(int i, int i2) {
        return this.e.a(i, i2);
    }
}
