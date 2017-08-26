package com.tapjoy.internal;

import android.graphics.Bitmap;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.au.a;
import com.tapjoy.internal.gt.AnonymousClass2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public final class gx {
    public static final bn e = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            return new gx(bsVar);
        }
    };
    private static final as f;
    public URL a;
    public Bitmap b;
    public byte[] c;
    public he d;

    static {
        as awVar = new aw();
        if (!(awVar instanceof ax)) {
            Object aVar = new a((av) awVar);
        }
        f = awVar;
    }

    public gx(URL url) {
        this.a = url;
    }

    public final boolean a() {
        return (this.b == null && this.c == null) ? false : true;
    }

    public final void b() {
        Closeable fileInputStream;
        Throwable th;
        boolean a = ez.b().a("mm_external_cache_enabled", true);
        boolean z = !a;
        if (z) {
            this.b = (Bitmap) f.a(this.a);
            if (this.b != null) {
                return;
            }
        }
        if (a) {
            File a2 = gt.a.a(this.a);
            if (a2 != null) {
                Closeable closeable = null;
                try {
                    fileInputStream = new FileInputStream(a2);
                    try {
                        a(fileInputStream);
                        dc.a(fileInputStream);
                    } catch (IOException e) {
                        dc.a(fileInputStream);
                        if (this.b != null) {
                        }
                        if (z) {
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        closeable = fileInputStream;
                        th = th3;
                        dc.a(closeable);
                        throw th;
                    }
                } catch (IOException e2) {
                    fileInputStream = null;
                    dc.a(fileInputStream);
                    if (this.b != null) {
                    }
                    if (z) {
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    dc.a(closeable);
                    throw th;
                }
                if (this.b != null && this.c == null) {
                    a2.delete();
                } else if (z && this.b != null) {
                    f.a(this.a, this.b);
                    return;
                } else {
                    return;
                }
            }
        }
        URLConnection a3 = em.a(this.a);
        long j = 0;
        String headerField = a3.getHeaderField("Cache-Control");
        if (!ct.c(headerField)) {
            String[] split = headerField.split(",");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String trim = split[i].trim();
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                        break;
                    } catch (NumberFormatException e3) {
                    }
                } else {
                    i++;
                }
            }
        }
        fileInputStream = a3.getInputStream();
        InputStream a4 = a(fileInputStream);
        dc.a(fileInputStream);
        gt gtVar = gt.a;
        if (gt.a(j) && a && !(this.b == null && this.c == null)) {
            gt gtVar2 = gt.a;
            URL url = this.a;
            if (gtVar2.b != null) {
                gtVar2.e.submit(new AnonymousClass2(gtVar2, url, a4, j));
            }
        }
        if (z && this.b != null) {
            f.a(this.a, this.b);
        }
    }

    private ByteArrayInputStream a(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        da.a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(toByteArray);
        hf hfVar = new hf();
        hfVar.a(toByteArray);
        he a = hfVar.a();
        if (a.b == 0) {
            this.c = toByteArray;
            this.d = a;
        } else {
            u uVar = u.a;
            this.b = u.a(byteArrayInputStream);
            byteArrayInputStream.reset();
        }
        return byteArrayInputStream;
    }

    gx(bs bsVar) {
        Object obj;
        if (bsVar.k() == bx.STRING) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.a = bsVar.e();
            return;
        }
        bsVar.h();
        String l = bsVar.l();
        while (bsVar.j()) {
            if (String.URL.equals(l)) {
                this.a = bsVar.e();
            } else {
                bsVar.s();
            }
        }
        bsVar.i();
    }
}
