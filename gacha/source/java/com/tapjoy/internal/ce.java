package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class ce {
    public static ExecutorService a;
    public static ch b;
    private Future c;

    public abstract Object a(URI uri, InputStream inputStream);

    public abstract String b();

    public abstract String c();

    public Map a() {
        return Collections.emptyMap();
    }

    public String d() {
        return null;
    }

    public Map e() {
        return new LinkedHashMap();
    }

    public final synchronized void a(cj cjVar, ExecutorService executorService) {
        Object obj;
        if (this.c == null || this.c.isDone()) {
            obj = 1;
        } else {
            obj = null;
        }
        String str = "Call has not completed";
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(str));
        }
        this.c = executorService.submit(new cg(this, cjVar));
    }

    public Object f() {
        return b.a(this);
    }
}
