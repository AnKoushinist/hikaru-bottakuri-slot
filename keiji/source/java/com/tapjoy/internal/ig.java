package com.tapjoy.internal;

public final class ig {
    public static ig a = new ig(null);
    public String b;
    public Throwable c;
    private Object[] d;

    public ig(String str) {
        this(str, null, null);
    }

    public ig(String str, Object[] objArr, Throwable th) {
        this.b = str;
        this.c = th;
        if (th == null) {
            this.d = objArr;
        } else if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        } else {
            int length = objArr.length - 1;
            Object obj = new Object[length];
            System.arraycopy(objArr, 0, obj, 0, length);
            this.d = obj;
        }
    }
}
