package jp.co.mediasdk.android;

import android.util.Log;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import jp.co.mediasdk.a;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class LoggerBase {
    protected static void a(int i, String str, Class cls, String str2, String str3, Object... objArr) {
        if (i <= Logger.a) {
            a(i, str, String.format("[%s::%s] %s", new Object[]{cls.getSimpleName(), str2, str3}), objArr);
        }
    }

    protected static void a(int i, String str, Object obj, String str2, String str3, Object... objArr) {
        if (i <= Logger.a) {
            a(i, str, String.format("[%s::%s] %s", new Object[]{obj.getClass().getSimpleName(), str2, str3}), objArr);
        }
    }

    protected static void a(int i, String str, String str2, Object... objArr) {
        if (i <= Logger.a) {
            String format;
            try {
                format = String.format("[%s] %s\n", new Object[]{str, String.format(str2, objArr)});
            } catch (Exception e) {
                format = String.format("[%s] %s\n", new Object[]{str, str2});
            }
            String simpleName = a.class.getSimpleName();
            switch (i) {
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                    Log.e(simpleName, format);
                    return;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    Log.w(simpleName, format);
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    Log.i(simpleName, format);
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    Log.d(simpleName, format);
                    return;
                default:
                    Log.v(simpleName, format);
                    return;
            }
        }
    }

    public static void a(String str) {
        a(2, String.VIDEO_ERROR, str, new Object[0]);
    }

    public static void a(Object obj, String str, String str2, Object... objArr) {
        a(2, String.VIDEO_ERROR, obj, str, str2, objArr);
    }

    public static void a(Class cls, String str, String str2, Object... objArr) {
        a(2, String.VIDEO_ERROR, cls, str, str2, objArr);
    }

    public static void b(Object obj, String str, String str2, Object... objArr) {
        a(3, "warn", obj, str, str2, objArr);
    }

    public static void c(Object obj, String str, String str2, Object... objArr) {
        a(4, "notice", obj, str, str2, objArr);
    }

    public static void b(String str) {
        a(6, TapjoyConstants.TJC_DEBUG, str, new Object[0]);
    }

    public static void c(String str) {
        a(7, "trace", str, null);
    }

    public static void d(Object obj, String str, String str2, Object... objArr) {
        a(7, "trace", obj, str, str2, objArr);
    }

    public static void b(Class cls, String str, String str2, Object... objArr) {
        a(7, "trace", cls, str, str2, objArr);
    }
}
