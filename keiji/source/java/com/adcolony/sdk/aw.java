package com.adcolony.sdk;

import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class aw {
    private LinkedList<Runnable> a = new LinkedList();
    private boolean b;

    aw() {
    }

    private boolean f(o oVar) {
        JSONObject b = oVar.b();
        String a = bb.a(b, "filepath");
        n.a().j().b();
        JSONObject a2 = bb.a();
        try {
            int b2 = bb.b(b, "offset");
            int b3 = bb.b(b, "size");
            boolean c = bb.c(b, "gunzip");
            String a3 = bb.a(b, "output_filepath");
            InputStream yVar = new y(new FileInputStream(a), b2, b3);
            if (c) {
                yVar = new GZIPInputStream(yVar, 1024);
            }
            if (a3.equals(BuildConfig.FLAVOR)) {
                StringBuilder stringBuilder = new StringBuilder(yVar.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    b2 = yVar.read(bArr, 0, 1024);
                    if (b2 < 0) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, b2, "ISO-8859-1"));
                }
                bb.b(a2, "size", stringBuilder.length());
                bb.a(a2, String.DATA, stringBuilder.toString());
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(a3);
                byte[] bArr2 = new byte[1024];
                int i = 0;
                while (true) {
                    b3 = yVar.read(bArr2, 0, 1024);
                    if (b3 < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr2, 0, b3);
                    i += b3;
                }
                fileOutputStream.close();
                bb.b(a2, "size", i);
            }
            yVar.close();
            bb.a(a2, "success", true);
            oVar.a(a2).a();
            return true;
        } catch (IOException e) {
            bb.a(a2, "success", false);
            oVar.a(a2).a();
            return false;
        } catch (OutOfMemoryError e2) {
            bd.g.b((Object) "Out of memory error - disabling AdColony.");
            n.a().a(true);
            bb.a(a2, "success", false);
            oVar.a(a2).a();
            return false;
        }
    }

    private boolean g(o oVar) {
        JSONObject b = oVar.b();
        String a = bb.a(b, "filepath");
        Object a2 = bb.a(b, "bundle_path");
        JSONArray f = bb.f(b, "bundle_filenames");
        n.a().j().b();
        JSONObject a3 = bb.a();
        try {
            File file = new File(a2);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[32];
            randomAccessFile.readInt();
            int readInt = randomAccessFile.readInt();
            JSONArray jSONArray = new JSONArray();
            byte[] bArr3 = new byte[1024];
            int i = 0;
            while (i < readInt) {
                randomAccessFile.seek((long) ((i * 44) + 8));
                randomAccessFile.read(bArr2);
                String str = new String(bArr2);
                randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                int readInt3 = randomAccessFile.readInt();
                jSONArray.put(readInt3);
                String str2 = BuildConfig.FLAVOR;
                try {
                    str2 = a + f.get(i);
                    randomAccessFile.seek((long) readInt2);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2);
                    int i2 = readInt3 / 1024;
                    readInt3 %= 1024;
                    for (readInt2 = 0; readInt2 < i2; readInt2++) {
                        randomAccessFile.read(bArr3, 0, 1024);
                        fileOutputStream.write(bArr3, 0, 1024);
                    }
                    randomAccessFile.read(bArr3, 0, readInt3);
                    fileOutputStream.write(bArr3, 0, readInt3);
                    fileOutputStream.close();
                    i++;
                } catch (JSONException e) {
                    bd.g.a("Could extract file name at index ").a(i).a(" unpacking ad unit bundle at").b(a2);
                    bb.a(a3, "success", false);
                    oVar.a(a3).a();
                    return false;
                }
            }
            randomAccessFile.close();
            file.delete();
            bb.a(a3, "success", true);
            bb.a(a3, "file_sizes", jSONArray);
            oVar.a(a3).a();
            return true;
        } catch (IOException e2) {
            bd.h.a("Failed to find or open ad unit bundle at path: ").b(a2);
            bb.a(a3, "success", false);
            oVar.a(a3).a();
            return false;
        } catch (OutOfMemoryError e3) {
            bd.g.b((Object) "Out of memory error - disabling AdColony.");
            n.a().a(true);
            bb.a(a3, "success", false);
            oVar.a(a3).a();
            return false;
        }
    }

    void a() {
        n.a("FileSystem.save", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        this.b.a.a(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.delete", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        this.b.a.a(oVar, new File(bb.a(oVar.b(), "filepath")));
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.listing", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void run() {
                        this.b.a.b(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.load", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 b;

                    public void run() {
                        this.b.a.c(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.rename", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 b;

                    public void run() {
                        this.b.a.d(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.exists", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        this.b.a.e(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.extract", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 b;

                    public void run() {
                        this.b.a.f(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
        n.a("FileSystem.unpack_bundle", new q(this) {
            final /* synthetic */ aw a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass8 b;

                    public void run() {
                        this.b.a.g(oVar);
                        this.b.a.b();
                    }
                });
            }
        });
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        String a = bb.a(b, "filepath");
        String a2 = bb.a(b, String.DATA);
        String a3 = bb.a(b, "encoding");
        boolean z = a3 != null && a3.equals("utf8");
        n.a().j().b();
        JSONObject a4 = bb.a();
        try {
            a(a, a2, z);
            bb.a(a4, "success", true);
            oVar.a(a4).a();
            return true;
        } catch (IOException e) {
            bb.a(a4, "success", false);
            oVar.a(a4).a();
            return false;
        }
    }

    void a(String str, String str2, boolean z) {
        BufferedWriter bufferedWriter = z ? new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str), c.DEFAULT_CHARSET)) : new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str)));
        bufferedWriter.write(str2);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    boolean a(o oVar, File file) {
        n.a().j().b();
        JSONObject a = bb.a();
        try {
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.list().length == 0) {
                file.delete();
            } else {
                String[] list = file.list();
                for (String file2 : list) {
                    a(oVar, new File(file, file2));
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
            bb.a(a, "success", true);
            oVar.a(a).a();
            return true;
        } catch (Exception e) {
            bb.a(a, "success", false);
            oVar.a(a).a();
            return false;
        }
    }

    boolean b(o oVar) {
        String a = bb.a(oVar.b(), "filepath");
        n.a().j().b();
        JSONObject a2 = bb.a();
        String[] list = new File(a).list();
        if (list != null) {
            JSONArray b = bb.b();
            for (String str : list) {
                JSONObject a3 = bb.a();
                bb.a(a3, "filename", str);
                if (new File(a + str).isDirectory()) {
                    bb.a(a3, "is_folder", true);
                } else {
                    bb.a(a3, "is_folder", false);
                }
                bb.a(b, (Object) a3);
            }
            bb.a(a2, "success", true);
            bb.a(a2, "entries", b);
            oVar.a(a2).a();
            return true;
        }
        bb.a(a2, "success", false);
        oVar.a(a2).a();
        return false;
    }

    String c(o oVar) {
        boolean z = true;
        JSONObject b = oVar.b();
        String a = bb.a(b, "filepath");
        String a2 = bb.a(b, "encoding");
        if (a2 == null || !a2.equals("utf8")) {
            z = false;
        }
        n.a().j().b();
        b = bb.a();
        try {
            StringBuilder a3 = a(a, z);
            bb.a(b, "success", true);
            bb.a(b, String.DATA, a3.toString());
            oVar.a(b).a();
            return a3.toString();
        } catch (IOException e) {
            bb.a(b, "success", false);
            oVar.a(b).a();
            return BuildConfig.FLAVOR;
        }
    }

    StringBuilder a(String str, boolean z) {
        File file = new File(str);
        StringBuilder stringBuilder = new StringBuilder((int) file.length());
        BufferedReader bufferedReader = z ? new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), c.DEFAULT_CHARSET)) : new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
                stringBuilder.append("\n");
            } else {
                bufferedReader.close();
                return stringBuilder;
            }
        }
    }

    boolean d(o oVar) {
        JSONObject b = oVar.b();
        String a = bb.a(b, "filepath");
        String a2 = bb.a(b, "new_filepath");
        n.a().j().b();
        JSONObject a3 = bb.a();
        try {
            if (new File(a).renameTo(new File(a2))) {
                bb.a(a3, "success", true);
                oVar.a(a3).a();
                return true;
            }
            bb.a(a3, "success", false);
            oVar.a(a3).a();
            return false;
        } catch (Exception e) {
            bb.a(a3, "success", false);
            oVar.a(a3).a();
            return false;
        }
    }

    boolean e(o oVar) {
        String a = bb.a(oVar.b(), "filepath");
        n.a().j().b();
        JSONObject a2 = bb.a();
        try {
            boolean exists = new File(a).exists();
            bb.a(a2, "result", exists);
            bb.a(a2, "success", true);
            oVar.a(a2).a();
            return exists;
        } catch (Exception e) {
            bb.a(a2, "result", false);
            bb.a(a2, "success", false);
            oVar.a(a2).a();
            e.printStackTrace();
            return false;
        }
    }

    void a(Runnable runnable) {
        if (!this.a.isEmpty() || this.b) {
            this.a.push(runnable);
            return;
        }
        this.b = true;
        runnable.run();
    }

    void b() {
        this.b = false;
        if (!this.a.isEmpty()) {
            this.b = true;
            ((Runnable) this.a.removeLast()).run();
        }
    }
}
