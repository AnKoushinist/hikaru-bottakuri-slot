package com.glossomads.Model;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.glossomads.View.v;
import com.glossomads.b.b;
import com.glossomads.m;
import com.glossomads.s;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class e {
    private String a;
    private String b;
    private final int c = 0;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.a + Operation.DIVISION + "movie" + this.b;
    }

    public static String a(String str, String str2) {
        return str + Operation.DIVISION + "movie" + str2;
    }

    public String d() {
        return this.a + Operation.DIVISION + "thumbnailW.png";
    }

    public String e() {
        return this.a + Operation.DIVISION + "thumbnailH.png";
    }

    public void b(String str, String str2) {
        this.a = str;
        this.b = str2;
        f();
    }

    public boolean f() {
        if (SugarUtil.isEmptyValue(this.a)) {
            SugarDebugLogger.d("asset file dir is null");
            return false;
        }
        g();
        return SugarUtil.makeDir(this.a);
    }

    public void g() {
        if (!SugarUtil.isEmptyValue(this.a)) {
            SugarUtil.deleteFile(new File(this.a));
        }
    }

    public boolean h() {
        if (SugarUtil.isEmptyValue(this.a) || SugarUtil.isEmptyValue(this.b)) {
            return false;
        }
        return new File(this.a).exists();
    }

    public long i() {
        if (SugarUtil.isEmptyValue(this.a)) {
            return 0;
        }
        File file = new File(this.a);
        if (file.exists()) {
            return file.lastModified();
        }
        return 0;
    }

    public long j() {
        return ((0 + new File(c()).length()) + new File(d()).length()) + new File(e()).length();
    }

    public boolean k() {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        Exception e;
        FileOutputStream fileOutputStream3;
        Throwable th;
        FileOutputStream fileOutputStream4 = null;
        boolean z = false;
        if (s.a().b()) {
            return true;
        }
        File file = new File(c());
        if (file.exists()) {
            Bitmap a;
            Bitmap a2;
            Point q = m.q();
            int i = q.x;
            int i2 = q.y;
            if (i < i2) {
                a = v.a(file, i, i2, 0);
                a2 = v.a(file, i2, i, 0);
            } else {
                a = v.a(file, i2, i, 0);
                a2 = v.a(file, i, i2, 0);
            }
            try {
                fileOutputStream = new FileOutputStream(new File(d()));
                try {
                    fileOutputStream2 = new FileOutputStream(new File(e()));
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream3 = null;
                    fileOutputStream4 = fileOutputStream;
                    try {
                        SugarDebugLogger.printStackTrace(e);
                        SugarUtil.close(fileOutputStream4);
                        SugarUtil.close(fileOutputStream3);
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream4;
                        fileOutputStream4 = fileOutputStream3;
                        SugarUtil.close(fileOutputStream);
                        SugarUtil.close(fileOutputStream4);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    SugarUtil.close(fileOutputStream);
                    SugarUtil.close(fileOutputStream4);
                    throw th;
                }
                try {
                    boolean z2;
                    a.compress(CompressFormat.PNG, 100, fileOutputStream);
                    a2.compress(CompressFormat.PNG, 100, fileOutputStream2);
                    if (a.getWidth() <= 0 || a.getHeight() <= 0 || a2.getWidth() <= 0 || a2.getHeight() <= 0) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    SugarUtil.close(fileOutputStream);
                    SugarUtil.close(fileOutputStream2);
                    z = z2;
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream3 = fileOutputStream2;
                    fileOutputStream4 = fileOutputStream;
                    SugarDebugLogger.printStackTrace(e);
                    SugarUtil.close(fileOutputStream4);
                    SugarUtil.close(fileOutputStream3);
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream4 = fileOutputStream2;
                    SugarUtil.close(fileOutputStream);
                    SugarUtil.close(fileOutputStream4);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                fileOutputStream3 = null;
                SugarDebugLogger.printStackTrace(e);
                SugarUtil.close(fileOutputStream4);
                SugarUtil.close(fileOutputStream3);
                return z;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                SugarUtil.close(fileOutputStream);
                SugarUtil.close(fileOutputStream4);
                throw th;
            }
        }
        return z;
    }

    public static File a(d dVar, String str, String str2) {
        if (dVar != null && SugarUtil.isNotEmptyValue(str) && SugarUtil.isNotEmptyValue(str2)) {
            str.toLowerCase();
            if (!SugarUtil.isNotEmptyValue(str) || str.endsWith(".m4v")) {
                File file = new File(dVar.b().c());
                if (file.exists()) {
                    new File(dVar.b().a()).setLastModified(System.currentTimeMillis());
                    return file;
                }
                throw new b(b.a);
            }
            throw new b(b.g);
        }
        throw new b(b.h);
    }

    public static Bitmap a(d dVar, boolean z) {
        Exception e;
        Throwable th;
        if (dVar == null) {
            return null;
        }
        File file;
        Bitmap decodeStream;
        if (z) {
            file = new File(dVar.b().d());
        } else {
            file = new File(dVar.b().e());
        }
        if (file.exists()) {
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    decodeStream = BitmapFactory.decodeStream(fileInputStream);
                    SugarUtil.close(fileInputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        SugarDebugLogger.printStackTrace(e);
                        SugarUtil.close(fileInputStream);
                        decodeStream = null;
                        return decodeStream;
                    } catch (Throwable th2) {
                        th = th2;
                        SugarUtil.close(fileInputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                SugarDebugLogger.printStackTrace(e);
                SugarUtil.close(fileInputStream);
                decodeStream = null;
                return decodeStream;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                SugarUtil.close(fileInputStream);
                throw th;
            }
        }
        decodeStream = null;
        return decodeStream;
    }
}
