package com.b.a.a;

import b.a.a.a.a.c.a.a;
import java.util.Random;

/* compiled from: RandomBackoff */
class l implements a {
    final a a;
    final Random b;
    final double c;

    public l(a aVar, double d) {
        this(aVar, d, new Random());
    }

    public l(a aVar, double d, Random random) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
        } else if (aVar == null) {
            throw new NullPointerException("backoff must not be null");
        } else if (random == null) {
            throw new NullPointerException("random must not be null");
        } else {
            this.a = aVar;
            this.c = d;
            this.b = random;
        }
    }

    public long a(int i) {
        return (long) (a() * ((double) this.a.a(i)));
    }

    double a() {
        double d = 1.0d - this.c;
        return d + (((this.c + 1.0d) - d) * this.b.nextDouble());
    }
}
