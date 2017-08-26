package com.tapjoy.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class dr extends AbstractList implements ds, RandomAccess {
    public static final ds a = new dr().b();
    private final List b;

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        this.b.add(i, (String) obj);
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        return a(this.b.set(i, (String) obj));
    }

    public dr() {
        this.b = new ArrayList();
    }

    public dr(ds dsVar) {
        this.b = new ArrayList(dsVar.size());
        addAll(dsVar);
    }

    public final int size() {
        return this.b.size();
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    public final boolean addAll(int i, Collection collection) {
        if (collection instanceof ds) {
            collection = ((ds) collection).a();
        }
        boolean addAll = this.b.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        this.b.clear();
        this.modCount++;
    }

    public final void a(dk dkVar) {
        this.b.add(dkVar);
        this.modCount++;
    }

    public final dk a(int i) {
        dk dkVar;
        dk dkVar2 = this.b.get(i);
        if (dkVar2 instanceof dk) {
            dkVar = dkVar2;
        } else if (dkVar2 instanceof String) {
            dkVar = dk.a((String) dkVar2);
        } else {
            dkVar = dk.a((byte[]) dkVar2);
        }
        if (dkVar != dkVar2) {
            this.b.set(i, dkVar);
        }
        return dkVar;
    }

    private static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof dk) {
            return ((dk) obj).e();
        }
        return dp.b((byte[]) obj);
    }

    public final List a() {
        return Collections.unmodifiableList(this.b);
    }

    public final ds b() {
        return new ea(this);
    }

    public final /* synthetic */ Object remove(int i) {
        Object remove = this.b.remove(i);
        this.modCount++;
        return a(remove);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.b.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        String e;
        if (obj instanceof dk) {
            dk dkVar = (dk) obj;
            e = dkVar.e();
            if (dkVar.f()) {
                this.b.set(i, e);
            }
            return e;
        }
        byte[] bArr = (byte[]) obj;
        e = dp.b(bArr);
        if (dp.a(bArr)) {
            this.b.set(i, e);
        }
        return e;
    }
}
