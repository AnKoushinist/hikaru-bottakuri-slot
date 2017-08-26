package jp.maio.sdk.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

public class ar {
    private static ar c = null;
    private static String d = (t.b() + "/WebApiManager/viewlog/viewlog.log");
    private Timer a;
    private TimerTask b;
    private String e = "viewlog.log";
    private ArrayList f = new ArrayList();
    private boolean g = true;

    private ar() {
    }

    public static synchronized ar a(String str, int i) {
        ar arVar;
        synchronized (ar.class) {
            if (c == null) {
                c = new ar();
                c.b(str, i);
            }
            arVar = c;
        }
        return arVar;
    }

    private synchronized void a() {
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(d));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.f);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void a(int i) {
        Calendar instance = Calendar.getInstance();
        instance.add(12, -i);
        Collection arrayList = new ArrayList();
        for (int size = this.f.size() - 1; size >= 0; size--) {
            aq aqVar = (aq) this.f.get(size);
            if (instance.getTime().after(aqVar.e) && aqVar.d.booleanValue()) {
                arrayList.add(aqVar);
            }
        }
        if (!arrayList.isEmpty()) {
            this.f.removeAll(arrayList);
        }
    }

    private void a(String str) {
        this.b = new as(this, str);
    }

    private synchronized ArrayList b() {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        try {
            InputStream fileInputStream = new FileInputStream(new File(d));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrayList = (ArrayList) objectInputStream.readObject();
            try {
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private synchronized void b(String str, int i) {
        if (this.a == null) {
            File file = new File(d);
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (Exception e) {
                }
            }
            c.c(str, i);
        }
    }

    private void c(String str, int i) {
        this.f = b();
        this.a = new Timer();
        a(str);
        if (i < 1) {
            i = 20;
        }
        this.a.schedule(this.b, 0, (long) (i * GameControllerDelegate.THUMBSTICK_LEFT_X));
    }

    public synchronized void a(aq aqVar, int i) {
        boolean z;
        this.g = false;
        try {
            a(i);
            this.f.add(aqVar);
            a();
            z = "Add Log";
            ax.a(z, BuildConfig.FLAVOR, null);
            this.g = z;
        } catch (Exception e) {
            z = e;
            this.g = z;
        } finally {
            this.g = true;
        }
    }

    public synchronized boolean a(Date date, String str) {
        boolean z;
        int size = this.f.size() - 1;
        while (size >= 0) {
            aq aqVar = (aq) this.f.get(size);
            if (aqVar.a.equals(str)) {
                z = !date.before(aqVar.e);
            } else {
                size--;
            }
        }
        z = true;
        return z;
    }

    public synchronized int b(Date date, String str) {
        int i;
        i = 0;
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            aq aqVar = (aq) it.next();
            int i2 = (aqVar.a.equals(str) && !aqVar.b.booleanValue() && date.before(aqVar.e)) ? i + 1 : i;
            i = i2;
        }
        return i;
    }
}
