package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class ep {

    static class a implements InvocationHandler {
        private final Object a;
        private final Thread b;
        private final Looper c;

        public a(Object obj, Thread thread, Looper looper) {
            this.a = obj;
            this.b = thread;
            this.c = looper;
        }

        public final Object invoke(Object obj, final Method method, final Object[] objArr) {
            if (this.b == Thread.currentThread()) {
                return method.invoke(this.a, objArr);
            }
            if (method.getReturnType().equals(Void.TYPE)) {
                Runnable anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ a c;

                    public final void run() {
                        try {
                            method.invoke(this.c.a, objArr);
                        } catch (Throwable e) {
                            throw cu.a(e);
                        } catch (Throwable e2) {
                            throw cu.a(e2);
                        } catch (Throwable e22) {
                            throw cu.a(e22);
                        }
                    }
                };
                if (this.c != null && new Handler(this.c).post(anonymousClass1)) {
                    return null;
                }
                if (this.b == fr.b() && fr.a.a(anonymousClass1)) {
                    return null;
                }
                Looper mainLooper = Looper.getMainLooper();
                if (mainLooper == null || !new Handler(mainLooper).post(anonymousClass1)) {
                    return method.invoke(this.a, objArr);
                }
                return null;
            }
            throw new UnsupportedOperationException("method not return void: " + method.getName());
        }
    }

    public static Object a(Object obj, Class cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a(obj, Thread.currentThread(), Looper.myLooper()));
    }
}
