package jp.co.mediasdk.android;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HandlerManager {
    protected static Handler a = null;
    protected static List<RunnableEX> b = new ArrayList();

    public static boolean a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a = new Handler();
            return true;
        } else if (!ResourceContextSupport.j()) {
            return false;
        } else {
            a = new Handler(ResourceContextSupport.i().getMainLooper());
            return true;
        }
    }

    public static Handler b() {
        if (!c()) {
            a();
        }
        return a;
    }

    public static boolean c() {
        return a != null;
    }

    public static boolean a(Class cls) {
        if (cls == null) {
            LoggerBase.a(HandlerManager.class, "has", "class is null.", new Object[0]);
            return false;
        }
        for (RunnableEX runnableEX : b) {
            if (StringUtilEqualsSupport.a(runnableEX.getClass().getName(), cls.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(RunnableEX runnableEX) {
        return b.contains(runnableEX);
    }

    public static boolean b(RunnableEX runnableEX) {
        if (runnableEX == null) {
            LoggerBase.a(HandlerManager.class, "removeCallbacks", "runner is null.", new Object[0]);
            return false;
        }
        if (a(runnableEX)) {
            runnableEX.a(null);
            runnableEX.a(false);
            b().removeCallbacks(runnableEX);
        }
        b.remove(runnableEX);
        return true;
    }

    public static boolean b(Class cls) {
        if (cls == null) {
            LoggerBase.a(HandlerManager.class, "removeCallbacks", "class is null.", new Object[0]);
            return false;
        } else if (!a(cls)) {
            return true;
        } else {
            ArrayList arrayList = new ArrayList();
            for (RunnableEX runnableEX : b) {
                if (StringUtilEqualsSupport.a(runnableEX.getClass().getName(), cls.getName())) {
                    arrayList.add(runnableEX);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b((RunnableEX) it.next());
            }
            return true;
        }
    }

    public static boolean c(RunnableEX runnableEX) {
        if (c()) {
            if (!a(runnableEX)) {
                b.add(runnableEX);
            }
            runnableEX.b(1);
            b().post(runnableEX);
            return true;
        }
        LoggerBase.a(HandlerManager.class, "post", "hadnler is null.", new Object[0]);
        return false;
    }

    public static boolean a(RunnableEX runnableEX, long j) {
        if (c()) {
            if (!a(runnableEX)) {
                b.add(runnableEX);
            }
            runnableEX.b(1);
            b().postDelayed(runnableEX, j);
            return true;
        }
        LoggerBase.a(HandlerManager.class, "post", "hadnler is null.", new Object[0]);
        return false;
    }
}
