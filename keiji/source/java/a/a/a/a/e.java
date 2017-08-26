package a.a.a.a;

import a.a.a.a.a.b.i;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: FabricKitsFinder */
class e implements Callable<Map<String, k>> {
    final String a;

    public /* synthetic */ Object call() {
        return a();
    }

    e(String str) {
        this.a = str;
    }

    public Map<String, k> a() {
        Map<String, k> hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ZipFile b = b();
        Enumeration entries = b.entries();
        int i = 0;
        while (entries.hasMoreElements()) {
            int i2 = i + 1;
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > "fabric/".length()) {
                k a = a(zipEntry, b);
                if (a != null) {
                    hashMap.put(a.a(), a);
                    c.h().b("Fabric", String.format("Found kit:[%s] version:[%s]", new Object[]{a.a(), a.b()}));
                }
            }
            i = i2;
        }
        if (b != null) {
            try {
                b.close();
            } catch (IOException e) {
            }
        }
        c.h().b("Fabric", "finish scanning in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " reading:" + i);
        return hashMap;
    }

    private k a(ZipEntry zipEntry, ZipFile zipFile) {
        Closeable inputStream;
        Throwable e;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                Object property = properties.getProperty("fabric-identifier");
                Object property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                k kVar = new k(property, property2, property3);
                i.a(inputStream);
                return kVar;
            } catch (IOException e2) {
                e = e2;
                try {
                    c.h().e("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
                    i.a(inputStream);
                    return null;
                } catch (Throwable th) {
                    e = th;
                    i.a(inputStream);
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            c.h().e("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
            i.a(inputStream);
            return null;
        } catch (Throwable th2) {
            e = th2;
            inputStream = null;
            i.a(inputStream);
            throw e;
        }
    }

    protected ZipFile b() {
        return new ZipFile(this.a);
    }
}
