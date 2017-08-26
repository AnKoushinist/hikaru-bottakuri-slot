package com.adcolony.sdk;

class bd {
    static bd a = new bd(3, false);
    static bd b = new bd(3, true);
    static bd c = new bd(2, false);
    static bd d = new bd(2, true);
    static bd e = new bd(1, false);
    static bd f = new bd(1, true);
    static bd g = new bd(0, false);
    static bd h = new bd(0, true);
    int i;
    boolean j;
    StringBuilder k = new StringBuilder();

    bd(int i, boolean z) {
        this.i = i;
        this.j = z;
    }

    synchronized bd a(char c) {
        bd bdVar;
        if (!this.j || bf.a) {
            this.k.append(c);
            if (c == '\n') {
                bf.a(this.i, this.k.toString(), this.j);
                this.k.setLength(0);
            }
            bdVar = this;
        } else {
            bdVar = this;
        }
        return bdVar;
    }

    synchronized bd a(String str) {
        bd bdVar;
        if (!this.j || bf.a) {
            if (str == null) {
                this.k.append("null");
            } else {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    a(str.charAt(i));
                }
            }
            bdVar = this;
        } else {
            bdVar = this;
        }
        return bdVar;
    }

    synchronized bd a(Object obj) {
        if (!this.j || (this.j && bf.a)) {
            if (obj == null) {
                a("null");
            } else {
                a(obj.toString());
            }
        }
        return this;
    }

    synchronized bd a(double d) {
        bd bdVar;
        if (!this.j || bf.a) {
            ab.a(d, 2, this.k);
            bdVar = this;
        } else {
            bdVar = this;
        }
        return bdVar;
    }

    synchronized bd a(int i) {
        bd bdVar;
        if (!this.j || bf.a) {
            this.k.append(i);
            bdVar = this;
        } else {
            bdVar = this;
        }
        return bdVar;
    }

    synchronized bd b(Object obj) {
        a(obj);
        return a('\n');
    }

    synchronized bd b(double d) {
        a(d);
        return a('\n');
    }

    synchronized bd b(int i) {
        a(i);
        return a('\n');
    }
}
