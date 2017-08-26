package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.asserts.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ay implements InvocationHandler {
    private static final Object[] a = new Object[0];
    private final ap b;
    private final ba c;
    private final bc d;
    private final LinkedList e = new LinkedList();
    private final LinkedList f = new LinkedList();
    private boolean g;
    private Object h;

    ay(ap apVar, ba baVar, bc bcVar) {
        a.a(apVar);
        a.a(baVar);
        a.a(bcVar);
        this.b = apVar;
        this.c = baVar;
        this.d = bcVar;
        apVar.a(new az(this));
    }

    static Object a(ap apVar, ba baVar, bc bcVar) {
        return Proxy.newProxyInstance(bcVar.a().getClassLoader(), new Class[]{r0}, new ay(apVar, baVar, bcVar));
    }

    private Object a(Method method) {
        try {
            if (Boolean.TYPE.equals(method.getReturnType())) {
                return Boolean.valueOf(true);
            }
        } catch (Exception e) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
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
            return String.valueOf(invoke).replace(ay.class.getName(), a.getName());
        } else if (this.g && this.h == null) {
            c();
            return a(method);
        } else {
            if (this.b.a() == ar.ON) {
                b();
                if (this.h != null) {
                    return method.invoke(this.h, objArr);
                }
            }
            if (this.b.a() == ar.OFF && !(this.g && this.h == null)) {
                b(method, objArr);
            }
            return a(method);
        }
    }

    private void b() {
        if (!this.g) {
            try {
                this.h = this.c.a().c(null);
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
                    bb bbVar = (bb) it.next();
                    try {
                        Object[] objArr = new Object[bbVar.b.length];
                        WeakReference[] a = bbVar.b;
                        int length = a.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = i2 + 1;
                            objArr[i2] = a[i].get();
                            i++;
                            i2 = i3;
                        }
                        bbVar.d.invoke(this.h, objArr);
                    } catch (Exception e2) {
                    }
                }
                linkedList2.clear();
            }
        }
    }

    private void b(Method method, Object[] objArr) {
        if (this.e.size() < 5) {
            this.e.add(new bb(this, method, objArr));
            return;
        }
        if (this.f.size() >= 10) {
            this.f.removeFirst();
        }
        this.f.add(new bb(this, method, objArr));
    }

    private void c() {
        this.e.clear();
        this.f.clear();
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return a(method, objArr);
        } catch (Exception e) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
            return a(method);
        }
    }
}
