package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;

public interface ib extends Closeable, Flushable {
    void a(hr hrVar, long j);

    void close();

    void flush();
}
