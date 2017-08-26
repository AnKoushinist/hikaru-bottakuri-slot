package jp.maio.sdk.android;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.File;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;

class am {
    private static av a;

    public static long a() {
        return b(new File(c())) / RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS_ON_STAY;
    }

    static av a(String str, boolean z) {
        af afVar = new af();
        ax.a("MaioAdsCreating future locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        try {
            afVar.add(bb.b.submit(new an(str, z)));
            afVar.a();
            ax.a("MaioAdsDownloaded media waitall finished locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        } catch (Throwable e) {
            ax.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
        } catch (Throwable e2) {
            ax.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e2);
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

    static void a(av avVar) {
        if (a() > 30) {
            b(avVar);
        }
        af afVar = new af();
        try {
            afVar.add(bb.b.submit(new ao(avVar)));
            afVar.a();
        } catch (Throwable e) {
            ax.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
        } catch (Throwable e2) {
            ax.a("#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e2);
        }
    }

    static void a(av avVar, int i) {
        File file = new File(c() + Operation.DIVISION + String.valueOf(i));
        if (file.exists()) {
            a(file);
        }
        if (avVar != null) {
            for (bj e : avVar.e.values()) {
                for (s sVar : e.e()) {
                    if (sVar != null) {
                        for (v vVar : sVar.f) {
                            if (vVar != null && vVar.b == i) {
                                vVar.a(w.None);
                            }
                        }
                    }
                }
            }
        }
    }

    static void a(av avVar, int i, int i2) {
        bb.b.submit(new ap(avVar, i));
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

    static void b(av avVar) {
        File file = new File(c());
        if (file.exists()) {
            a(file);
        }
        if (avVar != null) {
            for (bj e : avVar.e.values()) {
                for (s sVar : e.e()) {
                    if (sVar != null) {
                        for (v vVar : sVar.f) {
                            if (vVar != null) {
                                vVar.a(w.None);
                            }
                        }
                    }
                }
            }
        }
    }

    private static String c() {
        return t.b() + "/WebApiManager/videos/";
    }

    private static boolean e(av avVar) {
        boolean z = false;
        for (bj bjVar : avVar.e.values()) {
            if (bjVar.g != null) {
                s[] e = bjVar.e();
                int length = e.length;
                boolean z2 = z;
                int i = 0;
                while (i < length) {
                    s sVar = e[i];
                    i++;
                    z2++;
                }
                z = z2;
            }
        }
        return !z;
    }
}
