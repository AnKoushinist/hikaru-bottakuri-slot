package jp.co.mediasdk.mscore.ui.adformat;

public class MSAdListenerManager {
    private static MSAdFormatListener a;

    public static void a() {
        a = null;
    }

    public static void a(String str) {
        if (a != null) {
            a.a(str);
        }
    }
}
