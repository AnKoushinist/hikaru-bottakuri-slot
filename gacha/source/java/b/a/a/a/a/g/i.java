package b.a.a.a.a.g;

import b.a.a.a.a.f.b;
import b.a.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

/* compiled from: DefaultCachedSettingsIo */
class i implements g {
    private final b.a.a.a.i a;

    public i(b.a.a.a.i iVar) {
        this.a = iVar;
    }

    public JSONObject a() {
        Closeable fileInputStream;
        Throwable e;
        Closeable closeable = null;
        c.h().a("Fabric", "Reading cached settings...");
        try {
            JSONObject jSONObject;
            File file = new File(new b(this.a).a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(b.a.a.a.a.b.i.a((InputStream) fileInputStream));
                    closeable = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        c.h().e("Fabric", "Failed to fetch cached settings", e);
                        b.a.a.a.a.b.i.a(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileInputStream;
                        b.a.a.a.a.b.i.a(closeable, "Error while closing settings cache file.");
                        throw e;
                    }
                }
            }
            c.h().a("Fabric", "No cached settings found.");
            jSONObject = null;
            b.a.a.a.a.b.i.a(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            c.h().e("Fabric", "Failed to fetch cached settings", e);
            b.a.a.a.a.b.i.a(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            e = th2;
            b.a.a.a.a.b.i.a(closeable, "Error while closing settings cache file.");
            throw e;
        }
    }

    public void a(long j, JSONObject jSONObject) {
        Closeable fileWriter;
        Throwable e;
        c.h().a("Fabric", "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(new b(this.a).a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    b.a.a.a.a.b.i.a(fileWriter, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        c.h().e("Fabric", "Failed to cache settings", e);
                        b.a.a.a.a.b.i.a(fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileWriter;
                        b.a.a.a.a.b.i.a(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                c.h().e("Fabric", "Failed to cache settings", e);
                b.a.a.a.a.b.i.a(fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                e = th2;
                b.a.a.a.a.b.i.a(closeable, "Failed to close settings writer.");
                throw e;
            }
        }
    }
}
