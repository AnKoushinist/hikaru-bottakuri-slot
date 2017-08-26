package jp.maio.sdk.android;

import java.io.File;
import org.cocos2dx.lib.BuildConfig;

class aq {
    private static az a;

    public static long a() {
        return b(new File(c())) / 1000000;
    }

    static az a(String str, boolean z) {
        ai aiVar = new ai();
        bc.a("MaioAdsCreating future locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        try {
            aiVar.add(bg.b.submit(new ar(str, z)));
            aiVar.a();
            bc.a("MaioAdsDownloaded media waitall finished locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        } catch (Throwable e) {
            bc.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
        } catch (Throwable e2) {
            bc.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e2);
        }
        return a;
    }

    private static void a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                a(a);
            }
        }
        file.delete();
    }

    static void a(az azVar) {
        if (a() > ((long) azVar.e)) {
            c(azVar);
        }
        g(azVar);
    }

    static void a(az azVar, int i) {
        File file = new File(c() + "/" + String.valueOf(i));
        if (file.exists()) {
            a(file);
        }
        if (azVar != null) {
            for (bo e : azVar.f.values()) {
                for (u uVar : e.e()) {
                    if (uVar != null) {
                        for (x xVar : uVar.f) {
                            if (xVar != null && xVar.b == i) {
                                xVar.a(y.None);
                            }
                        }
                    }
                }
            }
        }
    }

    static void a(az azVar, int i, int i2) {
        bg.b.submit(new at(azVar, i));
    }

    private static long b(File file) {
        long j = 0;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int i = 0;
            while (i < listFiles.length) {
                long b = b(listFiles[i]) + j;
                i++;
                j = b;
            }
        }
        return file.isFile() ? j + file.length() : j;
    }

    static void b(az azVar) {
        if (a() > ((long) azVar.e)) {
            c(azVar);
        }
        ai aiVar = new ai();
        try {
            aiVar.add(bg.b.submit(new as(azVar)));
            aiVar.a();
        } catch (Throwable e) {
            bc.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
        } catch (Throwable e2) {
            bc.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e2);
        }
    }

    private static String c() {
        return v.b() + "/WebApiManager/videos/";
    }

    static void c(az azVar) {
        File file = new File(c());
        if (file.exists()) {
            a(file);
        }
        if (azVar != null) {
            for (bo e : azVar.f.values()) {
                for (u uVar : e.e()) {
                    if (uVar != null) {
                        for (x xVar : uVar.f) {
                            if (xVar != null) {
                                xVar.a(y.None);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void g(az azVar) {
        for (bo boVar : azVar.f.values()) {
            int i = 0;
            while (i > 0) {
                try {
                    Thread.sleep(20000);
                    break;
                } catch (bd e) {
                    i++;
                    if (i == 10) {
                        break;
                    }
                } catch (InterruptedException e2) {
                    i++;
                    if (i == 10) {
                        break;
                    }
                }
            }
            u b = boVar.b();
            if (b != null) {
                x c = b.c();
                if (c != null) {
                    b.i = azVar.b.d;
                    b.j = azVar.b.f;
                    c.b();
                }
            }
        }
    }

    private static boolean h(az azVar) {
        boolean z = false;
        for (bo boVar : azVar.f.values()) {
            if (boVar.g != null) {
                u[] e = boVar.e();
                int length = e.length;
                boolean z2 = z;
                int i = 0;
                while (i < length) {
                    u uVar = e[i];
                    i++;
                    z2++;
                }
                z = z2;
            }
        }
        return !z;
    }
}
