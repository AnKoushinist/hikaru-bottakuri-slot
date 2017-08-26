package com.tapjoy.internal;

public final class go {
    public float a;
    public int b;

    public static go a(String str) {
        if (cr.c(str)) {
            return null;
        }
        try {
            go goVar = new go();
            int length = str.length();
            char charAt = str.charAt(length - 1);
            if (charAt == 'w') {
                goVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                goVar.b = 1;
            } else if (charAt == 'h') {
                goVar.a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                goVar.b = 2;
            } else {
                goVar.a = Float.valueOf(str).floatValue();
                goVar.b = 0;
            }
            return goVar;
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
