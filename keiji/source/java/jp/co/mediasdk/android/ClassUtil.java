package jp.co.mediasdk.android;

public class ClassUtil extends ClassUtilConstructorSupport {
    public static Class a(String str) {
        return c(String.format("%s.R", new Object[]{str}));
    }

    public static Class b(String str) {
        try {
            Class cls = Class.forName(str);
            if (cls != null) {
                return cls;
            }
            LoggerBase.a(ClassUtil.class, "getClass", "class name '%s' is not exist.", str);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            LoggerBase.a(ClassUtil.class, "getClass", "class name '%s' is not exist.", str);
            return null;
        }
    }

    public static Class c(String str) {
        if (d(str)) {
            return b(str);
        }
        String format = String.format("jp.co.mediasdk.android.%s", new Object[]{str});
        if (d(format)) {
            return b(format);
        }
        format = String.format("%s.%s", new Object[]{PackageManagerBase.a(), str});
        if (d(format)) {
            return b(format);
        }
        LoggerBase.a(ClassUtil.class, "getClass", "no such class '%s'.", str);
        return null;
    }

    public static boolean d(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
