package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public final class hv {
    static final Logger a = Logger.getLogger(hv.class.getName());

    private hv() {
    }

    public static ht a(ic icVar) {
        if (icVar != null) {
            return new hx(icVar);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static hs a(ib ibVar) {
        if (ibVar != null) {
            return new hw(ibVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static ib a(final OutputStream outputStream) {
        final id idVar = new id();
        if (outputStream != null) {
            return new ib() {
                public final void a(hr hrVar, long j) {
                    ie.a(hrVar.b, 0, j);
                    while (j > 0) {
                        idVar.a();
                        hy hyVar = hrVar.a;
                        int min = (int) Math.min(j, (long) (hyVar.c - hyVar.b));
                        outputStream.write(hyVar.a, hyVar.b, min);
                        hyVar.b += min;
                        j -= (long) min;
                        hrVar.b -= (long) min;
                        if (hyVar.b == hyVar.c) {
                            hrVar.a = hyVar.a();
                            hz.a(hyVar);
                        }
                    }
                }

                public final void flush() {
                    outputStream.flush();
                }

                public final void close() {
                    outputStream.close();
                }

                public final String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("out == null");
    }

    public static ic a(final InputStream inputStream) {
        final id idVar = new id();
        if (inputStream != null) {
            return new ic() {
                public final long b(hr hrVar, long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            idVar.a();
                            hy c = hrVar.c(1);
                            int read = inputStream.read(c.a, c.c, (int) Math.min(j, (long) (8192 - c.c)));
                            if (read == -1) {
                                return -1;
                            }
                            c.c += read;
                            hrVar.b += (long) read;
                            return (long) read;
                        } catch (AssertionError e) {
                            if (hv.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public final void close() {
                    inputStream.close();
                }

                public final String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("in == null");
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
