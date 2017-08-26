package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class gt {
    public static final gt a = new gt();
    public Context b;
    public SharedPreferences c = null;
    public SharedPreferences d = null;
    ExecutorService e = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private File f;

    class AnonymousClass2 implements Runnable {
        final /* synthetic */ URL a;
        final /* synthetic */ InputStream b;
        final /* synthetic */ long c;
        final /* synthetic */ gt d;

        AnonymousClass2(gt gtVar, URL url, InputStream inputStream, long j) {
            this.d = gtVar;
            this.a = url;
            this.b = inputStream;
            this.c = j;
        }

        public final void run() {
            long j = 604800;
            try {
                File createTempFile = File.createTempFile("tj_", null, this.d.b());
                if (createTempFile == null) {
                    new Object[1][0] = this.a;
                    return;
                }
                OutputStream fileOutputStream = new FileOutputStream(createTempFile);
                try {
                    da.a(this.b, fileOutputStream);
                    fileOutputStream.close();
                    long j2 = this.c;
                    if (j2 <= 604800) {
                        j = j2;
                    }
                    j = (j * 1000) + y.b();
                    synchronized (this.d) {
                        String b = this.d.b(this.a);
                        if (createTempFile.renameTo(this.d.a(b))) {
                            this.d.c.edit().putLong(b, j).commit();
                            Object[] objArr = new Object[]{createTempFile, b, this.a};
                        }
                    }
                } catch (IOException e) {
                    new Object[1][0] = this.a;
                }
            } catch (FileNotFoundException e2) {
                new Object[1][0] = this.a;
            } catch (IOException e3) {
                new Object[1][0] = this.a;
            }
        }
    }

    private gt() {
    }

    public static boolean a(long j) {
        return j >= 3600;
    }

    public final void a() {
        this.e.submit(new Runnable(this) {
            final /* synthetic */ gt a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.b != null) {
                    this.a.c();
                }
            }
        });
    }

    public final File a(URL url) {
        if (this.b == null) {
            return null;
        }
        synchronized (this) {
            String b = b(url);
            File a = a(b);
            if (a.exists()) {
                long b2 = y.b();
                long j = this.c.getLong(b, 0);
                if (j >= b2) {
                    Object[] objArr = new Object[]{b, url};
                    return a;
                }
                Object[] objArr2 = new Object[]{b, Long.valueOf(b2), Long.valueOf(j)};
                if (j != 0) {
                    this.c.edit().remove(b).commit();
                    this.d.edit().remove(b).commit();
                }
                a.delete();
                return null;
            }
            return null;
        }
    }

    final synchronized String b(URL url) {
        String str;
        String url2 = url.toString();
        str = new String(if.a(cm.a(url2.getBytes())));
        String string = this.d.getString(str, null);
        if (string == null) {
            this.d.edit().putString(str, url2).commit();
        } else if (!string.equals(url2)) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                string = str + "_" + i2;
                String string2 = this.d.getString(string, null);
                if (string2 == null || string2.equals(url2)) {
                    this.d.edit().putString(string, url2).commit();
                } else {
                    i = i2;
                }
            }
            this.d.edit().putString(string, url2).commit();
            str = string;
        }
        return str;
    }

    final File b() {
        File file = this.f;
        if (file == null) {
            file = new File(this.b.getCacheDir(), "tapjoy_mm_cache");
            this.f = file;
        }
        if (!file.isDirectory()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    final File a(String str) {
        return new File(b(), str);
    }

    private synchronized void c() {
        Object obj = 1;
        synchronized (this) {
            int i;
            Object obj2;
            long b = y.b();
            File[] listFiles = b().listFiles();
            HashMap hashMap = new HashMap();
            if (listFiles != null) {
                for (File file : listFiles) {
                    hashMap.put(file.getName(), file);
                }
            }
            Editor edit = this.c.edit();
            Editor edit2 = this.d.edit();
            Map hashMap2 = new HashMap(this.c.getAll());
            Map hashMap3 = new HashMap(this.d.getAll());
            Iterator it = hashMap2.entrySet().iterator();
            Object obj3 = null;
            while (it.hasNext()) {
                String str = (String) ((Entry) it.next()).getKey();
                if (hashMap3.containsKey(str)) {
                    obj2 = obj3;
                } else {
                    it.remove();
                    edit.remove(str);
                    new Object[1][0] = str;
                    obj2 = 1;
                }
                obj3 = obj2;
            }
            Iterator it2 = hashMap3.entrySet().iterator();
            while (it2.hasNext()) {
                str = (String) ((Entry) it2.next()).getKey();
                if (hashMap2.containsKey(str)) {
                    obj2 = obj3;
                } else {
                    it2.remove();
                    edit2.remove(str);
                    new Object[1][0] = str;
                    obj2 = 1;
                }
                obj3 = obj2;
            }
            it = hashMap2.entrySet().iterator();
            Object obj4 = obj3;
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                String str2 = (String) entry.getKey();
                if (((Long) entry.getValue()).longValue() < b) {
                    it.remove();
                    edit.remove(str2);
                    edit2.remove(str2);
                    obj4 = 1;
                } else {
                    hashMap.remove(str2);
                }
            }
            for (Entry entry2 : hashMap.entrySet()) {
                File file2 = (File) entry2.getValue();
                file2.delete();
                new Object[1][0] = file2;
            }
            if (hashMap2.size() > 30) {
                int size = hashMap2.size() - 30;
                List linkedList = new LinkedList(hashMap2.entrySet());
                Collections.sort(linkedList, new Comparator(this) {
                    final /* synthetic */ gt a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ int compare(Object obj, Object obj2) {
                        return ((Long) ((Entry) obj).getValue()).compareTo((Long) ((Entry) obj2).getValue());
                    }
                });
                Iterator it3 = linkedList.iterator();
                for (i = 0; i < size && it3.hasNext(); i++) {
                    entry2 = (Entry) it3.next();
                    str2 = (String) entry2.getKey();
                    Long l = (Long) entry2.getValue();
                    edit.remove(str2);
                    edit2.remove(str2);
                    a(str2).delete();
                    Object[] objArr = new Object[]{r1, l};
                }
            } else {
                obj = obj4;
            }
            if (obj != null) {
                edit.commit();
                edit2.commit();
            }
        }
    }
}
