package com.glossomads;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class b extends ConcurrentLinkedQueue<JSONObject> {
    private static HashMap<String, b> a;
    private File b;

    public /* synthetic */ boolean add(Object obj) {
        return a((JSONObject) obj);
    }

    public /* synthetic */ boolean offer(Object obj) {
        return b((JSONObject) obj);
    }

    public /* synthetic */ Object peek() {
        return e();
    }

    public /* synthetic */ Object poll() {
        return d();
    }

    public /* synthetic */ Object remove() {
        return c();
    }

    public static b a(String str, Context context) {
        if (a == null) {
            a = new HashMap();
        }
        if (a.get(str) != null) {
            return (b) a.get(str);
        }
        b bVar = new b(str, context.getApplicationContext().getCacheDir().getAbsolutePath());
        a.put(str, bVar);
        return bVar;
    }

    private b(String str, String str2) {
        this.b = new File(str2, str + ".q");
        if (this.b.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(a())));
                String str3 = BuildConfig.FLAVOR;
                while (true) {
                    str3 = bufferedReader.readLine();
                    if (str3 != null) {
                        b(new JSONObject(str3));
                    } else {
                        return;
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    String a() {
        return this.b.getPath();
    }

    public void b() {
        a(0);
    }

    public void a(int i) {
        synchronized (this.b) {
            if (!this.b.exists()) {
                Log.d("FileBasedQueue", "file is not exists. make:" + this.b.getPath());
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
                    Log.d("FileBasedQueue", "cannot write " + this.b.getPath() + ". retry:" + i);
                    a(i + 1);
                }
                Log.e("FileBasedQueue", "cannot write " + this.b.getPath() + ".");
            }
        }
    }

    public boolean a(JSONObject jSONObject) {
        boolean add = super.add(jSONObject);
        b();
        return add;
    }

    public boolean b(JSONObject jSONObject) {
        boolean offer = super.offer(jSONObject);
        b();
        return offer;
    }

    public JSONObject c() {
        JSONObject jSONObject = (JSONObject) super.remove();
        b();
        return jSONObject;
    }

    public boolean remove(Object obj) {
        boolean remove = super.remove(obj);
        b();
        return remove;
    }

    public JSONObject d() {
        JSONObject jSONObject = (JSONObject) super.poll();
        b();
        return jSONObject;
    }

    public JSONObject e() {
        JSONObject jSONObject = (JSONObject) super.peek();
        b();
        return jSONObject;
    }

    public String toString() {
        String str = BuildConfig.FLAVOR;
        Iterator it = iterator();
        String str2 = str;
        while (it.hasNext()) {
            str2 = str2 + ((JSONObject) it.next()).toString() + "\n";
        }
        return str2;
    }

    public void clear() {
        super.clear();
        this.b.delete();
    }
}
