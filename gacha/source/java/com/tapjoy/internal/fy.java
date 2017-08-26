package com.tapjoy.internal;

import com.tapjoy.internal.gd.c;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.util.LinkedList;

public final class fy implements Flushable {
    final Object a = this;
    bb b;
    private final File c;

    public fy(File file) {
        this.c = file;
        try {
            this.b = ay.a(new i(file, new gu(c.b)));
        } catch (Exception e) {
            a();
        }
    }

    final void a() {
        this.c.delete();
        if (this.b instanceof Closeable) {
            try {
                ((Closeable) this.b).close();
            } catch (Exception e) {
            }
        }
        this.b = new az(new LinkedList());
    }

    public final void flush() {
        synchronized (this.a) {
            if (this.b instanceof Flushable) {
                try {
                    ((Flushable) this.b).flush();
                } catch (Exception e) {
                    a();
                }
            }
        }
    }

    public final int b() {
        int size;
        synchronized (this.a) {
            try {
                size = this.b.size();
            } catch (Exception e) {
                a();
                size = 0;
            }
        }
        return size;
    }

    public final boolean c() {
        boolean isEmpty;
        synchronized (this.a) {
            try {
                isEmpty = this.b.isEmpty();
            } catch (Exception e) {
                a();
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    public final void a(int i) {
        synchronized (this.a) {
            try {
                this.b.b(i);
            } catch (Exception e) {
                a();
            }
        }
    }

    public final c b(int i) {
        c cVar;
        synchronized (this.a) {
            try {
                cVar = (c) this.b.a(i);
            } catch (Exception e) {
                a();
                cVar = null;
            }
        }
        return cVar;
    }
}
