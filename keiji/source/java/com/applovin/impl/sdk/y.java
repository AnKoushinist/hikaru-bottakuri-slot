package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

public class y {
    private final AppLovinLogger a;
    private final AppLovinSdkImpl b;
    private final String c = "FileManager";
    private final Object d;

    y(AppLovinSdk appLovinSdk) {
        this.b = (AppLovinSdkImpl) appLovinSdk;
        this.a = appLovinSdk.h();
        this.d = new Object();
    }

    long a(long j) {
        return j / 1048576;
    }

    public File a(String str, Context context, boolean z) {
        if (AppLovinSdkUtils.d(str)) {
            this.a.a("FileManager", "Looking up cached resource: " + str);
            if (!a(context) && !z) {
                return null;
            }
            File file;
            if (str.contains("icon")) {
                str = str.replace("/", "_").replace(".", "_");
            }
            synchronized (this.d) {
                File b = b(context);
                file = new File(b, str);
                try {
                    b.mkdirs();
                } catch (Exception e) {
                    return null;
                }
            }
            return file;
        }
        this.b.h().a("FileManager", "Nothing to look up, skipping...");
        return null;
    }

    String a(Context context, String str) {
        return a(context, str, false);
    }

    String a(Context context, String str, boolean z) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            this.b.h().a("FileManager", "Nothing to cache, skipping...");
            return null;
        } else if (dm.a(this.b, str)) {
            this.b.h().a("FileManager", "Starting caching of resource " + str);
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            File a = a(lastPathSegment, context, false);
            return (a == null || !a.exists()) ? a(a, str) ? z ? Uri.fromFile(a).toString() : lastPathSegment : null : z ? Uri.fromFile(a).toString() : lastPathSegment;
        } else {
            this.b.h().a("FileManager", "Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
    }

    void a(long j, Context context) {
        long c = (long) c();
        if (c == -1) {
            this.a.a("FileManager", "Cache has no maximum size set; skipping drop...");
        } else if (a(j) > c) {
            this.a.a("FileManager", "Cache has exceeded maximum size; dropping...");
            g(context);
            this.b.n().a("cache_drop_count");
        } else {
            this.a.a("FileManager", "Cache is present but under size limit; not dropping...");
        }
    }

    boolean a() {
        return ((Boolean) this.b.a(cb.as)).booleanValue();
    }

    protected boolean a(Context context) {
        if (n.a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (n.c() && !((Boolean) this.b.a(cb.bp)).booleanValue()) {
            return true;
        }
        this.b.h().c("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    boolean a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        FileOutputStream fileOutputStream;
        boolean z;
        Throwable e;
        this.a.a("FileManager", "Writing resource to filesystem: " + file.getName());
        synchronized (this.d) {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                    z = true;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        this.a.b("FileManager", "Unable to write data to file", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                                z = false;
                            }
                        }
                        z = false;
                        return z;
                    } catch (Throwable th) {
                        e = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw e;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                fileOutputStream = null;
                this.a.b("FileManager", "Unable to write data to file", e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                z = false;
                return z;
            } catch (Throwable th2) {
                e = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
        }
        return z;
    }

    boolean a(File file) {
        boolean delete;
        this.a.a("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.d) {
            try {
                delete = file.delete();
            } catch (Throwable e) {
                this.a.b("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", e);
                delete = false;
            }
        }
        return delete;
    }

    boolean a(File file, String str) {
        Throwable e;
        HttpURLConnection httpURLConnection;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream = null;
        this.a.a("FileManager", "Starting caching of " + str + " into " + file.getAbsoluteFile());
        if (((Boolean) this.b.a(cb.be)).booleanValue() && !str.contains("https://")) {
            this.b.h().c("FileManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
            str = str.replace("http://", "https://");
        }
        InputStream inputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            HttpURLConnection httpURLConnection2;
            byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            } catch (IOException e2) {
                e = e2;
                httpURLConnection = null;
                byteArrayOutputStream = byteArrayOutputStream2;
                try {
                    this.a.b("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", e);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception e5) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    e = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception e8) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                httpURLConnection = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw e;
            }
            try {
                httpURLConnection2.setConnectTimeout(((Integer) this.b.a(cb.o)).intValue());
                httpURLConnection2.setReadTimeout(((Integer) this.b.a(cb.q)).intValue());
                httpURLConnection2.setDefaultUseCaches(true);
                httpURLConnection2.setUseCaches(true);
                httpURLConnection2.setAllowUserInteraction(false);
                httpURLConnection2.setInstanceFollowRedirects(true);
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode < HttpResponseCode.OK || responseCode >= HttpResponseCode.MULTIPLE_CHOICES) {
                    if (null != null) {
                        try {
                            inputStream2.close();
                        } catch (Exception e9) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e10) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e11) {
                        }
                    }
                    return false;
                }
                inputStream = httpURLConnection2.getInputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read < 0) {
                        break;
                    }
                    try {
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } catch (Exception e12) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e13) {
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e14) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e15) {
                            }
                        }
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e16) {
                            }
                        }
                        return false;
                    }
                }
                if (a(byteArrayOutputStream2, file)) {
                    this.a.a("FileManager", "Caching completed for " + file);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e17) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e18) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e19) {
                        }
                    }
                    return true;
                }
                this.a.d("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e20) {
                    }
                }
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e21) {
                    }
                }
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception e22) {
                    }
                }
                return false;
            } catch (Throwable e23) {
                byteArrayOutputStream = byteArrayOutputStream2;
                HttpURLConnection httpURLConnection3 = httpURLConnection2;
                e = e23;
                httpURLConnection = httpURLConnection3;
                this.a.b("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", e);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            } catch (Throwable e232) {
                Throwable th3 = e232;
                httpURLConnection = httpURLConnection2;
                e = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw e;
            }
        } catch (IOException e24) {
            e = e24;
            httpURLConnection = null;
            byteArrayOutputStream = null;
            this.a.b("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", e);
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        } catch (Throwable th4) {
            e = th4;
            httpURLConnection = null;
            byteArrayOutputStream2 = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }

    public boolean a(String str, Context context) {
        boolean b;
        synchronized (this.d) {
            b = b(str, context, false);
        }
        return b;
    }

    long b() {
        long longValue = ((Long) this.b.a(cb.at)).longValue();
        return (longValue < 0 || !a()) ? -1 : longValue;
    }

    File b(Context context) {
        return a(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    public boolean b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.d) {
            File a = a(str, context, z);
            z2 = (a == null || !a.exists() || a.isDirectory()) ? false : true;
        }
        return z2;
    }

    int c() {
        int intValue = ((Integer) this.b.a(cb.au)).intValue();
        return (intValue < 0 || !a()) ? -1 : intValue;
    }

    public List c(Context context) {
        File b = b(context);
        if (!b.isDirectory()) {
            return new ArrayList(0);
        }
        List asList;
        synchronized (this.d) {
            asList = Arrays.asList(b.listFiles());
        }
        return asList;
    }

    public boolean d(Context context) {
        if (((Boolean) this.b.a(cb.bq)).booleanValue()) {
            try {
                a(".nomedia", context, false);
                File file = new File(b(context), ".nomedia");
                if (file != null) {
                    if (file.exists()) {
                        return true;
                    }
                    this.b.h().a("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                    return file.createNewFile();
                }
            } catch (Throwable e) {
                this.b.h().a("FileManager", "Failed to create nomedia file", e);
            }
        }
        return false;
    }

    void e(Context context) {
        try {
            if (!a()) {
                return;
            }
            if (this.b.c()) {
                this.a.d("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                return;
            }
            this.a.a("FileManager", "Compacting cache...");
            synchronized (this.d) {
                a(f(context), context);
            }
        } catch (Throwable e) {
            this.a.b("FileManager", "Caught exception while compacting cache!", e);
            this.b.i().a(cb.as, Boolean.valueOf(false));
            this.b.i().b();
        }
    }

    long f(Context context) {
        long j = 0;
        long b = b();
        Object obj = b != -1 ? 1 : null;
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.d) {
            for (File file : c(context)) {
                Object obj2 = null;
                if (obj != null && toSeconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) > b) {
                    this.a.a("FileManager", "File " + file.getName() + " has expired, removing...");
                    a(file);
                    obj2 = 1;
                }
                if (obj2 != null) {
                    this.b.n().a("cached_files_expired");
                } else {
                    j += file.length();
                }
            }
        }
        return j;
    }

    void g(Context context) {
        synchronized (this.d) {
            for (File a : c(context)) {
                a(a);
            }
        }
    }
}
