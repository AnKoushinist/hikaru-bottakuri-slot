package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public final class gl implements Flushable {
    final Object a = this;
    bc b;
    private final File c;

    public gl(File file) {
        this.c = file;
        try {
            this.b = az.a(new i(file, new bi(this) {
                final /* synthetic */ gl a;

                {
                    this.a = r1;
                }

                public final /* bridge */ /* synthetic */ void a(OutputStream outputStream, Object obj) {
                    dy.c.a(outputStream, (dy) obj);
                }

                public final /* synthetic */ Object b(InputStream inputStream) {
                    return (dy) dy.c.a(inputStream);
                }
            }));
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
        this.b = new ba(new LinkedList());
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

    public final dy b(int i) {
        dy dyVar;
        synchronized (this.a) {
            try {
                dyVar = (dy) this.b.a(i);
            } catch (Exception e) {
                a();
                dyVar = null;
            }
        }
        return dyVar;
    }
}
