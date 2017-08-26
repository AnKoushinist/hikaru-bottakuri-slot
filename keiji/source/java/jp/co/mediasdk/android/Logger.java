package jp.co.mediasdk.android;

public class Logger extends LoggerDumpSupport {
    protected static int a = 4;
    private static boolean b = false;

    public static void a(int i) {
        a = i;
    }

    public static void a() {
        b = true;
        a(7);
    }
}
