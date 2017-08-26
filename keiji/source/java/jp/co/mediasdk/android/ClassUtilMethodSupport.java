package jp.co.mediasdk.android;

public class ClassUtilMethodSupport extends ClassUtilDeclaredFieldSupport {
    public static boolean a(Class cls, String str) {
        if (cls == null) {
            LoggerBase.a(ClassUtilMethodSupport.class, "hasMethod", "clazz is null.", new Object[0]);
            return false;
        }
        try {
            return cls.getMethod(str, (Class[]) null) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return a(obj.getClass(), str);
    }
}
