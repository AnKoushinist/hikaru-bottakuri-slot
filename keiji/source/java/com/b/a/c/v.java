package com.b.a.c;

import a.a.a.a.a.b.i;
import com.d.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: MetaDataStore */
class v {
    private static final Charset a = Charset.forName(c.DEFAULT_CHARSET);
    private final File b;

    public v(File file) {
        this.b = file;
    }

    public ae a(String str) {
        Closeable fileInputStream;
        Throwable e;
        File c = c(str);
        if (!c.exists()) {
            return ae.a;
        }
        try {
            fileInputStream = new FileInputStream(c);
            try {
                ae e2 = e(i.a((InputStream) fileInputStream));
                i.a(fileInputStream, "Failed to close user metadata file.");
                return e2;
            } catch (Exception e3) {
                e = e3;
                try {
                    a.a.a.a.c.h().e("CrashlyticsCore", "Error deserializing user metadata.", e);
                    i.a(fileInputStream, "Failed to close user metadata file.");
                    return ae.a;
                } catch (Throwable th) {
                    e = th;
                    i.a(fileInputStream, "Failed to close user metadata file.");
                    throw e;
                }
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            a.a.a.a.c.h().e("CrashlyticsCore", "Error deserializing user metadata.", e);
            i.a(fileInputStream, "Failed to close user metadata file.");
            return ae.a;
        } catch (Throwable th2) {
            e = th2;
            fileInputStream = null;
            i.a(fileInputStream, "Failed to close user metadata file.");
            throw e;
        }
    }

    public Map<String, String> b(String str) {
        Closeable fileInputStream;
        Throwable e;
        File d = d(str);
        if (!d.exists()) {
            return Collections.emptyMap();
        }
        try {
            fileInputStream = new FileInputStream(d);
            try {
                Map<String, String> f = f(i.a((InputStream) fileInputStream));
                i.a(fileInputStream, "Failed to close user metadata file.");
                return f;
            } catch (Exception e2) {
                e = e2;
                try {
                    a.a.a.a.c.h().e("CrashlyticsCore", "Error deserializing user metadata.", e);
                    i.a(fileInputStream, "Failed to close user metadata file.");
                    return Collections.emptyMap();
                } catch (Throwable th) {
                    e = th;
                    i.a(fileInputStream, "Failed to close user metadata file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            a.a.a.a.c.h().e("CrashlyticsCore", "Error deserializing user metadata.", e);
            i.a(fileInputStream, "Failed to close user metadata file.");
            return Collections.emptyMap();
        } catch (Throwable th2) {
            e = th2;
            fileInputStream = null;
            i.a(fileInputStream, "Failed to close user metadata file.");
            throw e;
        }
    }

    private File c(String str) {
        return new File(this.b, str + "user" + ".meta");
    }

    private File d(String str) {
        return new File(this.b, str + "keys" + ".meta");
    }

    private static ae e(String str) {
        JSONObject jSONObject = new JSONObject(str);
        return new ae(a(jSONObject, "userId"), a(jSONObject, "userName"), a(jSONObject, "userEmail"));
    }

    private static Map<String, String> f(String str) {
        JSONObject jSONObject = new JSONObject(str);
        Map<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            hashMap.put(str2, a(jSONObject, str2));
        }
        return hashMap;
    }

    private static String a(JSONObject jSONObject, String str) {
        return !jSONObject.isNull(str) ? jSONObject.optString(str, null) : null;
    }
}
