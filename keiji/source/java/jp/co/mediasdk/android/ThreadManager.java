package jp.co.mediasdk.android;

import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    protected static List<RunnableEX> a = new ArrayList();

    public static boolean a(RunnableEX runnableEX) {
        if (!b(runnableEX)) {
            a.add(runnableEX);
        }
        runnableEX.b(2);
        new Thread(runnableEX).start();
        LoggerBase.b(ThreadManager.class, String.VIDEO_START, "runnable '%s' thread is now started.", runnableEX.toString());
        LoggerBase.b(ThreadManager.class, String.VIDEO_START, "'%d' runners are exist.", Integer.valueOf(a.size()));
        return true;
    }

    public static boolean b(RunnableEX runnableEX) {
        LoggerBase.b(ThreadManager.class, "has", "'%d' runners are exist.", Integer.valueOf(a.size()));
        return a.contains(runnableEX);
    }

    public static boolean c(RunnableEX runnableEX) {
        if (runnableEX == null) {
            LoggerBase.a(ThreadManager.class, "removeCallbacks", "runner is null.", new Object[0]);
            return false;
        }
        if (a.contains(runnableEX)) {
            runnableEX.a(null);
            runnableEX.a(false);
            a.remove(runnableEX);
            LoggerBase.b(ThreadManager.class, "removeCallbacks", "'%s' is removed.", runnableEX.toString());
        }
        return true;
    }

    public static int a() {
        return a.size();
    }
}
