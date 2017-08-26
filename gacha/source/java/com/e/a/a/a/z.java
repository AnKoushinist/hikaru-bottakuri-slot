package com.e.a.a.a;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

class z {
    final /* synthetic */ w a;
    private final WeakReference[] b;
    private final LinkedList c;
    private final Method d;

    private z(w wVar, Method method, Object... objArr) {
        int i = 0;
        this.a = wVar;
        this.c = new LinkedList();
        if (objArr == null) {
            objArr = w.a;
        }
        WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
        int length = objArr.length;
        int i2 = 0;
        while (i < length) {
            Object obj = objArr[i];
            if ((obj instanceof Map) || (obj instanceof Integer)) {
                this.c.add(obj);
            }
            int i3 = i2 + 1;
            weakReferenceArr[i2] = new WeakReference(obj);
            i++;
            i2 = i3;
        }
        this.b = weakReferenceArr;
        this.d = method;
    }
}
