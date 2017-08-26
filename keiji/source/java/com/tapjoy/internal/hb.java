package com.tapjoy.internal;

public final class hb {
    public float a;
    public int b;

    public static hb a(String str) {
        if (ct.c(str)) {
            return null;
        }
        try {
            hb hbVar = new hb();
            int length = str.length();
            char charAt = str.charAt(length - 1);
            if (charAt == 'w') {
                hbVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                hbVar.b = 1;
            } else if (charAt == 'h') {
                hbVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                hbVar.b = 2;
            } else {
                hbVar.a = Float.valueOf(str).floatValue();
                hbVar.b = 0;
            }
            return hbVar;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public final float a(float f, float f2) {
        if (this.b == 1) {
            return (this.a * f) / 100.0f;
        }
        if (this.b == 2) {
            return (this.a * f2) / 100.0f;
        }
        return this.a;
    }
}
