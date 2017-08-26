package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;

public class GNSVideoTerm {
    private static int a = 0;
    private static int b = 0;
    private static int c = 0;

    public static int a() {
        a = (int) (Math.random() * 100.0d);
        return a;
    }

    public static int b() {
        return a;
    }

    public static boolean a(Context context) {
        GNSLogger a = GNSLogger.a();
        a.d("Term", "\u518d\u751f\u56de\u6570 " + GNSVideoStartCnt.b(context, ((long) c) * 60000));
        a.d("Term", "\u518d\u751f\u56de\u6570\u5236\u9650 " + b);
        a.d("Term", "\u518d\u751f\u56de\u6570\u5236\u9650\u306e\u30ea\u30bb\u30c3\u30c8\u9593\u9694(\u5206) " + c);
        if (b != 0 && b <= GNSVideoStartCnt.b(context, ((long) c) * 60000)) {
            return true;
        }
        return false;
    }

    public static void a(int i) {
        b = i;
    }

    public static void b(int i) {
        c = i;
    }

    public static int c() {
        return c;
    }
}
