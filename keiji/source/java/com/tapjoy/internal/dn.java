package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public abstract class dn {
    public static final dn c = new dn(dk.VARINT, Boolean.class) {
        public final /* synthetic */ Object a(do doVar) {
            int d = doVar.d();
            if (d == 0) {
                return Boolean.FALSE;
            }
            if (d == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", new Object[]{Integer.valueOf(d)}));
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            int i;
            if (((Boolean) obj).booleanValue()) {
                i = 1;
            } else {
                i = 0;
            }
            dpVar.c(i);
        }
    };
    public static final dn d = new dn(dk.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                return dp.a(intValue);
            }
            return 10;
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                dpVar.c(intValue);
            } else {
                dpVar.c((long) intValue);
            }
        }

        public final /* synthetic */ Object a(do doVar) {
            return Integer.valueOf(doVar.d());
        }
    };
    public static final dn e = new dn(dk.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            return dp.a(((Integer) obj).intValue());
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.c(((Integer) obj).intValue());
        }

        public final /* synthetic */ Object a(do doVar) {
            return Integer.valueOf(doVar.d());
        }
    };
    public static final dn f = new dn(dk.VARINT, Integer.class) {
        public final /* synthetic */ int a(Object obj) {
            return dp.a(dp.b(((Integer) obj).intValue()));
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.c(dp.b(((Integer) obj).intValue()));
        }

        public final /* synthetic */ Object a(do doVar) {
            int d = doVar.d();
            return Integer.valueOf((-(d & 1)) ^ (d >>> 1));
        }
    };
    public static final dn g;
    public static final dn h;
    public static final dn i = new dn(dk.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return dp.a(((Long) obj).longValue());
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.c(((Long) obj).longValue());
        }

        public final /* synthetic */ Object a(do doVar) {
            return Long.valueOf(doVar.e());
        }
    };
    public static final dn j = new dn(dk.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return dp.a(((Long) obj).longValue());
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.c(((Long) obj).longValue());
        }

        public final /* synthetic */ Object a(do doVar) {
            return Long.valueOf(doVar.e());
        }
    };
    public static final dn k = new dn(dk.VARINT, Long.class) {
        public final /* synthetic */ int a(Object obj) {
            return dp.a(dp.b(((Long) obj).longValue()));
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.c(dp.b(((Long) obj).longValue()));
        }

        public final /* synthetic */ Object a(do doVar) {
            long e = doVar.e();
            return Long.valueOf((-(e & 1)) ^ (e >>> 1));
        }
    };
    public static final dn l;
    public static final dn m;
    public static final dn n = new dn(dk.FIXED32, Float.class) {
        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.d(Float.floatToIntBits(((Float) obj).floatValue()));
        }

        public final /* synthetic */ Object a(do doVar) {
            return Float.valueOf(Float.intBitsToFloat(doVar.f()));
        }
    };
    public static final dn o = new dn(dk.FIXED64, Double.class) {
        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.d(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }

        public final /* synthetic */ Object a(do doVar) {
            return Double.valueOf(Double.longBitsToDouble(doVar.g()));
        }
    };
    public static final dn p = new dn(dk.LENGTH_DELIMITED, String.class) {
        public final /* synthetic */ int a(Object obj) {
            int i = 0;
            String str = (String) obj;
            int length = str.length();
            int i2 = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt >= '\u0080') {
                    if (charAt < '\u0800') {
                        i2 += 2;
                    } else if (charAt < '\ud800' || charAt > '\udfff') {
                        i2 += 3;
                    } else if (charAt <= '\udbff' && i + 1 < length && str.charAt(i + 1) >= '\udc00' && str.charAt(i + 1) <= '\udfff') {
                        i2 += 4;
                        i++;
                    }
                    i++;
                }
                i2++;
                i++;
            }
            return i2;
        }

        public final /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.a.b((String) obj);
        }

        public final /* synthetic */ Object a(do doVar) {
            return doVar.a.c(doVar.h());
        }
    };
    public static final dn q = new dn(dk.LENGTH_DELIMITED, hu.class) {
        public final /* synthetic */ int a(Object obj) {
            return ((hu) obj).c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            dpVar.a((hu) obj);
        }

        public final /* synthetic */ Object a(do doVar) {
            return doVar.a.b(doVar.h());
        }
    };
    final Class a;
    dn b;
    private final dk r;

    public static final class a extends IllegalArgumentException {
        public final int a;

        a(int i, Class cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.a = i;
        }
    }

    public abstract int a(Object obj);

    public abstract Object a(do doVar);

    public abstract void a(dp dpVar, Object obj);

    public dn(dk dkVar, Class cls) {
        this.r = dkVar;
        this.a = cls;
    }

    public int a(int i, Object obj) {
        int a = a(obj);
        if (this.r == dk.LENGTH_DELIMITED) {
            a += dp.a(a);
        }
        return a + dp.a(dp.a(i, dk.VARINT));
    }

    public void a(dp dpVar, int i, Object obj) {
        dpVar.c(dp.a(i, this.r));
        if (this.r == dk.LENGTH_DELIMITED) {
            dpVar.c(a(obj));
        }
        a(dpVar, obj);
    }

    private void a(hs hsVar, Object obj) {
        dm.a(obj, "value == null");
        dm.a(hsVar, "sink == null");
        a(new dp(hsVar), obj);
    }

    public final byte[] b(Object obj) {
        dm.a(obj, "value == null");
        hs hrVar = new hr();
        try {
            a(hrVar, obj);
            return hrVar.g();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final void a(OutputStream outputStream, Object obj) {
        dm.a(obj, "value == null");
        dm.a(outputStream, "stream == null");
        hs a = hv.a(hv.a(outputStream));
        a(a, obj);
        a.a();
    }

    public final Object a(byte[] bArr) {
        dm.a(bArr, "bytes == null");
        hr hrVar = new hr();
        if (bArr != null) {
            return a(hrVar.a(bArr, 0, bArr.length));
        }
        throw new IllegalArgumentException("source == null");
    }

    public final Object a(InputStream inputStream) {
        dm.a(inputStream, "stream == null");
        return a(hv.a(hv.a(inputStream)));
    }

    private Object a(ht htVar) {
        dm.a(htVar, "source == null");
        return a(new do(htVar));
    }

    public static String c(Object obj) {
        return obj.toString();
    }

    static {
        dn anonymousClass10 = new dn(dk.FIXED32, Integer.class) {
            public final /* synthetic */ void a(dp dpVar, Object obj) {
                dpVar.d(((Integer) obj).intValue());
            }

            public final /* synthetic */ Object a(do doVar) {
                return Integer.valueOf(doVar.f());
            }
        };
        g = anonymousClass10;
        h = anonymousClass10;
        anonymousClass10 = new dn(dk.FIXED64, Long.class) {
            public final /* synthetic */ void a(dp dpVar, Object obj) {
                dpVar.d(((Long) obj).longValue());
            }

            public final /* synthetic */ Object a(do doVar) {
                return Long.valueOf(doVar.g());
            }
        };
        l = anonymousClass10;
        m = anonymousClass10;
    }

    public final dn a() {
        dn dnVar = this.b;
        if (dnVar != null) {
            return dnVar;
        }
        dnVar = new dn(this, this.r, List.class) {
            final /* synthetic */ dn r;

            public final /* synthetic */ int a(int i, Object obj) {
                int i2 = 0;
                List list = (List) obj;
                int i3 = 0;
                while (i2 < list.size()) {
                    i3 += this.r.a(i, list.get(i2));
                    i2++;
                }
                return i3;
            }

            public final /* synthetic */ void a(dp dpVar, int i, Object obj) {
                List list = (List) obj;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.r.a(dpVar, i, list.get(i2));
                }
            }

            public final /* synthetic */ Object a(do doVar) {
                return Collections.singletonList(this.r.a(doVar));
            }

            public final /* synthetic */ void a(dp dpVar, Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }

            public final /* synthetic */ int a(Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }
        };
        this.b = dnVar;
        return dnVar;
    }
}
