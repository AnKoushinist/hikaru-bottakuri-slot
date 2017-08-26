package com.e.a.a.a;

import com.e.a.a.a.a.a.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class w implements InvocationHandler {
    private static final Object[] a = new Object[0];
    private final n b;
    private final y c;
    private final aa d;
    private final LinkedList e = new LinkedList();
    private final LinkedList f = new LinkedList();
    private boolean g;
    private Object h;

    w(n nVar, y yVar, aa aaVar) {
        a.a(nVar);
        a.a(yVar);
        a.a(aaVar);
        this.b = nVar;
        this.c = yVar;
        this.d = aaVar;
        nVar.a(new x(this));
    }

    static Object a(n nVar, y yVar, aa aaVar) {
        return Proxy.newProxyInstance(aaVar.a().getClassLoader(), new Class[]{r0}, new w(nVar, yVar, aaVar));
    }

    private Object a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            com.e.a.a.a.a.b.a.a(e);
        }
        return null;
    }

    private Object a(Method method, Object[] objArr) {
        if (Object.class.equals(method.getDeclaringClass())) {
            String name = method.getName();
            Class a = this.d.a();
            if ("getClass".equals(name)) {
                return a;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object invoke = method.invoke(this, objArr);
            return String.valueOf(invoke).replace(w.class.getName(), a.getName());
        } else if (this.g && this.h == null) {
            c();
            return a(method);
        } else {
            if (this.b.a() == p.ON) {
                b();
                if (this.h != null) {
                    return method.invoke(this.h, objArr);
                }
            }
            if (this.b.a() == p.OFF && !(this.g && this.h == null)) {
                b(method, objArr);
            }
            return a(method);
        }
    }

    private void b() {
        if (!this.g) {
            try {
                this.h = this.c.a().b(null);
            } catch (Exception e) {
            }
            this.g = true;
        }
        if (this.h != null) {
            List<LinkedList> linkedList = new LinkedList();
            linkedList.add(this.e);
            linkedList.add(this.f);
            for (LinkedList linkedList2 : linkedList) {
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    z zVar = (z) it.next();
                    try {
                        Object[] objArr = new Object[zVar.b.length];
                        WeakReference[] a = zVar.b;
                        int length = a.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = i2 + 1;
                            objArr[i2] = a[i].get();
                            i++;
                            i2 = i3;
                        }
                        zVar.d.invoke(this.h, objArr);
                    } catch (Exception e2) {
                    }
                }
                linkedList2.clear();
            }
        }
    }

    private void b(Method method, Object[] objArr) {
        if (this.e.size() < 5) {
            this.e.add(new z(this, method, objArr));
            return;
        }
        if (this.f.size() >= 10) {
            this.f.removeFirst();
        }
        this.f.add(new z(this, method, objArr));
    }

    private void c() {
        this.e.clear();
        this.f.clear();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return a(method, objArr);
        } catch (Exception e) {
            com.e.a.a.a.a.b.a.a(e);
            return a(method);
        }
    }
}
