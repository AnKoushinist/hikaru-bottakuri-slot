package com.glossomads;

import android.content.Context;
import android.util.Pair;
import com.glossomads.Logger.SugarDebugLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.BuildConfig;

public class a<T extends c> extends ConcurrentHashMap<String, T> {
    private static HashMap<String, a> a;
    private File b;
    private Class<T> c;

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return a((String) obj, (c) obj2);
    }

    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return b((String) obj, (c) obj2);
    }

    public /* synthetic */ Object remove(Object obj) {
        return a(obj);
    }

    public static <T> a a(String str, Context context, Class<T> cls) {
        if (a == null) {
            a = new HashMap();
        }
        if (a.get(str) != null) {
            return (a) a.get(str);
        }
        a aVar = new a(str, context.getApplicationContext().getCacheDir().getAbsolutePath(), cls);
        a.put(str, aVar);
        return aVar;
    }

    private a() {
    }

    private a(String str, String str2, Class<T> cls) {
        this.b = new File(str2, str + ".m");
        this.c = cls;
        if (this.b.exists()) {
            SugarDebugLogger.d("load existing mapfile: " + this.b.getAbsolutePath().toString());
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(a())));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        Pair a = a(readLine);
                        a((String) a.first, ((c) cls.newInstance()).c((String) a.second));
                        SugarDebugLogger.d("load map: " + readLine);
                    } else {
                        return;
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public Pair<String, String> a(String str) {
        String[] split = str.split("\t", 0);
        if (split.length > 1) {
            return new Pair(split[0], split[1]);
        }
        return new Pair(split[0], BuildConfig.FLAVOR);
    }

    public String a() {
        return this.b.getPath();
    }

    public void b() {
        a(0);
    }

    public void a(int i) {
        synchronized (this.b) {
            if (!this.b.exists()) {
                SugarDebugLogger.d("FileBasedMap", "file is not exists. make:" + this.b.getPath());
                if (!this.b.getParentFile().exists()) {
                    this.b.getParentFile().mkdirs();
                }
                try {
                    this.b.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter fileWriter = new FileWriter(this.b);
                fileWriter.write(toString());
                fileWriter.close();
            } catch (Exception e2) {
                if (i < 3) {
                    SugarDebugLogger.d("FileBasedMap", "cannot write " + this.b.getPath() + ". retry:" + i);
                    a(i + 1);
                }
                SugarDebugLogger.e("FileBasedMap", "cannot write " + this.b.getPath() + ".");
            }
        }
    }

    public Object clone() {
        return this;
    }

    public T a(String str, T t) {
        c cVar = (c) super.put(str, t);
        b();
        return cVar;
    }

    public void putAll(Map<? extends String, ? extends T> map) {
        super.putAll(map);
        b();
    }

    public T a(Object obj) {
        c cVar = (c) super.remove(obj);
        b();
        return cVar;
    }

    public void clear() {
        super.clear();
        this.b.delete();
    }

    public String toString() {
        String str = BuildConfig.FLAVOR;
        String str2 = str;
        for (Entry entry : entrySet()) {
            str2 = str2 + ((String) entry.getKey()) + "\t" + ((c) entry.getValue()).toString() + "\n";
        }
        return str2;
    }

    public T b(String str, T t) {
        c cVar = (c) super.putIfAbsent(str, t);
        b();
        return cVar;
    }
}
