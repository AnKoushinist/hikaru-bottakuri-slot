package jp.co.mediasdk.android;

import java.util.LinkedList;

public class ThreadQueue {
    protected static int a = 1;
    protected static LinkedList<RunnableEX> b = new LinkedList();

    public static int a() {
        return b.size();
    }

    public static void b() {
        synchronized (ThreadQueue.class) {
            if (b.size() == 0) {
                LoggerBase.b(ThreadQueue.class, "next", "queue is empty.", new Object[0]);
                return;
            }
            LoggerBase.b(ThreadQueue.class, "next", "queue count is '%d', thread count is '%d'.", Integer.valueOf(a()), Integer.valueOf(ThreadManager.a()));
            if (ThreadManager.a() >= a) {
                LoggerBase.b(ThreadQueue.class, "next", "wait for previous thread.", new Object[0]);
                return;
            }
            RunnableEX runnableEX = (RunnableEX) b.poll();
            if (runnableEX == null) {
                return;
            }
            ThreadManager.a(runnableEX);
            b();
        }
    }
}
